package myterraria;

import engine.graphics.OrthographicCamera;
import engine.utils.Timer;
import engine.graphics.OldSpriteBatch;
import engine.maths.Maths;
import engine.tiledmap.TileManager;
import engine.tiledmap.TiledMap;
import engine.utils.Assets;
import myterraria.interfaces.ItemManager;
import myterraria.interfaces.ItemStack;
import myterraria.interfaces.ToolBar;
import myterraria.interfaces.Value;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;

public class TerrariaJE extends Main{



	public OldSpriteBatch sb;
	public OrthographicCamera cam;
	public int timer1=200;

	public static TiledMap map;
	public TileManager tileManager;
	public static int tile_pixel_size=32;//21.78,32,43.76
	public long seed=Maths.randomSeed(8);//81318082;//33268463;

	public float time=0;

	public myterraria.interfaces.ToolBar toolBar;

	public static Timer timer;
	public int minutes,hours;

	public static Player player;



	public void create(){
		sb=new OldSpriteBatch(WIDTH,HEIGHT);
		cam=new OrthographicCamera(WIDTH,HEIGHT);

		int map_width=500;
		int map_height=200;
		int surface=150;

		//cam.fullScreen(true);
		//cam.translate(map_width/2f*tile_pixel_size-Main.WIDTH/2f,surface*tile_pixel_size);

		tileManager=new TileManager();
		map=new TiledMap();
		map.addLayer(1,map_width,map_height,tile_pixel_size,tile_pixel_size);//стены
		map.addLayer(2,map_width,map_height,tile_pixel_size,tile_pixel_size);//ветки, вершины деревьев, трава
		map.addLayer(3,map_width,map_height,tile_pixel_size,tile_pixel_size);//блоки
		map.setView(2,4);
		map.setView(1,4);
		map.setView(3,4);
		WorldGenerator.generate(map,seed,surface);
		//WorldGenerator.isGenerated=true;
		//map.load("/home/user/Downloads/world.wld");

		LoadResources.loadResources();
		LoadResources.defineTilesAndItems(tileManager);

		Lights.init(map);

		toolBar=new ToolBar(cam,10);
		toolBar.setItem(new ItemStack(2),0);
		toolBar.setItem(new ItemStack(3),1);
		toolBar.setItem(new ItemStack(8),2);
		toolBar.setItem(new ItemStack(9),3);
		toolBar.setItem(new ItemStack(520),4);
		toolBar.setItem(new ItemStack(757),5);
		toolBar.setItem(new ItemStack(7),6);
		toolBar.setItem(new ItemStack(2763),7);
		toolBar.setItem(new ItemStack(2764),8);
		toolBar.setItem(new ItemStack(2765),9);

		timer=new Timer();

		player=new Player(map,map_width/2f,surface,2,3);
	}


	public int timer2;

	public int effect_timer;
	public float effect_force,effect_x;
	public boolean effect;


	public void render(){
		float delt=DELTA_TIME*75;
		time+=delt;
		glClearColor(0,0,0,1);
		cam.setPosition(player.draw_x-cam.getWidth()/2f+player.draw_width/2f,player.draw_y-cam.getHeight()/2f+player.draw_height/2f);
		sb.begin(cam.getProjection());

		/*RecursiveLights.dayLight=(float)Math.sin(timer.getMillis()/5000f)*5+1;
		if(RecursiveLights.dayLight>1)
			RecursiveLights.dayLight=1;
		if(RecursiveLights.dayLight<0)
			RecursiveLights.dayLight=0;
		RecursiveLights.dayLight+=0.1;
		if(RecursiveLights.dayLight>1)
			RecursiveLights.dayLight=1;
		RecursiveLights.updateScreenLights(map,cam);*/

		if(WorldGenerator.isGenerated){
			sb.setColor(Lights.dayLight, Lights.dayLight, Lights.dayLight,1);
			sb.draw(Assets.getTexture("Background_0"),cam.getX(),cam.getY(),cam.getWidth(),cam.getHeight());
			sb.setColor(1,1,1,1);


			if(shadows_off){
				int l=3;
				for(int i=0; i<map.layer(l).width; i++)
					for(int j=0; j<map.layer(l).height; j++){
						map.layer(l).colormap[i][j][0]=1;
						map.layer(l).colormap[i][j][1]=1;
						map.layer(l).colormap[i][j][2]=1;
					}
				l=1;
				for(int i=0; i<map.layer(l).width; i++)
					for(int j=0; j<map.layer(l).height; j++){
						map.layer(l).colormap[i][j][0]=1;
						map.layer(l).colormap[i][j][1]=1;
						map.layer(l).colormap[i][j][2]=1;
					}
			}


			//ShaderProgram shader1=Assets.getShader("map_shader");
			//shader1.begin();
			//shader1.setUniformf("u_time",time);
			//sb.setShader(shader1);

			map.draw(tileManager,sb,cam);

			//shader1.end();
			//sb.setShader(null);


			/*if(effect){
				ShaderProgram shader2=Assets.getShader("shader");
				shader2.begin();
				shader2.setUniformf("u_force",effect_force);
				sb.setShader(shader2);

				map.layer(3).draw(tileManager,sb,cam,effect_x,0);
				map.layer(3).draw(tileManager,sb,cam,-effect_x,0);
				map.layer(1).draw(tileManager,sb,cam,effect_x,0);
				map.layer(1).draw(tileManager,sb,cam,-effect_x,0);
				map.layer(2).draw(tileManager,sb,cam,effect_x,0);
				map.layer(2).draw(tileManager,sb,cam,-effect_x,0);

				shader2.end();
				sb.setShader(null);
			}*/


			//shader1=Assets.getShader("map_shader");
			//shader1.begin();
			//shader1.setUniformf("u_time",time);
			//sb.setShader(shader1);

			player.draw(sb,map,delt);

			//shader1.end();
			//sb.setShader(null);



			toolBar.draw(sb,cam);

			Lights.update(map,cam);

			minutes=(int)timer.getSeconds()-60*(int)Math.floor(timer.getSeconds()/60f);
			hours=(int)timer.getMinutes()-24*(int)Math.floor(timer.getMinutes()/24f);

			timer2++;
			if(timer2>4.25*delt){
				timer2=0;
				ItemManager.getItem(520).animation.next();
				ItemManager.getItem(521).animation.next();
				ItemManager.getItem(547).animation.next();
				ItemManager.getItem(548).animation.next();
				ItemManager.getItem(549).animation.next();
				ItemManager.getItem(575).animation.next();
			}

			if(timer1>0)
				timer1-=delt;
			if(timer1>0){
				//sb.setColor(1,1,1,timer1/200f);
				//sb.draw(Assets.getTexture("black_pixel"),cam.getX(),cam.getY(),cam.getX()+Main.WIDTH,cam.getY()+Main.HEIGHT);
				//sb.setColor(1,1,1,1);

				/*BitmapFont font=Assets.getTTF("font1");
				float percents=Float.parseFloat(new String(""+WorldGenerator.percents).substring(0,new String(""+WorldGenerator.percents).lastIndexOf(".")+2));
				String text1=WorldGenerator.stage+" "+percents+"%";
				font.setColor(1,1,1,timer1/200f);
				font.draw(sb,text1,cam.x+cam.width/2-font.getCache().addText(text1,0,0).width/2,cam.y+cam.height/2-font.getCache().addText(text1,0,0).height/2);

				BitmapFont font2=Assets.getTTF("font2");
				font2.setColor(1,1,1,timer1/200f);
				font2.draw(sb,"F3 for more info; F11 for fullscreen; Esc for exit; U,I for night,day; F4,F5 for save,load map; F7 for disable light;",cam.x+20,cam.y+20+font2.getLineHeight());*/
			}
		}else{
			/*BitmapFont font=Assets.getTTF("font1");
			float percents=Float.parseFloat(new String(""+WorldGenerator.percents).substring(0,new String(""+WorldGenerator.percents).lastIndexOf(".")+2));
			String text1=WorldGenerator.stage+" "+percents+"%";
			font.draw(sb,text1,cam.x+cam.width/2-font.getCache().addText(text1,0,0).width/2,cam.y+cam.height/2-font.getCache().addText(text1,0,0).height/2);

			BitmapFont font2=Assets.getTTF("font2");
			font2.setColor(1,1,1,timer1/200f);
			font2.draw(sb,"F3 for more info; F11 for fullscreen; Esc for exit; U,I for night,day; F4,F5 for save,load map; F7 for disable light;",cam.x+20,cam.y+20+font2.getLineHeight());*/
		}

		controls(delt);
		player.update(map,cam,delt);

		sb.end();

		if(effect){
			effect_timer+=delt;
			float force=(float)Math.sin(effect_timer*Math.PI/500f);
			effect_force=force*0.35f;
			effect_x=force*(float)Math.sin(time)*map.layer(3).tiles_offset_x/1.61f;
			if(effect_timer>500){
				effect=false;
				effect_timer=0;
				effect_force=0;
				effect_x=0;
			}
		}
	}


	public boolean f3_pressed,show_f3_info,prev_f3_pressed,shadows_off;


	public void controls(float delt){
		int tx=(int)Math.floor((Main.MOUSE.getX()+cam.getX())/map.layer(3).tiles_offset_x);
		int ty=(int)Math.floor((Main.HEIGHT-Main.MOUSE.getY()+cam.getY())/map.layer(3).tiles_offset_y);

		if(KEYBOARD.isKeyJustPressed(GLFW_KEY_U)){
			Lights.dayLight=0.1f;
			Lights.updateScreenLights(map,cam);
		}

		if(KEYBOARD.isKeyJustPressed(GLFW_KEY_I)){
			Lights.dayLight=1f;
			Lights.updateScreenLights(map,cam);
		}

		if(KEYBOARD.isKeyJustPressed(GLFW_KEY_F))
			effect=true;

		if(KEYBOARD.isKeyJustPressed(GLFW_KEY_F7) && !shadows_off){
			shadows_off=true;
			Lights.updateScreenLights(map,cam);
		}else if(KEYBOARD.isKeyJustPressed(GLFW_KEY_F7)){
			shadows_off=false;
			Lights.updateScreenLights(map,cam);
		}

		if(KEYBOARD.isKeyPressed(GLFW_KEY_F3)){
			f3_pressed=true;
		}else
			f3_pressed=false;
		if(!prev_f3_pressed && f3_pressed){
			if(show_f3_info)
				show_f3_info=false;
			else
				show_f3_info=true;
		}
		if(prev_f3_pressed!=f3_pressed)
			prev_f3_pressed=f3_pressed;

		if(show_f3_info){
			float maxMem=ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
			String memStr=""+((ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()));
			float mem=Float.parseFloat(memStr.substring(0,Math.max((memStr.length()-8),2)));
			//BitmapFont font=Assets.getTTF("font2");

			List<String> params=new ArrayList<>();
			params.add("FPS: "+FPS);
			params.add("Seed: "+seed);
			params.add("Mem: "+mem+"/"+maxMem+" MB");
			params.add("World size: "+map.layer(3).width+", "+map.layer(3).height);
			params.add("Tile x: "+tx+", y: "+ty);
			params.add("Game time: "+hours+":"+minutes);
			params.add("Player velocity x: "+player.rect.velocity.x+" y: "+player.rect.velocity.y);
			params.add("Jump: "+player.jump);

			//sb.setShader(Assets.getShader("inv_shader"));
			for(int i=0; i<params.size(); i++)
				//font.draw(sb,params.get(i),cam.getX()+20,cam.getY()+cam.getHeight()-font.getCache().addText(params.get(i),0,0).height-40*i);
				System.out.println(params.get(i));
			//sb.setShader(null);
		}

		//if(KEYBOARD.isKeyJustPressed(Input.Keys.F11))
		//	cam.fullScreen(!cam.fullscreen);

		if(MOUSE.RBM() && map.getTileId(3,tx,ty)!=0){
			if(TiledMapUtils.setTile(map,cam,3,tx,ty,0)){
				//if(map.getTileId(3,tx,ty)==3)
				//	Assets.getSound("Tink_"+Maths.random(0,2)).play();
				//else
				//	Assets.getSound("Dig_"+Maths.random(0,2)).play();
			}
		}
		if(MOUSE.LBM()){
			ItemStack item=toolBar.getSelectedItem();
			if(item!=null){
				Value item_type=ItemManager.getTag(item.id,"item_type");
				if(item_type.getValue()=="tile" && map.getTileId(3,tx,ty)==0){
					Value tile_id=ItemManager.getTag(item.id,"tile_id");
					//if(TiledMapUtils.setTile(map,cam,3,tx,ty,(Integer)tile_id.getValue()))
					//	Assets.getSound("Dig_"+Maths.random(0,2)).play();
				}
			}
			//map.layer(3).colormap[tx][ty][0]=map.layer(3).colormap[tx][ty][0]*0.5f+0.5f*0.3f;
			//map.layer(3).colormap[tx][ty][1]=map.layer(3).colormap[tx][ty][1]*0.5f+0.5f*0.5f;
			//map.layer(3).colormap[tx][ty][2]=map.layer(3).colormap[tx][ty][2]*0.5f+0.5f*1;
		}

		/*int scrolled=mouse.scrolled();
		toolBar.scroll(scrolled);
		if(scrolled!=0)
			Assets.getSound("Mech_0").play();
		for(int i=0; i<toolBar.size; i++)
			if(Gdx.input.isKeyJustPressed(i==9?7:i+8))
				if(toolBar.selected_cell_position!=i){
					toolBar.selected_cell_position=i;
					Assets.getSound("Mech_0").play();
				}*/

		if(KEYBOARD.isKeyPressed(GLFW_KEY_EQUAL) && map.layer(1).tiles_offset_x<=43.76){
			map.layer(1).tiles_offset_x+=delt/4;
			map.layer(2).tiles_offset_x+=delt/4;
			map.layer(3).tiles_offset_x+=delt/4;

			map.layer(1).tiles_offset_y+=delt/4;
			map.layer(2).tiles_offset_y+=delt/4;
			map.layer(3).tiles_offset_y+=delt/4;
			player.updateDrawPosition(map);
		}
		if(KEYBOARD.isKeyPressed(GLFW_KEY_MINUS) && map.layer(1).tiles_offset_x>=21.78){
			map.layer(1).tiles_offset_x-=delt/4;
			map.layer(2).tiles_offset_x-=delt/4;
			map.layer(3).tiles_offset_x-=delt/4;

			map.layer(1).tiles_offset_y-=delt/4;
			map.layer(2).tiles_offset_y-=delt/4;
			map.layer(3).tiles_offset_y-=delt/4;
			player.updateDrawPosition(map);
		}

		if(KEYBOARD.isKeyPressed(GLFW_KEY_ESCAPE))
			System.exit(0);

		if(KEYBOARD.isKeyJustPressed(GLFW_KEY_F4)){
			map.save("/home/user/Downloads/world.wld");
		}
		if(KEYBOARD.isKeyJustPressed(GLFW_KEY_F5)){
			map.load("/home/user/Downloads/world.wld");
			Lights.updateScreenLights(map,cam);
		}
	}



	public void dispose(){
		sb.dispose();
		Assets.dispose();
	}

	public void resize(int w,int h){
		//cam.resize(w,h);
		Lights.updateScreenLights(map,cam);
	}

	public void pause(){

	}

	public void resume(){

	}

}
