package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    public Button browserBtn;
    public javafx.scene.control.TextField path;

    public ObservableList listViewData = FXCollections.observableArrayList();
    public ListView dublicateListView;


    public void browseFolder() {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Browse Directory");
        File defaultDirectory = new File("/");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(null);

        if (selectedDirectory != null) {
            path.setText(selectedDirectory.getPath());

            Map<String, String> filesMap = new HashMap<>();
            Map<String, String> dublicateFiles = new HashMap<>();
            try {
                Files.walk(Paths.get(selectedDirectory.getAbsolutePath())).forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        String fileMD5 = MD5Checksum.getMD5Checksum(filePath.toString());
                        if (filesMap.containsValue(fileMD5)) {
                            dublicateFiles.put(filePath.toString(), Utils.getKeyFromValue(filesMap, fileMD5));
                        } else {
                            filesMap.put(filePath.toString(), fileMD5);
                        }


                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            // print map to console
            Utils.printMap(dublicateFiles);

            // set dublicate objects to a list
            listViewData = Utils.getObservableItemsFromMap(dublicateFiles);
            dublicateListView.setItems(listViewData);


        }

    }
}
