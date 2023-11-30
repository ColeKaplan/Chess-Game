import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
public class ChessFrame extends JFrame {
   public static int HEIGHT = 800;
   public static int WIDTH = 800;
   
   private ChessComponent comp;
   private Timer t;
   
   public ChessFrame(){
      this.setSize(WIDTH, HEIGHT + 23);
      this.setTitle("CHESS!");
      addComponents();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);

   }
   
   public void addComponents(){
      comp = new ChessComponent();
      this.add(comp);
      this.addMouseListener(comp);
      this.addKeyListener(new ChessKeyListener());
      this.setFocusable(true);
      
      t = new Timer(150, new ChessTimer());
   }
   
   class ChessTimer implements ActionListener {
      public void actionPerformed(ActionEvent e){
         //comp.moveBall();
         repaint();
      }
   }
   
   public void go(){
      t.start();
   }
   
   class ChessKeyListener implements KeyListener{
      public void keyTyped(KeyEvent e){}
      public void keyReleased(KeyEvent e){}
      public void keyPressed(KeyEvent e){
         
      }
   }
   
   public static void main(String[] args){
      ChessFrame f = new ChessFrame();
      f.go();
   }


   
}