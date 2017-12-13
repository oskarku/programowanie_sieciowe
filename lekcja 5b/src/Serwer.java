import java.io.*;
import java.net.*;
 
class Odbior extends Thread implements Runnable
{
   Socket sock;
   BufferedReader sockReader;
   String str_odbioru; 
 
   public Odbior(Socket sock) throws IOException                                         
   {                                                                                    
      this.sock=sock;                                                                   
      this.sockReader=new BufferedReader(new InputStreamReader(sock.getInputStream())); 
   } 
   public void Czytanie_danych() throws IOException {
	   this.str_odbioru=this.sockReader.readLine();                                                    
	   System.out.println("<Nadeszlo:> " + this.str_odbioru); 
   }
 
   @Override
   public void run()  
   {     
	 try {
		Czytanie_danych();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 
   }                 
}
class Wysylanie extends Thread implements Runnable{
	PrintWriter outp; 
	Socket sock;
	BufferedReader klaw; 
	String wiadomoasc;
	
	public Wysylanie(Socket sock) throws IOException {
		this.sock=sock;
		this.outp=new PrintWriter(sock.getOutputStream());
		
	}
	public void wyslij() throws IOException {
    this.klaw=new BufferedReader(new InputStreamReader(System.in));
    System.out.print("<Wysylamy:> ");                                                
    this.wiadomoasc=klaw.readLine();                                                      
    outp.println(wiadomoasc);                                                               
    outp.flush(); 
	}
	
	@Override
	public void run() {
		try {
			wyslij();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
 
public class Serwer
{
   public static final int PORT=50007;
 
   public static void main(String args[]) throws IOException      
   {                                                             
      //tworzenie gniazda serwerowego                            
      ServerSocket serv;                                         
      serv=new ServerSocket(PORT);                               
 
      //oczekiwanie na polaczenie i tworzenie gniazda sieciowego 
      System.out.println("Nasluchuje: "+serv);                   
      Socket sock;                                               
      sock=serv.accept();                                        
      System.out.println("Jest polaczenie: "+sock);              
      Odbior odbieracz=new Odbior(sock);
      Wysylanie wysylacz = new Wysylanie(sock);
      while (true) {
      //tworzenie watka odbierajacego                            
      odbieracz.start(); 
      //tworzenie watku wysylajacego
      wysylacz.start();
      if (wysylacz.wiadomoasc.equalsIgnoreCase("koniec")) break;
      }
 
 
 
      //zamykanie polaczenia                                     
      serv.close();                                              
      sock.close();                                              
   }                                                             
}