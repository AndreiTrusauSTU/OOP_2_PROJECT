package sk.fiit.trusau.project2.userPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sk.fiit.trusau.project2.Links;
import sk.fiit.trusau.project2.SingletonUserInfo;
import sk.fiit.trusau.project2.dbutils.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {

    @FXML
    private Button userPage_button_returnToMain;

    @FXML
    private Button mainPage_button_logOut;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPage_button_logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, Links.getLoginPage(), Links.getLoginTitle(), null);
            }
        });

        userPage_button_returnToMain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, Links.getMainPage(), Links.getMainTitle(), SingletonUserInfo.getInstance().getUserName());
            }
        });
    }


}
