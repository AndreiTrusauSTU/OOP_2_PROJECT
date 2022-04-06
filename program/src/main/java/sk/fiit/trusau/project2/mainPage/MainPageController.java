package sk.fiit.trusau.project2.mainPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sk.fiit.trusau.project2.Links;
import sk.fiit.trusau.project2.dbutils.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private Button mainPage_button_proffile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPage_button_proffile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, Links.getUserPage(),Links.getUserTitle(), "a");
            }
        });
    }
}
