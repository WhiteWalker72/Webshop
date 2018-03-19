package domain.account;

import java.util.Date;

public class Account {
    private final int accountID;
    private final Date startDate;
    private final int isActive;
    private final String username;
    private final String password;
    private final String salt;
    private int customerId;

    public Account(int accountID, Date startDate, int isActive, String username, String password, int customerId, String salt) {
        this.accountID = accountID;
        this.startDate = startDate;
        this.isActive = isActive;
        this.username = username;
        this.password = password;
        this.customerId = customerId;
        this.salt = salt;
    }

    public Account(int accountID, Date startDate, int isActive, String username, String password, String salt) {
        this.accountID = accountID;
        this.startDate = startDate;
        this.isActive = isActive;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public int getAccountID() {
        return accountID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getSalt() {
        return salt;
    }
}
