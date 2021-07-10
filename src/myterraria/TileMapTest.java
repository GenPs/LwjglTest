package myterraria;

import engine.graphics.OrthographicCamera;
import engine.graphics.OldSpriteBatch;
import engine.maths.Maths;
import engine.tiledmap.TileManager;
import engine.tiledmap.TiledMap;
import engine.utils.Assets;
import engine.utils.Timer;
import engine.utils.Utils;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;

public class TileMapTest extends Main{


    OrthographicCamera cam;
    OldSpriteBatch batch;

    TiledMap map;
    TileManager tileManager;
    public static int tile_pixel_size=32;//21.78,32,43.76
    public long seed=Maths.randomSeed(8);//81318082;//33268463;


    public void create(){
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);

        cam=new OrthographicCamera(WIDTH,HEIGHT);
        batch=new OldSpriteBatch(WIDTH,HEIGHT);

        int map_width=500;
        int map_height=200;
        int surface=150;

        //cam.translate(map_width/2f*tile_pixel_size-Main.WIDTH/2f,surface*tile_pixel_size);

        tileManager=new TileManager();
        map=new TiledMap();
        map.addLayer(1,map_width,map_height,tile_pixel_size,tile_pixel_size);//стены
        map.addLayer(2,map_width,map_height,tile_pixel_size,tile_pixel_size);//ветки, вершины деревьев, трава
        map.addLayer(3,map_width,map_height,tile_pixel_size,tile_pixel_size);//блоки
        map.setView(2,4);
        map.setView(1,0);
        map.setView(3,0);
        WorldGenerator.generate(map,seed,surface);

        LoadResources.loadResources();
        LoadResources.defineTilesAndItems(tileManager);

        Assets.loadTexture("textures_0.png","t1");
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(0.13f,0.13f,0.18f,1f);
        cam.update();
        batch.begin(cam.getProjection());

        //map.draw(tileManager,batch,cam);

        batch.draw(Assets.getTexture("t1"),0,0,700,700);

        batch.end();
        controls();
    }

    public void controls(){
        float cam_speed=12f;
        if(KEYBOARD.isKeyPressed(GLFW_KEY_W))
            cam.translate(0,cam_speed);
        if(KEYBOARD.isKeyPressed(GLFW_KEY_A))
            cam.translate(-cam_speed,0);
        if(KEYBOARD.isKeyPressed(GLFW_KEY_S))
            cam.translate(0,-cam_speed);
        if(KEYBOARD.isKeyPressed(GLFW_KEY_D))
            cam.translate(cam_speed,0);

        if(KEYBOARD.isKeyPressed(GLFW_KEY_MINUS))
            cam.scale(1.01f);
        if(KEYBOARD.isKeyPressed(GLFW_KEY_EQUAL))
            cam.scale(0.99f);

        if(KEYBOARD.isKeyPressed(GLFW_KEY_P))
            cam.setScale(1);

        cam.rotate(1);

        if(KEYBOARD.isKeyPressed(GLFW_KEY_ESCAPE))
            System.exit(0);

        if(fpstimer.getMillis()>=200){
            fpstimer.setMillis(0);
            WINDOW.setTitle("Lwjgl test; fps: "+Utils.numbersAfterPoint(FPS,1));
        }

        if(prevstage!=WorldGenerator.stage){
            prevstage=WorldGenerator.stage;
            System.out.println(prevstage);
        }
    }

    String prevstage;
    Timer fpstimer=new Timer().start();

    public void resize(int w,int h){
        cam.resize(w,h);
    }

    public void dispose(){
        batch.dispose();
        Assets.dispose();
    }

}