package domain.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.Pair;

import static org.junit.jupiter.api.Assertions.*;

public class PassHashingStrategyTest {

    private IPassHashingStrategy hashingStrategy;

    @BeforeEach
    public void init() {
        hashingStrategy = new PassHashingStrategyImpl();
    }

    @ParameterizedTest
    @ValueSource(strings = {"password", "Test1235", "hMsJWXlx5Yq8"})
    public void testPasswordNotEqualToHashed(String password) {
        assertNotEquals(hashingStrategy.hashPassword(password).getLeft(), password);
    }

    @ParameterizedTest
    @ValueSource(strings = {"password", "Test1235", "hMsJWXlx5Yq8"})
    public void testPassEquals(String password) {
        Pair<String, String> hashedPair = hashingStrategy.hashPassword(password);
        assertTrue(hashingStrategy.passwordEquals(password, hashedPair.getRight(), hashedPair.getLeft()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"password", "Test1235", "hMsJWXlx5Yq8"})
    public void testPassNotEquals(String password) {
        Pair<String, String> hashedPair = hashingStrategy.hashPassword(password);
        assertFalse(hashingStrategy.passwordEquals(password + "1", hashedPair.getRight(), hashedPair.getLeft()));
    }

    @AfterEach
    public void tearDown() {
        hashingStrategy = null;
    }

}
