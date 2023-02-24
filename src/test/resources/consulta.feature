Feature: the address can be retrieved from the api
  Users should be able to retrieve the frete from the api
  Scenario: client makes call to POST /v1/consulta-endereco
    Given the client sends a valid cep "01001-000"
    When making a request to /v1/consulta-endereco
    Then the client should receive an address in "São Paulo"
    And the shipping value should be 7.85

  Scenario: client makes call to POST /v1/consulta-frete with an invalid cep
    Given the client sends an invalid cep "01000-000"
    When making a request to /v1/consulta-frete
    Then the client should receive an error 400

