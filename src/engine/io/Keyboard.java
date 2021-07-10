package engine.io;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard{

    private final long windowId;

    public Keyboard(Window window){
        this.windowId=window.getId();

        //glfwSetKeyCallback(windowId,(windowHandle,button,action,mode)->{
        //
        //});
    }

    public boolean isKeyPressed(int keyCode){
        return glfwGetKey(windowId,keyCode)==GLFW_PRESS;
    }

    public boolean isKeyJustPressed(int keyCode){
        return glfwGetKey(windowId,keyCode)==GLFW_PRESS;
    }

    public long getWindowId(){
        return windowId;
    }

}
