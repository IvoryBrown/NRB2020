package application.worker.menu.newworksheet.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import application.client.menu.client.database.ClientDataBase;
import application.client.menu.client.pojo.Client;
import application.setting.identification.Identification;
import application.worker.menu.newworksheet.database.WorksheetDataBase;
import application.worker.menu.newworksheet.pojo.NewWorksheet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class NewWorksheetController implements Initializable {

	// Ugyfel
	@FXML
	private TableView<Client> clientTableView;
	private TableColumn<Client, String> clientName;
	@FXML
	private TableView<NewWorksheet> worksheetTableView;
	private TableColumn<Client, Integer> worksheetId;
	private TableColumn<Client, String> worksheetNumber, worksheetStatus, worksheetBillable, worksheetBillableData,
			worksheetComment;
	private TableColumn<Client, Date> worksheetStartDate, worksheetEndDate;

	@FXML
	private Button saveButton, updateButton;
	@FXML
	private Label clientNameLbl, messageLbl;
	@FXML
	private TextArea worksheetCommentTxt;
	private String clientId = null;

	private ClientDataBase clientDataBase = new ClientDataBase();
	private WorksheetDataBase worksheetDataBase = new WorksheetDataBase();

	// worker tabel
	@SuppressWarnings("unchecked")
	private void setClientTableData() {
		// Name column
		clientName = new TableColumn<Client, String>("Ügyfél");
		clientName.setMinWidth(166);
		clientName.setMaxWidth(166);
		clientName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
		// Username column

		clientTableView.getColumns().addAll(clientName);

		clientTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
			@Override
			public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
				if (oldValue == null || newValue != null) {
					clientNameLbl.setText(newValue.getClientName());
					clientId = newValue.getClientId();
				}
			}
		});

	}

	// Textfield ellenorzes
	private boolean checkWorksheet() {
		if (worksheetCommentTxt.getText().trim().isEmpty()) {
			worksheetCommentTxt.setStyle(
					" -fx-text-box-border: #CD5C5C; -fx-focus-color: #CD5C5C;  -fx-prompt-text-fill: #CD5C5C;");
			worksheetCommentTxt.setPromptText("Kötelezően kitöltendő");
		} else {
			worksheetCommentTxt.setStyle(null);
			worksheetCommentTxt.setPromptText("");
		}

		if (worksheetCommentTxt.getText().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	//Ugyfel id check
	private boolean checkClientID(String s) {
		if (s != null) {
			return true;
		} else {
			return false;
		}

	}

	private String localDate() {
		String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
		return date;

	}

	// worker DB list
	public ObservableList<Client> workerData() {
		final ObservableList<Client> workerData = FXCollections.observableArrayList();
		workerData.addAll(clientDataBase.getAllWorker());
		return workerData;

	}

	// database frissites
	@FXML
	private void updateButton() {
		clientTableView.getItems().clear();
		clientTableView.getColumns().clear();
		clientTableView.setItems(workerData());
		setClientTableData();
	}

	// database frissites
	@FXML
	private void saveButton() {
		if (checkClientID(clientId)) {
			if (checkWorksheet()) {
				worksheetDataBase.addClient(new NewWorksheet(clientId, "ML-" + Identification.random(), localDate(),
						"Nyitva", "Nem", worksheetCommentTxt.getText()));
				messageLbl.setStyle("-fx-text-fill: #0d6c0d;");
				messageLbl.setText("Sikeres mentés");
			} else {
				messageLbl.setStyle("-fx-text-fill: #CD5C5C;");
				messageLbl.setText("Sikertelen mentés");
			}

		} else {
			messageLbl.setStyle("-fx-text-fill: #CD5C5C;");
			messageLbl.setText("Nincs partner kiválasztva");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
