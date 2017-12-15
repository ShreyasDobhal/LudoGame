/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Shreyas
 */
public class House {
    public final javax.swing.JLabel token[]; // Array of 4 tokens of player
    public int locIndex[]; // Index of the block of tokens
    public boolean safeState[];
    private Point startBlock[];
    public String color; // Color of current house
    public String name;
    public boolean isActive;
    public int finished;
    private Point pos[]; // Possible points on the path
    private static final int pathLength=58;
    private static final int safeBlocks[]={0,1,9,14,22,27,35,40,48,52,53,54,55,56,57};
    
    public boolean sounds=false;
    
    public House(javax.swing.JLabel tk1,javax.swing.JLabel tk2,javax.swing.JLabel tk3,javax.swing.JLabel tk4,String col) {
        token=new javax.swing.JLabel[4];
        locIndex=new int[4];
        safeState=new boolean[4];
        startBlock=new Point[4];
        Arrays.fill(safeState,true);
        token[0]=tk1;
        token[1]=tk2;
        token[2]=tk3;
        token[3]=tk4;
        
        color=col;
        pos=new Point[pathLength];
        createPath();
    }
    
    public void initFirstBlock() {
        for (int i=0;i<4;i++) {
            startBlock[i]=new Point(token[i].getX(),token[i].getY());
            //System.out.println(startBlock[i]);
        }
    }
    
    private void createPath() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\ludo\\path\\"+color+".dat"));
            String s="";
            int ind=0;
            while ((s=br.readLine())!=null) {
                pos[ind++]=new Point(Integer.valueOf(s.substring(0,s.indexOf(' '))),Integer.valueOf(s.substring(s.indexOf(' ')+1)));
            }
        }
        catch (Exception e) {
            System.out.println("Reading error");
        }
    }
    public boolean isPossible(int id,int move) {
        if (locIndex[id]==0) {
            // Not unlocked yet
            if (move==6) {
                return true;
            }
            else {
                return false;
            }
        }
        if ((locIndex[id]+move)>=pathLength) {
            return false;
        }
        else {
            return true;
        }
    }
    public void move(int id,int move) {
        if (locIndex[id]==0) {
            if (sounds) {
                InputStream in;
                try {
                    in = new FileInputStream(new File("src\\ludo\\sounds\\unlocking.wav"));
                    AudioStream audio = new AudioStream(in);
                    AudioPlayer.player.start(audio);
                } catch(Exception e) {

                }
            }
            
            locIndex[id]=1;
            token[id].setLocation(pos[locIndex[id]].x,pos[locIndex[id]].y);
        }
        else {
            for (int i=0;i<move;i++) {
                locIndex[id]++;
                InputStream in;
                try {
                    Thread.sleep(400);
                    if (sounds) {
                        in = new FileInputStream(new File("src\\ludo\\sounds\\step.wav"));
                        AudioStream audio = new AudioStream(in);
                        AudioPlayer.player.start(audio);
                    }
                    
                    //System.out.println("step");
                } catch (Exception e) {
                    
                }
                token[id].setLocation(pos[locIndex[id]].x,pos[locIndex[id]].y);
            }
        }
        for (int i=0;i<safeBlocks.length;i++) {
            if (locIndex[id]==safeBlocks[i]) {
                safeState[id]=true;
                return;
            }
        }
        safeState[id]=false;
    }
    
    public void setToPos() {
        for (int i=0;i<4;i++) {
            if (locIndex[i]!=0) {
                token[i].setLocation(pos[locIndex[i]].x,pos[locIndex[i]].y);
            }
            else {
                token[i].setLocation(startBlock[i].x,startBlock[i].y);
            }
        }
    }
    
    
    public int attack(int id,House other[]) {
        // green +13 % 52
        // yellow +26 % 52
        // blue +39 % 52
        if (safeState[id]) {
            if (this.locIndex[id]>=52)
                return 55;
            switch (this.color) {
                case "red" :
                    token[id].setLocation(token[id].getX()-10,token[id].getY()+10);
                    break;
                case "green" :
                    token[id].setLocation(token[id].getX()-10,token[id].getY()-10);
                    break;
                case "yellow" :
                    token[id].setLocation(token[id].getX()+10,token[id].getY()-10);
                    break;
                case "blue" :
                    token[id].setLocation(token[id].getX()+10,token[id].getY()+10);
                    break;
            }
            return 55;
        }
        int currPos=this.locIndex[id];
        switch (this.color) {
            case "green":
                currPos=(currPos+13)%52;
                break;
            case "yellow":
                currPos=(currPos+26)%52;
                break;
            case "blue":
                currPos=(currPos+39)%52;
                break;
        }
        for (int i=0;i<other.length;i++) {
            for (int j=0;j<4;j++) {
                //System.out.println(currPos+" "+((other[i].locIndex[j]+i*13)%52));
                if (other[i].safeState[j])
                    continue;
                if (other[i].locIndex[j]==0)
                    continue;
                if ((!this.color.equals(other[i].color))&&((other[i].locIndex[j]+i*13)%52==currPos)) {
                    // Attacking other
                    return i*10+j;
                }
            } 
        }
        return 55;
    }
    
    public void kill(int id) {
        //locIndex[id]=0;
        System.out.println("Killed");
        if (sounds) {
            InputStream in;
            try {
                in = new FileInputStream(new File("src\\ludo\\sounds\\save.wav"));
                AudioStream audio = new AudioStream(in);
                AudioPlayer.player.start(audio);
            } catch(Exception e) {

            }
        }
        
        while (locIndex[id]>0) {
            try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    
                }
                token[id].setLocation(pos[locIndex[id]].x,pos[locIndex[id]].y);
            locIndex[id]--;
        }
        token[id].setLocation(startBlock[id].x,startBlock[id].y);
        /*
        if (locIndex[id]==0) {
            locIndex[id]=1;
            token[id].setLocation(pos[locIndex[id]].x,pos[locIndex[id]].y);
        }
        else {
            for (int i=0;i<move;i++) {
                locIndex[id]++;
                try {
                    Thread.sleep(400);
                    System.out.println("step");
                } catch (Exception e) {
                    
                }
                token[id].setLocation(pos[locIndex[id]].x,pos[locIndex[id]].y);
            }
        }
                */
    }
    
    /*
    public void disable() {
        for (int i=0;i<4;i++) {
            token[i].setEnabled(false);//setOpaque(false);
        }
    }
    
    
    public void enable() {
        for (int i=0;i<4;i++) {
            token[i].setEnabled(true);
        }
    }
    */
    
    public void deactivate() {
        for (int i=0;i<4;i++) {
            token[i].setVisible(false);
        }
    }
    
    public void show() {
        for (int i=0;i<4;i++) {
            token[i].repaint();
        }
    }
    
    class Point{
        int x,y;
        public Point(int x,int y) {
            this.x=x;
            this.y=y;
        }
        @Override
        public String toString() {
            String txt = this.x+" "+this.y;
            return txt;
        }
    }
}
