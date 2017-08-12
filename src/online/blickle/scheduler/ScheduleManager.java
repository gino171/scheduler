package online.blickle.scheduler;

import it.sauronsoftware.cron4j.Scheduler;

import java.util.ArrayList;
import java.util.List;

import online.blickle.persist.PersistenceService;

public class ScheduleManager {
	
	private Scheduler scheduler = new Scheduler();
	private ArrayList<String> taskIdList = new ArrayList<String>();
	private TaskManager taskManager;
	private PersistenceService repository = null;
	private ScheduleCommandList scheduleList = new ScheduleCommandList();
	public static String KEY = "online.blickle.scheduler.ScheduleManager";
	
	
	public ScheduleManager(PersistenceService ps, TaskManager tm) {
		repository = ps;
		taskManager = tm;
		scheduler.start();
		readAllSchedulesFromDB();
	}
	
	private void readAllSchedulesFromDB() {
		scheduleList = new ScheduleCommandList();
		
		List<ScheduleCommand> list = repository.performNamedQuery("GET ALL SCHEDULES");
		for (ScheduleCommand cmd : list) {
			applySchedule(cmd);
			scheduleList.addScheduleCommand(cmd);
		}
	}

	private void applySchedule(ScheduleCommand cmd) {
		if ( cmd.getId() != null && !cmd.getId().equals("")) {
			// valid schedule existing
			if (!cmd.getActive()) {
				//Deactivate
				scheduler.deschedule(cmd.getId());
				cmd.setId("");
			}
		} else {
			// not yet scheduled
			if (cmd.getActive()) {
				// activate schedule and store task-id
				String id = this.scheduleTask( cmd.getPattern(), cmd.getCmd());
				cmd.setId(id);
			}
		}
		
	}
	
	private String scheduleTask(String pattern, String taskType) {
		BTask task = taskManager.getTask(taskType);
		if (task == null) {
			//throw new IllegalArgumentException("Tasktype "+taskType+" is unknown.");
			System.out.println("Tasktype "+taskType+" is unknown and ignored.");
		}
		return scheduler.schedule(pattern, new ScheduledTask(task));
	}
	
	
	public ArrayList<String>getTaskIdList() {
		return taskIdList;
	}
	
	public void updateTask(ScheduleCommand cmd) {
		applySchedule(cmd);
		repository.update(cmd);
	}
	
	public void addNewTask(ScheduleCommand cmd) {
		cmd.setId(scheduleTask(cmd.getPattern(), cmd.getCmd()));
		applySchedule(cmd);
		repository.persist(cmd);
		scheduleList.addScheduleCommand(cmd);		
	}
	
	public ScheduleCommandList getScheduledCommandList() {
		return scheduleList;
	}
	
	public void deleteScheduleCommand(String id) {
		ScheduleCommand cmd = scheduleList.getCmdById(id);
		if (cmd == null) {
			throw new IllegalArgumentException("Scheduled Task with id  "+id+ " is unknown.");
		}
		scheduleList.remove(cmd);
		repository.remove(cmd);
		scheduler.deschedule(id);
	}
	
	public void deleteCommand(int dbid){
		ScheduleCommand cmd = scheduleList.getCmdByDbID(dbid);
		String id = cmd.getId();
		if (id != null && !id.equals("")) {
			scheduler.deschedule(id);
		}
		scheduleList.remove(cmd);
		repository.remove(cmd);
	}

	public BTask getTask(String id) {
		return ((ScheduledTask)scheduler.getTask(id)).getBTask();
	}
	
	
	public String getSchedulingPattern(String id) {
		return scheduler.getSchedulingPattern(id).toString();
	}
	
	
	
}
