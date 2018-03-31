package tecnodart.com.offlineonline;

import java.io.Serializable;

/**
 * Created by kunal on 30/3/18.
 */

public class UserDetails implements Serializable {


    String userid;
    String name;
    String password;
    String mobileno;
    String auth;
    String emailid;
    String income;
    String cardType;
    String noOfMember;
    RationQuota rationQuota;

    public RationQuota getRationQuota() {
        return rationQuota;
    }

    public void setRationQuota(RationQuota rationQuota) {
        this.rationQuota = rationQuota;
    }

    public String getNoOfMember() {
        return noOfMember;
    }

    public void setNoOfMember(String noOfMember) {
        this.noOfMember = noOfMember;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public UserDetails() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
