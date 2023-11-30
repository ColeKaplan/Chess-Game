import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class King extends Piece{

	public King(boolean white, int[] square, int value){
	super(white, square, "king", value);
	}

	public King(boolean white, int[] square, boolean hasMoved, int value){
	super(white, square, "king", value);
	if(hasMoved) super.move();
	}


	public ArrayList<int[]> possibleCaptures(){
		ArrayList<int[]> list = new ArrayList<int[]>();
		int[] squareToMoveTo = new int[2];
		squareToMoveTo[0] = getSquareOn()[0];
		squareToMoveTo[1] = getSquareOn()[1];
		squareToMoveTo[0]++;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			if(isInCheck(squareToMoveTo) == false){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
			}
		}


		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[0]--;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			if(isInCheck(squareToMoveTo) == false){
				//System.out.println("hello buddy");
				//System.out.println(pieceOnSquare(squareToMoveTo));
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
			}
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[0]--;
		squareToMoveTo[1]--;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			if(isInCheck(squareToMoveTo) == false){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
			}
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[0]++;
		squareToMoveTo[1]--;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			if(isInCheck(squareToMoveTo) == false){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
			}
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[0]++;
		squareToMoveTo[1]++;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			if(isInCheck(squareToMoveTo) == false){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
			}
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[0]--;
		squareToMoveTo[1]++;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			if(isInCheck(squareToMoveTo) == false){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
			}
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[1]--;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			if(isInCheck(squareToMoveTo) == false){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
			}
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[1]++;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			if(isInCheck(squareToMoveTo) == false){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
			}
		}



		return list;

	}

	public ArrayList<int[]> possibleChecks(){
		ArrayList<int[]> list = new ArrayList<int[]>();
		int[] squareToMoveTo = new int[2];
		squareToMoveTo[0] = getSquareOn()[0];
		squareToMoveTo[1] = getSquareOn()[1];
		squareToMoveTo[0]++;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
		}


		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[0]--;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[0]--;
		squareToMoveTo[1]--;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[0]++;
		squareToMoveTo[1]--;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[0]++;
		squareToMoveTo[1]++;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[0]--;
		squareToMoveTo[1]++;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[1]--;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
		}

		squareToMoveTo[0] =getSquareOn()[0];
		squareToMoveTo[1] =getSquareOn()[1];
		squareToMoveTo[1]++;
		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] <= 7 && (super.pieceOnSquare(squareToMoveTo) == null || (super.pieceOnSquare(squareToMoveTo).isPieceWhite() != super.isPieceWhite()))){
			list.add(new int[]{squareToMoveTo[0],squareToMoveTo[1]});
		}



		return list;

	}



	public boolean isInCheck(int[] square){
	
	//System.out.println("checking isincheck");
      for(Piece p : super.getPieces()){
      	//System.out.println("checking if this checks me" + p.getType());
         if(p.isPieceWhite() != super.isPieceWhite()){
         	//System.out.println(p.getType());
            ArrayList<int[]> list = p.possibleChecks();
            for(int[] capturableSquare : list){
               if(capturableSquare[0] == square[0] && capturableSquare[1] == square[1]){
               	//System.out.println("capturable at " + capturableSquare[0] + capturableSquare[1] + "by " + p.getType() + p.isPieceWhite());
                  return true;
               }
            }
         }

      }

      return false;
   }

   public void updateValue(){
   	 super.setValue(10000);
   }

   public int getValueOfMove(int[] square, int count){
		
		return 10000;

	}

	public int getIntrinsicValue(){
		return 1000;
	}

}