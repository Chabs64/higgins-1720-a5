/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Chad Higgins
 *
 */

/*
Your application shall satisfy the following requirements:

1. The user shall interact with the application through a Graphical User Interface

2. The user shall be able to store at least 100 inventory items
    2.1 Each inventory item shall have a value representing its monetary value in US dollars
    2.2 Each inventory item shall have a unique serial number in the format of XXXXXXXXXX where X can be either a letter or digit
    2.3 Each inventory item shall have a name between 2 and 256 characters in length (inclusive)

3. The user shall be able to add a new inventory item
    3.1 The application shall display an error message if the user enters an existing serial number for the new item

4. The user shall be able to remove an existing inventory item

5. The user shall be able to edit the value of an existing inventory item

6. The user shall be able to edit the serial number of an existing inventory item
    6.1 The application shall prevent the user from duplicating the serial number

7. The user shall be able to edit the name of an existing inventory item

8. The user shall be able to sort the inventory items by value

9. The user shall be able to sort inventory items by serial number

10. The user shall be able to sort inventory items by name

11. The user shall be able to search for an inventory item by serial number

12. The user shall be able to search for an inventory item by name

13. The user shall be able to save their inventory items to a file
    13.1 The user shall be able to select the file format from among the following set of options: TSV (tab-separated value), HTML, JSON
        13.1.1 TSV files shall shall list one inventory item per line, separate each field within an inventory item using a tab character,
         and end with the extension .txt
        13.1.2 HTML files shall contain valid HTML and end with the extension .html
        13.1.3 JSON files shall contain valid JSON and end with the extension .json
    13.2 The user shall provide the file name and file location of the file to save

14. The user shall be able to load inventory items from a file that was previously created by the application.
    14.1 The user shall provide the file name and file location of the file to load

Bonus:

The requirement to save/load an inventory list in JSON format is optional.
Implementation of this requirement will yield an extra 2 points on this assignment.
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryItemTest {

    @Test
    void setNameTest() {

        InventoryItem item = new InventoryItem("bob", "12345678", "1000");
        item.setName("cat");

        assertEquals("cat", item.getName());
    }

    @Test
    void getNameTest() {

        InventoryItem item = new InventoryItem("bob", "12345678", "1000");

        assertEquals("bob", item.getName());
    }

    @Test
    void setSerialNumberTest() {

        InventoryItem item = new InventoryItem("bob", "12345678", "1000");
        item.setSerialNumber("12345679");

        assertEquals("12345679", item.getSerialNumber());
    }

    @Test
    void getSerialNumberTest() {

        InventoryItem item = new InventoryItem("bob", "12345678", "1000");

        assertEquals("12345678", item.getSerialNumber());
    }

    @Test
    void setValueTest() {

        InventoryItem item = new InventoryItem("bob", "12345678", "1000");
        item.setValue("10000");

        assertEquals("10000", item.getValue());
    }

    @Test
    void getValueTest() {


        InventoryItem item = new InventoryItem("bob", "12345678", "1000");

        assertEquals("1000", item.getValue());
    }
}