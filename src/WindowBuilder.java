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
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;

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
    private JButton plus, minus;
	private JButton btnAdd;
	private JTextField textField;
	private JButton btnGrey;
	private BufferedImage originImage;
	private BufferedImage processImage;
	static private BufferedImage clearImage;
	private JButton btnClear;
	private int w;
	private int h;
	private String fileName;
	private JList<String> list;
	private boolean add = false;
	JButton openButton, saveButton;
	JTextArea log;
	JFileChooser fc;
	String filePath;
	
    private Integer initial = 1;
    private Integer saturation = 5;
    private Integer degree = 45;
    private ActionListener addContrast = (event) -> {
        ContrastFilter contrastFilter = new ContrastFilter();
        if (originImage == null) {
            return;
        }
        BufferedImage vorImage = contrastFilter.processImageWithValue(originImage, initial);
        AddImage(vorImage);
        initial += 5;
    };

    private ActionListener minusContrast = (event) -> {
        ContrastFilter contrastFilter = new ContrastFilter();
        if (originImage == null) {
            return;
        }
        BufferedImage vorImage = contrastFilter.processImageWithValue(originImage, initial);
        AddImage(vorImage);
        initial -= 5;
    };

    private ActionListener saturationListener = (event) -> {
        SaturationFilter contrastFilter = new SaturationFilter();
        if (originImage == null) {
            return;
        }
        BufferedImage vorImage = contrastFilter.processImageWithValue(originImage, saturation);
        AddImage(vorImage);
        saturation +=5;
    };

    private ActionListener contrastListener = (event) -> {
        plus = new JButton("+");
        // if user click, it reads the file name information from JTextField
        plus.addActionListener(minusContrast);
        plus.setBounds(850, 120, 50, 50);
        frame.getContentPane().add(plus);

        minus = new JButton("-");
        // if user click, it reads the file name information from JTextField
        minus.addActionListener(addContrast);
        minus.setBounds(850, 300, 50, 50);
        frame.getContentPane().add(minus);
        frame.repaint();
    };

//    private ActionListener rotateListener = (event) -> {
//        ((RotatePanel)ImagePanel).setDegree(degree);
//        ImagePanel.repaint();
//        degree = degree + 45;
//    };


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
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    //create imagePanel for display image
	    ImagePanel = new Panel();
	    ImagePanel.setBounds(124, 10, 581, 596);
	    frame.getContentPane().add(ImagePanel);
	    ImagePanel.setLayout(new BoxLayout(ImagePanel, BoxLayout.X_AXIS));
	    //Add image button
	    btnAdd = new JButton("Add Image");
	    btnAdd.setBackground(new Color(240, 248, 255));
	    // if user click, it reads the file name information from JTextField
	    btnAdd.addActionListener(new chooseBtnListener());
	    btnAdd.setBounds(10, 628, 144, 23);
	    
        frame.getContentPane().add(btnAdd);
	    //Add clear button
	    btnClear = new JButton("Clear");
	    btnClear.setBounds(320, 628, 136, 23);
	    //if user click clear button, imagePanel cleared
	    btnClear.addActionListener(new clearBtn());
	    frame.getContentPane().add(btnClear);
	    //Add grey filter button, if user click, image become grey and display on canvas
	    btnGrey = new JButton("GreyFilter");
		btnGrey.setBounds(320, 662, 136, 23);
	    frame.getContentPane().add(btnGrey);  
	    btnGrey.addActionListener(new GreyBtnListener());
	    
	    JButton btnMerge = new JButton("MergeFilter");
	    btnMerge.setBounds(164, 662, 146, 23);
	    frame.getContentPane().add(btnMerge);

	    btnMerge.addActionListener(new MergeBtnListener());
	    
	    
	    JButton btnVoronoi = new JButton("VoronoiFilter");
	    btnVoronoi.setBounds(10, 662, 144, 23);
	    frame.getContentPane().add(btnVoronoi);
	    btnVoronoi.addActionListener(new VoronoiBtnLisener());
	    
	    JButton btnSaveImage = new JButton("Save Image");
	    btnSaveImage.setBounds(164, 628, 146, 23);
	    frame.getContentPane().add(btnSaveImage);
	    
	    JButton btnAddEmoji = new JButton("Add Emoji");
	    btnAddEmoji.setBounds(646, 628, 128, 23);
	    frame.getContentPane().add(btnAddEmoji);
	    btnAddEmoji.addActionListener(new emojiListener());

	    String[] emojiName = new String[5];
	    emojiName[0] = "angry";
	    emojiName[1] = "comfort";
	    emojiName[2] = "heart";
	    list = new JList(emojiName);
	    list.setBounds(481, 631, 144, 88);
	    frame.getContentPane().add(list);

	    JButton contrastBtn = new JButton("Contrast");
        contrastBtn.setBounds(10, 696, 144, 23);
        frame.getContentPane().add(contrastBtn);
        contrastBtn.addActionListener(contrastListener);

        JButton saturation = new JButton("Adjust Hue");
        saturation.setBounds(164, 696, 146, 23);
        frame.getContentPane().add(saturation);
        
        JLabel lblSelectEmojiFrom = new JLabel("Select Emoji from the list, then click add emoji");
        lblSelectEmojiFrom.setHorizontalAlignment(SwingConstants.LEFT);
        lblSelectEmojiFrom.setBounds(425, 727, 268, 23);
        frame.getContentPane().add(lblSelectEmojiFrom);
        saturation.addActionListener(saturationListener);    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	

	//Add Image to Panel and add to Frame
	public void AddImage(BufferedImage image) {
		//clear ImagePanel before adding
		ImagePanel.removeAll();
		frame.getContentPane().add(ImagePanel);
	    w = ImagePanel.getWidth();
		h = ImagePanel.getHeight();
		Scale s = new Scale();
		image = s.FitImagetoFrame(image, w, h);
		//Create label with image
	    JLabel label = new JLabel(new ImageIcon(image));
	    //set label size
	    label.setBounds(ImagePanel.getBounds());
	    //add label to image panel
	
	    ImagePanel.add(label, BorderLayout.CENTER);;
		frame.getContentPane().add(ImagePanel);
	}
	

	
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
				Scale s = new Scale();
				BufferedImage img = s.FitImagetoFrame(processImage, w, h);
				JLabel background = new JLabel(new ImageIcon(img));
				imageLbl.setBounds(ImagePanel.getBounds());
				background.setBounds(ImagePanel.getBounds());
				background.setLayout(new GridBagLayout());
			    background.add(imageLbl);
				ImagePanel.add(background, BorderLayout.CENTER);
			}
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
				clearImage = originImage;
				AddImage(originImage);  
				add = true;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 	
    	}
	}
	
	public class clearBtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			processImage = clearImage;
			AddImage(clearImage);
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
}
