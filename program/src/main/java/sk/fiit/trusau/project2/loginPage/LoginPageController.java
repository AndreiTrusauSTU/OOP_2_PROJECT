package sk.fiit.trusau.project2.loginPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sk.fiit.trusau.project2.Links;
import sk.fiit.trusau.project2.dbutils.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML
    public Button loginPage_button_login;

    @FXML
    public Button loginPage_button_signUp;

    @FXML
    public TextField loginPage_textField_username;

    @FXML
    public TextField loginPage_textField_password;

    //singlton

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginPage_button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event, loginPage_textField_username.getText(), loginPage_textField_password.getText());
            }
        });

        loginPage_button_signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, Links.getSignUpPage(), Links.getSignUpTitle(), null);
            }
        });
    }
}
