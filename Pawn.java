import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Pawn extends Piece{

	public Pawn(boolean white, int[] square, int value){
		super(white, square, "pawn", value);
	}

	public Pawn(boolean white, int[] square, boolean hasMoved, int value){
		super(white, square, "pawn", value);
		if(hasMoved) super.move();
	}

	



	public ArrayList<int[]> possibleCaptures(){
		ArrayList<int[]> list = new ArrayList<int[]>();

		int[] squareToCapture = new int[2];
		squareToCapture[0] = getSquareOn()[0];
		squareToCapture[1] = getSquareOn()[1];

		if(isPieceWhite()){
			squareToCapture[1]++;
			squareToCapture[0]++;
			if(squareToCapture[0] >= 0 && squareToCapture[0] < 8){
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToCapture[0] && p.getSquareOn()[1] == squareToCapture[1] && p.isPieceWhite() == false){
						list.add(new int[]{squareToCapture[0], squareToCapture[1]});
					}
				}
			}
			

			squareToCapture[0] -= 2;
			if(squareToCapture[0] >= 0 && squareToCapture[0] < 8){
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToCapture[0] && p.getSquareOn()[1] == squareToCapture[1] && p.isPieceWhite() == false){
						list.add(new int[]{squareToCapture[0], squareToCapture[1]});
					}
				}
			}
		} else{

			squareToCapture[1]--;
			squareToCapture[0]++;
			if(squareToCapture[0] >= 0 && squareToCapture[0] < 8){
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToCapture[0] && p.getSquareOn()[1] == squareToCapture[1] && p.isPieceWhite() == true){
						list.add(new int[]{squareToCapture[0], squareToCapture[1]});
					}
				}
			}
			

			squareToCapture[0] -= 2;
			if(squareToCapture[0] >= 0 && squareToCapture[0] < 8){
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToCapture[0] && p.getSquareOn()[1] == squareToCapture[1] && p.isPieceWhite() == true){
						list.add(new int[]{squareToCapture[0], squareToCapture[1]});
					}
				}
			}

		} //THIS CHECKS IF PAWN CAN CAPTURE



		if(isPieceWhite()){
			int[] toMove = new int[2];
			toMove[0] = getSquareOn()[0];
			toMove[1] = getSquareOn()[1] + 1;
			if(isSquareEmpty(toMove)){
				list.add(toMove);
			}


			

			if(hasPieceMoved() == false){
				//System.out.println("has not moved");
				int[] toMoveTwo = new int[2];
				toMoveTwo[0] = getSquareOn()[0];
				toMoveTwo[1] = getSquareOn()[1] + 2;
				if(isSquareEmpty(toMove) && isSquareEmpty(toMoveTwo)){
					list.add(toMoveTwo);
				}

				
			}
		} else{
			int[] toMove = new int[2];
			toMove[0] = getSquareOn()[0];
			toMove[1] = getSquareOn()[1] - 1;
			if(isSquareEmpty(toMove)){
				list.add(toMove);
			}


			if(hasPieceMoved() == false){
				int[] toMoveTwo = new int[2];
				toMoveTwo[0] = getSquareOn()[0];
				toMoveTwo[1] = getSquareOn()[1] - 2;
				if(isSquareEmpty(toMove) && isSquareEmpty(toMoveTwo)){
					list.add(toMoveTwo);
				}
			}

		}

		return list;
	}

	public ArrayList<int[]> possibleChecks(){
		ArrayList<int[]> list = new ArrayList<int[]>();

		int[] squareToCapture = new int[2];
		squareToCapture[0] = getSquareOn()[0];
		squareToCapture[1] = getSquareOn()[1];

		if(isPieceWhite()){
			squareToCapture[1]++;
			squareToCapture[0]++;
			if(squareToCapture[0] >= 0 && squareToCapture[0] < 8){
				list.add(new int[]{squareToCapture[0], squareToCapture[1]});
			}
			

			squareToCapture[0] -= 2;
			if(squareToCapture[0] >= 0 && squareToCapture[0] < 8){
				list.add(new int[]{squareToCapture[0], squareToCapture[1]});
			}
		} else{

			squareToCapture[1]--;
			squareToCapture[0]++;
			if(squareToCapture[0] >= 0 && squareToCapture[0] < 8){
				list.add(new int[]{squareToCapture[0], squareToCapture[1]});
			}
			

			squareToCapture[0] -= 2;
			if(squareToCapture[0] >= 0 && squareToCapture[0] < 8){
				list.add(new int[]{squareToCapture[0], squareToCapture[1]});
			}

		}
		return list;
	}

	public void updateValue(){
		int value = 100;
		int xval = (int) (30 - Math.abs(6 * ((double) (super.getSquareOn()[0])) - 3.5));
		value += xval;

		if(!isPieceWhite()){
			int yval = 20 * (6 -super.getSquareOn()[1]);
			value += yval;
			super.setValue(value);
		} else{
			int yval = 20 * (super.getSquareOn()[1] - 1);
			value += yval;
			super.setValue(value);
		}

	}

	public int getValueOfMove(int[] square, int count){
		if(count < 10){
			if(square[1] <= 3){
				return 0;
			}
		}
		if(count < 20){
			if(square[1] <= 2){
				return -20;
			}
		} 
		int value = 100;
		int xval = 0;
		if(square[0] == 3 || square[0] == 4){
			xval = 30;
		} else if(square[0] == 2 || square[0] == 5){
			xval = 20;
		} else if(square[0] == 1 || square[0] == 6){
			xval = 10;
		} else if(square[0] == 0 || square[0] == 7){
			xval = 5;
		}

		
		value += xval;

		if(!isPieceWhite()){
			int yval = 20 * (8 -square[1]);
			value += yval;
			
		} else{
			int yval = 20 * (square[1] - 1);
			value += yval;
			
		}

		return value;
		

	}

	public int getIntrinsicValue(){
		return super.getValue() + 10;
	}

}