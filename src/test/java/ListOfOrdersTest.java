import api.utils.OrderClient;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.notNullValue;

public class ListOfOrdersTest {

    @Test
    public void getListOfOrders() {
        Response response = OrderClient.getListOfOrdersStep();
        response.then().assertThat().body("orders", notNullValue()).and()
                .statusCode(200);
    }
}

