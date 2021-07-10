package myterraria;

import engine.graphics.*;
import engine.maths.Matrix4;
import engine.utils.Assets;
import engine.utils.Timer;
import engine.utils.Utils;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;

public class LwjglTest extends Main{


    PerspectiveCamera pcam;
    ModelBatch batch;
    RawModel cube;


    public void create(){
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);

        pcam=new PerspectiveCamera(WIDTH,HEIGHT,0.01f,1000f,70f);
        batch=new ModelBatch();

        Assets.loadTexture("textures_0.png","t4");

        float x=-50f,y=-50f,z=-50f,size=100f;

        float[] vertices={
                x+size,y+size,z,      //V0
                x,y+size,z,           //V1
                x,y,z,                //V2
                x+size,y,z,           //V3
                x+size,y,z+size,      //V4
                x+size,y+size,z+size, //V5
                x,y+size,z+size,      //V6
                x,y,z+size,           //V7
        };
        int[] indices={
                //0,1,2,3, //Front
                //5,6,1,0, //Top
                //5,0,3,4, //Right
                //1,6,7,2, //Left
                //3,2,7,4, //Bottom
                //6,5,4,7, //Back

                3,2,1,0, //Front
                0,1,6,5, //Top
                4,3,0,5, //Right
                2,7,6,1, //Left
                4,7,2,3, //Bottom
                7,4,5,6, //Back
        };

        float tw=Assets.getTexture("t4").getWidth();
        float th=Assets.getTexture("t4").getHeight();

        float t1x=8,t1y=6;
        float t2x=12,t2y=10;
        float t3x=15,t3y=10;

        float[] texCoords={
                (t2x*16   )/tw,(t2y*16   )/th,
                (t2x*16   )/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16   )/th,

                (t3x*16   )/tw,(t3y*16   )/th,
                (t3x*16   )/tw,(t3y*16+16)/th,
                (t3x*16+16)/tw,(t3y*16+16)/th,
                (t3x*16+16)/tw,(t3y*16   )/th,

                (t2x*16   )/tw,(t2y*16   )/th,
                (t2x*16   )/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16   )/th,

                (t2x*16   )/tw,(t2y*16   )/th,
                (t2x*16   )/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16   )/th,

                (t2x*16   )/tw,(t2y*16   )/th,
                (t2x*16   )/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16   )/th,

                (t2x*16   )/tw,(t2y*16   )/th,
                (t2x*16   )/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16   )/th,

                (t1x*16   )/tw,(t1y*16   )/th,
                (t1x*16   )/tw,(t1y*16+16)/th,
                (t1x*16+16)/tw,(t1y*16+16)/th,
                (t1x*16+16)/tw,(t1y*16   )/th,

                (t2x*16   )/tw,(t2y*16   )/th,
                (t2x*16   )/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16+16)/th,
                (t2x*16+16)/tw,(t2y*16   )/th,
        };
        cube=new RawModel(vertices,texCoords,indices,GL_QUADS);

        transformation=new Matrix4();
    }

    Matrix4 transformation;

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.13f,0.13f,0.18f,1f);
        pcam.update();
        batch.begin(pcam.getProjection(),pcam.getView());

        batch.draw(cube,Assets.getTexture("t4"),transformation);
        transformation.translate(0,0,0);

        batch.end();
        controls();
    }

    public void controls(){
        float cam_speed=0.07f;

        if(KEYBOARD.isKeyPressed(GLFW_KEY_W))
            pcam.translate(0,0,cam_speed);
        if(KEYBOARD.isKeyPressed(GLFW_KEY_S))
            pcam.translate(0,0,-cam_speed);

        if(KEYBOARD.isKeyPressed(GLFW_KEY_A))
            pcam.translate(0,0,-cam_speed);
        if(KEYBOARD.isKeyPressed(GLFW_KEY_D))
            pcam.translate(0,0,cam_speed);

        if(KEYBOARD.isKeyPressed(GLFW_KEY_SPACE))
            pcam.translate(0,cam_speed,0);
        if(KEYBOARD.isKeyPressed(GLFW_KEY_LEFT_SHIFT))
            pcam.translate(0,-cam_speed,0);

        turn();

        if(KEYBOARD.isKeyPressed(GLFW_KEY_ESCAPE))
            System.exit(0);
        if(fpstimer.getMillis()>=200){
            fpstimer.setMillis(0);
            WINDOW.setTitle("Lwjgl test; fps: "+Utils.numbersAfterPoint(FPS,1));
        }
    }

    float tx,ty,rtx,rty,sensivityx=30,sensivityy=30;

    public void turn(){
        tx=MOUSE.getX();
        ty=MOUSE.getY();

        float yaw=(rtx-tx)/WIDTH*sensivityx;
        float pitch=(rty-ty)/HEIGHT*sensivityy;
        pcam.rotate(yaw,pitch,0);

        glfwSetCursorPos(WINDOW.getId(),640,360);
        MOUSE.pos.set(640,360);
        rtx=640;
        rty=360;
    }

    Timer fpstimer=new Timer().start();

    public void resize(int w,int h){
        pcam.resize(w,h);
    }

    public void dispose(){
        batch.dispose();
        Assets.dispose();
    }

}