package api.test;

import api.test.models.Adderss;
import api.test.models.Company;
import api.test.models.User;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersServiceTest {
    private UsersService usersService = new UsersService();

    @Test
    public void testStatusCode() {
        usersService.getResponseUsers()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testHeader() {
        usersService.getResponseUsers()
                .then()
                .assertThat()
                .header("Content-Type", notNullValue())
                .header("Content-Type", equalTo("application/json; charset=utf-8"));
    }

    @Test
    public void testBody() {
        usersService.getResponseUsers()
                .then()
                .assertThat()
                .body("size()", equalTo(10));
    }

    @Test
    public void printUserById() {
        User expectedUser = new User(
                2,
                "Ervin Howell",
                "Antonette",
                "Shanna@melissa.tv",
                new Adderss(
                        "Victor Plains",
                        "Suite 879",
                        "Wisokyburgh",
                        "90566-7771",
                        new Adderss.Geo(
                                -43.9509,
                                -34.4618
                        )
                ),
                "010-692-6593 x09125",
                "anastasia.net",
                new Company(
                        "Deckow-Crist",
                        "Proactive didactic contingency",
                        "synergize scalable supply-chains"
                )
        );

        User actualUser = usersService.getUserByID(2);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testListUsers() {
        assertEquals(10, usersService.getListUsers().size());
    }
}
