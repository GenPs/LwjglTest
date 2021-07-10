package myterraria.interfaces;

import engine.graphics.OrthographicCamera;
import engine.graphics.OldSpriteBatch;
import engine.graphics.TextureRegion;
import engine.utils.Assets;

public class ToolBar implements Interface{

    public ToolBar(OrthographicCamera cam,int s){
        size=s;
        cell_size=Math.round(cam.getHeight()/15f);
        offset=Math.round((cell_size*1.5f)/10.5f);

        x=cell_size/2;


        items=new ItemStack[size];
    }

    public int cell_size;
    public int size;
    public int offset;
    public int x,y;
    public int selected_cell_position;

    public ItemStack[] items;


    public void draw(OldSpriteBatch sb,OrthographicCamera cam){
        y=Math.round(cam.getHeight())-offset-cell_size-cell_size/2;

        for(int i=0; i<size; i++){
            sb.setColor(1,1,1,0.9f);
            sb.draw(Assets.getTexture("Inventory_Back"+(i==selected_cell_position?"14":"")),cam.getX()+x+cell_size*i+offset*i,cam.getY()+y,cell_size,cell_size);
            sb.setColor(1,1,1,1);
            ItemStack itemStack=getItem(i);
            if(itemStack!=null){
                Item item=ItemManager.getItem(itemStack.id);
                if(item!=null){
                    Animation animation=item.animation;
                    if(animation!=null){
                        TextureRegion texture=animation.currentFrame();
                        if(texture!=null)
                            sb.draw(texture,cam.getX()+x+cell_size*i+offset*i+cell_size*item.ox,cam.getY()+y+cell_size*item.oy,cell_size*item.w,cell_size*item.h);
                    }
                }
            }
        }
    }

    public void scroll(int amount){
        selected_cell_position+=amount;
        if(selected_cell_position>=size)
            selected_cell_position-=size;
        if(selected_cell_position<0)
            selected_cell_position+=size;
    }

    public void setItem(ItemStack item,int x){
        items[x]=item;
    }

    public ItemStack getSelectedItem(){
        return items[selected_cell_position];
    }

    public ItemStack getItem(int position){
        return items[position];
    }

}
