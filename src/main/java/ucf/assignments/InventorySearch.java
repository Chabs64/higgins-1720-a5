/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Chad Higgins
 *
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventorySearch {

    private ObservableList<InventoryItem> TableList = FXCollections.observableArrayList();
    private ObservableList<InventoryItem> ResultList = FXCollections.observableArrayList();

    public InventorySearch(ObservableList<InventoryItem> List)
    {
        this.TableList = List;
    }

    public ObservableList<InventoryItem> SearchList(String SearchTerm)
    {
        for (InventoryItem inventoryItem : TableList) {

            if (inventoryItem.getName().contains(SearchTerm)) {
                ResultList.add(inventoryItem);
            } else if (inventoryItem.getSerialNumber().contains(SearchTerm)) {
                ResultList.add(inventoryItem);
            }
        }

        return ResultList;
    }
}
