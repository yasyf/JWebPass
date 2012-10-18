package jwebpass;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author yasyf
 */
public class JWebPass {

    //VARS//
    //URL of passwords document -- see the below as an example
    public static String passLoc = "http://blog.yasyf.com/coding/jwebpassword-protect-java-app-gui-with-server-stored-passwords/";
    //Length of codes/passwords
    public static int passLength = 4;
    //Delimiter at end of passwords 
    public static String passDelimStart = "START";
    //Delimiter at end of passwords 
    public static String passDelimEnd = "END";
    //if you want an offline backup code
    public static boolean offlineAlt = true;
    public static String offlinePass = "42";
    //use system proxies, only if set
    public static boolean useProxies = true;
    //VARS//
    //OBJECTS//
    public static promptResponses responses = new promptResponses();
    //OBJECTS//

    public static void main(String[] args) throws IOException, Exception {

        try {


            WebReader pass = new WebReader(passLoc, passLength, passDelimStart, passDelimEnd);
            //System.out.println(pass.getPasswords());
            String entrance = responses.requestAuth();
            if (entrance.isEmpty()) {
                onFailure("No ID Entered");
            }
            if (WebReader.offline == true) {
                if (entrance.equals(offlinePass)) {
                    onSuccess("Correct ID Entered! (".concat(entrance).concat(")"),pass.getPasswords());
                } else {
                    onFailure("Incorrect ID Entered");
                }
            } else {
                for (int i = 0; i < pass.getPasswords().size(); i++) {

                    if (pass.getPasswords().get(i).equals(entrance)) {
                        i = pass.getPasswords().size();
                        onSuccess("Correct ID Entered! (".concat(entrance).concat(")"),pass.getPasswords());

                    } else if (i == pass.getPasswords().size() - 1) {
                        onFailure("Incorrect ID Entered");
                    }

                }
            }

        } catch (NullPointerException ex) {

            onFailure("Incorrect ID Entered");
        }


    }
    //what to execute on success

    public static void onSuccess(String message, ArrayList passwords) {
        responses.successfulAuth(message);
        responses.successfulAuth("The codes returned were ".concat(passwords.toString()));
        System.exit(0);
    }

    public static void onFailure(String message) throws Exception {
        responses.failedAuth(message);
        System.exit(0);
    }
}
