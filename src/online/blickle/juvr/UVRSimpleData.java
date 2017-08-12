package online.blickle.juvr;

import java.util.Date;
import java.util.List;

public class UVRSimpleData {

	public List<SimpleDataValue> actors;
	public List<SimpleDataValue> sensors;
	public Date date;
	public String dateAsString;

	public static class SimpleDataValue {
		public String id;
		public String description;
		public double value;
	}
}
