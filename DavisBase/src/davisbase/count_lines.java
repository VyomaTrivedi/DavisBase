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
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class count_lines {
    
     public static int count_file_line(String name) throws IOException {
    InputStream is = new BufferedInputStream(new FileInputStream(name));
    try {
        byte[] c = new byte[1024];
        int count = 0;
        int read_char = 0;
        boolean empty = true;
        while ((read_char = is.read(c)) != -1) {
            empty = false;
            for (int i = 0; i < read_char; ++i) {
                if (c[i] == '\n') {
                    ++count;
                }
            }
        }
        return (count == 0 && !empty) ? 1 : count;
    } finally {
        is.close();
    }
    
     }
}
    

