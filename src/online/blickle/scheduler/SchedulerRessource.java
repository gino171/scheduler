package online.blickle.scheduler;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/schedules")
public class SchedulerRessource  {
	
	@Context ServletContext servletContext;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public ScheduleCommandList getSchedules() throws Exception{
		ScheduleManager mgr = getScheduleManager(servletContext);
		ScheduleCommandList resList =mgr.getScheduledCommandList();
		return  resList;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleCommandList addSchedule(ScheduleCommand cmd) {
		ScheduleManager mgr = getScheduleManager(servletContext);
		mgr.addNewTask(cmd);
		return mgr.getScheduledCommandList();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleCommandList updateSchedule(ScheduleCommand cmd) {
		ScheduleManager mgr = getScheduleManager(servletContext);
		mgr.updateTask(cmd);
		return mgr.getScheduledCommandList();
	}
	
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleCommandList deleteSchedule(@PathParam("id") Integer id) {
		ScheduleManager mgr = getScheduleManager(servletContext);
		mgr.deleteCommand(id);
		System.out.print("Deleted schedule "+id);
		ScheduleCommandList resList =mgr.getScheduledCommandList();
		return  resList;
	}
	
	private ScheduleManager getScheduleManager(ServletContext context) {
		return (ScheduleManager)context.getAttribute(ScheduleManager.KEY);
	}
	
	
}