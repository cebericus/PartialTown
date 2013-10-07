package bui;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A minimap to parallel the full size game map and facilitate navigation
 * subclass of MapCanvas that overrides the viewPortDraw method with a colored
 * pixel for each tile.
 * @see MapCanvas
 * @author nelsoncs
 */
public class MapMiniCanvas extends MapCanvas {
	
	/** dimension of "dots" on the minimap, so dim= 2 will be a 2 x 2 pixel "dot"*/
	private int dim = 2;
	private Color red;
	private Map<Character, Color> mini_map;
	
	private MapCanvas linked_canvas;
	
	/** used to update plat coordinate text boxes in the UI */
	protected Text x_coord_txt;
	protected Text y_coord_txt;
	
	/**
	 * constructor
	 * @param sh
	 * @param map
	 * @param imgs
	 * @param linked_canvas
	 * @param x_coord 
	 * @param y_coord 
	 */
	public MapMiniCanvas( Shell sh, GameMap map, ImgSet imgs, 
						   MapCanvas linked_canvas, Text x_coord, Text y_coord ) {
		
		super( sh, map, imgs );						// explicit call to super constructor
		
		Display display = sh.getDisplay();
		
		Color color;
		
		// TODO convert these to (rgba, true) and set alpha to 50% transparency
		// note to do this should be moved to the IMGList or similar class to group together with others
		mini_map = new HashMap<Character, Color>();
		mini_map.put( 'B', color = new Color( display, 227, 222, 195 ) );	// beach
		mini_map.put( 'D', color = new Color( display, 186, 170, 97 ) ); 	// dirt
		mini_map.put( 'T', color = new Color( display, 39, 54, 15 ) ); 	// trees (forest)
		mini_map.put( 'G', color = new Color( display, 0x74, 122, 41 ) ); 	// grass
		mini_map.put( 'W', color = new Color( display, 137, 143, 160 ) ); 	// water
		
		this.red = new Color( display, 255, 0, 0 );

		// TODO not sure when to dispose colors
		
		this.linked_canvas = linked_canvas;
		
		this.linked_canvas.setMini_child( this );
		
		// initialize plat read out
		this.x_coord_txt = x_coord;
		this.y_coord_txt = y_coord;
	}

	/**
	 * 
	 */
	@Override
	public void viewPortDraw( Event event ){

		// build the  map on canvas
		int x = 0;
		int y = 0;
		
		int x_max = GameMap.x_game;
		int y_max = GameMap.y_game;

		// Note: loading from array x first then y will "cut tops off of trees"
		for( y = 0; y < y_max; ++y ){	
			for( x = 0; x < x_max; ++x ){

				try {
					if(     y == Integer.parseInt(this.y_coord_txt.getText()) 
						 && x == Integer.parseInt(this.x_coord_txt.getText()) ){
						
						/** if we are drawing the active tile, make it red */
						event.gc.setForeground( red );
					}
					else
						
						/** otherwise make the tile a color based on the geography */
						event.gc.setForeground( mini_map.get( map.gameTiles[x][y].getcGeo() ) );

					/** need to size the displayed mini-tile base on mini map dimensions */
					int tmp_x = x * this.dim;
					int tmp_y = y * this.dim;
					
					event.gc.drawPoint( tmp_x, tmp_y );
					event.gc.drawPoint( tmp_x+1, tmp_y );
					event.gc.drawPoint( tmp_x, tmp_y+1 );
					event.gc.drawPoint( tmp_x+1, tmp_y+1 );

				} catch (java.lang.NullPointerException e) {
					System.out.println( "mini map point error" );
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Double click on MiniMap changes the active GameTile and 
	 * <b>also<\b> x_ and y_ori for the full size MapCanvas.  This behavior
	 * is different than the double click on the full size map.  Dbl clk on the
	 * full size map changes the active tile, but <b>not<\b> the x_, y_ori pair.
	 * <p>
	 * This function has to have x and y sign flipped and also flip the sign for the
	 * adjustment term when compared to the drawViewport function in MapCanvas.<\p>
	 */
	@Override
	public void mouseDoubleClick( MouseEvent e ){

		try {
			System.out.println( "e.x e.y  : " + e.x + " " + e.y );
			
			/** set up for transform */
			Rectangle rec = new Rectangle( 0,0,0,0 );
			rec.x = e.x / this.dim;
			rec.y = e.y / this.dim;
			
			/** update the plat location text boxes */
			x_coord_txt.setText( Integer.toString( e.x / this.dim ) );		
			y_coord_txt.setText( Integer.toString( e.y / this.dim ) );
			
			/** set active_tile */
			this.linked_canvas.getMap().setActive_tile( rec.x, rec.y);
			
			/** calculate tile position */
			rec = isoTfn( rec, Img.x_adj, Img.y_adj, false );

			/** shift the main map to new location */
			this.linked_canvas.setX_ori( rec.x );	
			this.linked_canvas.setY_ori( rec.y );

			this.redraw();

			linked_canvas.redraw();
			linked_canvas.setFocus();

		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * @return the x_coord
	 */
	public Text getX_coord() {
		return x_coord_txt;
	}

	/**
	 * @param x_coord the x_coord to set
	 */
	public void setX_coord( int x_coord ) {
		this.x_coord_txt.setText( Integer.toString( x_coord ) );
	}

	/**
	 * @return the y_coord
	 */
	public Text getY_coord() {
		return y_coord_txt;
	}

	/**
	 * @param y_coord the y_coord to set
	 */
	public void setY_coord( int y_coord ) {
		this.y_coord_txt.setText( Integer.toString( y_coord ) );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
