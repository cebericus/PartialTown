/**
 * 
 */
package bui;

/**
 * @author nelsoncs
 *
 */
abstract public class IsoShapeDecorator implements IsoShape {
	
	protected final IsoShapeBase base;
	
	public IsoShapeDecorator( IsoShapeBase base ){
		this.base = base;
	}


	abstract public void draw();


//	public void setColor(int r, int g, int b) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	public void tryDispose() {
//		// TODO Auto-generated method stub
//		
//	}
//	
	

}
