package engine.io;

import engine.maths.vectors.Vector2i;
import static org.lwjgl.glfw.GLFW.*;

public class Mouse{

    private long windowId;
    private boolean inWindow,LBM,RBM,
            MB3,MB4,MB5,MB6,MB7,MB8;
    public Vector2i pos;
    private int scrolled;

    public Mouse(Window window){
        this.windowId=window.getId();
        pos=new Vector2i();

        glfwSetCursorPosCallback(windowId,(windowHandle,x,y)->{
            pos.x=(int)x;
            pos.y=(int)y;
        });

        glfwSetCursorEnterCallback(windowId,(windowHandle,entered)->{
            inWindow=entered;
        });

        glfwSetMouseButtonCallback(windowId,(windowHandle,button,action,mode)->{
            LBM=button==GLFW_MOUSE_BUTTON_1 && action==GLFW_PRESS;
            RBM=button==GLFW_MOUSE_BUTTON_2 && action==GLFW_PRESS;
            MB3=button==GLFW_MOUSE_BUTTON_3 && action==GLFW_PRESS;
            MB4=button==GLFW_MOUSE_BUTTON_4 && action==GLFW_PRESS;
            MB5=button==GLFW_MOUSE_BUTTON_5 && action==GLFW_PRESS;
            MB6=button==GLFW_MOUSE_BUTTON_6 && action==GLFW_PRESS;
            MB7=button==GLFW_MOUSE_BUTTON_7 && action==GLFW_PRESS;
            MB8=button==GLFW_MOUSE_BUTTON_8 && action==GLFW_PRESS;
        });

        glfwSetScrollCallback(windowId,(windowHandle,dx,dy)->{
            scrolled+=dy;
        });
    }

    public boolean isScrolled(){
        return scrolled!=0;
    }

    public int getScrolled(){
        int scrl=scrolled;
        scrolled=0;
        return scrl;
    }

    public long getWindowId(){
        return windowId;
    }

    public boolean inWindow(){
        return inWindow;
    }

    public Vector2i getPos(){
        return pos;
    }
    public int getX(){
        return pos.x;
    }
    public int getY(){
        return pos.y;
    }

    public boolean LBM(){
        return LBM;
    }
    public boolean RBM(){
        return RBM;
    }

    public boolean B3(){
        return MB3;
    }
    public boolean B4(){
        return MB4;
    }
    public boolean B5(){
        return MB5;
    }
    public boolean B6(){
        return MB6;
    }
    public boolean B7(){
        return MB7;
    }
    public boolean B8(){
        return MB8;
    }

}
