package jwebpass;

import java.io.IOException;

/**
 *
 * @author yasyf
 */
public class JWebPass {
//URL of passwords document -- see the below as an example

    public static String passLoc = "http://50.28.29.78/~omeglesp/auth.php";
    //Length of codes/passwords
    public static int passLength = 4;
    //Delimiter at end of passwords 
    public static String passDelimStart = "START";
    //Delimiter at end of passwords 
    public static String passDelimEnd = "END";
    //if you want an offline backup code
    public static boolean offlineAlt = true;
    public static String offlinePass = "42";
    public static promptResponses responses = new promptResponses();

    public static void main(String[] args) throws IOException, Exception {

        try {


            WebReader pass = new WebReader(passLoc, passLength, passDelimStart, passDelimEnd);
            //System.out.println(pass.getPasswords());
            String entrance = responses.requestAuth();
            if (entrance.isEmpty()) {
                responses.failedAuth("Incorrect ID Entered");
            }
            if(WebReader.offline == true)
            {
              if(entrance.equals(offlinePass)) 
              {
                  responses.successfulAuth("Correct ID Entered! (".concat(entrance).concat(")"));
                    System.exit(0);
              }
              else
              {
                  responses.failedAuth("Incorrect ID Entered");
              }
            }
            else
            {
            for (int i = 0; i < pass.getPasswords().size(); i++) {

                if (pass.getPasswords().get(i).equals(entrance)) {
                    i = pass.getPasswords().size();
                    responses.successfulAuth("Correct ID Entered! (".concat(entrance).concat(")"));
                    System.exit(0);

                } else if (i == pass.getPasswords().size() - 1) {
                    responses.failedAuth("Incorrect ID Entered");
                }

            }
            }

        } catch (NullPointerException ex) {

            promptResponses responses = new promptResponses();
            responses.failedAuth("Incorrect ID Entered");
        }

  
    }
  
}
