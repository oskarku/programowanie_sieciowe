

    import java.io.*;
    import java.net.*;
     
    public class Klient
    {
       public static final int PORT=50002;
       public static final String HOST = "192.168.0.101";
     
       public static void main(String[] args)                            
       {  try {
    	   
    	  
          //nawiazanie polaczenia z serwerem                                               
          Socket sock;                                                                     
          sock=new Socket(HOST,PORT);                                                      
          System.out.println("Nawiazalem polaczenie: "+sock);
  
          

       
     
          //tworzenie strumieni danych pobieranych z klawiatury i dostarczanych do socketu 
          BufferedReader klaw;                                                             
          klaw=new BufferedReader(new InputStreamReader(System.in));                       
          PrintWriter outp;                                                                
          outp=new PrintWriter(sock.getOutputStream());      
          
          BufferedReader inp;                                                    
          inp=new BufferedReader(new InputStreamReader(sock.getInputStream())); 
    	   
 
          
                                                           
          while (true) {
        	  System.out.println("wyslij do serwera :");
        	  String stro=klaw.readLine();
        	  outp.write(stro);
        	  outp.flush();
        	  String str;                                                            
              str=inp.readLine();
        	  System.out.print("Odebrałem od serwera");
        	  System.out.println(str);
        	  if (str.equals("koniec")) {
        		  outp.println("koniec polaczenia");
        		  break;
        	  }
        	  
        	  
          }
          //zamykanie polaczenia                                                           
          klaw.close();                                                                    
          outp.close();                                                                    
          sock.close(); 
       }
       catch (UnknownHostException o){
    	   System.out.println("Mam problem z poloczeniem lokalnym , sprawdz ip i port");
       } 
       catch (SocketException l) {
    	   System.out.println("Wyglada na to ze mmay problem z polaczeniem , sprawdz kabel od internetu :) ");
       }
       catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       }                                                                                   
    }

