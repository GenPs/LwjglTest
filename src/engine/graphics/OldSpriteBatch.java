package engine.graphics;

import engine.maths.GdxMatrix4;
import engine.maths.Matrix4;

import static org.lwjgl.opengl.GL33.*;

public class OldSpriteBatch{


    private Matrix4 projection;
    private ShaderProgram defaultShader;

    private boolean isBegin;
    private Color color;


    public OldSpriteBatch(int viewportWidth,int viewportHeight){
        projection=new Matrix4().setToOrtho2D(0,0,viewportWidth,viewportHeight);

        String vert=
                "#version 400 core                                 \n"+
                "                                                  \n"+
                "in vec3 aPos;                                     \n"+
                "in vec2 aTexCoord;                                \n"+
                "out vec2 texCoord;                                \n"+
                "                                                  \n"+
                "uniform mat4 projMat;                             \n"+
                "                                                  \n"+
                "void main(void){                                  \n"+
                "    gl_Position=projMat*vec4(aPos,1.0);           \n"+
                "    texCoord=aTexCoord;                           \n"+
                "}                                                   ";

        String frag=
                "#version 400 core                                 \n"+
                "                                                  \n"+
                "in vec2 texCoord;                                 \n"+
                "out vec4 fragColor;                               \n"+
                "                                                  \n"+
                "uniform sampler2D u_texture;                      \n"+
                "uniform vec4 u_color;                             \n"+
                "                                                  \n"+
                "void main(void){                                  \n"+
                "    fragColor=u_color*texture(u_texture,texCoord);\n"+
                "}                                                   ";

        defaultShader=new ShaderProgram(vert,frag);
        defaultShader.addUniforms("u_texture","u_color","projMat");
        defaultShader.bindAttribute(0,"aPos");
        defaultShader.bindAttribute(1,"aTexCoord");

        color=new Color(1,1,1,1);
    }

    public void draw(Texture texture,float x,float y,float width,float height){
        if(texture==null)
            return;

        float[] vertices={
                x,y+height,0,
                x,y,0,
                x+width,y,0,
                x+width,y+height,0
        };
        int[] indices={
                0,1,2,3
        };
        float[] texCoords={
                0.0f,0.0f,
                0.0f,1.0f,
                1.0f,1.0f,
                1.0f,0.0f
        };
        RawModel rawModel=new RawModel(vertices,texCoords,indices,GL_QUADS);

        int texture_unit=0;
        texture.bind(texture_unit);
        defaultShader.setUniform("u_texture",texture_unit);
        defaultShader.setUniform("u_color",color);

        glBindVertexArray(rawModel.vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(rawModel.mode,rawModel.vertexCount,GL_UNSIGNED_INT,0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        texture.unbind();

        rawModel.dispose();
    }

    public void draw(Texture texture,float x,float y,float width,float height,boolean flipX,boolean flipY){
        if(texture==null)
            return;

        float[] vertices;
        if(flipX && flipY)
            vertices=new float[]{
                    x+width,y,0,        //2
                    x+width,y+height,0, //3
                    x,y+height,0,       //0
                    x,y,0               //1
            };
        else if(flipX)
            vertices=new float[]{
                    x+width,y+height,0, //3
                    x+width,y,0,        //2
                    x,y,0,              //1
                    x,y+height,0        //0
            };
        else if(flipY)
            vertices=new float[]{
                    x,y,0,              //1
                    x,y+height,0,       //0
                    x+width,y+height,0, //3
                    x+width,y,0         //2
            };
        else
            vertices=new float[]{
                    x,y+height,0,       //0
                    x,y,0,              //1
                    x+width,y,0,        //2
                    x+width,y+height,0  //3
            };

        int[] indices=new int[]{
                0,1,2,3
        };
        float[] texCoords={
                0.0f,0.0f,
                0.0f,1.0f,
                1.0f,1.0f,
                1.0f,0.0f
        };
        RawModel rawModel=new RawModel(vertices,texCoords,indices,GL_QUADS);

        int texture_unit=0;
        texture.bind(texture_unit);
        defaultShader.setUniform("u_texture",texture_unit);
        defaultShader.setUniform("u_color",color);

        glBindVertexArray(rawModel.vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(rawModel.mode,rawModel.vertexCount,GL_UNSIGNED_INT,0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        texture.unbind();

        rawModel.dispose();
    }

    public void draw(TextureRegion textureRegion,float x,float y,float width,float height){
        if(textureRegion==null || textureRegion.getTexture()==null)
            return;

        float[] vertices={
                x,y+height,0,
                x,y,0,
                x+width,y,0,
                x+width,y+height,0
        };
        int[] indices={
                0,1,2,3,
        };

        float[] texCoords={
                textureRegion.getU(),textureRegion.getV(),
                textureRegion.getU(),textureRegion.getV2(),
                textureRegion.getU2(),textureRegion.getV2(),
                textureRegion.getU2(),textureRegion.getV()
        };
        RawModel rawModel=new RawModel(vertices,texCoords,indices,GL_QUADS);

        int texture_unit=0;
        textureRegion.getTexture().bind(texture_unit);
        defaultShader.setUniform("u_texture",texture_unit);
        defaultShader.setUniform("u_color",color);

        glBindVertexArray(rawModel.vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(rawModel.mode,rawModel.vertexCount,GL_UNSIGNED_INT,0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        textureRegion.getTexture().unbind();

        rawModel.dispose();
    }

    public void draw(TextureRegion textureRegion,float x,float y,float width,float height,boolean flipX,boolean flipY){
        if(textureRegion==null || textureRegion.getTexture()==null)
            return;

        float[] vertices;
        if(flipX && flipY)
            vertices=new float[]{
                    x+width,y,0,
                    x+width,y+height,0,
                    x,y+height,0,
                    x,y,0
            };
        else if(flipX)
            vertices=new float[]{
                    x+width,y+height,0,
                    x+width,y,0,
                    x,y,0,
                    x,y+height,0
            };
        else if(flipY)
            vertices=new float[]{
                    x,y,0,
                    x,y+height,0,
                    x+width,y+height,0,
                    x+width,y,0
            };
        else
            vertices=new float[]{
                    x,y+height,0,
                    x,y,0,
                    x+width,y,0,
                    x+width,y+height,0
            };

        int[] indices=new int[]{
                0,1,2,3
        };
        float[] texCoords={
                textureRegion.getU(),textureRegion.getV(),
                textureRegion.getU(),textureRegion.getV2(),
                textureRegion.getU2(),textureRegion.getV2(),
                textureRegion.getU2(),textureRegion.getV()
        };
        RawModel rawModel=new RawModel(vertices,texCoords,indices,GL_QUADS);

        int texture_unit=0;
        textureRegion.getTexture().bind(texture_unit);
        defaultShader.setUniform("u_texture",texture_unit);
        defaultShader.setUniform("u_color",color);

        glBindVertexArray(rawModel.vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(rawModel.mode,rawModel.vertexCount,GL_UNSIGNED_INT,0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        textureRegion.getTexture().unbind();

        rawModel.dispose();
    }

    public void draw(Texture texture,float x,float y,float width,float height,int rx,int ry,int rwidth,int rheight){
        if(texture==null)
            return;

        float[] vertices={
                x,y+height,0,
                x,y,0,
                x+width,y,0,
                x+width,y+height,0
        };
        int[] indices={
                0,1,2,3
        };
        float[] texCoords={
                (float)rx/texture.getWidth(),(float)ry/texture.getHeight(),
                (float)rx/texture.getWidth(),(float)ry/texture.getHeight()+(float)rheight/texture.getHeight(),
                (float)rx/texture.getWidth()+(float)rwidth/texture.getWidth(),(float)ry/texture.getHeight()+(float)rheight/texture.getHeight(),
                (float)rx/texture.getWidth()+(float)rwidth/texture.getWidth(),(float)ry/texture.getHeight()
        };
        RawModel rawModel=new RawModel(vertices,texCoords,indices,GL_QUADS);

        int texture_unit=0;
        texture.bind(texture_unit);
        defaultShader.setUniform("u_texture",texture_unit);
        defaultShader.setUniform("u_color",color);

        glBindVertexArray(rawModel.vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(rawModel.mode,rawModel.vertexCount,GL_UNSIGNED_INT,0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        texture.unbind();

        rawModel.dispose();
    }

    public void draw(Texture texture,float x,float y,float width,float height,int rx,int ry,int rwidth,int rheight,boolean flipX,boolean flipY){
        if(texture==null)
            return;

        float[] vertices;
        if(flipX && flipY)
            vertices=new float[]{
                    x+width,y,0,
                    x+width,y+height,0,
                    x,y+height,0,
                    x,y,0
            };
        else if(flipX)
            vertices=new float[]{
                    x+width,y+height,0,
                    x+width,y,0,
                    x,y,0,
                    x,y+height,0
            };
        else if(flipY)
            vertices=new float[]{
                    x,y,0,
                    x,y+height,0,
                    x+width,y+height,0,
                    x+width,y,0
            };
        else
            vertices=new float[]{
                    x,y+height,0,
                    x,y,0,
                    x+width,y,0,
                    x+width,y+height,0
            };

        int[] indices=new int[]{
                0,1,2,3
        };
        float[] texCoords={
                (float)rx/texture.getWidth(),(float)ry/texture.getHeight(),
                (float)rx/texture.getWidth(),(float)ry/texture.getHeight()+(float)rheight/texture.getHeight(),
                (float)rx/texture.getWidth()+(float)rwidth/texture.getWidth(),(float)ry/texture.getHeight()+(float)rheight/texture.getHeight(),
                (float)rx/texture.getWidth()+(float)rwidth/texture.getWidth(),(float)ry/texture.getHeight()
        };
        RawModel rawModel=new RawModel(vertices,texCoords,indices,GL_QUADS);

        int texture_unit=0;
        texture.bind(texture_unit);
        defaultShader.setUniform("u_texture",texture_unit);
        defaultShader.setUniform("u_color",color);

        glBindVertexArray(rawModel.vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(rawModel.mode,rawModel.vertexCount,GL_UNSIGNED_INT,0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        texture.unbind();

        rawModel.dispose();
    }

    public void draw(RawModel rawModel,Texture texture){
        if(texture==null)
            return;

        int texture_unit=0;
        texture.bind(texture_unit);
        defaultShader.setUniform("u_texture",texture_unit);
        defaultShader.setUniform("u_color",color);

        glBindVertexArray(rawModel.vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(rawModel.mode,rawModel.vertexCount,GL_UNSIGNED_INT,0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        texture.unbind();
    }

    public void begin(){
        if(!isBegin){
            isBegin=true;
            defaultShader.bind();
            defaultShader.setUniform("projMat",projection);
        }
    }

    public void begin(Matrix4 projectionMatrix){
        if(!isBegin){
            isBegin=true;
            defaultShader.bind();
            defaultShader.setUniform("projMat",projectionMatrix);
        }
    }

    public void end(){
        if(isBegin){
            isBegin=false;
            defaultShader.unbind();
        }
    }

    public void setColor(float r,float g,float b,float a){
        color.set(r,g,b,a);
    }
    public void setColor(Color color){
        this.color=color;
    }
    public Color getColor(){
        return color;
    }

    public void dispose(){
        defaultShader.dispose();
    }


}
