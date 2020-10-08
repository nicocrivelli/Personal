package exA;
 
import java.util.ArrayList;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame; 

public class Main {

	public static void main(String[] args) {
		
/*		final GLProfile gp = GLProfile.get(GLProfile.GL2);  
		GLCapabilities cap = new GLCapabilities(gp);  
		  
		final GLCanvas gc = new GLCanvas(cap);  
		Draw bl = new Draw();  
		gc.addGLEventListener(bl);  
		gc.setSize(400, 400);  
		  
		final JFrame frame = new JFrame ("JOGL Line");  
		frame.add(gc);  
		frame.setSize(500,400);  
		frame.setVisible(true);	*/
		
		Reader lector = new Reader();
		ArrayList<String> nodes = lector.readDat("./src/main/resources/stars.dat");
		
		Draw graph = new Draw();
		graph.createGraph(nodes);
		
	}

}
