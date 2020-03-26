import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUI extends JFrame implements WindowStateListener {
	Suca suca;
	JTextField sizex, sizey, bx, by, tx, ty, cx, cy;
	JLabel xlbl, ylbl, lbx, lby, ltx, lty, lcx, lcy;
	JButton calc;
	//JTabbedPane 
	final static int x = 600, y = 500;
	static GUI g;

	public GUI() {
		suca = new Suca();
		suca.setBackground(Color.BLACK);
		suca.setPreferredSize(new Dimension((int) (x * 0.9), (int) (y * 0.9)));
		xlbl = new JLabel("אורך הסוכה:");
		ylbl = new JLabel("רוחב הסוכה:");
		sizex = new JTextField(3);
		sizey = new JTextField(3);
		Suca.sizex = sizex;
		Suca.sizey = sizey;
		// ------------------
		lbx = new JLabel("אורך המיטה:");
		lby = new JLabel("רוחב המיטה:");
		bx = new JTextField(3);
		by = new JTextField(3);
		Suca.bx = bx;
		Suca.by = by;
		// ------------------
		ltx = new JLabel("אורך השולחן:");
		lty = new JLabel("רוחב השולחן:");
		tx = new JTextField(3);
		ty = new JTextField(3);
		Suca.tx = tx;
		Suca.ty = ty;
		// ------------------
		lcx = new JLabel("אורך הכיסא:");
		lcy = new JLabel("רוחב הכיסא:");
		cx = new JTextField(3);
		cy = new JTextField(3);
		Suca.cx = cx;
		Suca.cy = cy;
		// ------------------
		calc = new JButton("צייר סוכה");
		calc.addActionListener(suca);

		add(xlbl);
		add(sizex);
		add(ylbl);
		add(sizey);
		add(lbx);
		add(bx);
		add(lby);
		add(by);
		add(ltx);
		add(tx);
		add(lty);
		add(ty);
		add(lcx);
		add(cx);
		add(lcy);
		add(cy);
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
		suca.addMouseListener(suca);
		suca.addMouseMotionListener(suca);
	}

	public static void main(String[] args) {
		g = new GUI();
	}

	@Override
	public void windowStateChanged(WindowEvent state) {
		suca.setPreferredSize(new Dimension((int) (this.getWidth() * 0.9), (int) (this.getHeight() * 0.9)));
		suca.repaint();
	}
}

@SuppressWarnings("serial")
class Suca extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	public static JTextField cy;
	public static JTextField cx;
	public static JTextField ty;
	public static JTextField tx;
	public static JTextField by;
	public static JTextField bx;
	public static JTextField sizey;
	public static JTextField sizex;
	Rectangle suca, bed, table;
	int lsx, lsy, lbx, lby, ltx, lty;
	boolean sc = false, bc = false, tc = false;

	@Override
	public void actionPerformed(ActionEvent click) {
		int sucaStartX = (getWidth() - (getsx() * 10)) / 2;
		int sucaStartY = (getHeight() - (getsy() * 10)) / 2;
		int sucaEndX = getsx() * 10, sucaEndY = getsy() * 10;
		suca = new Rectangle(sucaStartX, sucaStartY, sucaEndX, sucaEndY);
		bed = new Rectangle(suca.x, suca.y, getbx() * 10, getby() * 10);
		//g.fillRect();
		paint(getGraphics());
	}

	public void paint(Graphics g) {
		setPreferredSize(new Dimension((int) (GUI.g.getWidth() * 0.9), (int) (GUI.g.getHeight() * 0.9)));
		// Graphics g = getGraphics();
		if (g == null)
			return;
		Graphics2D g2 = (Graphics2D) g;
		if(suca == null) {
			GUI.g.setMinimumSize(new Dimension(getsx() * 15, getsy() * 18));
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, getWidth(), getHeight());
			return;
		}
		// g2.getFont().
		// Font f = g.getFont();
		GUI.g.setMinimumSize(new Dimension(getsx() * 15, getsy() * 18));
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.WHITE);
		lsx = suca.x;
		lsy = suca.y;
		suca.x = (getWidth() - (getsx() * 10)) / 2;
		suca.y = (getHeight() - (getsy() * 10)) / 2;
		if (getsx() > 0)
			for (int i = suca.x; i <= suca.x + suca.width; i = i + (getsx() / 3 * 2)) {
				g2.drawString((i - suca.x) / 100.0 + "", i, suca.y - g2.getFont().getSize());
				g2.drawString((i - suca.x) / 100.0 + "", i, suca.y + suca.height + g2.getFont().getSize() + 2);
			}
		if (getsy() > 0)
			for (int i = suca.y; i <= suca.y + suca.height; i = i + (getsy() / 3 * 2)) {
				g2.drawString((i - suca.y) / 100.0 + "", suca.x - g2.getFont().getSize() * 2, i);
				g2.drawString((i - suca.y) / 100.0 + "", suca.x + suca.width + g2.getFont().getSize(), i);
			}
		g2.fill(suca);
		// g.fillRect(suca.x, suca.y, suca.width, suca.height);
		g.setColor(Color.DARK_GRAY);
		for (int i = suca.x; i < suca.x + suca.width; i = i + 100) {
			g.fillRect(i, suca.y, 1, suca.height);
		}

		for (int i = suca.y; i < suca.y + suca.height; i = i + 100) {
			g.fillRect(suca.x, i, suca.width, 1);
		}
		g2.setColor(Color.BLUE);
		g2.setFont(Font.decode("Arial"));
		
		bed.x = lbx - (bed.width / 2) + (suca.x - lsx);
		bed.y = lby - (bed.height / 2) + (suca.y - lsy);
		//bed.x = 
		g2.fill(bed);
		//g2.
		//
		//bed = new Rectangle(suca.x, suca.y, getbx() * 10, getby() * 10);
		//g.fillRect(suca.x, suca.y, getbx() * 10, getby() * 10);
		g2.setColor(Color.WHITE);
		//g2.drawString("מיטה", suca.x + (getbx() * 10) / 2, suca.y + (getby() * 10) / 2 + g2.getFont().getSize());
		g2.drawString("מיטה", bed.x + (bed.width / 2) - g2.getFont().getSize(), bed.y + (bed.height / 2));
		
		g2.setColor(Color.RED);
		g2.fillRect(suca.x, suca.y + getby() * 10, gettx() * 10, getty() * 10);
		g2.setColor(Color.WHITE);
		g2.drawString("שולחן", suca.x + (gettx() * 10) / 2,
				suca.y + (getby() * 10) + (getty() * 10) / 2 + g2.getFont().getSize());
		/*
		 * g.setColor(Color.DARK_GRAY); for (int i = sucaStartX; i < sucaStartX
		 * + sucaEndX; i = i + 100) { g.fillRect(i, sucaStartY, 1, sucaEndY); }
		 * for (int i = sucaStartY; i < sucaStartY + sucaEndY; i = i + 100) {
		 * g.fillRect(sucaStartX, i, sucaEndX, 1); } //
		 */
	}

	int getsx() {
		try {
			return (int) Double.parseDouble(sizex.getText());
		} catch (Exception e) {
			return 0;
		}
	}

	int getsy() {
		try {
			return (int) Double.parseDouble(sizey.getText());
		} catch (Exception e) {
			return 0;
		}
	}

	int getbx() {
		try {
			return (int) Double.parseDouble(bx.getText());
		} catch (Exception e) {
			return 0;
		}
	}

	int getby() {
		try {
			return (int) Double.parseDouble(by.getText());
		} catch (Exception e) {
			return 0;
		}
	}

	int gettx() {
		try {
			return (int) Double.parseDouble(tx.getText());
		} catch (Exception e) {
			return 0;
		}
	}

	int getty() {
		try {
			return (int) Double.parseDouble(ty.getText());
		} catch (Exception e) {
			return 0;
		}
	}

	int getcx() {
		return (int) Double.parseDouble(cx.getText());
	}

	int getcy() {
		return (int) Double.parseDouble(cy.getText());
	}

	@Override
	public void mouseDragged(MouseEvent drag) {
		/*
		if(sc) {
			
			//lsx = suca.x;
			//lsy = suca.y;
			
			//sc = true;
			suca.x = drag.getX();
			suca.y = drag.getY();
			paint(getGraphics());
		}
		*/
		if(bc) {
			/*
			bed.x = drag.getX() - (bed.width / 2);
			bed.y = drag.getY() - (bed.height / 2);
			*/
			lbx = drag.getX();
			lby = drag.getY();
			paint(getGraphics());
		}
	}

	@Override
	public void mousePressed(MouseEvent press) {
		/*
		if(suca.contains(press.getX(), press.getY())) {
			//JOptionPane.showMessageDialog(null, "Suca Pressed!");
			//*
			//lsx = suca.x;
			//lsy = suca.y;
			sc = true;
			paint(getGraphics());
			//*
			suca.x = press.getX();
			suca.y = press.getY();
		}
		*/
		if(bed.contains(press.getPoint())) {
			bc = true;
			//lsx = suca.x;
			//lsy = suca.y;
			//paint(getGraphics());
		}
	}

	@Override
	public void mouseReleased(MouseEvent rls) {
		/*
		if(sc) {
			sc = false;
		}
		*/
		if(bc) {
			bc = false;
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(bed.contains(e.getPoint()) && e.getButton() == MouseEvent.BUTTON3) {
			lbx = bed.width;
			lby = bed.height;
			bed.height = lbx;
			bed.width = lby;
			paint(getGraphics());
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
}