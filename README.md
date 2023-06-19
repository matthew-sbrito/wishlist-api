# Wishlist API

### Projeto para administrar a lista de desejos de um cliente no e-commerce!

![Java Badge](https://img.shields.io/badge/Java-17-orange)
![Maven Badge](https://img.shields.io/badge/Maven-3.9.2-darkred)
![Spring Boot Badge](https://img.shields.io/badge/Spring_Boot-3.1.0-lightgreen)
![Mongo Badge](https://img.shields.io/badge/MongoDB-latest-darkgreen)

### Como foi desenvolvido:
- O projeto foi pensado e construído com os princípios do Clean Architecture;
- Utilizando as ferramentas:
  - Java 17;
  - Maven 3.9.2;
  - Spring Boot 3.1.0;
  - MongoDB LATEST;
  - Docker;
  - docker compose;
- Utilizando a arquitetura REST, para chamadas HTTP;
- Utilizando as seguintes bibliotecas para os testes:  
  - JUnit; 
  - RestAssured
  - Testcontainers;
    - MongoDB Test Container;

### Como utilizar:

- Você irá precisar do Java 17 instalado na sua máquina;
- Você irá precisar também do Docker instalado na sua máquina, para subir o contêiner para rodar aplicação e também para os testes;
- Com as 2 ferramentas instaladas, basta executar o script:
  ```shell
    ./startup.sh 
  ```
- Com o comando acima, o projeto será testado, e ao passar nos testes será empacotado, gerando o arquivo JAR necessário para executar a aplicação;
- Logo em seguida ele irá construir uma imagem a partir desse JAR;
- E por fim subir os contêineres;
- Ao subir os contêineres, a aplicação ficará disponível na porta 8080 da sua máquina;
- É possível acessar a documentação Swagger em: http://localhost:8080/swagger-ui/index.html
