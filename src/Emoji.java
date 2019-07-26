import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class build a emoji libray and it has the function of addEmoji, scale emoji and rotateEmoji
 * @author liulu
 *
 */

public class Emoji {
	//private instance store the emoji name and emoji image
	private HashMap<String, Image> emojiLibrary;

	/**
	 * This method adds Emoji 
	 * 
	 * 
	 *
	 **/
	public Image getEmoji(String name) {
		Image EmojiImage = emojiLibrary.get(name);
	  return EmojiImage;
	}
	
	/**
	 * This method adjust size of current Emoji
	 * @param x_axis is width
	 * @param x_axis is height
	 * 
	 *
	 **/
	public Image scaleEmoji(int x_axis, int y_axis) {
		Image scaledImage = null;
		return scaledImage;
	
	}
	
	
	/**
	 * This method rotate current Emoji
	 * @param angle is degree to turn
	 * 
	 * 
	 *
	 **/
	public Image rotateEmoji(int angle) {
		Image rotateImage = null;
		return rotateImage;
		
	}
	/**
	 * build emojiLirary
	 */
	public void buildLibray() {
		
	}

}
