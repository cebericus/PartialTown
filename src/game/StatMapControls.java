
package game;

/**
 * @author nelsoncs
*
 * Used by GameMenusAndButtons.class
 * char code, String str, 
*  int bound_x_pos, int bound_y_pos, 
*  int bound_x,        int bound_y, 
*  String button
 * 
 */
public enum StatMapControls {

	/**
	 * Menu -> Plats -> Zoning -> Commercial
	 */
	COMMERCIAL('C', "Commercial",              6, 240, 128, 26, " Commercial"),
	
	/**
	 * Menu -> Plats -> Zoning -> Industrial
	 */
	INDUSTRIAL('I', "Industrial",                    6, 272, 128, 26, " Industrial"),
	
	/**
	 * Menu -> Plats -> Zoning -> Residential
	 */
	RESIDENTIAL('R', "Residential",                6, 304, 128, 26,  "Residential"),
	
	/**
	 * Menu -> Plats -> Zoning -> Unzoned
	 */
	UNZONED('U', "Unzoned",                        6, 336, 128, 26, "    Unzoned"),
	
  /**
   * Menu -> Plats -> Structures -> CoalPowerPlant
   */
	COAL('O', "Coal Power Plant",                    6, 388, 128, 26,  "Coal"),

  /**
   * Menu -> Plats -> Structures -> NaturalGasPowerPlant
   */
	GAS('N', "Natural Gas Power Plant",           6, 420, 128, 26, "NatGas"),

  /**
   * Menu -> Plats -> Structures -> PoliceStation
   */	
	POLICE('P', "Police Station",                      6, 452, 128, 26, "Police"),
	
  /**
   * Menu -> Plats -> Structures -> Park
   */
	PARKS('K', "Parks and Recreation",            6, 484, 128, 26, "Parks"),

  /**
   * Menu -> Plats -> Structures -> Road
   */
	ROADS('A', "Roads",                                 6, 516, 128, 26, "Roads"),

  /**
   * Menu -> Plats -> Structures -> SolarPowerArray
   */
	SOLAR('L', "Solar Power Array",                6, 548, 128, 26, "Solar"),

  /**
   * Menu -> Plats -> Structures -> SewageTreatmentPlant
   */
	SEWAGE('S', "Sewage Treatment Plant",   6, 580, 128, 26, "Sewage"),

  /**
   * Menu -> Plats -> Structures -> WindPowerFarm
   */
	WIND('E', "Wind Power Farm",                  6, 612, 128, 26, "Wind");
	
	private final char code;
	private final String str;
	private final int bound_x_pos; 
	private final int bound_y_pos; 
	private final int bound_x;
	private final int bound_y; 
	private final String button;

  /**
   * 
   */
  StatMapControls(char code, String str, 
		  								int bound_x_pos, int bound_y_pos, 
		  								int bound_x,        int bound_y, 
		  								String button
		  								) {

	  this.code = code;
	  this.str = str;
	  this.bound_x_pos = bound_x_pos; 
	  this.bound_y_pos = bound_y_pos; 
	  this.bound_x = bound_x;
	  this.bound_y = bound_y; 
	  this.button = button;
	  
  }

	/**
	 * @return the code
	 */
	public final char code() {
		return code;
	}

	/**
	 * @return the str
	 */
	public final String str() {
		return str;
	}

	/**
	 * @return the bound_x_pos
	 */
	public final int bound_x_pos() {
		return bound_x_pos;
	}

	/**
	 * @return the bound_y_pos
	 */
	public final int bound_y_pos() {
		return bound_y_pos;
	}

	/**
	 * @return the bound_x
	 */
	public final int bound_x() {
		return bound_x;
	}

	/**
	 * @return the bound_y
	 */
	public final int bound_y() {
		return bound_y;
	}

	/**
	 * @return the button
	 */
	public final String button() {
		return button;
	}
	
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}