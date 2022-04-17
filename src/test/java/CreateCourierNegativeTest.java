import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class CreateCourierNegativeTest {

    private final String login;
    private final String password;
    private final String firstName;
    private final String field;
    private final String message;
    private final int code;

    public CreateCourierNegativeTest(String login, String password,String firstName,String field,String message,int code) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.field = field;
        this.message = message;
        this.code = code;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getData() {
        return new Object[][] {
                { "","aasdad","asdasd","message", "\u041D\u0435\u0434\u043E\u0441\u0442\u0430\u0442\u043E\u0447\u043D\u043E \u0434\u0430\u043D\u043D\u044B\u0445 \u0434\u043B\u044F \u0441\u043E\u0437\u0434\u0430\u043D\u0438\u044F \u0443\u0447\u0435\u0442\u043D\u043E\u0439 \u0437\u0430\u043F\u0438\u0441\u0438",400},
                { "aasdad","","asdasd","message", "\u041D\u0435\u0434\u043E\u0441\u0442\u0430\u0442\u043E\u0447\u043D\u043E \u0434\u0430\u043D\u043D\u044B\u0445 \u0434\u043B\u044F \u0441\u043E\u0437\u0434\u0430\u043D\u0438\u044F \u0443\u0447\u0435\u0442\u043D\u043E\u0439 \u0437\u0430\u043F\u0438\u0441\u0438",400},
                { "123124544","aasdad","asdasd","message", "\u042D\u0442\u043E\u0442 \u043B\u043E\u0433\u0438\u043D \u0443\u0436\u0435 \u0438\u0441\u043F\u043E\u043B\u044C\u0437\u0443\u0435\u0442\u0441\u044F. \u041F\u043E\u043F\u0440\u043E\u0431\u0443\u0439\u0442\u0435 \u0434\u0440\u0443\u0433\u043E\u0439.",409},

        };
    }
    @DisplayName("Negative tests /api/v1/courier") // имя теста
    @Description("No Login,No Password,User Already in use ") // описание теста
    @Test
    public void createNewCourierWithBusinessErrors() {

        Response response = sendPostRequestCreateCourier(login,password,firstName);
        response.prettyPrint();
        compareResponse(response,field, message);
        response.then().statusCode(code);
    }


    @Step("Send POST request to /api/v1/courier")
    public Response sendPostRequestCreateCourier(String courierLogin,String courierPassword,String courierFirstName){
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
    public void compareResponse(Response response, String field,String message ){
        response.then().assertThat().body(field, equalTo(message));
    }


}
