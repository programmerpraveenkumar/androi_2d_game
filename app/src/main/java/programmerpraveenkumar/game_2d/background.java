package programmerpraveenkumar.game_2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kumar on 17/5/16.
 */
public class background {
    private Bitmap image;
    private int x,y,dx;

    public  background(Bitmap res){
        image = res;

    }

    public void update(){
        x +=dx;
        if(x <-GamePanel.WIDTH){
            x = 0;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
        if(x<0){
            canvas.drawBitmap(image,x+GamePanel.WIDTH,y,null);
        }
    }
    public void setVector(int dx){
        this.dx = dx;
    }
}
