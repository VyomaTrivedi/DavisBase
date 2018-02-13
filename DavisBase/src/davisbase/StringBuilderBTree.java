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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class StringBuilderBTree {
    
    
public void buildLong(String sourceFile,String outputF,int keyLength) throws IOException
{
    String sFile = sourceFile;
    int kLength = keyLength;
    String tempOutput = outputF;
    BufferedWriter output = new BufferedWriter( new FileWriter(new File(tempOutput)) );              
    Scanner sc = null;
    sc = new Scanner(new File(sFile));
   
            while (sc.hasNextLine()) 
            {
                    String result = "";
                    String line = sc.nextLine();
                    String key = line.substring(0, kLength);
                  
                    for(int i =0; i < key.length(); ++i) 
                    {
                     char c = key.charAt(i);
                        
                     if (Character.isLetter(c)) 
                         {
                         result+= (c - 0x0041 + 101);
                         } 
                        else if (Character.isDigit(c)) 
                         {
                          result+= c;
                         } 
                        else throw new RuntimeException("BS");
                    }
                    
                    output.write(result+System.getProperty("line.separator"));
                    
            }
             
}

public void buildString(String outputF,String destFile,int keyLength) throws IOException
{
    String dFile = destFile;
    int kLength = keyLength;
    String tempOutput = outputF;
    BufferedWriter output = new BufferedWriter( new FileWriter(new File(dFile)) );              
    Scanner sc = null;
    sc = new Scanner(new File(tempOutput));
   
            while (sc.hasNextLine()) 
            {
                    String result = "";
                    String line = sc.nextLine();
                    String key = line.substring(0, kLength-1);
                    String key2 = line.substring(kLength-1);
                    int temp = Integer.parseInt(key2);
                    result = key+String.valueOf(Character.toChars(temp));
                    output.write(result+System.getProperty("line.separator"));
                    
            }
             
}        

 
}
