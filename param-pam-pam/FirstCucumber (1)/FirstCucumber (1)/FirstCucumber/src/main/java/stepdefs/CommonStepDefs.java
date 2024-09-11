package stepdefs;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

/**
 * Класс реализации шагов
 */
public class CommonStepDefs extends BaseSteps {

    @Given("перехожу на сайт {string}")
    public void перейти_на_сайт(String url) {
        System.out.println(chromeDriver.getTitle());
        chromeDriver.get(url);
    }

    @Then("^тайтл верен '(.*)'$")
    public void тайтлВерен(String checkTitle) {
        String title = chromeDriver.getTitle();
        System.out.println(title);
        Assert.assertTrue("Тайтл " + title + " на сайте не содержит Bell Integrator", title.contains(checkTitle));
    }

    @When("вывести список")
    public void вывестиСписок(List<String> arg) {
        System.out.println(arg);
    }

    @When("вывести таблицу")
    public void вывестиТаблицу(DataTable arg) {
        System.out.println(arg.toString());
    }

    @When("вывести название доступных пунктов")
    public void вывестиНазваниеДоступныхПунктов(DataTable arg) {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        table.stream()
                .filter(x -> x.get("Доступен").equals("true"))
                .forEach(x -> System.out.println(
                        x.get("Название") + " " +
                                x.get("Количество подменю") + " " +
                                x.get("Отлад"))
                );

        for (Map<String, String> stringStringMap : table) {
            if (stringStringMap.get("Доступен").equals("true"))
                System.out.println(stringStringMap.get("Название") + " " + stringStringMap.get("Количество подменю") + " " + stringStringMap.get("Отлад"));
        }
    }

    @Then("^вывод (.*) и (.*) из списка$")
    public void выводКлючИЗначениеИзСписка(String a, String b) {
        System.out.println(a + b);
    }
}
