# Lista de Mercado

A **Lista de Mercado** é uma API RESTful para gerenciar compras e itens de compras, com operações para criar, ler, atualizar e excluir tanto compras quanto itens.

## Endpoints

### **Compra Endpoints**

#### 1. Criar Compra
`POST /compra`

Cria uma nova compra.

### Request Body:
``` json
{
  "nome": "Nome da Compra",
  "dataCompra": "2025-01-24T10:00:00"
}
``` 
Resposta:
Status: 201 Created

#### 2. Listar Compras
`GET /compra`

Recupera uma lista de todas as compras.

Body: Lista de objetos CompraDTO, por exemplo:

``` json

[
{
"nome": "Compra 1",
"dataCompra": "2025-01-24",
"itens": null
},
{
"nome": "Compra 2",
"dataCompra": "2025-01-25",
"itens": null
}
]
```
Resposta:
Status: 200 OK

#### 3. Obter Compra por ID
`GET /compra/{id}`

Recupera os detalhes de uma compra específica pelo id.

Parâmetros:
id (path): ID único da compra (formato UUID)

Body: Objeto CompraDTO, por exemplo:

``` json
{
"nome": "Compra 1",
"dataCompra": "2025-01-24",
"itens": ["Item1", "Item2"]
}
```
Resposta:
Status: 200 OK
Status: 404 Not Found se a compra não for encontrada.

#### 4. Deletar Compra
`DELETE /compra/{id}`

Exclui uma compra pelo id.

Parâmetros:
id (path): ID único da compra (formato UUID)

Resposta:
Status: 204 No Content se a compra for excluída com sucesso.
Status: 404 Not Found se a compra não for encontrada.

#### 5. Atualizar Compra
   `PUT /compra/{id}`

Atualiza uma compra existente.

Parâmetros:
id (path): ID único da compra (formato UUID)
Request Body:
``` json
{
"nome": "Novo Nome",
"dataCompra": "2025-01-25"
}
```
Resposta:
Status: 204 No Content se a compra for atualizada com sucesso.
Status: 404 Not Found se a compra não for encontrada.

### **Item Endpoints**

#### 1. Criar Item
`POST /item/{id}`

Cria um item para uma compra específica.

Parâmetros:
id (path): ID da compra (formato UUID)

Request Body:
``` json
{
"nome": "Nome do Item",
"categoria": "Categoria do Item",
"status": true,
"unidade": "Unidade do Item"
}
```
Resposta:
Status: 201 Created
Body: Vazio

obs: Se criar um item com o mesmo nome, irá somar as unidades.

#### 2. Deletar Item
`DELETE /item/{id}`

Exclui um item pelo id.

Parâmetros:
id (path): ID único do item (formato UUID)

Resposta:
Status: 204 No Content se o item for excluído com sucesso.

#### 3. Listar Itens
`GET /item`

Recupera todos os itens.

Body: Lista de objetos ItemDTO, por exemplo:
``` json
[
{
"nome": "Item 1",
"categoria": "Categoria 1",
"status": true,
"unidade": "3"
},
{
"nome": "Item 2",
"categoria": "Categoria 2",
"status": false,
"unidade": "2"
}
]
```
Resposta:
Status: 200 OK

#### 4. Obter Item por ID
`GET /item/{id}`

Recupera os detalhes de um item específico pelo id.

Parâmetros:
id (path): ID único do item (formato UUID)

Body: Objeto ItemDTO, por exemplo:

``` json
{
"nome": "Item 1",
"categoria": "Categoria 1",
"status": true,
"unidade": "2"
}
```
Resposta:
Status: 200 OK

#### 5. Atualizar Item
`PUT /item/{id}`

Atualiza um item existente.

Parâmetros:
id (path): ID único do item (formato UUID)
Request Body:
```` json
{
"nome": "Novo Nome",
"categoria": "Nova Categoria",
"status": false,
"unidade": "Nova Unidade"
}
````

Resposta:
Status: 204 No Content se o item for atualizado com sucesso.
Status: 404 Not Found se o item não for encontrado.

## Tecnologias Usadas

1. **Spring Boot:** Framework para criação da API.
2. **Java:** Linguagem de programação utilizada para desenvolvimento.
3. **JPA:** Para persistência de dados no banco de dados.
4. **Postgres:** Banco de dados utilizado para persistência.
5. **Maven:** Gerenciador de dependências e build.

