/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Chad Higgins
 *
 */

package ucf.assignments;

public class InventoryItem {

    private String Name;
    private String SerialNumber;
    private String Value;

    public InventoryItem(String name, String serialNumber, String value)
    {
        setName(name);
        setSerialNumber(serialNumber);
        setValue(value);
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setSerialNumber(String serialNumber) {
        this.SerialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setValue(String value) {
        this.Value = value;
    }

    public String getValue() {
        return Value;
    }
}
