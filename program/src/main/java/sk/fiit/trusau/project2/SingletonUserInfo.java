package sk.fiit.trusau.project2;

public final class SingletonUserInfo {

    private static SingletonUserInfo singletonUserInfo = null;

    public SingletonUserInfo(Integer userId, String userName, String password, Integer amount_of_money, Integer role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.amount_of_money = amount_of_money;
        this.role = role;
    }

    public static SingletonUserInfo getInstance() {
        if (singletonUserInfo == null)
            singletonUserInfo = new SingletonUserInfo(null, null, null, null, null);

        return singletonUserInfo;
    }

    private Integer userId;
    private String userName;
    private String password;
    private Integer amount_of_money;
    private Integer role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAmount_of_money() {
        return amount_of_money;
    }

    public void setAmount_of_money(Integer amount_of_money) {
        this.amount_of_money = amount_of_money;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
