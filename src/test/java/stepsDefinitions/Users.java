package stepsDefinitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

public class Users {
  RequestSpecification httpRequest;
  Response response;
  int responseCode;
  ResponseBody body;
  String token;
  JSONObject requestParam = new JSONObject();

  @Dado("que tenho um endpoint de consulta")
  public void baseURI() {
    RestAssured.baseURI = "https://reqres.in/";
    httpRequest = RestAssured.given();
  }

  @Quando("faço a requisição com URL")
  public void getRequest() {
    response = httpRequest.get("api/users");
  }

  @Entao("o status code {int} é retornado")
  public void verifyStatusCodeIsReturned(Integer code) {
    responseCode = response.getStatusCode();
    Assertions.assertEquals(code, responseCode);
  }

  @Entao("as informações da conta")
  public void validateDate() {
    body = response.getBody();

    JsonPath jsonPath = response.jsonPath();
    String firstName = jsonPath.getJsonObject("data[0].first_name").toString();

    Assertions.assertEquals("George", firstName);
  }

  @Dado("que estou autenticado.")
  public void authentication() {
    baseURI();
    response = httpRequest.get("api/login");
    JSONObject requestParam = new JSONObject();

    requestParam.put("email", "eve.holt@reqres.in");
    requestParam.put("password", "cityslicka");

    httpRequest.contentType(ContentType.JSON);
    httpRequest.body(requestParam.toString());

    response = httpRequest.post("api/login");
    body = response.getBody();
    JsonPath jsonPath = response.jsonPath();
    token = jsonPath.getJsonObject("token").toString();

    Assert.assertEquals(response.statusCode(), 200);
  }

  @Dado("forneço todos os dados obrigatórios {string}, {string}, {string}, {string}")
  public void formUser(String fieldName, String param, String fieldJob, String job) {
    requestParam.put(fieldName, param);
    requestParam.put(fieldJob,job);
  }
  @Quando("realizo uma requisição para o endpoint de cadastro,")
  public void createUser() {
    httpRequest.header("Authorization", "Bearer " + token);
    httpRequest.contentType(ContentType.JSON);
    httpRequest.body(requestParam.toString());
    response = httpRequest.post("api/users");
  }

  @Entao("o sistema retorna o status code {int},")
  public void verifyStatusCodeReturned(Integer code) {
    verifyStatusCodeIsReturned(code);
  }

  @Entao("os dados do novo usuário são exibidos na resposta.")
  public void userDataReturn() {
    response.getBody();
    JsonPath jsonPath = response.jsonPath();
    String name = jsonPath.getJsonObject("name").toString();
    String job = jsonPath.getJsonObject("job").toString();

    Assertions.assertEquals("Anderson", name);
    Assertions.assertEquals("Quality", job);
  }
}



