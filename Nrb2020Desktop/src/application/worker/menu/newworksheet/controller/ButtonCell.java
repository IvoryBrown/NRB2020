package application.worker.menu.newworksheet.controller;

import application.setting.popup.TooltipMessage;
import application.worker.menu.newworksheet.description.main.DescriptionMain;
import application.worker.menu.newworksheet.description.main.DescriptionRefreshMain;
import application.worker.menu.newworksheet.description.pojo.StaticId;
import application.worker.menu.newworksheet.pojo.NewWorksheet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

//new worksheet controller class

public class ButtonCell extends TableCell<NewWorksheet, Boolean> {
	private final Button commodityButton = new Button("+");
	private final Button infoButton = new Button("!");
	private final HBox hBox = new HBox(commodityButton, infoButton);

	public ButtonCell(TableView<NewWorksheet> tblView) {
		// button munkalap
		commodityButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				int selectdIndex = getTableRow().getIndex();
				NewWorksheet selectedRecord = tblView.getItems().get(selectdIndex);
				StaticId.setId(selectedRecord.getWorksheetId());
				new DescriptionMain().start();
			}

		});

		// info munkalap
		infoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				int selectdIndex = getTableRow().getIndex();
				NewWorksheet selectedRecord = tblView.getItems().get(selectdIndex);
				StaticId.setId(selectedRecord.getWorksheetId());
				new DescriptionRefreshMain().start();
			}

		});
	}

	// viselkedes write

	@Override
	protected void updateItem(Boolean t, boolean empty) {
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		commodityButton.setTooltip(new TooltipMessage("Új munkalap"));
		commodityButton.setMinSize(40, 25);
		infoButton.setTooltip(new TooltipMessage("Info"));
		infoButton.setMinSize(40, 25);
		super.updateItem(t, empty);
		if (!empty) {

			setGraphic(hBox);
		} else {
			setGraphic(null);
			setText(null);
		}
	}
}
