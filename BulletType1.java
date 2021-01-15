/*BulletType1
 * this program is a object of all linear bullets
 */

import java.awt.*;
public class BulletType1 extends Bullet {
  //constructor
  BulletType1(double x, double y,double endx, double endy,  int length, int weight, double speed, double changeAngle, boolean isPlayerBullet, int existTime){
    super.setBoundingBox(new Rectangle((int)x, (int)y, length, weight));
    super.setx(x);
    super.sety(y);
    super.setSpeed(speed);
    super.setEndx(endx);
    super.setEndy(endy);
    super.setLength(length);
    super.setWeight(weight);
    super.setDeltaX(endx - x);
    super.setDeltaY(endy - y);
    super.setAngle(changeAngle);
    super.setTrackTime(new Clock());
    super.setIsPlayerBullet(isPlayerBullet);
    super.setExistTime(existTime);
  }
  
  
  
}
