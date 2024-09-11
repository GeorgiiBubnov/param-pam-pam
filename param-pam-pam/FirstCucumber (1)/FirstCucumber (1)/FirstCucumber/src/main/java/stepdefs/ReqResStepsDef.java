package stepdefs;

import api.endpoints.ReqResEndpoints;
import dto.employees.Employee;
import dto.employees.EmployeeCreated;
import dto.employees.EmployeesList;
import helpers.JsonHelper;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static api.specifications.Specification.*;

/**
 * Класс реализации шагов
 */
public class ReqResStepsDef extends BaseSteps {

    @Дано("^Подготовить любого пользователя в списке сотрудников из отдела (\"([^\"]+)\") и сохранить как \"([^\"]+)\"$")
    public void подготовитьЛюбогоПользователяВСпискеСотрудниковИзОтделаИСохранитьКак(String department, String stashVariable) {
        EmployeesList employeesList = JsonHelper.fromJSONFile("employees.json", EmployeesList.class);
        Employee employee = employeesList.getEmployees().stream().filter(x -> x.getDepartment().equals(department)).findFirst().orElseThrow();
        testContext.put(stashVariable, employee);
    }

    @Когда("добавляю пользователя {string} в сервисе ReqRes api и сохраняю id в сервисе как {string}")
    public void добавляюПользователяВСервисеReqResApi(String userVariable, String responseVariable) {
        installSpec(requestSpec(),responseSpec201());
        Employee employee = (Employee) testContext.get(userVariable);
        EmployeeCreated employeeCreated = given()
                .body(employee)
                .log().all()
                .when()
                .post(ReqResEndpoints.USERS)
                .then()
                .log().all()
                .extract().as(EmployeeCreated.class);
        System.out.println(employeeCreated.getCreatedAt());
        testContext.put(responseVariable, employeeCreated.getId());
        deleteSpec();
    }


    @То("при поиске пользователя {string} в сервисе ReqRes api получаю ответ {int}")
    public void приПоискеПользователяВСервисеReqResApiПолучаюОтвет(String id, int statusExpected) {
        installSpec(requestSpec());
        int status =
                given()
                        .pathParam("id", testContext.get(id))
                        .log().all()
                        .when()
                        .get(ReqResEndpoints.USER_BY_ID)
                        .then()
                        .log().all()
                        .extract().statusCode();
        Assert.assertEquals("Статус код ответа не равен ожидаемому значению",statusExpected, status);
    }
}
