# 🖥️🏎️ Objetivos da API Gerenciador de Corridas de Kart

API desenvolvida para atender as necessidades de um Organizador de Corridas de Kart. Tem como principal função solucionar os problemas do Cliente e ser a Ponte de Comunicação entre Aplicação Web e Mobile.

O projeto Web do Gerenciador já tinha sido feito em PHP, porém notamos que não havia uma boa estrutura, além de não conseguirmos conectar essa Aplicação com a do Mobile. Logo, a solução de criar uma API que lidasse com os dois tipos de APP foi a solução adotada.

---

<br><br>

## 🚀👩‍💻 Time de Desenvolvimento

-  [Emily Izabelle](https://github.com/em1ky) como Engenheira de Banco de Dados
-  [Giuliana Gralha](https://github.com/Giuliana09) como Engenheira Front-end
-  [Larissa Silva](https://github.com/LarissaSL) como Engenheira Back-end
-  [Leticia Graziele](https://github.com/LeticiaGraziel) como UX/UI e Auxiliar de Banco de Dados
-  [Silvana Sales](https://github.com/SilvanaMenezes) como UX/UI e Fullstack

---

<br><br>

## 📌 Pré-requisitos de Tecnologias

Para iniciar o projeto, você precisa ter os seguintes requisitos instalados:

- **[Java 17 ou superior](https://www.oracle.com/br/java/technologies/downloads/)**  
  Verifique se você tem o Java instalado no seu ambiente de desenvolvimento. Caso contrário, instale a versão mais recente.

- **IDE para JAVA**  
  Você pode escolher entre as seguintes opções de IDE:
  - [Visual Studio Code](https://code.visualstudio.com/)
  - [IntelliJ (Recomendada)](https://www.jetbrains.com/idea/download/)
  - [Eclipse](https://www.eclipse.org/downloads/)

- **[MySQL Workbench](https://dev.mysql.com/downloads/workbench/)**  
  Para gerenciar o banco de dados, o MySQL Workbench é recomendado.

  **OBS.:** Caso não tenha o MySQL, você pode utilizar o [PostgreSQL](https://www.postgresql.org/download/) ou outro banco de dados relacional. Lembre-se de alterar o arquivo `application.properties` para configurar a criação das tabelas manualmente.

- **[Imnsomnia](https://insomnia.rest/download)**  
  Para poder testar os Endpoints da API

<br>

### Exemplo de Configuração no `application.properties` para MySQL:
```properties
spring.datasource.url=jdbc:mysql://${DB_HOST}/${DB_NAME}?createDatabaseIfNotExist=true
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

<br>

### Exemplo de Configuração no `application.properties` para PostgreSQL:
```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
```

---

<br><br><br>

## 📑 Índice
### 1. Inclusões
- [Inclusões](#-inclus%C3%B5es)

### 2. Funcionalidades
- [Funcionalidades](#%EF%B8%8F-funcionalidades)

### 3. Como usar?
- [Configurando o Banco de Dados](#configurando-o-banco-de-dados)

### 4. Utilizando o Controller de Autenticação
- [Validadores de Autenticação](#%EF%B8%8F-validadores-de-autentica%C3%A7%C3%A3o)
- [Método de Autenticação](#-1-m%C3%A9todo-de-autentica%C3%A7%C3%A3o)

### 5. Utilizando o Controller de Usuários
- [Validadores de Usuário](#%EF%B8%8F-validadores-de-usu%C3%A1rio)
- [Criação de Novos Usuários](#-1-m%C3%A9todo-de-cria%C3%A7%C3%A3o-de-novos-usu%C3%A1rios)
- [Listagem de Usuários](#-2-listagem-de-usu%C3%A1rios)
- [Métodos de Ordenação e Paginação](#-21-m%C3%A9todos-de-ordena%C3%A7%C3%A3o-e-pagina%C3%A7%C3%A3o)
- [Exclusão de Usuário](#-3-exclus%C3%A3o-de-usu%C3%A1rio)
- [Atualização de Usuário](#-4-atualiza%C3%A7%C3%A3o-de-usu%C3%A1rios)

### 6. Utilizando o Controller de Kartodromos
- [Validadores de Kartodromo](#%EF%B8%8F-validadores-de-kartodromos)
- [Criação de Novos Kartodromos](#-1-m%C3%A9todo-de-cria%C3%A7%C3%A3o-de-novos-kartodromos)
- [Listagem de Kartodromos](#-2-listagem-de-kartodromos)
- [Métodos de Ordenação e Paginação](#-21-m%C3%A9todos-de-ordena%C3%A7%C3%A3o-e-pagina%C3%A7%C3%A3o-1)
- [Exclusão de Kartodromo](#-3-exclus%C3%A3o-de-kartodromo)
- [Atualização de Kartodromo](#-4-atualiza%C3%A7%C3%A3o-de-kartodromo)

### 7. Utilizando o Controller de Campeonatos
- [Validadores de Campeonato](#%EF%B8%8F-validadores-de-campeonatos)
- [Criação de Novos Campeonatos](#-1-m%C3%A9todo-de-cria%C3%A7%C3%A3o-de-novos-campeonatos)
- [Listagem de Campeonatos](#-2-listagem-de-campeonatos)
- [Métodos de Ordenação e Paginação](#-21-m%C3%A9todos-de-ordena%C3%A7%C3%A3o-e-pagina%C3%A7%C3%A3o-2)
- [Exclusão de Campeonato](#-3-exclus%C3%A3o-de-campeonato)
- [Atualização de Campeonato](#-4-atualiza%C3%A7%C3%A3o-de-campeonatos)

### 8. Utilizando o Controller de Corridas
- [Validadores de Corrida](#%EF%B8%8F-validadores-de-corridas)
- [Criação de Novos Corridas](#-1-m%C3%A9todo-de-cria%C3%A7%C3%A3o-de-novas-corridas)
- [Listagem de Corridas](#-2-listagem-de-corridas)
- [Métodos de Ordenação e Paginação](#-21-m%C3%A9todos-de-ordena%C3%A7%C3%A3o-e-pagina%C3%A7%C3%A3o-3)
- [Métodos de Filtros para Listagem](#-22-m%C3%A9todos-de-filtros-para-listagem)
- [Exibir todas as Corridas que tenham Check-ins feitos](#filtrar-corridas-que-tenham-check-ins-feitos)
- [Combinação de Filtros](#combina%C3%A7%C3%A3o-de-filtros)
- [Exclusão de Corrida](#-3-exclus%C3%A3o-de-corrida)
- [Atualização de Corrida](#-4-atualiza%C3%A7%C3%A3o-de-corridas)

### 9. Utilizando o Controller de Inscrição
- [Validadores de Inscrição](#%EF%B8%8F-validadores-de-inscri%C3%A7%C3%A3o)
- [Criação de Novas Inscrições](#-1-m%C3%A9todo-de-cria%C3%A7%C3%A3o-de-inscri%C3%A7%C3%A3o)
- [Listagem de Inscrições](#-2-listagem-de-inscri%C3%A7%C3%B5es)
- [Exibir todas as Incrições de uma Corrida](#21-exibir-todas-as-inscri%C3%A7%C3%B5es-de-uma-corrida)
- [Métodos de Filtros para Listagem](#-22-m%C3%A9todos-de-filtros-para-listagem-1)
- [Métodos de Ordenação e Paginação](#-23-m%C3%A9todos-de-ordena%C3%A7%C3%A3o-e-pagina%C3%A7%C3%A3o)
- [Exclusão de Inscrição](#-3-exclus%C3%A3o-de-inscri%C3%A7%C3%A3o)
- [Atualização de Inscrição](#-4-atualiza%C3%A7%C3%A3o-de-inscri%C3%A7%C3%B5es)

### 10. Utilizando o Controller de Check-in
- [Validadores de Check-in](#%EF%B8%8F-validadores-de-check-in)
- [Criação de Novas Check-ins](#-1-m%C3%A9todo-de-cria%C3%A7%C3%A3o-de-check-in)
- [Listagem de Check-ins](#-2-listagem-de-check-ins)
- [Exibir todas os Check-ins de uma Corrida](#21-exibir-todos-os-check-ins-de-uma-corrida)
- [Métodos de Filtro para Listagem](#-22-m%C3%A9todos-de-filtro-para-listagem)
- [Exclusão de Check-in](#-3-exclus%C3%A3o-de-check-in)
- [Atualização de Check-in](#-4-atualiza%C3%A7%C3%A3o-de-check-in)
- [Quantida de Check-ins por Corrida](#-5-quantidade-de-check-ins-por-corrida)
- [Solicitar Lista de Check-in para Compartilhar via Whatsapp](#-6-solicitar-lista-de-pilotos-que-fizeram-check-in)

### Extra 
- [Tecnologias](#-tecnologias)
- [Apêndices](#-ap%C3%AAndices)

---

<br><br><br>

## 🎯 Inclusões

- ✅ Criação do Readme da API

---

<br><br>

## ⚙️ Funcionalidades

- 🟢 CRUD de Kartodromos
- 🟢 CRUD de Campeonatos
- 🟢 CRUD de Corridas
- 🟢 CRUD de Usuários
- 🟢 CRUD de Inscrição
- 🟢 CRUD de Check-in
- 🟢 Funcionalidade de Check-in de Pilotos
- 🟡 CRUD de Classificação das Corridas
- 🟠 Autenticação de Usuários
- 🟡 Compra de Ingressos de Corridas
- 🟡 Carrinho de Compras
- 🟡 Check-out de Pagamentos
- 🟡 Check-out de Pilotos
- 🟡 Sorteador de Números de Karts
- 🟢 Compartilhamento via Whatsapp da Lista de Pilotos

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📓 Padrões de Nomenclatura nos Commits

Abaixo segue uma tabela onde explicamos um padrão para nossos commits.

| **Tipo**    | **Descrição**                                                   |
|-------------|-----------------------------------------------------------------|
| **FEAT**    | Para novos recursos                                             |
| **FIX**     | Solucionando um problema                                        |
| **RAW**     | Arquivo de configs, dados, features, parâmetros                 |
| **BUILD**   | Arquivos de build e dependências                                |
| **PERF**    | Mudança de performance                                          |
| **REMOVE**  | Exclusão de arquivos, diretórios ou código                      |
| **CHORE**   | Atualizações de tarefas de build, configs de admin, pacotes, etc|
| **REFACTOR**| Refatorações sem alterar funcionalidade                         |
| **TESTE**   | Alterações em teste                                             |
| **CI**      | Mudanças relacionadas a integração contínua                     |
| **DOCS**    | Mudanças na documentação                                        |
| **CLEANUP** | Remover trechos desnecessários                                  |
| **STYLE**   | Formatações de código                                           |

`Exemplo de uso:`
```
git commit -m "FEAT - CRUD de Usuarios"
```

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

# 🖥️🛠️ Como usar?

## Configurando o Banco de Dados
Por motivos de Segurança foi utilizado variáveis de ambiente para acesso as configurações do Banco de Dados, por isso é preciso deixar configurado essas variáveis.


### 1. Criação das Variáveis de Ambiente

1. Abrir as Configurações de Variáveis de Ambiente:
    - Clique com o botão direito em "Este Computador" ou "Meu Computador" na área de trabalho ou no explorador de arquivos.
    - Selecione "Propriedades".
    - Clique em "Configurações Avançadas do Sistema" no painel à esquerda.
    - Na aba "Avançado", clique em "Variáveis de Ambiente".


2. Criar Variáveis de Ambiente:
    - Na seção "Variáveis do Sistema", clique em "Novo".
    - No campo "Nome da variável", insira `DB_HOST`.
    - No campo "Valor da variável", insira o endereço do host do seu banco de dados (exemplo: localhost ou o IP do servidor).
    - Clique em "OK".
    - Repita o processo para `DB_NAME`(nome do Schema/Banco), `DB_USERNAME`(exemplo: root) e `DB_PASSWORD` (exemplo: ""), inserindo os valores correspondentes.


3. Salvar e Aplicar:
    - Após adicionar todas as variáveis, clique em "OK" para fechar todas as janelas.
    - Reinicie qualquer terminal ou IDE que esteja usando para garantir que as variáveis de ambiente sejam carregadas.

Resultado das Variáveis de Ambiente:

![image](https://github.com/user-attachments/assets/efe01ed8-593a-43da-ad54-38ceae473ead)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

# Utilizando o Controller de Autenticação

## 🛠️ Validadores de Autenticação

### 1. **Autenticação**

| **Campo**   | **Validação**                                                                                                   |
|-------------|-----------------------------------------------------------------------------------------------------------------|
| **Email**   | Deve ser um email válido e registrado no sistema.                                                               |
| **Senha**   | Deve corresponder à senha registrada para o usuário. A senha deve ser verificada conforme as regras de complexidade estabelecidas no sistema. |

**Nota:** Apenas usuários ativos podem fazer login. Usuários administradores são os únicos autorizados a acessar o aplicativo mobile.

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br>

## ✅ 1. Método de Autenticação

- Para autenticar um usuário, envie uma requisição para o seguinte endereço:

```
POST http://localhost:8080/login
```

**Corpo esperado:**

```json
{
    "email": "email@example.com.br",
    "senha": "senha"
}
```

<br>

Se a autenticação for bem-sucedida, você receberá o Status Code `200` com um token de acesso.

![image](https://github.com/user-attachments/assets/41815f24-7be8-4c96-b286-82f439f99c32)


<br>

Em caso de falha na autenticação, o Status Code poderá ser `401` ou `403` e a resposta incluirá uma mensagem de erro no formato RFC.

<br>

**📃❌ Algumas mensagens de Erros:**

- Caso a senha seja incorreta:

![image](https://github.com/user-attachments/assets/13cfdd85-7fff-4478-9477-cbc7d9feefdf)
  

- Caso o usuário tente fazer login no App mobile e não seja um Administrador.

![image](https://github.com/user-attachments/assets/81f8e3eb-8e36-49d7-83d4-a4c7d14eea58)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

# Utilizando o Controller de Usuários

## 🛠️ Validadores de Usuário

### 1. **Usuários**

| **Campo**              | **Validação**                                                                                                      |
|------------------------|--------------------------------------------------------------------------------------------------------------------|
| **Nome**               | Deve ser um texto não vazio e não deve exceder 45 caracteres.                                                       |
| **Sobrenome**          | Deve ser um texto não vazio e não deve exceder 45 caracteres.                                                       |
| **CPF**                | Deve ser um CPF válido, apenas números e único.                                                                     |
| **Telefone**           | Deve ser um número de telefone válido e não vazio.                                                                   |
| **Tipo**               | Deve ser um valor permitido ('admin', 'usuario').                                                               |
| **Email**              | Deve ser um email válido, não vazio e único.                                                                        |
| **Senha**              | Deve atender aos critérios de complexidade definidos (mínimo de 8 caracteres, incluindo letras maiúsculas e minúsculas, não pode incluir o nome ou sobrenome do usuário, números e nenhuma das palavras comuns definidas no validador). |
| **Data de Nascimento** | Deve ser uma data válida e o usuário deve ter a idade mínima para o registro (15 anos).                               |

---

<br><br>

## ✅ 1. Método de Criação de Novos Usuários

- Para criar um novo usuário, envie uma requisição para o seguinte endereço:

```
POST http://localhost:8080/usuario
```

**Corpo esperado:**

```json
{
    "nome": "Koda",
    "sobrenome": "Silva",
    "cpf": "12345678911",
    "telefone": "11934669846",
    "tipo": "usuario",
    "email": "koda@example.com.br",
    "senha": "Koda2044",
    "data_de_nascimento": "2022-12-31"
}
```
<br>

Se tudo ocorrer conforme esperado, você receberá o Status Code `201`.


![image](https://github.com/user-attachments/assets/b94f6b15-6be2-476c-8bad-1d44bc333bc3)



<br>

Em caso de erros, o Status Code será `400` e a resposta incluirá uma mensagem de erro no formato RFC.

<br>

**📃❌ Algumas mensagens de Erros:**


![image](https://github.com/user-attachments/assets/07fc5482-4ef6-4724-a174-1a59e4424a24)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📃 2. Listagem de Usuários

<br>

- Para listar um usuário individualmente, acesse o mesmo URL, passando o ID do usuário a ser listado. Se tudo estiver correto, você receberá os dados do usuário solicitado.
```
http://localhost:8080/usuario/{id}
```

![image](https://github.com/user-attachments/assets/3b4cb57d-7d44-4a71-8448-84b03b183408)


 
<br>

- Para listar todos os registros de uma entidade, basta acessar o URL:

```
http://localhost:8080/usuario/
```

![image](https://github.com/user-attachments/assets/4cff1592-9c2d-4de3-864c-3a9b2bcdeb07)

 

## 📃 2.1. Métodos de Ordenação e Paginação

- Para ordenar os registros, use:

```
http://localhost:8080/usuario?ordem={NomeDoCampoParaOrdenar}
```
<br>

- Para definir o número de registros por página:

```
http://localhost:8080/usuario?tamanho={NumeroDeQuantosRegistrosDesejaTrazer}
```
<br>

- Para acessar uma página específica:

```
http://localhost:8080/usuario?pagina={QualPaginaDesejaVer}
```

<br><br>

Por padrão, a ordenação é crescente. Para ordenação decrescente, adicione:

```
http://localhost:8080/usuario?ordem={NomeDoCampoParaOrdenar},desc
```


<br>

- Para combinar métodos de ordenação e paginação, use `&`:

```
http://localhost:8080/usuario?tamanho={NumeroDeQuantosRegistrosDesejaTrazer}&ordem={NomeDoCampoParaOrdenar}
```
<br><br>

- **Exemplo de combinação:**

```
http://localhost:8080/usuario?tamanho=1&ordem=cpf,desc
```

![image](https://github.com/user-attachments/assets/0766d068-9198-4c15-9f0c-83c984d3d9a8)

<br>

Essa inclusão cobre a criação de entidades e a utilização do controlador de usuários, detalhando o processo de requisições e métodos disponíveis


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## ❎ 3. Exclusão de Usuário

<br>

Para excluir um usuário, acesse o mesmo URL, passando o ID do usuário a ser excluído. Se tudo estiver correto, você receberá um feedback indicando que a exclusão foi bem-sucedida.
 
![image](https://github.com/user-attachments/assets/eff98137-6060-4adf-b5cc-92185d08743b)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📝✅ 4. **Atualização de Usuários**

Para atualizar um usuário, envie uma requisição para o seguinte endereço:
```
PUT http://localhost:8080/usuario
```

Você **deve enviar o ID do usuário no corpo da requisição**.

**Corpo esperado:**
```json
{
    "id": 1,
    "nome": "Novo Nome",
    "sobrenome": "Novo Sobrenome",
    "email": "novoemail@example.com.br",
    "telefone": "11998765432",
    "cpf": "12345678911",
    "data_de_nascimento": "2000-01-01",
    "senha": "NovaSenha2024"
}
```
<br><br>

## 🛠️ Campos que Podem Ser Atualizados

| **Campo**              | **Descrição**                                        |
|------------------------|------------------------------------------------------|
| **Nome**               | Nome do usuário.                                    |
| **Sobrenome**          | Sobrenome do usuário.                               |
| **Email**              | Email do usuário.                                  |
| **Telefone**           | Número de telefone do usuário.                      |
| **CPF**                | CPF do usuário.                                     |
| **Data de Nascimento** | Data de nascimento do usuário.                      |
| **Senha**              | Senha do usuário.                                   |


OBS.: As mesmas validações de criação são feitas na de Atualização.

---

<br><br>

✅ Se a atualização for bem-sucedida, você receberá o Status Code 200.

![image](https://github.com/user-attachments/assets/75453f34-c5b5-4108-88bf-d7febc627aa2)


<br>

📃❌ **Caso contrário, o Status Code será 400, com uma mensagem de erro formatada de acordo com o padrão RFC**.

![image](https://github.com/user-attachments/assets/48f216eb-d2f8-4a04-90ff-8f896d77859f)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

# Utilizando o Controller de Kartodromos

## 🛠️ Validadores de Kartodromos

### 1. **Kartodromos**

| **Campo**         | **Validação**                                                                                      |
|-------------------|----------------------------------------------------------------------------------------------------|
| **Nome**          | Deve ser um texto não vazio e não deve exceder 45 caracteres.                                     |
| **Rua**           | Deve ser um texto não vazio e não deve exceder 45 caracteres.                                     |
| **Número**        | Deve ser um texto não vazio e não deve exceder 10 caracteres.                                     |
| **Bairro**        | Deve ser um texto não vazio e não deve exceder 45 caracteres.                                     |
| **CEP**           | Deve ser um texto não vazio e não deve exceder 10 caracteres.                                     |
| **Cidade**        | Opcional, máximo 45 caracteres.                                                                   |
| **Estado**        | Opcional, máximo 45 caracteres.                                                                   |
| **Endereco Foto** | Opcional, máximo 100 caracteres.                                                                  |

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br>

## ✅ 1. Método de Criação de Novos Kartodromos

- Para criar um novo kartódromo, envie uma requisição para o seguinte endereço:
```
POST http://localhost:8080/kartodromo
```

**Corpo esperado:**

```json
{
    "nome": "Kartodromo Teste 2",
    "endereco": {
        "rua": "Rua limpa",
        "numero": "91",
        "bairro": "Organizacao",
        "cep": "04864251",
        "cidade": "São Paulo",
        "estado": "SP"
    },
    "endereco_foto": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTstSV_gBxmoI6InGm2epdnEwhGrI05keF-gA&s"
}
```
<br>

Se tudo ocorrer conforme esperado, você receberá o Status Code `201`.


![image](https://github.com/user-attachments/assets/7b183a68-97f0-4651-892e-29f9bbfd7a18)



<br>

Em caso de erros, o Status Code será `400` e a resposta incluirá uma mensagem de erro no formato RFC.

<br>

**📃❌ Algumas mensagens de Erros:**


![image](https://github.com/user-attachments/assets/0f9b0335-ffad-4402-94e7-82143551c982)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📃 2. Listagem de Kartodromos
<br>

- Para listar um kartódromo individualmente, acesse o mesmo URL, passando o ID do kartódromo a ser listado. Se tudo estiver correto, você receberá os dados do kartódromo solicitado.
  
```
http://localhost:8080/kartodromo/{id}
```


![image](https://github.com/user-attachments/assets/1f564e15-0af9-45eb-aba3-6b3290bd72be)


<br>

- Para listar todos os registros de kartódromos, basta acessar o URL:

```
http://localhost:8080/kartodromo/
```

![image](https://github.com/user-attachments/assets/a861a3e8-c158-4910-8446-2e723ee29694)



## 📃 2.1. Métodos de Ordenação e Paginação

- Para ordenar os registros, use:

```
http://localhost:8080/kartodromo?ordem={NomeDoCampoParaOrdenar}
```
<br>

- Para definir o número de registros por página:

```
http://localhost:8080/kartodromo?tamanho={NumeroDeQuantosRegistrosDesejaTrazer}
```
<br>

- Para acessar uma página específica:

```
http://localhost:8080/kartodromo?pagina={QualPaginaDesejaVer}
```

<br><br>

Por padrão, a ordenação é crescente. Para ordenação decrescente, adicione:

```
http://localhost:8080/kartodromo?ordem={NomeDoCampoParaOrdenar},desc
```

<br>

- Para combinar métodos de ordenação e paginação, use `&`:

```
http://localhost:8080/kartodromo?tamanho={NumeroDeQuantosRegistrosDesejaTrazer}&ordem={NomeDoCampoParaOrdenar}
```

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## ❎ 3. Exclusão de Kartodromo

<br>

Para excluir um kartódromo, acesse o mesmo URL, passando o ID do kartódromo a ser excluído. Se tudo estiver correto, você receberá um feedback indicando que a exclusão foi bem-sucedida.


![image](https://github.com/user-attachments/assets/69233a40-0388-4e82-84a9-f27b4ed48ced)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📝✅ 4. Atualização de Kartodromo

Para atualizar um kartódromo, envie uma requisição para o seguinte endereço:
```
PUT http://localhost:8080/kartodromo
```
Você **deve enviar o ID do kartódromo no corpo da requisição**.

**Corpo esperado:**
```json
{
    "id": 1,
    "nome": "Nome Atualizado",
    "endereco": {
        "rua": "Rua Atualizada",
        "numero": "999",
        "bairro": "Bairro Atualizado",
        "cep": "12345678",
        "cidade": "Cidade Atualizada",
        "estado": "SP"
    },
    "endereco_foto": "https://example.com/nova-imagem.jpg",
    "ativo": 1
}
```
<br><br>

## 🛠️ Campos que Podem Ser Atualizados

| **Campo**           | **Descrição**                                       |
|---------------------|-----------------------------------------------------|
| **Nome**            | Nome do kartódromo.                                |
| **Rua**             | Rua do kartódromo.                                 |
| **Número**          | Número do kartódromo.                             |
| **Bairro**          | Bairro do kartódromo.                             |
| **CEP**             | CEP do kartódromo.                                |
| **Cidade**          | Cidade do kartódromo.                             |
| **Estado**          | Estado do kartódromo.                             |
| **Endereco Foto**   | URL da foto do endereço do kartódromo.             |
| **Ativo**           | Estado de ativação do kartódromo (1 para ativo).   |

OBS.: As mesmas validações de criação são feitas na de Atualização.

---

<br><br>

✅ Se a atualização for bem-sucedida, você receberá o Status Code 200.

![image](https://github.com/user-attachments/assets/fd3456ac-5589-4eb6-994b-e2f035b3e033)


<br>

📃❌ **Caso contrário, o Status Code será 400, com uma mensagem de erro formatada de acordo com o padrão RFC**.

![image](https://github.com/user-attachments/assets/63a3bedc-e6de-4b56-b0b0-69910b5574ed)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

# Utilizando o Controller de Campeonatos

## 🛠️ Validadores de Campeonatos

### 1. **Campeonatos**

| **Campo**            | **Validação**                                                                                                 |
|----------------------|---------------------------------------------------------------------------------------------------------------|
| **Nome**             | Deve ser "Crash Kart Championship" ou "Desafio dos Loucos".                                                  |
| **Data Inicial**     | Deve ser uma data válida e não pode ser posterior à Data Final.                                               |
| **Data Final**       | Deve ser uma data válida e não pode ser anterior à Data Inicial.                                               |
| **Ano de Criação**   | Deve ser igual ou superior ao ano atual.                                                                      |
| **Nome Único**       | Não pode haver outro Campeonato com o mesmo Nome, Data Inicial e Data Final.                                 |

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br>

## ✅ 1. Método de Criação de Novos Campeonatos

- Para criar um novo campeonato, envie uma requisição para o seguinte endereço:
```
POST http://localhost:8080/campeonato
```

**Corpo esperado:**

```json
{
    "nome": "Crash Kart Championship",
    "data_inicial": "2024-01-01",
    "data_final": "2024-12-31"
}
```
<br>

Se tudo ocorrer conforme esperado, você receberá o Status Code `201`.


![image](https://github.com/user-attachments/assets/d2ca1a33-963c-44ee-898a-df670983aa99)



<br>

Em caso de erros, o Status Code será `400` e a resposta incluirá uma mensagem de erro no formato RFC.

<br>

**📃❌ Algumas mensagens de Erros:**

![image](https://github.com/user-attachments/assets/3bb72163-a1df-42b2-a39e-1187cb7e4844)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📃 2. Listagem de Campeonatos

<br>

- Para listar um campeonato individualmente, acesse o mesmo URL, passando o ID do campeonato a ser listado. Se tudo estiver correto, você receberá os dados do campeonato solicitado.
```
http://localhost:8080/campeonato/{id}
```

![image](https://github.com/user-attachments/assets/f0e85b05-2234-4263-8cbf-ea960ad2d845)


<br>

- Para listar todos os registros de campeonatos, basta acessar o URL:

```
http://localhost:8080/campeonato/
```

![image](https://github.com/user-attachments/assets/7178c09b-a82c-4234-8171-35413aaf225b)


<br>

## 📃 2.1. Métodos de Ordenação e Paginação

- Para ordenar os registros, use:

```
http://localhost:8080/campeonato?ordem={NomeDoCampoParaOrdenar}
```
<br>

- Para definir o número de registros por página:

```
http://localhost:8080/campeonato?tamanho={NumeroDeQuantosRegistrosDesejaTrazer}
```
<br>

- Para acessar uma página específica:

```
http://localhost:8080/campeonato?pagina={QualPaginaDesejaVer}
```

<br><br>

Por padrão, a ordenação é crescente. Para ordenação decrescente, adicione:

```
http://localhost:8080/campeonato?ordem={NomeDoCampoParaOrdenar},desc
```

<br>

- Para combinar métodos de ordenação e paginação, use `&`:

```
http://localhost:8080/campeonato?tamanho={NumeroDeQuantosRegistrosDesejaTrazer}&ordem={NomeDoCampoParaOrdenar}
```
<br><br>

- **Exemplo de combinação:**

```
http://localhost:8080/campeonato?tamanho=1&ordem=nome,desc
```

![image](https://github.com/user-attachments/assets/0766d068-9198-4c15-9f0c-83c984d3d9a8)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## ❎ 3. Exclusão de Campeonato

<br>

Para excluir um campeonato, acesse o mesmo URL, passando o ID do campeonato a ser excluído. Se tudo estiver correto, você receberá um feedback indicando que a exclusão foi bem-sucedida.

![image](https://github.com/user-attachments/assets/e2dc9d72-55fd-4ef7-8dc7-dc6944f4fb5f)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📝✅ 4. **Atualização de Campeonatos**

Para atualizar um campeonato, envie uma requisição para o seguinte endereço:

```
PUT http://localhost:8080/campeonato
```

Você **deve enviar o ID do campeonato no corpo da requisição**.

**Corpo esperado:**
```json
{
    "id": 1,
    "nome": "Desafio dos Loucos",
    "data_inicial": "2024-05-01",
    "data_final": "2024-11-30"
}
```
<br><br>

## 🛠️ Campos que Podem Ser Atualizados

| **Campo**           | **Descrição**                                 |
|---------------------|-----------------------------------------------|
| **Nome**            | Nome do campeonato.                          |
| **Data Inicial**    | Data de início do campeonato.                 |
| **Data Final**      | Data de término do campeonato.                |

OBS.: As mesmas validações de criação são feitas na de Atualização.

---

<br><br>

✅ Se a atualização for bem-sucedida, você receberá o Status Code `200`.

![image](https://github.com/user-attachments/assets/42b31d53-8731-46a7-ba9c-4e7cd5647791)


<br>

📃❌ **Caso contrário, o Status Code será `400`, com uma mensagem de erro formatada de acordo com o padrão RFC**.

![image](https://github.com/user-attachments/assets/f526edae-7385-4467-8753-05fb400d254a)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

# Utilizando o Controller de Corridas

<br><br>

## 🛠️ Validadores de Corridas

### 1. **Corridas**

| **Campo**            | **Validação**                                                                                                      |
|----------------------|--------------------------------------------------------------------------------------------------------------------|
| **Campeonato_ID**    | Deve existir um campeonato no sistema e o campeonato deve estar ativo.                                             |
| **Kartodromo_ID**    | Deve existir um kartódromo no sistema e o kartódromo deve estar ativo.                                             |
| **Nome**             | Deve ser único para o mesmo Campeonato, Data, Horário e Categoria.                                                 |
| **Data**             | Deve estar dentro do período de início e término do Campeonato.                                                     |
| **Horário**          | Deve permitir apenas duas corridas no mesmo horário.                                                               |
| **Transmissão**      | Opcional. Pode ser verdadeiro ou falso.                                                                            |
| **Categoria**        | No Campeonato "Desafio dos Loucos", deve ser "DDL_90". No Campeonato "Crash Kart Championship", deve ser "CKC_95" ou "CKC_110". |
| **Classificação**    | Deve ser um texto não vazio.                                                                                         |
| **Código**           | Deve ser único.                                                                                                     |
| **Preço**            | Deve ser um valor decimal maior ou igual a 0.                                                                     |

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br>

## ✅ 1. Método de Criação de Novas Corridas

- Para criar uma nova corrida, envie uma requisição para o seguinte endereço:
  
```
POST http://localhost:8080/corrida
```

**Corpo esperado:**

```json
{
    "campeonato_id": 1,
    "kartodromo_id": 2,
    "nome": "CKC Etapa 1",
    "data": "2024-07-15",
    "horario": "14:00:00",
    "transmissao": true,
    "categoria": "LIVRE",
    "classificacao": " CKC_95",
    "codigo": "GPV2024",
    "preco": 150.00
}
```

<br>

Se tudo ocorrer conforme esperado, você receberá o Status Code `201`.

![image](https://github.com/user-attachments/assets/f7a80197-5ece-4665-bdea-7f7c838a8aca)


<br>

Em caso de erros, o Status Code será `400` e a resposta incluirá uma mensagem de erro no formato RFC.

<br>

**📃❌ Algumas mensagens de Erros:**

- Caso você informe um Campeonato ou kartódromo que não existem, você recebera um erro 404(Not Found) ou 400 (Bad Request)

<br>

![image](https://github.com/user-attachments/assets/63c6ece5-8778-4b5a-91ea-17e07c19e9ac)

<br>

- Caso você tente iniciar a corrida fora das datas do Campeonato

![image](https://github.com/user-attachments/assets/ea0d8cae-aadc-4da5-9daf-3cdc6af892f8)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📃 2. Listagem de Corridas

<br>

- Para listar uma corrida individualmente, acesse o mesmo URL, passando o ID da corrida a ser listado. Se tudo estiver correto, você receberá os dados da corrida solicitada.
```
http://localhost:8080/corrida/{id}
```

![image](https://github.com/user-attachments/assets/64b1a886-7a75-4615-a528-b19e2f11538c)


<br>

- Para listar todos os registros de corridas, basta acessar o URL:

  
`OBS.:` As corridas são ordenadas pela Data.

```
http://localhost:8080/corrida/
```

<br>

## 📃 2.1. Métodos de Ordenação e Paginação

- Para ordenar os registros, use:

```
http://localhost:8080/corrida?ordem={NomeDoCampoParaOrdenar}
```
<br>

- Para definir o número de registros por página:

```
http://localhost:8080/corrida?tamanho={NumeroDeQuantosRegistrosDesejaTrazer}
```
<br>

- Para acessar uma página específica:

```
http://localhost:8080/corrida?pagina={QualPaginaDesejaVer}
```

<br><br>

Por padrão, a ordenação é crescente. Para ordenação decrescente, adicione:

```
http://localhost:8080/corrida?ordem={NomeDoCampoParaOrdenar},desc
```

<br>

- Para combinar métodos de ordenação e paginação, use `&`:

```
http://localhost:8080/corrida?tamanho={NumeroDeQuantosRegistrosDesejaTrazer}&ordem={NomeDoCampoParaOrdenar}
```
<br><br>

- **Exemplo de combinação:**

```
http://localhost:8080/corrida?tamanho=1&ordem=data,desc
```

<br>

## 📃 2.2. Métodos de Filtros para Listagem

**Parâmetros Opcionais:**

| Parâmetro   | Descrição                                     | Exemplo                    | Valores aceitos            |
|-------------|-----------------------------------------------|----------------------------|----------------------------|
| `kartodromo`| Nome do kartódromo                            | Kartodromo%20KGV           | Nomes dos Kartodromos Cadastrados (Ignora o Case - UpperCase/LowerCase) |
| `mes`        | Mês da corrida                               | 12 (dezembro)              | Número dos meses (1-12)             |
| `dia`        | Dia da corrida                               | 15                         | Número dos dias do Mês (1-31)       |
| `nome`       | Nome da corrida                              | CKC%20etapa%201.5          | Nomes das Corridas Cadastradas (Ignora o Case - UpperCase/LowerCase) |
| `check`      | Se é para Check-in ou Check-out a exibição   | check=true                 | true ou false |

- Filtrar por dia

```
http://localhost:8080/corrida?dia=14
```

<br>

- Filtrar por mês

```
http://localhost:8080/corrida?mes=12
```

<br>

- Filtrar por nome

```
http://localhost:8080/corrida?nome=CKC%20etapa%1
```

<br>

- Filtrar por kartodromo

```
http://localhost:8080/corrida?kartodromo=Kartodromo%20Teste%203
```

<br><br>

### Filtrar Corridas que tenham Check-ins feitos

- Filtro para listagem de todas as corridas que tenham Check-ins feitos

```
http://localhost:8080/corrida?check=true
```

![image](https://github.com/user-attachments/assets/e3abe5ad-36bd-4b9b-b844-ba2ef3688937)

<br>

**Nota:** Você pode combinar esse Filtro com os outros disponiveis na corrida, como nome, kartodromo, dia e mes, basta colocar &parametro=valor

<br>

```
http://localhost:8080/corrida?check=true&nome=ckc
```

<br>

### Combinação de Filtros

**Nota:** Perceba que em vez de Espaço entre as diferentes palavras, usamos o %20, ou seja, em vez de Kartodromo KGV ficaria Kartodromo%20KGV

- Filtro de Kartodromo + Filtro de Mês

```
http://localhost:8080/corrida?kartodromo=Kartodromo%20KGV&mes=12
```
<br>

![image](https://github.com/user-attachments/assets/ece4d156-e4ef-4ad7-8083-ff9bf0537808)



<br><br>

- Combinando todos os Filtros
```
http://localhost:8080/corrida?kartodromo=Kartodromo%20Teste%203&mes=12&dia=14&nome=CKC%20etapa%201.5
```

<br>

![image](https://github.com/user-attachments/assets/bd5d2eda-3187-42aa-a854-bf9f46d12b96)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## ❎ 3. Exclusão de Corrida

<br>

Para excluir uma corrida, acesse o mesmo URL, passando o ID da corrida a ser excluída. Se tudo estiver correto, você receberá um feedback indicando que a exclusão foi bem-sucedida.

![image](https://github.com/user-attachments/assets/77ae9bf1-87bc-430e-a2b7-9eef510457b5)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📝✅ 4. **Atualização de Corridas**

Para atualizar uma corrida, envie uma requisição para o seguinte endereço:
```
PUT http://localhost:8080/corrida
```
Você **deve enviar o ID da corrida no corpo da requisição**.

**Corpo esperado:**
```json
{
    "id": 1,
    "campeonato_id": 1,
    "kartodromo_id": 2,
    "nome": "Grande Prêmio de Inverno",
    "data": "2024-08-20",
    "horario": "16:00:00",
    "transmissao": false,
    "categoria": "LIVRE",
    "classificacao": " CKC_110",
    "codigo": "GPI2024",
    "preco": 120.00
}
```
<br><br>

## 🛠️ Campos que Podem Ser Atualizados

| **Campo**            | **Descrição**                                     |
|----------------------|---------------------------------------------------|
| **Campeonato_ID**    | ID do Campeonato associado à corrida.            |
| **Kartodromo_ID**    | ID do Kartódromo associado à corrida.            |
| **Nome**             | Nome da corrida.                                |
| **Data**             | Data da corrida.                                |
| **Horário**          | Horário da corrida.                             |
| **Transmissão**      | Se a corrida será transmitida.                   |
| **Categoria**        | Categoria da corrida, conforme o campeonato.     |
| **Classificação**    | Classificação da corrida.                        |
| **Código**           | Código único da corrida.                         |
| **Preço**            | Preço da corrida.                                |

OBS.: As mesmas validações de criação são feitas na de Atualização.

---

<br><br>

✅ Se a atualização for bem-sucedida, você receberá o Status Code `200`.

![image](https://github.com/user-attachments/assets/33bf60e7-8029-4bfa-aa7f-6ba7bb8f0ed6)


<br>

📃❌ **Caso contrário, o Status Code será `400`, com uma mensagem de erro formatada de acordo com o padrão RFC**.

![image](https://github.com/user-attachments/assets/7a51d8d4-cc3f-46af-8051-1217d61e3ed4)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

# Utilizando o Controller de Inscrições

<br><br>

## 🛠️ Validadores de Inscrição

| **Validador**                                                      | **Descrição**                                                                                                      |
|--------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|
| **Validador Data de Inscricao Valida**                              | Permite que o usuário se inscreva se a data for anterior ou no dia da corrida. Ex.: Alguém vê a corrida e deseja participar no mesmo dia. |
| **Validador Usuario Unico por Corrida**                             | Garante que cada usuário possa se inscrever apenas uma vez por corrida.                                             |
| **Validador Chave Estrangeira Usuario**                             | Permite a inscrição apenas de usuários existentes e que estejam ativos no sistema.                                  |
| **Validador Chave Estrangeira Corrida**                             | Garante que o usuário se inscreva apenas em corridas existentes e ativas.                                           |
| **Validador Se Usuario ja esta em outra Corrida no mesmo dia e horario** | Não permite que o usuário se inscreva se já estiver inscrito em outra corrida na mesma data e horário.              |

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br>

## ✅ 1. Método de Criação de Inscrição

- Para inscrever um usuário em uma corrida, envie uma requisição para o seguinte endereço:
  
```
POST http://localhost:8080/inscricao
```

**Corpo esperado:**

```json
{
    "corrida_id": 1,
    "usuario_id": 2
}
```

<br>

✅ Se a criação for bem-sucedida, você receberá o Status Code `201`.

![image](https://github.com/user-attachments/assets/de0960c7-07e6-40e8-9b2f-973730c27881)

<br>

📃❌ **Caso contrário, o Status Code será `400`, com uma mensagem de erro formatada de acordo com o padrão RFC.**

<br>

**📃❌ Algumas mensagens de Erros:**

- **Caso o usuário já esteja inscrito na corrida:**
  - Status Code `400` e mensagem de erro informando que o usuário já está inscrito.

  ![image](https://github.com/user-attachments/assets/80aa5cd8-e9e5-4eb3-b1b1-bfe269e200a8)

<br>

- **Caso o usuário já esteja inscrito em outra corrida na mesma data e horário:**
  - Status Code `400` e mensagem informando sobre o conflito de horário.
  
  ![image](https://github.com/user-attachments/assets/593831a6-002d-4efd-af6b-9418ba52f29d)

<br>

- **Caso o usuário tente se inscrever em uma corrida que já aconteceu:**
  - Status Code `400` e mensagem informando que a corrida já ocorreu.
  
  ![image](https://github.com/user-attachments/assets/7a61138e-12d2-4deb-bba7-a8ed5f49b31a)

<br>

- **Caso o usuário não esteja ativo no sistema:**
  - Status Code `400` e mensagem informando que o usuário está inativo.
  
  ![image](https://github.com/user-attachments/assets/87dcd4e2-8944-4fbf-9dff-4ef59c418d9c)

<br>

- **Caso a corrida não esteja ativa no sistema:**
  - Status Code `400` e mensagem informando que a corrida está inativa.
  
  ![image](https://github.com/user-attachments/assets/ef13b224-0962-47b5-b061-e0cf6a1c0b10)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## ✅ 2. Listagem de Inscrições

- Para exibir uma inscrição específica, utilize o seguinte endpoint:

```
GET http://localhost:8080/inscricao/{id}
```

<br>

Se o Id de Inscrição for válido, você receberá o Status Code `200`.

![image](https://github.com/user-attachments/assets/c6bc35f7-1d14-40ad-99c3-335058d6c5ad)

<br>

📃❌ **Caso contrário, o Status Code será `400`, com uma mensagem de erro formatada de acordo com o padrão RFC.**

![image](https://github.com/user-attachments/assets/2d5004f3-322a-4d1f-a1e4-8bbec315b05c)

---


- Para exibir todas as inscrições, utilize o seguinte endpoint:

```
GET http://localhost:8080/inscricao
```

**Nota:** As inscrições são ordenadas pelo ID da corrida por padrão.

![image](https://github.com/user-attachments/assets/68997bf9-4ea1-4612-be4c-ac51a59b7cb6)


<br><br>

## 2.1. Exibir todas as Inscrições de uma Corrida

- Para exibir as inscrições de uma corrida específica, utilize o endpoint abaixo:

```
GET http://localhost:8080/inscricao?id_corrida=2
```

![image](https://github.com/user-attachments/assets/dbc239af-7d1f-41d9-8865-97c213bef2d5)


---

## 📃 2.2. Métodos de Filtros para Listagem

**Parâmetros Opcionais:**

| Parâmetro   | Descrição                                     | Exemplo                    | Valores aceitos            |
|-------------|-----------------------------------------------|----------------------------|----------------------------|
| `id_corrida`| Id da Corrida                      | 1          | Númericos e que o Id exista no Banco
| `check`        | Se é para Check-in e Check-out  | true              | true e false
| `status_pagamento`        | Status de Pagamentos nas Inscrições   | pago  | pago, pendente e cancelado

<br>

- Filtrar id de corrida e status de pagamento (pago, cancelado, pendente)

```
GET http://localhost:8080/inscricao?id_corrida=2&status_pagamento=pago
```

<br>

- Filtrar corridas para fazer Check-in e Check-out

```
GET http://localhost:8080/inscricao?id_corrida=2&check=true
```

<br>

- Filtrar id de corrida e status de pagamento (pago, cancelado, pendente) e para check-in e check-out

```
GET http://localhost:8080/inscricao?id_corrida=2&status_pagamento=pago&check=true
```

<br>

---

## 📃 2.3. Métodos de Ordenação e Paginação

- **Ordenar por nome de A-Z:**

```
GET http://localhost:8080/inscricao?idCorrida=1&ordem=usuario.nome
```

- **Ordenar por nome de Z-A:**

```
GET http://localhost:8080/inscricao?idCorrida=1&ordem=usuario.nome,desc
```


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>


## ✅ 3. Exclusão de Inscrição

- Para deletar uma inscrição, utilize o seguinte endpoint:

```
DELETE http://localhost:8080/inscricao/{id}
```

<br>

✅ Se a exclusão for bem-sucedida, você receberá o Status Code `204`.

![image](https://github.com/user-attachments/assets/70e1a0ee-fec7-4d7c-8e84-d74bf935746a)

📃❌ **Caso contrário, o Status Code será `400`, com uma mensagem de erro formatada de acordo com o padrão RFC.**

- **Deletar com Status Pago dentro de 7 dias:**
  - Status Code `400` e mensagem informando que inscrições pagas dentro de 7 dias não podem ser removidas.
  
  ![image](https://github.com/user-attachments/assets/ce5da6a4-8595-40bd-8727-844aab2f87d5)

<br>

- **Deletar com Status Pago fora do prazo de 7 dias:**
  - Status Code `200` e mensagem de confirmação da remoção.

  ![image](https://github.com/user-attachments/assets/42901261-755c-4136-9c3d-a51607197216)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📝✅ 4. **Atualização de Inscrições**

- Para atualizar os dados de uma inscrição, utilize o seguinte endpoint:

```
PUT http://localhost:8080/inscricao/{id}
```

Você **deve enviar o ID da Inscrição no corpo da requisição**.

<br>

## 🛠️ Campos que Podem Ser Atualizados

| **Campo**            | **Descrição**                                     |
|----------------------|---------------------------------------------------|
| **Id da Corrida**    | ID da Corrida associado à corrida.            |
| **Id do Usuário**    | ID do Usuário associado ao Usuário.            |
| **Status do Pagamento**  | (`pago`, `pendente`, `cancelado`)  |

OBS.:  validações de criação são feitas na de Atualização.

<br>

✅ Se a atualização for bem-sucedida, você receberá o Status Code `200`.

![image](https://github.com/user-attachments/assets/34c3aff2-8674-449a-88c8-6f129e7e6dd4)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

# Utilizando o Controller de Check-in

<br><br>

## 🛠️ Validadores de Check-in

| **Validador**                           | **Descrição**                                                                                                   |
|-----------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| **Validador de Peso**                   | Permite que o usuário faça check-in se o peso estiver correto conforme as regras da corrida.                    |
| **Validador Chave Estrangeira Usuário** | Permite check-ins apenas para inscrições existentes e com status de pagamento pago.                             |
| **Validador CheckIn já feito**          | Garante que o check-in seja feito apenas uma vez por usuário.                                                   |
| **Validador Usuário Ativo**             | Permite check-ins apenas de usuários que estejam ativos no sistema.                                             |

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br>

## ✅ 1. Método de Criação de Check-in

- Para registrar o check-in de um usuário em uma corrida, envie uma requisição para o seguinte endpoint:
  
```
POST http://localhost:8080/check-in
```

**Corpo esperado:**

```json
{
	"inscricao_id": 9,
	"peso_inicial": 88,
	"lastro": 2
}
```

<br>

✅ Se a criação for bem-sucedida, você receberá o Status Code `201`.

<br>

![image](https://github.com/user-attachments/assets/6927ca68-c4be-47a0-83fe-28adcc909825)

<br>

📃❌ **Em caso de erro, o Status Code será `400`, com uma mensagem de erro formatada de acordo com o padrão RFC.**

<br>

**📃❌ Algumas mensagens de erro comuns:**

- **Check-in já realizado:**
  - Status Code `400` e mensagem informando que o check-in já foi feito.
  
<br>
 
![image](https://github.com/user-attachments/assets/e0062c94-6b71-409c-8476-dcd808b297e5)

<br>

- **Peso insuficiente para a corrida:**
  - Status Code `400` e mensagem informando o peso esperado e o peso total do usuário.

<br>

![image](https://github.com/user-attachments/assets/fbd87354-538a-41d6-9e4f-34ff6b2661e4)

<br>

- **Check-in fora da data da corrida:**
  - Status Code `400` e mensagem informando a data correta da corrida.

<br>

![image](https://github.com/user-attachments/assets/039027f3-a148-4c5b-8146-7d59c83e7989)


<br>

- **Inscrição não paga:**
  - Status Code `400` e mensagem informando que o usuário não realizou o pagamento.

<br>

![image](https://github.com/user-attachments/assets/1010cf97-4e25-43db-a087-79b83e4504f4)

<br>


- **Usuário inativo:**
  - Status Code `400` e mensagem informando que o usuário está inativo no sistema.

<br>

![image](https://github.com/user-attachments/assets/371ad243-067f-44e2-ac0e-2ecc4ae2e760)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## ✅ 2. Listagem de Check-ins

- Para exibir um check-in específico, utilize o seguinte endpoint:

```
GET http://localhost:8080/check-in/{idInscricao}
```

<br>

✅ Se o ID da Inscrição for válido, você receberá o Status Code `200`.

<br>

![image](https://github.com/user-attachments/assets/2873f5a7-821a-4011-aebd-793efbb5fbea)


<br>

📃❌ **Em caso contrário, o Status Code será `400`, com uma mensagem de erro formatada de acordo com o padrão RFC.**


![image](https://github.com/user-attachments/assets/7bc400c6-357f-43bd-bee1-d9d9a02115da)


---

- Para exibir todos os check-ins, utilize o seguinte endpoint:

```
GET http://localhost:8080/check-in
```

**Nota:** Os check-ins são ordenados pela data de criação.

<br>

![image](https://github.com/user-attachments/assets/3ab2163f-8b1d-4f6d-9c14-9aacb53ec919)


<br><br>

### 2.1. Exibir todos os check-ins de uma corrida

- Para listar os check-ins de uma corrida específica, utilize o endpoint abaixo:

```
GET http://localhost:8080/check-in?id_corrida=6
```

**Nota:** Os check-ins são ordenados pela data de criação.

<br>

![image](https://github.com/user-attachments/assets/b7e9377f-2bf1-4142-bf94-d02098bcbf29)

<br><br>

---

## 📃 2.2. Métodos de Filtro para Listagem

**Parâmetros Opcionais:**

| Parâmetro   | Descrição                                | Exemplo                    | Valores Aceitos            |
|-------------|------------------------------------------|----------------------------|----------------------------|
| `id_corrida`| Id da corrida                            | 1                          | Números inteiros e existentes no banco de dados |

<br>

- Filtrar por ID de corrida:

```
GET http://localhost:8080/check-in?id_corrida=2
```

---

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## ✅ 3. Exclusão de Check-in

- Para deletar um check-in, utilize o seguinte endpoint:

```
DELETE http://localhost:8080/check-in/{id}
```

<br>

✅ Se a exclusão for bem-sucedida, você receberá o Status Code `204`.

<br>

![image](https://github.com/user-attachments/assets/f3535a48-63a2-4831-a712-7d1e0d8a0575)


<br>

📃❌ **Em caso contrário, o Status Code será `400`, com uma mensagem de erro formatada de acordo com o padrão RFC.**

- **Tentativa de deletar um check-in inexistente:**
  - Status Code `400` e mensagem informando que o check-in não foi encontrado.

<br>

![image](https://github.com/user-attachments/assets/7b815596-2515-4bed-ba5f-a3b03b9c5389)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📝✅ 4. Atualização de Check-in

- Para atualizar os dados de um check-in, utilize o seguinte endpoint:

```
PUT http://localhost:8080/check-in
```

Você **deve enviar o ID da inscrição no corpo da requisição**.

**JSON esperado:**

```json
{
	"inscricao_id": 9,
	"peso_inicial": 90,
	"lastro": 5
}
```

<br>

## 🛠️ Campos que Podem Ser Atualizados

| **Campo**         | **Descrição**            |
|-------------------|--------------------------|
| **Peso Inicial**  | Peso do usuário no check-in |
| **Lastro**        | Valor do lastro em número inteiro |

**Nota:** As validações da criação também são aplicadas na atualização.

<br>

✅ Se a atualização for bem-sucedida, você receberá o Status Code `200`.

<br>

![image](https://github.com/user-attachments/assets/149995d9-9d3c-49c9-9377-a30bdd3a2288)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📝✅ 5. Quantidade de Check-ins por corrida

- Para verificar a quantidade de check-ins realizados em uma corrida específica, utilize o endpoint:

```
GET http://localhost:8080/check-in/qtdPorCorrida/2
```

- Se houver check-ins já realizados, o retorno será o número total de check-ins.


![image](https://github.com/user-attachments/assets/a6059cf8-baa3-4c0d-ad69-889ab03c1c75)

<br>
  
- Caso contrário, o retorno será `0`.

![image](https://github.com/user-attachments/assets/1f1044eb-6351-4a24-aa72-eb1b2797965b)


<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**

---

<br><br><br>

## 📝 6. Solicitar lista de Pilotos que fizeram Check-in

- Para solicitar a Lista de pilotos que fizeram Check-in em uma determinada corrida, utiliza o seguinte endpoint:

```
GET http://localhost:8080/check-in/compartilhar/{idCorrida}
```

<br>

✅ Se a requisição for bem-sucedida, você receberá o Status Code `200`.

<br>

![image](https://github.com/user-attachments/assets/a06e58a7-e685-4d89-b1b7-1af4aa2aeed2)

<br>

📃❌ **Em caso contrário, o Status Code será `400`, com uma mensagem de erro formatada de acordo com o padrão RFC.**

- **Tentativa de solicitar uma lista de check-in em que a corrida não existe ou está inativa:**
  - Status Code `400` e mensagem informando que a corrida não foi encontrada.

<br>

![image](https://github.com/user-attachments/assets/77c81cf1-a2d5-401a-b73f-ff89e9d8a83d)

<br>

---

<br><br><br>


## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento desse projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[MySQL](https://www.mysql.com)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**

## 📑 Apêndices

- `Confira o Projeto antes de virar API aqui:` ➡️➡️ [Projeto Web via PHP](https://github.com/LarissaSL/SistemaCKC)
- `Confira o Projeto Mobile aqui:` ➡️➡️ [Projeto Mobile](https://github.com/Giuliana09/app-mobile-CKC)
- `Confira o Novo Projeto Web aqui:` ➡️➡️ [Projeto Web via JS](https://github.com/LarissaSL/API_Gerenciador-De-Corridas-de-Kart)

<br>

**🔝 [Voltar ao Índice](#-%C3%ADndice)**
