import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Exterior extends JFrame implements WindowStateListener {
	Suca3d suca;
	JTextField sizex, sizey, z;
	JLabel xlbl, ylbl, lz;
	JButton calc;
	// JTabbedPane
	final static int x = 600, y = 500;
	static Exterior g;

	public Exterior() {
		suca = new Suca3d();
		suca.setBackground(Color.BLACK);
		suca.setPreferredSize(new Dimension((int) (x * 0.9), (int) (y * 0.9)));
		xlbl = new JLabel("אורך הסוכה:");
		ylbl = new JLabel("רוחב הסוכה:");
		sizex = new JTextField(3);
		sizey = new JTextField(3);
		Suca3d.sizex = sizex;
		Suca3d.sizey = sizey;
		// ------------------
		lz = new JLabel("גובה הסוכה:");
		z = new JTextField(3);
		Suca3d.z = z;
		// ------------------
		calc = new JButton("צייר סוכה");
		calc.addActionListener(suca);

		add(xlbl);
		add(sizex);
		add(ylbl);
		add(sizey);
		add(lz);
		add(z);
		add(calc);
		add(suca);

		this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.setLayout(new FlowLayout());
		this.setMinimumSize(new Dimension(x, y));
		this.setLocationByPlatform(true);
		this.setTitle("מתכנן הסוכה");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowStateListener(this);
	}

	public static void main(String[] args) {
		g = new Exterior();
	}

	@Override
	public void windowStateChanged(WindowEvent state) {
		suca.setPreferredSize(new Dimension((int) (this.getWidth() * 0.9), (int) (this.getHeight() * 0.9)));
		suca.repaint();
	}
}

@SuppressWarnings("serial")
class Suca3d extends JPanel implements ActionListener {
	public static JTextField z;
	public static JTextField sizey;
	public static JTextField sizex;
	Rectangle suca;

	@Override
	public void actionPerformed(ActionEvent click) {
		int sucaStartX = (getWidth() - (getsx())) / 2;
		int sucaStartY = (getHeight() - (getsy())) / 2;
		int sucaEndX = getsx(), sucaEndY = getsy();
		suca = new Rectangle(sucaStartX, sucaStartY, sucaEndX, sucaEndY);
		paint(getGraphics());
	}

	public void paint(Graphics g) {
		setPreferredSize(new Dimension((int) (Exterior.g.getWidth() * 0.9), (int) (Exterior.g.getHeight() * 0.9)));

		if (g == null)
			return;
		Graphics2D g2 = (Graphics2D) g;

		if (suca == null) {
			Exterior.g.setMinimumSize(new Dimension((int) (getsx() * 0.15), (int) (getsy() * 0.18)));
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, getWidth(), getHeight());
			return;
		}
		//Exterior.g.setMinimumSize(new Dimension((int) (getsx() * 0.15), (int) (getsy() * 0.18)));
		setPreferredSize(new Dimension(suca.width + getsz() * 2, suca.height + getsz() * 2));
		Exterior.g.setMinimumSize(new Dimension(suca.width + getsz() * 2, suca.height + getsz() * 2));
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.WHITE);
		if (getsx() > 0)
			for (int i = suca.x; i <= suca.x + suca.width; i = i + (getsx() / 30 * 2)) {
				g2.drawString((i - suca.x) / 100.0 + "", i, suca.y - g2.getFont().getSize());
				//g2.drawString((i - suca.x) / 100.0 + "", i, suca.y + suca.height + g2.getFont().getSize() + 2);
			}
		if (getsy() > 0)
			for (int i = suca.y; i <= suca.y + suca.height; i = i + (getsy() / 30 * 2)) {
				//g2.drawString((i - suca.y) / 100.0 + "", suca.x - g2.getFont().getSize() * 2, i);
				g2.drawString((i - suca.y) / 100.0 + "", suca.x + suca.width + g2.getFont().getSize(), i);
			}
		g2.setColor(Color.DARK_GRAY);
		g2.fill(suca);
		/*
		g2.setColor(Color.DARK_GRAY);
		for (int i = suca.x; i < suca.x + suca.width; i = i + 100) {
			g2.fillRect(i, suca.y, 1, suca.height);
		}

		for (int i = suca.y; i < suca.y + suca.height; i = i + 100) {
			g2.fillRect(suca.x, i, suca.width, 1);
		}
		*/

		g2.setColor(Color.WHITE);
		//g2.setColor(Color.getColor("brown"));
		//a
		int startX = suca.x - getsz();
		int startY = suca.y + getsz();
		for (int i = 0; i < suca.height; i++) {
			g2.drawLine(startX, startY + i, suca.x, suca.y + i);
		}
		//b
		startX = suca.x - getsz();
		startY = suca.y + getsz();
		for (int i = 0; i < suca.width; i++) {
			g2.drawLine(startX + i, startY, suca.x + i, suca.y);
		}
		//c
		startX = (suca.x + suca.width) - getsz();
		startY = suca.y + getsz();
		for (int i = 0; i < suca.height; i++) {
			g2.drawLine(startX, startY + i, suca.x + suca.width, suca.y + i);
		}
		//schach
		g2.setColor(Color.GREEN);
		startX = suca.x - getsz();
		startY = suca.y + getsz();
		int endX = (suca.x + suca.width) - getsz();
		int endY = suca.y + getsz();
		for (int i = 0; i <= suca.height; i+=15) {
			g2.drawLine(startX, startY + i, endX, endY + i);
			g2.drawLine(startX, startY + i + 1, endX, endY + i + 1);
			g2.drawLine(startX, startY + i + 2, endX, endY + i + 2);
			g2.drawLine(startX, startY + i + 3, endX, endY + i + 3);
			g2.drawLine(startX, startY + i + 4, endX, endY + i + 4);
		}
		//g2.fillRect(startX, startY, suca.width, suca.height);
	}

	int getsx() {
		try {
			return (int) (Double.parseDouble(sizex.getText()) * 100);
		} catch (Exception e) {
			return 0;
		}
	}

	int getsy() {
		try {
			return (int) (Double.parseDouble(sizey.getText()) * 100);
		} catch (Exception e) {
			return 0;
		}
	}

	int getsz() {
		try {
			return (int) (Double.parseDouble(z.getText()) * 100);
		} catch (Exception e) {
			return 0;
		}
	}
}