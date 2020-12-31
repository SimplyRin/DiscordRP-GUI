package net.simplyrin.discordrpgui;

import java.io.File;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.md_5.bungee.config.Configuration;
import net.simplyrin.discordrpgui.utils.Config;

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
public class Controller {

	@FXML
	private TextField applicationId;

	@FXML
	private TextField details;
	@FXML
	private TextField state;

	@FXML
	private TextField smallImageKey;
	@FXML
	private TextField smallImageText;

	@FXML
	private TextField largeImageKey;
	@FXML
	private TextField largeImageText;

	@FXML
	private Button connect;
	@FXML
	private Button disconnect;

	private RichPresence.Builder presence;
	private IPCClient ipcClient;

	private Long time = 0L;

	@FXML
	private void initialize()  {
		this.disconnect.setDisable(true);

		File file = new File("config.yml");
		Configuration config = Config.getConfig(file);

		this.applicationId.setText(config.getString("Application-ID"));
		this.details.setText(config.getString("Details"));
		this.state.setText(config.getString("State"));
		this.smallImageKey.setText(config.getString("Small-Image-Key"));
		this.smallImageText.setText(config.getString("Small-Image-Text"));
		this.largeImageKey.setText(config.getString("Large-Image-Key"));
		this.largeImageText.setText(config.getString("Large-Image-Text"));

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("Saving config file...");

				Configuration config = Config.getConfig(file);

				config.set("Application-ID", getText(applicationId));
				config.set("Details", getText(details));
				config.set("State", getText(state));
				config.set("Small-Image-Key", getText(smallImageKey));
				config.set("Small-Image-Text", getText(smallImageText));
				config.set("Large-Image-Key", getText(largeImageKey));
				config.set("Large-Image-Text", getText(largeImageText));

				Config.saveConfig(config, file);

				System.out.println("Saving complete!");

				System.out.println("Closing IPC client...");
				onDisconnect(null);
				System.out.println("Closing complete!");
			}
		});
	}

	@FXML
	private void onConnection(ActionEvent event) {
		System.out.println("#onConnect");

		String tempApplicationId = this.applicationId.getText();
		if (tempApplicationId.equals("")) {
			this.buildAlert(AlertType.ERROR, "エラー", "Application ID を入力してください！", null);
			return;
		}
		long applicationId;
		try {
			applicationId = Long.valueOf(tempApplicationId);
		} catch (Exception e) {
			this.buildAlert(AlertType.ERROR, "エラー", "正しく Application ID を入力してください！", null);
			return;
		}

		String details = this.getText(this.details);
		String state = this.getText(this.state);

		String smallImageKey = this.getText(this.smallImageKey);
		String smallImageText = this.getText(this.smallImageText);

		String largeImageKey = this.getText(this.largeImageKey);
		String largeImageText = this.getText(this.largeImageText);

		System.out.println("Application ID: " + applicationId);
		System.out.println("Details: " + details);
		System.out.println("Small Image Key: " + smallImageKey);
		System.out.println("Small Image Text: " + smallImageText);
		System.out.println("Large Image Key: " + largeImageKey);
		System.out.println("Large Image Text: " + largeImageText);

		if (this.ipcClient == null) {
			this.ipcClient = new IPCClient(applicationId);
			try {
				this.ipcClient.connect(new DiscordBuild[0]);
			} catch (NoDiscordClientException e) {
				this.buildAlert(AlertType.ERROR, "エラー", "Discord を起動している状態で実行してください！", null);
				return;
			}
		}
		this.presence = new RichPresence.Builder();
		this.presence.setDetails(details);
		this.presence.setState(state);
		if (this.time == 0L) {
			this.time = System.currentTimeMillis();
		}
		this.presence.setStartTimestamp(this.time);
		this.presence.setSmallImage(smallImageKey, smallImageText);
		this.presence.setLargeImage(largeImageKey, largeImageText);
		this.ipcClient.sendRichPresence(this.presence.build());

		this.disconnect.setDisable(false);
		this.connect.setText("Re-Send");

		this.buildAlert(AlertType.INFORMATION, "成功", "Discord に接続しました。", "Rich Presence を有効化しました！");
	}

	@FXML
	private void onDisconnect(ActionEvent event) {
		System.out.println("#onDisconnect");

		try {
			this.ipcClient.close();
			this.ipcClient = null;
		} catch (Exception e) {
		}

		this.disconnect.setDisable(true);
		this.connect.setText("Connect");
	}

	private String getText(TextField textField) {
		String text = textField.getText();
		if (text.equals("")) {
			text = null;
		}
		return text;
	}

	private void buildAlert(Alert.AlertType alertyType, String title, String headerText, String content) {
		Stage primaryStage = (Stage) this.connect.getScene().getWindow();

		Alert alert = new Alert(alertyType);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		try {
			stage.getIcons().add(primaryStage.getIcons().get(0));
		} catch (Exception e) {
		}

		alert.setTitle(title);
		alert.setHeaderText(headerText);
		if (content != null) {
			alert.setContentText(content);
		}
		alert.show();
	}

}
