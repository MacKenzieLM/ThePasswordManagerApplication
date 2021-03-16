package test.password.thepasswordmanagerapplication;

public class User {

    private String description;
    private String password;
    private String dateTime;


    public User(String description, String password, String dateTime) {
        this.description = description;
        this.password = password;
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
