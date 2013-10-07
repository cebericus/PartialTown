package bui;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * @author nelsoncs
 */
public class ControlTimeTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * test constructor, section 3.3.1
	 */
	@Test
	public final void testTimeControl() {
		ControlTime tc = new ControlTime();
		
		Assert.assertEquals(0, tc.getCurrent_time() );
		Assert.assertEquals(1, tc.getIncrement_time() );
	}

	/*
	 * test advance() function should return arithmetic product
	 * of the class variable increment_time and the function argument
	 * units, section 3.3.2
	 */
	@Test
	public final void testAdvance() {
		ControlTime tc = new ControlTime();
		
		Assert.assertEquals("test advance 5 time units", 
				(5 * tc.getIncrement_time()), tc.advance(5));
	}

	/*
	 * test boolean value of paused variable, probably cannot get more trivial
	 * than this function, section 3.3.3
	 */
	@Test
	public final void testIsPaused() {
		ControlTime tc = new ControlTime();
		
		tc.setPaused(true);
		Assert.assertEquals("test isPaused true", 
				true, tc.isPaused());
		
		tc.setPaused(false);
		Assert.assertEquals("test isPpaused false", 
				false, tc.isPaused());
	}
	
	/*
	 * test function to invert paused variable from current value, section 3.3.4
	 */
	@Test
	public final void testInvertPause() {
		ControlTime tc = new ControlTime();
		
		tc.setPaused(true);
		Assert.assertEquals("test invertPaused true", 
				false, tc.invertPause());
		
		Assert.assertEquals("test invertPaused false", 
				true, tc.invertPause());
	}

}
