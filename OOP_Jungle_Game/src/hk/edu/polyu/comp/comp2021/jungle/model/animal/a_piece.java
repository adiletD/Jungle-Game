package hk.edu.polyu.comp.comp2021.jungle.model.animal;
import hk.edu.polyu.comp.comp2021.jungle.model.tile;

import java.io.Serializable;

import static java.lang.Math.abs;

/**
 * a_piece is an abstract class that gives the general valid moves for most animals
 */
public abstract class a_piece implements Serializable {

    /**
     * team1: the number of pieces not eaten in team1
     * team2: the number of pieces not eaten in team2
     */
    private static int team1=8, team2=8;

    /**
     * team: what team the piece is in
     */
    private int team;
    /**
     * rank: what animal it is
     */
    private int rank;

    /**
     * @param a to access the board
     * @param old_tile the previous tile
     * @param new_tile the tile it wants to move to
     * @param team_number whose turn it is now
     * @return if its valid or not
     */
    public boolean isValid(tile[][] a, tile old_tile, tile new_tile, int team_number){
        if (old_tile.getAnimal().getTeam()!=team_number) return false;
        if (new_tile.getAnimal().getTeam()==team_number) return false;

        if (abs(old_tile.getX()-new_tile.getX()) + abs(old_tile.getY()-new_tile.getY())>1) return false;

        if (new_tile.getType()==2) return false;
        return new_tile.getAnimal().getRank() <= this.getRank();

    }

    /**
     * @param team_number which team is the eaten piece in
     */
    public static void pieces_eaten(int team_number){
        if (team_number==1){
            team1--;
        }
        if (team_number==2){
            team2--;
        }
    }

    /**
     * @param team_number which team do you want to know that's left
     * @return the number of pieces left in that team
     */
    public static int pieces_left(int team_number){
        if (team_number==1){
            return team1;
        }
        else {
            return team2;
        }
    }

    /**
     * @return return the team
     */
    public int getTeam() {
        return team;
    }

    /**
     * @return return the rank
     */
    public int getRank(){
        return rank;
    }

    /**
     * @param team_number the team to be assigned to the animal
     */
    void assignTeam(int team_number){
        team=team_number;
    }

    /**
     * @param rank_number the rank to be assigned to the animal
     */
    void assignRank(int rank_number){
        rank=rank_number;
    }

}


