package java_pro3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NetworkTest3Client {
	Socket socket;
	PrintWriter out;
	BufferedReader reader;
	
	public NetworkTest3Client() {
		 try {
	         socket = new Socket("192.168.0.31", 5000);
	         out = new PrintWriter(socket.getOutputStream(), true);
	         reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"EUC-KR"));
	      } catch (Exception e) {
	         // TODO: handle exception
	      }
	   }
	   
	   public void sendMsg() {
	      try {
	         Scanner scanner = new Scanner(System.in);
	         System.out.println("메세지 입력:");
	         String data = scanner.nextLine();
	      
	         String re_data = reader.readLine();  // server로부터 자료 수신
	         System.out.println("수신자료는 " + re_data);
	         
	      } catch (Exception e) {
	         System.out.println("sendMsg err : " + e);
	      } finally {
	         try {
	            reader.close();
	            out.close();
	            socket.close();
	            
	         } catch (Exception e2) {
	            // TODO: handle exception
	         }
	      }
	   }


			
	public static void main(String[] args) {
		NetworkTest3Client client = new NetworkTest3Client();
		client.sendMsg();
		// client 
		try {
		} catch (Exception e) {
			System.out.println("client err : "+e);
		}

	}

}
