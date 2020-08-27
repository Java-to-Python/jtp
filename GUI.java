// This class will be used for the GUI of the program
import javax.swing.*;
import java.awt.*;

class gui
{
  public static void main(String args[])
  {
    //Creating the Frame
    JFrame frame = new JFrame("Java to Python trranslator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300,300);
    
    //Creating the MenuBar and adding components
    JMenuBar menuBar = new JMenuBar();
    JMenu menu1 = new JMenu("FILE");
    JMenu menu2 = new JMenu("Help");
    menuBar.add(menu1);
    menuBar.add(menu2);
    JMenuItem menu11 = new JMenuItem("Open");
    JMenuItem menu22 = new JMenuItem("Save as");
    menu1.add(menu11);
    menu1.add(menu22);
    
    //Text to show what text will be translated
    JTextArea textArea = new JTextArea();
    
    //Button for translating the java file to python
    JPanel panel = new JPanel();
    JButton button = new JButton("Translate");
    panel.add(button);
    
    //Addes components to the frame and shows the program
    frame.getContentPane().add(BorderLayout.SOUTH, panel);
    frame.getContentsPane().add(BoarderLayout.NORTH, menuBar);
    frame.getContentsPane().add(BoarderLayout.CENTER, textArea);
    frame.setVisible(true);
  }
}
