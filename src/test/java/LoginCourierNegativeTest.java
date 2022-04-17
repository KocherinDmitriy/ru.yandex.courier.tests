import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class LoginCourierNegativeTest {

    private final String login;
    private final String password;
    private final String field;
    private final String message;
    private final int code;

    public LoginCourierNegativeTest(String login, String password,String field,String message,int code) {
        this.login = login;
        this.password = password;
        this.field = field;
        this.message = message;
        this.code = code;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getData() {
        return new Object[][] {
                { "","aasdad","message", "Недостаточно данных для входа",400},
                { "aasdad","","message", "Недостаточно данных для входа",400},
                { "123124544","asdasd","message", "Учетная запись не найдена",404},

        };
    }
    @DisplayName("Negative tests /api/v1/courier/login") // имя теста
    @Description("No Login,No Password,User Not Found ") // описание теста
    @Test
    public void loginCourierWithBusinessErrors() {

        Response response = sendPostRequestLoginCourier(login,password);
        response.prettyPrint();
        compareResponse(response,field, message);
        response.then().statusCode(code);
    }

    @Step("Send POST request to /api/v1/courier")
    public Response sendPostRequestLoginCourier(String courierLogin,String courierPassword){
        RequestSpecification given = given();
        given.header("Content-type", "application/json");
        given.and();
        given.body("{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\"}");
        given.when();
        Response response = given
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");;
        return response;
    }

    // метод для шага "Сравнить ответ от сервера":
    @Step("Compare answer to server")
    public void compareResponse(Response response, String field,String message ){
        response.then().assertThat().body(field, equalTo(message));
    }


}
