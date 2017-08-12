package online.blickle.scheduler;

import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;

public class ScheduledTask extends Task{

	private BTask task;
	public ScheduledTask(BTask taskToSchedule) {
		this.task = taskToSchedule;
	}

	@Override
	public void execute(TaskExecutionContext context) throws RuntimeException {
		this.task.perform();
	}
	
	public BTask getBTask() {
		return this.task;
	}
	
}
