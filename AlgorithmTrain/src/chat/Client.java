package chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException {

		Socket client = new Socket("localhost",8888);

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream dis = new DataInputStream(client.getInputStream());
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());

		boolean isRunning = true;
		while(isRunning) {
			String msg = console.readLine();
			System.out.println(msg);
			dos.writeUTF(msg);
			dos.flush();
		}
		dos.flush();
		dos.close();
		client.close();
	}
}
