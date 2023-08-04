package java_pro3;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

public class NetworkTest1 {
	public static void main(String[] args) {
		// Network의 개념
		// URL : 네트워크 상에서 자원이 있는 곳을 알려주기 위한 규약
		// url 읽어오기 
		try {
			URL url = new URL("https://www.korea.com");
			InputStream is = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			// System.out.println(br.readLine()); 한 행씩 읽어옴
			
			// korea.com 웹서버에서 읽은 자료를 파일로 저장
			PrintWriter pw = new PrintWriter(System.out, true);
			PrintWriter fw = new PrintWriter(new FileOutputStream("c:/work/kor.html"));
			
			 
			String line = "";
			
			while((line = br.readLine()) != null) {
				System.out.println(line); // 표준 충력장치로 출력
				// 파일로 저장
				pw.println(line);
				fw.println(line);
				fw.flush();
			}
			br.close(); is.close(); pw.close(); fw.close();
			
			
		} catch (Exception e) {
			System.out.println("er : " +e);
		}

	}

}
