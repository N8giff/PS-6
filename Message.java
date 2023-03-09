import java.awt.*;
import java.util.Arrays;

/**
 * @title Message
 * @subtitle Assignment: PS-6
 * @Author Nathan Giffard
 * @class Dartmouth CS 10, Winter 2023
 * @date March 7th, 2023
 * @description Updates the sketch based on messages from users.
 */
public class Message {
    private Shape curr = null; //current shape
    String[] message;   //message broken out into array
    Editor e;   //editor
    Sketch sketch;  //sketch
    String text;    //text


    /**
     * Constructor
     * @param e editor
     * @param text text
     * @param sketch sketch
     */
    public void message(Editor e, String text, Sketch sketch){
        this.e = e;
        this.text = text;
        this.sketch = sketch;
    }

    /**
     * Updates the shapes
     * @param msg   message input
     * @param sketch    sketch
     * @return  sketch id
     */
    public int update(String msg, Sketch sketch){
        Sketch temp;
        message = msg.split(" ");

        switch (message[0]) {
            case "draw" -> {
                if (message[1].equals("polyline")) {
                    String[] points = message[2].split(",");
                    //System.out.println(Arrays.toString(points));
                    curr = new Polyline(Integer.parseInt(points[0]), Integer.parseInt(points[1]), new Color(Integer.parseInt(message[3])));

                    for (int i = 2; i < points.length-2; i = i + 2) {
                        ((Polyline) curr).setEnd(Integer.parseInt(points[i]),Integer.parseInt(points[i + 1]));
                    }
                }
                if (message[1].equals("ellipse")) {
                    curr = new Ellipse(Integer.parseInt(message[2]), Integer.parseInt(message[3]), Integer.parseInt(message[4]),
                            Integer.parseInt(message[5]), new Color(Integer.parseInt(message[6])));
                }
                if (message[1].equals("rectangle")) {
                    curr = new Rectangle(Integer.parseInt(message[2]), Integer.parseInt(message[3]), Integer.parseInt(message[4]),
                            Integer.parseInt(message[5]), new Color(Integer.parseInt(message[6])));
                }
                if (message[1].equals("segment")) {
                    curr = new Segment(Integer.parseInt(message[2]), Integer.parseInt(message[3]), Integer.parseInt(message[4]),
                            Integer.parseInt(message[5]), new Color(Integer.parseInt(message[6])));
                }
                sketch.addSketch(curr);
            }
            case "move" -> {
                int id = Integer.parseInt(message[1]);
                curr = sketch.getShape(id);
                if (curr != null) {
                    curr.moveBy(Integer.parseInt(message[4]), Integer.parseInt(message[5]));
                }
            }
            case "delete" -> {
                int id = Integer.parseInt(message[1]);
                curr = sketch.getShape(id);
                sketch.remove(id);
            }
            case "recolor" -> {
                int id = Integer.parseInt(message[1]);
                curr = sketch.getShape(id);
                curr.setColor(new Color(Integer.parseInt(message[2])));
            }
            case "update" -> {
                if (message[2].equals("polyline")) {
                    String[] points = message[3].split(",");
                    //System.out.println(points);
                    curr = new Polyline(Integer.parseInt(points[0]), Integer.parseInt(points[1]), new Color(Integer.parseInt(message[4])));

                    for (int i = 2; i < points.length-2; i = i + 2) {
                        ((Polyline) curr).setEnd(Integer.parseInt(points[i]),Integer.parseInt(points[i + 1]));
                    }
                    sketch.addSketch(Integer.parseInt(message[5]), curr);
                }
                if (message[2].equals("ellipse")) {
                    curr = new Ellipse(Integer.parseInt(message[3]), Integer.parseInt(message[4]), Integer.parseInt(message[5]),
                            Integer.parseInt(message[6]), new Color(Integer.parseInt(message[7])));
                    sketch.addSketch(Integer.parseInt(message[8]), curr);
                }
                if (message[2].equals("rectangle")) {
                    curr = new Rectangle(Integer.parseInt(message[3]), Integer.parseInt(message[4]), Integer.parseInt(message[5]),
                            Integer.parseInt(message[6]), new Color(Integer.parseInt(message[7])));
                    sketch.addSketch(Integer.parseInt(message[8]), curr);
                }
                if (message[2].equals("segment")) {
                    curr = new Segment(Integer.parseInt(message[3]), Integer.parseInt(message[4]), Integer.parseInt(message[5]),
                            Integer.parseInt(message[6]), new Color(Integer.parseInt(message[7])));
                    sketch.addSketch(Integer.parseInt(message[8]), curr);
                }
            }
            case "start" -> {
                temp = new Sketch();
                sketch = temp;
            }
        }
        return sketch.getId(curr); //return shape ID
    }
}
