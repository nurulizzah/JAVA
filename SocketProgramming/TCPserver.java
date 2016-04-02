import java.io.*;
import java.net.*;

class TCPServer
{
	public static void main(String argv[]) throws Exception
	{
		TCPServer server = new TCPServer();
		server.run();
	}

	public void run() throws  Exception
	{
		ServerSocket servSock=new ServerSocket(3535);
		Socket ss_accept =  servSock.accept();

		BufferedReader ss_bf = new BufferedReader(new InputStreamReader(ss_accept.getInputStream()));
		String temp= ss_bf.readLine();
		System.out.println(temp);
	}


}
