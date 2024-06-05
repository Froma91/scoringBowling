import org.example.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    // test 1 qui contient 20 lancers à 0 points. resultat attendu 0
    @Test
    @DisplayName("20 rolls at 0 points should return 0")
    public void testZero() {
        Game game = new Game();
        for (int i=0; i<20; i++) {
            game.roll(0);
        }
        assertEquals(0, game.score());
    }
    // test 2 qui contient 20 lancers à 1 point. resultat attendu 20
    @Test
    @DisplayName("20 rolls at 1 point should return 20")
    public void testAllOnes() {
        Game game = new Game();
        for (int i=0; i<20; i++) {
            game.roll(1);
        }
        assertEquals(20, game.score());
    }
    // test 3 qui contient un "spare" (5+5), puis un 3 et un lancer à 4 points. resultat attendu 24
    @Test
    @DisplayName("One spare should return 24")
    public void testOneSpare() {
        Game game = new Game();
        game.roll(5);
        game.roll(5); // spare
        game.roll(3);
        for (int i=0; i<17; i++) {
            game.roll(0);
        }
        assertEquals(16, game.score());
    }
    // test 4 qui contient un "strike" (10), puis un 3 et un 4. resultat attendu 24
    @Test
    @DisplayName("One strike should return 24")
    public void testOneStrike() {
        Game game = new Game();
        game.roll(10); // strike
        game.roll(3);
        game.roll(4);
        for (int i=0; i<16; i++) {
            game.roll(0);
        }
        assertEquals(24, game.score());
    }
    // test 5 qui contient un "perfect game" (12 strikes). resultat attendu 300
    @Test
    @DisplayName("Perfect game should return 300")
    public void testPerfectGame() {
        Game game = new Game();
        for (int i=0; i<12; i++) {
            game.roll(10);
        }
        assertEquals(300, game.score());
    }
}
