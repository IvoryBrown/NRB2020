package application.home.menu.controller;

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

public class HomeMenuController implements Initializable {
	// Fooldal menu
	// Bal

	@FXML
	private VBox homeMenuVBox;
	@FXML
	private AnchorPane homePane, settingPane, workerPane;
	private TreeView<String> homeMenuTreeView;

	private final String MENU_HOME = "Kezdőlap";
	private final String MENU_SETTING = "Beállítások";
	private final String MENU_WORKER = "Dolgozók";

	@SuppressWarnings("unchecked")
	private void treeMenu() {
		TreeItem<String> treeItemRoot1 = new TreeItem<String>("Menü");
		homeMenuTreeView = new TreeView<>(treeItemRoot1);
		homeMenuTreeView.setShowRoot(false);

		TreeItem<String> nodeItemA = new TreeItem<String>(MENU_HOME);
		TreeItem<String> nodeItemB = new TreeItem<>(MENU_SETTING);
		TreeItem<String> nodeItemC = new TreeItem<>(MENU_WORKER);
		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC);

		homeMenuVBox.getChildren().add(homeMenuTreeView);

		homeMenuTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				TreeItem<String> selectedItem = (TreeItem<String>) newValue;
				String selectedMenu;
				selectedMenu = selectedItem.getValue();
				if (null != selectedMenu) {
					switch (selectedMenu) {
					case MENU_HOME:
						homePane.setVisible(true);
						settingPane.setVisible(false);
						workerPane.setVisible(false);
						break;
					case MENU_SETTING:
						homePane.setVisible(false);
						settingPane.setVisible(true);
						workerPane.setVisible(false);
						break;
					case MENU_WORKER:
						homePane.setVisible(false);
						settingPane.setVisible(false);
						workerPane.setVisible(true);
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
