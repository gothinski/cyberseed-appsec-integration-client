package edu.syr.cyberseed.sage.integrationclient.entities;

import java.util.Date;
import java.util.Set;


public class User_patient {


    private String username;

    private String password;

    private String fname;

    private String lname;

    private String roles;

    private String custom_permissions_to_add;

    private String custom_permissions_to_remove;

        private Date dob;
        private int ssn;
        private String address;
        String permissions="None";
        public String getUsername() {
            return username;
        }
    public String getPermissions() {
        return permissions;
    }
        public void setUsername(String username) {this.username = username;}

        public Date getDob() { return dob; }
        public void setDob(Date dob) {this.dob = dob;}

        public Integer getSsn() { return ssn; }

        public void setSsn(Integer ssn) {this.ssn = ssn;}

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {this.address = address;}


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getCustom_permissions_to_add() {
        return custom_permissions_to_add;
    }

    public void setCustom_permissions_to_add(String custom_permissions_to_add) {
        this.custom_permissions_to_add = custom_permissions_to_add;
    }

    public String getCustom_permissions_to_remove() {
        return custom_permissions_to_remove;
    }

    public void setCustom_permissions_to_remove(String custom_permissions_to_remove) {
        this.custom_permissions_to_remove = custom_permissions_to_remove;
    }

}



