package application.worker.menu.newworksheet.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import application.client.menu.client.database.ClientDataBase;
import application.client.menu.client.pojo.Client;
import application.setting.combobox.ComboBox;
import application.setting.identification.Identification;
import application.worker.menu.newworksheet.database.WorksheetDataBase;
import application.worker.menu.newworksheet.pojo.NewWorksheet;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class NewWorksheetController implements Initializable {

	// Ugyfel
	@FXML
	private TableView<Client> clientTableView;
	private TableColumn<Client, String> clientName;
	@FXML
	private TableView<NewWorksheet> worksheetTableView;
	private TableColumn<NewWorksheet, Integer> worksheetId;
	private TableColumn<NewWorksheet, String> clientNameWork, worksheetNumber, worksheetStatus, worksheetBillable,
			worksheetBillableData, worksheetComment;
	private TableColumn<NewWorksheet, Date> worksheetStartDate, worksheetEndDate;

	@FXML
	private Button saveButton, updateButton;
	@FXML
	private Label clientNameLbl, messageLbl;
	@FXML
	private TextArea worksheetCommentTxt;
	private String clientId = null;

	private ClientDataBase clientDataBase = new ClientDataBase();
	private WorksheetDataBase worksheetDataBase = new WorksheetDataBase();

	// client tabel
	@SuppressWarnings("unchecked")
	private void setClientTableData() {
		// Name column
		clientName = new TableColumn<Client, String>("Ügyfél");
		clientName.setMinWidth(166);
		clientName.setMaxWidth(166);
		clientName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));

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

	// client tabel
	@SuppressWarnings("unchecked")
	private void setWorksheetTableData() {
		// Name column
		worksheetId = new TableColumn<NewWorksheet, Integer>("ID");
		worksheetId.setMinWidth(36);
		worksheetId.setMaxWidth(36);
		worksheetId.setCellValueFactory(new PropertyValueFactory<NewWorksheet, Integer>("worksheetId"));

		clientNameWork = new TableColumn<NewWorksheet, String>("Ügyfél");
		clientNameWork.setPrefWidth(140);
		clientNameWork.setCellValueFactory(new PropertyValueFactory<NewWorksheet, String>("clientName"));

		worksheetNumber = new TableColumn<NewWorksheet, String>("Azonosító");
		worksheetNumber.setMinWidth(90);
		worksheetNumber.setMaxWidth(90);
		worksheetNumber.setCellValueFactory(new PropertyValueFactory<NewWorksheet, String>("worksheetNumber"));

		worksheetStartDate = new TableColumn<NewWorksheet, Date>("Kezdés");
		worksheetStartDate.setMinWidth(90);
		worksheetStartDate.setMaxWidth(90);
		worksheetStartDate.setCellValueFactory(new PropertyValueFactory<NewWorksheet, Date>("worksheetStartDate"));

		worksheetEndDate = new TableColumn<NewWorksheet, Date>("Befejezés");
		worksheetEndDate.setMinWidth(90);
		worksheetEndDate.setMaxWidth(90);
		worksheetEndDate.setCellValueFactory(new PropertyValueFactory<NewWorksheet, Date>("worksheetEndDate"));

		worksheetStatus = new TableColumn<NewWorksheet, String>("Állapot");
		worksheetStatus.setMinWidth(110);
		worksheetStatus.setMaxWidth(110);
		worksheetStatus.setCellValueFactory(new PropertyValueFactory<NewWorksheet, String>("worksheetStatus"));
		worksheetStatus.setCellValueFactory(i -> {
			final String value = i.getValue().getWorksheetStatus();
			return Bindings.createObjectBinding(() -> value);
		});
		worksheetStatus.setCellFactory(ComboBoxTableCell.forTableColumn("Zárva"));
		worksheetStatus.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NewWorksheet, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<NewWorksheet, String> d) {
				NewWorksheet actualWorksheet = (NewWorksheet) d.getTableView().getItems()
						.get(d.getTablePosition().getRow());
				actualWorksheet.setWorksheetStatus(d.getNewValue());
				actualWorksheet.setWorksheetEndDate(localDate());
				worksheetDataBase.updateWorksheet(actualWorksheet);
			}
		});

		worksheetBillable = new TableColumn<NewWorksheet, String>("Számla");
		worksheetBillable.setMinWidth(100);
		worksheetBillable.setMaxWidth(100);
		worksheetBillable.setCellValueFactory(new PropertyValueFactory<NewWorksheet, String>("worksheetBillable"));
		worksheetBillable.setCellValueFactory(i -> {
			final String value = i.getValue().getWorksheetBillable();
			return Bindings.createObjectBinding(() -> value);
		});
		worksheetBillable.setCellFactory(ComboBoxTableCell.forTableColumn(ComboBox.woorksheet()));
		worksheetBillable.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NewWorksheet, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<NewWorksheet, String> d) {
				NewWorksheet actualWorksheet = (NewWorksheet) d.getTableView().getItems()
						.get(d.getTablePosition().getRow());
				actualWorksheet.setWorksheetBillable(d.getNewValue());
				worksheetDataBase.updateWorksheet(actualWorksheet);
			}
		});

		worksheetBillableData = new TableColumn<NewWorksheet, String>("Számla adatok");
		worksheetBillableData.setPrefWidth(150);
		worksheetBillableData
				.setCellValueFactory(new PropertyValueFactory<NewWorksheet, String>("worksheetBillableData"));
		worksheetBillableData.setCellFactory(TextFieldTableCell.forTableColumn());
		worksheetBillableData.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NewWorksheet, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<NewWorksheet, String> d) {
				NewWorksheet actualWorksheet = (NewWorksheet) d.getTableView().getItems()
						.get(d.getTablePosition().getRow());
				actualWorksheet.setWorksheetBillableData(d.getNewValue());
				worksheetDataBase.updateWorksheet(actualWorksheet);
			}
		});

		worksheetComment = new TableColumn<NewWorksheet, String>("Megjegyzés");
		worksheetComment.setPrefWidth(150);
		worksheetComment.setCellValueFactory(new PropertyValueFactory<NewWorksheet, String>("worksheetComment"));
		worksheetComment.setCellFactory(TextFieldTableCell.forTableColumn());
		worksheetComment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NewWorksheet, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<NewWorksheet, String> d) {
				NewWorksheet actualWorksheet = (NewWorksheet) d.getTableView().getItems()
						.get(d.getTablePosition().getRow());
				actualWorksheet.setWorksheetComment(d.getNewValue());
				worksheetDataBase.updateWorksheet(actualWorksheet);
			}
		});

		worksheetTableView.getColumns().addAll(worksheetId, clientNameWork, worksheetNumber, worksheetStartDate,
				worksheetEndDate, worksheetStatus, worksheetBillable, worksheetBillableData, worksheetComment);

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

	// Ugyfel id check
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

	// client DB list
	public ObservableList<Client> clientData() {
		final ObservableList<Client> workerData = FXCollections.observableArrayList();
		workerData.addAll(clientDataBase.getAllWorker());
		return workerData;

	}

	// worker DB list
	public ObservableList<NewWorksheet> workersheetData() {
		final ObservableList<NewWorksheet> workerData = FXCollections.observableArrayList();
		workerData.addAll(worksheetDataBase.getAllWorker());
		return workerData;

	}

	// database frissites
	@FXML
	private void updateButton() {
		// cliens tabla
		clientTableView.getItems().clear();
		clientTableView.getColumns().clear();
		clientTableView.setItems(clientData());
		setClientTableData();
		// munkalap
		worksheetTableView.getItems().clear();
		worksheetTableView.getColumns().clear();
		worksheetTableView.setItems(workersheetData());
		setWorksheetTableData();
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
