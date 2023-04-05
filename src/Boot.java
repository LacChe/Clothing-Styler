import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Boot extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private static Image offScreenBuffer;
	private static Graphics offScreenGraphics;
	private static Boot b;
	private static boolean ready = false;

	public static void main(String[] args) {
		JFrame window = new JFrame("Frame");
		window.setBounds(40, 20, 1280, 720);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		b = new Boot();
		window.getContentPane().add(b);
		window.setBackground(Color.BLACK);
		window.setVisible(true);
		b.init();
		window.addMouseListener(b);
	}

	public void init() {
		offScreenBuffer = createImage(getWidth(), getHeight());
		offScreenGraphics = offScreenBuffer.getGraphics();
		initSession();
	}

	public void initSession() {
		offScreenGraphics.clearRect(0, 0, 1280, 720);
		repaint();

		Reader.Read();

	}

	public void paint(Graphics g) {
		if (ready) {
			Artist.draw();

			g.drawImage(offScreenBuffer, 0, 0, this);
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int X = e.getX() - 3;
		int Y = e.getY() - 32;
		System.out.println(X + " " + Y);
		Button.isClicked(X, Y);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	public static Boot getBoot() {
		return b;
	}

	public static Graphics getOffScreenGraphics() {
		return offScreenGraphics;
	}

	public static void setReady(boolean b) {
		ready = b;
	}

}// end class Boot