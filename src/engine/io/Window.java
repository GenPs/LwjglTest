package engine.io;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window{

    private final long id;
    private int width,height;
    private String title;
    private boolean vsync,resizable,fullscreen;

    public Window(int width,int height,String title,boolean resizable,boolean vsync,boolean fullscreen){
        this.width=width;
        this.height=height;
        this.title=title;
        this.resizable=resizable;
        this.vsync=vsync;
        this.fullscreen=fullscreen;

        GLFWErrorCallback.createPrint(System.err).set();
        if(!org.lwjgl.glfw.GLFW.glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_SAMPLES,4);
        glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);

        if(resizable)
            glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
        else
            glfwWindowHint(GLFW_RESIZABLE,GLFW_FALSE);

        id=glfwCreateWindow(width,height,title,fullscreen ? glfwGetPrimaryMonitor() : NULL,NULL);
        if(id==NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(id,(window,key,scancode,action,mods)->{
            if(key==GLFW_KEY_ESCAPE && action==GLFW_RELEASE)
                glfwSetWindowShouldClose(window,true);
        });

        if(!fullscreen){
            GLFWVidMode vidmode=glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(id,(vidmode.width()-width)/2,(vidmode.height()-height)/2);
        }

        glfwMakeContextCurrent(id);
        createCapabilities();

        if(vsync)
            glfwSwapInterval(1);
        else
            glfwSwapInterval(0);
    }

    public void setVSync(boolean vsync){
        this.vsync=vsync;
        if(vsync)
            glfwSwapInterval(1);
        else
            glfwSwapInterval(0);
    }

    public void setTitle(String title){
        this.title=title;
        glfwSetWindowTitle(id,title);
    }

    public void setSize(int width,int height){
        this.width=width;
        this.height=height;
        glfwSetWindowSize(id,width,height);
    }

    public void show(){
        glfwShowWindow(id);
    }

    public void hide(){
        glfwHideWindow(id);
    }

    public void destroy(){
        glfwDestroyWindow(id);
    }

    public boolean close(){
        return glfwWindowShouldClose(id);
    }

    public void update(){
        glfwSwapBuffers(id);
        glfwPollEvents();
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public boolean isResizable(){
        return resizable;
    }

    public boolean isVsync(){
        return vsync;
    }

    public boolean isFullscreen(){
        return fullscreen;
    }

}