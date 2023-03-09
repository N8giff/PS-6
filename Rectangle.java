import java.awt.Color;
import java.awt.Graphics;

/**
 * A rectangle-shaped Shape
 * Defined by an upper-left corner (x1,y1) and a lower-right corner (x2,y2)
 * with x1<=x2 and y1<=y2
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author CBK, updated Fall 2016
 */
public class Rectangle implements Shape {
	// TODO: YOUR CODE HERE
	private int x1,y1,x2,y2; //rectangle corners
	private Color color; //rectangle color

	/**
	 * Creates a rectangle with only one corner
	 * @param x1	x coordinate of top left corner
	 * @param y1	y coordinate of top left corner
	 * @param color	color of shape
	 */
	public Rectangle(int x1,int y1,Color color){
		this.x1 = x1;
		this.x2 = x1;
		this.y1 = y1;
		this.y2 = y1;
		this.color = color;
	}

	/**
	 * Creates a rectangle with two corners
	 * @param x1	x coordinate of top left corner
	 * @param y1	y coordinate of top left corner
	 *  @param x2    x coordinate of bottom right corner
	 * 	@param y2	y coordinate of bottom right corner
	 * @param color	color of shape
	 */
	public Rectangle(int x1,int y1, int x2, int y2, Color color){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.color = color;
	}

	/**
	 * Sets corner coordinates for rectangle
	 * @param x1	top left x
	 * @param y1	top left y
	 * @param x2	bottom right x
	 * @param y2	bottom right y
	 */
	public void setCorners(int x1, int y1, int x2, int y2){
		this.x1 = Math.min(x1,x2);
		this.y1 = Math.min(y1,y2);
		this.x2 = Math.max(x1,x2);
		this.y2 = Math.max(y1,y2);
	}

	/**
	 * moves the rectangle by dx/dy
	 * @param dx	change in x
	 * @param dy	change in y
	 */
	@Override
	public void moveBy(int dx, int dy) {
		x1 += dx;
		y1 += dy;
		x2 += dx;
		y2 += dy;
	}

	/**
	 * Get color of rectangle
	 * @return	Color of the rectangle
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Set color of the rectangle
	 * @param color The shape's color
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Checks if a given point lies inside the rectangle
	 * @param x	x value to check
	 * @param y	y value to check
	 * @return	boolean value
	 */
	@Override
	public boolean contains(int x, int y) {
		int xDist = (x2-x1)/2;	//max x dist to center
		int yDist = (y2-y1)/2; //max y dist to center
		int dx = x - (x1 + xDist); // given x dist from center
		int dy = y - (y1 + yDist); // given y dist from center

		return Math.pow(dx / xDist,2) + Math.pow(dy / yDist, 2) <= 1;
	}

	/**
	 * draws the rectangle
	 * @param g	graphic to draw to
	 */
	@Override
	public void draw(Graphics g) {

		g.setColor(color);
		g.fillRect(x1,y1,(x2-x1),(y2-y1));
	}

	/**
	 * print rectangle stats
	 * @return	String
	 */
	public String toString() {
		return "rectangle" + " " + x1 + " " +
				y1 + " " + x2 + " " + y2 + " " +
				color.getRGB();
	}
}
