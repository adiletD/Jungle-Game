package hk.edu.polyu.comp.comp2021.jungle.model;
import hk.edu.polyu.comp.comp2021.jungle.model.animal.a_piece;

import java.io.Serializable;

/**
 * the properties of each tile
 */
public class tile implements Serializable {
    /**
     * what animal is in the tile
     */
    private a_piece animal;
    /**
     * tile_type: 1 = dirt, 2:water, 3:trap, 4: den, 9 : error
     */
    private int type;
    /**
     * the position of the tile's x axis
     */
    private final int x;
    /**
     * the position of the tile's y axis
     */
    private final int y;

    /**
     * @param animal what animal is in the tile
     * @param tile_type the type of the tile
     * @param x when initializing, what is the x axis
     * @param y when initializing, what is the y axis
     */
    tile(a_piece animal,int tile_type, int x, int y){
        this.animal = animal;
        this.type = tile_type;
        this.x=x;
        this.y=y;
    }

    /**
     * @return return x axis of the tile
     */
    public int getX(){
        return x;
    }

    /**
     * @return return y axis of the tile
     */
    public int getY(){
        return y;
    }

    /**
     * @return the animal
     */
    public a_piece getAnimal(){
        return animal;
    }

    /**
     * @param animal_assigned the animal to the be assigned to the tile
     */
    public void assignAnimal(a_piece animal_assigned){
        animal=animal_assigned;
    }

    /**
     * @return get the tile type
     */
    public int getType(){
        return type;
    }

    /**
     * @param type_assigned the type to be assigned to the tile
     */
    public void assignType(int type_assigned){
        type=type_assigned;
    }

}