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
- 🟢 CRUD de Campeonatos
- 🟢 CRUD de Corridas
- 🟢 CRUD de Usuários
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

** 📃❌ Algumas mensagens de Erros:**


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

<br>

** 📃❌ Algumas mensagens de Erros:**


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

---

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

** 📃❌ Algumas mensagens de Erros:**

![image](https://github.com/user-attachments/assets/3bb72163-a1df-42b2-a39e-1187cb7e4844)


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

Essa inclusão cobre a criação de campeonatos e a utilização do controlador de campeonatos, detalhando o processo de requisições e métodos disponíveis.

---

<br><br><br>

## ❎ 3. Exclusão de Campeonato

<br>

Para excluir um campeonato, acesse o mesmo URL, passando o ID do campeonato a ser excluído. Se tudo estiver correto, você receberá um feedback indicando que a exclusão foi bem-sucedida.

![image](https://github.com/user-attachments/assets/e2dc9d72-55fd-4ef7-8dc7-dc6944f4fb5f)


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
---

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

![image](https://github.com/user-attachments/assets/151c6237-0244-486c-a249-5bdcdbcd5182)


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


---

<br><br><br>

## 📃 2. Listagem de Corridas

<br>

- Para listar uma corrida individualmente, acesse o mesmo URL, passando o ID da corrida a ser listado. Se tudo estiver correto, você receberá os dados da corrida solicitada.
```
http://localhost:8080/corrida/{id}
```

![image](https://github.com/user-attachments/assets/db48440f-78b6-4e56-aefb-7323397e0092)


<br>

- Para listar todos os registros de corridas, basta acessar o URL:

  
`OBS.:` As corridas são ordenadas pela Data.

```
http://localhost:8080/corrida/
```

![image](https://github.com/user-attachments/assets/125c22f9-f979-483f-a2fd-1462ce97099b)


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
---

<br><br><br>

## ❎ 3. Exclusão de Corrida

<br>

Para excluir uma corrida, acesse o mesmo URL, passando o ID da corrida a ser excluída. Se tudo estiver correto, você receberá um feedback indicando que a exclusão foi bem-sucedida.

![image](https://github.com/user-attachments/assets/77ae9bf1-87bc-430e-a2b7-9eef510457b5)


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

![image](https://github.com/user-attachments/assets/07665d19-cd58-4799-a57b-5f6e1a6cb382)

<br>

📃❌ **Caso contrário, o Status Code será `400`, com uma mensagem de erro formatada de acordo com o padrão RFC**.

![image](https://github.com/user-attachments/assets/7a51d8d4-cc3f-46af-8051-1217d61e3ed4)


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
- `Confira o Projeto Mobile aqui:` ➡️➡️ [Projeto Mobile](https://github.com/Giuliana09/app-mobile-CKC)
- `Confira o Novo Projeto Web aqui:` ➡️➡️ [Projeto Web via JS](https://github.com/LarissaSL/API_Gerenciador-De-Corridas-de-Kart)

