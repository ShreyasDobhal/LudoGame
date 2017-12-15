/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

/**
 *
 * @author Shreyas
 */
public class FadingEffect {
    private float op=1.0f;
    public void Fade(java.awt.Frame window) {
        //window=this;
        
        Thread fading = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int x=java.awt.MouseInfo.getPointerInfo().getLocation().x;
                    int y=java.awt.MouseInfo.getPointerInfo().getLocation().y;
                    if (x>window.getX()&&x<(window.getX()+window.getWidth())&&y>window.getY()&&y<(window.getY()+window.getHeight())) {
                        window.setOpacity(1.0f);
                    }
                    else {
                        window.setOpacity(0.75f);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        
                    }
                }
            }
        });
        fading.start();
    }
    
    public void fadeAway(java.awt.Frame window) {
        Thread fading = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (op>0) {
                        window.setOpacity(op);
                        op-=0.05;
                        Thread.sleep(50);
                    }
                    window.setVisible(false);
                } catch (Exception e) {
                    
                }
            }
        });
        fading.start();
    }
}
