package models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserModel {

    String email, name, password;
    Date creation_date;
    int privilege;

    public UserModel(String email, String name, String password, int privilege ) {
        this.email = email;
        this.name = name;
        this.password = password;
        //this.creation_date = creation_date;
        this.privilege = privilege;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Date getCreation_Date(Date creation_date) {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
