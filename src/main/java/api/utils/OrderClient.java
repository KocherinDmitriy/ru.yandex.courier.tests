package api.utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.given;

public class OrderClient {

    @Step("Send Get request to /api/v1/orders")
    public static Response getListOfOrdersStep() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders");
        return response;
    }
    @Step("Send POST request to /api/v1/orders")
    public static Response sendPostRequestCreateCourier(Integer metroStation, String color) {
        String firstName = RandomStringUtils.randomAlphabetic(10);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String address = RandomStringUtils.randomAlphabetic(10);
        String comment = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomNumeric(10);
        String rentTime = RandomStringUtils.randomNumeric(2);
        String registerOrderRequestBody =
                "{\"firstName\":\"" + firstName + "\","
                        + "\"lastName\":\"" + lastName + "\","
                        + "\"address\":\"" + address + "\","
                        + "\"metroStation\":\"" + metroStation + "\","
                        + "\"phone\":\"" + phone + "\","
                        + "\"rentTime\":\"" + rentTime + "\","
                        + "\"deliveryDate\":\"" + GenerateRandomDate.generateRandomDate() + "\","
                        + "\"comment\":\"" + comment + "\","
                        + "\"color\":[\"" + color + "\"]}";
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(registerOrderRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/orders");
        ;
        return response;
    }
}
