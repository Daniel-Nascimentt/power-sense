# POWER SENSE

## Tecnologias utilizadas:

* Java 17 (Padrão Spring Initializr)
* Spring boot 3.1.0 (Padrão Spring Initializr)
    * DevTools (Facilitar setup no ambiente de desenvolvimento)
    * Lombok (Facilitar criação de métodos acessores e construtores quando necessário)
    * Spring Web (Para usar uma API REST)
    * Open API (Habilitar Swagger)
    * Modelmapper (Criar a partir de um request um model)
    * Spring Data JPA (Para implementar paginação)
    * Bean Validation (Para fazer validações de campos na borda mais externa da API, as REQUESTS)
* GIT (Controle de versão do projeto)
* IDE's (Eclipse, Intellij, VS Code)
* Postman


#

# API PESSOAS

> /pessoas/**

### CADASTRO DE PESSOAS
> /pessoas/novoContratante (Usuário)

***request***
```json
{
  "nome": "Daniel Nasicimento",
  "cpf": "123456789",
  "dataNascimento": "19-05-2000",
  "sexo": "MASCULINO"
}
```
#
***response***
> HTTP STATUS 201 (Caso de sucesso)

> HTTP STATUS 400 (Violação de constraint / Bean Validation) 

*ESSE RETORNO PODE ACONTECER PARA **QUAIS QUER ENDPOINTS***
```json
{
  "timestamp": "2023-06-19T21:46:08.556+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed for object='contratanteRequest'. Error count: 2",
  "path": "/pessoas/novoContratante"
}
```


> /pessoas/novoResidente (Pessoas que moram junto ao usuário)

***request***
```json
{
  "nome": "Daniel Nasicimento",
  "cpf": "987654321",
  "dataNascimento": "19-05-2000",
  "sexo": "MASCULINO",
  "parentescoComContratante": "Sobrinho",
  "cpfContratante": "123456789"
}
```
#
***response***
> HTTP STATUS 201 (Caso de sucesso)