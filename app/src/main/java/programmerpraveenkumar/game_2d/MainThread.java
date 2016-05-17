package programmerpraveenkumar.game_2d;

import android.graphics.Canvas;
import android.provider.Settings;
import android.view.SurfaceHolder;

/**
 * Created by kumar on 17/5/16.
 */
public class MainThread extends Thread {


    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private boolean running;
    public static Canvas canvas;
    public GamePanel gamepanel;


    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamepanel = gamePanel;
    }

    @Override
    public void run() {
        super.run();
        long startTime;
        long timeMills;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / FPS;
        while (running) {
            startTime = System.nanoTime();
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamepanel.update();
                    this.gamepanel.draw(canvas);
                }
            } catch (Exception e) {}
            finally {
                if (canvas != null) {
                   try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {e.printStackTrace();}
                }
            }


            timeMills = ( System.nanoTime()-startTime)/1000000;
            waitTime = targetTime - timeMills;
            try{
                this.sleep(waitTime);
            }catch (Exception e){}
            totalTime = System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);

            }


        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
