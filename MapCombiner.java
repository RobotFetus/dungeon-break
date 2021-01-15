/*MapMaker
 * this program combines all the rooms into a big map
 */
import java.util.Scanner;
import java.io.*;
public class MapCombiner  {
  PrintWriter outMap;     //file writer
  Scanner[][][] reader;    //scanner
  int mapNumber;           //stage number
  File mapFile;                //map file
  int endingStageX;         //x value on the map for the ending room
  int endingStageY;      //y value on the map for the ending room
  //constructor
  MapCombiner(){
    reader = new Scanner[4][4][4];
    
  }
  
  
  /*combinMap
   * this method combines random rooms into a big map
   */
  public void combinMap(){
    //combine 16 rooms to a map, length of 4, width of 4
    for(int stageNumber=1;stageNumber<5;stageNumber++){
      //random the exit rooom
      endingStageX=(int)(Math.random()*4);
      endingStageY=(int)(Math.random()*4);
      for(int i=0;i<4;i++){
        for(int e=0;e<4;e++){  
          //random room
          mapNumber=(int)(Math.random()*4);
          //output it to the stage map
          try{
            if (endingStageX==e&&endingStageY==i){
              reader[i][e][stageNumber-1] = new Scanner(new File("endingStage.txt"));
              
            }else{
              reader[i][e][stageNumber-1] = new Scanner(new File("map"+mapNumber+".txt"));
            }
          }catch(Exception exc){
          }
        }
      }
      mapFile= new File("stageMap"+stageNumber+".txt");
      try{
        outMap=new PrintWriter(mapFile);
      }catch(Exception exc){};
      //make the bottom part and the very right part 0, so there will be no mistake while running MapMaker
      for(int i=0;i<4;i++){
        for(int y=0;y<22;y++){
          for(int e=0;e<4;e++){
            outMap.print(reader[i][e][stageNumber-1].nextLine());
          }
          outMap.println("0");
        }
      }
      for(int i=0;i<89;i++){
        outMap.print("0 ");
      }
      outMap.close(); 
      
      
      
    }
  }
  
}
