package net.simplyrin.discordrpgui;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.md_5.bungee.config.Configuration;
import net.simplyrin.discordrpgui.utils.Config;
import net.simplyrin.rinstream.RinStream;

/**
 * Created by SimplyRin on 2018/11/18.
 *
 * Copyright (c) 2018 SimplyRin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		new RinStream();

		File file = new File("config.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Configuration config = Config.getConfig(file);

			config.set("Application-ID", null);
			config.set("Details", null);
			config.set("State", null);
			config.set("Small-Image-Key", null);
			config.set("Small-Image-Text", null);
			config.set("Large-Image-Key", null);
			config.set("Large-Image-Text", null);

			Config.saveConfig(config, file);
		}

		Parent parent;
		try {
			parent = FXMLLoader.load(this.getClass().getResource("/main.fxml"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		try {
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icon.png")));
		} catch (Exception e) {
		}

		stage.setTitle("DiscordRP GUI");
		stage.sizeToScene();
		stage.setResizable(false);
		stage.setScene(new Scene(parent, 336, 219));
		stage.setOnCloseRequest(event -> {
			System.exit(0);
		});
		stage.show();
	}

}
