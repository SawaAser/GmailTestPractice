package api.test;

import api.test.models.User;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.*;

public class UsersService {
    private String endpoint = "https://jsonplaceholder.typicode.com/users";

    public Response getResponseUsers() {
        return given()
                .when()
                    .get(endpoint);
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

    public Response createUser(User user) {
        return given()
                    .contentType("application/json")
                    .body(user)
                .when()
                    .post(endpoint);
    }

    public Response deleteUserByID(int id) {
        return given()
                .when()
                    .delete(endpoint + "/" + id);
    }

    public Response putUserByID(int id, User user) {
        return given()
                    .body(user)
                .when()
                    .put(endpoint + "/" + id);
    }
}
