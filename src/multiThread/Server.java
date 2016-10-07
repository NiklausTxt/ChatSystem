package multiThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
	public static void main(String[] args) {
		Logger logger = LogManager.getLogger(Server.class.getName());
		try {
			logger.info("��������������...");
			ServerSocket serverSocket = new ServerSocket(1000);
			logger.info("�����������ɹ�...");
			logger.info("�ȴ�����...");
			Character c = 'A';
			while (true) {
				
				Socket socket = serverSocket.accept();
				logger.info(c.toString()+"�ɹ����ӷ�����");
				SocketHandler handler = new SocketHandler(socket);
				handler.setName(c.toString());
				handler.start();
				Manager.getManager().add(handler);
				c=(char) (c+1);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
