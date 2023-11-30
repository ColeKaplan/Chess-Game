import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Queen extends Piece{

	public Queen(boolean white, int[] square, int value){
		super(white, square, "queen", value);
	}
	public Queen(boolean white, int[] square, boolean hasMoved, int value){
		super(white, square, "queen", value);
		if(hasMoved) super.move();
	}

	public ArrayList<int[]> possibleChecks(){
		return possibleCaptures();
	}


	public ArrayList<int[]> possibleCaptures(){
		boolean flag = true;
		ArrayList<int[]> list = new ArrayList<int[]>();
		int[] squareToMoveTo = new int[2];
		squareToMoveTo[0] = getSquareOn()[0];
		squareToMoveTo[1] = getSquareOn()[1];
		while(flag && squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 7){
			squareToMoveTo[0]++;
			squareToMoveTo[1]++;
			if(super.isSquareEmpty(squareToMoveTo)){
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
			for(Piece p : super.getPieces()){
				if(p.getSquareOn()[0]==squareToMoveTo[0]&& p.getSquareOn()[1]== squareToMoveTo[1]){
					if(p.isPieceWhite() != isPieceWhite()){
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					}
					flag = false;
				}
			}

			}
		}

		squareToMoveTo[0] = getSquareOn()[0];
		squareToMoveTo[1] = getSquareOn()[1];
		flag = true;
		while(flag && squareToMoveTo[0] > 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 7){
			squareToMoveTo[0]--;
			squareToMoveTo[1]++;
			if(super.isSquareEmpty(squareToMoveTo)){
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
			for(Piece p : super.getPieces()){
				if(p.getSquareOn()[0]==squareToMoveTo[0]&& p.getSquareOn()[1]== squareToMoveTo[1]){
					if(p.isPieceWhite() != isPieceWhite()){
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					}
					flag = false;
				}
			}

			}
		}

		squareToMoveTo[0] = getSquareOn()[0];
		squareToMoveTo[1] = getSquareOn()[1];
		flag = true;
		while(flag && squareToMoveTo[0] > 0 && squareToMoveTo[0] <= 7 && squareToMoveTo[1] > 0 && squareToMoveTo[1] <= 7){
			squareToMoveTo[0]--;
			squareToMoveTo[1]--;
			if(super.isSquareEmpty(squareToMoveTo)){
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
			for(Piece p : super.getPieces()){
				if(p.getSquareOn()[0]==squareToMoveTo[0]&& p.getSquareOn()[1]== squareToMoveTo[1]){
					if(p.isPieceWhite() != isPieceWhite()){
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					}
					flag = false;
				}
			}

			}
		}

		squareToMoveTo[0] = getSquareOn()[0];
		squareToMoveTo[1] = getSquareOn()[1];
		flag = true;
		while(flag && squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 7 && squareToMoveTo[1] > 0 && squareToMoveTo[1] <= 7){
			squareToMoveTo[0]++;
			squareToMoveTo[1]--;
			if(super.isSquareEmpty(squareToMoveTo)){
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
			for(Piece p : super.getPieces()){
				if(p.getSquareOn()[0]==squareToMoveTo[0]&& p.getSquareOn()[1]== squareToMoveTo[1]){
					if(p.isPieceWhite() != isPieceWhite()){
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					}
					flag = false;
				}
			}

			}
		}

		squareToMoveTo[0] = getSquareOn()[0];
		squareToMoveTo[1] = getSquareOn()[1];
		flag = true;
		while(flag && squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 7){
			squareToMoveTo[0]++;
			if(super.isSquareEmpty(squareToMoveTo)){
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
			for(Piece p : super.getPieces()){
				if(p.getSquareOn()[0]==squareToMoveTo[0]&& p.getSquareOn()[1]== squareToMoveTo[1]){
					if(p.isPieceWhite() != isPieceWhite()){
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					}
					flag = false;
				}
			}
			}
			
		}

		squareToMoveTo[0] = getSquareOn()[0];
		squareToMoveTo[1] = getSquareOn()[1];
		flag = true;
		while(flag && squareToMoveTo[0] > 0 && squareToMoveTo[0] <= 7){
			squareToMoveTo[0]--;
			if(super.isSquareEmpty(squareToMoveTo)){
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
			for(Piece p : super.getPieces()){
				if(p.getSquareOn()[0]==squareToMoveTo[0]&& p.getSquareOn()[1]== squareToMoveTo[1]){
					if(p.isPieceWhite() != isPieceWhite()){
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					}
					flag = false;
				}
			}

			}
		}

		squareToMoveTo[0] = getSquareOn()[0];
		squareToMoveTo[1] = getSquareOn()[1];
		flag = true;
		while(flag && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 7){
			squareToMoveTo[1]++;
			if(super.isSquareEmpty(squareToMoveTo)){
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
			for(Piece p : super.getPieces()){
				if(p.getSquareOn()[0]==squareToMoveTo[0]&& p.getSquareOn()[1]== squareToMoveTo[1]){
					if(p.isPieceWhite() != isPieceWhite()){
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					}
					flag = false;
				}
			}

			}
		}

		squareToMoveTo[0] = getSquareOn()[0];
		squareToMoveTo[1] = getSquareOn()[1];
				flag = true;
		while(flag && squareToMoveTo[1] > 0 && squareToMoveTo[1] <= 7){
			squareToMoveTo[1]--;
			if(super.isSquareEmpty(squareToMoveTo)){
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
			for(Piece p : super.getPieces()){
				if(p.getSquareOn()[0]==squareToMoveTo[0]&& p.getSquareOn()[1]== squareToMoveTo[1]){
					if(p.isPieceWhite() != isPieceWhite()){
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					}
					flag = false;
				}
			}

			}
		}

		return list;



	}

	public void updateValue(){
		int value = 900;

		super.setValue(value);

	}

	public int getIntrinsicValue(){
		return 900;
	}

	public int getValueOfMove(int[] square, int count){
		return super.getValue() + 10;
		

	}

}