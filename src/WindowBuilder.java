import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class WindowBuilder {

	private JFrame frame;
	private Panel ImagePanel;
	private JButton btnNewButton_1;
	private String FileName;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilder window = new WindowBuilder();
					window.frame.setVisible(true);
				
					//window.AddImage(FileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    JButton btnNewButton = new JButton("Add Picture");
	    btnNewButton.setBounds(406, 528, 89, 23);
	    ImagePanel = new Panel();
	    ImagePanel.setBounds(86, 10, 625, 674);
	    frame.getContentPane().add(ImagePanel);
	    
	    btnNewButton_1 = new JButton("Add Image");
	    btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		FileName = textField.getText();
	    		AddImage();
	    	}
	    });
	    btnNewButton_1.setBounds(382, 727, 89, 23);
	    frame.getContentPane().add(btnNewButton_1);
	    
	    textField = new JTextField();
	    textField.setBounds(360, 696, 129, 23);
	    frame.getContentPane().add(textField);
	    textField.setColumns(10);
	   
	    JLabel lblTypeFileName = new JLabel("Type File Name");
	    lblTypeFileName.setBounds(264, 700, 89, 14);
	    frame.getContentPane().add(lblTypeFileName);
		
	}
	
	public void AddImage() {
	    BufferedImage image;
		try {
			image = ImageIO.read(new File(FileName));
			ImagePanel.setLayout(null);
		    JLabel label = new JLabel(new ImageIcon(image));
		    label.setBounds(72, 10, 625, 674);
		    ImagePanel.add(label);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.getContentPane().add(ImagePanel);
	}
}