package serever_multi_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMt extends Thread 
{
    private int nbrClient=0;
    
 
    
	public static void main(String[] args)
	{
		new ServerMt().start();

	}
	
     public void run() {
    	 try {
			ServerSocket ss = new ServerSocket(3456);
			System.out.println("demarage de serveur .................................");
			while(true)
			    {
					Socket socket = ss.accept();
					nbrClient++;
					new Conversation(socket ,  nbrClient).start();
			    }
		      } 
    	 catch (IOException e) 
    	 {
			
			e.printStackTrace();
		 }
     }
     
     class Conversation extends Thread
     {
    	 private Socket socket ;
    	 private int nbrClient ;

		public Conversation(Socket socket , int num)
		{
			super();
			this.socket = socket;
			this.nbrClient = num;
		}
		
    	public void run()
    	 {
    		 try {
				BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter    pw = new PrintWriter   (socket.getOutputStream(),true);
				String IP = socket.getRemoteSocketAddress().toString();
				
				System.out.println("connexion de client num "+nbrClient+"ip = "+IP);
				pw.println("welcome you are the client num "+nbrClient);
				while(true)
				{
					String req= bf.readLine();
					System.out.println("i recuve this requet : " + req);
					pw.println(req.length());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    	 }
     }
     
}
