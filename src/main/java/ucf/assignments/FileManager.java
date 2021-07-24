/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Chad Higgins
 *
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {

    public FileManager(){}

    private int ExtensionType(String FilePath)
    {
        int i = 0;

        if (FilePath.contains(".html"))
        {
            i = 1;
        }

        if (FilePath.contains(".txt"))
        {
            i = 2;
        }

        return i;
    }

    public void SaveFile(File filepath, ObservableList<InventoryItem> list)
    {
        if (filepath != null) {

            if (ExtensionType(filepath.toString()) == 1) {
                HTMlSave(filepath, list);
            }

            if (ExtensionType(filepath.toString()) == 2) {
                TSVSave(filepath, list);
            }

        }
    }

    private void TSVSave(File filepath, ObservableList<InventoryItem> List)
    {

        try {
            PrintWriter printWriter = new PrintWriter(filepath);

            for (int i = 0; i < List.size(); i++) {
                printWriter.write(List.get(i).getValue());
                printWriter.write("\t");
                printWriter.write(List.get(i).getSerialNumber());
                printWriter.write("\t");
                printWriter.write(List.get(i).getName());
                printWriter.write("\n");
            }

            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void HTMlSave(File filepath, ObservableList<InventoryItem> List)
    {

        try {
            PrintWriter printWriter = new PrintWriter(filepath);

            //html header stuff
            printWriter.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "  <head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <title>Inventory Item List</title>\n" +
                    "  </head>\n" +
                    "  <body>");

            //table columns
            printWriter.write("<table>\n" +
                    "  <tr>\n" +
                    "    <th scope=\"col\">Value</th>\n" +
                    "    <th scope=\"col\">Serial Number</th>\n" +
                    "    <th scope=\"col\">Name</th>\n" +
                    "  </tr>");

            //table items
            String tableitem;

            for (int i = 0; i < List.size(); i++) {

                tableitem = String.format("<tr>\n" +
                                "<td>%s</td>\n" +
                                "<td>%s</td>\n " +
                                "<td>%s</td>\n" +
                                "</tr>",
                        List.get(i).getValue(), List.get(i).getSerialNumber(), List.get(i).getName());
                printWriter.write(tableitem);

                String value = List.get(i).getValue();
                String serial = List.get(i).getSerialNumber();
                String name = List.get(i).getName();
            }

            printWriter.write("</table>\n");

            printWriter.write("</body>\n" +
                    "</html>");

            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<InventoryItem> LoadFile(File filepath)
    {
        ObservableList<InventoryItem> List = FXCollections.observableArrayList();

        if (ExtensionType(filepath.toString()) == 1)
        {
            List = HTMlLoad(filepath);
        }

        if (ExtensionType(filepath.toString()) == 2)
        {
            List = TSVLoad(filepath);
        }

        if (List.size() > 0)
        {
            return List;
        }

        return null;
    }

    private ObservableList<InventoryItem> TSVLoad(File filepath) {
        ObservableList<InventoryItem> List = FXCollections.observableArrayList();

        String value;
        String name;
        String serialNumber;
        String line;

        try {
            Scanner scanner = new Scanner(filepath);

            while(scanner.hasNextLine())
            {
                line = scanner.nextLine();
                String[] tabArray = line.split("\t");

                value = tabArray[0];
                name = tabArray[1];
                serialNumber = tabArray[2];
                List.add(new InventoryItem(name, serialNumber, value));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return List;
    }

    private ObservableList<InventoryItem> HTMlLoad(File filepath) {
        ObservableList<InventoryItem> List = FXCollections.observableArrayList();


        return List;
    }
}
