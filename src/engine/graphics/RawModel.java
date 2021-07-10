package engine.graphics;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;

public class RawModel{


    public int vaoId,vboId,vboId2,vertexCount,mode;


    public RawModel(float[] vertices,float[] texCoords,int[] indices,int mode){
        this.mode=mode;
        vertexCount=indices.length;
        vaoId=glGenVertexArrays();
        glBindVertexArray(vaoId);

        bindIndicesBuffer(indices);
        storeDataInAttributeList(0,3,vertices);
        storeDataInAttributeList(1,2,texCoords);

        glBindVertexArray(0);
    }

    public void bindIndicesBuffer(int[] indices){
        vboId2=glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,vboId2);
        IntBuffer b=storeDataInIntBuffer(indices);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,b,GL_STATIC_DRAW);
    }

    public void storeDataInAttributeList(int attributeNumber,int coordinateSize,float[] data){
        vboId=glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vboId);
        FloatBuffer b=storeDataInFloatBuffer(data);
        glBufferData(GL_ARRAY_BUFFER,b,GL_STATIC_DRAW);
        glVertexAttribPointer(attributeNumber,coordinateSize,GL_FLOAT,false,0,0);
        glBindBuffer(GL_ARRAY_BUFFER,0);
    }

    public IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer=BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer=BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public void dispose(){
        glDeleteVertexArrays(vaoId);
        glDeleteBuffers(vboId);
        glDeleteBuffers(vboId2);
    }

}
