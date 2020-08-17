package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {
	// Fooldal; partnerek
	// Fo panel
	@FXML
	private Button homeMenuButton, clientMenuButton, workerMenuButton;
	@FXML
	private AnchorPane homeMenu, clientMenu, workerMenu;

	@FXML
	private void homeMenuButton() {
		homeMenu.setVisible(true);
		clientMenu.setVisible(false);
		workerMenu.setVisible(false);
	}

	@FXML
	private void clientMenuButton() {
		homeMenu.setVisible(false);
		clientMenu.setVisible(true);
		workerMenu.setVisible(false);
	}

	@FXML
	private void workerMenuButton() {
		homeMenu.setVisible(false);
		clientMenu.setVisible(false);
		workerMenu.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
