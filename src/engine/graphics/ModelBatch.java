package engine.graphics;

import engine.maths.Matrix4;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class ModelBatch{


    private ShaderProgram defaultShader;

    private boolean isBegin;
    private Color color;


    public ModelBatch(){
        String vert=
                "#version 400 core                                            \n"+
                "                                                             \n"+
                "in vec3 aPos;                                                \n"+
                "in vec2 aTexCoord;                                           \n"+
                "out vec2 texCoord;                                           \n"+
                "                                                             \n"+
                "uniform mat4 u_proj;                                         \n"+
                "uniform mat4 u_view;                                         \n"+
                "uniform mat4 u_tran;                                         \n"+
                "                                                             \n"+
                "void main(void){                                             \n"+
                "    gl_Position=vec4(aPos,1.0)*u_proj*u_view*u_tran;         \n"+
                "    texCoord=aTexCoord;                                      \n"+
                "}                                                              ";

        String frag=
                "#version 400 core                                            \n"+
                "                                                             \n"+
                "in vec2 texCoord;                                            \n"+
                "out vec4 fragColor;                                          \n"+
                "                                                             \n"+
                "uniform sampler2D u_texture;                                 \n"+
                "uniform vec4 u_color;                                        \n"+
                "                                                             \n"+
                "void main(void){                                             \n"+
                "    fragColor=u_color*texture(u_texture,texCoord);           \n"+
                "}                                                              ";

        defaultShader=new ShaderProgram(vert,frag);
        defaultShader.addUniforms("u_texture","u_color","projMat","viewMat","tranMat");
        defaultShader.bindAttribute(0,"aPos");
        defaultShader.bindAttribute(1,"aTexCoord");

        color=new Color(1,1,1,1);
    }

    public void draw(RawModel rawModel,Texture texture,Matrix4 transformation){
        if(texture==null)
            return;

        int texture_unit=0;
        texture.bind(texture_unit);
        defaultShader.setUniform("tranMat",transformation);
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

    public void begin(Matrix4 projection, Matrix4 view){
        if(!isBegin){
            isBegin=true;
            defaultShader.bind();
            defaultShader.setUniform("projMat",projection);
            defaultShader.setUniform("viewMat",view);
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
