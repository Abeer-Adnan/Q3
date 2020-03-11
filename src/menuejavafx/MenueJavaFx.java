/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuejavafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author rant
 */
public class MenueJavaFx extends Application {

    TextArea tafc;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("File View ");
        MenuBar menubar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem MenuO = new MenuItem("Open");
        MenuItem MenuC = new MenuItem("Close");
        MenuItem MenuE = new MenuItem("Exit");
        menuFile.getItems().addAll(MenuO, MenuC, MenuE);

        Menu menuEdit = new Menu("Edit");
        MenuItem MenuF = new MenuItem("Font");
        MenuItem MenuCo = new MenuItem("Color");
        menuEdit.getItems().addAll(MenuF, MenuCo);

        menubar.getMenus().addAll(menuFile, menuEdit);
        tafc = new TextArea("Initial text");
        Menueee me = new Menueee();
        MenuO.setOnAction(me);
        MenuC.setOnAction(me);
        MenuE.setOnAction(me);
        MenuF.setOnAction(me);
        MenuCo.setOnAction(me);
        VBox vBox = new VBox(menubar, tafc);
        Scene scene = new Scene(vBox, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private class Menueee implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String menuItem = ((MenuItem) event.getSource()).getText();
            switch (menuItem) {
                case "Open":
                    FileChooser filech = new FileChooser();
                    filech.setTitle("file selection");
                    filech.setInitialDirectory(new File("."));
                    File file = filech.showOpenDialog(null);
                    tafc.setText("");
                    tafc.setWrapText(true);

                    try {
                        Scanner s = new Scanner(file);
                        while (s.hasNext()) {
                            tafc.appendText(s.nextLine());
                        }
                        s.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MenueJavaFx.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case "Close":
                    tafc.clear();
                    break;
                case "Exit":
                    System.exit(0);
                    break;
                case "Font":
                    Dialog<String> dFont = new ChoiceDialog<>("10px", FXCollections.observableArrayList("10px", "15px", "20px"));

                    dFont.setTitle("Size Selection");
                    dFont.setHeaderText("select the Size from list");
                    dFont.setContentText("Avalibal Size");
                    String size = dFont.showAndWait().get();
                    tafc.setStyle("-fx-font-size:" + size + ";");
                    break;
                case "Color":
                    Dialog<String> dColor = new ChoiceDialog<>("Red", FXCollections.observableArrayList("Blue", "Green", "Red"));
                    dColor.setTitle("Color Selection");
                    dColor.setHeaderText("select the Color from list");
                    dColor.setContentText("Avalibal Color");
                    String color = dColor.showAndWait().get();
                    tafc.setStyle("-fx-text-fill:" + color + ";");
                    break;
            }
        }

    }
}
