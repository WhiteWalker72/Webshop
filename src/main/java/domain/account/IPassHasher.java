package domain.account;

public interface IPassHasher {

    String hashPassword(String password);

    String unHashPassword(String hashedPassword);

}