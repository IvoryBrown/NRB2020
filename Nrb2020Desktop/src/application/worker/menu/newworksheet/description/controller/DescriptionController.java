package application.worker.menu.newworksheet.description.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import application.worker.menu.newworksheet.description.database.DescriptionDB;
import application.worker.menu.newworksheet.description.pojo.Description;
import application.worker.menu.newworksheet.description.pojo.StaticId;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class DescriptionController implements Initializable {
	@FXML
	private Button saveButton;

	@FXML
	private TextArea descriptionCommentTxt;
	@FXML
	private Label messageLbl;
	private DescriptionDB escriptionDB = new DescriptionDB();

	@FXML
	private void saveButton() {
		if (checkWorksheet()) {
			escriptionDB.addDescription(new Description(Integer.parseInt(StaticId.getId()), localDate(),
					descriptionCommentTxt.getText(), "Nyitva"));
			messageLbl.setStyle("-fx-text-fill: #0d6c0d;");
			messageLbl.setText("Sikeres mentés");
			clearTxt();
		} else {
			messageLbl.setStyle("-fx-text-fill: #CD5C5C;");
			messageLbl.setText("Sikertelen mentés");
		}

	}
	
	private void clearTxt() {
		descriptionCommentTxt.clear();
	}

	// Datum
	private String localDate() {
		String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
		return date;

	}

	private boolean checkWorksheet() {
		if (descriptionCommentTxt.getText().trim().isEmpty()) {
			descriptionCommentTxt.setStyle(
					" -fx-text-box-border: #CD5C5C; -fx-focus-color: #CD5C5C;  -fx-prompt-text-fill: #CD5C5C;");
			descriptionCommentTxt.setPromptText("Kötelezően kitöltendő");
		} else {
			descriptionCommentTxt.setStyle(null);
			descriptionCommentTxt.setPromptText("");
		}

		if (descriptionCommentTxt.getText().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
