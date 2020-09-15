package hk.edu.polyu.comp.comp2021.jungle.model.animal;

/**
 * this class extends from a_piece to create the animal cat
 */
public class Cat extends a_piece {
    /**
     * @param team what team the cat is in
     */
    public Cat(int team){
        this.assignTeam(team);
        this.assignRank(2);
    }
}
