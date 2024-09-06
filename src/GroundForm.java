import java.util.InputMismatchException;
import java.util.Scanner;

public class GroundForm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Player player = new Player("Hero", 100, 30, 3);  // Initial player stats
        int maxLevels = 3;  // Number of levels in the game
        int currentLevel = 1;

        while (player.isAlive() && currentLevel <= maxLevels) {
            System.out.println("\n--- Level " + currentLevel + " ---");

            // Create enemies for this level, making them stronger at higher levels
            Enemy[] enemies = createEnemiesForLevel(currentLevel);

            int enemyIndex = 0;
            while (player.isAlive() && enemyIndex < enemies.length) {
                Enemy currentEnemy = enemies[enemyIndex];
                System.out.println("\nA wild " + currentEnemy.name + " appears!");

                while (player.isAlive() && currentEnemy.isAlive()) {
                    System.out.println("\nPlayer's health: " + player.health);
                    System.out.println("Enemy's health: " + currentEnemy.health);
                    System.out.println("1. Attack");
                    System.out.println("2. Use Potion");
                    System.out.print("Choose your action: ");

                    int choice = 0;
                    boolean validInput = false;

                    // Error handling to ensure valid input
                    while (!validInput) {
                        try {
                            choice = scanner.nextInt();
                            if (choice == 1 || choice == 2) {
                                validInput = true;  // Valid input if 1 or 2
                            } else {
                                System.out.println("Invalid choice. Enter 1 to Attack or 2 to Use Potion.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number (1 for Attack or 2 for Potion).");
                            scanner.next();  // Clear the invalid input
                        }
                    }

                    // Player action based on the choice
                    switch (choice) {
                        case 1 -> {
                            player.attack(currentEnemy);
                            if (currentEnemy.isAlive()) {
                                currentEnemy.attack(player);
                            } else {
                                System.out.println(currentEnemy.name + " has been defeated!");
                                enemyIndex++;
                            }
                        }
                        case 2 -> {
                            player.usePotion();
                            if (currentEnemy.isAlive()) {
                                currentEnemy.attack(player);
                            }
                        }
                        default -> System.out.println("Invalid choice. Try again.");
                    }
                }
            }

            // If the player defeated all enemies on this level, level up
            if (player.isAlive()) {
                System.out.println("\nYou've cleared Level " + currentLevel + "!");
                levelUp(player);  // Increase player stats when leveling up
                currentLevel++;   // Move to the next level
            }
        }

        // End the game
        if (player.isAlive()) {
            System.out.println("\nCongratulations! You've defeated all levels!");
        } else {
            System.out.println("\nGame Over! The enemies defeated you.");
        }

        scanner.close();
    }

    // Function to create stronger enemies for each level
    public static Enemy[] createEnemiesForLevel(int level) {
        return new Enemy[]{
            new Enemy("Goblin", 40 + level * 10, 15 + level * 5),
            new Enemy("Orc", 60 + level * 15, 20 + level * 5),
            new Enemy("Dragon", 120 + level * 20, 35 + level * 10)
        };
    }

    // Function to level up the player
    private static void levelUp(Player player) {
        player.health += 30;  // Increase health on level up
        player.attackPower += 10;  // Increase attack power on level up
        System.out.println("\nLevel Up! Your health and attack power have increased!");
        System.out.println("New Health: " + player.health + ", New Attack Power: " + player.attackPower);
    }
}
