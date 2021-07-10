#version 400 core

in vec2 texCoord;
out vec4 fragColor;

uniform sampler2D u_texture;
uniform vec4 u_color;

void main(void){
    fragColor=u_color*texture(u_texture,texCoord);
}