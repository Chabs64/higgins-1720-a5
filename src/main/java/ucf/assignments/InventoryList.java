/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Chad Higgins
 *
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.Observable;

public class InventoryList {

    /*
    + ObservableList<InventoryItem>

    + getList()
    + clearList()

    + addItem(Double, String, String)
    + editItemValue(Double)
    + editItemName(String)
    + editItemSerialNumber(String)
     */
    String name = "bob";
    String serialNumber = "bob";
    String value = "bob";


    ObservableList<InventoryItem> TableList = FXCollections.observableArrayList();

}
