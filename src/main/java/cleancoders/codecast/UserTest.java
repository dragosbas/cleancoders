package cleancoders.codecast;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void oneUserIsTheSameAsItself() throws Exception {
        User u1 = new User("u1");
        u1.setId("u1ID");
        assertTrue(u1.isSame(u1));
    }
    @Test
    public void usersWithTheSameIdAreTheSame() throws Exception{
        User u1 = new User("u1");
        User u2 = new User("u2");
        u1.setId("u1ID");
        u2.setId("u1ID");
        assertTrue(u1.isSame(u2));
    }
@Test
    public void twoDiffererentUsersAreNotTheSame() throws Exception{
        User u1 = new User("u1");
        User u2 = new User("u2");
        u1.setId("u1Id");
        u2.setId("uId");
        assertFalse(u1.isSame(u2));
    }
    @Test
    public void usersWithNullIdsAreNeverSame()throws Exception{
    User u1 = new User("u1");
    User u2 = new User("u2");
    assertFalse(u1.isSame(u2));
    }

}
