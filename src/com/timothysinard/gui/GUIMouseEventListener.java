package com.timothysinard.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.timothysinard.utils.Timer;

public class GUIMouseEventListener
		implements
			MouseListener,
			MouseMotionListener,
			MouseWheelListener {

	private final ChelloGUI gui;
	private final Timer timer;

	public GUIMouseEventListener(ChelloGUI gui, DrawPanel panel) {
		this.gui = gui;
		this.timer = new Timer();
		this.timer.startTimer();
		panel.addMouseMotionListener(this);
		panel.addMouseListener(this);
		panel.addMouseWheelListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.gui.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.gui.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.gui.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.gui.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.updateMousePosition(e);
		this.gui.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {

		this.gui.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.updateMousePosition(arg0);
		this.gui.repaint();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	private void updateMousePosition(MouseEvent e) {

	}
}
