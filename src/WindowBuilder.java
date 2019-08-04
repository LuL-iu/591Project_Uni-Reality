import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**
 * this is a window interaction class, it read user input, add image, display image, clear image, apply filter and add emoji, rotate/scale emoji, scale/rotate emoji
 * @author liulu
 *
 */
public class WindowBuilder {
    // private instance JFrame for display the canvas and store window information
	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	private Panel ImagePanel;
	private JButton btnAdd;
	private JButton btnGrey;
	private BufferedImage originImage;
	private BufferedImage processImage;
	private JButton btnClear;
	private int w;
	private int h;
	private int i;
	private String fileName;
	JButton openButton, saveButton;
	JTextArea log;
	JFileChooser fc;
	String filePath;

	/**
	 * Launch the application.This is for class test
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
		i = 0;
		//create frame
		frame = new JFrame();
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    //create imagePanel for display image
	    ImagePanel = new Panel();
	    ImagePanel.setBounds(88, 41, 623, 620);
	    frame.getContentPane().add(ImagePanel);
	    //Add image button
	    btnAdd = new JButton("Add Image");
	    // if user click, it reads the file name information from JTextField
	    btnAdd.addActionListener(new chooseBtnListener());
	    btnAdd.setBounds(86, 690, 144, 23);
	    
        frame.getContentPane().add(btnAdd);
	    //Add clear button
	    btnClear = new JButton("Clear");
	    btnClear.setBounds(266, 690, 89, 23);
	    //if user click clear button, imagePanel cleared
	    btnClear.addActionListener(new clearBtn());
	    frame.getContentPane().add(btnClear);
	    //Add grey filter button, if user click, image become grey and display on canvas
	    btnGrey = new JButton("GreyFilter");
		btnGrey.setBounds(567, 725, 144, 23);
	    frame.getContentPane().add(btnGrey);  
	    btnGrey.addActionListener(new GreyBtnListener());
	    
	    JButton btnMerge = new JButton("MergeFilter");
	    btnMerge.setBounds(567, 690, 141, 23);
	    frame.getContentPane().add(btnMerge);

	    btnMerge.addActionListener(new MergeBtnListener());
	    
	    
	    JButton btnVoronoi = new JButton("VoronoiFilter");
	    btnVoronoi.setBounds(404, 725, 144, 23);
	    frame.getContentPane().add(btnVoronoi);
	    btnVoronoi.addActionListener(new VoronoiBtnLisener());
	    
	    JButton btnSaveImage = new JButton("Save Image");
	    btnSaveImage.setBounds(85, 725, 144, 23);
	    frame.getContentPane().add(btnSaveImage);
	    
	    JButton btnAddEmoji = new JButton("Add Emoji");
	    btnAddEmoji.setBounds(266, 725, 89, 23);
	    frame.getContentPane().add(btnAddEmoji);
	    btnAddEmoji.addActionListener(new emojiListener());
	    
//	    URL url = this.getClass().getResource("giphy.gif");
//	    Icon myImgIcon = new ImageIcon(url);
//	    JLabel gifLbl = new JLabel(myImgIcon);
//	    gifLbl.setBounds(10, 11, 226, 623);
//	    frame.getContentPane().add(gifLbl);
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    JProgressBar progressBar = new JProgressBar();
//	    progressBar.setBounds(248, 725, 146, 14);
//	    progressBar.setValue(0);
//	    progressBar.setStringPainted(true);
//	    frame.getContentPane().add(progressBar);
//	    fill(progressBar);
	 

	}
	
//	public void fill(JProgressBar b) {
//		int i = 0;
//		try {
//			while(i < 100) {
//				b.setValue(i+10);
//				Thread.sleep(1000);
//				i += 20;
//			}
//		}
//		catch (Exception e) {
//			
//		}
//	}
//	
	//Add Image to Panel and add to Frame
	public void AddImage(BufferedImage image) {
		//clear ImagePanel before adding
		ImagePanel.removeAll();
		frame.getContentPane().add(ImagePanel);
		int w = ImagePanel.getWidth();
		int h = ImagePanel.getHeight();
		Scale s = new Scale();
		image = s.FitImagetoFrame(image, w, h);
		//Create label with image
	    JLabel label = new JLabel(new ImageIcon(image));
	    //set label size
	    label.setBounds(85, 14, 625, 674);
	    //add label to image panel
	
	    ImagePanel.add(label);
		frame.getContentPane().add(ImagePanel);
	}
	

	
	public class emojiListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ImagePanel.removeAll();
			URL url = this.getClass().getResource("A.gif");
			System.out.print(url);
			Icon myImgIcon = new ImageIcon(url);
			JLabel imageLbl = new JLabel(myImgIcon);
			File f = new File("Flowers.jpg");
			JLabel background = new JLabel(new ImageIcon(processImage));
			background.setLayout(new GridBagLayout());
		    background.add(imageLbl);
			imageLbl.setBounds(ImagePanel.getBounds());
			background.setBounds(ImagePanel.getBounds());
			ImagePanel.add(background);
			frame.getContentPane().add(ImagePanel);

		}
	}
	
	public File createFileChooser(JButton a){
		int i = 0;
		JFileChooser fc = new JFileChooser();
	    fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(a);
		while (i == 0){
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File f = fc.getSelectedFile();
	            filePath = f.getPath();
	            i = 1;
	            return f;
			}
		}
		return null;
		
	}
	
	public class VoronoiBtnLisener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
    		Filter voronoi = new VoronoiFilter();
    		BufferedImage vorImage = voronoi.processImage(processImage);
    		processImage = vorImage;
    		AddImage(vorImage);
		}
	}
	
	public class GreyBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
    		Filter grey = new GreyFilter();
    		BufferedImage greyImage = grey.processImage(processImage);
    		processImage = greyImage;
    		AddImage(greyImage);
    	}
	}
	
	public class MergeBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Filter Merge = new MergeFilter();
			BufferedImage MergeImage = Merge.processImage(processImage);
			processImage = MergeImage;
			AddImage(MergeImage);
			
		}
	}
	
	public class chooseBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
    		File f =  createFileChooser(btnAdd);
    		fileName = f.getName();
			try {
				originImage = (BufferedImage)ImageIO.read(f);
				processImage = originImage;
				AddImage(originImage);  
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 	
    	}
	}
	
	public class clearBtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			processImage = originImage;
			AddImage(originImage);
    	}
	}
	
	public class saveBth implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			File output = new File(fileName+"modified");
			try {
				ImageIO.write(processImage, "jpg", output);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class gifPanel extends JPanel {

		  Image image;

		  public gifPanel() {
		    image = Toolkit.getDefaultToolkit().createImage("giphy.gif");
		  }

		  @Override
		  public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    if (image != null) {
		      g.drawImage(image, 0, 0, this);
		    }
		  }

		}
}
