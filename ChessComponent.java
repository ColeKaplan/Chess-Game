import java.util.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.*;
public class ChessComponent extends JComponent implements MouseListener {
   private String fen;
   private ArrayList<Piece> pieces;
   private boolean whiteToMove;
   private int countMoves;
   private int x;
   private int y;
   private String str;
   private Color[] colors;
   private Piece pieceSelected;
   private boolean promote;
   private Piece toPromote;
   private boolean enPassant;
   private Piece toEnPassant;
   private boolean ai;
   private boolean start;
   private boolean stalemate;
   private boolean gameover;
   
   public ChessComponent(){
      fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
      pieces = new ArrayList<Piece>();
      startPieces();
      pieceSelected = null;
      whiteToMove = true;
      colors = new Color[65];
      setColors();
      countMoves = 1;
      promote = false;
      toPromote = null;
      enPassant = false;
      toEnPassant = null;
      ai = false;
      start = false;
      stalemate = false;
      gameover = false;
   }
   
   public void paintComponent(Graphics g){
      Graphics2D g2 = (Graphics2D) g;
      Board drawBoard = new Board();
      //g2.drawImage(pieces.get(0).getPic(), 50, 50, 100, 100, null);
      
      drawBoard.draw(g2, colors, promote, toPromote, start, ai, gameover);
      /*System.out.println("we finna repaint");*/
      drawStartingPieces(g2);

      
      /*g2.setColor(left.getColor());
      g2.fill(left);
      
      g2.setColor(right.getColor());
      g2.fill(right);
      
      g2.setColor(ball.getColor());
      g2.fill(ball);
      
      g2.setColor(Color.black);
      g2.drawString("Score: " + leftScore, 250, 50);
      
      g2.drawString("Score: " + rightScore, 750, 50);*/
   }
   /*private void setCircles(){
      for(boolean bool : circles){
         bool = false;
      }
   }*/

   private void promote(Piece piece){
    System.out.println("promoting");
      toPromote = piece;
      promote = true;
      if(ai && whiteToMove == false){
        for(int i = 0; i < pieces.size(); i++){
          if(pieces.get(i).getSquareOn()[0] == piece.getSquareOn()[0] && pieces.get(i).getSquareOn()[1] == piece.getSquareOn()[1]){
            pieces.set(i, new Queen(false, piece.getSquareOn(), 900));
          }
        }
      }
   }
   private void setColors(){
      colors[1] = Color.WHITE;
      for(int i = 2; i <= 64; i++){
         if((i % 8) != 1){
         colors[i] = (colors[i-1] == Color.WHITE) ? Color.GRAY : Color.WHITE;
         } else {
            colors[i] = colors[i-1];
         }
      }
   }
   public void drawStartingPieces(Graphics2D g2){
      if(start){
        for(Piece p : pieces){

           //System.out.println(p);
           //System.out.println(p.getSquareOn());
           //System.out.println(pieces);
           int x = p.getSquareOn()[0]*100;
           int y = p.getSquareOn()[1]* (-100) + 700;
           g2.drawImage(p.getPic(), x, y, 100, 100, null);
        }
      }
   }
   public void update(){
      for(Piece p : pieces){
         p.setPieces(pieces);
      }
      makeFen();
      if(!whiteToMove){
         countMoves++;
         whiteToMove = true;
      }  else {
         whiteToMove = false;
      }
   }

   public void startPieces(){
      //System.out.println("sp got called");
      pieces.add(new Rook(true, new int[]{0,0}, 500));
      pieces.add(new Rook(true, new int[]{7,0}, 500));
      pieces.add(new Rook(false, new int[]{7,7}, 500));
      pieces.add(new Rook(false, new int[]{0,7}, 500));
      pieces.add(new Knight(true, new int[]{1,0}, 300));
      pieces.add(new Knight(true, new int[]{6,0}, 300));
      pieces.add(new Knight(false, new int[]{1,7}, 300));
      pieces.add(new Knight(false, new int[]{6,7}, 300));
      pieces.add(new Bishop(true, new int[]{2,0}, 300));
      pieces.add(new Bishop(true, new int[]{5,0}, 300));
      pieces.add(new Bishop(false, new int[]{2,7}, 300));
      pieces.add(new Bishop(false, new int[]{5,7}, 300));
      pieces.add(new Queen(true, new int[]{3,0}, 900));
      pieces.add(new Queen(false, new int[]{3,7}, 900));
      pieces.add(new King(true, new int[]{4,0}, 10000));
      pieces.add(new King(false, new int[]{4,7}, 10000));
      pieces.add(new Pawn(true, new int[]{0,1}, 100));
      pieces.add(new Pawn(true, new int[]{1,1}, 100));
      pieces.add(new Pawn(true, new int[]{2,1}, 100));
      pieces.add(new Pawn(true, new int[]{3,1}, 100));
      pieces.add(new Pawn(true, new int[]{4,1}, 100));
      pieces.add(new Pawn(true, new int[]{5,1}, 100));
      pieces.add(new Pawn(true, new int[]{6,1}, 100));
      pieces.add(new Pawn(true, new int[]{7,1}, 100));
      pieces.add(new Pawn(false, new int[]{0,6}, 100));
      pieces.add(new Pawn(false, new int[]{1,6}, 100));
      pieces.add(new Pawn(false, new int[]{2,6}, 100));
      pieces.add(new Pawn(false, new int[]{3,6}, 100));
      pieces.add(new Pawn(false, new int[]{4,6}, 100));
      pieces.add(new Pawn(false, new int[]{5,6}, 100));
      pieces.add(new Pawn(false, new int[]{6,6}, 100));
      pieces.add(new Pawn(false, new int[]{7,6}, 100));
      
   }

   

   public void makeFen(){
      fen = "";
      int count = 0;
      int[] square = new int[2];
      for(int i = 7; i >= 0; i--){
        count = 0;
         for(int j = 0; j < 8; j++){
            square[0] = j;
            square[1] = i;
            if(!isSquareEmpty(square)){
               if(count != 0){
                  //fen += count;
                  count = 0;
               }
               Piece p = pieceOnSquare(square);
               String c = p.getType().substring(0, 1);
               if(p.isPieceWhite()){
                  c = c.toUpperCase();
               }
               fen += c;
            } else{
               count++;
            }

            if(count != 0){
              if(count != 1) {fen = fen.substring(0, fen.length()-1) + count;}
              else {
                fen += count;
              }
            }

            
         }
         fen += "/";
      } 
      fen = fen.substring(0, fen.length()-1);
      if(whiteToMove){
         fen+= " w";
      } else {
         fen += " b";
      }
      fen += castles();
      fen += " -";
      if(whiteToMove){
         fen += " " + (countMoves /2) + " " + countMoves;
      } else {
         fen += " " + (countMoves / 2 + 1) + " " + countMoves;
      }


       
   }

   /*public void makeBoardFromFen(){
      pieces = new ArrayList<Piece>();
      String position = fen.substring(0, fen.indexOf(" "));
      int[] squareWeTraverse = new int[2];
      squareWeTraverse[0] = 0;
      squareWeTraverse[1] = 7;
      for(int i = 0; i < position.length(); i++){
         String letter = position.substring(i, i+1);
         if(letter.equals("/")){

         } else if(letter.equals("p")){
            pieces.add(new Pawn(false, squareWeTraverse));
         } else if(letter.equals("r")){
            pieces.add(new Rook(false, squareWeTraverse));
         } else if(letter.equals("q")){
            pieces.add(new Queen(false, squareWeTraverse));
         } else if(letter.equals("k")){
            pieces.add(new King(false, squareWeTraverse));
         } else if(letter.equals("n")){
            pieces.add(new Knight(false, squareWeTraverse));
         } else if(letter.equals("b")){
            pieces.add(new Bishop(false, squareWeTraverse));
         } else if(letter.equals("P")){
            pieces.add(new Pawn(true, squareWeTraverse));
         } else if(letter.equals("R")){
            pieces.add(new Rook(true, squareWeTraverse));
         } else if(letter.equals("Q")){
            pieces.add(new Queen(true, squareWeTraverse));
         } else if(letter.equals("K")){
            pieces.add(new King(true, squareWeTraverse));
         } else if(letter.equals("N")){
            pieces.add(new Knight(true, squareWeTraverse));
         } else if(letter.equals("B")){
            pieces.add(new Bishop(true, squareWeTraverse));
         } else{
            int num = Integer.parseInt(letter);
            squareWeTraverse[0] += (num - 1);
         }


         if(letter.equals("/")){
            squareWeTraverse[0] = 0;
            squareWeTraverse[1]--;
         } else {
            squareWeTraverse[0]++;
         }
      }

      String move = fen.substring(fen.indexOf(" ") + 1, fen.indexOf(" ") + 2);
      if(move.equals("w")){
         whiteToMove = true;
      } else{
         whiteToMove = false;
      } 
   }*/

   private String castles(){
      String toReturn = " ";
      Piece p = pieceOnSquare(new int[]{4, 0});
      if(p!= null && p.getType().equals("king")){
         if(!p.hasPieceMoved()){
            Piece krook = pieceOnSquare(new int[]{7,0});
            if(krook != null && krook.getType().equals("rook")){
               if(!krook.hasPieceMoved()){
                  toReturn += "K";
               }
            }
            Piece qrook = pieceOnSquare(new int[]{0,0});
            if(qrook != null && qrook.getType().equals("rook")){
               if(!qrook.hasPieceMoved()){
                  toReturn += "Q";
               }
            }
         }
      }


      Piece blackKing = pieceOnSquare(new int[]{4, 7});
      if(blackKing!= null && blackKing.getType().equals("king")){
         if(!blackKing.hasPieceMoved()){
            Piece bkr = pieceOnSquare(new int[]{7,7});
            if(bkr != null && bkr.getType().equals("rook")){
               if(!bkr.hasPieceMoved()){
                  toReturn += "k";
               }
            }
            Piece bqr = pieceOnSquare(new int[]{0,7});
            if(bqr != null && bqr.getType().equals("rook")){
               if(!bqr.hasPieceMoved()){
                  toReturn += "q";
               }
            }
         }
      }

      return toReturn;
   }

   private Piece pieceOnSquare(int[] square){
      for(Piece p : pieces){
         if(p.getSquareOn()[0] == square[0] && p.getSquareOn()[1] == square[1]){
            return p;
         }
      }

      return null;
   }
   

   private boolean isSquareEmpty(int[] square){
      for(Piece p : pieces){
         if(p.getSquareOn()[0] == square[0] && p.getSquareOn()[1] == square[1]){
            return false;
         }
      }

      return true;
   }

   private boolean isInCheck(Piece current){ 

      for(Piece p : pieces){
        if(p.getType().equals("king") && p.isPieceWhite() == current.isPieceWhite()){
          King k = (King) p;
          //System.out.println("found king and returned " + k.isInCheck(k.getSquareOn()));
          return k.isInCheck(k.getSquareOn());
        }
      }

      return false;
   }
/////AAAAAAAAAAAAAAAAAAAAAAAAAAAA
/////AAAAAAAAAAAAAAAAAAAAAAAAAAAA
/////AAAAAAAAAAAAAAAAAAAAAAAAAAAA
/////AAAAAAAAAAAAAAAAAAAAAAAAAAAA
/////AAAAAAAAAAAAAAAAAAAAAAAAAAAA
   private boolean willNotBeLosingTrade(int[] square, Piece capturedWhitePiece, Piece blackPiece){
    if(blackPiece.getValue() < capturedWhitePiece.getValue()){
      return true;
    }

    Piece q = null;
    for(int i = 0; i < pieces.size(); i++){
      q = pieces.get(i);
      if(q.getType().equals(capturedWhitePiece.getType()) && q.getSquareOn()[0] == capturedWhitePiece.getSquareOn()[0] && q.getSquareOn()[0] == capturedWhitePiece.getSquareOn()[0]){
        pieces.remove(i);
        break;
      }
    }
    blackPiece.setPieces(pieces);

    for(Piece whitePiecesThatCouldCapture : pieces){
      if(whitePiecesThatCouldCapture.isPieceWhite()){ //if its white
        for(int[] possibleMove : avoidCheck(whitePiecesThatCouldCapture, whitePiecesThatCouldCapture.possibleCaptures())){
          if(possibleMove[0] == square[0] && possibleMove[1] == square[1]){
            pieces.add(q);
            blackPiece.setPieces(pieces);
            return false;
          }
        }
      }
    }

    pieces.add(q);
    for(Piece w : pieces){
      w.setPieces(pieces);
    }
    
    return false;

   }

   private boolean isAFreeCapture(int[] square, Piece whitePieceOnSquare, Piece blackPiece){
    Piece q = null;
    for(int i = 0; i < pieces.size(); i++){
      q = pieces.get(i);
      if(q.getType().equals(whitePieceOnSquare.getType()) && q.getSquareOn()[0] == whitePieceOnSquare.getSquareOn()[0] && q.getSquareOn()[0] == whitePieceOnSquare.getSquareOn()[0]){
        pieces.remove(i);
        break;
      }
    }
    for(Piece w : pieces){
      w.setPieces(pieces);
    }

    for(int i = 0; i < pieces.size(); i++){
      Piece whiteDefender = pieces.get(i);
      if(whiteDefender.isPieceWhite()){
        for(int[] move : avoidCheck(whiteDefender, whiteDefender.possibleCaptures())){ //might just have to use possible captures
          if(move[0] == square[0] && move[1] == square[1]){
            pieces.add(q);
            blackPiece.setPieces(pieces);
            return false;
          }
        }
      }
    }

    pieces.add(q);
    for(Piece w : pieces){
      w.setPieces(pieces);
    }
    return true;
   }

   private boolean isAnUncapturableSquare(int[] square){
    if(isSquareEmpty(square) == false){ //ALSO RETURNS FALSE IF SQUARE IS OCCUPIED
      return false;
    }

    for(Piece whiteDefender : pieces){
      if(whiteDefender.isPieceWhite()){
        for(int[] move : avoidCheck(whiteDefender, whiteDefender.possibleCaptures())){
          if(move[0] == square[0] && move[1] == square[1]){
            return false;
          }
        }
      }
    }

    return true;
   }

   private ArrayList<Piece> numProtectors(int[] square){
    ArrayList<Piece> protectors = new ArrayList<Piece>();
    Piece q = null;
    int count = 0;
    Piece piece = pieceOnSquare(square);
    if(piece != null){
      for(int i = 0; i < pieces.size(); i++){
        q = pieces.get(i);
        if(q.getType().equals(piece.getType()) && q.getSquareOn()[0] == piece.getSquareOn()[0] && q.getSquareOn()[0] == piece.getSquareOn()[0]){
          pieces.remove(i);
          break;
        }
      }
      for(Piece w : pieces){
        w.setPieces(pieces);
      }

    }
    

    for(int i = 0; i < pieces.size(); i++){
      Piece blackProtector = pieces.get(i);
      if(!blackProtector.isPieceWhite()){
        for(int[] move : avoidCheck(blackProtector, blackProtector.possibleChecks())){ //PROBLEM
          if(move[0] == square[0] && move[1] == square[1]){
            protectors.add(blackProtector);
          }
        }
      }
    }

    int index = 0;
    ArrayList<Piece> sorted = new ArrayList<Piece>();
    while(protectors.size() > 0){
      index = 0;
      int minimum = protectors.get(0).getValue();
      for(int i = 0; i < protectors.size(); i++){
        if(protectors.get(i).getValue() < minimum){
          minimum = protectors.get(i).getValue();
          index = i;
        }
      }

      sorted.add(protectors.get(index));
      protectors.remove(index);
    }


    return sorted;
   }

   

   private ArrayList<Piece> numAttackers(int[] square){
    ArrayList<Piece> protectors = new ArrayList<Piece>();
    Piece q = null;
    boolean flag = false;
    if(pieceOnSquare(square) != null){
      flag = true;
      Piece piece = pieceOnSquare(square);
      for(int i = 0; i < pieces.size(); i++){
        q = pieces.get(i);
        if(q.getType().equals(piece.getType()) && q.getSquareOn()[0] == piece.getSquareOn()[0] && q.getSquareOn()[0] == piece.getSquareOn()[0]){
          pieces.remove(i);
          break;
        }
      }
      pieces.get(0).setPieces(pieces);
    }
    
    

    for(int i = 0; i < pieces.size(); i++){
      Piece whiteAttacker = pieces.get(i);
      if(whiteAttacker.isPieceWhite()){
        for(int[] move : avoidCheck(whiteAttacker, whiteAttacker.possibleChecks())){
          if(move[0] == square[0] && move[1] == square[1]){
            protectors.add(whiteAttacker);
          }
        }
      }
    }

    if(flag){
      pieces.add(q);
      for(Piece w : pieces){
        w.setPieces(pieces);
      }
    }
    int index = 0;
    ArrayList<Piece> sorted = new ArrayList<Piece>();
    while(protectors.size() > 0){
      index = 0;
      int minimum = protectors.get(0).getValue();
      for(int i = 0; i < protectors.size(); i++){
        if(protectors.get(i).getValue() < minimum){
          minimum = protectors.get(i).getValue();
          index = i;
        }
      }

      sorted.add(protectors.get(index));
      protectors.remove(index);
    }


    return sorted;
   }

   public boolean evaluateBestCaptureForComputer(){
    Piece pieceToMove = null;
    int[] squareToMoveTo = new int[2];
    int maxCaptureValue = 0;

    int index = 0;
    for(int i = 0; i < pieces.size(); i++){
      Piece blackPiece = pieces.get(i);

      if(!blackPiece.isPieceWhite()){ //if the piece is black
        for(int[] possibleBlackMove : avoidCheck(blackPiece, blackPiece.possibleChecks())){ //loop through all the moves
          if(isSquareEmpty(possibleBlackMove) == false && pieceOnSquare(possibleBlackMove).isPieceWhite()){ //if a white piece is on the square to Capture
            Piece whiteToCapture = pieceOnSquare(possibleBlackMove);
            if(whiteToCapture.getValue() > maxCaptureValue && isAFreeCapture(possibleBlackMove, whiteToCapture, blackPiece)){
              pieceToMove = blackPiece;
              index = i;
              squareToMoveTo[0] = possibleBlackMove[0];
              squareToMoveTo[1] = possibleBlackMove[1];
              maxCaptureValue = whiteToCapture.getValue();
            } else if(whiteToCapture.getValue() - blackPiece.getValue() > maxCaptureValue && willNotBeLosingTrade(possibleBlackMove, whiteToCapture, blackPiece)){
              pieceToMove = blackPiece;
              index = i;
              squareToMoveTo[0] = possibleBlackMove[0];
              squareToMoveTo[1] = possibleBlackMove[1];
              maxCaptureValue = whiteToCapture.getValue();
            } else{

            }
          }
        }
      }
    }

    if(maxCaptureValue > 0){
      
      Piece other = pieceOnSquare(squareToMoveTo);
      System.out.println("Piece other is a " + other.getType() + " at " + other.getSquareOn()[0] + " , " + other.getSquareOn()[1]);
      for(Piece z : pieces){
        if(pieceToMove.getType().equals(z.getType()) && pieceToMove.getSquareOn()[0] == z.getSquareOn()[0] && pieceToMove.getSquareOn()[1] == z.getSquareOn()[1]){
          z.updateSquareOn(squareToMoveTo);
        }
        if(z.getType().equals("pawn") && z.getSquareOn()[1] == 0){
          aiPromote(z);
        }
      }
      
      for(int i = 0; i < pieces.size(); i++){
         if(pieces.get(i).equals(other)){
            pieces.remove(i);
         }
      }
      checkCheckMate();
      //System.out.println(pieceSelected.getSquareOn()[0] + ", " + pieceSelected.getSquareOn()[1]);
       
       makeFen();
       //System.out.println(fen);
       countMoves++;
       whiteToMove = !whiteToMove;
       repaint();
      return true;
    }

    return false;
   }


   private boolean moreDefense(ArrayList<Piece> blackProtectors, ArrayList<Piece> whiteAttackers, Piece blackPiece){
    for(int i = 0; i < blackProtectors.size(); i++){
      if(blackProtectors.get(i).getType().equals(blackPiece.getType()) && blackProtectors.get(i).getSquareOn()[0]==(blackPiece.getSquareOn()[0]) && blackProtectors.get(i).getSquareOn()[0]==(blackPiece.getSquareOn()[0])){
        blackProtectors.remove(i);
      }
    }
    if(whiteAttackers.size() == 0){
      return true;
    } 

    if(blackProtectors.size() == 0){
      return false;
    }

    if(blackPiece.getIntrinsicValue() > whiteAttackers.get(0).getIntrinsicValue()){
      return false;
    }

    return moreDefense(blackProtectors, whiteAttackers);

   }

   public boolean moreDefense(ArrayList<Piece> blackProtectors, ArrayList<Piece> whiteAttackers){
      if(whiteAttackers.size() == 0){
        //System.out.println("alli");
        return true;
      } 

      if(blackProtectors.size() == 0){
        //System.out.println("aca");
        return false;
      }


      if(blackProtectors.get(0).getIntrinsicValue() < whiteAttackers.get(0).getIntrinsicValue()){
        //System.out.println("aqui");
        return true;
      } else if(whiteAttackers.size() == 1){
        //System.out.println("alla");
        return true;
      } else if(whiteAttackers.get(1).getIntrinsicValue() > blackProtectors.get(0).getIntrinsicValue()){
        //System.out.println("Cole told you so");
        return false;
      } else{
        //System.out.println("js");
        blackProtectors.remove(0);
        whiteAttackers.remove(0);
        return moreDefense(blackProtectors, whiteAttackers);
      }
   }



   public void evaluateBestNonCaptureForComputer(){
    //this assumes no capture results in a positive way(depth of 2)
    //grreatest increase(Checks wont be captured at square) && hasMoved(giood to move a piece that hasnt moved)
    int maxValueDifference = -100;
    Piece toMove = null;
    int index = 0;
    int[] squareToMoveTo = new int[2];
    for(int i = 0; i < pieces.size(); i++){
      if(pieces.get(i).isPieceWhite()){

      } else{


      Piece p = pieces.get(i);
      if(countMoves < 6 && pieces.get(i).hasPieceMoved() == false){
        for(int[] possibleMove : avoidCheck(p, p.possibleCaptures())){
          int diff = pieces.get(i).getValueOfMove(possibleMove, countMoves) - pieces.get(i).getValue();
          //System.out.println(p.getType() + " has the move value of " + diff + " at " + possibleMove[0] + " , " + possibleMove[1]);
          if(diff > maxValueDifference && moreDefense(numProtectors(possibleMove), numAttackers(possibleMove), pieces.get(i))){

            index = i;
            toMove = p;
            maxValueDifference = diff;
            squareToMoveTo[0] = possibleMove[0];
            squareToMoveTo[1] = possibleMove[1];
          }

        }

      } else{
        for(int[] possibleMove : avoidCheck(p, p.possibleCaptures())){
          int diff = pieces.get(i).getValueOfMove(possibleMove, countMoves) - pieces.get(i).getValue();
          if(diff > maxValueDifference && moreDefense(numProtectors(possibleMove), numAttackers(possibleMove), pieces.get(i))){
            index = i;
            toMove = p;
            maxValueDifference = diff;
            squareToMoveTo[0] = possibleMove[0];
            squareToMoveTo[1] = possibleMove[1];
          } 
      }
      
    }
  }


   }

   if(toMove == null){
    //System.out.println("this code sucks");
     Piece z = null;
     int i = 0;
     while(pieces.get(i).isPieceWhite() != false){
      i++;
     }
     z = pieces.get(i);
     index = i;
     int[] possibleMove = z.possibleCaptures().get(0);
     squareToMoveTo[0] = possibleMove[0];
     squareToMoveTo[1] = possibleMove[1];

   }

   pieces.get(index).updateSquareOn(squareToMoveTo);
   Piece z = pieces.get(index);
   if(z.getType().equals("pawn") && z.getSquareOn()[1] == 0){
      aiPromote(z);
    }
   checkCheckMate();
      //System.out.println(pieceSelected.getSquareOn()[0] + ", " + pieceSelected.getSquareOn()[1]);
   //setColors();
       
   makeFen();
   //System.out.println(fen);
   countMoves++;
   whiteToMove = !whiteToMove;
   repaint();
}

public void randomAIMove(){
  for(int i = 0; i < pieces.size(); i++){
    Piece p = pieces.get(i);
    if(p.isPieceWhite() == false){
      //System.out.println(p.getType() + " is currently at square " + p.getSquareOn()[0] + " , " + p.getSquareOn()[1]);
      int[] squareToMoveTo = new int[2];
      if(avoidCheck(p, p.possibleCaptures()).size() > 0){
        
        squareToMoveTo[0] = avoidCheck(p, p.possibleCaptures()).get(0)[0];
        squareToMoveTo[1] = avoidCheck(p, p.possibleCaptures()).get(0)[1];
        //System.out.println(squareToMoveTo[0] + ", " + squareToMoveTo[1]);
        pieces.get(i).updateSquareOn(squareToMoveTo);
        //System.out.println(p.getSquareOn()[0] + " , " + p.getSquareOn()[1]);
        checkCheckMate();
        //setColors();
        countMoves++;
        whiteToMove = !whiteToMove;
               //System.out.println("got here empty");
        repaint();
        return;
        

        /*if(isSquareEmpty(squareToMoveTo)){
              //System.out.println("also got to the moving of a piece");
               //p.updateSquareOn(squareToMoveTo);
               checkCheckMate();
               setColors();
               //ieceSelected = null;
               makeFen();
               System.out.println(fen);
               countMoves++;
               whiteToMove = !whiteToMove;
               //System.out.println("got here empty");
               repaint();
    //aiMove();
               //randomAIMove();

            } else {
               Piece other = pieceOnSquare(squareToMoveTo);
               if(other.isPieceWhite() != (p.isPieceWhite())){
                  for(int j = 0; j < pieces.size(); i++){
                     if(pieces.get(j).equals(other)){
                        pieces.remove(j);
                     }
                  }
                  //System.out.println("got to the moving of a piece");
                  //System.out.println("other is " + other.getType());
                  //System.out.println("pieceselected is " + pieceSelected.getType());
                  //System.out.println(pieceSelected.getSquareOn()[0] + ", " + pieceSelected.getSquareOn()[1]);
                  //p.updateSquareOn(squareToMoveTo);
                  checkCheckMate();
                  //System.out.println(pieceSelected.getSquareOn()[0] + ", " + pieceSelected.getSquareOn()[1]);
                  setColors();
                  pieceSelected = null;
                  makeFen();
                  System.out.println(fen);
                  countMoves++;
                  whiteToMove = !whiteToMove;
                  //System.out.println("got here other");
                  repaint();
    //aiMove();
                  //randomAIMove();
               }*/

      }
    }

    
  }



}

public void aiPromote(Piece z){
  for(int i = 0; i < pieces.size(); i++){
    Piece toCheck = pieces.get(i);
    if(z.getType().equals(toCheck.getType()) && z.getSquareOn()[0] == toCheck.getSquareOn()[0] && z.getSquareOn()[1] == toCheck.getSquareOn()[1]){
      pieces.remove(i);
      Piece toAdd = new Queen(toCheck.isPieceWhite(), toCheck.getSquareOn(), 900);
      pieces.add(toAdd);
      toAdd.setPieces(pieces);
      repaint();
    }


  }
}



   public void aiMove(){
    /*if(countMoves == 1){ //firstMoveCode
      int[] queenSquare = new int[2];
      queenSquare[0] = 3;
      queenSquare[1] = 3;
      int[] kingKnight = new int[2];
      kingKnight[0] = 5;
      kingKnight[1] = 2;
      if(!isSquareEmpty(queenSquare) || !isSquareEmpty(kingKnight)){
        int[] a = new int[2];
        a[0] = 3;
        a[1] = 6;
        Piece z = pieceOnSquare(a);
        z.updateSquareOn(a);
        //checkCheckMate();
      //System.out.println(pieceSelected.getSquareOn()[0] + ", " + pieceSelected.getSquareOn()[1]);
        setColors();
       
        makeFen();
        System.out.println(fen);
        countMoves++;
        whiteToMove = !whiteToMove;
        repaint();
        return;
      } else{
        int[] a = new int[2];
        a[0] = 4;
        a[1] = 6;
        pieceOnSquare(a).updateSquareOn(a);
        //checkCheckMate();
      //System.out.println(pieceSelected.getSquareOn()[0] + ", " + pieceSelected.getSquareOn()[1]);
        setColors();
       
        makeFen();
        System.out.println(fen);
        countMoves++;
        whiteToMove = !whiteToMove;
        repaint();
        return;
      }
    }*/

    if(evaluateBestCaptureForComputer() == true){
      System.out.println("capture");
      //evaluateBestCaptureForComputer();
    } else{
      //System.out.println("not capture");
      evaluateBestNonCaptureForComputer();
    }

    

   }
/////AAAAAAAAAAAAAAAAAAAAAAAAAAAA
/////AAAAAAAAAAAAAAAAAAAAAAAAAAAA
/////AAAAAAAAAAAAAAAAAAAAAAAAAAAA
/////AAAAAAAAAAAAAAAAAAAAAAAAAAAA
/////AAAAAAAAAAAAAAAAAAAAAAAAAAAA

   
   private ArrayList<int[]> avoidCheck(Piece current, ArrayList<int[]> possibleCaptures){
    if(current.getType().equals("pawn") && enPassant){
        possibleCaptures = enPassant(current, possibleCaptures);
      }
    //System.out.println("current type: " + current.getType());
      ArrayList<Piece> storePieces = new ArrayList<Piece>();
      ArrayList<int[]> list = new ArrayList<int[]>();
      for(Piece original : pieces){
          String letter = original.getType().substring(0,1);
          if(letter.equals("p")){
            storePieces.add(new Pawn(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } else if(letter.equals("r")){
            storePieces.add(new Rook(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } else if(letter.equals("q")){
            storePieces.add(new Queen(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } else if(letter.equals("k")){
            storePieces.add(new King(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } else if(letter.equals("n")){
            storePieces.add(new Knight(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } else if(letter.equals("b")){
            storePieces.add(new Bishop(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } 
      }
      for(int[] square : possibleCaptures){
        //System.out.println(square[0] + ", " + square[1]);
        pieces = new ArrayList<Piece>();
        for(Piece original : storePieces){
            String letter = original.getType().substring(0,1);
            if(letter.equals("p")){
              pieces.add(new Pawn(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
           } else if(letter.equals("r")){
              pieces.add(new Rook(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
           } else if(letter.equals("q")){
              pieces.add(new Queen(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
           } else if(letter.equals("k")){
              pieces.add(new King(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
           } else if(letter.equals("n")){
              pieces.add(new Knight(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
           } else if(letter.equals("b")){
              pieces.add(new Bishop(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
           } 
        }
        Piece getRidOf = null;
        for(Piece findCurrent : pieces){
          if(findCurrent.getSquareOn()[0] == current.getSquareOn()[0] && findCurrent.getSquareOn()[1] == current.getSquareOn()[1]){
            findCurrent.updateSquareOn(square);
            if(pieceOnSquare(square) != null){
              for(Piece findBadPiece : pieces){
                if(findBadPiece.getSquareOn()[0] == square[0] && findBadPiece.getSquareOn()[1] == square[1] && findBadPiece.isPieceWhite() != current.isPieceWhite()){
                  getRidOf = findBadPiece;
                }
              }
            }
          }
        }
        if(getRidOf != null){
          for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getType().equals(getRidOf.getType()) && pieces.get(i).getSquareOn()[0] == getRidOf.getSquareOn()[0] && pieces.get(i).getSquareOn()[1] == getRidOf.getSquareOn()[1]){
              pieces.remove(i);
            }
          }
        }
        //current.updateSquareOn(square);
        //System.out.println(current.getSquareOn()[0] + ", " + current.getSquareOn()[1]);
        boolean check = false;
        //System.out.println("these aare the pieces");
        for(Piece updatePiece: pieces){
          updatePiece.setPieces(pieces);
          //System.out.println(updatePiece.getType() + " is at " + updatePiece.getSquareOn()[0] + ", " + updatePiece.getSquareOn()[1]);
        }
        for(Piece isKing: pieces){
            if(isKing.getType().equals("king") && isKing.isPieceWhite() == current.isPieceWhite()){
                King k = new King(isKing.isPieceWhite(), isKing.getSquareOn(), isKing.hasPieceMoved(), isKing.getValue());
                for(Piece p : pieces){
                  //System.out.println("checking if this checks me" + p.getType());
                   if(p.isPieceWhite() != k.isPieceWhite()){
                    //System.out.println(p.getType());
                      ArrayList<int[]> captures = p.possibleChecks();
                      for(int[] capturableSquare : captures){
                         if(capturableSquare[0] == k.getSquareOn()[0] && capturableSquare[1] == k.getSquareOn()[1]){
                          //System.out.println("capturable at " + capturableSquare[0] + capturableSquare[1] + "by " + p.getType() + p.isPieceWhite());
                            check = true;
                         }
                      }
                   }

                }

                
                
            } 
        }
        if(check == false) {
                  list.add(new int[]{square[0], square[1]});
        }
      }
      pieces.clear();
      for(Piece original : storePieces){
          String letter = original.getType().substring(0,1);
          if(letter.equals("p")){
            pieces.add(new Pawn(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } else if(letter.equals("r")){
            pieces.add(new Rook(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } else if(letter.equals("q")){
            pieces.add(new Queen(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } else if(letter.equals("k")){
            pieces.add(new King(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } else if(letter.equals("n")){
            pieces.add(new Knight(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } else if(letter.equals("b")){
            pieces.add(new Bishop(original.isPieceWhite(), original.getSquareOn(), original.hasPieceMoved(), original.getValue()));
         } 
      }
      for(Piece reupdate : pieces){
        reupdate.setPieces(pieces);
      }
      pieceSelected = pieceOnSquare(current.getSquareOn());
      if(pieceSelected.getType().equals("king")) list = checkCastling(pieceSelected, list);
      //System.out.println("redefine pieceselected as: " + pieceSelected.getType());
      return list;

   }

   public boolean checkCheckMate(){
      King k = null;
      for(Piece isKing : pieces){
        if(isKing.getType().equals("king") && isKing.isPieceWhite() != whiteToMove){
            k = new King(isKing.isPieceWhite(), isKing.getSquareOn(), isKing.hasPieceMoved(), isKing.getValue());
        }
      }
      boolean check = false;
      for(Piece p : pieces){
        if(p.isPieceWhite() != k.isPieceWhite()){
          //System.out.println(p.getType());
            ArrayList<int[]> captures = p.possibleChecks();
            for(int[] capturableSquare : captures){
               if(capturableSquare[0] == k.getSquareOn()[0] && capturableSquare[1] == k.getSquareOn()[1]){
                //System.out.println("capturable at " + capturableSquare[0] + capturableSquare[1] + "by " + p.getType() + p.isPieceWhite());
                  check = true;
               }
            }
         }
     }
      if(!check){
        for(int i = 0; i < pieces.size(); i++){
          Piece p = pieces.get(i);
          if(p.isPieceWhite() == k.isPieceWhite()){
            if(avoidCheck(p, p.possibleCaptures()).size() != 0){
              //System.out.println("returned faalse here: " + p.getType() +  avoidCheck(p, p.possibleCaptures()).get(0)[0] + ", " + avoidCheck(p, p.possibleCaptures()).get(0)[1] + avoidCheck(p, p.possibleCaptures()).size());
              return false;
            }
          }
        }
        System.out.println("stalemate");
        stalemate = true;
        gameover = true;
        return true;
      } else {
        //System.out.println("is in check");
        for(int i = 0; i < pieces.size(); i++){
          Piece p = pieces.get(i);
          if(p.isPieceWhite() == k.isPieceWhite()){
            if(avoidCheck(p, p.possibleCaptures()).size() != 0){
              //System.out.println("returned faalse here: " + p.getType() +  avoidCheck(p, p.possibleCaptures()).get(0)[0] + ", " + avoidCheck(p, p.possibleCaptures()).get(0)[1] + avoidCheck(p, p.possibleCaptures()).size());
              return false;
            }
          }
        }
        System.out.println("game over");
        gameover = true;
        return true;
      }

   }



   private ArrayList<int[]> checkCastling(Piece currently, ArrayList<int[]> list){
      King current = (King) currently;
      if(current.hasPieceMoved() || current.isInCheck(current.getSquareOn())) return list;
      if(current.isPieceWhite()){
        if(pieceOnSquare(new int[]{7,0}) != null && pieceOnSquare(new int[]{7,0}).getType().equals("rook") && pieceOnSquare(new int[]{7,0}).hasPieceMoved() == false && pieceOnSquare(new int[]{6,0}) == null && pieceOnSquare(new int[]{5,0}) == null && current.isInCheck(new int[]{6,0}) == false && current.isInCheck(new int[]{5,0}) == false){
          list.add(new int[]{6,0});
        }
        if(pieceOnSquare(new int[]{0,0}) != null && pieceOnSquare(new int[]{0,0}).getType().equals("rook") && pieceOnSquare(new int[]{0,0}).hasPieceMoved() == false && pieceOnSquare(new int[]{1,0}) == null && pieceOnSquare(new int[]{2,0}) == null && pieceOnSquare(new int[]{3,0}) == null && current.isInCheck(new int[]{2,0}) == false&& current.isInCheck(new int[]{3,0}) == false){
          list.add(new int[]{2,0});
        }
      } else{
        if(pieceOnSquare(new int[]{7,7}) != null && pieceOnSquare(new int[]{7,7}).getType().equals("rook") && pieceOnSquare(new int[]{7,7}).hasPieceMoved() == false && pieceOnSquare(new int[]{6,7}) == null && pieceOnSquare(new int[]{5,7}) == null && current.isInCheck(new int[]{6,7}) == false && current.isInCheck(new int[]{5,7}) == false){
          list.add(new int[]{6,7});
        }
        if(pieceOnSquare(new int[]{0,7}) != null && pieceOnSquare(new int[]{0,7}).getType().equals("rook") && pieceOnSquare(new int[]{0,7}).hasPieceMoved() == false && pieceOnSquare(new int[]{1,7}) == null && pieceOnSquare(new int[]{2,7}) == null && pieceOnSquare(new int[]{3,7}) == null && current.isInCheck(new int[]{2,7}) == false && current.isInCheck(new int[]{3,7}) == false){
          list.add(new int[]{2,7});
        }
      }
      return list;
   }

   private ArrayList<int[]> enPassant(Piece current, ArrayList<int[]> list){
      if(pieceOnSquare(new int[]{current.getSquareOn()[0]-1, current.getSquareOn()[1]}) != null && pieceOnSquare(new int[]{current.getSquareOn()[0]-1, current.getSquareOn()[1]}).getType().equals("pawn")){
        Piece other = pieceOnSquare(new int[]{current.getSquareOn()[0]-1, current.getSquareOn()[1]});
        if(other.getSquareOn()[0] == toEnPassant.getSquareOn()[0] && other.getSquareOn()[1] == toEnPassant.getSquareOn()[1] && other.isPieceWhite() == toEnPassant.isPieceWhite()){
          if(current.isPieceWhite()){
            list.add(new int[]{current.getSquareOn()[0]-1, current.getSquareOn()[1]+1});
          } else {
            list.add(new int[]{current.getSquareOn()[0]-1, current.getSquareOn()[1]-1});
          }
        }
      }
      if(pieceOnSquare(new int[]{current.getSquareOn()[0]+1, current.getSquareOn()[1]}) != null && pieceOnSquare(new int[]{current.getSquareOn()[0]+1, current.getSquareOn()[1]}).getType().equals("pawn")){
        Piece other = pieceOnSquare(new int[]{current.getSquareOn()[0]+1, current.getSquareOn()[1]});
        if(other.getSquareOn()[0] == toEnPassant.getSquareOn()[0] && other.getSquareOn()[1] == toEnPassant.getSquareOn()[1] && other.isPieceWhite() == toEnPassant.isPieceWhite()){
          if(current.isPieceWhite()){
            list.add(new int[]{current.getSquareOn()[0]+1, current.getSquareOn()[1]+1});
          } else {
            list.add(new int[]{current.getSquareOn()[0]+1, current.getSquareOn()[1]-1});
          }
        }
      }
      return list;
   }


   /*private ArrayList<int[]> avoidCheck(Piece current, ArrayList<int[]> possibleCaptures){
      ArrayList<int[]> list = new ArrayList<int[]>();
      System.out.println("we got to here");
      for(int[] square : possibleCaptures){
        ArrayList<Piece> tempPieces =  new ArrayList<Piece>();
        for(Piece original : pieces){
            String letter = original.getType().substring(0,1);
            if(letter.equals("p")){
              tempPieces.add(new Pawn(original.isPieceWhite(), original.getSquareOn()));
           } else if(letter.equals("r")){
              tempPieces.add(new Rook(original.isPieceWhite(), original.getSquareOn()));
           } else if(letter.equals("q")){
              tempPieces.add(new Queen(original.isPieceWhite(), original.getSquareOn()));
           } else if(letter.equals("k")){
              tempPieces.add(new King(original.isPieceWhite(), original.getSquareOn()));
           } else if(letter.equals("n")){
              tempPieces.add(new Knight(original.isPieceWhite(), original.getSquareOn()));
           } else if(letter.equals("b")){
              tempPieces.add(new Bishop(original.isPieceWhite(), original.getSquareOn()));
           } 
          }
          for(Piece p : tempPieces){
            if(p.getSquareOn()[0] == current.getSquareOn()[0] && p.getSquareOn()[1] == current.getSquareOn()[1]){
                p.updateSquareOn(square);
                boolean check = true;
                for(Piece isKing : tempPieces){
                  if(isKing.getType().equals("king") && isKing.isPieceWhite() == p.isPieceWhite()){
                    King k = new King(isKing.isPieceWhite(), isKing.getSquareOn());
                    for(Piece tempP : tempPieces){
                      if(tempP.isPieceWhite() != k.isPieceWhite()){
                        for(int[] move : tempP.possibleCaptures)
                      }
                    }
                  }
                }
                if(check == false) {
                  list.add(new int[]{square[0], square[1]});
                  //System.out.println(square[0] + "isnt in check if" + square[1]);
                } else {
                  System.out.println("we are still in check if piece " + p.getType() + " goes to ssquare " + square[0] + ", " + square[1]);
                }
            }
          }
      }

      return list;
   }*/

   private boolean isClickable(int x, int y){
      //System.out.println("checking clickable");
      if(isSquareEmpty(new int[]{x,y})){
         for(Piece p: pieces){
            //System.out.println(p.getSquareOn()[0] +", " + p.getSquareOn()[1]);
         }
         //System.out.println("square is empty");
         return false;
      } else {
         Piece p = pieceOnSquare(new int[]{x,y});
         if(whiteToMove && p.isPieceWhite()){
            return true;
         } else if(!whiteToMove && !p.isPieceWhite()){
            return true;
         }
      }
      return false;
   }
   private void highlight(int x, int y){
      //System.out.println("is clickable");
      Piece clicked = pieceOnSquare(new int[]{x,y});
      //System.out.println("clicked " + clicked.getType());
      colors[(7-y)*8 + x + 1] = Color.YELLOW;
      pieceSelected = pieceOnSquare(new int[]{x,y});
      //System.out.println("set pieceSelected to" + pieceSelected.getType());
      pieceSelected.setPieces(pieces);
      //System.out.println(pieceSelected.getType() + "test");
      ArrayList<int[]> pCaptures = pieceSelected.possibleCaptures();
      
      //if(isInCheck(pieceSelected)){
        pCaptures = avoidCheck(pieceSelected, pCaptures); //run another method
      //}
        
      //pieceSelected = pieceOnSquare(pieceSelected.getSquareOn());
      for(int[] square : pCaptures){
         //System.out.println("square at " + square[0] + " , " + square[1]);
         colors[(7-square[1])*8 + square[0] + 1] = Color.RED; //make array of circle spots to do in repaint
      }
   }

   public void mousePressed(MouseEvent e)
  {
    if(gameover == false){
      for(Piece p : pieces){
        p.setPieces(pieces);
        p.updateValue();
      }
     if(pieceSelected != null) setColors();
      //System.out.println("mouse presssed, x = " + x + " y = " + y);
      x = e.getX()/100;
      y = 7-(e.getY()/100);
      str = "Mouse Pressed";
      //System.out.println(x + ", " + y);
      if(isClickable(x,y)) highlight(x,y);
      repaint();

    }
    
  }
  public void mouseReleased(MouseEvent e)
  {
    x = e.getX()/100;
    y = 7-(e.getY()/100);
    str = "Mouse Released";
    //System.out.println("mouse released");
    if(start && gameover == false){
      if(promote){
        if((x == 2 || x == 3) && (y == 3 || y == 4)){
          promote = false;
          Piece toReplace = new Knight(toPromote.isPieceWhite(), toPromote.getSquareOn(), 300);
          for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getSquareOn()[0] == toPromote.getSquareOn()[0] && pieces.get(i).getSquareOn()[1] == toPromote.getSquareOn()[1] && pieces.get(i).getType().equals(toPromote.getType())){
              pieces.set(i, toReplace);
            }
          } 
        } else if((x == 4 || x == 5) && (y == 3 || y == 4)){
          promote = false;
          Piece toReplace = new Queen(toPromote.isPieceWhite(), toPromote.getSquareOn(), 900);
          for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getSquareOn()[0] == toPromote.getSquareOn()[0] && pieces.get(i).getSquareOn()[1] == toPromote.getSquareOn()[1] && pieces.get(i).getType().equals(toPromote.getType())){
              pieces.set(i, toReplace);
            }
          }
        }
      } else {
        if(pieceSelected != null && true){ //and white's turn
          //System.out.println("piece selected not null");
          for(int[] square : avoidCheck(pieceSelected, pieceSelected.possibleCaptures())){
             if(square[0] == x && square[1] == y){
                if(isSquareEmpty(square)){

                  //System.out.println("also got to the moving of a piece");
                   if(pieceSelected.getType().equals("king")){
                      if(Math.abs(pieceSelected.getSquareOn()[0]-x) == 2){
                        if(x == 6){
                          pieceOnSquare(new int[]{7, pieceSelected.getSquareOn()[1]}).updateSquareOn(new int[]{5, pieceSelected.getSquareOn()[1]});
                        } else {
                          pieceOnSquare(new int[]{0, pieceSelected.getSquareOn()[1]}).updateSquareOn(new int[]{3, pieceSelected.getSquareOn()[1]});
                        }
                      }
                    }
                  if(enPassant && pieceSelected.getType().equals("pawn") && (square[1] == 5 || square[1] == 2) && square[0] == toEnPassant.getSquareOn()[0]){
                    for(int i = 0; i < pieces.size(); i++){
                      if(pieces.get(i).getSquareOn()[0] == toEnPassant.getSquareOn()[0] && pieces.get(i).getSquareOn()[1] == toEnPassant.getSquareOn()[1]){
                        pieces.remove(i);
                      }
                    }
                  }
                  if(pieceSelected.getType().equals("pawn") && Math.abs(square[1]-pieceSelected.getSquareOn()[1]) == 2){
                    enPassant = true;
                    toEnPassant = pieceSelected;
                  } else {
                    enPassant = false;
                  }
                   pieceSelected.updateSquareOn(square);
                   
                   repaint();
                   setColors();
                   if(pieceSelected.getType().equals("pawn") && (square[1] == 7 || square[1] == 0)){
                    promote(pieceSelected);
                   }
                   pieceSelected = null;
                   checkCheckMate();
                   //makeFen();
                   //System.out.println(fen);
                   countMoves++;
                   whiteToMove = !whiteToMove;
                   //System.out.println("got here empty");
                   repaint();

                   if(ai) aiMove();
                   //System.out.println("we are hereeee" + whiteToMove);

        //aiMove();
                   //aiMove();

                } else {
                   Piece other = pieceOnSquare(square);
                   if(other.isPieceWhite() != (pieceSelected.isPieceWhite())){
                      for(int i = 0; i < pieces.size(); i++){
                         if(pieces.get(i).equals(other)){
                            pieces.remove(i);
                         }
                      }
                      //System.out.println("got to the moving of a piece");
                      //System.out.println("other is " + other.getType());
                      //System.out.println("pieceselected is " + pieceSelected.getType());
                      //System.out.println(pieceSelected.getSquareOn()[0] + ", " + pieceSelected.getSquareOn()[1]);
                      pieceSelected.updateSquareOn(square);
                      //System.out.println(pieceSelected.getType());
                      //System.out.println(pieceSelected.getSquareOn()[0]);
                      
                      repaint();
                      //System.out.println(pieceSelected.getSquareOn()[0] + ", " + pieceSelected.getSquareOn()[1]);
                      setColors();
                      //System.out.println(pieceSelected.getType());
                      //System.out.println(square[1]);
                      if(pieceOnSquare(square).getType().equals("pawn") && (square[1] == 7 || square[1] == 0)){
                          promote(pieceSelected);
                      }
                      pieceSelected = null;
                      checkCheckMate();
                      //makeFen();
                      //System.out.println(fen);
                      countMoves++;
                      whiteToMove = !whiteToMove;
                      //System.out.println("got here other");
                      repaint();
        //aiMove();
                      if(ai) aiMove();
                      //System.out.println("we are here" + whiteToMove);
                   }
                }
             }
          }
        }
      }
    }//end start
    else if(start == false){//hasnt started
        if((x >= 2 &&  x <= 5) && (y == 5 || y == 4)){
          start = true;
          ai = true;
        } else if((x >= 2 &&  x <= 5) && (y == 2 || y == 3)){
          start = true;
          ai = false;
        }
    }
    
    
  }
  public void mouseClicked(MouseEvent e)
  {
    x = e.getX();
    y = e.getY();
    str = "Mouse Clicked";

    repaint();
  }
  public void mouseEntered(MouseEvent e)
  {
    x = e.getX();
    y = e.getY();
    str = "Mouse Entered";
    repaint();
  }
  public void mouseExited(MouseEvent e)
  {
    x = e.getX();
    y = e.getY();
    str = "Mouse Exited";
    repaint();
  }
   /*public void move(){
      String missedSide = ball.move(left, right);
      if (missedSide.equals("left")){
         rightScore++;
         ball = new Ball ChessFrame.WIDTH /2 - Ball.BALL_DIAMETER / 2, ChessFrame.HEIGHT / 2 - Ball.BALL_DIAMETER / 2, (int) (Math.random() * 25), (int) (Math.random() * 15), Color.YELLOW);
      } else if (missedSide.equals("right")){
         leftScore++;
         ball = new Ball ChessFrame.WIDTH /2 - Ball.BALL_DIAMETER / 2, ChessFrame.HEIGHT / 2 - Ball.BALL_DIAMETER / 2, (int) (Math.random() * 25), (int) (Math.random() * 15), Color.YELLOW);
      }
   }
   
   public void move(String paddleSide, String direction){
      if (paddleSide.equals("right")){
         right.move(direction);
      } else {
         left.move(direction);
      }
   }*/
}