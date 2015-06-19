package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.omg.CORBA.portable.UnknownException;

public class TestClient {
	public static void main(String args[]){
		System.out.println("Client On");
		
		try{
				while(true){
				String message;
				
				Socket socket = new Socket("127.0.0.1", 5000);
				OutputStream out = socket.getOutputStream();
				message = "0x5001|홍길동|22";
				out.write(message.getBytes());
				socket.close();
				
				Socket socket2 = new Socket("127.0.0.1", 5000);
				OutputStream out2 = socket2.getOutputStream();
				message = "0x6001|hong|1234|홍길동|22|남성";
				out2.write(message.getBytes());
				socket2.close();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}catch(UnknownException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
