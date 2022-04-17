import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {

    @Test
    public void createNewCourierWithRandomLogPass() {

        Response response = sendPostRequestCreateCourier();
        compareResponse(response, "ok", true);
        response.then().statusCode(201);

    }


    @Step("Send POST request to /api/v1/courier")
    public Response sendPostRequestCreateCourier(){
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);
        Response response =given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"login\":\"" + courierLogin + "\","
                        + "\"password\":\"" + courierPassword + "\","
                        + "\"firstName\":\"" + courierFirstName + "\"}")
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier");;
        return response;
    }

    // метод для шага "Сравнить ответ от сервера":
    @Step("Compare answer to server")
    public void compareResponse(Response response, String field,Boolean message ){
        response.then().assertThat().body(field, equalTo(message));
    }


}


