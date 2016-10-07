package multiThread;

public class TestClient {
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			new Client();
		}
	}
}
