package com.timothysinard.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import com.timothysinard.core.Chello.Chello;
import com.timothysinard.utils.ThemeManager;

public class ChelloGUI extends Frame
		implements
			ActionListener,
			ItemListener,
			ComponentListener {

	private static final long serialVersionUID = 1L;

	// The main container to hold the parts of the GUI
	private final Container GUIContainer;
	// Main display panel that hows the board
	private final DrawPanel boardPanel;
	// Main canvas for the board panel
	private final GraphicCanvas boardCanvas;

	public ChelloGUI(Dimension dimension, String title) {
		super(dimension, title);

		// Create main panel to be drawn on
		this.boardPanel = new DrawPanel();
		this.boardCanvas = new GraphicCanvas("boardCanvas");
		this.boardPanel.setDrawable(this.boardCanvas);

		// Prepare listener
		this.addComponentListener(this);

		this.GUIContainer = this.getContentPane();
		this.GUIContainer.add(this.boardPanel, BorderLayout.CENTER);

		this.buildBoard();

		this.setVisible(true);
		this.setLocation(50, 50);

		new GUIMouseEventListener(this, this.boardPanel);
		new GUIKeyManager(this, this.boardPanel);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void boardChange(Chello state) {
		// TODO Auto-generated method stub

	}

	private void buildBoard() {
		BufferedImage image = ThemeManager.getThemeManager().getImage("board");
		// Z view level is 0

		this.boardCanvas.addDrawable("board", new Graphic("board", image, 0, 0,
				new Dimension(image.getWidth(), image.getHeight()), 0, true));

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public GraphicCanvas getBoardCanvas() {
		return this.boardCanvas;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}
}
