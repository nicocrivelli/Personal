package exA;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.media.opengl.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

public class Draw implements GLEventListener {

	@Override
	public void init(GLAutoDrawable drawable) {

	}

	@Override
	public void dispose(GLAutoDrawable drawable) {

	}

	@Override
	public void display(GLAutoDrawable drawable) {

		final GL2 gl = drawable.getGL().getGL2();
		// Draw H
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2d(-0.8, 0.6);
		gl.glVertex2d(-0.8, -0.6);
		gl.glVertex2d(-0.8, 0.6);
		gl.glVertex2d(-0.4, -0.6);
		gl.glVertex2d(-0.4, 0.6);
		gl.glVertex2d(-0.4, -0.6);
		gl.glEnd();
		// Draw W
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2d(0.4, 0.6);
		gl.glVertex2d(0.4, -0.6);
		gl.glVertex2d(0.4, -0.6);
		gl.glVertex2d(0.8, -0.6);
		gl.glVertex2d(0.4, 0.6);
		gl.glVertex2d(0.8, 0.6);
		gl.glEnd();

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

	}

	public void createGraph(ArrayList<String> nodes) {

		String url = "src/test/resources/grafico.png";
		File imgFile = new File(url);
		try {
			imgFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		DefaultDirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

		int cuentaPares = 0;

		for (int i = 0; i < nodes.size(); i++) {
			g.addVertex(nodes.get(i));
			cuentaPares++;
			if (cuentaPares == 2) {
				g.addEdge(nodes.get(i - 1), nodes.get(i));
				cuentaPares = 0;
			}
		}

		viewGraph(g, url);
	}

	public void viewGraph(DefaultDirectedGraph<String, DefaultEdge> g, String url) {
		JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<String, DefaultEdge>(g);
		mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
		layout.execute(graphAdapter.getDefaultParent());

		BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 0.9, Color.BLACK, true, null);
		File imgFile = new File(url);
		try {
			ImageIO.write(image, "PNG", imgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage img;
		try {
			img = ImageIO.read(imgFile);
			ImageIcon icon = new ImageIcon(img);
			JFrame frame = new JFrame();
			frame.setLayout(new FlowLayout());
			frame.setSize(1000, 725);
			JLabel lbl = new JLabel();
			lbl.setIcon(icon);
			frame.add(lbl);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
