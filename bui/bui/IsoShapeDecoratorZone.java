/**
 * 
 */
package bui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;

/**
 * @author sgk
 *
 */
public class IsoShapeDecoratorZone extends IsoShapeDecorator {

	/**
	 * @param base
	 */
	public IsoShapeDecoratorZone(IsoShapeBase base) {
		
		super(base);
	}

	/* (non-Javadoc)
	 * @see bui.IsoShape#setColor(int, int, int)
	 */
	@Override
	public void setColor(int r, int g, int b) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see bui.IsoShape#tryDispose()
	 */
	@Override
	public void tryDispose() {
		// TODO Auto-generated method stub

	}

	
	/* (non-Javadoc)
	 * @see bui.IsoShapeDecorator#draw()
	 */
	@Override
	public void draw() {
	}
	
	/* (non-Javadoc)
	 * @see bui.IsoShapeDecorator#draw()
	 */
	public void draw( String s ) {
		
		base.draw();
		
		this.drawZone( s );
	}

	private void drawZone( String s ) {
		/** check if we still have a graphics context, maybe create one */
		if( base.gc.isDisposed() == true )
			base.gc = new GC( base.parent );
		
		Color blk = new Color( super.base.parent.getDisplay(), 0, 0, 0 );
		base.gc.setForeground( blk );
		
		Font f = new Font(base.gc.getGCData().device, 
						   base.gc.getFont().toString(), 18, SWT.NORMAL );
		base.gc.setFont( f );
		
		
		base.gc.setAlpha(128);
		base.gc.drawText( s, base.x , base.y  );
		
		System.out.println( "IsoShapeDecoratorZone x, y : " + base.x + " " + base.y );
		
		blk.dispose();
		f.dispose();
		
		base.tryDispose();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
