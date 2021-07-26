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

            printWriter.write("Value");
            printWriter.write("\t");
            printWriter.write("Serial Number");
            printWriter.write("\t");
            printWriter.write("Name");
            printWriter.write("\n");

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

        if(filepath != null)
        {
            if (ExtensionType(filepath.toString()) == 1) {
                List = HTMlLoad(filepath);
            }

            if (ExtensionType(filepath.toString()) == 2) {
                List = TSVLoad(filepath);
            }

        }

        return List;
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
                serialNumber = tabArray[1];
                name = tabArray[2];

                List.add(new InventoryItem(name, serialNumber, value));
            }

            List.remove(0);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return List;
    }

    private ObservableList<InventoryItem> HTMlLoad(File filepath) {
        ObservableList<InventoryItem> List = FXCollections.observableArrayList();

        String value = null;
        String name = null;
        String serialNumber = null;
        String line;

        try {
            Scanner scanner = new Scanner(filepath);

            while(scanner.hasNextLine())
            {
                line = scanner.nextLine();
                if(line.contains("<tr>"))
                {
                    for (int i = 0; i < 3; i++)
                    {
                        if (scanner.hasNextLine())
                        {
                            line = scanner.nextLine();
                            String[] htmlArray1 = line.split("<td>|<th scope=\"col\">");
                            String[] htmlArray2 = htmlArray1[1].split("</td>|</th>");

                            if (!htmlArray2[0].contains("</tr>")) {
                                if (i == 0) {
                                    value = htmlArray2[0];
                                } else if (i == 1) {
                                    serialNumber = htmlArray2[0];
                                } else if (i == 2) {
                                    name = htmlArray2[0];
                                }
                            }
                        }
                    }
                    List.add(new InventoryItem(name, serialNumber, value));
                }
            }

            List.remove(0);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return List;
    }
}
