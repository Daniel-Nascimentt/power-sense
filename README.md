# POWER SENSE - Sentido de potência

## Índice

- <a href="#Start-no-proeto">Start no projeto</a>
- <a href="#Tecnologias-utilizadas">Tecnologias utilizadas</a>
- <a href="#Decisoes-de-encapsulamento">Decisões de encapsulamento</a>
- <a href="#Regras-de-negocio-importantes">Regras de negócio importantes</a>
- <a href="#Yaml-para-visualizacao-dos-endpoints-no-editor.swagger">Yaml para visualização dos endpoints no editor.swagger</a>

#

## Start no projeto 👨🏻‍🔧

1º - Ter instalado docker ou mySql na máquina (Nesse exemplo usarei o docker)

2º - Executar o comando docker: 
> docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ps -d mysql:8

(Importante validar se a porta 3306 da sua máquina está sendo utilizada por um mysql local)

3º Fazer start do projeto com as seguintes linhas do application.yml comentadas:
```yml
#  sql:
#    init:
#      mode: ALWAYS
```

4º *Opcional* - Derrubar a aplicação e descomentar o trecho mencionado acima e subir novamente. Com isso as tabelas do banco de dados serão populadas para teste.


#
## Tecnologias utilizadas 👨🏻‍💻

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
* MySQL (Persistir os dados)
* Docker (Para subir um container com mysql)
#

## Decisões de encapsulamento ✍🏻

Em código adotamos alguns padrões de encapsulamento, criando alguns métodos que auxiliam na conversão de objetos, por exemplo, de uma *Request* para um *Model*. Ou de um *Model* para um *Response*. Ambos os métodos auxiliares são retornados em endpoints especificos. Por exemplo, em um endpoint de consumo, é possivel visualizar as informações do eletrodomestico, quem utiliza e quanto que foi consumido. Dessa forma cada endpoint retorna somente o que definimos ser relevante.

Um ponto importante que vai de acordo com encapsulamento e não torna o simples tão complexo, optamos pode deixar nossas classes de Model representar nossas entidades no banco de dados, por se tratar de poucas entidades e sem a necessidade de ter uma classe model e uma entidade separadas.

Trecho de exemplo:

```java
    // Construtor que recebe um model
    public EletrodomesticoResponse(EletrodomesticoModel eletrodomestico) {
        toResponse(eletrodomestico);
    }

    // Método que é responsavel por converter todas as informações 
    // dos eletrodomesticos e as pessoas que o consomem.
    public EletrodomesticoResponse toResponseEletroDomesticoAllConsumidores(EletrodomesticoModel eletro) {

        toResponse(eletro);

        if(eletro.getContratanteUtiliza() != null) {
            this.contratanteUtiliza = new ContratanteResponse().toResponseEletrodomestico(eletro.getContratanteUtiliza());
        }

        eletro.getResidentesUtilizam().forEach(resid -> {
            this.residentesUtilizam.add(new ResidenteResponse().toResponseEletrodomestico(resid));
        });

        return this;
    }

    // Método que é responsavel por converter model em response apenas para 
    // os campos que representam um eletrodomestico.
    public void toResponse(EletrodomesticoModel eletrodomestico) {
        this.id = eletrodomestico.getId();
        this.nome = eletrodomestico.getNome();
        this.modelo = eletrodomestico.getModelo();
        this.potencia = eletrodomestico.getPotencia();
        this.voltagem = eletrodomestico.getVoltagem();
    }

    // Método que é responsavel por converter todas as informações vinculadas
    // ao eletrodomestico, como quem consome, e informações sobre o consumo.
    public EletrodomesticoResponse toResponseAllInformations(EletrodomesticoModel eletrodomestico, ConsumoModel consumo) {
        this.toResponseEletroDomesticoAllConsumidores(eletrodomestico);
        this.consumo = new ConsumoResponse(consumo);

        return this;
    }
```
#

## Regras de negócio importantes 🕵️‍♂️

* O Consumo do eletrodomestico é calculado automaticamente multiplicando a potencia pelas horas de uso.
* O Contratante pode morar com varios residentes.
* O Residente pode morar com um contratante.
* Tanto o Contratante quanto o Residente possuem um endereço, Mas um contratante pode ter vários endereços, enquanto o residente apenas 1.
* Um eletrodomestico só pode ser cadastrado se ouver algum CPF que utilize o mesmo.
* Um Residente não pode ser cadastrado com o mesmo CPF de um contratante.
* Um Eletrodomestico pode ser utilizado pelos residentes e o contratante.
* CPF para cadastro precisa ser valido (CPFs cadastrados via "data.sql" não seguem esse padrão, são usados apenas para teste)


#
## Yaml para visualização dos endpoints no editor.swagger 🧶

Caso não de para baixar o código fonte mas querira visualizar os endpoints, requests e responses,
basta abrir a URL *https://editor.swagger.io* e colar o seguinte yml: 

```yml
openapi: 3.0.1
info:
  title: OpenAPI definition
  version: v0
servers:
  - url: http://localhost:8080
    description: Generated server url
paths:
  /pessoa/atualizarResidente/{cpf}:
    put:
      tags:
        - pessoas-controller
      operationId: atualizarResidente
      parameters:
        - name: cpf
          in: path
          required: true
          schema:
            type: string
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/ResidenteRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /pessoa/atualizarContratante:
    put:
      tags:
        - pessoas-controller
      operationId: atualizarContratante
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/ContratanteRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /endereco/atualizar:
    put:
      tags:
        - endereco-controller
      operationId: atualizarEndereco
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/EnderecoRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /eletrodomestico/atualiziar:
    put:
      tags:
        - eletrodomestico-controller
      operationId: atualizarEletrodomestico
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/EletrodomesticoRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /pessoa/novoResidente:
    post:
      tags:
        - pessoas-controller
      operationId: cadastrarNovoResidente
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/ResidenteRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /pessoa/novoContratante:
    post:
      tags:
        - pessoas-controller
      operationId: cadastrarNovoContratante
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/ContratanteRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /endereco/cadastrar:
    post:
      tags:
        - endereco-controller
      operationId: cadastrarEndereco
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/EnderecoRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /eletrodomestico/reportarConsumo:
    post:
      tags:
        - eletrodomestico-controller
      operationId: reportarConsumo
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/ConsumoRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /eletrodomestico/cadastrar:
    post:
      tags:
        - eletrodomestico-controller
      operationId: cadastrarEletrodomestico
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/EletrodomesticoRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/EletrodomesticoResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /pessoa/listaResidentes/{cpfContratante}:
    get:
      tags:
        - pessoas-controller
      operationId: listaResidentesPorContratante
      parameters:
        - name: cpfContratante
          in: path
          required: true
          schema:
            type: string
        - name: paginacao
          in: query
          required: true
          schema:
            $ref: '#/components/schemas/Pageable'
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/PageResidenteResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /pessoa/buscarResidentePorNome/{nome}:
    get:
      tags:
        - pessoas-controller
      operationId: buscarResidentePorNome
      parameters:
        - name: nome
          in: path
          required: true
          schema:
            type: string
        - name: paginacao
          in: query
          required: true
          schema:
            $ref: '#/components/schemas/Pageable'
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/PageResidenteResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /pessoa/buscarResidente/{cpf}:
    get:
      tags:
        - pessoas-controller
      operationId: buscarResidente
      parameters:
        - name: cpf
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ResidenteResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /pessoa/buscarContratante/{cpf}:
    get:
      tags:
        - pessoas-controller
      operationId: buscarContratante
      parameters:
        - name: cpf
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ContratanteResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /endereco/buscarTodos/{cep}:
    get:
      tags:
        - endereco-controller
      operationId: detalharEndereco
      parameters:
        - name: cep
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /endereco/buscar/{cpf}:
    get:
      tags:
        - endereco-controller
      operationId: buscarPorCpf
      parameters:
        - name: cpf
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/EnderecoResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /eletrodomestico/obterCoonsumo/{idEletrodomestico}:
    get:
      tags:
        - eletrodomestico-controller
      operationId: obterConsumoEletrodomestico
      parameters:
        - name: idEletrodomestico
          in: path
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /eletrodomestico/buscarTodos/{cpf}:
    get:
      tags:
        - eletrodomestico-controller
      operationId: listarEletrodomesticosPorCPF
      parameters:
        - name: cpf
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/EletrodomesticoResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /eletrodomestico/buscar/{id}:
    get:
      tags:
        - eletrodomestico-controller
      operationId: detalharEletrodomestico
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /pessoa/deletarResidente/{cpf}:
    delete:
      tags:
        - pessoas-controller
      operationId: deletarResidente
      parameters:
        - name: cpf
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /pessoa/deletarContratante/{cpf}:
    delete:
      tags:
        - pessoas-controller
      operationId: deletarContratante
      parameters:
        - name: cpf
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /endereco/deletar/{id}:
    delete:
      tags:
        - endereco-controller
      operationId: excluirEndereco
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
  /eletrodomestico/deletar/{id}:
    delete:
      tags:
        - eletrodomestico-controller
      operationId: excluirEletrodomestico
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: object
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Not Found
          content:
            '*/*':
              schema:
                type: object
components:
  schemas:
    ResidenteRequest:
      required:
        - cpf
        - cpfContratante
        - dataNascimento
        - nome
        - parentescoComContratante
        - sexo
      type: object
      properties:
        nome:
          type: string
        cpf:
          type: string
        dataNascimento:
          type: string
          format: date
        sexo:
          type: string
          enum:
            - MASCULINO
            - FEMININO
            - OUTRO
        parentescoComContratante:
          type: string
        cpfContratante:
          type: string
    ContratanteRequest:
      required:
        - cpf
        - dataNascimento
        - nome
        - sexo
      type: object
      properties:
        nome:
          type: string
        cpf:
          type: string
        dataNascimento:
          type: string
          format: date
        sexo:
          type: string
          enum:
            - MASCULINO
            - FEMININO
            - OUTRO
    EnderecoRequest:
      required:
        - bairro
        - cep
        - cidade
        - cpfContratante
        - estado
        - numero
        - rua
      type: object
      properties:
        id:
          type: integer
          format: int64
        rua:
          type: string
        numero:
          type: integer
          format: int64
        bairro:
          type: string
        cidade:
          type: string
        estado:
          type: string
          enum:
            - AC
            - AL
            - AP
            - AM
            - BA
            - CE
            - DF
            - ES
            - GO
            - MA
            - MT
            - MS
            - MG
            - PA
            - PB
            - PR
            - PE
            - PI
            - RJ
            - RN
            - RS
            - RO
            - RR
            - SC
            - SP
            - SE
            - TO
        cpfContratante:
          type: string
        cep:
          type: string
        cpfsResidentes:
          type: array
          items:
            type: string
    EletrodomesticoRequest:
      required:
        - modelo
        - nome
        - potencia
        - voltagem
      type: object
      properties:
        id:
          type: integer
          format: int64
        nome:
          type: string
        modelo:
          type: string
        potencia:
          type: integer
          format: int64
        voltagem:
          type: string
          enum:
            - V110
            - V220
            - V110_220
        utilizadoPorCpfs:
          type: array
          items:
            type: string
    ConsumoRequest:
      required:
        - horasConsumo
        - idEletroDomestico
      type: object
      properties:
        idEletroDomestico:
          type: integer
          format: int64
        horasConsumo:
          type: integer
          format: int64
    ConsumoResponse:
      type: object
      properties:
        watts:
          type: integer
          format: int64
        horasConsumo:
          type: integer
          format: int64
    ContratanteResponse:
      type: object
      properties:
        nome:
          type: string
        cpf:
          type: string
        dataNascimento:
          type: string
          format: date
        sexo:
          type: string
          enum:
            - MASCULINO
            - FEMININO
            - OUTRO
    EletrodomesticoResponse:
      type: object
      properties:
        id:
          type: integer
          format: int64
        nome:
          type: string
        modelo:
          type: string
        potencia:
          type: integer
          format: int64
        voltagem:
          type: string
          enum:
            - V110
            - V220
            - V110_220
        consumo:
          $ref: '#/components/schemas/ConsumoResponse'
        contratanteUtiliza:
          $ref: '#/components/schemas/ContratanteResponse'
        residentesUtilizam:
          type: array
          items:
            $ref: '#/components/schemas/ResidenteResponse'
    ResidenteResponse:
      type: object
      properties:
        nome:
          type: string
        cpf:
          type: string
        dataNascimento:
          type: string
          format: date
        sexo:
          type: string
          enum:
            - MASCULINO
            - FEMININO
            - OUTRO
        parentescoComContratante:
          type: string
        contratante:
          $ref: '#/components/schemas/ContratanteResponse'
    Pageable:
      type: object
      properties:
        page:
          minimum: 0
          type: integer
          format: int32
        size:
          minimum: 1
          type: integer
          format: int32
        sort:
          type: array
          items:
            type: string
    PageResidenteResponse:
      type: object
      properties:
        totalElements:
          type: integer
          format: int64
        totalPages:
          type: integer
          format: int32
        size:
          type: integer
          format: int32
        content:
          type: array
          items:
            $ref: '#/components/schemas/ResidenteResponse'
        number:
          type: integer
          format: int32
        sort:
          $ref: '#/components/schemas/SortObject'
        first:
          type: boolean
        last:
          type: boolean
        numberOfElements:
          type: integer
          format: int32
        pageable:
          $ref: '#/components/schemas/PageableObject'
        empty:
          type: boolean
    PageableObject:
      type: object
      properties:
        offset:
          type: integer
          format: int64
        sort:
          $ref: '#/components/schemas/SortObject'
        pageNumber:
          type: integer
          format: int32
        pageSize:
          type: integer
          format: int32
        paged:
          type: boolean
        unpaged:
          type: boolean
    SortObject:
      type: object
      properties:
        empty:
          type: boolean
        sorted:
          type: boolean
        unsorted:
          type: boolean
    EnderecoResponse:
      type: object
      properties:
        id:
          type: integer
          format: int64
        rua:
          type: string
        numero:
          type: integer
          format: int64
        bairro:
          type: string
        cidade:
          type: string
        estado:
          type: string
          enum:
            - AC
            - AL
            - AP
            - AM
            - BA
            - CE
            - DF
            - ES
            - GO
            - MA
            - MT
            - MS
            - MG
            - PA
            - PB
            - PR
            - PE
            - PI
            - RJ
            - RN
            - RS
            - RO
            - RR
            - SC
            - SP
            - SE
            - TO
        cep:
          type: string

````