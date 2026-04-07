package com.example.shadowarena;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Thread gameThread;
    private boolean running = false;
    private Paint paint = new Paint();
    private int playerX = 300, playerY = 500;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        running = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void run() {
        while (running) {
            if (!getHolder().getSurface().isValid()) continue;
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(playerX, playerY, 40, paint);
            getHolder().unlockCanvasAndPost(canvas);

            try { Thread.sleep(16); } catch (InterruptedException e) {}
        }
    }
}
