package ucf.assignments;

/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Chad Higgins
 *
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManagerController implements Initializable {

    public ObservableList<InventoryItem> TableList = FXCollections.observableArrayList();
    FileChooser fileChooser = new FileChooser();

    @FXML
    private BorderPane MainScene;

    @FXML
    private TextField ErrorMessage;

    @FXML
    private TextField AddValue;

    @FXML
    private TextField AddSerialNumber;

    @FXML
    private TextField AddName;

    @FXML
    private TextField SearchTerm;

    @FXML
    private TableView<InventoryItem> InventoryTable;

    @FXML
    private TableColumn<InventoryItem, String> Value;

    @FXML
    private TableColumn<InventoryItem, String> SerialNumber;

    @FXML
    private TableColumn<InventoryItem, String> Name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileChooser.setInitialDirectory(new File("C:\\"));
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("HTML", "*.html");
        fileChooser.getExtensionFilters().add(fileExtensions);
        FileChooser.ExtensionFilter fileExtensions2 = new FileChooser.ExtensionFilter("TSV", "*.txt");
        fileChooser.getExtensionFilters().add(fileExtensions2);

        InventoryTable.setEditable(true);

        Name.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("Name"));
        Name.setCellFactory(TextFieldTableCell.forTableColumn());
        Name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<InventoryItem, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<InventoryItem, String> event) {
                InventoryItem item = event.getRowValue();
                if (validateName(event.getNewValue()))
                {
                    item.setName(event.getNewValue());
                    ErrorMessage.setText("No Errors.");
                }
                else
                {
                    if(event.getNewValue().length() > 256) {
                        ErrorMessage.setText("The Name was too long! Try Again.");
                    }
                    else {
                        ErrorMessage.setText("The Name was too short! Try Again.");
                    }
                }
            }
        });

        SerialNumber.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("SerialNumber"));
        SerialNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        SerialNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<InventoryItem, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<InventoryItem, String> event) {
                InventoryItem item = event.getRowValue();
                if (validateSerialNumber(event.getNewValue()))
                {
                    item.setSerialNumber(event.getNewValue());
                    ErrorMessage.setText("No Errors.");
                }
                else if(!validateSerialNumber(event.getNewValue()))
                {
                    ErrorMessage.setText("The Serial Number was not unique serial number in the format of XXXXXXXXXX where " +
                            "X can be either a letter or digit! Try Again.");
                }
            }
        });

        Value.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("Value"));
        Value.setCellFactory(TextFieldTableCell.forTableColumn());
        Value.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<InventoryItem, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<InventoryItem, String> event) {
                InventoryItem item = event.getRowValue();
                if (validateValue(event.getNewValue()))
                {
                    item.setValue(event.getNewValue());
                    ErrorMessage.setText("No Errors.");
                }
                else
                {
                    ErrorMessage.setText("That Value was not a monetary value, you may have a maximum of 2 decimal places! Try Again.");
                }
            }
        });

        InventoryTable.setItems(TableList);
    }

    @FXML
    public void SearchBar(KeyEvent keyEvent) {

        if(SearchTerm.getText() == null)
        {
            InventoryTable.setItems(TableList);
        }
        else
        {
            ObservableList<InventoryItem> SearchResult = new InventorySearch(TableList).SearchList(SearchTerm.getText());

            if (SearchResult.size() > 0) {
                InventoryTable.setItems(SearchResult);
                ErrorMessage.setText("No Errors.");
            }
            else
            {
                InventoryTable.setItems(SearchResult);
                ErrorMessage.setText("No matches found!");
            }
        }
    }

    @FXML
    public void SaveClicked(ActionEvent actionEvent) {

        File file = fileChooser.showSaveDialog(new Stage());

        FileManager manager = new FileManager();
        manager.SaveFile(file, TableList);
    }

    @FXML
    public void LoadClicked(ActionEvent actionEvent) {

        File file = fileChooser.showOpenDialog(new Stage());

        FileManager manager = new FileManager();
        TableList = manager.LoadFile(file);
        InventoryTable.setItems(TableList);
    }

    @FXML
    public void CloseClicked(ActionEvent actionEvent) {

        Stage stage = (Stage) MainScene.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void AddClicked(ActionEvent actionEvent) {

        String name = AddName.getText();
        String serialNumber = AddSerialNumber.getText();
        String value = AddValue.getText();

        addItem(name, serialNumber, value);
    }

    @FXML
    public void DeleteClicked(ActionEvent actionEvent) {

        InventoryItem SelectedItem = InventoryTable.getSelectionModel().getSelectedItem();
        deleteItem(InventoryTable.getItems().indexOf(SelectedItem));
    }

    @FXML
    public void ClearListClicked(ActionEvent actionEvent) {

        InventoryTable.getItems().clear();
    }

    //helper methods

    public void deleteItem(int i)
    {
        if(i <= TableList.size() && i > -1) {
            TableList.remove(i);
        }
    }

    public void addItem(String name, String serialNumber, String value)
    {
        if(validateSerialNumber(serialNumber) && validateName(name) && validateValue(value)) {
            addItemToTable(name, serialNumber, value);
            ErrorMessage.setText("No Errors.");
            AddValue.clear();
            AddSerialNumber.clear();
            AddName.clear();
        }
        else
        {
            //update error message based on error

            if (!validateSerialNumber(serialNumber))
            {
                ErrorMessage.setText("The Serial Number was not unique serial number in the format of XXXXXXXXXX where " +
                        "X can be either a letter or digit! Try Again.");

            }
            else if (!validateName(name))
            {
                if(name.length() > 256) {
                    ErrorMessage.setText("The Name was too long! Try Again.");
                }
                else {
                    ErrorMessage.setText("The Name was too short! Try Again.");
                }
            }
            else if(!validateValue(value))
            {
                ErrorMessage.setText("That Value was not a monetary value, you may have a maximum of 2 decimal places! Try Again.");
            }
        }
    }

    public void addItemToTable(String name, String serialNumber, String value) {
        TableList.add(new InventoryItem(name, serialNumber, "$"+ value));
    }

    public boolean validateSerialNumber(String serialNumber)
    {
        //sn null check
        if(serialNumber == null)
        {
            return false;
        }

        //compare sn for length
        if(serialNumber.length() != 8)
        {
            return false;
        }

        //compare sn for only numbers and letters
        if (!serialNumber.matches("[a-zA-Z_0-9]*"))
        {
            return false;
        }

        //compare sn for uniqueness
        for (InventoryItem inventoryItem : TableList) {
            if (serialNumber.equals(inventoryItem.getSerialNumber())) {
                return false;
            }
        }

        return true;
    }

    public boolean validateName(String name)
    {
        if(name == null)
        {
            return false;
        }

        if(name.length() < 2 || name.length() > 256)
        {
            return false;
        }

        return true;
    }

    public boolean validateValue(String value)
    {
        if(value == null)
        {
            return false;
        }

        if(!value.matches("[0-9]+\\.?[0-9]{2}?|[0-9]+\\.?[0-9]"))
        {
            return false;
        }

        return true;
    }

}
