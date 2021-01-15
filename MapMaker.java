/*MapMaker
 * this program tranfers the mapstage.txt to mapbox.txt
 */

import java.util.Scanner;
import java.io.*;
public class MapMaker  {
  Scanner reader;   //scanner to read the file
  int startX;        //start count x
  int endX;          //end count x
  int startY;        //start count y
  int endY;          //end count y
  int digit;          //digit appear on the map.txt
  String mapOut;  //output the wall's x and y pposition, length, and width
  int countx, county;  //count x y when loop over the map
  File mapFile;        //file of the map
  PrintWriter outMap;  //out print new verson of the map
  boolean loop;      // the loop is over when this == false
  int[][] map;   //array of map
  //constructor
  MapMaker(){
    map=new int[89][89]; //  set up the map
  }
  
  /*changeMap
   * this method tranfers the mapstage.txt to mapbox.txt
   */
  public void changeMap(){
    for(int stageNumber=1;stageNumber<=6;stageNumber++){
      
      //clears the mapout every time starting a new stage
      mapOut=""; 
      //set the scanner
      try{
        reader = new Scanner(new File("stageMap"+stageNumber+".txt"));
      }catch(Exception exc){
      }
      //put the map in to a array
      for(int i=0;i<89;i++){
        for(int e=0;e<89;e++){
          map[i][e]=reader.nextInt();
        }
      }
      
      //loop over it
      for(int i=0;i<89;i++){
        for(int e=0;e<89;e++){
          //if a number other than 0 is found, change it to a wall
          if(map[i][e]!=0){
            digit=map[i][e];
            startX=i;
            startY=e;
            countx=i;
            county=e;
            endY=89;
            loop=true;
            //find all similar digits surrounding it
            while(loop==true){
              map[countx][county]=0;
              if(map[countx][county+1]!=digit){
                endY=county;             
                if(map[countx+1][startY]==digit){
                  countx++;
                  county=startY;
                  map[countx][county]=0;
                  county--;
                }else{
                  loop=false;
                }
              }
              county++;
            }
            if(digit==8){
              //if the digit is 8, then create aa exit, else create a wall
              mapOut+="newExit "+(startY)+" "+(startX)+" "+((endY+1)-startY)+" "+((countx+1)-startX)+" "+System.getProperty("line.separator");
              
            }else{
              mapOut+="newWall "+(startY)+" "+(startX)+" "+((endY+1)-startY)+" "+((countx+1)-startX)+" "+System.getProperty("line.separator");
              
            }
          }       
        }
      }
      
      //output in the mapbox.txt
      try{
        mapFile= new File("mapBox"+stageNumber+".txt");
        outMap=new PrintWriter(mapFile);
      }catch(Exception exc){};
      
      
      outMap.println(mapOut);
      outMap.close();
    }
    
  }
}
