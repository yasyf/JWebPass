package jwebpass;
import java.io.IOException;
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
    public WebReader(String myURL,int passLength, String passDelim) throws IOException {
                     System.setProperty("java.net.useSystemProxies", "true");
                     
  

        try {
            connection = new URL(myURL).openConnection();
            connection.addRequestProperty("User-Agent", 
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter(passDelim);
            if(scanner.hasNext())
            {   
            content = scanner.next();
            }
            else
            {
                throw new Exception("No data was returned -- perhaps you are offline?");
            }
            for (int i = 0; i < content.length(); i += passLength) {
                passwords.add(content.substring(i, i + passLength));
            }
        } 
        catch (StringIndexOutOfBoundsException ex)
        {
            System.out.println("StringIndexOutOfBoundsException: Your passwords/codes are not the correct length");
        }
        catch (Exception ex) {
            
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

