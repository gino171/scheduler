package online.blickle.scheduler;

import java.util.Collection;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/tasktypes")
public class TaskTypeRessource {
	
	@Context ServletContext servletContext;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Collection<TaskType> getTypes() {
		TaskManager tm = getTaskManager(servletContext);
		return tm.getTaskTypes();
		
	}

	@GET
	@Path("{cmd}")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleCommand executeNow(@PathParam("cmd") String cmd) {
		TaskManager tm = getTaskManager(servletContext);
		BTask task = tm.getTask(cmd);
		if (task!= null) {
			task.perform();
			return new ScheduleCommand(cmd, task.getType().getDesc(), "");
		}
		throw new IllegalStateException("Task "+cmd+" unknown.");
	}
	
	private TaskManager getTaskManager(ServletContext context) {
		return (TaskManager)context.getAttribute(TaskManager.KEY);
	}
}