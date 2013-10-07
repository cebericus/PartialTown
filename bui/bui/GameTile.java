package bui;

import org.eclipse.swt.graphics.ImageData;

/**
 * Game tile 
 * @author nelsoncs
 * @see ImgSet
 * @see StatGeography
 * @see StatZone
 * @see StatStructure
 */
public class GameTile {
	
	private int x;
	private int y;
	
	private int x_pix;
	private int y_pix;
	
	private ImageData img_data;

	private char cGeo;
	private StatGeography geo;
	
	private char cZone;
	private StatZone statZone;
	
	private char cStructure;
	private StatStructure statStructure;
	
	private Stats stats;
	 
	
	/**
	 * constructor
	 * sets default geography to grass 'G', statZone to unzoned 'U' and statStructure to
	 * null character
	 */
	public GameTile () {

		this.x = 0;
		this.y = 0;

		cGeo = 'G'; 		// default to grass

		cZone = 'U'; 		// default to unzoned

		cStructure = '\0'; 	// default to null
	}

	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}


	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}


	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}


	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}


	/**
	 * @return the x_pix
	 */
	public int getX_pix() {
		return x_pix;
	}


	/**
	 * @param x_pix the x_pix to set
	 */
	public void setX_pix(int x_pix) {
		this.x_pix = x_pix;
	}


	/**
	 * @return the y_pix
	 */
	public int getY_pix() {
		return y_pix;
	}


	/**
	 * @param y_pix the y_pix to set
	 */
	public void setY_pix(int y_pix) {
		this.y_pix = y_pix;
	}


	/**
	 * @return the img_data
	 */
	public ImageData getCurrent_img_data() {
		return img_data;
	}


	/**
	 * @param img_data the img_data to set
	 */
	public void setCurrent_img_data(ImageData current_img_data) {
		this.img_data = current_img_data;
	}


	/**
	 * @return the cGeo
	 */
	 public char getcGeo() {
		 return cGeo;
	 }

	 /**
	  * @param cGeo the cGeo to set
	  */
	 public void setcGeo(char cGeo) {
		 this.cGeo = cGeo;
	 }

	 private void setGeo(StatGeography value) {
		 this.geo = value;
	 }

	 public StatGeography getGeo() {
		 return this.geo;
	 }
	 
//	 public boolean placeGeography(StatGeography geo) {
//		 // TODO implement this operation
//		 throw new UnsupportedOperationException("not implemented");
//	 }

	 /**
	 * @return the cZone
	 */
	public char getcZone() {
		return cZone;
	}

	/**
	 * @param cZone the cZone to set
	 */
	public void setcZone(char cZone) {
		this.cZone = cZone;
	}


	private void setZone(StatZone value) {
		 this.statZone = value;
	 }

	 private StatZone getZone() {
		 return this.statZone;
	 }
	 
//	 public boolean placeZone(StatZone statZone) {
//		 // TODO implement this operation
//		 throw new UnsupportedOperationException("not implemented");
//	 }

	 
	 /**
	 * @return the cStructure
	 */
	public char getcStructure() {
		return cStructure;
	}


	/**
	 * @param cStructure the cStructure to set
	 */
	public void setcStructure(char cStructure) {
		this.cStructure = cStructure;
	}


	public StatStructure getStructure() {
		 return this.statStructure;
	 }

	 public void setStructure(StatStructure value) {
		 this.statStructure = value;
	 }
	 
//	 public boolean placeStructure(StatStructure statStructure) {
//		 // TODO implement this operation
//		 throw new UnsupportedOperationException("not implemented");
//	 }

	 
	 public void setStats(Stats value) {
		 this.stats = value;
	 }

	 public Stats getStats() {
		 return this.stats;
	 }
	 

	 public Stats calc_impact() {
		 // TODO implement this operation
		 throw new UnsupportedOperationException("not implemented");
	 }

	 public int calc_happiness() {
		 // TODO implement this operation
		 throw new UnsupportedOperationException("not implemented");
	 }

}
