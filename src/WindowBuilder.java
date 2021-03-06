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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

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
    JButton plus, minus;
	JButton btnAdd;
	JButton btnGrey;
	JButton btnMerge;
	private String filePath;
	private BufferedImage originImage;
	private BufferedImage processImage;
	JButton btnClear;
	private int w;
	private int h;
	private String fileName;
	private JList<String> list;
	private boolean add = false;
	JButton openButton, saveButton;
	private JFileChooser fc;
    private Integer hue = 5;
    private String selectEmoji;

    
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
        
	    //if user click, it will add the "+" and "-" button, and adjust the brightness level based on "+" or "-"
	    JButton brightnessBtn = new JButton("Brightness");
	    brightnessBtn.setBounds(12, 498, 101, 23);
        frame.getContentPane().add(brightnessBtn);
        brightnessBtn.addActionListener(brightnessListener);
        
        //if user click, it will call the hue filter and adjust the hue level 
        JButton HueBtn = new JButton("Adjust Hue");
        HueBtn.setBounds(123, 498, 101, 23);
        frame.getContentPane().add(HueBtn);
        HueBtn.addActionListener(hueListener); 
        
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
			selectEmoji = list.getSelectedValue();
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
		//wait for user to choose, get the return file from chooser 
		while (i == 0){
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File f = fc.getSelectedFile();
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
			
	//it apply the grey filter to process image and display on screen
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
	
	//it will ask user to choose another image and apply two images with merge filter to process image and display on screen
	public class MergeBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!add) {
				System.out.println("Please select a image first.");
				return;
			}
			File f = createFileChooser(btnMerge);
			BufferedImage toolImage;
			try {
				if (f == null) {
					return;
				}
				toolImage = ImageIO.read(f);
				MergeFilter Merge = new MergeFilter();
				BufferedImage MergeImage = Merge.MergeImage(processImage, toolImage);
				processImage = MergeImage;
				AddImage(MergeImage);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	////it call the file choose function and add the choosen image to screen
	public class chooseBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
    		File f =  createFileChooser(btnAdd);
    		if(f == null) {
    			return;
    		}
    		filePath = f.getPath();
    		fileName = f.getName();
			try {
				originImage = (BufferedImage)ImageIO.read(f);
				//scale original image to fit to screen
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
	
	//it will return the image to the original status
	public class clearBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(filePath == null) {
				return;
			}
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
	
	/**
	 * it save the process image to project folder
	 * 
	 *
	 */
	public class saveBthListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!add) {
				System.out.println("Please select a image first.");
				return;
			}
			String outputName = fileName.split("\\.")[0];
			File output = new File("Modified" + fileName);
			try {
				ImageIO.write(processImage, "jpg", output);
				System.out.println("image save sucessfull!");
		    } catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * user click, it will add 5 brightness level  to process image and display process image
	 */
	 private ActionListener addBrightness = (event) -> {
        BrightnessFilter BrightnessFilter = new BrightnessFilter();
        if (originImage == null) {
            return;
        }
        processImage = BrightnessFilter.processImageWithValue(processImage,5);
        AddImage(processImage);
	};
	    
	/**
	 * user click, it will minus 5 brightness level  to process image and display process image
	 */
    private ActionListener minusBrightness = (event) -> {
    	BrightnessFilter BrightnessFilter = new BrightnessFilter();
        if (originImage == null) {
            return;
        }
        processImage = BrightnessFilter.processImageWithValue(processImage, -5);
        AddImage(processImage);
    };
   
    /**
     * if user click, it will add 5 hue level to process image and display the process image
     */
    private ActionListener hueListener = (event) -> {
        HueFilter contrastFilter = new HueFilter();
        if (originImage == null) {
            return;
        }
        processImage = contrastFilter.processImageWithValue(processImage, hue);
        AddImage(processImage);
        hue +=5;
    };
    
    /**
     *if user click, it will display "+" and "-" button 
     */
    private ActionListener brightnessListener = (event) -> {
        plus = new JButton("+");
        plus.addActionListener(addBrightness);
        plus.setBounds(5, 10, 50, 50);
        frame.getContentPane().add(plus);

        minus = new JButton("-");
        minus.addActionListener(minusBrightness);
        minus.setBounds(5, 70, 50, 50);
        frame.getContentPane().add(minus);
        frame.repaint();
    };
}
