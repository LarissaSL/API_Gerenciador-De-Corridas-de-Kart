# 🖥️🏎️ Objetivos da API Gerenciador de Corridas de Kart

API desenvolvida para atender as necessidades de um Organizador de Corridas de Kart. Tem como principal função solucionar os problemas do Cliente e ser a Ponte de Comunicação entre Aplicação Web e Mobile.

O projeto Web do Gerenciador já tinha sido feito em PHP, porém notamos que não havia uma boa estrutura, além de não conseguirmos conectar essa Aplicação com a do Mobile. Logo, a solução de criar uma API que lidasse com os dois tipos de APP foi a solução adotada.

## 🚀👩‍💻 Time de Desenvolvimento

-  [Emily Izabelle](https://github.com/em1ky) como Engenheira de Banco de Dados
-  [Giuliana Gralha](https://github.com/Giuliana09) como Engenheira Front-end
-  [Larissa Silva](https://github.com/LarissaSL) como Engenheira Back-end
-  [Leticia Graziele](https://github.com/LeticiaGraziel) como UX/UI e Auxiliar de Banco de Dados
-  [Silvana Sales](https://github.com/SilvanaMenezes) como UX/UI e Fullstack

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

