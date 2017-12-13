import java.io.*;
import java.net.*;
 
public class Serwer
{
   public static final int PORT= 50002;
 
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
 
      //tworzenie strumienia danych pobieranych z gniazda sieciowego         
      BufferedReader inp;                                                    
      inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));  
 
      //Opcje odczytu i zapisu z i do strumienia
   
      PrintWriter outp; 
      outp=new PrintWriter(sock.getOutputStream()); //zapis
      
      
      //komunikacja - czytanie danych ze strumienia                                                 
      BufferedReader klaw;                                                             
      klaw=new BufferedReader(new InputStreamReader(System.in));
      while (true) { 
    	  System.out.print("jestem w petli");
          String str=inp.readLine();
    	  System.out.println("Serwer, odczyt: [ "+str+" ]");
    	  if(str.equals("koniec")) {
    		  outp.println("KONIEC POLONCZENIA");
    		  break;
    	  }
    	  System.out.println("wyslij do klienta :");
    	  String stro=klaw.readLine();
    	  outp.println(stro);
    	  outp.flush();
    	  
    	  
      }
 
      //zamykanie polaczenia                                                 
      inp.close();                                                           
      sock.close();                                                          
      serv.close();                                                          
   }                                                                         
}