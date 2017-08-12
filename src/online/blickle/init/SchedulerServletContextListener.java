package online.blickle.init;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import online.blickle.juvr.DummyTask;
import online.blickle.juvr.DweetUploadTask;
import online.blickle.persist.PersistenceService;
import online.blickle.persist.impl.Repository;
import online.blickle.scheduler.BTask;
import online.blickle.scheduler.ScheduleManager;
import online.blickle.scheduler.TaskManager;

public class SchedulerServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Call on destruction
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Call to initialize
		ServletContext servletContext = event.getServletContext();
		//here I could read from Web.xml
	
		// Manage dependencies
		PersistenceService persistenceService = new Repository();
		servletContext.setAttribute(Repository.KEY, persistenceService);
		
		Collection<BTask> taskTypes = new ArrayList<BTask>();
		taskTypes.add(new DummyTask("Task1"));
		taskTypes.add(new DweetUploadTask());
		TaskManager tm = new TaskManager(taskTypes);
		servletContext.setAttribute(TaskManager.KEY, tm);
		
		ScheduleManager sm = new ScheduleManager(persistenceService,tm);
		servletContext.setAttribute(ScheduleManager.KEY, sm);
		
		
	
	}
}