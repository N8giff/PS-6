import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 */
public class Polyline implements Shape {
	// TODO: YOUR CODE HERE
	private Color color;    //color of line
	private int x1, x2, y1, y2;    //coordinates of line
	ArrayList<Point> points = new ArrayList<>(); //track points

	/**
	 * Creates a line
	 *
	 * @param x1     x coordinate
	 * @param y1     y coordinate
	 * @param color color of shape
	 */
	public Polyline(int x1, int y1, Color color) {
		this.x1 = x1; this.y1 = y1;
		points.add(new Point(x1, y1));
		this.color = color;
	}

	/**
	 * move each point in points by dx and dy
	 *
	 * @param dx change in x
	 * @param dy change in y
	 */
	@Override
	public void moveBy(int dx, int dy) {
		for (int x = 0; x < points.size(); x++) {
			Point temp = points.get(x);
			points.set(x, new Point((int) (temp.getX()) + dx, (int) (temp.getY()) + dy)); //add new point
		}
	}

	/**
	 * Get the color of the shape
	 *
	 * @return Color
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Set the color of the shape
	 *
	 * @param color The shape's color
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Adds the point to the array and sets it as the end
	 *
	 * @param x2	x coordinate to add
	 * @param y2	y coordinate to add
	 */
	public void setEnd(int x2, int y2) {
		this.x2 = x2; this.y2 = y2;
		points.add(new Point(x2,y2));
	}

	/**
	 * Check if a given point is inside shape
	 *
	 * @param x given x
	 * @param y given y
	 * @return boolean
	 */
	@Override
	public boolean contains(int x, int y) {
		for (int i = 0; i < points.size() - 1; i++) {
			if (Segment.pointToSegmentDistance(i, y, (int) points.get(i).getX(), (int) points.get(i).getY(), (int) points.get(i + 1).getX(), (int) points.get(i + 1).getY()) <= 3) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Draw shape
	 *
	 * @param g graphic to draw to
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);    //set the color
		for (int j = 0; j < points.size() - 1; j++) {
			g.drawLine((int) points.get(j).getX(), (int) points.get(j).getY(), (int) points.get(j + 1).getX(), (int) points.get(j + 1).getY());
		}
	}

	/**
	 * print polyLine
	 *
	 * @return String of points and color of line
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Point p : points) {
			sb.append((int)(p.getX())).append(",").append((int)(p.getY())).append(",");
		}
		return "polyline " + sb + " " + color.getRGB();
	}
}