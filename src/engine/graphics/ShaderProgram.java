package engine.graphics;

import engine.maths.Matrix4;
import engine.maths.vectors.Vector2;
import engine.maths.vectors.Vector3;
import java.util.HashMap;

import static org.lwjgl.opengl.GL33.*;

public class ShaderProgram{


    private final int programId,vertexShaderId,fragmentShaderId;
    private HashMap<String,Integer> uniforms;


    public ShaderProgram(String vertexCode,String fragmentCode){
        vertexShaderId=createShader(vertexCode,GL_VERTEX_SHADER);
        fragmentShaderId=createShader(fragmentCode,GL_FRAGMENT_SHADER);
        programId=glCreateProgram();
        if(programId==0)
            System.out.println("Could not create shader");
        glAttachShader(programId,vertexShaderId);
        glAttachShader(programId,fragmentShaderId);
        glLinkProgram(programId);
        glValidateProgram(programId);
        if(glGetProgrami(programId,GL_VALIDATE_STATUS)==GL_FALSE)
            System.err.println("Warning validating shader code: "+glGetProgramInfoLog(programId,2048));

        uniforms=new HashMap<>();
    }

    public void addUniforms(String... names){
        for(String name:names)
            uniforms.put(name,glGetUniformLocation(programId,name));
    }

    public void bindAttribute(int index,String name){
        glBindAttribLocation(programId,index,name);
    }

    protected int createShader(String shaderCode,int shaderType){
        int shaderId=glCreateShader(shaderType);
        glShaderSource(shaderId,shaderCode);
        glCompileShader(shaderId);
        if(glGetShaderi(shaderId,GL_COMPILE_STATUS)==GL_FALSE)
            System.err.println("Error compiling shader code: "+glGetShaderInfoLog(shaderId,2048));
        return shaderId;
    }

    public void setUniform(String uniformName,Matrix4 matrix4){
        glUniformMatrix4fv(uniforms.get(uniformName),false,matrix4.val);
    }

    public void setUniform(String uniformName,float x,float y){
        glUniform2f(uniforms.get(uniformName),x,y);
    }

    public void setUniform(String uniformName,float x,float y,float z){
        glUniform3f(uniforms.get(uniformName),x,y,z);
    }

    public void setUniform(String uniformName,float x,float y,float z,float w){
        glUniform4f(uniforms.get(uniformName),x,y,z,w);
    }

    public void setUniform(String uniformName,Vector2 v){
        glUniform2f(uniforms.get(uniformName),v.x,v.y);
    }

    public void setUniform(String uniformName,Vector3 v){
        glUniform3f(uniforms.get(uniformName),v.x,v.y,v.z);
    }

    public void setUniform(String uniformName,int value){
        glUniform1i(uniforms.get(uniformName),value);
    }

    public void setUniform(String uniformName,float value){
        glUniform1f(uniforms.get(uniformName),value);
    }

    public void setUniform(String uniformName,Color color){
        glUniform4f(uniforms.get(uniformName),color.red(),color.green(),color.blue(),color.alpha());
    }

    public void setUniform(String uniformName,Texture texture){
        int unit=1;
        glActiveTexture(GL_TEXTURE0+unit);
        glBindTexture(GL_TEXTURE_2D,texture.getId());
        glUniform1i(uniforms.get(uniformName),unit);
    }

    public void bind(){
        glUseProgram(programId);
    }

    public void unbind(){
        glUseProgram(0);
    }

    public void dispose(){
        unbind();
        if(programId!=0){
            glDetachShader(programId,vertexShaderId);
            glDetachShader(programId,fragmentShaderId);
            glDeleteShader(vertexShaderId);
            glDeleteShader(fragmentShaderId);
            glDeleteProgram(programId);
        }
    }

    public int getProgramId(){
        return programId;
    }
}
