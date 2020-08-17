package application.worker.menu.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class WorkerMenuController implements Initializable{
	// Partnerek menu
	// Bal

	@FXML
	private VBox wrokerMenuVBox;
	@FXML
	private AnchorPane worksheetPane;
	private TreeView<String> workerMenuTreeView;

	private final String MENU_WORKER = "Munkalap";
	private final String MENU_WORKERS = "Leírás";
	private final String MENU_PURVEYOR = "Beszállítók";
	private final String MENU_W = "???";

	@SuppressWarnings("unchecked")
	private void treeMenu() {
		TreeItem<String> treeItemRoot1 = new TreeItem<String>("Menü");
		workerMenuTreeView = new TreeView<>(treeItemRoot1);
		workerMenuTreeView.setShowRoot(false);

		TreeItem<String> nodeItemA = new TreeItem<String>(MENU_WORKER);
		TreeItem<String> nodeItemA1 = new TreeItem<String>(MENU_WORKERS);
		nodeItemA.getChildren().addAll(nodeItemA1);
		TreeItem<String> nodeItemB = new TreeItem<>(MENU_PURVEYOR);
		TreeItem<String> nodeItemC = new TreeItem<>(MENU_W);
		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC);

		wrokerMenuVBox.getChildren().add(workerMenuTreeView);

		workerMenuTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				TreeItem<String> selectedItem = (TreeItem<String>) newValue;
				String selectedMenu;
				selectedMenu = selectedItem.getValue();
				if (null != selectedMenu) {
					switch (selectedMenu) {
					case MENU_WORKER:
						worksheetPane.setVisible(true);
						break;
					case MENU_PURVEYOR:
						worksheetPane.setVisible(false);
						break;
					case MENU_W:
						worksheetPane.setVisible(false);
						break;

					}
				}
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		treeMenu();

	}
}
