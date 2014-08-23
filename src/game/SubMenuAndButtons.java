/**
 * 
 */
package game;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * @author nelsoncs
 *
 */
public class SubMenuAndButtons {
	
	Shell sh;
	MapCanvas canvas;
	Menu menu; 
	MenuItem menuItem;
	char placeStructure; 
	String menuString; 
	int bound_x_pos; 
	int bound_y_pos; 
	int bound_x; 
	int bound_y; 
	Button button; 
	String buttonString;

	/**
	 * @param sh
	 * @param canvas
	 * @param menu
	 * @param menuItem
	 * @param placeStructure
	 * @param menuString
	 * @param bound_x_pos
	 * @param bound_y_pos
	 * @param bound_x
	 * @param bound_y
	 * @param button
	 * @param buttonString
	 */
	public SubMenuAndButtons(
			Shell sh,
			final MapCanvas canvas,
			Menu menu, 
			MenuItem menuItem,
			final char placeStructure, 
			String menuString, 
			int bound_x_pos, 
			int bound_y_pos, 
			int bound_x, 
			int bound_y, 
			Button button, 
			String buttonString
			) {
		
		this.sh = sh;
		this.canvas = canvas;
		this.menu = menu;
		this.menuItem = menuItem;
		this.menuString = menuString;
		this.placeStructure = placeStructure;
		this.bound_x_pos = bound_x_pos; 
		this.bound_y_pos = bound_y_pos; 
		this.bound_x = bound_x; 
		this.bound_y = bound_y; 
		this.button = button; 
		this.buttonString = buttonString;
		
		
		menuItem = new MenuItem(menu, SWT.NONE);

		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				placeStructure(placeStructure);
			}
		});
		menuItem.setText(menuString);

		button = new Button(sh, SWT.NONE);
		button.setBounds(bound_x_pos, bound_y_pos, bound_x, bound_y);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				placeStructure(placeStructure);
				canvas.setFocus();
			}
		});
		button.setText(buttonString);
	}

	/**
	 * method for Menu -> Plats -> etc.
	 * 
	 * @param ch
	 *            Character
	 */
	private void placeStructure(Character ch) {

		try {
			canvas.map.active_tile.setcStructure(ch);
			canvas.redraw();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
