package edu.syr.cyberseed.sage.integrationclient.entities;

import java.lang.String;

public class Doctor {

//    private static final long serialVersionUID = -3009157732241241604L;

    private String username;

    private String pname;

    private String paddress;

    private String rphrase;

    //private Set<User> username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {this.username = username;}

    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {this.pname = pname;}

    public String getPaddress() { return paddress; }
    public void setPaddress (String paddress) {this.paddress = paddress;}

    public String getRphrase() {
        return rphrase;
    }
    public void setRphrase(String rphrase) {this.rphrase = rphrase;}
    //@ManyToMany(mappedBy = "usernames")
    //public Set<User> getUsername() {
    //return username;
    //}

}