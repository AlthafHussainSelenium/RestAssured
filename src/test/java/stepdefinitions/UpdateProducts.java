package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;

import static org.junit.Assert.assertEquals;

public class UpdateProducts {

    public RequestSpecification httpRequest;
    public Response response;
    public ResponseBody body;
    public int responseCode;
    public JSONObject requestParams;
    public JsonPath jsnpath;

    @Given("Hit the url of put products api endpoint")
    public void hit_the_url_of_put_products_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();
    }

    @When("Pass the url of the product in the request with {}")
    public void pass_the_url_of_the_product_in_the_request_with(String ProductName) {
        httpRequest = RestAssured.given();
        requestParams.put("title", "Test product");
        requestParams.put("price", "10.95");
        requestParams.put("description", "Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        requestParams.put("image", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        requestParams.put("category", "clothing");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.put("products/"+ProductName);
        body = response.getBody();
        jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Then("Verify the status code as {int}")
    public void verify_the_status_code_as(int id) {
        responseCode = response.getStatusCode();
        assertEquals(responseCode, id);
    }
}
