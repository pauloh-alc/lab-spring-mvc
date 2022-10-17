# lab-spring-mvc
Repositório de estudos para criação de Aplicações Web em java, utilizando Java Spring MVC.

## Conceitos iniciais

- Spring: não é um framework apenas, mas um conjunto de projetos (o spring seria mais um Ecossitema)
- Spring Framework: é apenas um, dentre todo conjunto de projetos que o spring possui (principais funcionalidades - spring MVC, suporte para JDBC, JPA, injeção de dependências [dependency injection - DI])
- Injeção de dependências: é um tipo de inversão de controle (ou Inversion Of Control - IoC) que dá o nome ao processo de prover instâncias de classes que tem um objeto que precisa para funcionar.
- Observações: O Spring Framework é a base do ecossistema e a injeção de dependências é a base do spring Framework (vantagem: baixo acoplamento e flexibilidade)

## Spring MVC

- Vantagem: desenvolvimento de aplicações web ROBUSTAS, FLEXÍVEIS e com uma clara SEPARAÇÂO de RESPOSABILIDADES nos papéis do tratamento da requisição.
- MVC: Model, View e Controller

## Parte teórica do MVC

Geralmente abrimos o browser, isto é, um user agent, digitamos um endereço na "barra de endereços" e em seguida damos um "enter". Se por acaso nada der errado, uma página HTML será renderizada.

Mas o que acontece entre o "enter" e a página HTML renderizada?

- O contexto do Spring MVC:


