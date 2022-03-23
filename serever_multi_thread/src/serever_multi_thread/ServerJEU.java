package serever_multi_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerJEU extends Thread 
{
    private int nbrClient=0;
    private int nombresucrete ;
    private boolean fin = false;
    private String gagnant;
 
    
	public static void main(String[] args)
	{
		new ServerJEU().start();

	}
	
     public void run() {
    	 try {
			ServerSocket ss = new ServerSocket(3456);
			System.out.println("demarage de serveur .................................");
			nombresucrete =  new Random().nextInt(1000);
			System.out.println("nombresucrete = "+nombresucrete);
			
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
				pw.println("chose a number .............");
				while(true)
				{
					String req= bf.readLine();
					
					System.out.println("le client :"+IP+ "chose : "+req);
					int nombre = 0;
					try {
						nombre = Integer.parseInt(req);
					}catch(NumberFormatException e)
					{
						pw.println("Number Format invalid ");
						System.out.println("Number Format invalid ");
						continue;
					}
					if (fin == false) {
								if(nombre >nombresucrete)
								{
									    pw.println("votre nombre est superieur");
								}else if(nombre < nombresucrete) {
									    pw.println("votre nombre est inferieur");
								}else {
								
										pw.println("you win ........");
										gagnant = IP;
										System.out.println(" we have a winner : "+IP);
								        fin = true; 
								}
					}else {
						pw.println(" we already have a winner  wich is "+IP);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    	 }
     }
     
}
