package online.blickle.juvr;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UVRReader {

	private String url;
	public UVRReader(String url) {
		this.url = url;
	}
	
	public UVRSimpleData readData() throws Exception{
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_JSON);
		Response res = invBuilder.get();
		
		UVRSimpleData sd = res.readEntity(UVRSimpleData.class);
		
		return sd;
		
	}
}
