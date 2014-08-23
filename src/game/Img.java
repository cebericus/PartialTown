package game;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.SWTException;

/**
 * rescales image to a 128 pixel wide base 
 * TODO crashes if supplied with an image that is less than 128 pixels wide
 * 
 * @see ImgSet
 * @author nelsoncs
 */
public class Img {

	private Display display;

	private ImageData img_data;
	private Image image;

	private Rectangle rec;

	public static final int x_adj = 64;  	// depends on image set 
	public static final int y_adj = 32; 	// 

	String tmp;

	/**
	 * constructor assumes the image file is in the same directory as Img.java
	 * @param display
	 * @param file_name
	 */
	public Img( Display display, String file_name ) {

		this.display = display;

		try {
			this.img_data = new ImageData( file_name );
			
		} catch (SWTException e) {
			e.printStackTrace();
			System.out.println( "Error trying to load file " + file_name );
			
			// needed for JUnit test to function correctly
			throw e;  									
		}
				
		// imageData is rescaled before making Image
		// TODO will not accept less than 128 px base width, program crashes
		int img_width = x_adj * 2;
		
		/** TODO need to always round up on odd sizes */
		int img_height = this.img_data.width / img_width;
		
		/** convert imageData to Image */
		this.image = new Image( this.display, 			
									this.img_data.scaledTo( img_width, 
									this.img_data.height / img_height )  
								); 
		
		this.setRec( this.image.getBounds() );			// update rec data
	}

	private void setRecHeight(int height) {
		this.rec.height = height;
		
	}

	private void setRecWidth(int width) {
		this.rec.width = width;
		
	}
	
	/**
	 * @return as an Image
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * 
	 * @param rec
	 */
	public void setRec( Rectangle rec ) {
		this.rec = rec;
	}

	/**
	 * @return the rec width
	 */
	public int width() {
		return this.rec.width;
	}

	/**
	 * @return the rec width
	 */
	public int height() {
		return this.rec.height;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
