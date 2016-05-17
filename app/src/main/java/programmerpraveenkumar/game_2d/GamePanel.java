package programmerpraveenkumar.game_2d;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by kumar on 17/5/16.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    Context context;
    public static final int HEIGHT = 480;//value from bg image
    public static final int WIDTH = 856; //value from bg image
    background bg;
    MainThread mainThread;
    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        mainThread = new MainThread(getHolder(),this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        bg = new background(BitmapFactory.decodeResource(getResources(),R.drawable.grassbg1));
        bg.setVector(-5);
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Boolean retry = true;
        while (retry){
            try{
                mainThread.setRunning(false);
                mainThread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return super.onTouchEvent(event);
    }
    public void update(){
            bg.update();
    }

    @Override
    public void draw(Canvas canvas) {
        // super.draw(canvas);
        final float scaleFactorX = getWidth()/WIDTH;
        final float scaleFactorY = getHeight()/HEIGHT ;
        if(canvas != null){
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX,scaleFactorY);
            bg.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}
