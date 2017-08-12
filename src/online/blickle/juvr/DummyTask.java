package online.blickle.juvr;

import online.blickle.scheduler.BTask;
import online.blickle.scheduler.TaskType;

public class DummyTask implements BTask {

	private String name;
	public DummyTask(String name) {
		this.name = name;
	}
	@Override
	public void perform() {
		System.out.println("Performed task "+name);
		
	}
	
	@Override
	public TaskType getType() {
		return new TaskType(name,name);
	}
}
