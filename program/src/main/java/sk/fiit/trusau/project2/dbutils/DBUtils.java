package sk.fiit.trusau.project2.dbutils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sk.fiit.trusau.project2.Links;
import sk.fiit.trusau.project2.SingletonUserInfo;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) { // enter the application
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // switch to the login page and signup page
            try {
                root = FXMLLoader.load(Objects.requireNonNull(DBUtils.class.getResource(fxmlFile)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String fxmlFile, String title, String userName, String password, Integer amount_of_money, Integer role) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            psCheckUserExist = connection.prepareStatement("SELECT user_name FROM user_property WHERE user_name = ?");
            psCheckUserExist.setString(1, userName);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO user_property (user_name, user_password, amount_of_money, role) VALUES (?,?,?,?)");
                psInsert.setString(1, userName);
                psInsert.setString(2, password);
                psInsert.setInt(3, amount_of_money);
                psInsert.setInt(4, role);
                psInsert.executeUpdate();

                changeScene(event, fxmlFile, title, userName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);

            closePreparedStatement(psCheckUserExist);
            closePreparedStatement(psInsert);
            closeConnection(connection);
        }
    }

    public static void logInUser(ActionEvent event, String userName, String password) { // maybe add everything else
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user_property WHERE user_name = '" + userName + "'");

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User not found");
                alert.show();
            } else {
                while (resultSet.next()) {
                    SingletonUserInfo.getInstance().setUserId(resultSet.getInt(("user_id")));
                    SingletonUserInfo.getInstance().setUserName(resultSet.getString("user_name"));
                    String user_password = resultSet.getString("user_password");
                    SingletonUserInfo.getInstance().setAmount_of_money(resultSet.getInt("amount_of_money"));
                    SingletonUserInfo.getInstance().setRole(resultSet.getInt("role"));

                    if (user_password.equals(password)) {
                        if (SingletonUserInfo.getInstance().getRole() == 2) {
                            changeScene(event, Links.getAdminPage(), Links.getAdminTitle(), userName);
                        } else {
                            changeScene(event, Links.getMainPage(), Links.getMainTitle(), userName);
                        }
                    } else {
                        System.out.println("incorrect password in login page");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Incorrect password");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    public static int getUserId(String userName) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user_property");


            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String user_name = resultSet.getString("user_name");

                if (user_name.equals(userName)){
                    return user_id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }
        return -1;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "root");
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

    private static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
