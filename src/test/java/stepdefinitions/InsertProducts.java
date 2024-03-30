package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class InsertProducts {

    public RequestSpecification httpRequest;
    public Response response;
    public ResponseBody body;
    public int responseCode;
    public JSONObject requestParams;
    public JsonPath jsnpath;

    @Given("I hit the url of post products api endpoint")
    public void i_hit_the_url_of_post_products_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = given();
        requestParams = new JSONObject();

    }

    @When("I pass the request body of product title {}")
    public void i_pass_the_request_body_of_product_title(String shoes) {
        requestParams.put("title", shoes);
        requestParams.put("price", "10.95");
        requestParams.put("description", "Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        requestParams.put("image", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        requestParams.put("category", "clothing");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.post("products");
        body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Then("Verify the status code for post request is {int}")
    public void verify_the_status_code_for_post_request_is(int code) {
        responseCode = response.getStatusCode();
        assertEquals(responseCode, code);
    }


    @And("I receive the response body with id as {int}")
    public void i_receive_the_response_body_with_id_as(Integer id) {
        //Jason Representation from Response body
        jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("id").toString();
        assertEquals(id.toString(), s);
    }
}
