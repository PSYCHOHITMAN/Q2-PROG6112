import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GroundFormTest {

    private GroundForm game;

    @Before
    public void setUp() {
        game = new GroundForm();
    }

    @Test
    public void testCreateEnemiesForLevel() {
        Enemy[] enemies = GroundForm.createEnemiesForLevel(1);
        assertEquals(3, enemies.length);
        assertEquals("Goblin", enemies[0].name);
        assertEquals(50, enemies[0].health);
        assertEquals(20, enemies[0].attackPower);

        enemies = GroundForm.createEnemiesForLevel(2);
        assertEquals(3, enemies.length);
        assertEquals("Goblin", enemies[0].name);
        assertEquals(60, enemies[0].health);
        assertEquals(25, enemies[0].attackPower);
    }


}
