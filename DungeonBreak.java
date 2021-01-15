/**
 * DungeonBreak.java
 * This is a game for the final Project
 */
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Toolkit;
import java.util.ArrayList;
class DungeonBreak extends JFrame{
  
  private GameMain myAnimation;                                                         //the animation panel
  static  ArrayList<Bullet> bulletArray=new ArrayList<Bullet>();   //bullet array list
  static  ArrayList<Enemy> enemyArray=new ArrayList<Enemy>();  //enemy array list
  static ArrayList<Wall> wallArray=new ArrayList<Wall>();  //wall array list
  static Player player;                                               //a player             
  static Boolean  shiftKey;                            //shift key
  static Boolean  zKey;                               //'z' key
  static Boolean  fKey;                                         //'f' key
  static Boolean mousePress;                     //mouse left button
  static final int maxx=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();  //size of the screen x
  static final int  maxy=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();//size of the screen y
  static int rightKeyp;  //right/D arrow key
  static int leftKeyp;//left/A arrow key
  static int upKeyp;//up/W arrow key
    static int downKeyp;//down/S arrow key
  static Boolean rightMouse;  //right mouse key press
  static int nKey;            //number of key presses
  static JFrame thisFrame;        //frame refers to the main frame
  int keyCode;                      //see which keys are pressed
  static final int mapSizeX=6000, mapSizeY=6000;    //map size
  
  //constructor
  DungeonBreak() {
    super("DoungeonBreak");
    rightMouse=false;
      rightKeyp=0; 
    leftKeyp=0;
    upKeyp=0;
    mousePress=false;  
    downKeyp=0;
    nKey=0;
    shiftKey=false;                  
    zKey=false;                    
    fKey=false;   
    this.thisFrame=this;
    this.setResizable(false);
    this.setSize(maxx,maxy); 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    try{Thread.sleep(100);    //These two lines add a small delay
    } catch (Exception exc){}
    addKeyListener(new KeyListen());
    this.setUndecorated(true);
    addMouseListener(new MyMouseListener());
    myAnimation = new GameMain();
    this.add(myAnimation);
    this.setVisible(true);
    
  }
  
/*start
   * this method starts the animation of the game
   */
  public void start() { 
    
    myAnimation.animate();
  }
  
  
  /*main
   * the main method of the program
   */
  public static void main(String[] args) {
    MapCombiner mapCombiner=new MapCombiner();
    MapMaker mapMaker=new MapMaker();
    
    mapCombiner.combinMap();
    mapMaker.changeMap();
    new DungeonBreak().start();
    
  }
  /*MyMouseListener
   * this class is a mouse listener
   */
  private class MyMouseListener implements MouseListener {
    
    public void mouseClicked(MouseEvent e) {
      
    }
    public void mousePressed(MouseEvent e) {
      if(e.getModifiers() == MouseEvent.BUTTON1_MASK){
        mousePress=true;
      }
      if(e.getModifiers() == MouseEvent.BUTTON3_MASK){ 
        rightMouse=true;
      }
    }
    
    public void mouseReleased(MouseEvent e) {
      if(e.getModifiers() == MouseEvent.BUTTON1_MASK){
        mousePress=false;
      }
      if(e.getModifiers() == MouseEvent.BUTTON3_MASK){
        rightMouse=false;
      }
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
  } //end of mouselistener
  
  /* KeyListen 
   * this class is a key listener
   */
  
  class KeyListen extends KeyAdapter {
    //when key pressed
    public void keyPressed(KeyEvent event) {
      
      keyCode = event.getKeyCode();
      if (keyCode == event.VK_LEFT||event.getKeyChar() =='a'||event.getKeyChar() =='A') {
        leftKeyp=1;
      }
      if (keyCode == event.VK_RIGHT||event.getKeyChar() =='d'||event.getKeyChar() =='D'){
        rightKeyp=1;
      }
      if (keyCode == event.VK_UP||event.getKeyChar() =='w'||event.getKeyChar() =='W'){
        upKeyp=1;
      }
      if (keyCode == event.VK_DOWN||event.getKeyChar() =='s'||event.getKeyChar() =='S'){
        downKeyp=1;
      }
      if ((keyCode == 'f')||(keyCode == 'F')){
        if(fKey==false){
          fKey=true;
        }else if(fKey==true){
          fKey=false;
        }
      }
      if (keyCode == event.VK_SHIFT){
        
        shiftKey=true;
      } 
      if (event.getKeyChar() == 'z'){
        
        zKey=true;
      }
      nKey=leftKeyp+rightKeyp+upKeyp+downKeyp;
    }
    
    //when key released
    public void keyReleased(KeyEvent event) {
      keyCode = event.getKeyCode();
      if (keyCode == event.VK_LEFT||event.getKeyChar() =='a'||event.getKeyChar() =='A') {
        leftKeyp=0;
      }
      if (keyCode == event.VK_RIGHT||event.getKeyChar() =='d'||event.getKeyChar() =='D'){
        rightKeyp=0;
      }
      if (keyCode == event.VK_UP||event.getKeyChar() =='w'||event.getKeyChar() =='W'){
        upKeyp=0;
      }
      if (keyCode == event.VK_DOWN||event.getKeyChar() =='s'||event.getKeyChar() =='S'){
        downKeyp=0;
      }
      if (event.getKeyChar() == 'z'){
        
        zKey=false;
      }
      if (keyCode == event.VK_SHIFT){
        shiftKey=false;
      }
      nKey=leftKeyp+rightKeyp+upKeyp+downKeyp;
    }
  }
}
