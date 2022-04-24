import api.utils.CourerClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class LoginCourierNegativeTest {

    private final String login;
    private final String password;
    private final String field;
    private final String message;
    private final int code;

    public LoginCourierNegativeTest(String login, String password, String field, String message, int code) {
        this.login = login;
        this.password = password;
        this.field = field;
        this.message = message;
        this.code = code;
    }

    @Parameterized.Parameters (name = "{index} => login={0}, password={1}, field={2},message={3},code={4}") // добавили аннотацию
    public static Object[][] getData() {
        return new Object[][]{
                {"", "aasdad1", "message", "Недостаточно данных для входа", 400},
                {"aasdad", "", "message", "Недостаточно данных для входа", 400},
                {"123124544", "asdasd", "message", "Учетная запись не найдена", 404},

        };
    }

    @DisplayName("Negative tests /api/v1/courier/login") // имя теста
    @Description("No Login,No Password,User Not Found ") // описание теста
    @Test
    public void loginCourierWithBusinessErrors() {

        Response response = CourerClient.sendPostRequestLoginCourier(login, password);
        response.prettyPrint();
        CourerClient.compareResponse(response, field, message);
        response.then().statusCode(code);
    }
}
