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

    public static void main(String[] args) {
        String password = "test";
        IPassHashingStrategy passHasher = new PassHashingStrategyImpl();
        Pair<String, String> hashPair = passHasher.hashPassword(password);
        System.out.println(hashPair.getLeft());
        System.out.println(hashPair.getRight());
        System.out.println(passHasher.passwordEquals("test", hashPair.getRight(), hashPair.getLeft()));
        System.out.println(passHasher.passwordEquals("testt", hashPair.getRight(), hashPair.getLeft()));
    }

}
