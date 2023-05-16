package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import menu.Menu;
import render.Camera;
import world.Tile;
import world.World;

public class Keyboard implements KeyListener {

	public static boolean[] keys = new boolean[65536];

	public static boolean W, A, S, D, Shift, ESC;

	public Keyboard() {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

		if (e.getKeyCode() == KeyEvent.VK_W) W = true;
		if (e.getKeyCode() == KeyEvent.VK_A) A = true;
		if (e.getKeyCode() == KeyEvent.VK_S) S = true;
		if (e.getKeyCode() == KeyEvent.VK_D) D = true;
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) Shift = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

		if (e.getKeyCode() == KeyEvent.VK_W) W = false;
		if (e.getKeyCode() == KeyEvent.VK_A) A = false;
		if (e.getKeyCode() == KeyEvent.VK_S) S = false;
		if (e.getKeyCode() == KeyEvent.VK_D) D = false;
		if (Camera.camera().showMenu()) {
			if (e.getKeyCode() == KeyEvent.VK_W) Menu.menu().pressed("W");
			if (e.getKeyCode() == KeyEvent.VK_A) Menu.menu().pressed("A");
			if (e.getKeyCode() == KeyEvent.VK_S) Menu.menu().pressed("S");
			if (e.getKeyCode() == KeyEvent.VK_D) Menu.menu().pressed("D");
		}

		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			Shift = false;
			Menu.menu().goBack();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (!Camera.camera().showMenu()) {
				Camera.camera().showMenu(true);
			} else {
				Menu.menu().goBack();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (Camera.camera().showMenu()) {

			} else if (Camera.camera().showText()) {
				Camera.camera().showText(false);
			} else {
				World.world().player().setActivate();
			}
		}
		// testing
		if (e.getKeyCode() == KeyEvent.VK_ENTER) System.out
				.println(World.world().player().x() / Tile.TILE_S + " " + World.world().player().y() / Tile.TILE_S);
	}

	public void keyTyped(KeyEvent e) {
	}

}