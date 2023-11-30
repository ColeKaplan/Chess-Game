import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Knight extends Piece{

	public Knight(boolean white, int[] square, int value){
		super(white, square, "night", value);
	}

	public Knight(boolean white, int[] square, boolean hasMoved, int value){
		super(white, square, "night", value);
		if(hasMoved) super.move();
	}

	public ArrayList<int[]> possibleChecks(){
		return possibleCaptures();
	}

	

	public ArrayList<int[]> possibleCaptures(){
		ArrayList<int[]> list = new ArrayList<int[]>();
		int[] squareToMoveTo = new int[2];

		squareToMoveTo[0] = super.getSquareOn()[0] + 2;
		squareToMoveTo[1] = super.getSquareOn()[1] + 1;

		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 8 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 8){
			if(super.isSquareEmpty(squareToMoveTo)){
				////System.out.println("0");
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
				////System.out.println("usselesss");
			} else {
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToMoveTo[0] && p.getSquareOn()[1] == squareToMoveTo[1] && p.isPieceWhite() != super.isPieceWhite()){
						////System.out.println(p.isPieceWhite() + ", " + super.isPieceWhite());
						//System.out.println("1");
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					} 
				}
			}
		}

		squareToMoveTo[0] = super.getSquareOn()[0] + 2;
		squareToMoveTo[1] = super.getSquareOn()[1] - 1;

		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 8 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 8){
			if(super.isSquareEmpty(squareToMoveTo)){
				////System.out.println("2");
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToMoveTo[0] && p.getSquareOn()[1] == squareToMoveTo[1] && p.isPieceWhite() != super.isPieceWhite()){
						//System.out.println("3");
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					} 
				}
			}
		}

		squareToMoveTo[0] = super.getSquareOn()[0] - 2;
		squareToMoveTo[1] = super.getSquareOn()[1] - 1;

		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 8 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 8){
			if(super.isSquareEmpty(squareToMoveTo)){
				//System.out.println("4");
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToMoveTo[0] && p.getSquareOn()[1] == squareToMoveTo[1] && p.isPieceWhite() != super.isPieceWhite()){
						//System.out.println("5");
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					} 
				}
			}
		}

		squareToMoveTo[0] = super.getSquareOn()[0] - 2;
		squareToMoveTo[1] = super.getSquareOn()[1] + 1;

		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 8 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 8){
			if(super.isSquareEmpty(squareToMoveTo)){
				//System.out.println("6");
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToMoveTo[0] && p.getSquareOn()[1] == squareToMoveTo[1] && p.isPieceWhite() != super.isPieceWhite()){
						//System.out.println("7");
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					} 
				}
			}
		}

		squareToMoveTo[0] = super.getSquareOn()[0] + 1;
		squareToMoveTo[1] = super.getSquareOn()[1] + 2;

		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 8 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 8){
			if(super.isSquareEmpty(squareToMoveTo)){
				//System.out.println("8");
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToMoveTo[0] && p.getSquareOn()[1] == squareToMoveTo[1] && p.isPieceWhite() != super.isPieceWhite()){
						//System.out.println("9");
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					} 
				}
			}
		}

		squareToMoveTo[0] = super.getSquareOn()[0] + 1;
		squareToMoveTo[1] = super.getSquareOn()[1] - 2;

		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 8 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 8){
			if(super.isSquareEmpty(squareToMoveTo)){
				//System.out.println("10");
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToMoveTo[0] && p.getSquareOn()[1] == squareToMoveTo[1] && p.isPieceWhite() != super.isPieceWhite()){
						//System.out.println("11");
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					} 
				}
			}
		}

		squareToMoveTo[0] = super.getSquareOn()[0] - 1;
		squareToMoveTo[1] = super.getSquareOn()[1] + 2;

		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 8 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 8){
			if(super.isSquareEmpty(squareToMoveTo)){
				//System.out.println("12");
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToMoveTo[0] && p.getSquareOn()[1] == squareToMoveTo[1] && p.isPieceWhite() != super.isPieceWhite()){
						//System.out.println("13");
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					} 
				}
			}
		}

		squareToMoveTo[0] = super.getSquareOn()[0] - 1;
		squareToMoveTo[1] = super.getSquareOn()[1] - 2;

		if(squareToMoveTo[0] >= 0 && squareToMoveTo[0] < 8 && squareToMoveTo[1] >= 0 && squareToMoveTo[1] < 8){
			if(super.isSquareEmpty(squareToMoveTo)){
				//System.out.println("14");
				list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
			} else {
				for(Piece p : super.getPieces()){
					if(p.getSquareOn()[0] == squareToMoveTo[0] && p.getSquareOn()[1] == squareToMoveTo[1] && p.isPieceWhite() != super.isPieceWhite()){
						//System.out.println("15");
						list.add(new int[]{squareToMoveTo[0], squareToMoveTo[1]});
					} 
				}
			}
		}

		return list;


	}

	public void updateValue(){
		int value = 300;
		int captures = possibleCaptures().size();
		value += (captures * 20);

		super.setValue(value);

	}

	public int getIntrinsicValue(){
		return 300;
	}

	public int getValueOfMove(int[] square, int count){
		if(square[0] >= 3 && square[0] <= 4 && square[1] >= 3 && square[1] <= 4){
			return super.getValue() + 150;
		} else if(square[0] >= 2 && square[0] <= 5 && square[1] >= 2 && square[1] <= 5){
			return super.getValue() + 100;
		} else if(square[0] >= 1 && square[0] <= 6 && square[1] >= 1 && square[1] <= 6){
			return super.getValue() - 0;
		} else{
			return super.getValue() - 25;
		}

	}

}