package stepdefs;

import Config.Properties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BellAfterSearch;
import pages.BellBeforeSearch;
import stash.Context;

public class BellIntegratorSteps extends BaseSteps {
    @Given("пользователь переходит на страницу поиска сайта Bell Integrator")
    public void пользовательПереходитНаСтраницуПоискаСайтаBellIntegrator() {
        chromeDriver.get(Properties.testProperties.bellintegrator_search_url());
        BellBeforeSearch bellPO = new BellBeforeSearch(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), bellPO);
    }

    @When("^пользователь выполняет поиск по фразе \"(.*)\"$")
    public void пользовательВыполняетПоискПоФразе(String searchText) {
        BellBeforeSearch bellBeforeSearch = (BellBeforeSearch) testContext.get(Context.CURRENT_PAGE.name());
        bellBeforeSearch.find(searchText);
        BellBeforeSearch bellPO = new BellAfterSearch(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), bellPO);
    }

    @Then("хотя бы в одном результате поиска содержится строка {string}")
    public void вРезультатахПоискаСодержитсяСтрока(String resultSearchText) {
        BellAfterSearch bellAfterSearch = (BellAfterSearch) testContext.get(Context.CURRENT_PAGE.name());
        Assert.assertTrue("Статьи содержащие " + resultSearchText + " не найдены для поискового слова " + resultSearchText,
                bellAfterSearch.getResults().stream().anyMatch(x -> x.getText().contains(resultSearchText)));
    }
}
