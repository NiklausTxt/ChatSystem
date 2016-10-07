package multiThread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public boolean shouldContinue = true;
	
	public static void main(String[] args) {
		
		try {
			System.out.println("等待连接...");
			// Socket socket = new Socket("192.168.0.100", 6666);
			Socket socket = new Socket("localhost", 1000);

			System.out.println("连接成功...");

			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			Scanner sc = new Scanner(System.in);
			boolean shouldContinue = true;
			String clientMsg;
			InputHandler inputHandler = new InputHandler(input);
			inputHandler.start();
			while (shouldContinue) {
				clientMsg = sc.nextLine();
				// System.out.println("client:" + clientMsg);
				output.write(clientMsg + "\r");
				output.flush();
				if ("bye".equals(clientMsg)) {
					shouldContinue = false;
				}
			}
			sc.close();
			// socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
