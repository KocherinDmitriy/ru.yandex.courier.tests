package api.utils;

import api.ScooterRegisterCourier;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourerClient {

    @Step("Send POST request to /api/v1/courier")
    public static Response sendPostRequestCreateCourier(String courierLogin, String courierPassword, String courierFirstName) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"login\":\"" + courierLogin + "\","
                        + "\"password\":\"" + courierPassword + "\","
                        + "\"firstName\":\"" + courierFirstName + "\"}")
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier");
        ;
        return response;
    }

    // метод для шага "Сравнить ответ от сервера":
    @Step("Compare answer to server")
    public static void compareResponse(Response response, String field, String message) {
        response.then().assertThat().body(field, equalTo(message));
    }

    //метод для шага "Сравнить ответ от сервера" с булевым ответом:
    @Step("Compare answer to server")
    public static void compareResponseWithBollean(Response response, String field, Boolean message) {
        response.then().assertThat().body(field, equalTo(message));
    }

    //Метод для авторизации курьера
    @Step("Send POST request to /api/v1/courier/login")
    public static Response sendPostRequestTolLogin() {
        ScooterRegisterCourier courier = new ScooterRegisterCourier();
        ArrayList<String> loginPass = courier.registerNewCourierAndReturnLoginPassword();
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"login\":\"" + loginPass.get(0) + "\","
                        + "\"password\":\"" + loginPass.get(1) + "\"}")
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        ;
        return response;
    }

    @Step("Send POST request to /api/v1/courier")
    public static Response sendPostRequestLoginCourier(String courierLogin, String courierPassword) {
        RequestSpecification given = given();
        given.header("Content-type", "application/json");
        given.and();
        given.body("{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\"}");
        given.when();
        Response response = given
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        ;
        return response;
    }

}
