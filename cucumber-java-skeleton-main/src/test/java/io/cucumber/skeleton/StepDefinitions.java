package io.cucumber.skeleton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    WebDriver navegador;
    String output ="";
    @Given("eu acesso a pagina gradepalestras do QA Ladies")
    public void eu_acesso_a_pagina_gradepalestras_do_qa_ladies() {
        // Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\natba\\Drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Navegando para a página do QA Ladies
        navegador.get("http://www.qaladies.com/gradepalestras.html");
    }

    @When("eu seleciono a tabela de Palestras e Horarios")
    public void eu_seleciono_a_tabela_de_Palestras_e_Horarios() {
        //WebDriver navegador = new ChromeDriver();
        output = navegador.findElement(By.className("progress-table-wrap")).getText();
    }

    @And("eu exporto a tabela para arquivo Excel")
    public void eu_exporto_a_tabela_para_arquivo_Excel() {
        String endereco ="extractedFilePath.txt";
        try {
            Files.write(Paths.get(endereco), output.getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("eu tiro screenshot das tres primeiras palestrantes exibidas na tela")
    public void eu_tiro_screenshot_das_tres_primeiras_palestrantes_exibidas_na_tela() {
        WebElement element = navegador.findElement(By.xpath ("//*[@class='team-area fix']"));
        element.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File scrFile = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);
        String arquivo = "screenshot.png";
        try {
                 FileUtils.copyFile(scrFile, new File(arquivo));
        } catch (Exception e) {
            System.out.println("Houve problemas ao copiar o arquivo para a pasta: " +e.getMessage());
        }
    }

    @And("fecho o chrome browser")
    public void fecho_o_chrome_browser() {
        navegador.close();
    }
}
