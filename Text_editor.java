import java.awt.Font;
//import javax.print.PrintException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.lang.System.Logger;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Text_editor extends JFrame implements ActionListener {

    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");

    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem printFile = new JMenuItem("Print");
    JMenuItem exitFile = new JMenuItem("Exit");

    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectall = new JMenuItem("Select All");

    JMenuItem about = new JMenuItem("About");

    JTextArea textArea = new JTextArea();

    Text_editor() {
        setTitle("Text Editor");
        setBounds(200, 200, 900, 800); // to set location
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("editor.png"));
        setIconImage(icon.getImage()); // to set the icons

        setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(exitFile);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);

        help.add(about);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        exitFile.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        about.addActionListener(this);

        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        printFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
        exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,KeyEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            // it reset the page to empty page
            textArea.setText(null);

        } else if (e.getActionCommand().equalsIgnoreCase("Open")) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action = fileChooser.showOpenDialog(null);

            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                // to read the selected file
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    textArea.read(reader, null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {

            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action = fileChooser.showSaveDialog(null); // it describe the two action Save or Cancel
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                // to know which folder user choose to save the file
                String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
                // check whether user give the extension as txt or not
                if (!fileName.contains(".txt")) {
                    fileName += ".txt";
                }
                // to save the file
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    textArea.write(writer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }else if (e.getActionCommand().equalsIgnoreCase("Print")) {

            try {
                textArea.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Text_editor.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("Exit")) {

            System.exit(0);

        } else if (e.getActionCommand().equalsIgnoreCase("Cut")) {

            textArea.cut(); // they are built in for textarea

        } else if (e.getActionCommand().equalsIgnoreCase("Copy")) {

            textArea.copy(); // they are built in for textarea


        } else if (e.getActionCommand().equalsIgnoreCase("Paste")) {

            textArea.paste(); // they are built in for textarea


        } else if (e.getActionCommand().equalsIgnoreCase("Select All")) {

            textArea.selectAll();  // they are built in for textarea


        } else if (e.getActionCommand().equalsIgnoreCase("About")) {

            new About().setVisible(true);

        }

    }

    public static void main(String args[]) throws Exception{

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // to give text editor the new look
        new Text_editor().setVisible(true); // to show the editor because by default it is hidden
    }

}




