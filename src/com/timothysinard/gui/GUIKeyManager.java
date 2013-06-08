package com.timothysinard.gui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.timothysinard.utils.ThemeManager;

public class GUIKeyManager implements KeyListener {

	private final ChelloGUI gui;
	private char[] alpha = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z'};
	private char[] num_spec = new char[]{'1', '2', '3', '4', '5', '6', '7',
			'8', '9', '0', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};
	private char[] spec = new char[]{'`', '-', '=', '[', ']', '\\', ';', '\'',
			',', '.', '/', '~', '_', '+', '{', '}', '|', ':', '"', '<', '>',
			'?'};

	private ArrayList<String> eventIDStack;

	private final int FONT_SIZE = 35;
	private final int HORIZONTAL_SPACE = 25;
	private final int VERTICAL_SPACE = 35;
	private int line_x;
	private int line_y;

	public GUIKeyManager(ChelloGUI gui, DrawPanel panel) {
		this.gui = gui;
		this.line_x = 0;
		this.line_y = 0;
		this.eventIDStack = new ArrayList<String>();
		gui.addKeyListener(this);
	}

	private void drawChar(char key) {
		BufferedImage crop = this.getChar(key);
		if (crop != null) {

			String id = key + ":" + System.nanoTime();
			this.eventIDStack.add(id);
			this.gui.getBoardCanvas().addDrawable(
					id,
					new Graphic(id, crop, this.line_x * this.HORIZONTAL_SPACE,
							this.line_y * this.VERTICAL_SPACE, new Dimension(
									crop.getWidth(), crop.getHeight()), 0,
							false));
			this.line_x++;
			if ((this.line_x + 3) * this.HORIZONTAL_SPACE >= this.gui
					.getBoardCanvas().getRecentScreenData().getWidth()) {

				this.eventIDStack.add("ENTER" + this.line_x);
				this.line_x = 0;
				this.line_y++;
			}
		}

	}

	private BufferedImage getChar(char key) {
		BufferedImage image = ThemeManager.getThemeManager().getImage("alpha");
		BufferedImage crop = null;
		for (int i = 0; i < this.alpha.length && crop == null; i++) {
			if (key == this.alpha[i]) {
				crop = image.getSubimage(this.FONT_SIZE * i, 0, this.FONT_SIZE,
						this.FONT_SIZE);
			} else if (key == Character.toLowerCase(this.alpha[i])) {
				crop = image.getSubimage(this.FONT_SIZE * i, this.FONT_SIZE,
						this.FONT_SIZE, this.FONT_SIZE);
			}
		}
		for (int i = 0; i < this.num_spec.length && crop == null; i++) {
			if (key == this.num_spec[i]) {
				crop = image.getSubimage(this.FONT_SIZE * i,
						this.FONT_SIZE * 2, this.FONT_SIZE, this.FONT_SIZE);
			}
		}
		for (int i = 0; i < this.spec.length && crop == null; i++) {
			if (key == this.spec[i]) {
				crop = image.getSubimage(this.FONT_SIZE * i,
						this.FONT_SIZE * 3, this.FONT_SIZE, this.FONT_SIZE);
			}
		}

		return crop;
	}
	@Override
	public void keyPressed(KeyEvent ke) {

		if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			this.eventIDStack.add("SPACE");
			this.line_x++;
		} else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			this.eventIDStack.add("ENTER" + this.line_x);
			this.line_x = 0;
			this.line_y++;
		} else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE
				&& this.eventIDStack.size() > 0) {

			if (this.eventIDStack.get(this.eventIDStack.size() - 1).equals(
					"SPACE")) {
				if (this.line_x > 0) {
					this.line_x--;
				}
				this.eventIDStack.remove(this.eventIDStack.size() - 1);
			} else if (!(this.eventIDStack.get(this.eventIDStack.size() - 1)
					.substring(0, 5).equals("ENTER"))) {
				this.gui.getBoardCanvas().removeDrawable(
						this.eventIDStack.get(this.eventIDStack.size() - 1));
				if (this.line_x > 0) {
					this.line_x--;
				}
				this.eventIDStack.remove(this.eventIDStack.size() - 1);
			}

			if (this.eventIDStack.size() > 0) {
				if (this.eventIDStack.get(this.eventIDStack.size() - 1)
						.substring(0, 5).equals("ENTER")) {
					if (this.line_y > 0) {
						this.line_y--;
					}
					this.line_x = Integer.parseInt(this.eventIDStack.get(
							this.eventIDStack.size() - 1).substring(5));
					this.eventIDStack.remove(this.eventIDStack.size() - 1);
				}
			}

		} else {

			this.drawChar(ke.getKeyChar());
		}

		this.gui.repaint();

	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent ke) {

	}
}
