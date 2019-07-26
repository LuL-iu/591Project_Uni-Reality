import java.util.ArrayList;
/**
 * this class takes input of font name, color value, text context, it could set text font, color and rotate text , scale text
 * @author liulu
 *
 */
public class Text {
    //private instance, include all the font type in the array list 
	private ArrayList<String> fontLibray;
	//private instance, text content from user
	private String content;
	//take text input from windowBuilder 
	private int rgbValue;
	private String font;
	
	public Text(String textContent, int rgbValue, String font ) {
		this.content = textContent;
		this.font = font;
		this.rgbValue = rgbValue;
		
	}
	
	/**
	 * This method take angle input and rotate text with this angle	  
	 * 
	 *
	 **/
	public void rotateText(double angle) {
		
		
		
	}
	
	
	/**
	 * This method format Text into Font required  
	 * 
	 * 
	 *
	 **/
	public void setFont(String FontName) {
		font = FontName;
		
		
	}

	/**
	 * This method format Text into different color based off RGB  
	 * 
	 * 
	 *
	 **/
	public void setColor(int rgbValue) {
		rgbValue = rgbValue;
	}
	
	/**
	 * This method scale text with input ratio
	 * 
	 * 
	 *
	 **/
	public void scaleText(double ratio) {
	
	}
	


}
