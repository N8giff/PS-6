import java.io.*;
import java.net.Socket;

/**
 * Handles communication between the server and one client, for SketchServer
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012; revised Winter 2014 to separate SketchServerCommunicator
 */
public class SketchServerCommunicator extends Thread {
	private Socket sock;					// to talk with client
	private BufferedReader in;				// from client
	private PrintWriter out;				// to client
	private SketchServer server;			// handling communication for
	private Message thisMessage = new Message();

	public SketchServerCommunicator(Socket sock, SketchServer server) {
		this.sock = sock;
		this.server = server;
	}

	/**
	 * Sends a message to the client
	 * @param msg
	 */
	public void send(String msg) {
		out.println(msg);
	}
	
	/**
	 * Keeps listening for and handling (your code) messages from the client
	 */
	public void run() {
		try {
			System.out.println("someone connected");
			
			// Communication channel
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);

			// Tell the client the current state of the world
			// TODO: YOUR CODE HERE
			for(int key : server.getSketch().getShapes().keySet()){
				Shape currShape = server.getSketch().getShape(key);
				System.out.println("Updating server...");
				send("draw " + currShape.toString() + " " + server.getSketch().getId(currShape));
			}

			// Keep getting and handling messages from the client
			// TODO: YOUR CODE HERE
			String line;
			while((line = in.readLine()) != null){
				System.out.println("received: " + line);
				int id = thisMessage.update(line, server.getSketch());
				if(line.split(" ")[0].equals("draw")){
					server.broadcast("update " + line + " " + id);
				}
				else{
					server.broadcast(line);
				}
			}

			// Clean up -- note that also remove self from server's list so it doesn't broadcast here
			server.removeCommunicator(this);
			out.close();
			in.close();
			sock.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
