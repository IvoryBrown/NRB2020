package application.worker.menu.newworksheet.description.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DescriptionMain {
	public void start() {
		try {
			Parent root = FXMLLoader.load(
					getClass().getResource("/application/worker/menu/newworksheet/description/fxml/Description.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setWidth(1300);
			stage.setHeight(650);
			stage.setTitle("NRB2020");
			stage.setOnCloseRequest(evt -> {
				evt.consume();
				shutdown(stage);
			});
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void shutdown(Stage mainWindow) {
		Alert alert = new Alert(Alert.AlertType.NONE, "Biztos ki akarsz lépni?", ButtonType.YES, ButtonType.NO);
		alert.getDialogPane().getStylesheets().add("/application/css/ShowInfo.css");
		alert.initStyle(StageStyle.TRANSPARENT);
		if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			mainWindow.close();
		}
	}
}
