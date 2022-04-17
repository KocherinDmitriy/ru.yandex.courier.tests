import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


public class LoginCourierTest {
    @Test
    public void courierLogin() {
        Response response=sendPostRequestTolLogin();
        response.then().assertThat().body("id",notNullValue()).and()
                .statusCode(200);
    }   

    @Step("Send POST request to /api/v1/courier/login")
    public Response sendPostRequestTolLogin(){
        ScooterRegisterCourier courier=new ScooterRegisterCourier();
        ArrayList<String> loginPass= courier.registerNewCourierAndReturnLoginPassword();
        Response response =given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"login\":\"" + loginPass.get(0) + "\","
                        + "\"password\":\"" + loginPass.get(1) + "\"}")
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");;
        return response;
    }

    }

