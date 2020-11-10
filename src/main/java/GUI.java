// This class will be used for the GUI of the program

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
//import javax.jnlp.*;

class gui {

    public gui() {

        //Creating the Frame
        JFrame frame = new JFrame("Java to Python Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

        //Creating Buttons & TextAreas
        JButton translatorButton = new JButton("Translate");
        
        //JButton runButton = new JButton("Run");  
        JTextArea textArea = new JTextArea("public class MyClass{ 				\r\n" + 
        		"	public static void main (String[] args){\r\n" + 
        		"		System.out.println(\"Hello World\");\r\n" + 
        		"		int x=5;\r\n" + 
        		"		String hi=\"Idk\";\r\n" + 
        		"		while(x>0){\r\n" + 
        		"			System.out.println(hi);\r\n" + 
        		"			x--;\r\n" + 
        		"		}\r\n" + 
        		"	}\r\n" + 
        		" }\r\n" + 
        		"", 15, 40);
        JTextArea textArea2 = new JTextArea("", 15, 40);
        JScrollPane textScrollPane = new JScrollPane(textArea);
        JScrollPane textScrollPane2 = new JScrollPane(textArea2);

        //Creating Panels to hold components
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Java"));
        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Python"));
        JPanel panel2 = new JPanel();

        //Adds Components to panel
        panel.add(textScrollPane, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.SOUTH);
        panel2.add(translatorButton);
        //panel2.add(runButton);      
        panel3.add(textScrollPane2);

        //Translate Button Function
        translatorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	textArea2.setText("");
            	textArea.validate();
            	String text=(Java_To_Python_Translator.translate(textArea.getText()));
                textArea2.setText(text);
            }
        });

        //File Opener
        menu11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
                JFileChooser jC = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Java","java");
            	jC.addChoosableFileFilter(filter);
            	jC.setFileFilter(filter);
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
        menu22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser jC = new  JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            	FileNameExtensionFilter filter = new FileNameExtensionFilter("Python","py");
            	jC.addChoosableFileFilter(filter);
            	jC.setFileFilter(filter);
          	   	int returnVal = jC.showSaveDialog(frame);
          	   	if (returnVal == JFileChooser.APPROVE_OPTION) {
                   File file = jC.getSelectedFile();
                   
                   //Change to .py file
                   if(!file.getName().contains(".py")) { 
                	   File renamedFile = new File(file.getName() + ".py"); 
                	   file.renameTo(renamedFile);
                	   renamedFile.delete();
            	   }
                   
                   try {
      				if(file.createNewFile()) {
      					System.out.println( "Created new file:" + file.getName());
      				 } else {
      					 System.out.println("Failed to create");
      				 }
                   } catch (IOException e1) {
      				System.out.println("*Error Saving Python File*****");
      				e1.printStackTrace();
                   }
                   try {
                  	FileWriter fW = new FileWriter(file);
      				fW.write(textArea2.getText());
      				fW.close();
                   }catch (IOException z) {
                  	 System.out.println("*Failed to copy information to new file*");
                  	 z.printStackTrace();
                   }
                 }
            }
        });

        //Help ... Github Page
        menu33.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    URI uri = new URI("https://github.com/Java-to-Python/jtp");
                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(uri);
                } catch (Exception evt) {
                }
            }
        });

        //Adds panels to the frame and shows the program
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridy = 0;
        c.weighty = 0.3;
        frame.add(panel, c);
        c.gridy = 1;
        c.weighty = 0.1;
        frame.add(panel3, c);
        frame.setVisible(true);
    }
}