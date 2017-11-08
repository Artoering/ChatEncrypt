package Chat;
import Encrypted.ConvertString;
import Encrypted.encrypt;
import java.awt.Color;
import static java.awt.Color.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashSet;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.paint.Color.color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
/**
 *
 * @author Random
 */
public class Client extends javax.swing.JFrame {
    BufferedReader in;
    PrintWriter out;
    //public static HashSet<PrintWriter> out =new HashSet<PrintWriter>();
    String Nama = getNama();
    private Component jFrame1;
    private static String in_key;
    private encrypt en;
    private final String default_key = "default" ;
    public Client() {
        initComponents();
        jRadioButtonMenuItem2.setSelected(false);
        in_key = default_key;
        taPesan.setEditable(true);
        lbNama.setText(Nama+"'s Is Chatting . . .");
        tfPesan.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ae) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try{ 
                //out.println(tfPesan.getText());
                //out.println(tfPesan.getText()); 
                en= new encrypt(tfPesan.getText(),in_key);
                en.DoEncryption();  
                //JOptionPane.showMessageDialog(null, "en.getEncryption() = "+en.getEncryption());
                //JOptionPane.showMessageDialog(null, "en.getHexaEncryption() = "+en.getHexaEncryption()); 
                out.println(en.getHexaEncryption());  
                
                tfPesan.setText("");
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "terjadi kesalahan = "+ex);
            }
                
            }
        });
    }
    
    private String getServerAddress(){
        String IpAddress=new String();  
        IpAddress = JOptionPane.showInputDialog(null,"Masukkan IP Address dari server : ","Welcome to the chat",JOptionPane.QUESTION_MESSAGE);
        if(IpAddress.isEmpty())
            IpAddress = "127.0.0.1";
        return IpAddress; 
    }
    private String getNama(){
        String name=new String();  
        while(name.isEmpty())
            name = JOptionPane.showInputDialog(null,"Masukkan Username : ","Welcome to the chat",JOptionPane.PLAIN_MESSAGE);        
        return name;      
    }
    
    private String getkey(){
        return Client.in_key;
    }
    
    private void chat() throws IOException {
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress,3231);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        while(true){
            String line = in.readLine();
            if(line.startsWith("SUBMIT_NAME")){
                out.println(Nama);
            }if(line.startsWith("NAME_ACCEPTED")){
                tfPesan.setEditable(true);
            }if(line.startsWith("MASSAGE")){ 
                initialDecrypt(); 
                //taPesan.append(line.substring(7)+"\n");   
                taPesan.append(MsgFrom(line)+Decrypt(line)+"\n");  
            }
        }
    }
    
    private String MsgFrom(String msg){
        int endPoint=0;
        for(int loop=0;loop<msg.length();loop++){
            if(msg.charAt(loop)==':') 
                endPoint=loop+1; 
        }  
        return msg.substring(7,endPoint);
    }
    private String MsgContent(String msg){
        int getContent=0;
        for(int loop=0;loop<msg.length();loop++){
            if(msg.charAt(loop)==':') 
                getContent=loop+2; 
        }  
        return new ConvertString().ToBin(msg.substring(getContent));
    }
    
    private void initialDecrypt(){
        en= new encrypt("null",in_key);
        en.DoEncryption(); 
    }
    private String Decrypt(String content){ 
        en.DoDecryption(MsgContent(content));  
        return en.getDecryption();
    }
    
    public void first() throws IOException{
        Client client =new Client();
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.setVisible(true);
        client.chat();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbNama = new java.awt.Label();
        tfPesan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taPesan = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("jFrame1"); // NOI18N

        lbNama.setText("label1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPesan)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfPesan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        taPesan.setEditable(false);
        taPesan.setColumns(20);
        taPesan.setForeground(new java.awt.Color(255, 0, 0));
        taPesan.setRows(5);
        taPesan.setEnabled(false);
        jScrollPane1.setViewportView(taPesan);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu4.setText("Key");

        jRadioButtonMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.SHIFT_MASK));
        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Default");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Custom Key");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jRadioButtonMenuItem2);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("View");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Key");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(jFrame1, "your key encrypt : "+getkey()); 
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        if(in_key.equals(default_key)){
            jRadioButtonMenuItem1.setSelected(false);
        }else{
            jRadioButtonMenuItem2.setSelected(false);
            in_key = default_key;
        } 
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
        jRadioButtonMenuItem1.setSelected(false);
        in_key = JOptionPane.showInputDialog(null,"Insert Key");
    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed
    public static void main(String args[]) throws IOException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ChatClient01().setVisible(true);
            }
        });
        Client client =new Client();
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.setVisible(true);
        client.chat();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label lbNama;
    private javax.swing.JTextArea taPesan;
    private javax.swing.JTextField tfPesan;
    // End of variables declaration//GEN-END:variables
}
