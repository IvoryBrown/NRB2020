package application.client.menu.controller;

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

public class ClientMenuController implements Initializable {
	// Partnerek menu
	// Bal

	@FXML
	private VBox clientMenuVBox;
	@FXML
	private AnchorPane clientPane, purveyorPane;
	private TreeView<String> clientMenuTreeView;

	private final String MENU_CLIENT = "Ügyfelek";
	private final String MENU_PURVEYOR = "Beszállítók";
	private final String MENU_WORKER = "???";

	@SuppressWarnings("unchecked")
	private void treeMenu() {
		TreeItem<String> treeItemRoot1 = new TreeItem<String>("Menü");
		clientMenuTreeView = new TreeView<>(treeItemRoot1);
		clientMenuTreeView.setShowRoot(false);

		TreeItem<String> nodeItemA = new TreeItem<String>(MENU_CLIENT);
		TreeItem<String> nodeItemB = new TreeItem<>(MENU_PURVEYOR);
		TreeItem<String> nodeItemC = new TreeItem<>(MENU_WORKER);
		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC);

		clientMenuVBox.getChildren().add(clientMenuTreeView);

		clientMenuTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				TreeItem<String> selectedItem = (TreeItem<String>) newValue;
				String selectedMenu;
				selectedMenu = selectedItem.getValue();
				if (null != selectedMenu) {
					switch (selectedMenu) {
					case MENU_CLIENT:
						clientPane.setVisible(true);
						purveyorPane.setVisible(false);
						break;
					case MENU_PURVEYOR:
						clientPane.setVisible(false);
						purveyorPane.setVisible(true);
						break;
					case MENU_WORKER:
						clientPane.setVisible(false);
						purveyorPane.setVisible(false);
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
