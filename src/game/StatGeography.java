package game;
/**
 * @author nelsoncs
 * 
 */
public enum StatGeography {
	BEACH(0, 8, 0, 0, 0, 0, 0, 0, "desert.png" ), 
	DIRT(10,  0, 10, 0, 0, 0,   0,  0, "desert_0.png"  ), 
	GRASS(25, 5, 0, -2, 0, 0, 0, 0, "blank.png" ), 
	FORREST(200, 4, 0, -5, 0, 0, 0, 0, "tree3_clipped_branches.png" ), 
	WATER(0, 10, 0, 0, 0, 0, 0, 0, "waterlurd.png" ) ;

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
	StatGeography (int price, int happiness, int jobs, int pollution, int crime, 
			int housing, int power, int water, String img_name ) {

		stats = new Stats();

		stats.setPrice( price );
		stats.setHappiness( happiness );
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


	/**
	 * 
	 * main - tests the enum initialization, and the return values for first and last
	 * columns of each enum
	 */
	public static void main(String[] args) {
		
		// initialization for int type first column
		for( StatGeography g: StatGeography.values() )
		{
			System.out.println( "StatGeography: " + g );

			System.out.println( "Price: " + g.stats.getPrice() );
		}
		
		// initialization for String type last column
		for( StatGeography g: StatGeography.values() )
		{
			System.out.println( "StatGeography: " + g );

			System.out.println( "Image string: " + g.img );
		}
	}


}
