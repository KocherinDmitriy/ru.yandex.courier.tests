import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


public class ListOfOrdersTest {

    @Test
    public void getListOfOrders() {
        Response response=getListOfOrdersStep();
        response.then().assertThat().body("orders",notNullValue()).and()
                .statusCode(200);
    }

    @Step("Send POST request to /api/v1/orders")
    public Response getListOfOrdersStep(){
        Response response =given()
                .header("Content-type", "application/json")
                .and()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders");
        return response;
    }

}

