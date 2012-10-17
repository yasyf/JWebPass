package jwebpass;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author yasyf
 */
public class promptResponses {

    public String requestAuth() {
        return JOptionPane.showInputDialog(new JPasswordField(), "Enter Your ID Code", "Authentication Needed",
                JOptionPane.WARNING_MESSAGE);
    }

    public void failedAuth() throws Exception {
        JOptionPane.showMessageDialog(new JDialog(),
                "Incorrect ID Entered",
                "Authentication Failure",
                JOptionPane.ERROR_MESSAGE);
        System.exit(1);
        throw new Exception("Authentication Needed");
    }

    public void successfulAuth() {
        JOptionPane.showMessageDialog(new JDialog(),
                "Correct ID Entered!",
                "Authentication Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
