import api.utils.CourerClient;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class CreateCourierTest {

    @Test
    public void createNewCourierWithRandomLogPass() {
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);

        Response response = CourerClient.sendPostRequestCreateCourier(courierLogin, courierPassword, courierFirstName);
        response.prettyPrint();
        CourerClient.compareResponseWithBollean(response, "ok", true);
        response.then().statusCode(201);

    }
}


