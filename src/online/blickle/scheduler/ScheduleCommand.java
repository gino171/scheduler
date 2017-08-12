package online.blickle.scheduler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;


/*
CREATE TABLE ScheduleCommand
(
dbid int AUTO_INCREMENT,
cmd varchar(255),
pattern varchar(255),
PRIMARY KEY (dbid)
);

INSERT INTO ScheduleCommand (cmd,pattern) VALUES ('DummyTask','20 * * * *');

*/

@XmlRootElement
@Entity (name="ScheduleCommand")
@NamedQuery(query = "Select e from ScheduleCommand e", name = "GET ALL SCHEDULES") 
public class ScheduleCommand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	protected Long dbid;
	
	@Column
	protected String cmd;
	
	@Column
	protected String pattern;
	
	@Column
	protected Boolean active=true;

	@Transient
	protected String id;

	public ScheduleCommand() {
		
	}
	
	public ScheduleCommand(String id, String cmd, String pattern) {
		this.id = id;
		this.cmd = cmd;
		this.pattern = pattern;
	}
	
	public Boolean getActive() {
		if (active==null) {
			return true;
		}
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getDbid() {
		return dbid;
	}

	public void setDbid(Long dbid) {
		this.dbid = dbid;
	}

	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
}
