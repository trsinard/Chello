package com.timothysinard.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;

import com.timothysinard.utils.ImageLoader;

/**
 * A drawable class that works as a canvas for a collection of images. Stores a
 * collection of drawables identified by a string-key, and then works as the
 * master draw-call, calling the draw functions for each drawable in the
 * collection.
 */
public class GraphicCanvas implements Drawable {
	private final ImageLoader il = ImageLoader.getImageLoader();
	// Collection storing the drawable items
	private final ArrayList<Drawable> drawItems;
	// Stores the most recent screen data/properties.
	private ScreenData recentScreenData;
	// Z-layer
	private final int layerZ;
	// Drawable ID
	private final String id;

	public GraphicCanvas(String id) {
		this.id = id;
		this.drawItems = new ArrayList<Drawable>();
		this.recentScreenData = null;
		this.layerZ = 0;
	}

	/**
	 * Adds a drawable to collection of items being drawn.
	 * 
	 * <b>Preconditions:</b> Requires String identification and reference to
	 * drawable item. <b>Postconditions:</b> Stores given item in collection,
	 * replacing any item with same key. <b>Throws:</b> None
	 */
	public void addDrawable(String key, Drawable item) {
		for (Drawable d : this.drawItems) {
			if (d.getID().equals(item.getID())) {
				this.drawItems.remove(d);
				this.drawItems.add(item);
				return;
			}
		}
		this.drawItems.add(item);
		Collections.sort(this.drawItems,
				new GraphicsLayerComparator<Drawable>());
	}

	@Override
	public void draw(Graphics2D g, ScreenData sd) {
		this.recentScreenData = sd;
		g.setColor(Color.WHITE);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		for (Drawable d : this.drawItems) {
			d.draw(g, sd);
		}
	}

	/**
	 * Get and return a drawable item from collection
	 * 
	 * <b>Preconditions:</b> Requires String key identification
	 * <b>Postconditions:</b> Get and return the drawable item that matches key.
	 * Returns null if not found. <b>Throws:<b> None
	 * 
	 * @param key
	 * @return
	 */
	public Drawable getDrawable(String key) {
		for (Drawable d : this.drawItems) {
			if (d.getID().equals(key)) {
				return d;
			}
		}
		return null;
	}

	@Override
	public String getID() {
		return this.id;
	}

	/**
	 * Gets and returns the image-loader.
	 * 
	 * <b>Preconditions:</b> None <b>Postconditions:</b> Returns reference to
	 * image-loader. <b>Throws:</b> None
	 */
	public ImageLoader getImageLoader() {
		return this.il;
	}

	/**
	 * Get and return reference to the recent screen-data object.
	 * 
	 */
	public ScreenData getRecentScreenData() {
		return this.recentScreenData;
	}

	@Override
	public int getZ() {
		return this.layerZ;
	}

	@Override
	public void mouseClickPosition(int x, int y) {
		// TODO Auto-generated method stub

	}

	/**
	 * Removes drawable matching given Drawable item from draw-collection. If
	 * found, removed and returns true. If not found, returns false.
	 * 
	 * @param d
	 * @return
	 */
	public boolean removeDrawable(Drawable d) {
		return this.drawItems.remove(d);
	}

	/**
	 * Remove drawable matching given ID from draw-collection. If found, removed
	 * and returns true. If not found, returns false.
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeDrawable(String id) {
		for (Drawable d : this.drawItems) {
			if (d.getID().equals(id)) {
				this.drawItems.remove(d);
				return true;
			}
		}
		return false;
	}

}
