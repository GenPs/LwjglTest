package engine.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL33.*;

public class Texture{

    private int id;
    private int width;
    private int height;

    public Texture(String internalPNG){
        try{
            BufferedImage img=ImageIO.read(new FileInputStream(internalPNG));
            width=img.getWidth();
            height=img.getHeight();
            int[] pixels=new int[width*height];
            img.getRGB(0,0,img.getWidth(),img.getHeight(),pixels,0,img.getWidth());
            ByteBuffer buffer=ByteBuffer.allocateDirect(img.getWidth()*img.getHeight()*4);

            for(int h=0; h<img.getHeight(); h++){
                for(int w=0; w<img.getWidth(); w++){
                    int pixel=pixels[h*img.getWidth()+w];

                    buffer.put((byte)((pixel>>16) & 0xFF));
                    buffer.put((byte)((pixel>>8) & 0xFF));
                    buffer.put((byte)((pixel) & 0xFF));
                    buffer.put((byte)((pixel>>24) & 0xFF));
                }
            }
            buffer.flip();

            id=glGenTextures();
            glBindTexture(GL_TEXTURE_2D,id);
            glPixelStorei(GL_UNPACK_ALIGNMENT,1);

            glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_CLAMP);
            glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_CLAMP);
            glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);

            glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA8,width,height,0,GL_RGBA,GL_UNSIGNED_BYTE,buffer);
            glGenerateMipmap(GL_TEXTURE_2D);
            glBindTexture(GL_TEXTURE_2D,0);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getId(){
        return id;
    }

    public void bind(int unit){
        glActiveTexture(GL_TEXTURE0+unit);
        glBindTexture(GL_TEXTURE_2D,id);
    }

    public void unbind(){
        glBindTexture(GL_TEXTURE_2D,0);
    }

    public void dispose(){
        if(id!=0){
            glDeleteTextures(id);
            id=0;
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}