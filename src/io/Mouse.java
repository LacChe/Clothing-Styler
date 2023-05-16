package io;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import render.Camera;
import world.World;

public class Mouse implements MouseListener {

	private int x, y;

	public Mouse() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		if (e.getButton() == 1 || e.getButton() == 3) {
			int pX = World.world().player().x() - Camera.camera().offX();
			int pY = World.world().player().y() - Camera.camera().offY();
			int pW = World.world().player().w();
			int pH = World.world().player().h();
			float radians = (float) Math.atan2((double) (y - pY - pH / 2), (double) (x - pX - pW / 2));
			if (e.getButton() == 1) World.world().addSpray((int) (radians * 180 / Math.PI));
			if (e.getButton() == 3) World.world().addStink((int) (radians * 180 / Math.PI));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

}
