Feature: Palestras e Horarios Site QA Ladies

  Scenario: Tabela de Palestras e Horarios para Excel e Tirar Print
    Given eu acesso a pagina gradepalestras do QA Ladies
    When eu seleciono a tabela de Palestras e Horarios
    And eu exporto a tabela para arquivo Excel
    Then eu tiro screenshot das tres primeiras palestrantes exibidas na tela
    And fecho o chrome browser
