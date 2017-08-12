package online.blickle.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {

	public static final String KEY = "online.blickle.scheduler.TaskManager";
	
	protected Collection<TaskType> taskTypeList = new ArrayList<TaskType>();
	protected Map<String, BTask> taskMap = new HashMap<String,BTask>();
	public TaskManager(Collection<BTask> taskList) {
		
		for (BTask task:taskList) {
			this.add(task);
		}
		//this.add(new DummyTask("Dummy Task#171"));
		
		//this.add(new DweetUploadTask());
		
	}
	
	
	private void add(BTask task) {
		taskTypeList.add(task.getType());
		BTask knownTask = taskMap.put(task.getType().getType(), task);
		if (knownTask != null) {
			throw new IllegalArgumentException("Tried to add task with same ID twice: "+task.getType().getType());
		}
	}
	
	public Collection<TaskType> getTaskTypes() {
		return taskTypeList;
	}
	
	public BTask getTask(String type) {
		return taskMap.get(type);
	}
	
	public BTask getTask(TaskType type) {
		return taskMap.get(type.getType());
	}

	public boolean isTask(String type) {
		return null != taskMap.get(type);
	}

}
