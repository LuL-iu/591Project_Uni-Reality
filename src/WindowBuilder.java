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
/**
 * this is a window interaction class, it can add image and display image, apply filter and add emoji
 * @author liulu
 *
 */
public class WindowBuilder {

	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private Panel ImagePanel;
	private JButton btnNewButton_1;
	private JTextField textField;
	private JButton btnNewButton_2;
	private BufferedImage originImage;
	private JButton btnClear;

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
	    //Add image button
	    btnNewButton_1 = new JButton("Add Image");
	    btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String FileName = textField.getText();
	    		try {
	    			originImage = ImageIO.read(new File(FileName));
	    			AddImage(originImage);
	    		}catch(IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
	    		
	    	}
	    });
	    btnNewButton_1.setBounds(212, 725, 144, 23);
	    frame.getContentPane().add(btnNewButton_1);
	    
	    textField = new JTextField();
	    textField.setBounds(360, 696, 129, 23);
	    frame.getContentPane().add(textField);
	    textField.setColumns(10);
	   
	    JLabel lblTypeFileName = new JLabel("Type File Name");
	    lblTypeFileName.setBounds(264, 700, 89, 14);
	    frame.getContentPane().add(lblTypeFileName);
	    //Add filter button
	    btnNewButton_2 = new JButton("GreyFilter");
		btnNewButton_2.setBounds(441, 725, 144, 23);
	    frame.getContentPane().add(btnNewButton_2);
	    //Add clear button
	    btnClear = new JButton("Clear");
	    btnClear.setBounds(496, 696, 89, 23);
	    btnClear.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		clear();
	    	}
	    });
	    frame.getContentPane().add(btnClear);
	    btnNewButton_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Filter grey = new GreyFilter();
	    		BufferedImage greyImage = grey.processImage(originImage);
	    		AddImage(greyImage);
	    	}
	    });
		
	}
	
	//Add Image to Panel and add to Frame
	public void AddImage(BufferedImage image) {

		ImagePanel.removeAll();
		frame.getContentPane().add(ImagePanel);
	    JLabel label = new JLabel(new ImageIcon(image));
	    label.setBounds(72, 10, 625, 674);
	    ImagePanel.add(label);
		frame.getContentPane().add(ImagePanel);
	}
	
	//Clear Panel
	public void clear() {
		ImagePanel.removeAll();
		frame.getContentPane().add(ImagePanel);
	}
}
