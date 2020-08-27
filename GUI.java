// This class will be used for the GUI of the program
import javax.swing.JFrame;
import java.awt.Container;

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
    JMenuItem m11 = new JMenuItem("Open");
    JMenuItem m22 = new JMenuItem("Save as");
    menu1.add(m11);
    menu1.add(m22);
    
    JButton button = new JButton("Press");
    
    frame.getContentPane().add(button); // Adds Button to content pane of frame
    
    frame.setVisible(true);
  }
}
