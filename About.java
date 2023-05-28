import java.awt.Font;
import javax.swing.*;

public class About extends JFrame {

    About() {
        setBounds(100, 100, 500, 400);
        setTitle("About the Text Editor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("Notepad_icon.svg.png"));
        setIconImage(icon.getImage()); // to set the icons
        setLayout(null);

        JLabel textLabel = new JLabel(
                "<html>Welcome to Text Editor <br/> This is a simple text editor for Microsoft Windows and a basic text-editing program which enables computer users to create documents.<br />This editor created using Java Swings.<br />All rights editor@2023</html>");
        textLabel.setBounds(100, 50, 350, 300);
        textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12)); // to change the font of text
        add(textLabel);
    }

    public static void main(String args[]) {
        new About().setVisible(true);
    }
}
