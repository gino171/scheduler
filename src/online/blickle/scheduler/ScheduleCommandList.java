package online.blickle.scheduler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScheduleCommandList {

	
	@XmlElementWrapper(name = "scheduleCommandList")
	@XmlElement(name = "scheduleCommand")
	protected ArrayList<ScheduleCommand> list = new ArrayList<ScheduleCommand>();

	
	public ScheduleCommandList() {
	}
	
	public ScheduleCommandList(List<ScheduleCommand> list) {
		this.list.addAll(list);
	}
	
	public void addScheduleCommand(ScheduleCommand cmd) {
		list.add(cmd);
	}
	
	public ScheduleCommand getCmdById(String id) {
		for (ScheduleCommand cmd: list) {
			if (cmd.getId() != null && cmd.getId().equals(id)) {
				return cmd;
			}
		}
		return null;
	}
	
	public ScheduleCommand getCmdByDbID(int id) {
		for (ScheduleCommand cmd: list) {
			if (cmd.getDbid() != null && cmd.getDbid()==id) {
				return cmd;
			}
		}
		return null;
	}
	
	public void remove(ScheduleCommand cmd) {
		this.list.remove(cmd);
	}

	
	

}
