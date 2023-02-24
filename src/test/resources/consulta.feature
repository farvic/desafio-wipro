Feature: the address can be retrieved from the api
  Users should be able to retrieve the frete from the api
  Scenario: client makes call to POST /v1/consulta-endereco
    When the client sends his cep to /v1/consulta-endereco
    Then the client receives the address
