package runner;

import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.ConfigurationParameter;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Cucumber
@SelectClasspathResource("src/test/resources/features") // Selecionando o diretório das features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepsDefinitions") // Definindo o diretório dos step definitions
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cucumber-reports/cucumber-report.json") // Relatório em JSON
public class TestRunner {

}
