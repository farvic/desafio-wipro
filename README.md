<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>


<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

[![LinkedIn][linkedin-shield]][linkedin-url]

[//]: # ([![GitHub Workflow Status &#40;with branch&#41;]&#40;https://img.shields.io/github/actions/workflow/status/farvic/spring-boot-template-repo/build_test_action.yml?branch=main&label=tests&logo=github&style=for-the-badge&#41;]&#40;https://github.com/farvic/spring-boot-template-repo/actions/workflows/build_test_action.yml&#41;)

# WiPro - Desafio Bradesco

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Sumário</summary>
  <ol>
    <li>
      <a href="#about-the-project">Sobre o projeto</a>
    </li>
    <li>
        <a href="#built-with">Built With</a>
    </li>
    <li>
        <a href="#topics-covered-in-this-template">Topics covered in this template</a>
    <li>
        <a href="#getting-started">Getting Started</a>
    </li>
    <li>
        <a href="#roadmap">Roadmap</a>
    </li>
    <li>
        <a href="#contact">Contact</a>
    </li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## Sobre o projeto

O projeto foi desenvolvido com o intuito de atender o desafio proposto no processo seletivo da WiPro.
O desafio consiste em criar uma API com o seguinte contrato.

![Alt text](/imagens/desafio.png "Optional title")

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


## Topics covered in this template

* REST API
* Domain-Driven Design
* Tests with JUnit Jupiter, MockMvc and Mockito
* Databases with H2 embedded database
* Custom Exception Handling
* Swagger UI API

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


<!-- Execução -->
## Execução

1. . Clone o repositório

   ```sh
   Clone o repositório: https://github.com/farvic/desafio-wipro
   ```

2. Utilize a IDE ou o seguinte comando do Maven para executar o projeto

   ```bash
   mvn spring-boot:run
   ```



[//]: # (4. Some relevant lines from the application.properties)

[//]: # ()
[//]: # (   ```properties)

[//]: # (    # localhost:8080/)

[//]: # (    port=8080)

[//]: # ()
[//]: # ()
[//]: # (    # localhost:8080/swagger-ui/index.html)

[//]: # ()
[//]: # (    springdoc.swagger-ui.path=/swagger-ui.html)

[//]: # (    springdoc.api-docs.path=/v3/api-docs)

[//]: # (    sprindoc.swagger-ui.config-url=/v3/api-docs/swagger-config)

[//]: # (    springdoc.swagger-ui.url=/v3/api-docs)

[//]: # ()
[//]: # ()
[//]: # (    # H2 Database name - in memory database)

[//]: # (    spring.datasource.url=jdbc:h2:mem:testdb)

[//]: # (    spring.h2.console.enabled=true)

[//]: # ()
[//]: # (    # localhost:8080/h2-console)

[//]: # (    spring.h2.console.path=/h2-console)

[//]: # ()
[//]: # (    spring.datasource.driverClassName=org.h2.Driver)

[//]: # ()
[//]: # (    # authentication to access the database console)

[//]: # (    spring.datasource.username=sa)

[//]: # (    spring.datasource.password=)

[//]: # (   ```)

5. Ao rodar o projeto, a documentação da API pode ser acessada através do link: [localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


[//]: # ()
[//]: # ([//]: # &#40;CONTRIBUTING&#41;)
[//]: # ()
[//]: # (## Contributing)

[//]: # ()
[//]: # (Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.)

[//]: # ()
[//]: # (If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".)

[//]: # (Don't forget to give the project a star! Thanks again!)

[//]: # ()
[//]: # (1. Fork the Project)

[//]: # (2. Create your Feature Branch &#40;`git checkout -b feature/AmazingFeature`&#41;)

[//]: # (3. Commit your Changes &#40;`git commit -m 'Add some AmazingFeature'`&#41;)

[//]: # (4. Push to the Branch &#40;`git push origin feature/AmazingFeature`&#41;)

[//]: # (5. Open a Pull Request)

<!-- Contato -->
## Contato

Caso queira falar sobre o projeto, entre em contato comigo por aqui ou pelo LinkedIn.

Victor Fonseca -  [@Linkedin](https://www.linkedin.com/in/victorfa)



<p align="right">(<a href="#readme-top">voltar ao topo</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-blue.svg?style=for-the-badge
[linkedin-url]: https://linkedin.com/in/victorfa
