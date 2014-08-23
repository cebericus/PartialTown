package game;
/*
 * @author nelsoncs
 */
public enum StatZone {
	INDUSTRIAL(450, -5, 75, 7, 7, 0, -75, -35, "desert.png" ), 
	COMMERCIAL(350, 2, 35, 2, 3, 0, -35, -25, "desert_0.png" ), 
	RESIDENTIAL(250, 0, -100, 0, 1, -30, -30, -15, "tree3.png" ),
	UNZONED(     0,  0,   0,  0, 0,   0,  0,   0, "black.png" ),
	SELECT(   0,  0,   0,  0, 0,   0,  0,   0, "select_0.png" );

	private Stats stats;
	private String img;

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
	 */
	StatZone (int price, int happiness, int jobs, int pollution, int crime, 
			int housing, int power, int water, String img_name ) {
		
		stats = new Stats();
		
		stats.setPrice( price );
		stats.setHappiness( happiness );
		stats.setJobs( jobs );
		stats.setPollution( pollution );
		stats.setCrime( crime );
		stats.setZone( housing );
		stats.setPower( power );
		stats.setWater( water );
		
		img = img_name;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	/*
	 * 3
	 * main - tests the enum initialization, and the return values for first and last
	 * columns of each enum
	 * @param none
	 */
	public static void main(String[] args) {

		//  initialization for int type first column
		for( StatZone z: StatZone.values() )
		{
			System.out.println( "StatZone: " + z );

			System.out.println( "Price: " + z.stats.getPrice() );

		}

		// initialization for String type last column
		for( StatZone z: StatZone.values() )
		{
			System.out.println( "StatZone: " + z );

			System.out.println( "Image string: " + z.img );

		}
	}

}
