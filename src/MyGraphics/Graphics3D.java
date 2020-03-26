package MyGraphics;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphics3D {
	Graphics2D painter;

	public Graphics3D(Graphics2D painter) {
		this.painter = painter;
	}
	
	public void fillCube(int x, int y, int size) {
		if(painter == null) return;
		painter.fill(new Rectangle(x, y, size, size));

		//a
		int startX = x - size;
		int startY = y + size;
		for (int i = 0; i < size; i++) {
			painter.drawLine(startX, startY + i, x, y + i);
		}
		//b
		//startX = suca.x - getsz();
		//startY = suca.y + getsz();
		for (int i = 0; i < size; i++) {
			painter.drawLine(startX + i, startY, x + i, y);
		}
		//c
		startX = (x + size) - size;
		//startY = suca.y + getsz();
		for (int i = 0; i < size; i++) {
			painter.drawLine(startX, startY + i, x + size, y + i);
		}
		//d
		startX = x - size;
		startY = (y - size) + size;
		for (int i = 0; i < size; i++) {
			painter.drawLine(startX + i, startY, x + i, y + size);
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		Graphics3D g = new Graphics3D((Graphics2D) p.getGraphics());
		//p.setPreferredSize(arg0);
		f.pack();
		f.add(p);
		f.setVisible(true);
		while(p.getGraphics() == null);
		g.fillCube(10, 10, 5);
	}
}