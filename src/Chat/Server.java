
package Chat;

import static Chat.Server.names;
import static Chat.Server.writers;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashSet; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
 
public class Server {
    private static final int PORT = 3231;
    public static HashSet<String> names =new HashSet<String>();
    public static HashSet<PrintWriter> writers =new HashSet<PrintWriter>();
    public static void main(String[] args) throws IOException {
        System.out.println("The Chat Server is Running");
        ServerSocket listener=new ServerSocket(PORT);
        try{
            while(true){
                new Handler(listener.accept()).start();
            }
        }finally{
            listener.close();
            Client ruin = new Client();
            ruin.first();
        }
    }
}
class Handler extends Thread{
    private String name;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public Handler(Socket socket){
        this.socket = socket;
    }
    
    public void run(){
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            while(true){
                out.println("SUBMIT_NAME");
                name = in.readLine();
                    if(name == null){
                        return;
                    }                   
                    synchronized(names){
                        if(!names.contains(name)){
                            names.add(name);
                            break;
                        }
                    }   
            }
        
            out.println("NAME_ACCEPTED");
            writers.add(out);
            while(true){
                String input = in.readLine();
                    if(input == null){
                        return;
                    }
                    for(PrintWriter writers : writers){
                        //writers.println("MASSAGE"+name+" is chat "+" : "+input); 
                        writers.println("MASSAGE"+name+" is chat "+" : "+input); 
                    }
                    System.out.println("MASSAGE"+name+" is chat "+" : "+input);
            }
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,"Someone Exit Room");
        }finally{
            if(out!=null) writers.remove(out);
        }try{
            socket.close();
        }catch(Exception ex){
            
        }
    }
}