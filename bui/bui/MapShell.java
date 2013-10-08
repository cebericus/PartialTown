package bui;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;

import org.eclipse.wb.swt.SWTResourceManager;

/**
 * MapShell is main() for the game and the core of the user GUI.
 * @author nelsoncs
 *
 */
public class MapShell {

	static String value;
	
	Shell sh;
	MapCanvas canvas;
	MapMiniCanvas mini_canvas;
	
	GameMap map;
	ImgSet map_images;

	private Text x_coord;
	private Text y_coord;
	
	private Text MayorPercentage;
	private Text CitizensTotalNumber;
	private Text CitizensPercentageHappy;
	private Text BusinessTotal;
	private Text BusinessCommercial;
	private Text JobsTotal;
	private Text JobsUnemployment;
	private Text RevenuesTotal;
	private Text PropertyValue;
	private Text PollutionGenerated;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text BusinessIndustrial;
	

	
	/**
	 * constructor
	 */
	public MapShell() {
		Display display = Display.getDefault();
		
		sh = new Shell();				// Shell apparently cannot be subclassed
		sh.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));

		sh.setSize(1920, 1080);
		sh.setText("Partial Town");
		
		this.createContents( sh );
		
		/** begin plat coordinates */
		// this needs to stay here so it can be handed to MapCanvas
		Label lblLocation = new Label(sh, SWT.NONE | SWT.SINGLE | SWT.RIGHT );
		lblLocation.setAlignment(SWT.CENTER);
		lblLocation.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblLocation.setBounds(6, 41, 128, 14);
		lblLocation.setText("Plat Location");

		x_coord = new Text(sh, SWT.BORDER | SWT.SINGLE | SWT.RIGHT );
		x_coord.setBounds(6, 56, 59, 24);
		
		/** TODO need s to be integrated with MapCanvas */
		x_coord.setText("0");
		
		y_coord = new Text(sh, SWT.BORDER | SWT.SINGLE | SWT.RIGHT );
		y_coord.setBounds(75, 56, 59, 24);
		
		/** TODO need s to be integrated with MapCanvas */
		y_coord.setText("0");
		
		x_coord.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent me ) {
				System.out.println( "text mod x_coord" );

			}
		});

		y_coord.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent me ) {
				System.out.println( "text mod y_coord" );
			}
		});
		
		
		// these are from snippet19, modified
		x_coord.addListener (SWT.Verify, new Listener () {
			public void handleEvent (Event e) {

				if( !platValidate( e.text ))
				{
					e.doit = false;
				}
			}
		});
		
		y_coord.addListener (SWT.Verify, new Listener () {
			public void handleEvent (Event e) {

				if( !platValidate( e.text ))
				{
					e.doit = false;
				}
			}
		});
		/** end plat coordinates */

		/** pass in plat coordinate Text boxes */
		map = new GameMap( x_coord, y_coord );
		//map_images = new ImgSet( display );
		
		canvas = new MapCanvas( sh, map, map_images );
		canvas.setBounds(140, 6, 1770, 1017);
		Color black = new Color(display, 255, 255, 255);
		canvas.setBackground(black);
		
		
//		Shell float_sh = new Shell(  SWT.TITLE |SWT.MIN |SWT.MAX | SWT.BORDER_SOLID |SWT.ON_TOP );
		Shell float_sh = new Shell( sh, SWT.ON_TOP);
		FormData float_sh_form_data = new FormData();
		float_sh_form_data.top = new FormAttachment(sh, 0,50);
		float_sh.setLayoutData(float_sh_form_data);
		
		float_sh.setBounds(200, 100, 260, 260);

		mini_canvas = new MapMiniCanvas( float_sh, map, map_images, canvas, x_coord, y_coord );
		mini_canvas.setBounds(0, 0, 258, 258);
		Color blue = new Color(display, 5, 5, 255);
		mini_canvas.setBackground(blue);
		

		DateTime dateTime = new DateTime(sh, SWT.BORDER);
		dateTime.setBounds(10, 6, 124, 29);

		/** Associated Buttons are in createContents() */
		Label labelZoning = null;
		sidebarLabels(labelZoning, "Zoning", 6, 220, 128, 14);
		
		Label labelStructures = null;		
		sidebarLabels(labelStructures, "Structures", 6, 368, 128, 14);
		
		Label lblMayoralPopularity = null;
		sidebarLabels(lblMayoralPopularity, "Mayoral Popularity", 6, 644, 128, 14);
		
		Label lblMayoralSentiment = new Label(sh, SWT.BORDER | SWT.SHADOW_IN);
		lblMayoralSentiment.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblMayoralSentiment.setFont(SWTResourceManager.getFont("Ubuntu", 14, SWT.NORMAL));
		lblMayoralSentiment.setAlignment(SWT.CENTER);
		lblMayoralSentiment.setBounds(75, 659, 59, 24);
		lblMayoralSentiment.setText("Hated");
		
		MayorPercentage = new Text(sh, SWT.BORDER | SWT.RIGHT);
		MayorPercentage.setText("-99%");
		MayorPercentage.setBounds(9, 659, 59, 24);

		Label lblCitizens = null;
		sidebarLabels(lblCitizens, "Citizens", 6, 689, 128, 14);
		
		CitizensTotalNumber = new Text(sh, SWT.BORDER | SWT.RIGHT);
		CitizensTotalNumber.setBounds(9, 704, 59, 24);
		
		CitizensPercentageHappy = new Text(sh, SWT.BORDER | SWT.RIGHT);
		CitizensPercentageHappy.setBounds(75, 704, 59, 24);
		
		Label lblBusiness = null;
		sidebarLabels(lblBusiness, "Business", 6, 764, 128, 14);
		
		BusinessTotal = new Text(sh, SWT.BORDER | SWT.RIGHT);
		BusinessTotal.setBounds(9, 779, 59, 24);
		
		BusinessCommercial = new Text(sh, SWT.BORDER | SWT.RIGHT);
		BusinessCommercial.setBounds(75, 779, 59, 24);
		
		Label lblJobs = null;
		sidebarLabels(lblJobs, "Jobs", 6, 839, 128, 14);
		
		JobsTotal = new Text(sh, SWT.BORDER | SWT.RIGHT);
		JobsTotal.setBounds(9, 854, 59, 24);
		
		JobsUnemployment = new Text(sh, SWT.BORDER | SWT.RIGHT);
		JobsUnemployment.setBounds(75, 854, 59, 24);
		
		Label lblRevenues = null;
		sidebarLabels(lblRevenues, "Revenues", 6, 884, 128, 14);
		
		RevenuesTotal = new Text(sh, SWT.BORDER | SWT.RIGHT);
		RevenuesTotal.setBounds(9, 899, 59, 24);
		
		PropertyValue = new Text(sh, SWT.BORDER | SWT.RIGHT);
		PropertyValue.setBounds(75, 899, 59, 24);
		float_sh.open();
		
		Label lblPollution = null;
		sidebarLabels(lblPollution, "Pollution", 6, 929, 128, 14);
		
		PollutionGenerated = new Text(sh, SWT.BORDER | SWT.RIGHT);
		PollutionGenerated.setBounds(9, 944, 59, 24);
		
		text_10 = new Text(sh, SWT.BORDER | SWT.RIGHT);
		text_10.setBounds(75, 944, 59, 24);
		
		text_11 = new Text(sh, SWT.BORDER | SWT.RIGHT);
		text_11.setBounds(9, 734, 59, 24);
		
		text_12 = new Text(sh, SWT.BORDER | SWT.RIGHT);
		text_12.setBounds(75, 734, 59, 24);
		
		text_13 = new Text(sh, SWT.BORDER | SWT.RIGHT);
		text_13.setBounds(75, 809, 59, 24);
		
		BusinessIndustrial = new Text(sh, SWT.BORDER | SWT.RIGHT);
		BusinessIndustrial.setBounds(9, 809, 59, 24);
		

		sh.open();
		sh.layout();
		
		float_sh.open();
		float_sh.layout();
		
		canvas.setFocus();
		
		while (!sh.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}
	
	/**
	 * helper method for the plat map text boxes found in MapShell constructor
	 * @param str
	 * @return false if not digit or prepended minus sign, otherwise true
	 */
	private boolean platValidate( String str ){
		
		char [] chars = new char [str.length ()];

		str.getChars (0, chars.length, chars, 0);

		if( !('0' <= chars[0] && chars[0] <= '9') && !(chars[0] == '-' ) ) // validate first char allowing minus sign
		{
			return false;
		}
		else
		{
			for (int i=0; i<chars.length; i++) {
				if (!('0' <= chars[i] && chars[i] <= '9') ) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * method for Menu -> Plats -> etc.
	 * @param ch Character
	 */
	private void placeZone( Character ch ){

		try {
			canvas.map.active_tile.setcZone( ch );	
			canvas.redraw();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method for Menu -> Plats -> etc.
	 * @param ch Character
	 */
	private void placeStructure( Character ch ){

		try {
			canvas.map.active_tile.setcStructure( ch );	
			canvas.redraw();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sidebarLabels(
		Label label,
		String text,
		int bound_x_pos, int bound_y_pos, int bound_x, int bound_y
		)
	{
		label = new Label(sh, SWT.SHADOW_IN | SWT.RIGHT);
		label.setText(text);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		label.setAlignment(SWT.CENTER);
		label.setBounds(bound_x_pos, bound_y_pos, bound_x, bound_y);
	}
	
	private void menuAndButtonCreate(
			Menu menu,
			MenuItem menuItem, 
			final char placeStructure, 
			String menuString, 
			int bound_x_pos, int bound_y_pos, int bound_x, int bound_y, 
			Button button,
			String buttonString
			) 
	{
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
	 * destructor for SWT must dispose of all resources created with new.
	 */
	private void exit() {
		this.canvas.dispose();
		this.mini_canvas.dispose();
	    this.sh.dispose();
	    System.exit(0);
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents( Shell sh ) {
		
		sh.setLayout(null);

		/**
		 * Menu -> 
		 */
		Menu menu = new Menu(sh, SWT.BAR);
		sh.setMenuBar(menu);
		
		/**
		 * Menu -> Map
		 */
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("Map");

		/**
		 * Menu -> Map -> SubMenu
		 */
		Menu file_sub_menu = new Menu(mntmFile);
		mntmFile.setMenu(file_sub_menu);

		/**
		 * Menu -> Map -> New
		 */
		MenuItem mntmNew = new MenuItem(file_sub_menu, SWT.NONE);
		mntmNew.setText("New");
		
		/**
		 * Menu -> Map -> Load
		 */
		MenuItem mntmLoad = new MenuItem(file_sub_menu, SWT.NONE | SWT.SHADOW_ETCHED_IN);
		mntmLoad.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//					canvas.onLoad();			// load new map, call file dialog
//					mini_canvas.redraw();		// second map is just redrawn using new data
//			}
			
			@Override
			public void widgetSelected(SelectionEvent e) {
					canvas.onLoad( );			// load new map, call file dialog
					canvas.redraw();
					mini_canvas.redraw();		// second map is just redrawn using new data
			}
		});
		mntmLoad.setText("Load");
		
		/**
		 * Menu -> Map -> Save
		 */
		MenuItem mntmSave = new MenuItem(file_sub_menu, SWT.NONE);
		mntmSave.setText("Save");
		
		/**
		 * Menu -> Map -> Exit
		 */
		MenuItem mntmExit = new MenuItem(file_sub_menu, SWT.NONE | SWT.SHADOW_ETCHED_IN);
		mntmExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				exit();
			}
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				exit();
			}
		});
		mntmExit.setText("Exit");
		/**
		 * Menu -> Plats
		 */
		MenuItem mntmNewItem = new MenuItem(menu, SWT.CASCADE);
		mntmNewItem.setText("Plats");
		
		/**
		 * Menu -> Plats -> SubMenu
		 */
		Menu menu_2 = new Menu(mntmNewItem);
		mntmNewItem.setMenu(menu_2);
		
		/**
		 * Menu -> Plats -> Zoning
		 */
		MenuItem mntmZoning = new MenuItem(menu_2, SWT.CASCADE);
		mntmZoning.setText("Zoning");
		
		Menu menu_1 = new Menu(mntmZoning);
		mntmZoning.setMenu(menu_1);
		
		/**
		 * Menu -> Plats -> Zoning -> Commercial
		 */
		MenuItem mntmCommercial = new MenuItem(menu_1, SWT.NONE);
//		
//		canvas.addListener (SWT.Paint, new Listener () {
//			public void handleEvent (Event event) {
//				placeImage( event );
//			}
//		});
		
		mntmCommercial.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetDefaultSelected(SelectionEvent event) {
//				placeImage();
//			}

//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				placeImage( 'O' );
//			}
		});
		mntmCommercial.setText("Commercial");
		
		Button btnCommercial = new Button(sh, SWT.NONE);
		btnCommercial.setBounds(6, 240, 128, 26);
		btnCommercial.setText(" Commercial");
		
//		image = new Image( display,
//				map_images.getZone_list().clone()[1].getImage().getImageData().scaledTo(40,40));
//		btnCommercial.setImage( image );
//		image.dispose();
		
		/**
		 * Menu -> Plats -> Zoning -> Industrial
		 */
		MenuItem mntmIndustrial = new MenuItem(menu_1, SWT.NONE);
		mntmIndustrial.setText("Industrial");
		
		Button btnIndustrial = new Button(sh, SWT.NONE);
		btnIndustrial.setBounds(6, 272, 128, 26);
		btnIndustrial.setText(" Industrial");
	
		// to do ----> should be a separate function for button images
//		Image image = new Image( display,
//				map_images.getZone_list().clone()[0].getImage().getImageData().scaledTo(40,40));
//		btnIndustrial.setImage( image );
//		image.dispose();
		
		/**
		 * Menu -> Plats -> Zoning -> Residential
		 */
		MenuItem mntmResidential = new MenuItem(menu_1, SWT.NONE);
		mntmResidential.setText("Residential");
		
		Button btnResidential = new Button(sh, SWT.NONE);
		btnResidential.setBounds(6, 304, 128, 26);
		btnResidential.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				placeZone( 'R' );
				
//				IsoShapeDecoratorZone z = new IsoShapeDecoratorZone(
//						(new IsoShapeBase(canvas, canvas.map.getActive_tile().getX_pix(), 
//								canvas.map.getActive_tile().getY_pix(), SWT.NULL)) );
//				z.draw("R");
				
				canvas.setFocus();
			}
		});
		btnResidential.setText("Residential");
		
		/** set a mini-image in the button
//		image = new Image( display,
//				map_images.getZone_list().clone()[2].getImage().getImageData().scaledTo(40,40));
//		btnResidential.setImage( image );
//		image.dispose();
		
		/**
		 * Menu -> Plats -> Zoning -> Unzoned
		 */
		MenuItem mntmUnzoned = new MenuItem(menu_1, SWT.NONE);
		mntmUnzoned.setText("Unzoned");
		
		Button btnUnzoned = new Button(sh, SWT.NONE);
		btnUnzoned.setBounds(6, 336, 128, 26);
		btnUnzoned.setText("    Unzoned");
		
//		image = new Image( display,
//		map_images.getZone_list().clone()[3].getImage().getImageData().scaledTo(40,40));
//btnUnzoned.setImage( image );
//image.dispose();
		
		/**
		 * Menu -> Plats -> Structures
		 */
		MenuItem mntmStructures_1 = new MenuItem(menu_2, SWT.CASCADE);
		mntmStructures_1.setText("Structures");
		
		/**
		 * Menu -> Plats -> Structures - SubMenu
		 */
		Menu menu_4 = new Menu(mntmStructures_1);
		mntmStructures_1.setMenu(menu_4);
		
		/**
		 * TODO: iterable structure to make these controls.
		 * 
		 * Menu -> Plats -> Structures -> CoalPowerPlant
		 */
		MenuItem mntmCoalPowerPlant = null;
		Button btnCoal = null;
		menuAndButtonCreate(
				menu_4,
				mntmCoalPowerPlant,
				'O',
				"Coal Power Plant",
				6, 388, 128, 26,
				btnCoal,
				"Coal"
				);
		
		/**
		 * Menu -> Plats -> Structures -> NaturalGasPowerPlant
		 */
		MenuItem mntmNaturalGasPlant = null;
		Button btnGas = null;
		menuAndButtonCreate(
				menu_4,
				mntmNaturalGasPlant,
				'N',
				"Natural Gas Power Plant",
				6, 420, 128, 26,
				btnGas,
				"NatGas"
				);
		
		/**
		 * Menu -> Plats -> Structures -> Park
		 */
		MenuItem mntmPark = null;
		Button btnParks = null;
		menuAndButtonCreate(
				menu_4,
				mntmPark,
				'K',
				"Parks and Recreation",
				6, 484, 128, 26,
				btnParks,
				"Parks"
				);
		
		/**
		 * Menu -> Plats -> Structures -> PoliceStation
		 */
		MenuItem mntmPoliceStation = null;
		Button btnPolice = null;
		menuAndButtonCreate(
				menu_4,
				mntmPoliceStation,
				'P',
				"Police Station",
				6, 452, 128, 26,
				btnPolice,
				"Police"
				);
		
		/**
		 * Menu -> Plats -> Structures -> Road
		 */
		MenuItem mntmRoad = null;
		Button btnRoad = null;
		menuAndButtonCreate(
				menu_4,
				mntmRoad,
				'A',
				"Roads",
				6, 516, 128, 26,
				btnRoad,
				"Roads"
				);
		
		/**
		 * Menu -> Plats -> Structures -> SolarPowerArray
		 */
		MenuItem mntmSolarPowerArray = null;
		Button btnSolarPowerArray = null;
		menuAndButtonCreate(
				menu_4,
				mntmSolarPowerArray,
				'L',
				"Solar Power Array",
				6, 548, 128, 26,
				btnSolarPowerArray,
				"Solar"
				);
		
		/**
		 * Menu -> Plats -> Structures -> SewageTreatmentPlant
		 */
		MenuItem mntmSewageTreatmentPlant = null;
		Button btnSewageTreatmentPlant = null;
		menuAndButtonCreate(
				menu_4,
				mntmSewageTreatmentPlant,
				'S',
				"Sewage Treatment Plant",
				6, 580, 128, 26,
				btnSewageTreatmentPlant,
				"Sewage"
				);
		
		/**
		 * Menu -> Plats -> Structures -> WindPowerFarm
		 */
		MenuItem mntmWindPowerFarm = null;
		Button btnWindPowerFarm = null;
		menuAndButtonCreate(
				menu_4,
				mntmWindPowerFarm,
				'E' ,
				"Wind Power Farm",
				6, 612, 128, 26,
				btnWindPowerFarm,
				"Wind"
				);
		
		MenuItem mntmGeography = new MenuItem(menu_2, SWT.CASCADE);
		mntmGeography.setText("Geography");
		
		Menu menu_5 = new Menu(mntmGeography);
		mntmGeography.setMenu(menu_5);
		
		MenuItem mntmBeach = new MenuItem(menu_5, SWT.NONE);
		mntmBeach.setText("Beach");
		
		MenuItem mntmDirt = new MenuItem(menu_5, SWT.NONE);
		mntmDirt.setText("Dirt");
		
		MenuItem mntmForrest = new MenuItem(menu_5, SWT.NONE);
		mntmForrest.setText("Forrest");
		
		MenuItem mntmGrass = new MenuItem(menu_5, SWT.NONE);
		mntmGrass.setText("Grass");
		
		MenuItem mntmWater = new MenuItem(menu_5, SWT.NONE);
		mntmWater.setText("Water");
		
		/**
		 * Menu -> Time
		 */
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.CASCADE);
		mntmNewItem_1.setText("Time");
		
		/**
		 * Menu -> Time->SubMenu
		 */
		Menu menu_3 = new Menu(mntmNewItem_1);
		mntmNewItem_1.setMenu(menu_3);
		
		/**
		 * Menu -> Forward
		 */
		MenuItem mntmForward = new MenuItem(menu_3, SWT.NONE);
		mntmForward.setText("Forward");
		
		/**
		 * Menu -> Pause
		 */
		MenuItem mntmPause = new MenuItem(menu_3, SWT.NONE);
		mntmPause.setText("Pause");
		
		/**
		 * Menu -> Report
		 */
		MenuItem mntmReport = new MenuItem(menu, SWT.NONE);
		mntmReport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		mntmReport.setText("Report");
		
		/**
		 * Menu -> Help
		 */
		MenuItem mntmHelp = new MenuItem(menu, SWT.NONE);
		mntmHelp.setText("Help");

	}
	

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MapShell sh = new MapShell();

		sh.exit();
	}
}
