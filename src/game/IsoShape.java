/**
 * 
 */
package game;

/**
 * interface specification for isometric shape decorator pattern
 * @author 
 * 
 */
public interface IsoShape {
	public void draw();
	public void setColor( int r, int g, int b );
	public void tryDispose();
}
