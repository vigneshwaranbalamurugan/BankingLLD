package models;

public class Admin extends Person {
    private static Admin instance = null;

    private Admin(String userName, String password) {
        super(userName, password);
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin("admin", "admin123");
        }
        return instance;
    }
}
