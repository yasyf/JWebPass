package jwebpass;

import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author yasyf
 */
public class JWebPass {

    public static void main(String[] args) throws IOException, Exception {
        try
        {
            
        //URL of passwords document -- see the below as an example
        String passLoc = "http://50.28.29.78/~omeglesp/auth.php";
        //Length of codes/passwords
        int passLength = 4;
        //Delimiter at end of passwords 
        String passDelimStart = "START";
        //Delimiter at end of passwords 
        String passDelimEnd = "END";
        promptResponses responses = new promptResponses();
        WebReader pass = new WebReader(passLoc, passLength, passDelimStart, passDelimEnd);
        //System.out.println(pass.getPasswords());
        String entrance = responses.requestAuth();
        if (entrance.isEmpty()) {
            responses.failedAuth();
        }
        for (int i = 0; i < pass.getPasswords().size(); i++) {

            if (pass.getPasswords().get(i).equals(entrance)) {
               i = pass.getPasswords().size();
                JOptionPane.showMessageDialog(new JDialog(),
                        "Correct ID Entered!",
                        "Authentication Success",
                        JOptionPane.INFORMATION_MESSAGE);
                 System.exit(0);
                 
            } 
            else if (i == pass.getPasswords().size() - 1) {
                JOptionPane.showMessageDialog(new JDialog(),
                        "Incorrect ID Entered",
                        "Authentication Failure",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
                throw new Exception("Authentication Needed");
            }

        }

    }
          catch (NullPointerException ex) {
            
            ex.printStackTrace();
        }
    }
  
}

