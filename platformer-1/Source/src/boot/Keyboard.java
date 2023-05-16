package boot;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	public static boolean[] pressed = new boolean[200];

	public Keyboard() {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()] = false;

		// one time things
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			Boot.pause();
		if (e.getKeyCode() == KeyEvent.VK_Q)
			Loader.addMap("core");
		if (e.getKeyCode() == KeyEvent.VK_W)
			Loader.resetMap(Boot.getInspector().gridID());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public static boolean isPressed(int i) {
		return pressed[i];
	}

}

/**
 * 
 * 
 * @Override public void keyPressed(KeyEvent E) { int key = E.getKeyCode();
 * 
 *           Thread SPRINT = new Thread(new Runnable() { public void run() {
 *           I.sprint(true); } });
 * 
 *           Thread UP = new Thread(new Runnable() { public void run() {
 *           I.adjustVelocity(0, -1); } }); Thread DOWN = new Thread(new
 *           Runnable() { public void run() { I.adjustVelocity(0, 1); } });
 *           Thread LEFT = new Thread(new Runnable() { public void run() {
 *           I.adjustVelocity(-1, 0); } }); Thread RIGHT = new Thread(new
 *           Runnable() { public void run() { I.adjustVelocity(1, 0); } });
 * 
 *           if (key == KeyEvent.VK_UP) UP.start(); if (key == KeyEvent.VK_DOWN)
 *           DOWN.start(); if (key == KeyEvent.VK_LEFT) LEFT.start(); if (key ==
 *           KeyEvent.VK_RIGHT) RIGHT.start(); if (key == KeyEvent.VK_CONTROL)
 *           SPRINT.start(); }
 * 
 * @Override public void keyReleased(KeyEvent E) { int key = E.getKeyCode();
 * 
 *           Thread SPACE = new Thread(new Runnable() { public void run() { if
 *           (running == -1) running = 0; else running = -1; } });
 * 
 *           Thread SPRINT = new Thread(new Runnable() { public void run() {
 *           I.sprint(false); } });
 * 
 *           Thread VK1 = new Thread(new Runnable() { public void run() { ready
 *           = false; save(); ready = true; } });
 * 
 *           Thread VK2 = new Thread(new Runnable() { public void run() { ready
 *           = false; load(); ready = true; } });
 * 
 *           Thread RESET = new Thread(new Runnable() { public void run() {
 *           ready = false; if (I.gridID() >= 0) { for (int i = 0; i <
 *           Map.getMaps().size(); i++) { if (Map.getMaps().get(i).getID() ==
 *           I.gridID()) { String n = Map.getMaps().get(i).getFileName();
 *           Map.getMaps().set(i, new Map(n)); } } running = -1; } ready = true;
 *           } });
 * 
 *           Thread UP = new Thread(new Runnable() { public void run() {
 *           I.setYVelocity(0); } }); Thread DOWN = new Thread(new Runnable() {
 *           public void run() { I.setYVelocity(0); } }); Thread LEFT = new
 *           Thread(new Runnable() { public void run() { I.setXVelocity(0); }
 *           }); Thread RIGHT = new Thread(new Runnable() { public void run() {
 *           I.setXVelocity(0); } });
 * 
 *           Thread ADDMAP = new Thread(new Runnable() { public void run() {
 *           Map.getMaps().add(new Map("src//testSpeed.txt")); } }); Thread
 *           GHOST = new Thread(new Runnable() { public void run() {
 *           I.setGhost(!I.ghost()); } });
 * 
 *           if (key == KeyEvent.VK_SPACE) SPACE.start(); if (key ==
 *           KeyEvent.VK_1) VK1.start(); if (key == KeyEvent.VK_2) VK2.start();
 *           if (key == KeyEvent.VK_9) ADDMAP.start(); if (key == KeyEvent.VK_0)
 *           RESET.start(); if (key == KeyEvent.VK_UP) UP.start(); if (key ==
 *           KeyEvent.VK_DOWN) DOWN.start(); if (key == KeyEvent.VK_LEFT)
 *           LEFT.start(); if (key == KeyEvent.VK_RIGHT) RIGHT.start(); if (key
 *           == KeyEvent.VK_CONTROL) SPRINT.start(); if (key == KeyEvent.VK_G)
 *           GHOST.start(); }
 * 
 * @Override public void keyTyped(KeyEvent e) {
 * @SuppressWarnings(value = { "unused" }) int key = e.getKeyCode(); }
 * 
 * 
 **/