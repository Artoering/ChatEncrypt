package Encrypted;


import java.util.Scanner; 

public class ConvertString{       
    public String ToBin(String hex){
        String bin = "";
        String binFragment = "";
        int iHex;
        hex = hex.trim();
        hex = hex.replaceFirst("0x", "");

        for(int i = 0; i < hex.length(); i++){
            iHex = Integer.parseInt(""+hex.charAt(i),16);
            binFragment = Integer.toBinaryString(iHex);

            while(binFragment.length() < 4){
                binFragment = "0" + binFragment;
            }
            bin += binFragment;
        }
        return bin;
    }
    
    public String toHexa(String binary){
        String hexa = new String();
        int digitNumber = 1; 
        int sum = 0;
        int test = binary.length()%4;
        if(test!=0) {
            binary = padLeft(binary, test);
        }
        for(int i = 0; i < binary.length(); i++){
            if(digitNumber == 1)
                sum+=Integer.parseInt(binary.charAt(i) + "")*8;
            else if(digitNumber == 2)
                sum+=Integer.parseInt(binary.charAt(i) + "")*4;
            else if(digitNumber == 3)
                sum+=Integer.parseInt(binary.charAt(i) + "")*2;
            else if(digitNumber == 4 || i < binary.length()+1){
                sum+=Integer.parseInt(binary.charAt(i) + "")*1;
                digitNumber = 0;
                if(sum < 10)
                    hexa += sum;  
                else if(sum == 10) 
                    hexa += "A";
                else if(sum == 11)
                    hexa += "B"; 
                else if(sum == 12)
                    hexa += "C";
                else if(sum == 13)
                    hexa += "D";
                else if(sum == 14)
                    hexa += "E";
                else if(sum == 15)
                    hexa += "F";
                sum=0;
            }
            digitNumber++;  
        } 
        return hexa;
    }
    public static String padLeft(String s, int n) {
        return String.format("%0$"+n+"s", s);
    } 
} 