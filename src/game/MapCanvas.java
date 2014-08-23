package game;
/**
 * @author nelsoncs
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;

/**
 * Creates a drawable canvas by subclassing SWT.canvas and drawing the images provided
 * through the Img and ImgSet classes as a game map on the canvas.
 * @see GameMap
 */
public class MapCanvas extends Canvas implements MouseListener, MouseMoveListener {
	
//	/** TODO : replace with an Observer or other design pattern to eliminate
//	 * circular reference to child canvas.
//	 */
//	private Shell sh;
	
	/** the keyboard movement needs to be able to update the minimap
	 * so the minimap constructor sets this reference.
	 */
	MapMiniCanvas mini_child;
	
	/** used by constructor to retain the current display used by Shell */
	protected Display curr_display; 		
	
	/** used by constructor to retain the current ascii game map */
	protected GameMap map;	
	
	/** reference to Singleton ImgSet images that parallels the parent map */
	private ImgSet img_set;
	/** reference to ImgSet HashMap of Images*/
	private HashMap img_hash;
	
	/** offset origin for the canvas coordinate system */
	private int x_ori;						
	private int y_ori;

	/** to avoid duplicate mapCanvas instances from reloading .txt terrain file  
	 * when constructed,flag shared by all instances of MapCanvas */
	static private boolean load_flag = false;
	
	static public boolean PAINT = true;
	static public boolean FIND = false;
	
	
	
	/**
	 * constructor calls the parent super constructor, sets up private attributes
	 * and then calls the onLoad function to initialize the game map.<p>
	 * 
	 * Note: probably does not do a very good check of the proper terrain map being loaded
	 * Important: any thing calling this constructor must be a subclass and override drawViewport
	 * or the whole thing probably crashes
	 * 
	 * @param sh SWT.Shell 
	 * @param map
	 * @param imgs
	 */
	public MapCanvas( Shell sh, GameMap map, ImgSet imgs ) {
		
		super( sh, SWT.NONE|SWT.BORDER);	// initialize parent
		
		this.curr_display = sh.getDisplay();	
		
		this.map = map;						//data model for map
		
		/** set up x_ and y_ori to match the current active_Tile */
		/** set up for transform */
		Rectangle rec = new Rectangle( 0,0,0,0 );
		rec.x = map.active_tile.getX();
		rec.y = map.active_tile.getY();
		
		rec = isoTfn( rec, Img.x_adj, Img.y_adj, false );
		
		this.x_ori = rec.x;
		this.y_ori = rec.y;
		
		/** ImgSet is a Singleton, constructor is Instance() function*/
		img_set = ImgSet.Instance( curr_display );
		img_hash = img_set.getImage_set();
		
		// check for an extant map. TODO : convert to Singleton
		if( load_flag == false )
		{
			try {
				this.onLoad();		// load .txt map from disk and redraw display
				load_flag = true;	// indicate to any other instantiated maps that
									// we have data already
			} catch (Exception e) {
				System.out.println( "MapCanvas constructor: " + e.getMessage() );
			}
		}

		/** Canvas SWT Listeners */
		
		/** Paint listener */
		this.addListener (SWT.Paint, new Listener () {
			public void handleEvent (Event event) {
				MapCanvas.this.viewPortDraw( event );
			}
		});

		/** required for abstract interface spec. */
		this.addMouseListener( this );
		this.addMouseMoveListener( this );
		
		/** keyboard navigation */
		this.addKeyListener( new KeyAdapter() {
			public void keyPressed( KeyEvent ke )
			{				
				MapCanvas.this.keyPressed( ke );
			}
		} );

	}

	/**
	 * Isometric Transform: uses the width and height parameters of an 
	 * SWT.Rectangle and extracts
	 * a corresponding integer pair that should correlate to the coordinates
	 * of a given GameTile in the overall GameMap.<p>Depends on Img.x_ and y_adj
	 * which should be passed in as x_ and y_scale_factor</p>Historical notes:
	 * the original, empirically determined formulas were:
	 * 		<p>tmp_x = (x*Img.x_adj) - (y*Img.x_adj) + x_ori + x_offset;</p>
	 *		<p>tmp_y = (x*Img.y_adj) + (y*Img.y_adj) + y_ori + y_offset;</p>
	 * <p> These refactored as: 
	 * 		<p>(x-y) *x_adj * sign + (x_ori + x_offset),</p> 
	 * 		<p>(x+y) *y_adj * sign + (y_ori + y_offset)</p> where the 
	 * sign was +1 for laying out gameTiles and -1 for finding the coordinates of 
	 * a tile. isoTFN does not add the _ori or _offset factors, this must be 
	 * done separately if desired.  <p>There were easily made errors when replicating the formula
	 * in different places in the code.  So, the function setup is more complex
	 * but probably more reliable for long term use.</p></p>
	 * @param rec SWT.Retangle, only the width and height are used
	 * @param x_scale_factor should be Img.x_adj 
	 * @param y_scale_factor should be Img.y_adj
	 * @param fwd_rev should be true to paint gameTiles and false to find gameTiles 
	 * equivalent to multiplying by plus or minus one respectively 
	 * @return Rectangle only the width and height will be changed
	 * 
	 */
	protected Rectangle isoTfn( Rectangle rec, 
									int x_scale_factor, 
									int y_scale_factor, 
									boolean fwd_rev ){

		int tmp_x = rec.x;
		int tmp_y = rec.y;

		int sign = 1;

		if( fwd_rev == false )
			sign = -1;

		rec.x = ( tmp_x - tmp_y ) * x_scale_factor * sign;
		rec.y = ( tmp_x + tmp_y ) * y_scale_factor * sign;

		return rec;
	}

	
	/**
	 * Build the map on a Canvas:
	 * probably should be named DrawCanvas, does not really define a viewport.
	 * Function actually draws the entire map regardless of visibility in the 
	 * shell/canvas.<p>
	 * @param event
	 */
	public void viewPortDraw( Event event ){

		/** build the map on Canvas */
		int x = 0;
		int y = 0;

		int tmp_x = 0;
		int tmp_y = 0;

		int x_offset = 0;	// offset for images larger than 128 pixels wide
		int y_offset = 0;	// offset for images larger than 64 pixels tall

		Image tmp_img;
		
		/** (x, y) */
		Rectangle rec = new Rectangle( 0, 0, 0,0 );

		/** draw entire map, both on and off screen (yes, very inefficient) */
		for( y = 0; y < GameMap.x_game; ++y ){					// loading from array backwards will "cut tops off of trees"
			for( x = 0; x < GameMap.y_game; ++x ){
				
				/** evaluate the ascii map arrays for the top most tile Image */
				try {
					if( map.gameTiles[x][y].getcStructure() != '\0' )
					{
						Img tmp = (Img) this.img_hash.get( map.gameTiles[x][y].getcStructure() );
						tmp_img = tmp.getImage();
					}
					else
						if( map.gameTiles[x][y].getcZone() != 'U' ){
							Img tmp = (Img) this.img_hash.get( map.gameTiles[x][y].getcZone() );
							tmp_img = tmp.getImage();
						}
						else{
							Img tmp = (Img) this.img_hash.get( map.gameTiles[x][y].getcGeo() );
							tmp_img = tmp.getImage();
						}
					
					x_offset = Img.x_adj - tmp_img.getBounds().width;	// for tall images
					y_offset = Img.y_adj - tmp_img.getBounds().height; 	// want negative vertical offset
					
					/** set up for transform */
					rec.x = x;
					rec.y = y;
					
					rec = isoTfn( rec, Img.x_adj, Img.y_adj, PAINT );

					/** adjust for position on map and variable image height */
					/** TODO : "400" needs to be replaced by function that offsets
					 * to center of current active window, based on the dimensions
					 *  of that visible window area */
					tmp_x = rec.x + x_ori + x_offset + 400;
					tmp_y = rec.y + y_ori + y_offset + 400;	

					event.gc.drawImage( tmp_img, tmp_x, tmp_y );
					
					/** each GameTile needs to know the pixel coordinates of its
					 *   BASE rectangle so need to subtract image offsets  */
					map.gameTiles[x][y].setX_pix( tmp_x - x_offset );
					map.gameTiles[x][y].setY_pix( tmp_y - y_offset );
					
				} catch (java.lang.NullPointerException e) {
					e.printStackTrace();
				}

			}
		}
		
		/** place the active GameTile indicator */
		try {
			
			/** look up the current coords of active tile */
			tmp_x = map.getActive_tile().getX_pix();
			tmp_y = map.getActive_tile().getY_pix();
			
			/** create a polygon shape for indicator */
			IsoShapeDecorator i = new IsoShapeDecoratorBorder( 
						( new IsoShapeBase( this, tmp_x, tmp_y, SWT.NULL) )  
					);
			i.draw();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * File dialog to load map file: some of this code was modeled on 
	 * http://www.eclipse.org/articles/Article-Image-Viewer/Image_viewer.html
	 * 
	 */
	public void onLoad( ) {
		
		FileDialog fileChooser = new FileDialog( this.getShell(), SWT.OPEN);

		fileChooser.setText("Load map file");

		fileChooser.setFilterPath( System.getProperty("user.dir" + "terrain") );

		fileChooser.setFilterExtensions( new String[] { "*.txt" });

		try {
			String filename = fileChooser.open();

			FileReader in_file = new FileReader(filename);

			BufferedReader in_f_read = new BufferedReader( in_file );	// open file with bufferd io

			map.loadMap(in_f_read); // give file handle to map class to load gameTiles with data

		} catch (Exception e) {
			System.out.println( "onLoad exception, " + e.getMessage() + " using default tile for entire map." );
			e.printStackTrace();
		}
	}

	
	/**
	 * @return the x_ori
	 */
	public int getX_ori() {
		return x_ori;
	}

	/**
	 * @param x_ori the x_ori to set
	 */
	public void setX_ori(int x_ori) {
		this.x_ori = x_ori;
	}

	/**
	 * @return the y_ori
	 */
	public int getY_ori() {
		return y_ori;
	}

	/**
	 * @param y_ori the y_ori to set
	 */
	public void setY_ori(int y_ori) {
		this.y_ori = y_ori;
	}

	
	/**
	 * @return the map
	 */
	public GameMap getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(GameMap map) {
		this.map = map;
	}

	/**
	 * Keyboard movement commands: 
	 * Controls the movement of the MapCanvas in response to the aswd and arrow keys
	 * translates both x and y axes to give pseudo planar movement at an angle.
	 * <p>Checks for bounds of the map by evaluating the size of the GameMap 
	 * GameTile[][] array dimensions ( 0 <= x < x_game, 0 <= y < y_game).</p>
	 *  
	 * @param ke is a KeyEvent from the addKeyListener -> KeyAdapter constructor
	 */
	private void keyPressed( KeyEvent ke )
	{
		int scalar = 1;
		
		/** find out where we are */
		GameTile gameTile = this.map.getActive_tile();
		int tile_x = gameTile.getX();
		int tile_y = gameTile.getY();
		
		/** up */
		if( (tile_y - 1) >= 0 && 
				( ke.character == 'w' ||  ke.character == 'W' || ke.keyCode == SWT.ARROW_UP ) )
		{
			/** shift origin with respect to Canvas, map appears to move */
			x_ori -= Img.x_adj * scalar;
			y_ori += Img.y_adj * scalar;
			
			/** change active GameTile, tile select image will appear to move when
			 * Canvas is redrawn
			 */
			this.map.setActive_tile( tile_x, tile_y - 1 );

			/** redraw canvas */
			this.redraw();
			
			/** change the red indicator on the minimap to match select image movement */
			this.mini_child.setY_coord( tile_y - 1 );
			
			/** redraw the minimap */
			this.mini_child.redraw();
			
			return;
		}

		/** down */
		if( (tile_y + 1) < GameMap.y_game && 
				( ke.character == 's' ||  ke.character == 'S' || ke.keyCode == SWT.ARROW_DOWN ) )
		{
			x_ori += Img.x_adj * scalar;
			y_ori -= Img.y_adj * scalar;
			
			this.map.setActive_tile( tile_x, tile_y + 1 );

			this.redraw();
			
			this.mini_child.setY_coord( tile_y + 1 );
			this.mini_child.redraw();
			
			return;
		}

		/** left */
		if( (tile_x - 1) >= 0 && 
				( ke.character == 'a' ||  ke.character == 'A' || ke.keyCode == SWT.ARROW_LEFT ) )
		{
			x_ori += Img.x_adj * scalar;
			y_ori += Img.y_adj * scalar;
			
			this.map.setActive_tile( tile_x - 1, tile_y );

			this.redraw();
			
			this.mini_child.setX_coord( tile_x - 1 );
			this.mini_child.redraw();
			
			return;
		}
		
		/** right */
		if( (tile_x + 1) < GameMap.x_game && 
				( ke.character == 'd' ||  ke.character == 'D' || ke.keyCode == SWT.ARROW_RIGHT ) )
		{
			x_ori -= Img.x_adj * scalar;
			y_ori -= Img.y_adj * scalar;
			
			this.map.setActive_tile( tile_x + 1, tile_y );

			this.redraw();
			
			this.mini_child.setX_coord( tile_x + 1 );
			this.mini_child.redraw();
			
			return;
		}
	}
	
	/**
	 * Double click on the full size map changes the active tile, but 
	 * <b>not<\b> the x_, y_ori pair.
	 * <p>This behavior is different than the double click on the full size map.
	 * Double click on MiniMap changes the active GameTile and 
	 * <b>also<\b> x_ and y_ori for the full size MapCanvas.    
	 * <\p>
	 * @param e MouseEvent
	 */
	@Override
	public void mouseDoubleClick( MouseEvent e ){
		
		// get canvas relative coordinates of pixel clicked on
		
		// find plat coordinates containing given pixel by calc with x_ori and y_ori
		
		// draw selection graphic overlay on plat
		try {
			System.out.println( "MapCanvas mouse double click x y" + e.x + " " + e.y);



//			this.linked_map.setX_ori( (-1 * (e.x/ this.dim) * Img.x_adj) + ( (e.y/ this.dim) * Img.x_adj) );		
//			this.linked_map.setY_ori( (-1 * (e.x/ this.dim) * Img.y_adj) - ( (e.y/ this.dim) * Img.y_adj) );
//
//
//			x_coord.setText( Integer.toString( e.x/ this.dim ) );		// update the plat locations text boxes
//			y_coord.setText( Integer.toString( e.y/ this.dim ) );
//
//			this.redraw();
//
//			linked_map.redraw();
//			linked_map.setFocus();

		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	

	/**
	 * stub required: implement MouseListener
	 */
	@Override
	public void mouseUp(MouseEvent e){
		
	}
	
	/**
	 * stub required: implement MouseListener
	 */
	@Override
	public void mouseDown(MouseEvent e){
		
	}
	
	/**
	 * stub required: implement MouseMoveListener
	 */
	@Override
	public void mouseMove(MouseEvent e){
		
	}

	
	/**
	 * @param mini_child the mini_child to set
	 */
	public void setMini_child( MapMiniCanvas mini_child ) {
		this.mini_child = mini_child;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
