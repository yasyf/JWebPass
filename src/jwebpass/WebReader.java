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

    private String content;
    private URLConnection connection;
    private ArrayList passwords = new ArrayList();
    private String passStart;
    public static boolean offline = false;

    public WebReader(String myURL, int passLength, String passDelimStart, String passDelimEnd) throws IOException, Exception {

        if (JWebPass.useProxies) {
            System.setProperty("java.net.useSystemProxies", "true");
        }
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
            JWebPass.responses.failedAuth("StringIndexOutOfBoundsException: Your passwords/codes are not the correct length");
        } catch (NoRouteToHostException ex) {
            System.out.println("NoRouteToHostException: You or your server is offline!");
            if (JWebPass.offlineAlt == false) {
                JWebPass.responses.failedAuth("NoRouteToHostException: You or your server is offline!");
            } else {
                offline = true;
            }


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
