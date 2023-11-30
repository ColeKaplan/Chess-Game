import java.util.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.*;
import javax.imageio.*;
public class Piece{
	private boolean hasMoved;
	private boolean isWhite;
	private int[] squareOn;
	private int value;
	private String type;
	private ArrayList<Piece> pieces;
	private BufferedImage pic;

	public Piece(boolean white, int[] square, String piecetype, int value){
		hasMoved = false;
		isWhite = white;
		squareOn = new int[2];
		squareOn[0] = square[0];
		squareOn[1] = square[1];
		type = piecetype;
		pieces = new ArrayList<Piece>();
		this.value = value;
		pic = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		try{
		if(isWhite){
			File file = new File("white" + piecetype + ".png");
        	pic = ImageIO.read(file);
		} else{
			File file = new File("black" + piecetype + ".png");
        	pic = ImageIO.read(file);
		}
	} catch(Exception e){
		System.out.println("error");
	}
		
	}




	public void setPieces(ArrayList<Piece> newPieces){
		this.pieces = newPieces;
	}

	public int getValue(){
		return value;
	}

	public int getIntrinsicValue(){
		return 0;
	}

	public ArrayList<Piece> getPieces(){
		return pieces;
	}
	public BufferedImage getPic(){
		return pic;
	}

	public void move(){
		hasMoved = true;
	}

	public boolean hasPieceMoved(){
		return hasMoved;
	}

	public boolean isPieceWhite(){
		return isWhite;
	}

	public int[] getSquareOn(){
		return squareOn;
	}

	public void updateSquareOn(int[] square){
		squareOn = square;
		hasMoved = true;
	}

	public String getType(){
		return type;
	}

	public ArrayList<int[]> possibleChecks(){
		return null;
	}

	public  ArrayList<int[]> possibleCaptures(){
		return new ArrayList<int[]>();
	}

	 public Piece pieceOnSquare(int[] square){
      for(Piece p : pieces){
         if(p.getSquareOn()[0] == square[0] && p.getSquareOn()[1] == square[1]){
            return p;
         }
      }

      return null;
   }

   private boolean isInCheck(){ 

      for(Piece p : pieces){
        if(p.getType().equals("king") && p.isPieceWhite() == isWhite){
          King k = (King) p;
          System.out.println("found king and returned " + k.isInCheck(k.getSquareOn()));
          return k.isInCheck(k.getSquareOn());
        }
      }

      return false;
   }

   public boolean isSquareEmpty(int[] square){

      for(Piece p : pieces){

         if(p.getSquareOn()[0] == square[0] && p.getSquareOn()[1] == square[1]){
       
            return false;
         }
      }
      //System.out.println("squaare iss empty" + square[0] +" , " + square[1]);
      return true;
   }

	public String toString(){
		return "Type: " +  type;
	}

	public void updateValue(){

	}

	public void setValue(int val){
		value = val;

	}

	public int getValueOfMove(int[] square, int count){
		return 0;

	}




}