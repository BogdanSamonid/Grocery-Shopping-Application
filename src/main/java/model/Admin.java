package model;

import javafx.scene.control.TextField;

public class Admin {

    private String username;
    private String ID;
    private String password;

    public Admin(String username, String ID, String password) {
        this.username=username;
        this.ID=ID;
        this.password=password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(TextField username) {
        this.username = username.getText();
    }

    public String getID() {
        return this.ID;
    }

    public void setID(TextField ID) {
        this.ID = ID.getText();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(TextField password) {
        this.password = password.getText();
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;

        Admin admin=(Admin) o;

        if(!username.equals(admin.username)) return false;
        if(!ID.equals(admin.ID)) return false;
        if(!password.equals(admin.password)) return false;

        return true;
    }

    @Override
    public int hashCode (){
        int result=username.hashCode();
        result=31*result+ID.hashCode();
        result=31*result+password.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "Admin -> " + username + ID + password;
    }
}

