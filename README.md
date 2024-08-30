# 🖥️🏎️ Objetivos da API Gerenciador de Corridas de Kart

API desenvolvida para atender as necessidades de um Organizador de Corridas de Kart. Tem como principal função solucionar os problemas do Cliente e ser a Ponte de Comunicação entre Aplicação Web e Mobile.

O projeto Web do Gerenciador já tinha sido feito em PHP, porém notamos que não havia uma boa estrutura, além de não conseguirmos conectar essa Aplicação com a do Mobile. Logo, a solução de criar uma API que lidasse com os dois tipos de APP foi a solução adotada.

## 🚀👩‍💻 Time de Desenvolvimento

-  [Emily Izabelle](https://github.com/em1ky) como Engenheira de Banco de Dados
-  [Giuliana Gralha](https://github.com/Giuliana09) como Engenheira Front-end
-  [Larissa Silva](https://github.com/LarissaSL) como Engenheira Back-end
-  [Leticia Graziele](https://github.com/LeticiaGraziel) como UX/UI e Auxiliar de Banco de Dados
-  [Silvana Sales](https://github.com/SilvanaMenezes) como UX/UI e Fullstack

<br><br><br>
## 📑 Índice

---
<br><br><br>
## 🎯 Inclusões

- ✅ Criação do Readme da API
<br><br><br>
## ⚙️ Funcionalidades

- 🟢 CRUD de Kartodromos
- 🟡 CRUD de Campeonatos
- 🟡 CRUD de Corridas
- 🟢 CRUD de Usuários
- 🟡 CRUD de Produtos
- 🟡 CRUD de Classificação das Corridas
- 🟡 Autenticação de Usuários
- 🟡 Compra de Ingressos de Corridas
- 🟡 Carrinho de Compras
- 🟡 Check-out de Pagamentos
- 🟡 Check-in de Pilotos
- 🟡 Check-out de Pilotos
- 🟡 Sorteador de Números de Karts
- 🟡 Compartilhamento via Whatsapp da Lista de Pilotos

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


---

<br><br><br>
# Utilizando o Controller de Usuários

<br><br>
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
** 📃❌ Mensagem com vários erros:**


![image](https://github.com/user-attachments/assets/07fc5482-4ef6-4724-a174-1a59e4424a24)


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


---
<br><br><br>
## ❎ 3. Exclusão de Usuário

<br>
Para excluir um usuário, acesse o mesmo URL, passando o ID do usuário a ser excluído. Se tudo estiver correto, você receberá um feedback indicando que a exclusão foi bem-sucedida.
 
![image](https://github.com/user-attachments/assets/eff98137-6060-4adf-b5cc-92185d08743b)


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

---
<br><br><br>
# Utilizando o Controller de Kartodromos

<br><br>
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

---

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


![image](https://github.com/user-attachments/assets/0f9b0335-ffad-4402-94e7-82143551c982)

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

---
<br><br><br>
## ❎ 3. Exclusão de Kartodromo

<br>
Para excluir um kartódromo, acesse o mesmo URL, passando o ID do kartódromo a ser excluído. Se tudo estiver correto, você receberá um feedback indicando que a exclusão foi bem-sucedida.


![image](https://github.com/user-attachments/assets/69233a40-0388-4e82-84a9-f27b4ed48ced)


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

---




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
- `Confira o Projeto Mobile aqui:` ➡️➡️ [Projeto Mobile](https://github.com/LarissaSL/API_Gerenciador-De-Corridas-de-Kart)
- `Confira o Novo Projeto Web aqui:` ➡️➡️ [Projeto Web via JS](https://github.com/LarissaSL/API_Gerenciador-De-Corridas-de-Kart)

