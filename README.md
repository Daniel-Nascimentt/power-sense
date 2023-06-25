# POWER SENSE - Sentido de potência

## Tecnologias utilizadas:

* Java 17 (Padrão Spring Initializr)
* Spring boot 3.1.0 (Padrão Spring Initializr)
    * DevTools (Facilitar setup no ambiente de desenvolvimento dando Restart no servidor a cada modificação feita)
    * Lombok (Facilitar criação de métodos acessores e construtores quando necessário)
    * Spring Web (Para usar uma API REST)
    * Open API (Habilitar Swagger) URL: **/swagger-ui/index.html*
    * Spring Data JPA (Para implementar paginação)
    * Bean Validation (Para fazer validações de campos na borda mais externa da API, as REQUESTS)
* GIT (Controle de versão do projeto)
* IDE's (Eclipse, Intellij, VS Code)
* Postman (Testes da API)
#
# AVISO IMPORTANTE!!!!

Métodos que fazem consulta por ID ou CPF ainda não foi implementado a validação de existencia do mesmo. Será implementado no momento de integração da aplicação com um banco de dados. Os testes realizados foram meramente para teste de validações de endpoints e suas constraints.
#

# API PESSOAS

## CADASTRO DE CONTRATANTE (USUÁRIO)
> POST: /pessoas/novoContratante

***Request Body***
```json
{
  "nome": "Daniel Nasicimento",
  "cpf": "123456789",
  "dataNascimento": "19-05-2000",
  "sexo": "MASCULINO"
}
```
#
***Response***
> HTTP STATUS 201 (Caso de sucesso)

> HTTP STATUS 400 (Violação de constraint / Bean Validation) 

*OBS: ESSE RETORNO PODE ACONTECER PARA **QUAIS QUER ENDPOINTS*** QUE A BEAN VALIDATION DETECTAR VIOLAÇÃO DE CONSTRAINT.
```json
{
  "timestamp": "2023-06-19T21:46:08.556+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed for object='contratanteRequest'. Error count: 2",
  "path": "/pessoas/novoContratante"
}
```

#

## CADASTRO DE RESIDENTE (Pessoas que moram junto ao usuário)

> /pessoas/novoResidente

***Request Body***
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
***Response***
> HTTP STATUS 201 (Caso de sucesso) 

> HTTP STATUS 400 (Violação de constraint / Bean Validation) 

#

## CONSULTA DE CONTRATANTE/RESIDENTE 

> GET: /pessoas/buscarContratante/{cpf}

***Response***
```json
{
  "nome": "Daniel Nasicimento",
  "cpf": "123456789",
  "dataNascimento": "19-05-2000",
  "sexo": "MASCULINO"
}
```

> GET: /pessoas/buscarResidente/{cpf}

***Response***
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

## UPDATE DE CONTRATANTE/RESIDENTE 

> PUT: /pessoas/atualizarContratante/{cpf}

***Request Body***
```json
{
  "nome": "Daniel Nasicimento",
  "cpf": "123456789",
  "dataNascimento": "19-05-2000",
  "sexo": "MASCULINO"
}
```

> PUT: /pessoas/atualizarResidente/{cpf}

***Request Body***
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

***Response***
> HTTP STATUS 200 (Caso de sucesso)

> HTTP STATUS 400 (Violação de constraint / Bean Validation) 


#

## DELETE DE CONTRATANTE/RESIDENTE 

> DELETE: /pessoas/deletarContratante/{cpf}

> DELETE: /pessoas/deletarResidente/{cpf}

***Response***
> HTTP STATUS 200 (Caso de sucesso)

#

#

# API de endereços

## Cadastro de endereços 

> POST: /enderecos/cadastrar

***Request Body***
```json
{
  "rua": "Rua java",
  "numero": 17,
  "bairro": "Eclipse",
  "cidade": "Oracle",
  "estado": "Windows"
}
```


***Response***
> HTTP STATUS 201 (Caso de sucesso)

#

## Consulta de endereços (TODOS E POR ID)

> GET: /enderecos/buscar/{id}

***Response***
```json
{
  "id": 4633343939904034000,
  "rua": "Java",
  "numero": 17,
  "bairro": "Eclipse",
  "cidade": "Oracle",
  "estado": "SP"
}
```

> GET: /enderecos/buscarTodos

***Response***
```json
{
  "id": 4633343939904034000,
  "rua": "Java",
  "numero": 17,
  "bairro": "Eclipse",
  "cidade": "Oracle",
  "estado": "SP"
},
{
  "id": 4633343939904035000,
  "rua": "Java",
  "numero": 11,
  "bairro": "Intellij",
  "cidade": "Oracle Brasil",
  "estado": "RJ"
}
```

## UPDATE de endereços 

> PUT: /enderecos/atualizar/{id}

***Request Body***
```json
{
  "rua": "Rua java",
  "numero": 17,
  "bairro": "Eclipse",
  "cidade": "Oracle",
  "estado": "Windows"
}
```
***Response***
> HTTP STATUS 200 (Caso de sucesso)

## DELETE de endereço

> DELETE: /enderecos/deletar/{id}

***Response***
> HTTP STATUS 200 (Caso de sucesso)

#

#

# API de eletrodomesticos

## Cadastro de eletrodomesticos 

> POST: /eletrodomesticos/cadastrar

***Request Body***
```json
{
  "nome": "Geladeira",
  "modelo": "Frost Free",
  "potencia": 2000,
  "voltagemEnum": "V110"
}
```

***Response***
> HTTP STATUS 201 (Caso de sucesso)

## Consulta de eletrodomesticos (Todos e por ID) 

> GET: /eletrodomesticos/buscar/{id}

***Response***
```json
{
  "id": 4633343939904035000,
  "nome": "Geladeira",
  "modelo": "Frost Free",
  "potencia": 2000,
  "voltagemEnum": "V110"
}
```

> GET: /eletrodomesticos/buscarTodos

```json
{
  "id": 4633343939904035000,
  "nome": "Geladeira",
  "modelo": "Frost Free",
  "potencia": 2000,
  "voltagemEnum": "V110"
},
{
  "id": 4666343939904035000,
  "nome": "Microondas",
  "modelo": "6 Litros",
  "potencia": 1000,
  "voltagemEnum": "V220"
}
```

## UPDATE de eletrodomesticos 

> PUT: /eletrodomesticos/atualizar/{id}

***Request Body***
```json
{
  "nome": "Geladeira",
  "modelo": "Frost Free",
  "potencia": 2000,
  "voltagemEnum": "V110"
}
```

***Response***
> HTTP STATUS 200 (Caso de sucesso)


## DELETE de eletrodomestico

> DELETE: /eletrodomesticos/deletar/{id}

***Response***
> HTTP STATUS 200 (Caso de sucesso)
