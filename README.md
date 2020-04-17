# Introdução

Este repositório abriga um projeto desenvolvido durante o curso de 
Java da JetBrains Academy o projeto [Tic-Tac-Toe](https://hyperskill.org/learn/step/2180).

O objetivo do projeto era estimular o planejamento de programas complexos 
desde o zero, para isso a JetBrains Academy disponibiliza uma série de 
materiais para você estudar tópicos relacionados as habilidades que ela
acredita que você precisará desenvolver para cumprir o projeto.

A cada etapa do projeto eles informam o seu objetivo, alguns exemplos de 
inputs e outputs esperados para você testar se o comportamento do seu 
programa está dentro do esperado.

## O Jogo.

Ao iniciar o jogo ele irá exibir um tabuleiro com espaços vazios, 
solicitando que o primeiro jogador insira as coordenadas de sua jogada.

As coordenadas devem ser baseadas no plano cartesiano 2D (x, y), sendo
números entre 1 e 3, um tabuleiro regular tem as seguintes coordenadas:

```
|----------------------|
| (1, 3) (2, 3) (3, 3) |
| (1, 2) (2, 2) (3, 2) |
| (1, 1) (2, 1) (3, 1) |
|----------------------|
```

Após a jogada do primeiro jogador é realizada a do segundo e depois a 
do primeiro e assim por diante.

O Jogo possui validação de inputs, um sistema de validação de estado 
(ex. Ainda dá para jogar, o jogador X ou O ganhou ou empate).

### Observações

A pasta test foi criada pela JetBrains durante o desenvolvimento do projeto
para realizar testes na IDE se as etapas estavam sendo cumpridas, mas no 
fim o código do projeto é enviado para a JetBrains academy onde ele é 
avaliado pelos testes deles novamente.