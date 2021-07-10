package myterraria;

import engine.graphics.OrthographicCamera;
import engine.maths.vectors.Vector2i;
import engine.tiledmap.TiledMap;

import java.util.ArrayList;
import java.util.List;

public class Lights{



    public static List<float[]> lights=new ArrayList<>();

    public static void addLight(int x,int y,int t,int a,float r,float g,float b){
        for(int i=0; i<lights.size(); i++){
            float[] lg=lights.get(i);
            if(lg[0]==x && lg[1]==y){
                lights.remove(i);
                break;
            }
        }
        lights.add(new float[]{x,y,t,a,r,g,b});
    }

    public static void removeLight(int x,int y){
        for(int i=0; i<lights.size(); i++){
            float[] lg=lights.get(i);
            if(lg[0]==x && lg[1]==y){
                lights.remove(i);
                break;
            }
        }
    }



    public static int prevsx,prevsy;
    public static boolean update,inprocess;
    public static Vector2i upd_str,upd_end;
    public static float dayLight=1;

    public static void update(TiledMap map,OrthographicCamera cam){
        Vector2i v=map.layer(3).getStartRender(cam);
        if(v.x!=prevsx || v.y!=prevsy){
            updateScreenLights(map,cam);
            prevsx=v.x;
            prevsy=v.y;
        }
        if(update){
            updateLightsOnMap(upd_str,upd_end,map);
            update=false;
            inprocess=false;
        }
    }

    private static void updateLightsOnMap(Vector2i str,Vector2i end,TiledMap map){
        for(int x=str.x; x<end.x; x++){
            for(int y=str.y; y<end.y; y++){
                map.layer(1).colormap[x][y][0]=colormap[1][x][y][0];//*lightmap[1][x][y];
                map.layer(1).colormap[x][y][1]=colormap[1][x][y][1];//*lightmap[1][x][y];
                map.layer(1).colormap[x][y][2]=colormap[1][x][y][2];//*lightmap[1][x][y];

                map.layer(2).colormap[x][y][0]=colormap[2][x][y][0];//*lightmap[2][x][y];
                map.layer(2).colormap[x][y][1]=colormap[2][x][y][1];//*lightmap[2][x][y];
                map.layer(2).colormap[x][y][2]=colormap[2][x][y][2];//*lightmap[2][x][y];

                map.layer(3).colormap[x][y][0]=colormap[3][x][y][0];//*lightmap[3][x][y];
                map.layer(3).colormap[x][y][1]=colormap[3][x][y][1];//*lightmap[3][x][y];
                map.layer(3).colormap[x][y][2]=colormap[3][x][y][2];//*lightmap[3][x][y];
            }
        }
    }

    public static void updateScreenLights(final TiledMap map,final OrthographicCamera cam){
        if(!inprocess){
            inprocess=true;
            new Thread(new Runnable(){
                public void run(){
                    Vector2i str=map.layer(3).getStartRender(cam,-10);
                    Vector2i end=map.layer(3).getEndRender(cam,10);
                    for(int i=str.x; i<end.x; i++){
                        for(int j=str.y; j<end.y; j++){
                            colormap[1][i][j][0]=-1;
                            colormap[2][i][j][0]=-1;
                            colormap[3][i][j][0]=-1;
                            lightmap[1][i][j]=0;
                            lightmap[2][i][j]=0;
                            lightmap[3][i][j]=0;
                        }
                    }
                    for(int x=str.x; x<end.x; x++){
                        for(int y=map.layer(1).height-1; y>-1; y--){
                            boolean b=true;
                            for(int i=0; i<ignoreTilesU.length; i+=2)
                                if(map.getTileId(ignoreTilesU[i+1],x,y)==ignoreTilesU[i])
                                    b=false;
                            if((map.getTileId(1,x,y)!=0 || map.getTileId(3,x,y)!=0) && b){
                                applyLightRec(map,3,x,y,6,24,dayLight,1,1,1);
                                applyLightRec(map,2,x,y,6,24,dayLight,1,1,1);
                                applyLightRec(map,1,x,y,6,24,dayLight,1,1,1);
                                break;
                            }else{
                                setColor(1,x,y,1,1,1);
                                setColor(2,x,y,1,1,1);
                                setColor(3,x,y,1,1,1);
                                setLight(1,x,y,dayLight);
                                setLight(2,x,y,dayLight);
                                setLight(3,x,y,dayLight);
                            }
                        }
                    }
                    for(int i=0; i<lights.size(); i++){
                        float lg[]=lights.get(i);
                        applyLightRec(map,3,(int)lg[0],(int)lg[1],(int)lg[2],(int)lg[3],1,lg[4],lg[5],lg[6]);
                        applyLightRec(map,2,(int)lg[0],(int)lg[1],(int)lg[2],(int)lg[3],1,lg[4],lg[5],lg[6]);
                        applyLightRec(map,1,(int)lg[0],(int)lg[1],(int)lg[2],(int)lg[3],1,lg[4],lg[5],lg[6]);
                    }
                    upd_str=str;
                    upd_end=end;
                    update=true;
                }
            }).start();
        }
    }



    public static float[][][][] colormap;
    public static float[][] colordir;
    public static float[][][] lightmap;

    public static void setColor(int l,int x,int y,float r,float g,float b){
        colormap[l][x][y][0]=r;
        colormap[l][x][y][1]=g;
        colormap[l][x][y][2]=b;
    }

    public static void setLight(int l,int x,int y,float s){
        lightmap[l][x][y]=s;
    }

    public static void init(TiledMap map){
        colormap=new float[4][map.layer(1).width][map.layer(1).height][3];
        colordir=new float[map.layer(1).width][map.layer(1).height];
        lightmap=new float[4][map.layer(1).width][map.layer(1).height];
    }



    public static int[] ignoreTiles={0,1,0,2,0,3,4,3,140,3};
    public static int[] ignoreTilesU={4,3,140,3};

    public static void applyLightRec(TiledMap map,int l,int cx,int cy,int mrlt,int mrlw,float a,float r,float g,float b){
        for(int i=-mrlw; i<mrlw; i++)
            for(int j=-mrlw; j<mrlw; j++)
                if(map.layer(3).inBounds(i+cx,j+cy))
                    colordir[i+cx][j+cy]=0;
        applyLightRecRGB(map,l,cx,cy,mrlt,mrlw,1,r,g,b);
        applyLightRecA(map,l,cx,cy,mrlt,mrlw,a);
    }

    public static void applyLightRecRGB(TiledMap map,int l,int cx,int cy,int mrlt,int mrlw,float lastLight,float r,float g,float b){
        if(lastLight<=0)
            return;

        float nr=colormap[l][cx][cy][0]*(1-lastLight)+r*lastLight;
        float ng=colormap[l][cx][cy][1]*(1-lastLight)+g*lastLight;
        float nb=colormap[l][cx][cy][2]*(1-lastLight)+b*lastLight;

        boolean nc=colormap[l][cx][cy][0]==-1;
        colormap[l][cx][cy][0]=nc?r:nr;
        colormap[l][cx][cy][1]=nc?g:ng;
        colormap[l][cx][cy][2]=nc?b:nb;

        colordir[cx][cy]=lastLight;

        float newLight=lastLight;
        boolean bt=true,bw=true;
        for(int i=0; i<ignoreTiles.length; i+=2){
            if(ignoreTiles[i+1]==3 && map.getTileId(ignoreTiles[i+1],cx,cy)==ignoreTiles[i])
                bt=false;
            if(ignoreTiles[i+1]==1 && map.getTileId(ignoreTiles[i+1],cx,cy)==ignoreTiles[i])
                bw=false;
        }

        if(bt && bw){
            newLight-=1f/mrlt;
        }else if(bt){
            newLight-=1f/mrlt;
        }else if(bw){
            newLight-=1f/mrlw;
        }else
            newLight-=1f/mrlw;

        if(cx+1<map.layer(l).width && newLight>colordir[cx+1][cy])
            applyLightRecRGB(map,l,cx+1,cy,mrlt,mrlw,newLight,r,g,b);
        if(cy+1<map.layer(l).height && newLight>colordir[cx][cy+1])
            applyLightRecRGB(map,l,cx,cy+1,mrlt,mrlw,newLight,r,g,b);
        if(cx-1>=0 && newLight>colordir[cx-1][cy])
            applyLightRecRGB(map,l,cx-1,cy,mrlt,mrlw,newLight,r,g,b);
        if(cy-1>=0 && newLight>colordir[cx][cy-1])
            applyLightRecRGB(map,l,cx,cy-1,mrlt,mrlw,newLight,r,g,b);
    }

    public static void applyLightRecA(TiledMap map,int l,int cx,int cy,int mrlt,int mrlw,float lastLight){
        if(lastLight<0)
            return;

        lightmap[l][cx][cy]=lastLight;

        float newLight=lastLight;
        boolean bt=true,bw=true;
        for(int i=0; i<ignoreTiles.length; i+=2){
            if(ignoreTiles[i+1]==3 && map.getTileId(ignoreTiles[i+1],cx,cy)==ignoreTiles[i])
                bt=false;
            if(ignoreTiles[i+1]==1 && map.getTileId(ignoreTiles[i+1],cx,cy)==ignoreTiles[i])
                bw=false;
        }

        if(bt && bw){
            newLight-=1f/mrlt;
        }else if(bt){
            newLight-=1f/mrlt;
        }else if(bw){
            newLight-=1f/mrlw;
        }else
            newLight-=1f/mrlw;

        if(cx+1<map.layer(l).width && newLight>lightmap[l][cx+1][cy])
            applyLightRecA(map,l,cx+1,cy,mrlt,mrlw,newLight);
        if(cy+1<map.layer(l).height && newLight>lightmap[l][cx][cy+1])
            applyLightRecA(map,l,cx,cy+1,mrlt,mrlw,newLight);
        if(cx-1>=0 && newLight>lightmap[l][cx-1][cy])
            applyLightRecA(map,l,cx-1,cy,mrlt,mrlw,newLight);
        if(cy-1>=0 && newLight>lightmap[l][cx][cy-1])
            applyLightRecA(map,l,cx,cy-1,mrlt,mrlw,newLight);
    }



}