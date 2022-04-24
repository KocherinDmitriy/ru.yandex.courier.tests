import api.utils.OrderClient;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String colorNew;
    private final int station;

    public CreateOrderTest(int station, String colorNew) {
        this.colorNew = colorNew;
        this.station = station;
    }

    @Parameterized.Parameters (name = "{index} => station={0},colorNew={1}")// добавили аннотацию
    public static Object[][] getData() {
        return new Object[][]{
                {1, "BLACK"},
                {2, "GREY"},
                {2, "GREY,BLACK"},
                {2, ""},
        };
    }

    @Test
    public void createNewNewOrder() {
        Response response = OrderClient.sendPostRequestCreateCourier(station, colorNew);
        response.prettyPrint();
        response.then().assertThat().body("track", notNullValue()).and().statusCode(201);

    }

}



