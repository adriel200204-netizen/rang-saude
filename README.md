# RANG Saúde

Sistema web para cadastro e consulta de unidades de saúde por faixa de CEP.

## Tecnologias

- Java 21
- Jakarta Faces (JSF) 4.0
- PrimeFaces 14.0
- JPA 3.1
- Hibernate 6
- MySQL 8
- Maven
- Apache Tomcat 10.1

## Funcionalidades

### Consulta de unidade de saúde

Permite informar um CEP e localizar a unidade de saúde responsável pela faixa correspondente.

O sistema apresenta:

- Nome do estabelecimento
- CNES

### Cadastro de unidade de saúde

Permite cadastrar:

- CNES
- Nome do estabelecimento
- CEP inicial
- CEP final

O CNES deve ser único.

Caso seja informado um CNES já cadastrado, o sistema apresenta uma mensagem informando que o CNES já existe.

## Banco de dados

Banco utilizado:

`rang_saude`

Tabela principal:

`unidade_saude`

Campos:

- `id`
- `cnes`
- `nome_estabelecimento`
- `cep_inicio`
- `cep_fim`

## Dados iniciais

O banco contém as seguintes unidades:

| CNES | Nome | CEP inicial | CEP final |
|---|---|---:|---:|
| 9971408 | Unidade Central | 1 | 1000000 |
| 2543354 | Unidade Bairro Agostini | 1000001 | 2000000 |
| 9254501 | Unidade Bairro Jardim Peperi | 2000001 | 3000000 |

## Execução

### Requisitos

- JDK 21
- Maven
- MySQL 8
- Apache Tomcat 10.1

### Compilação

Na raiz do projeto, execute:

```bash
mvn clean package
```

O arquivo WAR será gerado em:

```text
target/rang-saude-1.0-SNAPSHOT.war
```

Copie o arquivo WAR para a pasta `webapps` do Apache Tomcat e inicie o servidor.

A aplicação poderá ser acessada em:

```text
http://localhost:8080/rang-saude-1.0-SNAPSHOT/
```

## Estrutura do projeto

```text
src/
└── main/
    ├── java/
    │   └── br/com/rang/saude/
    │       ├── bean/
    │       │   ├── CadastroBean.java
    │       │   └── ConsultaBean.java
    │       ├── dao/
    │       │   └── UnidadeSaudeDAO.java
    │       ├── model/
    │       │   └── UnidadeSaude.java
    │       ├── TesteAplicacao.java
    │       └── TesteBanco.java
    │
    ├── resources/
    │   └── META-INF/
    │       └── persistence.xml
    │
    └── webapp/
        ├── cadastro.xhtml
        ├── index.xhtml
        └── WEB-INF/
            ├── beans.xml
            └── web.xml
```

## Exemplo de consulta

Para o CEP:

```text
1234567
```

O sistema retorna:

```text
Unidade Bairro Agostini
```

CNES:

```text
2543354
```

## Tratamento de CNES duplicado

Caso seja informado um CNES que já existe no banco de dados, o sistema não apresenta um erro HTTP 500.

É exibida uma mensagem informando:

```text
CNES já cadastrado. Informe outro CNES.
```

Quando o cadastro é realizado corretamente, é exibida a mensagem:

```text
Unidade cadastrada com sucesso.
```

## Configuração do banco

As configurações de conexão com o MySQL estão no arquivo:

```text
src/main/resources/META-INF/persistence.xml
```

Verifique as credenciais do MySQL antes de executar o projeto em outro ambiente.

Para disponibilizar o projeto publicamente, foi incluído o arquivo `persistence.example.xml` como modelo de configuração, sem credenciais reais.

## Observação

O projeto foi desenvolvido e testado utilizando Java 21, Apache Tomcat 10.1, MySQL 8 e as dependências Jakarta utilizadas no projeto.