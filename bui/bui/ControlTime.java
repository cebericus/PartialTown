package bui;

/**
 *  @author nelsoncs
 * ControlTime is a barebones incremental time controller for the game
 * Hopefully, it can be replaced with a concurrent time controller at some point 
 * to give a more realistic experience
 */
public class ControlTime {
	
	boolean paused ;			// true means game is paused
	private int current_time;
	private int increment_time;
	
	/**
	 * constructor
	 */
	public ControlTime() {
		current_time = 0;
		increment_time = 1;
		
		// start the controller (ie the game) in a paused state
		paused = true;
	}


	/**
	 * 
	 * @return int
	 */
	public int getCurrent_time() {
		return current_time;
	}

	/**
	 * 
	 * @param current_time
	 */
	public void setCurrent_time(int current_time) {
		this.current_time = current_time;
	}


	/**
	 * 
	 * @param value
	 */
	public void setIncrement_time(int value) {
		this.increment_time = value;
	}

	/**
	 * 
	 * @return int
	 */
	public int getIncrement_time() {
		return this.increment_time;
	}

	/*
	 * advance multiplies the currently set time increment with the requested 
	 * number of periods (units) to advance
	 * @param units
	 * @return number of time units to advance
	 */
	public int advance( int units ) {
		 
		return ( increment_time * units);
	}

	/*
	 * kind of a lame function
	 */
	public boolean isPaused() {
		return paused;
	}

	/**
	 * 
	 * @param paused
	 */
	public void setPaused( boolean paused ) {
		this.paused = paused;
	}
	
	/*
	 * pause if the game is currently paused, will unpause and vice versa
	 * @param invert should be set to true to change pause, set to false to simply
	 * query current state
	 */
	public boolean invertPause() {
		
		if( isPaused() )
			setPaused(false);
		else
			setPaused(true);
		
		return this.paused;
	}

}
