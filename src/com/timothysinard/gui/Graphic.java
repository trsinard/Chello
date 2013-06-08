package com.timothysinard.gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Graphic class used to store data of a Drawable, which draws itself.
 */
public class Graphic implements Drawable {

	// Image to be drawn
	private BufferedImage image;
	// Graphic ID
	public String id;
	// Screen x-position, y-position
	private int x_pos;
	private int y_pos;
	// Grid x-position, y-position. Block of a 2-dimensional matrix grid.
	private int x_grid;
	private int y_grid;
	// Rotation of image
	private int rotation;
	// Layer position
	private int layerZ;
	// Original size of image
	private Dimension originalSize;
	// Scaled size of image
	private Dimension size;
	// Repeat image to fill screen
	private boolean repeatFill;

	public Graphic(String id, BufferedImage image, int x_pos, int y_pos,
			Dimension size, int layerZ, boolean fill) {
		this.image = image;
		this.id = id;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.size = this.originalSize = size;
		this.layerZ = layerZ;
		this.x_grid = 1;
		this.y_grid = 1;
		this.rotation = 0;
		this.repeatFill = fill;
	}

	@Override
	public void draw(Graphics2D g, ScreenData sd) {

		int w = (int) Math.round(this.image.getWidth() * sd.getScaleRatio());
		int h = (int) Math.round(this.image.getHeight() * sd.getScaleRatio());
		if (this.repeatFill) {
			for (int x = 0; x * w < sd.getWidth(); x++) {
				for (int y = 0; y * h < sd.getHeight(); y++) {
					AffineTransform at = new AffineTransform();
					at.translate((this.x_pos + x * w) * sd.getScaleRatio(),
							(this.y_pos + y * h) * sd.getScaleRatio());

					at.rotate(Math.toRadians(this.rotation), w / 2, h / 2);
					at.scale(sd.getScaleRatio(), sd.getScaleRatio());
					g.drawImage(this.image, at, null);
				}
			}
		} else {
			AffineTransform at = new AffineTransform();
			at.translate(this.x_pos * sd.getScaleRatio(),
					this.y_pos * sd.getScaleRatio());
			at.rotate(Math.toRadians(this.rotation), w / 2, h / 2);
			at.scale(sd.getScaleRatio(), sd.getScaleRatio());
			g.drawImage(this.image, at, null);
		}
		// g.drawImage(image, x_pos, y_pos, w, h, null);
	}

	/**
	 * Gets grid position x/column.
	 * 
	 * @return
	 */
	public int getGridPosX() {
		return this.x_grid;
	}

	/**
	 * Gets grid position y/row.
	 * 
	 * @return
	 */
	public int getGridPosY() {
		return this.y_grid;
	}

	@Override
	public String getID() {
		return this.id;
	}

	/**
	 * Gets image reference.
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		return this.image;
	}

	/**
	 * Gets image's original size.
	 * 
	 * @return
	 */
	public Dimension getOriginalSize() {
		return this.originalSize;
	}

	/**
	 * Gets screen x-position.
	 * 
	 * @return
	 */
	public int getPosX() {
		return this.x_pos;
	}

	/**
	 * Gets screen y-position.
	 * 
	 * @return
	 */
	public int getPosY() {
		return this.y_pos;
	}

	/**
	 * Get and return if the graphic is repeated to fill the canvas.
	 * 
	 * @return
	 */
	public boolean getRepeat() {
		return this.repeatFill;
	}

	/**
	 * Gets image rotation.
	 * 
	 * @return
	 */
	public int getRotation() {
		return this.rotation;
	}

	/**
	 * Gets current size of image.
	 * 
	 * @return
	 */
	public Dimension getSize() {
		return this.size;
	}

	@Override
	public int getZ() {
		return this.layerZ;
	}

	@Override
	public void mouseClickPosition(int x, int y) {

	}

	/**
	 * Sets the grid position, in terms of 2-dimensional matrix
	 * 
	 * @param gx
	 * @param gy
	 */
	public void setGridPos(int gx, int gy) {
		this.x_grid = gx;
		this.y_grid = gy;
	}

	/**
	 * Sets Graphic's image to given buffered image.
	 * 
	 * @param image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * Sets graphic's screen position on x-axis
	 * 
	 * @param x_pos
	 */
	public void setPosX(int x_pos) {
		this.x_pos = x_pos;
	}

	/**
	 * Sets graphic's screen position on y-axis
	 * 
	 * @param y_pos
	 */
	public void setPosY(int y_pos) {
		this.y_pos = y_pos;
	}

	/**
	 * Set if the graphic will repeat to fill canvas.
	 * 
	 * @param bool
	 */
	public void setRepeat(boolean bool) {
		this.repeatFill = bool;
	}

	/**
	 * Sets image rotation
	 * 
	 * @param rot
	 */
	public void setRotation(int rot) {
		this.rotation = rot;
	}

	/**
	 * Sets new size of image
	 * 
	 * @param size
	 */
	public void setSize(Dimension size) {
		this.size = size;
	}

	/**
	 * Sets z-layer position
	 * 
	 * @param z
	 */
	public void setZ(int z) {
		this.layerZ = z;
	}

}
