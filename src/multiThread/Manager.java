package multiThread;

import java.util.ArrayList;
import java.util.List;

public class Manager {
	private Manager() {}
	private static final Manager m = new Manager();
	public static Manager getManager(){
		return m;
	}
	
	List<SocketHandler> list = new ArrayList<>();
	public void add(SocketHandler sh){
		list.add(sh);
	}
	public void remove(SocketHandler sh){
		list.remove(sh);
	}
	
	public void publish(SocketHandler socketHandler, String msg){
		for(int i=0;i<list.size();i++){
			SocketHandler sHandler = list.get(i);
			if(!sHandler.equals(socketHandler)){
				sHandler.out(msg);
			}
		}
		
	}
	
	
}
