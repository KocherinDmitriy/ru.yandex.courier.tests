import api.utils.CourerClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateCourierNegativeTest {

    private final String login;
    private final String password;
    private final String firstName;
    private final String field;
    private final String message;
    private final int code;

    public CreateCourierNegativeTest(String login, String password, String firstName, String field, String message, int code) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.field = field;
        this.message = message;
        this.code = code;
    }

    @Parameterized.Parameters (name = "{index} => login={0}, password={1}, firstName={2}, field={3}, message={4}, code={5}") // добавили аннотацию
    public static Object[][] getData() {
        return new Object[][]{
                {"", "aasdad1", "asdasd", "message", "\u041D\u0435\u0434\u043E\u0441\u0442\u0430\u0442\u043E\u0447\u043D\u043E \u0434\u0430\u043D\u043D\u044B\u0445 \u0434\u043B\u044F \u0441\u043E\u0437\u0434\u0430\u043D\u0438\u044F \u0443\u0447\u0435\u0442\u043D\u043E\u0439 \u0437\u0430\u043F\u0438\u0441\u0438", 400},
                {"aasdad", "", "asdasd", "message", "\u041D\u0435\u0434\u043E\u0441\u0442\u0430\u0442\u043E\u0447\u043D\u043E \u0434\u0430\u043D\u043D\u044B\u0445 \u0434\u043B\u044F \u0441\u043E\u0437\u0434\u0430\u043D\u0438\u044F \u0443\u0447\u0435\u0442\u043D\u043E\u0439 \u0437\u0430\u043F\u0438\u0441\u0438", 400},
                {"123124544", "aasdad", "asdasd", "message", "\u042D\u0442\u043E\u0442 \u043B\u043E\u0433\u0438\u043D \u0443\u0436\u0435 \u0438\u0441\u043F\u043E\u043B\u044C\u0437\u0443\u0435\u0442\u0441\u044F. \u041F\u043E\u043F\u0440\u043E\u0431\u0443\u0439\u0442\u0435 \u0434\u0440\u0443\u0433\u043E\u0439.", 409},

        };
    }

    @DisplayName("Negative tests /api/v1/courier") // имя теста
    @Description("No Login,No Password,User Already in use ") // описание теста
    @Test
    public void createNewCourierWithBusinessErrors() {

        Response response = CourerClient.sendPostRequestCreateCourier(login, password, firstName);
        response.prettyPrint();
        CourerClient.compareResponse(response, field, message);
        response.then().statusCode(code);
    }

}
