package jwebpass;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yasyf
 */
public class WebReader {

    String content;
    URLConnection connection;
    ArrayList passwords = new ArrayList();
    String passStart;

    public WebReader(String myURL, int passLength, String passDelimStart, String passDelimEnd) throws IOException, Exception {
        System.setProperty("java.net.useSystemProxies", "true");



        try {
            connection = new URL(myURL).openConnection();
            connection.addRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter(passDelimStart);
            if (scanner.hasNext()) {
                scanner.next();
                passStart = scanner.next();
            } else {
                throw new Exception("No data was returned -- perhaps you are offline?");
            }
            Scanner secondScan = new Scanner(passStart);
            secondScan.useDelimiter(passDelimEnd);
            if (secondScan.hasNext()) {
                content = secondScan.next();

            } else {
                throw new Exception("No data was returned -- perhaps you are offline?");
            }

            for (int i = 0; i < content.length(); i += passLength) {
                passwords.add(content.substring(i, i + passLength));
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("StringIndexOutOfBoundsException: Your passwords/codes are not the correct length");
            promptResponses responses = new promptResponses();
            responses.failedAuth("StringIndexOutOfBoundsException: Your passwords/codes are not the correct length");
        } catch (NoRouteToHostException ex) {
            System.out.println("NoRouteToHostException: You or your server is offline!");
            promptResponses responses = new promptResponses();
            responses.failedAuth("NoRouteToHostException: You or your server is offline!");
        } catch (Exception ex) {

            ex.printStackTrace();
        }


    }

    /**
     * @return the passwords
     */
    public ArrayList getPasswords() {
        return passwords;
    }
}
