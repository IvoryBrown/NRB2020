package application.client.menu.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.client.menu.client.database.ClientDataBase;
import application.client.menu.client.pojo.Client;
import application.setting.popup.TooltipMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class ClientController implements Initializable {
	// HomeMenu.fxml <- !!!Ugyfel tabla!!! -> Client.fxml ->Client.css ->
	// Client.java
	// Ugyfel adatok
	@FXML
	private TextField clientNameTxt, clientZipCodeTxt, clientSettlementTxt, clientTitleTxt, clientMailTxt,
			clientPhoneTxt, clientBillingTxt;

	// Gombok
	@FXML
	private Button saveButton, updateButton;
	@FXML
	private TableView<Client> clientTableView;
	private TableColumn<Client, Integer> clientId;
	private TableColumn<Client, String> clientName;
	private TableColumn<Client, String> clientZipCode;
	private TableColumn<Client, String> clientSettlement;
	private TableColumn<Client, String> clientTitle;
	private TableColumn<Client, String> clientEmail;
	private TableColumn<Client, String> clientMobil;
	private TableColumn<Client, String> clientBilling;
	@FXML
	private Label messageLbl;
	private ClientDataBase clientDataBase = new ClientDataBase();

	private void tooltipMessage() {
		clientNameTxt.setTooltip(new TooltipMessage("Példa: Cég név vagy személy"));
		clientZipCodeTxt.setTooltip(new TooltipMessage("Példa: 6767"));
		clientSettlementTxt.setTooltip(new TooltipMessage("Példa: Ópusztaszer"));
		clientTitleTxt.setTooltip(new TooltipMessage("Példa: Erdei Ferenc u. 38"));
		clientMailTxt.setTooltip(new TooltipMessage("Példa: info@nrb2020.hu"));
		clientPhoneTxt.setTooltip(new TooltipMessage("Példa: +36 30 733 9987"));
		clientBillingTxt.setTooltip(new TooltipMessage("Példa: Számlázó partner kód"));
		saveButton.setTooltip(new TooltipMessage("Mentés"));
		updateButton.setTooltip(new TooltipMessage("Táblázat frissítés"));
	}

	// Textfield ellenorzes
	private boolean checkClient() {
		if (clientNameTxt.getText().trim().isEmpty()) {
			clientNameTxt.setStyle(
					" -fx-text-box-border: #CD5C5C; -fx-focus-color: #CD5C5C;  -fx-prompt-text-fill: #CD5C5C;");
			clientNameTxt.setPromptText("Kötelezően kitöltendő");
		} else {
			clientNameTxt.setStyle(null);
			clientNameTxt.setPromptText("");
		}
		if (clientZipCodeTxt.getText().trim().isEmpty()) {
			clientZipCodeTxt.setStyle(
					" -fx-text-box-border: #CD5C5C; -fx-focus-color: #CD5C5C;  -fx-prompt-text-fill: #CD5C5C;");
			clientZipCodeTxt.setPromptText("Kötelezően kitöltendő");
		} else {
			clientZipCodeTxt.setStyle(null);
			clientZipCodeTxt.setPromptText("");
		}
		if (clientPhoneTxt.getText().trim().isEmpty()) {
			clientPhoneTxt.setStyle(
					" -fx-text-box-border: #CD5C5C; -fx-focus-color: #CD5C5C;  -fx-prompt-text-fill: #CD5C5C;");
			clientPhoneTxt.setPromptText("Kötelezően kitöltendő");
		} else {
			clientPhoneTxt.setStyle(null);
			clientPhoneTxt.setPromptText("");
		}
		if (clientNameTxt.getText().trim().isEmpty() || clientZipCodeTxt.getText().trim().isEmpty()
				|| clientPhoneTxt.getText().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	// telefon ellenorzes
	private boolean checkMobil(String s) {
		String regex = "^\\s?((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?\\s?";
		return s.matches(regex);
	}

	// iranyitoszam ellenorzes
	private boolean checkSettlement(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// mail ellenorzes
	private boolean checkClientMail(String mail) {
		Pattern emailPattern = Pattern.compile(
				"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
		if (!mail.trim().isEmpty()) {
			Matcher m = emailPattern.matcher(mail);
			if (m.matches()) {
				return true;
			} else {
				return false;
			}
		} else {
			clientMailTxt.setStyle(null);
			return true;
		}
	}

	// textfield torles
	private void clearTextField() {
		clientNameTxt.clear();
		clientZipCodeTxt.clear();
		clientSettlementTxt.clear();
		clientTitleTxt.clear();
		clientMailTxt.clear();
		clientPhoneTxt.clear();
		clientBillingTxt.clear();

	}

	// Save database
	@FXML
	private void saveButton() {
		if (checkClient()) {
			if (checkClientMail(clientMailTxt.getText())) {
				if (checkSettlement(clientZipCodeTxt.getText())) {
					if (checkMobil(clientPhoneTxt.getText())) {
						clientDataBase.addClient(new Client(clientNameTxt.getText(), clientZipCodeTxt.getText(),
								clientSettlementTxt.getText(), clientTitleTxt.getText(), clientMailTxt.getText(),
								clientPhoneTxt.getText(), "BIZX-" + clientBillingTxt.getText()));
						clearTextField();
						updateButton();
						messageLbl.setStyle("  -fx-text-fill:#0d6c0d");
						messageLbl.setText("Sikeres mentés");

					} else {
						messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
						messageLbl.setText("Nem megfelelő telefonszám");
					}
				} else {
					messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
					messageLbl.setText("Hibás Irányítószám");
				}

			} else {
				messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
				messageLbl.setText("Hibás E-mail");
			}

		} else {
			messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
			messageLbl.setText("Nincs minden mező kitőltve");
		}

	}

	// Save database
	@FXML
	private void updateButton() {
		clientTableView.getItems().clear();
		clientTableView.getColumns().clear();
		clientTableView.setItems(workerData());
		setClientTableData();
	}

	// worker tabel
	@SuppressWarnings("unchecked")
	private void setClientTableData() {
		// ID column
		clientId = new TableColumn<Client, Integer>("ID");
		clientId.setMinWidth(50);
		clientId.setCellValueFactory(new PropertyValueFactory<Client, Integer>("clientId"));
		// Name column
		clientName = new TableColumn<Client, String>("Név");
		clientName.setMinWidth(150);
		clientName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
		// Username column
		clientZipCode = new TableColumn<Client, String>("Irányítószám");
		clientZipCode.setMinWidth(130);
		clientZipCode.setCellValueFactory(new PropertyValueFactory<Client, String>("clientZipCode"));
		clientZipCode.setCellFactory(TextFieldTableCell.forTableColumn());
		clientZipCode.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				if (!d.getNewValue().trim().isEmpty()) {
					if (checkSettlement(d.getNewValue())) {
						actualClient.setClientZipCode(d.getNewValue());
						clientDataBase.updateClient(actualClient);
						messageLbl.setStyle("  -fx-text-fill:#0d6c0d");
						messageLbl.setText("Sikeres mentés");
						updateButton();
					} else {
						updateButton();
						messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
						messageLbl.setText("Hibás Irányítószám");
					}

				} else {
					updateButton();
					messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
					messageLbl.setText("Hiba, Nem lehet üres mező");
				}

			}
		});
		// Password column
		clientSettlement = new TableColumn<Client, String>("Település");
		clientSettlement.setMinWidth(150);
		clientSettlement.setCellValueFactory(new PropertyValueFactory<Client, String>("clientSettlement"));
		clientSettlement.setCellFactory(TextFieldTableCell.forTableColumn());
		clientSettlement.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				if (!d.getNewValue().trim().isEmpty()) {
					actualClient.setClientSettlement(d.getNewValue());
					clientDataBase.updateClient(actualClient);
					messageLbl.setStyle("  -fx-text-fill:#0d6c0d");
					messageLbl.setText("Sikeres mentés");
					updateButton();
				} else {
					updateButton();
					messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
					messageLbl.setText("Hiba, Nem lehet üres mező");
				}

			}
		});
		// Mail column
		clientTitle = new TableColumn<Client, String>("Cím");
		clientTitle.setMinWidth(280);
		clientTitle.setCellValueFactory(new PropertyValueFactory<Client, String>("clientTitle"));
		clientTitle.setCellFactory(TextFieldTableCell.forTableColumn());
		clientTitle.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				if (!d.getNewValue().trim().isEmpty()) {
					actualClient.setClientTitle(d.getNewValue());
					clientDataBase.updateClient(actualClient);
					messageLbl.setStyle("  -fx-text-fill:#0d6c0d");
					messageLbl.setText("Sikeres mentés");
					updateButton();
				} else {
					updateButton();
					messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
					messageLbl.setText("Hiba, Nem lehet üres mező");
				}

			}
		});
		// Post column
		clientEmail = new TableColumn<Client, String>("E-mail");
		clientEmail.setMinWidth(180);
		clientEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("clientEmail"));
		clientEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		clientEmail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				if (!d.getNewValue().trim().isEmpty()) {
					if (checkClientMail(d.getNewValue())) {
						actualClient.setClientEmail(d.getNewValue());
						clientDataBase.updateClient(actualClient);
						messageLbl.setStyle("  -fx-text-fill:#0d6c0d");
						messageLbl.setText("Sikeres mentés");
						updateButton();

					} else {
						updateButton();
						messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
						messageLbl.setText("Hiba, Nem megfelelő e-mail");
					}
				} else {
					updateButton();
					messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
					messageLbl.setText("Hiba, Nem lehet üres mező");
				}

			}
		});
		// Identification column
		clientMobil = new TableColumn<Client, String>("Telefon");
		clientMobil.setMinWidth(140);
		clientMobil.setCellValueFactory(new PropertyValueFactory<Client, String>("clientMobil"));
		clientMobil.setCellFactory(TextFieldTableCell.forTableColumn());
		clientMobil.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				if (!d.getNewValue().trim().isEmpty()) {
					if (checkMobil(d.getNewValue())) {
						actualClient.setClientMobil(d.getNewValue());
						clientDataBase.updateClient(actualClient);
						messageLbl.setStyle("  -fx-text-fill:#0d6c0d");
						messageLbl.setText("Sikeres mentés");
						updateButton();

					} else {
						updateButton();
						messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
						messageLbl.setText("Hiba, Nem megfelelő telefonszám");
					}
				} else {
					updateButton();
					messageLbl.setStyle("  -fx-text-fill: #CD5C5C");
					messageLbl.setText("Hiba, Nem lehet üres mező");
				}

			}
		});

		clientBilling = new TableColumn<Client, String>("Számlázó");
		clientBilling.setMinWidth(100);
		clientBilling.setCellValueFactory(new PropertyValueFactory<Client, String>("clientBilling"));

		clientTableView.getColumns().addAll(clientId, clientName, clientZipCode, clientSettlement, clientTitle,
				clientEmail, clientMobil, clientBilling);

	}

	// worker DB list
	public ObservableList<Client> workerData() {
		final ObservableList<Client> workerData = FXCollections.observableArrayList();
		workerData.addAll(clientDataBase.getAllWorker());
		return workerData;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tooltipMessage();
	}

}
