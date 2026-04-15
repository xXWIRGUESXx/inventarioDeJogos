# Inventário de Jogos - Heloisa de Sales Mariano e Gabriel Wirgues Marques

## Descrição

Este projeto foi desenvolvido para a disciplina de Estruturas de Dados Avançadas. A ideia é simular um sistema de inventário de jogo, onde personagens recebem itens com diferentes níveis de raridade.

O foco do trabalho é aplicar na prática tabelas hash, heaps e geração de números pseudoaleatórios, sem usar bibliotecas prontas.

---

## Estrutura do projeto

* **src/inventario/**: código-fonte em Java
* **bin/**: arquivos compilados
* **resultados_teste.csv**: arquivo com os resultados dos testes

---

## Compilação

Abra o terminal na pasta do projeto e execute:

```
javac -d bin src/inventario/*.java
```

---

## Execução do programa

Para rodar o menu interativo:

```
java -cp bin inventario.Main [parametros]
```

### Parâmetros opcionais

* `seed=NUMERO` → define a seed (ex: seed=42)
* `random=lcg` ou `random=xorshift` → tipo de gerador
* `hash=divisao` ou `hash=multiplicacao` → função hash
* `tamanho=NUMERO` → tamanho da tabela hash

### Exemplo

```
java -cp bin inventario.Main seed=42 random=xorshift hash=multiplicacao tamanho=200
```

---

## Testes de desempenho

Para rodar os testes automáticos:

```
java -cp bin inventario.TesteDesempenho
```

Isso vai gerar o arquivo `resultados_teste.csv` com os dados dos testes.

---

## Execução de TesteDesempenho com parâmetros

Para rodar um teste grande (exemplo):

```
java -cp bin inventario.TesteDesempenho 10000 1000 LCG DIVISAO 42
```

---

## Análise dos resultados

O arquivo CSV pode ser aberto no Excel ou Google Sheets pra analisar:

* tempo de execução
* uso de memória
* número de colisões
* quantidade de operações

---

## Reprodutibilidade

Os testes usam seeds fixas, então dá pra rodar várias vezes e obter os mesmos resultados.

---

## Observações

* Todas as estruturas (hash, heap, etc.) foram feitas do zero
* O sistema é parametrizável por linha de comando
* Dá pra expandir fácil com novos testes ou ajustes

---

## Descrição dos arquivos

- **GeradorDeItens.java**: Gera itens aleatórios usando LCG ou XORShift.
- **Item.java**: Representa um item do inventário.
- **Main.java**: Menu principal e interação com o usuário.
- **MaxHeap.java**: Implementação da heap máxima para raridade dos itens.
- **Node.java**: Nó para listas encadeadas na tabela hash.
- **Personagem.java**: Representa um personagem com inventário próprio.
- **SistemaInventario.java**: Lógica do inventário, integra hash e heap.
- **TabelaHash.java**: Implementação da tabela hash parametrizável.
- **TesteDesempenho.java**: Executa testes automáticos e exporta métricas para CSV.