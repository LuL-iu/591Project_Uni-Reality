import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.GridBagLayout;

import java.awt.Panel;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;

/**
 * this is a window interaction class, it read user input, add image, display image, clear image, apply filter and add emoji
 * 
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
    private JButton plus, minus;
	private JButton btnAdd;
	private JButton btnGrey;
	private String filePath;
	private BufferedImage originImage;
	private BufferedImage processImage;
	private JButton btnClear;
	private int w;
	private int h;
	private String fileName;
	private JList<String> list;
	private boolean add = false;
	JButton openButton, saveButton;
	private JFileChooser fc;
    private Integer initial = 1;
    private Integer saturation = 5;

    
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
		//create frame
		frame = new JFrame();
		frame.setBounds(0, 0, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    //create imagePanel for display image
	    ImagePanel = new Panel();
	    ImagePanel.setBounds(55, 42, 519, 370);
	    w = ImagePanel.getWidth();
	    h = ImagePanel.getHeight();
	    frame.getContentPane().add(ImagePanel);
	    ImagePanel.setLayout(new BoxLayout(ImagePanel, BoxLayout.X_AXIS));
	    //Add image button
	    btnAdd = new JButton("Add Image");
	    btnAdd.setBackground(new Color(240, 248, 255));
	    // if user click, it reads the file name information from JTextField
	    btnAdd.addActionListener(new chooseBtnListener());
	    btnAdd.setBounds(12, 430, 101, 23);
	    
        frame.getContentPane().add(btnAdd);
	    //Add clear button
	    btnClear = new JButton("Clear");
	    btnClear.setBounds(234, 430, 101, 23);
	    //if user click clear button, imagePanel cleared
	    btnClear.addActionListener(new clearBtnListener());
	    frame.getContentPane().add(btnClear);
	    
	    //Add grey filter button, if user click, image become grey and display on canvas
	    btnGrey = new JButton("GreyFilter");
		btnGrey.setBounds(234, 464, 101, 23);
	    frame.getContentPane().add(btnGrey);  
	    btnGrey.addActionListener(new GreyBtnListener());
	    
	    //Add Merge Filter, if user click, image merge the default image and dispaly on canvas
	    JButton btnMerge = new JButton("MergeFilter");
	    btnMerge.setBounds(123, 464, 101, 23);
	    frame.getContentPane().add(btnMerge);
	    btnMerge.addActionListener(new MergeBtnListener());
	    
	    //Add Voronoi Filter, if user click, image apply voronoi filter generate voronoi grid and paint the diagram with original image
	    JButton btnVoronoi = new JButton("VorFilter");
	    btnVoronoi.setBounds(12, 464, 101, 23);
	    frame.getContentPane().add(btnVoronoi);
	    btnVoronoi.addActionListener(new VoronoiBtnLisener());
	    
	    //if user click the save btn, it saved the process image to the project folder
	    JButton btnSaveImage = new JButton("Save Image");
	    btnSaveImage.setBounds(123, 430, 101, 23);
	    frame.getContentPane().add(btnSaveImage);
	    btnSaveImage.addActionListener(new saveBthListener());
	    
	    //if user choose the emoji from the list and then click add emoji, it will add the emoji from the source library and display infront of original image
	    JButton btnAddEmoji = new JButton("Add Emoji");
	    btnAddEmoji.setBounds(473, 498, 101, 23);
	    frame.getContentPane().add(btnAddEmoji);
	    btnAddEmoji.addActionListener(new emojiListener());
        
	    //create an emoji list name and display on the Jlist 
	    String[] emojiName = new String[3];
	    emojiName[0] = "angry";
	    emojiName[1] = "heart";
	    emojiName[2] = "comfort";
	    list = new JList(emojiName);
	    list.setBounds(345, 433, 118, 88);
	    frame.getContentPane().add(list);
        
	    //if user click, it will add the "+" and "-" button, and adjust the contrast level based on "+" or "-"
	    JButton contrastBtn = new JButton("Contrast");
        contrastBtn.setBounds(12, 498, 101, 23);
        frame.getContentPane().add(contrastBtn);
        contrastBtn.addActionListener(contrastListener);
        
        //if user click, it will call the hue filter and adjust the hue level 
        JButton HueBtn = new JButton("Adjust Hue");
        HueBtn.setBounds(123, 498, 101, 23);
        frame.getContentPane().add(HueBtn);
        HueBtn.addActionListener(saturationListener); 
        
        //instruction for add emoji
        JLabel lblSelectEmojiFrom = new JLabel("Select Emoji from the list, then click add emoji");
        lblSelectEmojiFrom.setHorizontalAlignment(SwingConstants.LEFT);
        lblSelectEmojiFrom.setBounds(249, 527, 325, 23);
        frame.getContentPane().add(lblSelectEmojiFrom);
     
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	

	//Add Image to Panel and add to Frame
	public void AddImage(BufferedImage image) {
		//clear ImagePanel before adding
		ImagePanel.removeAll();
		frame.getContentPane().add(ImagePanel);
		//Create label with image
	    JLabel label = new JLabel(new ImageIcon(image));
	    //set label size
	    label.setBounds(ImagePanel.getBounds());
	    //add label to image panel
	    ImagePanel.add(label, BorderLayout.CENTER);
		frame.getContentPane().add(ImagePanel);
	}
	

	//emojiListener if will add the emoji user selected from the list 
	public class emojiListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ImagePanel.removeAll();
			if(list.getSelectedValue() == null) {
				System.out.println("Please selected a emoji from the list");
				return;
			}
			String path = list.getSelectedValue() + ".gif";
			URL url = this.getClass().getResource(path);
			Icon myImgIcon = new ImageIcon(url);
			JLabel imageLbl = new JLabel(myImgIcon);
			if(!add) {
				System.out.println("Please add an image first");
				return;
			}
			else {
				JLabel background = new JLabel(new ImageIcon(processImage));
				imageLbl.setBounds(80, 100, 200,150);
				background.setBounds(ImagePanel.getBounds());
				background.setLayout(new GridBagLayout());
			    background.add(imageLbl);
				ImagePanel.add(background, BorderLayout.CENTER);
			}
			frame.getContentPane().add(ImagePanel);
		}
	}
	
	//it return the file that user select from file explore
	public File createFileChooser(JButton a){
		int i = 0;
		JFileChooser fc = new JFileChooser();
		//apply the image filter, only show image in the file explore
	    fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(a);
		//wait for user to choose, 
		while (i == 0){
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File f = fc.getSelectedFile();
	            filePath = f.getPath();
	            i = 1;
	            return f;
			}
			else {
				return null;
			}
		}
		return null;
		
	}
	
	public class VoronoiBtnLisener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!add) {
				System.out.println("Please select a image first.");
				return;
			}
			System.out.println("It might take a while. Please wait for image processing.");
    		Filter voronoi = new VoronoiFilter();
    		BufferedImage vorImage = voronoi.processImage(processImage);
    		processImage = vorImage;
    		AddImage(vorImage);
		}
	}
	
	public class GreyBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!add) {
				System.out.println("Please select a image first.");
				return;
			}
    		Filter grey = new GreyFilter();
    		BufferedImage greyImage = grey.processImage(processImage);
    		processImage = greyImage;
    		AddImage(greyImage);
    	}
	}
	
	public class MergeBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!add) {
				System.out.println("Please select a image first.");
				return;
			}
			
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
    		if(f == null) {
    			return;
    		}
    		fileName = f.getName();
			try {
				originImage = (BufferedImage)ImageIO.read(f);
				Scale s = new Scale();
				originImage = s.FitImagetoFrame(originImage, w, h);
				processImage = originImage;
				AddImage(originImage);  
				add = true;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 	
    	}
	}
	
	public class clearBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			File f = new File(filePath);
			try {
				originImage = ImageIO.read(f);
				Scale s = new Scale();
				originImage = s.FitImagetoFrame(originImage, w, h);
				processImage = originImage;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			AddImage(processImage);
    	}
	}
	
	public class saveBthListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!add) {
				System.out.println("Please select a image first.");
				return;
			}
			File output = new File("Modified" + fileName);
			try {
				ImageIO.write(processImage, "jpg", output);
				System.out.println("image save sucessfull!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	 private ActionListener addContrast = (event) -> {
	        ContrastFilter contrastFilter = new ContrastFilter();
	        if (originImage == null) {
	            return;
	        }
	        BufferedImage contrastImage = contrastFilter.processImageWithValue(processImage, initial);
	        AddImage(contrastImage);
	        initial += 5;
	    };

	    private ActionListener minusContrast = (event) -> {
	        ContrastFilter contrastFilter = new ContrastFilter();
	        if (originImage == null) {
	            return;
	        }
	        BufferedImage contrastImage = contrastFilter.processImageWithValue(processImage, initial);
	        AddImage(contrastImage);
	        initial -= 5;
	    };

	    private ActionListener saturationListener = (event) -> {
	        SaturationFilter contrastFilter = new SaturationFilter();
	        if (originImage == null) {
	            return;
	        }
	        BufferedImage Img = contrastFilter.processImageWithValue(processImage, saturation);
	        AddImage(Img);
	        saturation +=5;
	    };

	    private ActionListener contrastListener = (event) -> {
	        plus = new JButton("+");
	        // if user click, it reads the file name information from JTextField
	        plus.addActionListener(minusContrast);
	        plus.setBounds(5, 10, 50, 50);
	        frame.getContentPane().add(plus);

	        minus = new JButton("-");
	        // if user click, it reads the file name information from JTextField
	        minus.addActionListener(addContrast);
	        minus.setBounds(5, 70, 50, 50);
	        frame.getContentPane().add(minus);
	        frame.repaint();
	    };
}
