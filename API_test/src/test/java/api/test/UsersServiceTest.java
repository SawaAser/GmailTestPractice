package api.test;

import api.test.models.Adderss;
import api.test.models.Company;
import api.test.models.User;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

public class UsersServiceTest {
    private UsersService usersService = new UsersService();

    @Test
    public void testGet() {
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
    public void testSizeBody() {
        usersService.getResponseUsers()
                .then()
                .assertThat()
                .body("size()", equalTo(10));
    }

    @Test
    public void testUserById() {
        User expectedUser = createExpectedUser();
        User actualUser = usersService.getUserByID(2);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testPostUser() {
        User testUser = createTestUser();

        usersService.createUser(testUser)
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void testDeleteUser() {
        usersService.deleteUserByID(2)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testPutUser() {
        usersService.putUserByID(3, createTestUser(3))
                .then()
                .assertThat()
                .statusCode(200);
    }

    private User createExpectedUser() {
        return new User(
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
    }


    private User createTestUser() {
        return createTestUser(11);
    }

    private User createTestUser(int id) {
        return new User(
                id,
                "Ervin Howell",
                "Antonette",
                "Shanna@melissa.tv",
                createTestAddress(),
                "010-692-6593 x09125",
                "anastasia.net",
                createTestCompany()
        );
    }

    private Adderss createTestAddress() {
        return new Adderss(
                "Victor Plains",
                "Suite 879",
                "Wisokyburgh",
                "90566-7771",
                new Adderss.Geo(-43.9509, -34.4618)
        );
    }

    private Company createTestCompany() {
        return new Company(
                "Deckow-Crist",
                "Proactive didactic contingency",
                "synergize scalable supply-chains"
        );
    }
}
