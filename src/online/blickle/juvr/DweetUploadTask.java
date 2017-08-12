package online.blickle.juvr;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import online.blickle.scheduler.BTask;
import online.blickle.scheduler.TaskType;

public class DweetUploadTask implements BTask {

	

	@Override
	public void perform() {
		try {
			UVRReader r = new UVRReader("http://192.168.178.33/juvr/v1");
			UVRSimpleData ds = r.readData();
			dweetUpload(ds);

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void dweetUpload(UVRSimpleData ds) throws IOException {
		URL url = new URL("https://dweet.io/dweet/for/tbl-heating-monitor");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// Setting basic post request
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		ObjectMapper om = new ObjectMapper();
		om.writeValue(wr, ds);

		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();

		while ((output = in.readLine()) != null) {
			response.append(output);
		}
		in.close();

		// printing result from response
		System.out.println(responseCode+":"+response.toString());
	}

	@Override
	public TaskType getType() {
		return new TaskType("DweetUpload","Dweet Upload");
	}
	
	public static void main(String args[]) {
		DweetUploadTask d = new DweetUploadTask();
		d.perform();
	}
	
}
