package domain.account;

import domain.account.IPassHashingStrategy;
import domain.account.PassHashingStrategyImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mindrot.jbcrypt.BCrypt;
import utils.Pair;

import static org.junit.jupiter.api.Assertions.*;

class PassHashingStrategyTest {

    private IPassHashingStrategy hashingStrategy;

    @BeforeEach
    void init() {
        hashingStrategy = new PassHashingStrategyImpl();
    }

    @ParameterizedTest
    @ValueSource(strings = {"password", "Test1235", "hMsJWXlx5Yq8"})
    void testPasswordNotEqualToHashed(String password) {
        assertNotEquals(hashingStrategy.hashPassword(password).getLeft(), password);
    }

    @ParameterizedTest
    @ValueSource(strings = {"password", "Test1235", "hMsJWXlx5Yq8"})
    void testPassEquals(String password) {
        Pair<String, String> hashedPair = hashingStrategy.hashPassword(password);
        System.out.println(hashedPair.getLeft());
        System.out.println(BCrypt.);
        assertTrue(hashingStrategy.passwordEquals(password, hashedPair.getRight(), hashedPair.getLeft()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"password", "Test1235", "hMsJWXlx5Yq8"})
    void testPassNotEquals(String password) {
        Pair<String, String> hashedPair = hashingStrategy.hashPassword(password);
        assertFalse(hashingStrategy.passwordEquals(password + "1", hashedPair.getRight(), hashedPair.getLeft()));
    }

    @AfterEach
    void tearDown() {
        hashingStrategy = null;
    }

}
