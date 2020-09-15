package hk.edu.polyu.comp.comp2021.jungle.model.animal;

/**
 * the animal dog
 */
public class Dog extends a_piece {
    /**
     * @param team what team is the dog in
     */
    public Dog(int team){
        this.assignTeam(team);
        this.assignRank(3);
    }
}
