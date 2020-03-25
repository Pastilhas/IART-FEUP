# Pivit board game
# made by João Campos, Rita Mota, and Simão Santos
#
##################################################
from dataclasses import dataclass
from math import copysign

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

# Return piece in position (x,y)
def getPiece(board,x,y):
  piece = [p for p in board if (p.x == x and p.y == y)]
  if len(piece) == 0:
    return None
  return piece[0]

# Return board without piece in position (x,y)
def removePiece(board,x,y):
  new = [p for p in board if not (p.x == x and p.y == y)]
  return new

# Print board in traditional style
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
      piece = getPiece(board,x,y)
      if piece == None:
        line += ' '
      else:
        if piece.direction == 'y':
          if piece.type == 'minion':
            char = '|'
          else:
            char = '\u256B'
        else:
          if piece.type == 'minion':
            char = '\u2500'
          else:
            char = '\u256A'

        if piece.player == 'A':
          line += bcolors.BLUE + char + bcolors.ENDC
        else:
          line += bcolors.GREEN + char + bcolors.ENDC
      board = removePiece(board,x,y)
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

# Change position of piece from (sx,sy) to (dx,dy)
# Capture piece if destination is enemy piece
def movePiece(board,sx,sy,dx,dy):
  if not (0 <= sx <= 6 and 0 <= sy <= 6 and 0 <= dx <= 6 and 0 <= dy <= 6):
    return False

  pieceToMove = getPiece(board,sx,sy)
  if pieceToMove == None:
    return False

  if pieceToMove.direction == 'x' and sy == dy:
    if pieceToMove.type == 'minion' and (sx-dx)%2 == 0:
      return False
    else:
      inc = copysign(1,dx-sx)
      i = sx + inc
      while abs(i - dx) > 1:
        if getPiece(board,i,sy) != None:
          return False
        i += inc
      destPiece = getPiece(board,dx,sy)
      if destPiece != None:
        if destPiece.player == pieceToMove.player:
          return False
        else:
          removePiece(board,dx,sy)
      pieceToMove.x = dx
      return True

  elif pieceToMove.direction == 'y' and sx == dx:
    if pieceToMove.type == 'minion' and (sy-dy)%2 == 0:
      return False
    else:
      inc = copysign(1,sy-dy)
      i = sy + inc
      while i != dy - inc:
        if getPiece(board,sx,i) != None:
          return False
        i += inc
      destPiece = getPiece(board,sx,dy)
      if destPiece != None:
        if destPiece.player == pieceToMove.player:
          return False
        else:
          removePiece(board,sx,dy)
      pieceToMove.y = dy
      return True
  else:
    return False

##################################################

board = generateBoard(6)
print('')
print('')
debugBoard(board)
print('')
print('')
f = movePiece(board,0,1,1,1)
if f:
  print('moved piece')
else:
  print('nope')
print('')
print('')
debugBoard(board)
print('')
print('')
f = movePiece(board,1,0,1,1)
if f:
  print('moved piece')
else:
  print('nope')
print('')
print('')
debugBoard(board)
print('')
print('')
