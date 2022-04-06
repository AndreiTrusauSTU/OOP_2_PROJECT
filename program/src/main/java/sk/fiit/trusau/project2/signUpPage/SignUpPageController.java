package sk.fiit.trusau.project2.signUpPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sk.fiit.trusau.project2.dbutils.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpPageController implements Initializable {

    @FXML
    public Button signupPage_button_login;

    @FXML
    public Button signupPage_button_signUp;

    @FXML
    public TextField signupPage_textField_username;

    @FXML
    public TextField signupPage_textField_password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signupPage_button_signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!signupPage_textField_username.getText().isEmpty() && !signupPage_textField_password.getText().isEmpty()){
                    DBUtils.signUpUser(event,"/main-page.fxml","Main page", signupPage_textField_username.getText(), signupPage_textField_password.getText(), 100, 1);
                } else {
                    System.out.println("fill all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("fill");
                    alert.show();
                }
            }
        });

        signupPage_button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/login-page.fxml", "Log in", null);
            }
        });
    }
}
