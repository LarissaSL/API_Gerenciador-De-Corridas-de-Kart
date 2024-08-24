# 🖥️🏎️ Objetivos da API Gerenciador de Corridas de Kart

API desenvolvida para atender as necessidades de um Organizador de Corridas de Kart. Tem como principal função solucionar os problemas do Cliente e ser a Ponte de Comunicação entre Aplicação Web e Mobile.

O projeto Web do Gerenciador já tinha sido feito em PHP, porém notamos que não havia uma boa estrutura, além de não conseguirmos conectar essa Aplicação com a do Mobile. Logo, a solução de criar uma API que lidasse com os dois tipos de APP foi a solução adotada.

## 🚀👩‍💻 Time de Desenvolvimento

-  [Emily Izabelle](https://github.com/em1ky) como Engenheira de Banco de Dados
-  [Giuliana Gralha](https://github.com/Giuliana09) como Engenheira Front-end
-  [Larissa Silva](https://github.com/LarissaSL) como Engenheira Back-end
-  [Leticia Graziele](https://github.com/LeticiaGraziel) como UX/UI e Auxiliar de Banco de Dados
-  [Silvana Sales](https://github.com/SilvanaMenezes) como UX/UI e Fullstack

## 📑 Índice

---


## 🎯 Inclusões

- ✅ Criação do Readme da API

## ⚙️ Funcionalidades

- 🟡 CRUD de Kartodromos
- 🟡 CRUD de Campeonatos
- 🟡 CRUD de Corridas
- 🟡 CRUD de Usuários
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

---

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

![image](https://github.com/user-attachments/assets/71020e7d-9653-4184-a6a1-6521d71fc19e)

---


## Utilizando o Controller de Usuários

### ✅ 1. Método de Criação de Novos Usuários

- Para criar um novo usuário, envie uma requisição para o seguinte endereço:

```
http://localhost:8080/usuario
```

Selecione o método **POST** (caso esteja usando ferramentas como POSTMAN, INSOMNIA, etc).

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

Se tudo ocorrer conforme esperado, você receberá o Status Code `201`.


![image](https://github.com/user-attachments/assets/c0699d7b-fe31-407d-b65c-475fba299e0a)

 

Em caso de erros, o Status Code será `400` e a resposta incluirá uma mensagem de erro no formato RFC.

** 📃❌ Mensagem com vários erros:**


![image](https://github.com/user-attachments/assets/07fc5482-4ef6-4724-a174-1a59e4424a24)


---



### 📃 2. Listagem de Usuários

- Para listar um usuário individualmente, acesse o mesmo URL, passando o ID do usuário a ser listado. Se tudo estiver correto, você receberá os dados do usuário solicitado.
```
http://localhost:8080/usuario/{id}
```

![image](https://github.com/user-attachments/assets/57b41767-7bf9-4cdc-afb0-66fa8a51a3bf)

 

- Para listar todos os registros de uma entidade, basta acessar o URL:

```
http://localhost:8080/usuario/
```

![image](https://github.com/user-attachments/assets/4cff1592-9c2d-4de3-864c-3a9b2bcdeb07)

 

### 📃 2.1. Métodos de Ordenação e Paginação

- Para ordenar os registros, use:

```
http://localhost:8080/usuario?ordem={NomeDoCampoParaOrdenar}
```

- Para definir o número de registros por página:

```
http://localhost:8080/usuario?tamanho={NumeroDeQuantosRegistrosDesejaTrazer}
```

- Para acessar uma página específica:

```
http://localhost:8080/usuario?pagina={QualPaginaDesejaVer}
```



Por padrão, a ordenação é crescente. Para ordenação decrescente, adicione:

```
http://localhost:8080/usuario?ordem={NomeDoCampoParaOrdenar},desc
```



- Para combinar métodos de ordenação e paginação, use `&`:

```
http://localhost:8080/usuario?tamanho={NumeroDeQuantosRegistrosDesejaTrazer}&ordem={NomeDoCampoParaOrdenar}
```

- **Exemplo de combinação:**

```
http://localhost:8080/usuario?tamanho=1&ordem=cpf,desc
```

![image](https://github.com/user-attachments/assets/0766d068-9198-4c15-9f0c-83c984d3d9a8)

 

Essa inclusão cobre a criação de entidades e a utilização do controlador de usuários, detalhando o processo de requisições e métodos disponíveis


---


### ❎3. Exclusão de Usuário

Para excluir um usuário, acesse o mesmo URL, passando o ID do usuário a ser excluído. Se tudo estiver correto, você receberá um feedback indicando que a exclusão foi bem-sucedida.
 
![image](https://github.com/user-attachments/assets/eff98137-6060-4adf-b5cc-92185d08743b)


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

