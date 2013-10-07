package bui;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Display;

/**
 * Singleton class creates the set of images to be displayed on the game map
 * @author nelsoncs
 * 
 */
public class ImgSet {
	
	/** singleton flag/reference */
	static ImgSet _instance = null;

	/** set of id character to SWT.Image mappings for game map images */
	private HashMap<Character, Img> image_set;

	/**
	 * constructor setups up the lists of letter and file name associations for
	 * tile images.  Images must be in a directory named "bui" relative to the 
	 * user current directory
	 * @param display
	 */
	private ImgSet( Display display ){

		// TODO problem with getResource() 
		//String curr_dir = "/home/nelsoncs/eclipse/workspace/300_basic_ui/bui/";
		//String curr_dir = "/home/sgk/eclipse/300_basic_ui/bui/";

		if( _instance == null ) {

			String curr_dir = new String();
			curr_dir = System.getProperty("user.dir") + "/bui/";

			/** set up id character to filenames mapping */
			image_set = new HashMap<Character, Img>();

			try {
				image_set.put('B', new Img( display, curr_dir + StatGeography.BEACH.getImg() ) );
				image_set.put('D', new Img( display, curr_dir + StatGeography.DIRT.getImg() ) );
				image_set.put('T', new Img( display, curr_dir + StatGeography.FORREST.getImg() ) );
				image_set.put('G', new Img( display, curr_dir + StatGeography.GRASS.getImg() ) );
				image_set.put('W', new Img( display, curr_dir + StatGeography.WATER.getImg() ) );
				image_set.put('I', new Img( display, curr_dir + StatZone.INDUSTRIAL.getImg() ) );
				image_set.put('C', new Img( display, curr_dir + StatZone.COMMERCIAL.getImg() ) );
				image_set.put('R', new Img( display, curr_dir + StatZone.RESIDENTIAL.getImg() ) );
				image_set.put('U', new Img( display, curr_dir + StatZone.UNZONED.getImg() ) );
				image_set.put('Z', new Img( display, curr_dir + StatZone.SELECT.getImg() ) );
				image_set.put('O', new Img( display, curr_dir + StatStructure.COAL_POWER.getImg() ) );
				image_set.put('N', new Img( display, curr_dir + StatStructure.NATURAL_GAS.getImg() ) );
				image_set.put('K', new Img( display, curr_dir + StatStructure.PARK.getImg( ) ) );
				image_set.put('P', new Img( display, curr_dir + StatStructure.POLICE_STATION.getImg() ) );
				image_set.put('A', new Img( display, curr_dir + StatStructure.ROAD.getImg() ) );
				image_set.put('S', new Img( display, curr_dir + StatStructure.SEWAGE.getImg() ) );
				image_set.put('L', new Img( display, curr_dir + StatStructure.SOLAR_POWER.getImg() ) );
				image_set.put('E', new Img( display, curr_dir + StatStructure.WIND_POWER.getImg() ) );

			} catch (Exception e) {

				/** destroy reference to hashmap if i/o has failed */
				_instance = null;

				System.out.println( "Could not find : " + curr_dir );
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * public constructor for ImgSet singleton
	 * @param display
	 * @return ImgSet
	 */
	public static ImgSet Instance( Display display ){
		
		if( _instance == null ){
			ImgSet._instance = new ImgSet( display );
		}
		
		/** otherwise return singleton reference to existing set of images*/
		return _instance;
	}


	/**
	 * @return the image_set
	 */
	public HashMap<Character, Img> getImage_set() {
		return image_set;
	}

	/**
	 * @param image_set the image_set to set
	 */
	public void setImage_set(HashMap<Character, Img> image_set) {
		this.image_set = image_set;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
