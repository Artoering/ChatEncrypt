/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver_original;
public class arrayprinter {
     
    /** Creates a new instance of arrayprinter */
    public arrayprinter() {
    }
    public static void printarray(String[][] arr,String label) {
       DesPanel.StepsText.append("-- "+label+" -- "+'\n');
        //System.out.println("-- "+label+" -- ");
        for(int i=0;i<arr.length ;i++) {
             DesPanel.StepsText.append("| ");
            for(int j=0;j<arr[0].length;j++) {
                 
                 DesPanel.StepsText.append(arr[i][j]+" ");
            }
             DesPanel.StepsText.append("| "+'\n');
        }
    }
}