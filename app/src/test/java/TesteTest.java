import com.gorest.test.tests.BaseTest;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class TesteTest extends BaseTest {
    private static final String LISTAR_TODOS_USUARIOS_ENDPOINT = "/users";
    private static final String ACESS_TOKEN = "b7bdc0485c1046c5906b92d052bd56f906a9e3120ccfc9c80682958325668fb3";

    @Test
    public void testBuscarId(){
       Response response = given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .param("id", 2574312)
                .when()
                .get(LISTAR_TODOS_USUARIOS_ENDPOINT)
                .then().statusCode(200).extract().response();
        List<Integer> idList = response.jsonPath().getList("id");
        if (idList != null && !idList.isEmpty()) {
            int id = idList.get(0);
            System.out.println(id);
            given()
                    .header("Authorization", "Bearer " + ACESS_TOKEN)
                    .param("id", id)
                    .when()
                    .get(LISTAR_TODOS_USUARIOS_ENDPOINT)
                    .then().statusCode(200).body("id", is(id));
        } else {
            System.out.println("O campo 'id' está nulo ou vazio na resposta da API.");
        }

    }

}
