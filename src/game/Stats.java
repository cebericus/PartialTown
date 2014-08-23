package game;
/*
 * @author nelsoncs
 */


public class Stats {
	
	/*
	 * constructor
	 * @param none
	 */
	Stats()
	{
		price = 0;
		happiness = 0;
		jobs = 0;
		pollution = 0;
		crime = 0;
		housing = 0;
		power = 0;
		water= 0;
		zone = 0;
	}
	
	private int price;

	protected void setPrice(int value) {
		this.price = value;
	}

	protected int getPrice() {
		return this.price;
	}

	private int happiness;

	protected void setHappiness(int value) {
		this.happiness = value;
	}

	protected int getHappiness() {
		return this.happiness;
	}

	private int jobs;

	protected void setJobs(int value) {
		this.jobs = value;
	}

	protected int getJobs() {
		return this.jobs;
	}

	private int pollution;

	protected void setPollution(int value) {
		this.pollution = value;
	}

	protected int getPollution() {
		return this.pollution;
	}

	private int crime;

	protected void setCrime(int value) {
		this.crime = value;
	}

	protected int getCrime() {
		return this.crime;
	}

	private int housing;
	
	protected void setHousing(int value) {
		this.housing = value;
	}

	private int getHousing() {
		return this.housing;
	}

	private int power;

	protected void setPower(int value) {
		this.power = value;
	}

	protected int getPower() {
		return this.power;
	}

	private int water;

	protected void setWater(int value) {
		this.water = value;
	}

	private int getWater() {
		return this.water;
	}

	private int zone;

	protected void setZone(int value) {
		this.zone = value;
	}

	protected int getZone() {
		return this.zone;
	}
   
	/*
	 * 3.1
	 * main - tests the constructor and "set" functions, tests "set and get" functions
	 * @param none
	 */
	public static void main(String[] args) {
		Stats stats = new Stats();
		
		// 3.1.1 constructor and set functions
		System.out.println( "constructor returns" );

		System.out.println( stats.getPrice() );
		System.out.println( stats.getHappiness() );
		System.out.println( stats.getPollution() );
		System.out.println( stats.getCrime() );
		System.out.println( stats.getZone() );
		System.out.println( stats.getPower() );
		System.out.println( stats.getWater() );

		// 3.1.2 set and get functions
		stats.setPrice( 1 );
		stats.setHappiness( 2 );
		stats.setPollution( 3 );
		stats.setCrime( 4 );
		stats.setZone( 5 );
		stats.setPower( 6 );
		stats.setWater( 7 );


		System.out.println( "setting with sequence 1 thru 7 returns" );

		System.out.println( stats.getPrice() );
		System.out.println( stats.getHappiness() );
		System.out.println( stats.getPollution() );
		System.out.println( stats.getCrime() );
		System.out.println( stats.getZone() );
		System.out.println( stats.getPower() );
		System.out.println( stats.getWater() );
	}

   
   }
