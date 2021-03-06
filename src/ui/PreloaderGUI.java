package ui;

import exceptions.IDAlreadyInUseException;
import exceptions.UsernameAlreadyInUseException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Employee;
import model.ParkingLot;
import model.PreloaderBar;
import threads.PreloaderThread;
import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PreloaderGUI implements Initializable {
    private final PreloaderBar bar;
    boolean isLoaded;
    MainGUI controller;
    ParkingLot laCeiba;

    private static final String SAVE_PATH = "data/Serializable/object/data.1jz";

    @FXML
    private BorderPane splashPane = new BorderPane();

    @FXML
    private Label progressLBL = new Label();

    @FXML
    private Rectangle pBarRCT;

    /**
     * The main constructor of the class. <br>
     * */
    public PreloaderGUI(ParkingLot laCeiba) {
        this.laCeiba = laCeiba;
        bar = new PreloaderBar();
        isLoaded = false;
        controller = new MainGUI(laCeiba);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bar.setActive(true);
        new PreloaderThread(bar, this).start();
    }

    /**
     * Updates the rectangle bar's width, i.e. loads it. <br>
     * @throws InterruptedException Thrown when the thread can't sleep. <br>
     * */
    public void loadBar() throws InterruptedException  {
        double newWidth = bar.getBarWidth();
        pBarRCT.setWidth(newWidth);
        double percentage = (newWidth / bar.LOADED_WIDTH) * 100;
        DecimalFormat format = new DecimalFormat("#.00");
        progressLBL.setText("Cargando... (" + format.format(percentage) + "% )");
        if (percentage >= 65.0 && !isLoaded) {
            isLoaded = true;
        }
    }




    /**
     * Stops the bar loading and launches the app. <br>
     * */
    public void postLoaded() {
        bar.setActive(false);
        ((Stage) pBarRCT.getScene().getWindow()).close();
        controller.setCURRENT_PREF_MIN(352);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/main-view.fxml"));
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Image icon = new Image(String.valueOf(getClass().getResource("resources/icon.png")));
            stage.getIcons().add(icon);
            stage.setTitle("La Ceiba: Inicio");
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
