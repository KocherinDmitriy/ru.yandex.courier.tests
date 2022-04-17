import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Date;
import java.util.GregorianCalendar;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String colorNew;
    private final int station;

    public CreateOrderTest( int station,String colorNew) {
        this.colorNew = colorNew;
        this.station = station;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getData() {
        return new Object[][] {
                {1, "BLACK"},
                {2, "GREY"},
                {2, "GREY,BLACK"},
                {2, ""},
        };
    }

    @Test
    public void createNewNewOrder() {
        Response response = sendPostRequestCreateCourier(station,colorNew);
        response.prettyPrint();
        response.then().assertThat().body("track",notNullValue()).and().statusCode(201);

    }


    @Step("Send POST request to /api/v1/orders")
    public Response sendPostRequestCreateCourier(Integer metroStation,String color) {
        String firstName = RandomStringUtils.randomAlphabetic(10);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String address = RandomStringUtils.randomAlphabetic(10);
        String comment = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomNumeric(10);
        String rentTime= RandomStringUtils.randomNumeric(2);
        String registerOrderRequestBody =
                "{\"firstName\":\"" + firstName + "\","
                        + "\"lastName\":\"" + lastName + "\","
                        + "\"address\":\"" + address + "\","
                       + "\"metroStation\":\"" + metroStation + "\","
                        + "\"phone\":\"" + phone + "\","
                        + "\"rentTime\":\"" + rentTime + "\","
                        + "\"deliveryDate\":\"" + generateRandomDate() + "\","
                        + "\"comment\":\"" + comment + "\","
                       + "\"color\":[\""+color+"\"]}";
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(registerOrderRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/orders");
        ;
        return response;
    }


    //Генерация даты
    public String generateRandomDate() {
        long time = System.currentTimeMillis();
        Date dat = new Date(time);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return format.format(gc.getTime());
    }


}



