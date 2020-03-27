# Pivit board game
# made by João Campos, Rita Mota, and Simão Santos
#
##################################################
from dataclasses import dataclass
from math import copysign

@dataclass()
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

class Board:
  def  __init__(self,side):
    self.side=side
    self.board = []


  # Return a start board with side 6 or 8, otherwise return []
  def generateBoard(self,side):

    if side == 6:
      self.board.append(Piece(1, 0, 'A'))
      self.board.append(Piece(2, 0, 'B'))
      self.board.append(Piece(3, 0, 'A'))
      self.board.append(Piece(4, 0, 'B'))
      self.board.append(Piece(0, 1, 'B', 'x'))
      self.board.append(Piece(0, 2, 'A', 'x'))
      self.board.append(Piece(0, 3, 'B', 'x'))
      self.board.append(Piece(0, 4, 'A', 'x'))
      self.board.append(Piece(1, 5, 'A'))
      self.board.append(Piece(2, 5, 'B'))
      self.board.append(Piece(3, 5, 'A'))
      self.board.append(Piece(4, 5, 'B'))
      self.board.append(Piece(5, 1, 'B', 'x'))
      self.board.append(Piece(5, 2, 'A', 'x'))
      self.board.append(Piece(5, 3, 'B', 'x'))
      self.board.append(Piece(5, 4, 'A', 'x'))
    if side == 8:
      self.board.append(Piece(1, 0, 'A'))
      self.board.append(Piece(2, 0, 'B'))
      self.board.append(Piece(3, 0, 'A'))
      self.board.append(Piece(4, 0, 'A'))
      self.board.append(Piece(5, 0, 'B'))
      self.board.append(Piece(6, 0, 'A'))
      self.board.append(Piece(0, 1, 'B', 'x'))
      self.board.append(Piece(0, 2, 'A', 'x'))
      self.board.append(Piece(0, 3, 'B', 'x'))
      self.board.append(Piece(0, 4, 'B', 'x'))
      self.board.append(Piece(0, 5, 'A', 'x'))
      self.board.append(Piece(0, 6, 'B', 'x'))
      self.board.append(Piece(1, 7, 'A'))
      self.board.append(Piece(2, 7, 'B'))
      self.board.append(Piece(3, 7, 'A'))
      self.board.append(Piece(4, 7, 'A'))
      self.board.append(Piece(5, 7, 'B'))
      self.board.append(Piece(6, 7, 'A'))
      self.board.append(Piece(7, 1, 'B', 'x'))
      self.board.append(Piece(7, 2, 'A', 'x'))
      self.board.append(Piece(7, 3, 'B', 'x'))
      self.board.append(Piece(7, 4, 'B', 'x'))
      self.board.append(Piece(7, 5, 'A', 'x'))
      self.board.append(Piece(7, 6, 'B', 'x'))

  # Return board without piece in position (x,y)
  def removePiece(self,x,y):
    new = [p for p in self.board if not (p.x == x and p.y == y)]
    self.board = new

  # Change x-coordinate of piece to (dx)
  def moveX(self, piece, dx):
    if piece.type == 'minion' and abs(piece.x-dx)%2 == 0:
      print("Minions can only move in odd distances")
      return False

    else:
      inc = copysign(1,dx-piece.x)
      i = piece.x + inc

      while abs(i - dx) > 1:
        if getPiece(self.board,i,piece.y) != None:
          print(getPiece(self.board,i,piece.y))
          return False
        i += inc

      destPiece = getPiece(self.board,dx,piece.y)
      if destPiece != None:
        if destPiece.player == piece.player:
          return False
        else:
          self.removePiece(dx,piece.y)
          debugBoard(self.board)
      piece.x = dx
      return True

  # Change y-coordinate of piece to (dy)
  def moveY(self, piece, dy):
    if piece.type == 'minion' and abs(piece.y-dy)%2 == 0:
      print("Minions can only move in odd distances")
      return False

    else:
      inc = copysign(1,dy-piece.y)
      i = piece.y + inc

    while abs(i - dy) > 1:
      if getPiece(self.board,piece.x,i) != None:
        return False
      i += inc

    destPiece = getPiece(self.board,piece.x,dy)
    if destPiece != None:
      if destPiece.player == piece.player:
        return False
      else:
        self.removePiece(piece.x,dy)
        print("THIS IS THE NEW BOARD")
        debugBoard(self.board)

    piece.y = dy
    return True

  # Change position of piece from (sx,sy) to (dx,dy)
  # Capture piece if destination is enemy piece
  def movePiece(self,sx,sy,dx,dy):
    if not (0 <= sx <= self.side and 0 <= sx <= self.side and 0 <= sx <= self.side and 0 <= sx <= self.side):
      return False

    piece = getPiece(self.board,sx,sy)
    print("Moving piece from player " + piece.player)
    print("From position [" + str(sx) + "," + str(sy)+ "]")
    print("To position [" + str(dx) + "," + str(dy)+ "]")

    if piece == None:
      print("There's no piece in that position")
      return False

    if piece.direction == 'x' and sy == dy:
      if self.moveX(piece, dx):
        return True

    elif piece.direction == 'y' and sx == dx:
      if self.moveY(piece, dy):
        return True

    else:
      print("Pieces can only move in one direction at a time")
      return False
##################################################

# Return piece in position (x,y)
def getPiece(board,x,y):
  piece = [p for p in board if (p.x == x and p.y == y)]
  if len(piece) == 0:
    return None
  return piece[0]

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
      # board = removePiece(board,x,y)
      line += separatorRt
      x += 1
    line += separatorLf

    y += 1
    print(line)

  print(separatorLn)

# Print baord in debug style
def debugBoard(board):
  i=0
  for p in board:
    print("Piece " + str(i) + ": " + str(p))
    i += 1


##################################################

board = Board(6)
board.generateBoard(board.side)
print('')
print('')
printBoard(board.board, 6)
print('')
print('')

f = board.movePiece(0,1,3,1)
# if f:
#   print('moved piece')
# else:
#   print('nope')
# print('')
# print('')
debugBoard(board.board)
printBoard(board.board,6)
# print('')
# print('')

# f = movePiece(board,0,4,1,4)
# if f:
#   print('moved piece')
# else:
#   print('nope')
# print('')
# print('')
# debugBoard(board)
# printBoard(board,6)
# print('')
# print('')

# f = movePiece(board,2,0,2,3)
# if f:
#   print('moved piece')
# else:
#   print('nope')
# print('')
# print('')
# debugBoard(board)
# printBoard(board,6)
# print('')
# print('')

f = board.movePiece(3,0,3,1)
# if f:
#   print('moved piece')
# else:
#   print('nope')
# print('')
# print('')
debugBoard(board.board)
printBoard(board.board,6)
# print('')
# print('')

f = board.movePiece(3,1,3,0)
# if f:
#   print('moved piece')
# else:
#   print('nope')
# print('')
# print('')
debugBoard(board.board)
printBoard(board.board,6)
# print('')
# print('')
