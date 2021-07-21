package ucf.assignments;

/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Chad Higgins
 *
 */

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManagerController implements Initializable {


    FileChooser fileChooser = new FileChooser();

    public TextField ErrorMessage;
    public TextField AddValue;
    public TextField AddSerialNumber;
    public TextField AddName;
    public TextField SearchTerm;
    public TableView InventoryTable;
    public TableColumn Value;
    public TableColumn SerialNumber;
    public TableColumn Name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileChooser.setInitialDirectory(new File("C:\\"));

        FileChooser.ExtensionFilter fileExtensions =
                new FileChooser.ExtensionFilter(
                        "Web pages", "*.txt", "*.html");

        fileChooser.getExtensionFilters().add(fileExtensions);
    }

    public void SearchBar(KeyEvent keyEvent) {
    }

    public void SaveClicked(ActionEvent actionEvent) {
    }

    public void LoadClicked(ActionEvent actionEvent) {
    }

    public void CloseClicked(ActionEvent actionEvent) {
    }

    public void AddClicked(ActionEvent actionEvent) {
    }
}
