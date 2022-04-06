package sk.fiit.trusau.project2;

public class Links {
    private String loginPage;
    private String mainPage;
    private String signUpPage;
    private String userPage;
    private String adminPage;

    private String loginTitle;
    private String mainTitle;
    private String signUpTitle;
    private String userTitle;
    private String adminTitle;


    public static String getAdminPage() {
        return "/admin-page.fxml";
    }

    public static String getAdminTitle() {
        return "Admin Page";
    }

    public static String getLoginTitle() {
        return "Login Page";
    }

    public static String getMainTitle() {
        return "Main Page";
    }

    public static String getSignUpTitle() {
        return "Sign Up Page";
    }

    public static String getUserTitle() {
        return "User Page";
    }

    public static String getLoginPage() {
        return "/login-page.fxml";
    }

    public static String getMainPage() {
        return "/main-page.fxml";
    }

    public static String getSignUpPage() {
        return "/sign-up.fxml";
    }

    public static String getUserPage() {
        return "/user-page.fxml";
    }



}
