package jwebpass;

import java.io.IOException;

/**
 *
 * @author yasyf
 */
public class JWebPass {

    public static void main(String[] args) throws IOException, Exception {
        try {

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
                responses.failedAuth("Incorrect ID Entered");
            }
            for (int i = 0; i < pass.getPasswords().size(); i++) {

                if (pass.getPasswords().get(i).equals(entrance)) {
                    i = pass.getPasswords().size();
                    responses.successfulAuth("Correct ID Entered! (".concat(entrance).concat(")"));
                    System.exit(0);

                } else if (i == pass.getPasswords().size() - 1) {
                    responses.failedAuth("Incorrect ID Entered");
                }

            }

        } catch (NullPointerException ex) {

            promptResponses responses = new promptResponses();
            responses.failedAuth("Incorrect ID Entered");
        }
    }
}
