import api.utils.CourerClient;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest {
    @Test
    public void courierLogin() {
        Response response = CourerClient.sendPostRequestTolLogin();
        response.then().assertThat().body("id", notNullValue()).and()
                .statusCode(200);
    }

}

