# Pivit board game
# made by João Campos, Rita Mota, and Simão Santos
#
##################################################
from dataclasses import dataclass

@dataclass
class Piece:
    x: int
    y: int
    player: str
    direction: str = 'y'
    type: str = 'minion'

class bcolors:
    BLUE = '\033[94m'
    GREEN = '\033[92m'
    ENDC = '\033[0m'

##################################################

# Return a start board with side 6 or 8, otherwise return []
def generateBoard(side):
  board = []
  if side == 6:
    board.append(Piece(1, 0, 'A'))
    board.append(Piece(2, 0, 'B'))
    board.append(Piece(3, 0, 'A'))
    board.append(Piece(4, 0, 'B'))
    board.append(Piece(0, 1, 'B', 'x'))
    board.append(Piece(0, 2, 'A', 'x'))
    board.append(Piece(0, 3, 'B', 'x'))
    board.append(Piece(0, 4, 'A', 'x'))
    board.append(Piece(1, 5, 'A'))
    board.append(Piece(2, 5, 'B'))
    board.append(Piece(3, 5, 'A'))
    board.append(Piece(4, 5, 'B'))
    board.append(Piece(5, 1, 'B', 'x'))
    board.append(Piece(5, 2, 'A', 'x'))
    board.append(Piece(5, 3, 'B', 'x'))
    board.append(Piece(5, 4, 'A', 'x'))
  if side == 8:
    board.append(Piece(1, 0, 'A'))
    board.append(Piece(2, 0, 'B'))
    board.append(Piece(3, 0, 'A'))
    board.append(Piece(4, 0, 'A'))
    board.append(Piece(5, 0, 'B'))
    board.append(Piece(6, 0, 'A'))
    board.append(Piece(0, 1, 'B', 'x'))
    board.append(Piece(0, 2, 'A', 'x'))
    board.append(Piece(0, 3, 'B', 'x'))
    board.append(Piece(0, 4, 'B', 'x'))
    board.append(Piece(0, 5, 'A', 'x'))
    board.append(Piece(0, 6, 'B', 'x'))
    board.append(Piece(1, 7, 'A'))
    board.append(Piece(2, 7, 'B'))
    board.append(Piece(3, 7, 'A'))
    board.append(Piece(4, 7, 'A'))
    board.append(Piece(5, 7, 'B'))
    board.append(Piece(6, 7, 'A'))
    board.append(Piece(7, 1, 'B', 'x'))
    board.append(Piece(7, 2, 'A', 'x'))
    board.append(Piece(7, 3, 'B', 'x'))
    board.append(Piece(7, 4, 'B', 'x'))
    board.append(Piece(7, 5, 'A', 'x'))
    board.append(Piece(7, 6, 'B', 'x'))

  return board

# Print board in traditional style manner
def printBoard(board,side):
  x = 0
  y = 0
  separatorLn = '+---+---+---+---+---+---+'
  separatorLf = '| '
  separatorRt = ' '
  if side == 8:
    separatorLn += '---+---+'

  while y < side:
    x = 0

    print(separatorLn)

    line = ''
    while x < side:
      line += separatorLf
      piece = [p for p in board if (p.x == x and p.y == y)]
      if len(piece) == 0:
        line += ' '
      else:
        if piece[0].direction == 'y':
          if piece[0].type == 'minion':
            char = '|'
          else:
            char = '\u256B'
        else:
          if piece[0].type == 'minion':
            char = '\u2500'
          else:
            char = '\u256A'

        if piece[0].player == 'A':
          line += bcolors.BLUE + char + bcolors.ENDC
        else:
          line += bcolors.GREEN + char + bcolors.ENDC
      board = [p for p in board if not (p.x == x and p.y == y)]
      line += separatorRt
      x += 1
    line += separatorLf

    y += 1
    print(line)

  print(separatorLn)





# Print baord in debug style
def debugBoard(board):
  for p in board:
    print(p)

##################################################

board = generateBoard(6)
printBoard(board,6)
