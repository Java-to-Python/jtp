// This class will be used for the GUI of the program
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.net.*; 
import javax.swing.filechooser.*; 
import java.io.*;


class gui {    

  public gui(){
    
  
    //Creating the Frame
    JFrame frame = new JFrame("Java to Python Translator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800,800);
    
    //Creating the MenuBar and adding components
    JMenuBar menuBar = new JMenuBar();
    JMenu menu1 = new JMenu("FILE");
    JMenu menu2 = new JMenu("Help");
    menuBar.add(menu1);
    menuBar.add(menu2);
    JMenuItem menu11 = new JMenuItem("Open");
    JMenuItem menu22 = new JMenuItem("Save as");
    JMenuItem menu33 = new JMenuItem("Github");
    menu1.add(menu11);
    menu1.add(menu22);
    menu2.add(menu33);
    frame.setJMenuBar(menuBar);   
 
//    //Creating Buttons & TextAreas
    JButton translatorButton = new JButton("Translate");       
//    JButton runButton = new JButton("Run");  
    JTextArea textArea = new JTextArea("public class MyClass{ 				\r\n" + 
    		"	public static void main (String[] args){\r\n" + 
    		"		int x = 5;\r\n" + 
    		"		String hi = \"Hello World\";\r\n" + 
    		"	\r\n" + 
    		"	}\r\n" + 
    		" }",15,40);
    JTextArea textArea2 = new JTextArea(" ",15,40);  
    JScrollPane textScrollPane = new JScrollPane(textArea);
    JScrollPane textScrollPane2 = new JScrollPane(textArea2);      
 
    //Creating Panels to hold components
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Java"));
    JPanel panel3 = new JPanel(new BorderLayout());
    panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Python")); 
    JPanel panel2 = new JPanel();
     
    //Adds Components to panel
    panel.add(textScrollPane,BorderLayout.CENTER);
    panel.add(panel2,BorderLayout.SOUTH);
    panel2.add(translatorButton);
    //panel2.add(runButton);      
    panel3.add(textScrollPane2);   
    
    //Adds ActionListioners *button functions*
    
    //Translate Button Function
//    runButton.addActionListener(new ActionListener(){  
//       public void actionPerformed(ActionEvent e){  
//               textArea2.setText("Running...");  
//       }  
//    });  
    translatorButton.addActionListener(new ActionListener(){  
       public void actionPerformed(ActionEvent e){  
    	   textArea2.setText(Writing_class.writer(textArea.getText())); 
       }  
    });
      
    //File Opener
    menu11.addActionListener(new ActionListener(){  
       public void actionPerformed(ActionEvent e){  
         JFileChooser jC = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
         int returnVal = jC.showOpenDialog(frame);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
           File file = jC.getSelectedFile();
           try {
             BufferedReader input = new BufferedReader(new InputStreamReader(
                 new FileInputStream(file)));
             textArea.read(input, "READING FILE :-)");
           } catch (Exception x) {
             x.printStackTrace();
           }
          } else {
            textArea.setText("Operation is CANCELLED :(");
          }
       }  
    }); 
    
    //File Save As
    menu22.addActionListener(new ActionListener(){  
       public void actionPerformed(ActionEvent e){  
               textArea.setText("Saving...");  
       }  
    }); 
    
    //Help ... Github Page
    menu33.addActionListener(new ActionListener(){  
       public void actionPerformed(ActionEvent e){
        
           try{
             URI uri = new URI("https://github.com/Java-to-Python/jtp");  
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
          } catch (Exception evt){}  
       }
    }); 
     
 
    //Adds panels to the frame and shows the program
    frame.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.weightx=1;
    c.gridy=0;
    c.weighty=0.3;
    frame.add(panel,c);
    c.gridy=1;
    c.weighty=0.1;
    frame.add(panel3,c);
    frame.setVisible(true);
  }
 
}
