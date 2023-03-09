import java.util.*;
import java.awt.*;
public class Sketch {
    public TreeMap<Integer, Shape> shapes;
    public int id = 0;

    /**
     * Constructor initializes treemap
     */
    public Sketch(){
        shapes = new TreeMap<Integer,Shape>();
    }

    /**
     * Add shape to map and return shape ID
     * @param s shape to add
     * @return  int
     */
    public int addSketch(Shape s){
        shapes.put(id,s);
        id = id +=1;
        return id;
    }

    /**
     * Add shape to the map with a given ID
     * @param id    ID for the shape
     * @param s shape to add
     */
    public void addSketch(int id, Shape s){
        shapes.put(id,s);
    }

    /**
     * get shape by ID
     * @param id    ID to search
     * @return Shape
     */
    public Shape getShape(int id){
        return shapes.get(id);
    }

    /**
     * Checks for shapes that contain point
     * @param p point to check
     * @return  Shape
     */
    public Shape containsPoint(Point p) {
        for (int id : shapes.descendingKeySet()) {
            int xVal = (int) p.getX();
            int yVal = (int) p.getY();
            if (shapes.get(id).contains(xVal, yVal)) {
                return shapes.get(id);
            }
        }
        return null;
    }

    /**
     * Draw shapes to graphic
     * @param g graphic to draw to
     */
    public void draw(Graphics g) {
        for(Shape currShape : shapes.values()){
            currShape.draw(g);
        }
    }

    /**
     * Remove a shape by ID
     * @param id    ID to remove
     */
    public void remove(int id){
        shapes.remove(id,shapes.get(id));
    }

    /**
     * Get a shape ID
     * @param s shape to search for
     * @return  int
     */
    public int getId(Shape s){
        for(int id: shapes.keySet()){
            if(shapes.get(id).equals(s)){
                return id;
            }
        }
        return -1; //if not found, return -1
    }

    /**
     * Get the map of all shapes
     * @return  TreeMap
     */
    public TreeMap<Integer,Shape> getShapes(){
        return shapes;
    }

    /**
     * Delete all shapes
     */
    public void empty(){
        shapes = new TreeMap<Integer, Shape>();
    }
}