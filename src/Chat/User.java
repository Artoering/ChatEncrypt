package Chat;
import Encrypted.ConvertString;
import Encrypted.encrypt;
import chatserver_original.encrypted;
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
public class User extends javax.swing.JFrame {
    BufferedReader in;
    PrintWriter out;
    //public static HashSet<PrintWriter> out =new HashSet<PrintWriter>();
    String Nama = getNama(); 
    private Component jFrame1;
    private static String in_key;
    private encrypt en;
    public static String tempEncry;
    private final String default_key = "default" ;
    private static String temp_Plainttext;
    encrypted enx ;
    public User() {
        initComponents(); 
        jLabelNama.setText(Nama);
        in_key = default_key;
        taPesan.setEditable(true); 
        tfPesan.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent hh) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try{ 
                //out.println(tfPesan.getText());
                //out.println(tfPesan.getText()); 
                temp_Plainttext = tfPesan.getText();
                enx= new encrypted(tfPesan.getText(),in_key); 
                enx.DoEncryption();  
                //JOptionPane.showMessageDialog(null, "enx.getEncryption() = "+enx.getEncryption());
                //JOptionPane.showMessageDialog(null, "enx.getHexaEncryption() = "+enx.getHexaEncryption()); 
                out.println(enx.getHexaEncryption());  
                //JOptionPane.showMessageDialog(null, enx.getHexaEncryption());
                
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
        return User.in_key;
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
                //taPesan.append(MsgFrom(line)+Decrypt(line)+"\n"); 
                //enx= new encrypted(Decrypt(line),in_key); 
                //enx.DoEncryption(); 
                //JOptionPane.showMessageDialog(jFrame1, "dari = "+MsgFrom(line)+"isi = "+Decrypt(line));
                taPesan.append(MsgFrom(line)+Decrypt(line)+"\n"); 
                //dipanjangkan1();
            }
        }
    }
    void dipanjangkan1(){
        String plaintext=temp_Plainttext;
                String keyworD=in_key; 
                //enx= new encrypted(plaintext,keyworD);
                //JOptionPane.showMessageDialog(jFrame1, "plaintext :"+plaintext+"\nkeyworD : "+keyworD);
                //enx.DoEncryption();
                //OriginalText.append("\n "+ enx.getBinCi());
                 
                //CipherText.append(enx.getEncryption());
                //tempEncry = enx.getEncryption();
                //dipanjangkan2();
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
    private String getContent(String msg){
        int getContent=0;
        for(int loop=0;loop<msg.length();loop++){
            if(msg.charAt(loop)==':') 
                getContent=loop+2; 
        }  
        return msg.substring(getContent);
    }
    
    private void initialDecrypt(){
        en= new encrypt("null",in_key);
        en.DoEncryption(); 
    }
    private String Decrypt(String content){ 
        int Dynamic_PlainText = getContent(content).length()/2;
        //JOptionPane.showMessageDialog(jFrame1, "Panjang Text : "+Dynamic_PlainText);
        //new ConvertString().ToBin(Nama); 
        //content.substring(7)
        //enx= new encrypted("pesan1",in_key);
        //enx.DoEncryption();
        //tempEncry = enx.getEncryption();
         
        enx.DoDecryption(MsgContent(content),Dynamic_PlainText); 
        //enx.DoDecryption(getContent(content));
        //DeCipherText.append("\n"+enx.getDecryption());
        //enx.DoDecryption();  
        //JOptionPane.showMessageDialog(null, "dec : "+enx.getDecryption());
        return enx.getDecryption();
        //en.DoDecryption(MsgContent(content));   
        //return en.getDecryption();
    }
    
    public void first() throws IOException{
        User client =new User();
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.setVisible(true);
        client.chat();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taPesan = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfPesan = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabelNama = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("jFrame1"); // NOI18N
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(229, 221, 213));
        jPanel1.setToolTipText("");

        taPesan.setColumns(20);
        taPesan.setForeground(new java.awt.Color(255, 0, 51));
        taPesan.setRows(5);
        taPesan.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        jScrollPane1.setViewportView(taPesan);

        jPanel4.setBackground(new java.awt.Color(44, 189, 165));

        jPanel2.setBackground(new java.awt.Color(44, 189, 165));

        jButton1.setBackground(new java.awt.Color(44, 189, 165));
        jButton1.setForeground(new java.awt.Color(44, 189, 165));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(44, 189, 165));
        jButton3.setForeground(new java.awt.Color(44, 189, 165));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/maximize.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(44, 189, 165));
        jButton2.setForeground(new java.awt.Color(44, 189, 165));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimize.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 90, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton4.setBackground(new java.awt.Color(44, 189, 165));
        jButton4.setForeground(new java.awt.Color(44, 189, 165));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Appwhats.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 591, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(245, 241, 238));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/footer1.png"))); // NOI18N

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/footer3.png"))); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jLabelNama.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(tfPesan, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfPesan)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNama)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(EXIT_ON_CLOSE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //JOptionPane.showMessageDialog(this, "MAXSIMIZE");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(HIDE_ON_CLOSE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String NewKey = new String();
                NewKey = JOptionPane.showInputDialog(jFrame1, "Custom Secret Key");
                    if(NewKey.isEmpty())
                        NewKey = default_key;
                    else
                        in_key = NewKey;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if(jToggleButton1.isSelected())
        JOptionPane.showMessageDialog(jFrame1, "ON");
        else
        JOptionPane.showMessageDialog(jFrame1, "OFF");
    }//GEN-LAST:event_jToggleButton1ActionPerformed
    public static void main(String args[]) throws IOException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ChatClient01().setVisible(true);
            }
        });
        User client =new User();
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.setVisible(true);
        client.chat();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelNama;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextArea taPesan;
    private javax.swing.JTextField tfPesan;
    // End of variables declaration//GEN-END:variables
}
