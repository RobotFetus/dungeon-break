/*Boss
 * this program is a object of the boss
 */
import java.awt.*;
public class Boss extends Box {
  private int changeBullet;      //a variable that change the number of bullets the boss will shoot in attack mode 3
  private int maxHealth;          //the maximum health of the boss
  private int moveInterval=0;    //the time it takes between changes in direction and speed
  private Clock trackTimeMovement;     //a clock for moveInterval
  private Clock trackTimeAttact;  //a clock for attack
  private int attackModeSelection;      //what type of attack the boss will use
  private double moveSpeedX;        //movingspeed of the boss on x direction
  private double moveSpeedY;        //movingspeed of the boss on y direction
  private int enemyType;          //a variable that will tell what type of enemy will spawn in attack mode 2
  private int shootInteval;       //how much time has passed for it to begin shooting again
  //constructor
  Boss(int x, int y, int health, int moveInterval){
    super.setBoundingBox(new Rectangle((int)x, (int)y, 126, 126));
    changeBullet=1;
    maxHealth=health;
    super.setX(x);
    super.setY(y);
    super.setHealth(health);
    trackTimeMovement=new Clock();
    trackTimeAttact=new Clock();
    this.moveInterval=moveInterval;
    super.setShootBulletNumber(10);
    moveSpeedX=0;
    moveSpeedY=0;
  }
  
   /*attack
   * this method lets boss attack
   */
  public void attack(){
    if(trackTimeAttact.timePast()>super.getCoolDownTime()){
      trackTimeAttact.upDate();
      if(shootInteval<=0){
        if(super.getHealth()<(maxHealth*0.2)){
          attackModeSelection=(int)(Math.random()*4);
          
          
        }else if(super.getHealth()<(maxHealth*0.5)){
          attackModeSelection=(int)(Math.random()*3);
        }else if(super.getHealth()<(maxHealth*0.8)){
          attackModeSelection=(int)(Math.random()*2);
        }else{
          
          attackModeSelection=0;
        }
        if(attackModeSelection==0){
          shootInteval=100;
        } else if(attackModeSelection==1){
          shootInteval=1;
        } else if(attackModeSelection==2){
          shootInteval=50;
        } else if(attackModeSelection==3){
          shootInteval=3;
          
          ;
        } 
      }
      shootInteval--;
      if(attackModeSelection==0){
        super.setCoolDownTime(70);
        this.attackMode1();
        
      }else if(attackModeSelection==1){
        super.setCoolDownTime(100);
        this.attackMode2();
      }else if(attackModeSelection==2){
        super.setCoolDownTime(30);
        this.attackMode3();
      }else if(attackModeSelection==3){
        super.setCoolDownTime(100);
        this.attackMode4();
      }
    }
  }
  
   /*move
   * this method moves the boss
   */
  public void move(){
    if(trackTimeMovement.timePast()>moveInterval){
      moveSpeedX=Math.random()*4-2;
      moveSpeedY=Math.random()*4-2;
      
      trackTimeMovement.upDate();
    }
    if((moveSpeedX>0&&super.getX()+moveSpeedX<DungeonBreak.maxx)||(moveSpeedX<0&&super.getX()+moveSpeedX>0)){
      super.setX(super.getX()+moveSpeedX);
    }
    if((moveSpeedY>0&&super.getY()+moveSpeedY<DungeonBreak.maxy)||(moveSpeedY<0&&super.getY()+moveSpeedY>0)){
      super.setY(super.getY()+moveSpeedY);
    }
    
  }
  
  
   /*attackMode1
   *the attack mode 1 of the boss
   */
  public void attackMode1(){
    (DungeonBreak.bulletArray).add(new BulletType1(super.getX()+63, super.getY()+63,DungeonBreak.player.getX(),DungeonBreak.player.getY(),   15,15, 0.6,Math.random()*40-20,false, 10000));
    (DungeonBreak.bulletArray).add(new BulletType1(super.getX()+63+Math.random()*200-100, super.getY()+63+Math.random()*200-100,DungeonBreak.player.getX(),DungeonBreak.player.getY(),   15,15, 0.6,0,false, 10000));
    (DungeonBreak.bulletArray).add(new BulletType1(super.getX()+63, super.getY()+63,0,0,   15,15, Math.random()*0.5,Math.random()*360,false, 20000));
  }
  
  /*attackMode2
   *the attack mode 2 of the boss
   */
  public void attackMode2(){
    if((int)(Math.random()*10)<=3){
      enemyType=(int)(Math.random()*5);
      if(enemyType==0){
        DungeonBreak.enemyArray.add(new Enemy((Math.random()*100)+super.getX(), (Math.random()*100)+super.getY(),30,30,5,(int)(Math.random()*(2+5*2))+4,0.7,10,600,(Math.random()*0.5)+0.5,1800,(int)(Math.random()*(3+5))+2, 1, 1));
      }else if(enemyType==1){
        DungeonBreak.enemyArray.add(new Enemy((Math.random()*100)+super.getX(),(Math.random()*100)+super.getY(),30,30,5,1,1,1,150,(Math.random()*0.5)+0.5,1800,(int)(Math.random()*(10+5*3))+5, 1, 2));    //machanegun
      }else if(enemyType==2){
        DungeonBreak.enemyArray.add(new Enemy((Math.random()*100)+super.getX(), (Math.random()*100)+super.getY(),30,30,5,1,(Math.random()*(1+5*0.5))+1,1,1400,(int)(Math.random()*0.5)+0.5,0,1, 1, 3));   //snaper
      }else if(enemyType==3){
        DungeonBreak.enemyArray.add(new Enemy((Math.random()*100)+super.getX(), (Math.random()*100)+super.getY(),30,30,5,(int)(Math.random()*(7+5*3))+6,0.4,360,300,(Math.random()*0.5)+0.5,4000,4, 1, 4)); // shortgan
      }else if(enemyType==4){
        DungeonBreak.enemyArray.add(new Enemy((Math.random()*100)+super.getX(), (Math.random()*100)+super.getY(),30,30,5,1,0.4,0,300,(Math.random()*0.5)+0.5,4000,2, 2, 5)); // chase
      }
      for(int n=0;n<DungeonBreak.wallArray.size();n++){
        if(DungeonBreak.enemyArray.get(DungeonBreak.enemyArray.size()-1).getBoundingBox().intersects(DungeonBreak.wallArray.get(n).getBoundingBox())){
          DungeonBreak.enemyArray.remove(DungeonBreak.enemyArray.size()-1);
          break;
        }
      }
    }
  }
  
  /*attackMode3
   *the attack mode 3 of the boss
   */
  public void attackMode3(){
    
    (DungeonBreak.bulletArray).add(new BulletType1(Math.random()*800-400+DungeonBreak.player.getX(),Math.random()*800-400+DungeonBreak.player.getY(),Math.random()*100-50+DungeonBreak.player.getX(),Math.random()*100-50+DungeonBreak.player.getY(),   15,15, 0.1,Math.random()*40-20,false, 500));
    if(Math.sqrt(Math.pow(DungeonBreak.player.getX()-DungeonBreak.bulletArray.get(DungeonBreak.bulletArray.size()-1).getx(),2)+ Math.pow(DungeonBreak.player.getY()-DungeonBreak.bulletArray.get(DungeonBreak.bulletArray.size()-1).gety(),2))<=30){
      DungeonBreak.bulletArray.get(DungeonBreak.bulletArray.size()-1).remove();
    }
  }
  
  /*attackMode4
   *the attack mode 4 of the boss
   */
  public void attackMode4(){
    for(int i=0;i<super.getShootBulletNumber();i++){
      (DungeonBreak.bulletArray).add(new BulletType1(super.getX()+63, super.getY()+63,0,0,   15,15, Math.random()*0.5,Math.random()*360,false, 20000));
    }
    if(super.getShootBulletNumber()>=30){
      changeBullet=-1;
    }else if(super.getShootBulletNumber()<=7){
      changeBullet=1;
    }
    super.setShootBulletNumber(super.getShootBulletNumber()+changeBullet);
  }
}
