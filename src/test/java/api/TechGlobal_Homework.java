package api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TechGlobal_Homework {

    public static void main(String[] args) {

        Response response;
        Faker faker = new Faker();

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstName\": \""+faker.name().firstName()+"\",\n" +
                        "    \"lastName\": \""+faker.name().lastName()+"\",\n" +
                        "    \"email\": \""+faker.internet().emailAddress() +"\",\n" +
                        "    \"dob\": \"1990-01-26\"\n" +
                        "}")
                .when().post("https://tech-global-training.com/students")
                .then().log().all().extract().response();


        int getId = response.jsonPath().getInt("id");

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .when().get("https://tech-global-training.com/students/" + getId)
                .then().log().all().extract().response();

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstName\": \""+faker.name().firstName()+"\",\n" +
                        "    \"lastName\": \""+faker.name().lastName()+"\",\n" +
                        "    \"email\": \""+faker.internet().emailAddress() +"\",\n" +
                        "    \"dob\": \"1978-03-30\"\n" +
                        "}")
                .when().put("https://tech-global-training.com/students/" + getId)
                .then().log().all().extract().response();


        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstName\": \""+faker.name().firstName()+"\",\n" +
                        "    \"lastName\": \""+faker.name().lastName()+"\",\n" +
                        "    \"email\": \""+faker.internet().emailAddress() +"\",\n" +
                        "    \"dob\": \"1999-11-11\"\n" +
                        "}")
                .when().patch("https://tech-global-training.com/students/" + getId)
                .then().log().all().extract().response();

        response = RestAssured
                .given().log().all()
                .when().delete("https://tech-global-training.com/students")
                .then().log().all().extract().response();
    }
}