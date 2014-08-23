/**
 * 
 */
package game;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

/**
 * @author nelsoncs
 *
 */
public class IsoShapeDecoratorBorder extends IsoShapeDecorator {

	/**
	 * @param base
	 */
	public IsoShapeDecoratorBorder( IsoShapeBase base ) {
		
		super( base );		
	}

	/* (non-Javadoc)
	 * @see game.IsoShapeBase#draw()
	 */
	@Override
	public void draw() {
		base.draw();
		
		this.drawBorder();
	}
	
	/**
	 * 
	 */
	private void drawBorder(){
		/** check if we still have a graphics context, maybe create one */
		if( base.gc.isDisposed() == true )
			base.gc = new GC( base.parent );
		
		Color blk = new Color( super.base.parent.getDisplay(), 0, 0, 0 );
		
		base.gc.setAlpha(128);
		base.gc.setLineWidth(3);
		base.gc.setForeground( blk );
		base.gc.drawPolygon( base.shape );
		
		blk.dispose();
		
		base.tryDispose();
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see game.IsoShapeBase#setColor()
	 */
	@Override
	public void setColor(  int r, int g, int b ) {

		
	}
//
//	/* (non-Javadoc)
//	 * @see game.IsoShapeBase#trim()
//	 */
//	@Override
//	public Base trim() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public void tryDispose(){
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
