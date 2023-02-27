
[![LinkedIn][linkedin-shield]][linkedin-url]   [![GitHub Workflow Status (with branch)](https://img.shields.io/github/actions/workflow/status/farvic/desafio-wipro/build_test_action.yml?branch=main&label=tests&logo=github&style=for-the-badge)](https://github.com/farvic/desafio-wipro/actions/workflows/build_test_action.yml)



# WiPro - Desafio Bradesco

<!-- TOC -->
<!-- TABLE OF CONTENTS -->
<details>
  <summary>Sumário</summary>
  <ol>
    <li>
      <a href="#sobre-o-projeto">Sobre o projeto</a>
    </li>
    <li>
        <a href="#ferramentas-utilizadas">Ferramentas utilizadas</a>
    </li>
    <li>
        <a href="#o-problema">O problema</a>
        <ul><a href="#cep-no-formato-válido-com-endereço-encontrado">CEP no formato válido, com endereço encontrado</a></ul>
        <ul><a href="#cep-no-formato-válido-com-endereço-não-encontrado">CEP no formato válido, com endereço não encontrado</a></ul>
        <ul><a href=#cep-no-formato-inválido-ou-requisição-inválida--sem-o-parâmetro-cep->CEP no formato inválido ou requisição inválida (sem o parâmetro cep)</a></ul>
        <ul><a href=#busca-por-regiãofrete>Busca por região/frete</a></ul>
    <li>
        <a href="#execução">Execução</a>
        <ul><a href="#testes-do-cucumber">Testes do Cucumber</a></ul>
    </li>
    <li>
        <a href="#contato">Contato</a>
    </li>
  </ol>
</details>

<!-- SOBRE O PROJETO -->
## Sobre o projeto

O projeto foi desenvolvido com o intuito de atender o desafio proposto no processo seletivo da WiPro.
O desafio consiste em criar uma API com o seguinte contrato.

![Contrato da API em JSON, com solicitação contendo cep e resposta contendo endereço com frete.](/imagens/desafio.png "Contrato da API")

Para a busca do endereço através do CEP, a API do https://viacep.com.br/ deve ser consultada,
enquanto que o valor do frete é fixo para cada região:
* Sudeste (R$ 7,85)
* Centro-Oeste (R$ 12,50)
* Nordeste (R$ 15,98)
* Sul (R$17,30) Norte (R$ 20,83)
 
Outros requisitos do projeto são:
* O CEP ser passado com ou sem máscara na entrada
* Caso o CEP não seja encontrado, uma mensagem informativa deve ser retornada
para o cliente.

## Ferramentas utilizadas

* [Java 11](https://www.java.com/download/)
* [Maven 3.8.6](https://maven.apache.org/download.cgi)
* [Spring Boot 2.7.8](https://start.spring.io)
  * [H2 Database](https://www.h2database.com/html/main.html)
  * [Swagger UI](https://swagger.io/tools/swagger-ui/)
  * [JUnit 5](https://junit.org/junit5/)
  * [Mockito](https://site.mockito.org/)
  * [Cucumber](https://cucumber.io/)
* [Postman](https://www.postman.com/)
* [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/)


## O problema

O problema consiste em receber o cep e retornar o endereço e o valor do frete. Considerando os diferentes
cenários da aplicação, temos:

### CEP no formato válido, com endereço encontrado

O cliente entra com um cep válido, logo a aplicação segue o fluxo normal, retornando o endereço e o valor do frete. 

### CEP no formato válido, com endereço não encontrado

O cliente entra com um cep válido, porém o endereço não é encontrado, logo a aplicação retorna uma
mensagem de erro informando que o cep não foi encontrado.

### CEP no formato inválido ou requisição inválida (sem o parâmetro cep)

O cliente entra com um cep inválido, logo a aplicação retorna uma mensagem de erro informando que o cep é inválido.


A validação do cep é feita através das anotações do Spring Boot. A anotação @Valid é utilizada para validar
os campos do objeto recebido na requisição com base nas anotações do objeto.
A anotação @Pattern é utilizada para definir o formato do cep, através de uma expressão regular.

```java
    // Validação do cep por expressão regular que aceita o cep com ou sem máscara
    // (xxxxx-xxx ou xxxxxxxx)
    @NotNull
    @Pattern(regexp = "^(0[1-9]\\d{3}|[1-9]\\d{4})-?\\d{3}$")
    private String cep;
```

```java
    @PostMapping(value="/consulta-endereco")
    public ResponseEntity<Address> getShippingByCep(@Valid @RequestBody AddressDto cep) {
        return ResponseEntity.ok(addressService.getShippingByCep(cep));
    }
```

O erro é emitido através do tratamento de exceções globais, que captura a exceção de validação do cep.
A implementação está na pasta ```errors```.

Sendo assim, a camada de serviço já recebe apenas os ceps válidos, podendo ou não encontrar o endereço, uma vez que nem
todos os ceps do Brasil possuem endereço cadastrado. 
Em seguida, caso o CEP seja recebido no formato apenas números, é feita a formatação para o formato com máscara.
Tal formatação é feita apenas para gerar um único padrão de CEP para ser utilizado como chave única,
uma vez que as buscas anteriores são salvas na database. Isso evita que o mesmo CEP tenha dois registros diferentes.

Caso o mesmo CEP seja consultado novamente, não será necessária uma nova chamada na API. Isso serve como uma forma de
evitar que o serviço pare de funcionar caso a API fique fora do ar por alguns momentos.


### Busca por região/frete

Para a busca da região, foi utilizada uma Hash Map, que armazena o estado e a região correspondente.
A Hash Map é utilizada para melhorar a performance da busca, uma vez que a busca é feita em O(1).
Visualmente, o código não é tão bonito, mas a performance é melhor que outras abordagens mais limpas.

```java
private static final HashMap<String, Region> HASH_CEP_MAP = new HashMap<>();

static {
        HASH_CEP_MAP.put("AL", Region.NORDESTE);
        HASH_CEP_MAP.put("BA", Region.NORDESTE);
        
        (...) // demais estados
        
        HASH_CEP_MAP.put("RS", Region.SUL);
        HASH_CEP_MAP.put("SC", Region.SUL);
        }

public static Region getRegionByState(String state) {
        return HASH_CEP_MAP.get(state);
}
```




<!-- Execução -->
## Execução

1.  Clone o repositório

       ```sh
       Clone o repositório: https://github.com/farvic/desafio-wipro
       ```

2. Utilize a IDE ou o seguinte comando do Maven para executar o projeto

   ```bash
   mvn spring-boot:run
   ```

3. Para rodar testes de unidade, utilize o seguinte comando do Maven

   ```bash
   mvn test
   ```
    Para checar os resultados, também é possível clicar na insígnia no topo que direciona para o Github Actions:

    [![GitHub Workflow Status (with branch)](https://img.shields.io/github/actions/workflow/status/farvic/desafio-wipro/build_test_action.yml?branch=main&label=tests&logo=github&style=for-the-badge)](https://github.com/farvic/desafio-wipro/actions/workflows/build_test_action.yml)


4. Ao rodar o projeto, a documentação da API pode ser acessada através do link: [localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Testes do Cucumber

Ao rodar os testes de integração do cucumber, os resultados podem ser acessados através do link
disponibilizado no prompt, como no exemplo:


![Exemplo do link de resultados do teste do cucumber https://reports.cucumber.io/reports/{token}](/imagens/cucumber-exemplo.png "Exemplo do link de resultados dos testes de integração")


<!-- Contato -->
## Contato

Caso queira falar sobre o projeto, entre em contato comigo por aqui ou pelo LinkedIn.

Victor Fonseca -  [@Linkedin](https://www.linkedin.com/in/victorfa)



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-blue.svg?style=for-the-badge
[linkedin-url]: https://linkedin.com/in/victorfa
