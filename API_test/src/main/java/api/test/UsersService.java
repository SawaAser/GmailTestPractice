package api.test;

import api.test.models.User;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UsersService {
    private String endpoint = "https://jsonplaceholder.typicode.com/users";

    public Response getResponseUsers() {
        Response response = given()
                .when()
                .get(endpoint);
        return response;
    }

    public List<User> getListUsers() {
        return when()
                .get(endpoint)
                .jsonPath()
                .getList("", User.class);
    }

    public User getUserByID(int id) {
        return given()
                .queryParam("id", id)
                .when()
                .get(endpoint)
                .jsonPath()
                .getList("", User.class)
                .getFirst();
    }
}
