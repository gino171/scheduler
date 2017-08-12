package online.blickle.scheduler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class TaskManagerTest {
	
	@Test
	public void taskManager_addSameTaskMultipleTimes_cannotBeCreated() {
		Collection<BTask> tasks = new ArrayList<BTask>();
		tasks.add(new BTestTask("T1"));
		tasks.add(new BTestTask("T1"));
		try {
			TaskManager tmManager = new TaskManager(tasks);
			assertTrue("Multiple Tasks of same type not allowed", false);
		}catch (IllegalArgumentException ex) {
			assertTrue(true);
		}
	}
	
	@Test
	public void taskManager_getTaskByName_TaskObjectIsCorrect() {
		Collection<BTask> tasks = new ArrayList<BTask>();
		BTestTask t1 = new BTestTask("T1");
		BTestTask t2 = new BTestTask("T2");
		tasks.add(t1);
		tasks.add(t2);
		TaskManager tm = new TaskManager(tasks);
		
		// Call 2 times to increase counter
		tm.getTask("T1").perform();
		tm.getTask("T1").perform();
		
		assertEquals(2, t1.getPerformCount());
		
	}
	
	private Collection<BTask> getTestTasks() {
		Collection<BTask> tasks = new ArrayList<BTask>();
		tasks.add(new BTestTask("T1"));
		tasks.add(new BTestTask("T2"));
		tasks.add(new BTestTask("T3"));
		return tasks;
	}

	public static class BTestTask implements BTask {

		private int performCount = 0;
		private String type;
		
		public BTestTask (String type) {
			this.type=type;
		}
		
		@Override
		public void perform() {
			performCount++;
			
		}
		
		public int getPerformCount() {
			return performCount;
		}

		@Override
		public TaskType getType() {
			return new TaskType(type,"Test Task of type "+type);
		}
		
	}
}
