package application.worker.menu.newworksheet.description.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.worker.menu.newworksheet.description.database.DescriptionDB;
import application.worker.menu.newworksheet.description.pojo.Description;
import application.worker.menu.newworksheet.description.pojo.StaticId;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class DescriptionRefreshController implements Initializable {
	@FXML
	private Label messageLbl;
	@FXML
	private TableView<Description> descriptionTableView;
	private TableColumn<Description, Integer> descriptionId;
	private TableColumn<Description, String> descriptionStartDate, descriptionStatus, descriptionComment;
	private DescriptionDB descriptionDB = new DescriptionDB();

	// client tabel
	@SuppressWarnings("unchecked")
	private void setWorksheetTableData() {

		descriptionId = new TableColumn<Description, Integer>("ID");
		descriptionId.setMinWidth(36);
		descriptionId.setMaxWidth(36);
		descriptionId.setCellValueFactory(new PropertyValueFactory<Description, Integer>("descriptionId"));

		descriptionStartDate = new TableColumn<Description, String>("Dátum");
		descriptionStartDate.setPrefWidth(150);
		descriptionStartDate.setCellValueFactory(new PropertyValueFactory<Description, String>("descriptionStartDate"));

		descriptionStatus = new TableColumn<Description, String>("Státusz");
		descriptionStatus.setPrefWidth(150);
		descriptionStatus.setCellValueFactory(new PropertyValueFactory<Description, String>("descriptionStatus"));

		descriptionComment = new TableColumn<Description, String>("Megjegyzés");
		descriptionComment.setPrefWidth(150);
		descriptionComment.setCellValueFactory(new PropertyValueFactory<Description, String>("descriptionComment"));
		descriptionComment.setCellFactory(TextFieldTableCell.forTableColumn());
		descriptionComment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Description, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Description, String> d) {
//				Description actualWorksheet = (Description) d.getTableView().getItems()
//						.get(d.getTablePosition().getRow());
//				actualWorksheet.setDescriptionComment(d.getNewValue());

			}
		});

		descriptionTableView.getColumns().addAll(descriptionId, descriptionStartDate, descriptionStatus,
				descriptionComment);
	}

	// worker DB list
	private ObservableList<Description> workersheetData() {
		final ObservableList<Description> workerData = FXCollections.observableArrayList();
		workerData.addAll(descriptionDB.getAllWorker(StaticId.getId()));
		return workerData;

	}
	
	@FXML
	private void updateBtn() {
		System.out.println(StaticId.getId());
		descriptionTableView.getItems().clear();
		descriptionTableView.getColumns().clear();
		descriptionTableView.setItems(workersheetData());
for (int i = 0; i < workersheetData().size(); i++) {
	System.out.println(workersheetData().size());
}
		setWorksheetTableData();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		descriptionTableView.getItems().clear();
		descriptionTableView.getColumns().clear();
		descriptionTableView.setItems(workersheetData());

		setWorksheetTableData();

	}

}
