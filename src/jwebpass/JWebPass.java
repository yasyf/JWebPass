package jwebpass;

import java.io.IOException;

/**
 *
 * @author yasyf
 */
public class JWebPass {

    public static void main(String[] args) throws IOException {
        //URL of passwords document -- see the below as an example
        String passLoc = "http://50.28.29.78/~omeglesp/auth.php";
        //Length of codes/passwords
        int passLength = 4;
        //Delimiter at end of passwords 
        String passDelimStart = "START";
        //Delimiter at end of passwords 
        String passDelimEnd = "END";
        
        WebReader pass = new WebReader(passLoc, passLength, passDelimStart,passDelimEnd);
        System.out.println(pass.getPasswords());
    }
}
