/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storycanvas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dockfx.DockPane;

/**
 *
 * @author KMY
 */
public class StoryCanvas extends Application {

	@Override
	public void start (Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view/window/MainWindow.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();

		// DockPaneのスタイルシートを初期化
		DockPane.initializeDefaultUserAgentStylesheet();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main (String[] args) {
		launch(args);
	}

}
