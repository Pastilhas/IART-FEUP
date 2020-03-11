# Inteligência Artificial @ FEUP  (Artificial Inteligence)

Project for the IART class of MIEIC @ FEUP.
Made by [João Campos](https://github.com/Pastilhas), [Rita Mota](https://github.com/atomatir) and [Simão Santos](https://github.com/simao-santos).


# Pivit
### Introduction to the game

[Pivit](https://www.boardgamegeek.com/boardgame/135473/pivit) is game played in a 8x8 checkered board. Two to four players move their pieces strategically in other to have the most pieces when the game reaches a final state.
At the start of the game, the pieces are distributed in the sides the board, excluding the corners, in a way the first movement cannot be a capture.

Each piece can move in only one direction (x or y). Whenever a piece moves, when it reaches its destination, it rotates changing its orientation to be the oposite of the one before. When moving pieces cannot go through one another, but the destination of a piece can be an enemy piece. If the final cell of a movement is an enemy piece, that piece is captured a leaves the board.

There are two types of pieces, 'minions', which must end their movement in a cell of opposite colour of the cell they were in, and 'masters', which can move independently of the colour of the cells. A piece is promoted from 'minion' to 'master' if it enters one of the corners of the board. The game ends when all 'minion' pieces are gone and the winner is the player with most 'master' pieces on the board.


### Introduction to the project

In this project we are going to develop an AI that is capable of playing Pivit using several algorythms in order to win consistently.

---

### How to run
``py pivit.py ``
