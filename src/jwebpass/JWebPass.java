package jwebpass;

import java.io.IOException;

/**
 *
 * @author yasyf
 */
public class JWebPass {

    public static void main(String[] args) throws IOException {
        //URL of passwords document
        String passLoc = "http://50.28.29.78/~omeglesp/auth.php";
        //Length of codes/passwords
        int passLength = 4;
        
        WebReader pass = new WebReader(passLoc, passLength);
        System.out.println(pass.getPasswords());
    }
}
