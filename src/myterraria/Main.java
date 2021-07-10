package myterraria;

import engine.io.Keyboard;
import engine.io.Mouse;
import engine.io.Window;
import engine.utils.Timer;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main{

    public static int WIDTH,HEIGHT;
    public static float FPS,DELTA_TIME;
    public static Window WINDOW;
    public static Mouse MOUSE;
    public static Keyboard KEYBOARD;

    public static void main(String[] args){
        WIDTH=1280;//2560;
        HEIGHT=720;//1440;

        WINDOW=new Window(WIDTH,HEIGHT,"Lwjgl test",true,true,false);
        TileMapTest main=new TileMapTest();
        main.create();
        WINDOW.show();

        glfwSetWindowSizeCallback(WINDOW.getId(),(windowHandle,width,height)->{
            main.resize(width,height);
            glViewport(0,0,width,height);
            WIDTH=width;
            HEIGHT=height;
        });

        MOUSE=new Mouse(WINDOW);
        KEYBOARD=new Keyboard(WINDOW);

        //double previousTime=glfwGetTime();
        //int frameCount=0;

        Timer timer=new Timer().start();
        long lastTime=System.nanoTime();

        while(!WINDOW.close()){
            DELTA_TIME=timer.getMillis();
            timer.setMillis(0);
            FPS=-1000000000f/(lastTime-(lastTime=System.nanoTime()));

            main.render();
            WINDOW.update();
        }

        WINDOW.hide();
        main.dispose();
        WINDOW.destroy();
    }

}
