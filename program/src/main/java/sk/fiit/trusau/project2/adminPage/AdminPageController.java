package sk.fiit.trusau.project2.adminPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import sk.fiit.trusau.project2.Links;
import sk.fiit.trusau.project2.dbutils.DBUtils;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    private String selectedUser;
    private String text;

    @FXML
    private SplitMenuButton adminPage_Button_splitMenu;

    @FXML
    private TextArea adminPage_TextArea;

    @FXML
    private Button adminPage_Button;

    @FXML
    private Button adminPage_Button_logOut;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListToSplitMenu();
        adminPage_Button_logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, Links.getLoginPage(), Links.getLoginTitle(), null);
            }
        });

        adminPage_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setText(adminPage_TextArea.getText());

                Connection connection = null;
                Statement statement = null;

                try {
                    connection = getConnection();
                    statement = connection.createStatement();
                    statement.executeUpdate("UPDATE user_property SET amount_of_money = " + getText() + " WHERE user_id = " + DBUtils.getUserId(getSelectedUser()) + "");
                } catch (SQLException e) {
                    System.out.println("chyba pri obnovoveni udajov v databaze");
                    e.printStackTrace();
                }
            }
        });

    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "root");
    }

    private void addListToSplitMenu() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT user_name FROM user_property");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("user_name");
                MenuItem menuItem = new MenuItem(name);
                adminPage_Button_splitMenu.getItems().add(menuItem);
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        setSelectedUser(name);
                    }
                });
            }
        } catch (SQLException e) {
            System.out.println("chyba pri stahovani user_name");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }
}
