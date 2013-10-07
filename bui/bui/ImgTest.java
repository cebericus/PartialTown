package bui;


import java.io.FileNotFoundException;

import junit.framework.TestCase;
import junit.framework.Assert;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * test Img.java test the constructor for opening and loading
 * image files from file system into an ImageData object, section 3.5
 */
public class ImgTest extends TestCase {

	Display display;
	
	String curr_dir = new String();
	
	@Before
	public void setUp() throws Exception {
		this.display = new Display();

		curr_dir = System.getProperty("user.dir") + "/bui/";
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * test constructor with the constructor function, section 3.5.1
	 */
	@Test(expected=SWTException.class)
	public final void testImgBadFile() {
		
		boolean exception = false;
		
		try {
			Img i = new Img( display, curr_dir +  "" );
			
		} catch (SWTException e) {
			exception = true;
		}
		
		Assert.assertTrue(exception);
	}
	
	/*
	 * test constructor with null path/filename, section 3.5.1
	 */
	@Test
	public final void testImgNullFile() {
		
		boolean exception = false;
		
		try {
			Img i = new Img( display, curr_dir + "deserta.png" );
			
		} catch (SWTException e) {
			exception = true;
		}
		
		Assert.assertTrue(exception);		
	}
	
	/*
	 * test constructor with good filename/path, section 3.5.1
	 */
	@Test
	public final void testImgGoodFile() {
		
		boolean exception = false;
		
		try {
			Img i = new Img( display, curr_dir +  "desert.png" );
			
		} catch (SWTException e) {
			exception = true;
		}
		
		Assert.assertFalse(exception);	
	}

	/*
	 * test constructor with good filename/path, section 3.5.2
	 */
	@Test
	public final void testImgWrongType() {
		
		boolean exception = false;
		
		try {
			Img i = new Img( display, curr_dir +  "firestation6.jpg" );
			
		} catch (SWTException e) {
			exception = true;
		}
		
		Assert.assertTrue(exception);	
	}
}
