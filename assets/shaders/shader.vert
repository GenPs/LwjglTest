#version 400 core

in vec3 aPos;
in vec2 aTexCoord;
out vec2 texCoord;

uniform mat4 projMat;

void main(void){
    gl_Position=projMat*vec4(aPos,1.0);
    texCoord=aTexCoord;
}