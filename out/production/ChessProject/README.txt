Chess Code Readme File
----------------------

Run the ChessFrame file and the starting screen appears. 
You can choose to:
1) Challenge the Computer
	Play as white against our self developped ai with a depth of 2
	It would have a FIDE rating around 200
	Expect lag
2) Play 2 Person Chess
	Play as white and black, making the moves for each side
	Use this code to challenge a friend or test lines for yourself
In either mode, click on a piece to move it, and the highlighted squares will be the ones you can move to
The game will not allow you to make an illegal move or move the same colorred piece twice in a row
When the game enters a stalemate or checkmate, black lines will appear between tiles and the game will stop
Exit the code and to play it again repeat this process

Class Description
------------------

ChessFrame:
	ChessFrame creates the GUI and provides the frame for ChessComponent to be displayed on
ChessComponent:
	ChessComponent holds most of the actual gameplay, along with the arraylist of pieces
	ChessComponent draws the pieces onto the board
	ChessComponent has mouselisteners so that clicking on pieces can trigger highlighting, moving, and capturing
Board:
	Board is run frequently by ChessComponent's repaint method and is in charge of drawing the tiles, finishing lines, starting screen, and promotion images
Piece:
	Piece is a parent class for every piece type so that general methods can be called and they can all be in an arraylist together
	Piece tracks all of the individual piece data, but does not have access to pieces, so pieces is frequently updated through a setter method
Knight:
	Redefines move and value for Knights
Bishop:
	Redefines move and value for Bishops
Rook:
	Redefines move and value for Rooks
Queen:
	Redefines move and value for Queens
Pawn:
	Redefines move and value for Pawns
King:
	Redefines move and value for Kings
	Checks if the King is in Check
	
