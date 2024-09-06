// Player class inheriting from Character
public class Player extends Character {
    int potionCount;

    public Player(String name, int health, int attackPower, int potionCount) {
        super(name, health, attackPower);
        this.potionCount = potionCount;
    }

    public void usePotion() {
        if (potionCount > 0) {
            health += 30;
            potionCount--;
            System.out.println(name + " uses a potion and restores 30 health!");
        } else {
            System.out.println(name + " has no potions left!");
        }
    }
}
