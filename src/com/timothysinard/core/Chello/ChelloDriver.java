package com.timothysinard.core.Chello;
import java.awt.Dimension;

import javax.swing.SwingUtilities;

import com.timothysinard.gui.ChelloGUI;

public class ChelloDriver {
	public static void main(String[] args) {
		// Run in gui thread
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Create GUI game
				new ChelloGUI(new Dimension(900, 600), "Chello Board");
			}
		});
	}
}
