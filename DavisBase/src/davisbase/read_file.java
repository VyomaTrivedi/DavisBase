/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package davisbase;

/**
 *
 * @author VYOMA
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class read_file {
    
  public String[][] read_File(String Filename) throws IOException
  {          
    
    String filename = Filename;
    int c = count_lines.count_file_line(filename);
    //System.out.println("Lines: "+c);
            
    Scanner sc = null;
    sc = new Scanner(new File(filename));
    String[][] input_data = new String[c][2];

    for(int x = 0 ; x<c ; x++)
    {		
        String line = sc.nextLine();
        String key = line.substring(0, 15);
        String value = line.substring(15);
            
        for(int z = 0; z < 2; z++)
        {
            if(z==0)
            {
            input_data [x][z] = key;
            }
            else
            {
            input_data [x][z] = value;
            }
        //System.out.println( input_data[x][z].toString() );
        }
    }
    
    return input_data;

          
   }

   

} 
    


