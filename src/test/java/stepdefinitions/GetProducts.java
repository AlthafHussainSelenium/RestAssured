package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.assertEquals;

public class GetProducts {
    public int responseCode;
    public RequestSpecification httpRequest;
    public Response response;
    public ResponseBody body;
    public String responseBody;
    public JsonPath jsnpath;

    @Given("I hit the url of get products api endpoint")
    public void i_hit_the_url_of_get_products_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url of products in the request")
    public void i_pass_the_url_of_products_in_the_request() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("products");
    }

    @Then("I receive the response code of {int}")
    public void i_receive_the_response_code_of(int code) {
        responseCode = response.getStatusCode();
        assertEquals(responseCode, code);
    }

    @Then("I verify that the rate of the product is {}")
    public void i_verify_that_the_rate_of_the_product_is(String rate) {

        //Getting the bode from the response and storing in body variable
        body = response.getBody();

        //Convert body to string
        responseBody = body.toString();

        //Jason Representation from Response body
        jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("rating[0].rate").toString();
        assertEquals(rate, s);
    }
}
