package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.dinogame.Config;
import com.dinogame.view.DinoGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Google Dino Game");
		config.setWindowedMode(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
		//VSync гарантирует, что графический процессор не отправляет ни одного кадра, пока на экране отображается предыдущий кадр
		config.useVsync(true);
		new Lwjgl3Application(new DinoGame(), config);

	}
}