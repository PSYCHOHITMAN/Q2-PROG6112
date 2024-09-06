import java.util.Random;

// Base Character class
public class Character {
    String name;
    int health;
    int attackPower;
    
    public Character(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    public void attack(Character enemy) {
        Random rand = new Random();
        int damage = rand.nextInt(attackPower);
        System.out.println(name + " attacks " + enemy.name + " for " + damage + " damage!");
        enemy.takeDamage(damage);
    }
}
