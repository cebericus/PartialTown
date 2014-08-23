package game;
/**
 * @author nelsoncs
 * 
 */
public enum StatStructure {
	COAL_POWER(4000, -10, 50, 10, 0, 0, 5000, 25, "powerscoal-full.png", 0, 0 ),
	NATURAL_GAS(3500, -4, 50, 3, 0, 0, 2500, 10, "industryhc.png", 0, 0 ), 
	PARK(100, 8, 0, -3, 0, 0, -10, -5, "parkland-plane.png", 0, 0 ),
	POLICE_STATION(1000, 2, 15, 0, -10, 0, -15, 10, "firestation10.png", 0, 0 ), 
	ROAD(0, 0, 0, 0, 0, 0, 0, 0, "roadludr.png", 0, 0),
	SEWAGE(1000, -8, 20, 2, 0, 0, -35, 0, "firedone4.png", 0, 0 ), 
	//SEWAGE(1000, -8, 20, 2, 0, 0, -35, 0, "brewery_i_00_128.png", 0, 0 ), 
	SOLAR_POWER(2500, -2, 10, 0, 0, 0, 1000, 0, "powerssolar.png", 0, 0 ), 
	//WIND_POWER(2500, -3, 20, 0, 0, 0, 1000, 0, "windmill2w.png", 0, 0 );
	WIND_POWER(2500, -3, 20, 0, 0, 0, 1000, 0, "windmill3g.png", 20, 275 );


	private Stats stats;
	private String img;
	
	/** base_pre and base_post are used for images with overhang, shadows or other
	 * scaling issues.  Affect the image X-dimension
	 */
	private int base_pre;
	private int base_post;


	/**
	 * constructor
	 * @param price
	 * @param happiness
	 * @param jobs
	 * @param pollution
	 * @param crime
	 * @param housing
	 * @param power
	 * @param water
	 * @param img_name
	 * @param base_pre,
	 * @param base_post
	 */
	StatStructure (int price, int happiness, int jobs, int pollution, int crime, 
					int housing, int power, int water, 
					String img_name, int base_pre, int base_post ) {

		stats = new Stats();

		stats.setPrice( price );
		stats.setHappiness( happiness );
		stats.setJobs( jobs );
		stats.setPollution( pollution );
		stats.setCrime( crime );
		stats.setZone( housing );
		stats.setPower( power );
		stats.setWater( water );

		this.img = img_name;
		this.base_pre = base_pre;
		this.base_post = base_post;
		
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the base_pre
	 */
	public int getBase_pre() {
		return base_pre;
	}

	/**
	 * @return the base_post
	 */
	public int getBase_post() {
		return base_post;
	}

	/**, 0, 0
	 * 
	 * main - tests the enum initialization, and the return values for first and last
	 * columns of each enum
	 */
	public static void main(String[] args) {

		// initialization for int type first column
		for( StatStructure s: StatStructure.values() )
		{
			System.out.println( "StatStructure: " + s );

			System.out.println( "Price: " + s.stats.getPrice() );

		}

		// initialization for String type last column
		for( StatStructure s: StatStructure.values() )
		{
			System.out.println( "StatStructure: " + s );

			System.out.println( "Image string: " + s.img );

		}
	}


}

