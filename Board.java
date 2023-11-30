import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;
import javax.imageio.*;
public class Board{
	private int x;
	private int y;
	private int r;
	private Color color;

	public Board(){
		x = 0;
		y = 0; //23
		r = 100;
		color = Color.WHITE;
	}
	public void draw(Graphics2D g, Color[] colors, boolean promote, Piece toPromote, boolean start, boolean ai, boolean gameover){
		if(gameover){
			for(int i  = 1; i <= 64; i++){
				Rectangle tile = new Rectangle(x,y,r,r);
				g.setColor(colors[i]);
				g.fill(tile);

				updatePosition(i);

			}
			g.setColor(Color.BLACK);
			for(int i = 0; i <= 8; i++){
				Rectangle horizontal = new Rectangle((i*100)-1, 0, 2, 800);
				g.fill(horizontal);
			}
			for(int j = 0; j <= 8; j++){
				Rectangle vertical = new Rectangle(0, (j*100)-1, 800, 2);
				g.fill(vertical);
			}

		}
		else if(start){
			for(int i  = 1; i <= 64; i++){
				Rectangle tile = new Rectangle(x,y,r,r);
				g.setColor(colors[i]);
				g.fill(tile);

				updatePosition(i);

			}
			//System.out.println("in promotion: " + promote);
			//if(promote) System.out.println("in promotion: " + toPromote.isPieceWhite());
			if(promote && ai == false){
				Piece knight = new Knight(toPromote.isPieceWhite(), new int[]{1,0}, 300);
				Piece queen = new Queen(toPromote.isPieceWhite(), new int[]{1,0}, 1000);
				Rectangle background = new Rectangle(200,300, 400, 200);
				g.setColor(Color.WHITE);
				g.fill(background);
				g.drawImage(knight.getPic(), 200, 300, 200, 200, null);
				g.drawImage(queen.getPic(), 400, 300, 200, 200, null);
			} 
			/*for(int i = 1; i <= 64; i++){
				if(circles[i]){

				}
			}*/
		} else {
			BufferedImage aiPic = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
			BufferedImage p2Pic = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
			try{
			
				File file = new File("ai.png");
	        	aiPic = ImageIO.read(file);
	        	File file2 = new File("2person.png");
	        	p2Pic = ImageIO.read(file2);

			} catch(Exception e){
				System.out.println("error");
			}

			g.drawImage(aiPic, 150, 250, 450, 150, null);
			g.drawImage(p2Pic, 200, 400, 350, 150, null);
		}

	} 
	private void updatePosition(int i){
		if(i%8 == 0){
			x = 0;
			y += r;
		} else {
			x += r;
		}
	}
	private void updateColor(int i){
		if((i % 8) != 0){
			color = (color == Color.WHITE) ? Color.GRAY : Color.WHITE;
		}
	}
}