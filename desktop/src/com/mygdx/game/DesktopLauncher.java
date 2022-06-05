package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.dinogame.config.Config;
import com.dinogame.controller.Controller;

public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Google Dino Game");
		config.setWindowedMode(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
		config.useVsync(true);
		new Lwjgl3Application(new Controller(), config);

	}
}