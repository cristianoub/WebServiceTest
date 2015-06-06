package SimpleJavaClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class SimpleHttpClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Usage
		if (args.length < 1)
		{
			System.err.println("Usage: SimpleHttpClient <url>");
			return;
		}
		try {
			//Parse the URL
			URL url = new URL(args[0]);
			String host = url.getHost();
			String parth = url.getPath();
			int port = url.getPort();
			if (port < 0) port = 80;
			
			//send the request
			String request = "GET" + parth + " HTTP/1.1\n";
			request += "host: " + host;
			request += "\n\n";
			Socket sock = new Socket(host, port);
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			writer.print(request);
			writer.flush();
			//read and print the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String next_record = null;
			while ((next_record = reader.readLine()) != null)
                System.out.println(next_record);
            sock.close();	
		}
		catch(MalformedURLException e) {
			throw new RuntimeException("Please try again. Bad URL.\n" + e); }
			catch(UnknownHostException e) {
			throw new RuntimeException("Please try again. Unknown host.\n" + e);
			}
			catch(IOException e) {
			throw new RuntimeException("Please try again. Something's wrong.\n" + e); }
	}

}
