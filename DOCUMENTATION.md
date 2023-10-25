# Guia para o desafio tecnico da empresa Senior.

### Documentação de referência

### Objetivo
Desenvolver uma aplicação (Somente o backend) que possibilite realizar o cadastro de
hóspedes e o check in.
Queremos ver como você resolve problemas no seu dia-a-dia. Não há necessidade de
desenvolver o frontend da aplicação, vamos utilizar o Postman para testar sua aplicação.

### Requisitos funcionais
- Um CRUDL para o cadastro de hóspedes;
- No check in deve ser possível buscar hóspedes cadastrados pelo nome, documento
ou telefone;
- Consultar hóspedes que já realizaram o check in e não estão mais no hotel;
- Consultar hóspedes que ainda estão no hotel;
- As consultas devem apresentar o valor (Valor total e o valor da última hospedagem)
já gasto pelo hóspede no hotel;
---------
### JSON para cadastro do hóspede
```
{
  "hotel":{
    "nome": "hotel transilvania"
  },
  "nome": "Lucas",
  "documento": "77777777777",
  "telefone": "042999098571"
}
```
### JSON exemplo do check in
```
{
  "hospede":{
      "nome": "Lucas",
  "documento": "77777777777",
  "telefone": "042999098571"
},
  "dataEntrada": "2023-10-24T08:00:00",
  "dataSaida": "2023-10-25T22:21:00",
  "adicionalVeiculo": "true"
}
```
- `data no padrão ISO-8601`
----------
  ### Regras de negócio
  - Uma diária no hotel de segunda à sexta custa R$120,00;
  - Uma diária no hotel em finais de semana custa R$150,00;
  - Caso a pessoa precise de uma vaga na garagem do hotel há um acréscimo diário,
  sendo R$15,00 de segunda à sexta e R$20,00 nos finais de semana;
  - Caso o horário da saída seja após às 16:30h deve ser cobrada uma diária extra;

### Guides
Para acessar o projeto acesse este link e realize o clone em sua máquina:

* [Projeto hotel senior](https://github.com/LucasDev13/hotel-senior)

### Profiles
- O projeto consiste em três profiles:
  - `default` &rarr; application.properties &rarr; Propriedades para todo o projeto
  - `test` &rarr; application-dev.properties &rarr; Propriedades do banco H2
  - `dev` &rarr; application-dev.properties &rarr; Propriedades do banco PostgreSQL
---
`OBS:` Especifique em qual perfil o projeto será rodado em `application.properties`

### Profile de dev
![img_9.png](img_9.png)

### Profile de test
![img_10.png](img_10.png)

### Passo a passo para criar o banco de dados.
- Clonar o projeto
- Rodar o docker compose que esta dentro da pasta de resource para poder subir os containers do postgres
e do pgadmin: `$ docker compose up -d`
- Para acessar o pgAdmin é por esta url http://localhost:16543/browser/
- O usuario e senho de acesso são:
  - email: hotel.senior@gmail.com
  - senha: senior
    * ![img_1.png](img_1.png)
  
- Crie um novo sever
  * ![img_2.png](img_2.png)
- Defina o nome do server
- * ![img_11.png](img_11.png)
- E insira as informações do postgres definidos no arquivo docker-compose e salve.
  * ![img.png](img.png)
- `Host`: nome do container que foi criado o postgreSQL
- `Port`: Porta padrão de conexão com o banco
- `Maintenance database`: nome do banco de dados definido do docker-compose
- `username`: usuario definido no docker-compose
- `password`: senha definida no docker-compose
 * Após essas configurações, é só clicar em `salvar`.

- O banco de dados aparecerá desta maneira:
  * ![img_3.png](img_3.png)
- Acesso a aba de Query Tool para criamos as tabelas.
  * ![img_4.png](img_4.png)
- Insira o script salvo no arquivo `create.sql` que está dentro do projeto .
  * ![img_5.png](img_5.png)
  * ![img_6.png](img_6.png)

- Desta maneira as tabelas serão criadas.
  * ![img_7.png](img_7.png)

### Passo a passo para utilizar as requisições do projeto.
- ### Swagger da api
http://localhost:8080/swagger-ui.html

* Requisições que foram criadas:
  * ![img_8.png](img_8.png)

### Json para requisições e suas uri:
- Cadastro de hospedes:
  * http://localhost:8080/v1/api/guests
  * `POST`
```
{
  "hotel":{ 
    "nome": "hotel transilvania"
  },
  "nome": "Lucas",
  "documento": "77777777777",
  "telefone": "042999098571"
}
```
- Listar hospedes paginado, podendo trazer todos os hospedes:
  *  http://localhost:8080/v1/api/guests
  * `GET`

- Atualizar hospedes por id:
  * http://localhost:8080/v1/api/guests/1
  * `PUT`
  ```
  {
    "nome": "novo nome do hospede",
    "documento": "99999999999",
    "telefone": "032999046660"
  }
  ```
- Deletar hospedes:
  * http://localhost:8080/v1/api/guests/1
  * `DELETE`

- Fazer checkin:
  * http://localhost:8080/v1/api/checkin
  * `POST`
```
{
  "hospede":{
    "nome": "Lucas",
    "documento": "77777777777",
    "telefone": "042999098571"
},
  "dataEntrada": "2023-10-24T08:00:00",
  "dataSaida": "2023-10-25T22:21:00",
  "adicionalVeiculo": "true"
}
```
- Listar hospedes por parametros:
  * http://localhost:8080/v1/api/guests?param=03698106000
  * `GET`
  * Param recebe o nome, documento ou telefone