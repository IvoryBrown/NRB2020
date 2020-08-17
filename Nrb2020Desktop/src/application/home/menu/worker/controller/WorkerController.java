package application.home.menu.worker.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.home.menu.worker.database.WorkerDataBase;
import application.home.menu.worker.pojo.Worker;
import application.setting.identification.Identification;
import application.setting.popup.TooltipMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class WorkerController implements Initializable {
	// HomeMenu.fxml <- !!!Dolgozi tabla!!! -> Worker.fxml ->Worker.css ->
	// Worker.java -> Identification.java -> WorkerDataBase.java
	@FXML
	private TableView<Worker> workerTableView;
	@FXML
	private Button saveButton, identificationBtn, updateButton;
	@FXML
	private TextField nameTxt, userNameTxt, passwordTxt, emailTxt, rankTxt;
	// Azonosito
	@FXML
	private Label identificationLbl;
	// adatbazis kapcsolat
	private WorkerDataBase dataBase = new WorkerDataBase();

	// Dolgozoi oszlopok tabla
	private TableColumn<Worker, Integer> workerId;
	private TableColumn<Worker, String> workerName;
	private TableColumn<Worker, String> workerUserName;
	private TableColumn<Worker, String> workerMail;
	private TableColumn<Worker, String> workerPassword;
	private TableColumn<Worker, String> workerPost;
	private TableColumn<Worker, String> workerIdentification;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		tooltipMessage();
//		WorkerTablet();
	}

	// popup ablakok
	private void tooltipMessage() {
		nameTxt.setTooltip(new TooltipMessage("Példa: Kiss János Béla"));
		userNameTxt.setTooltip(new TooltipMessage("Példa: kjbela"));
		passwordTxt.setTooltip(new TooltipMessage("Példa: 12345678 Min.: 8 karakter"));
		emailTxt.setTooltip(new TooltipMessage("Példa: kjbela@valami.hu"));
		rankTxt.setTooltip(new TooltipMessage("Példa: Manager"));
		identificationLbl.setTooltip(new TooltipMessage("Automatikusan generált"));
		saveButton.setTooltip(new TooltipMessage("Automatikusan generált"));
	}

	// Azonosito generator
	@FXML
	private void identificationBtn() {
		identificationLbl.setText("");
		new Identification();
		identificationLbl.setText(Identification.random());
		// GC clear
		System.gc();
	}

	// Save database
	@FXML
	private void saveButton() {

	}

	// Update database
	@FXML
	private void updateButton() {
		workerTableView.setItems(workerData());
	}

	// database table
	public void WorkerTablet() {
		workerTableView.getItems().clear();
		workerTableView.getColumns().clear();
		setWorkerTableData();

	}

	// worker tabel
	@SuppressWarnings("unchecked")
	private void setWorkerTableData() {
		// ID column
		workerId = new TableColumn<Worker, Integer>("ID");
		workerId.setMinWidth(50);
		workerId.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("workerId"));
		// Name column
		workerName = new TableColumn<Worker, String>("Név");
		workerName.setMinWidth(150);
		workerName.setCellValueFactory(new PropertyValueFactory<Worker, String>("workerName"));
		// Username column
		workerUserName = new TableColumn<Worker, String>("Felhasználónév");
		workerUserName.setMinWidth(150);
		workerUserName.setCellValueFactory(new PropertyValueFactory<Worker, String>("workerUserName"));
		// Password column
		workerPassword = new TableColumn<Worker, String>("Jelszó");
		workerPassword.setMinWidth(100);
		workerPassword.setCellValueFactory(new PropertyValueFactory<Worker, String>("workerPassword"));
		// Mail column
		workerMail = new TableColumn<Worker, String>("Email");
		workerMail.setMinWidth(180);
		workerMail.setCellValueFactory(new PropertyValueFactory<Worker, String>("workerMail"));
		// Post column
		workerPost = new TableColumn<Worker, String>("Beosztás");
		workerPost.setMinWidth(100);
		workerPost.setCellValueFactory(new PropertyValueFactory<Worker, String>("workerPost"));
		// Identification column
		workerIdentification = new TableColumn<Worker, String>("Azonosító");
		workerIdentification.setMinWidth(100);
		workerIdentification.setCellValueFactory(new PropertyValueFactory<Worker, String>("workerIdentification"));

		workerTableView.getColumns().addAll(workerId, workerName, workerUserName, workerPassword, workerMail,
				workerPost, workerIdentification);

	}

	// worker DB list
	public ObservableList<Worker> workerData() {
		final ObservableList<Worker> workerData = FXCollections.observableArrayList();
		workerData.addAll(dataBase.getAllWorker());
		return workerData;

	}

}
