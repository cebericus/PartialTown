package bui;
/*
 * @author nelsoncs
 */

import java.io.BufferedReader;
import java.io.IOException;

import org.eclipse.swt.widgets.Text;

/**
 * primary map of game is an array of Tiles.
 * <p><b>Notes:</b> GameMap is currently instantiated in the topmost MapShell class and the
 * MapCanvas objects acquire shallow references to the GameMap object.  Both 
 * instances of MapCanvas make changes to the GameMap object in response to 
 * mouse clicks and keyboard input.  The MapShell class modifies GameMap through
 * the x and y plat map coordinate Text boxes.</p>
 * x_game and y_game control the overall size of the game map
 * 
 * @see GameTile
 */
public class GameMap {

	/** array of Tiles that comprise the game board */
	protected GameTile [][] gameTiles;
	
	public static final int x_game = 128;
	public static final int y_game = 128;
	
	/** reference to the active tile that is coordinated with red dot in minimap, and is the tile
	 * that new zone and structure placements occur
	 */
	protected GameTile active_tile;
	
	/**
	 * constructor
	 * @param x
	 * @param y
	 */
	public GameMap( Text x, Text y ) {
		
		int x_center = (x_game-1)/2;
		int y_center = (y_game-1)/2;
		
		gameTiles = new GameTile[x_game][y_game];

		 // instantiate 2D array of GameTile objects to fill game map 128 X 128
		for (int i = 0; i < gameTiles.length; i++) {
			for (int j = 0; j < gameTiles[i].length; j++) {
				gameTiles[i][j] = new GameTile();
				
				gameTiles[i][j].setX( i );				// each GameTile knows its place
				gameTiles[i][j].setY( j );				// this is used to update maps
			}
		}		
		
		/** try starting in center of game map */
		this.active_tile = gameTiles[ x_center ][ y_center ];
		
		/** also set the readouts in UI. <b>Note:</b> this is not good MVC */
		x.setText( Integer.toString( x_center ) );
		y.setText( Integer.toString( y_center ) );
	}


	/**
	 * @return GameTile active_tile
	 */
	public GameTile getActive_tile() {
		return active_tile;				
	}


	/**
	 * used to indicate the GameTile in active play for placement of Zones and 
	 * Structures
	 * @param x
	 * @param y
	 */
	public void setActive_tile( int x, int y ) {
		this.active_tile = gameTiles[x][y];				// update local attribute

	}


	/**
	 * read a map file from the file handle into map array cGeo
	 * @param in handle to buffered file 
	 */
	public void loadMap( BufferedReader in ) throws IOException {

		char eol = System.getProperty("line.separator").charAt(0);  // look for newline
		char c ;
		 
		/*
		 * ---> to do Note this needs a proofing function to eliminate badly formed terrain files
		 */
		for (int i = 0; i < gameTiles.length; ++i) {		// load the text file containing single char geography types
			for (int j = 0; j < gameTiles[i].length; ++j) {
				
				while( (c = (char) in.read()) == eol ); // crude skip of eol
				
				gameTiles[i][j].setcGeo( c );
			}
		}		
	}


	public boolean saveMap() {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	
//	/*
//	 * @return the map
//	 */
//	public GameTile [][] getMap() {
//		return gameTiles;
//	}
//
//	/*
//	 * @param map the map to set
//	 */
//	public void setMap(GameTile [][] map) {
//		this.tiles = map;
//	}
//
//
//	private int x_current_size;
//
//	private void setX_current_size(int value) {
//		this.x_current_size = value;
//	}
//
//	private int getX_current_size() {
//		return this.x_current_size;
//	}
//
//	
//	private int y_current_size;
//
//	private void setY_current_size(int value) {
//		this.y_current_size = value;
//	}
//
//	private int getY_current_size() {
//		return this.y_current_size;
//	}


//	private DisplayMap displayed_viewport;
//
//	private void setDisplayed_viewport(DisplayMap value) {
//		this.displayed_viewport = value;
//	}
//
//	private DisplayMap getDisplayed_viewport() {
//		return this.displayed_viewport;
//	}

}
