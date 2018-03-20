package domain.account;

import org.mindrot.jbcrypt.BCrypt;
import utils.Pair;

public class PassHashingStrategyImpl implements IPassHashingStrategy {

    @Override
    public Pair<String, String> hashPassword(String password) {
        String salt = BCrypt.gensalt();
        String hashed = BCrypt.hashpw(password, salt);
        return new Pair<>(hashed, salt);
    }

    @Override
    public boolean passwordEquals(String password, String salt, String hashedPass) {
        return BCrypt.hashpw(password, salt).equals(hashedPass);
    }

}
