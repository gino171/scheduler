package online.blickle.scheduler;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class TaskType {
	public String type;
	public String desc;


	public TaskType() {
	}
	
	public TaskType(String type, String desc) {
		this.type=type;
		this.desc= desc;
		
	}
	
	public String getType() {
		return type;
	}
	
	public String getDesc() {
		return desc;
	}
	
}