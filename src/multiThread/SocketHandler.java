package multiThread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SocketHandler extends Thread {

	Socket socket;
//	String[] sensitive = {"����","����","ϰ��ƽ","����","С��"};
	
	public SocketHandler(Socket socket) {
		this.socket = socket;
	}

	Logger logger = LogManager.getLogger(Server.class.getName());
	public void out(String str) {
		try {
			socket.getOutputStream().write((Thread.currentThread().getName()+":"+str + "\n").getBytes());// �������Է������˵�����
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		boolean shouldContinue = true;
		String msg;
		BufferedReader input;
		
		SensitiveFile sensitiveFile = new SensitiveFile();
		sensitiveFile.start();
		
		while (shouldContinue) {
			try {
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				msg = input.readLine();
				
				for(String s:sensitiveFile.getList()){
					if(msg.contains(s)){
						msg="�������������Ϣ���ѱ�����";
						break;
					}
				}
				
				if ("bye".equals(msg)) {
					String string = "���������ˣ�bye";
					Manager.getManager().publish(this, string);
					Manager.getManager().remove(this);
					
					logger.info(Thread.currentThread().getName()+"�Ѿ�����");
					
				}else{
//					logger.info(Thread.currentThread().getName()+":"+msg);
					Manager.getManager().publish(this, msg);
				}
				

			} catch (IOException e) {
				e.getStackTrace();
			}

		}
		
	}
}