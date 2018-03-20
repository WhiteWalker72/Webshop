package domain.account;

import utils.Pair;

public interface IPassHashingStrategy {

    /**
     * @param password password that needs to be hashed
     * @return Pair with hashed password on the left and salt on the right
     */
    Pair<String, String> hashPassword(String password);

    boolean passwordEquals(String password, String salt, String hashedPass);

}
