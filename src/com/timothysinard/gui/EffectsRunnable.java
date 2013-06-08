package com.timothysinard.gui;

import com.timothysinard.utils.Random;
import com.timothysinard.utils.Timer;

/**
 * Runnable thread dedicated to special effects.
 * 
 */
public class EffectsRunnable implements Runnable {

	// Reference to main GUI class
	private final ChelloGUI gui;
	// Reference to Timer
	private final Timer timer;
	// Constant refresh rate
	private final double REFRESH_RATE = 0.0015;

	public EffectsRunnable(ChelloGUI gui) {
		this.gui = gui;
		this.timer = new Timer();

	}

	@Override
	public void run() {
		timer.startTimer();
		adjustEffects(true);
		while (true) {
			if (timer.getElapsedTime() * 1.0e-9 > REFRESH_RATE) {
				adjustEffects(false);
				timer.resetTimer();
				gui.repaint();
			}
		}

	}

	/**
	 * Convenience method which calls all effects to activate. Received boolean
	 * param indicates if it is the first call or not.
	 * 
	 * @param first
	 */
	private void adjustEffects(boolean first) {

		
	}
}
