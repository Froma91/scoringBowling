import org.example.GameMock;
import org.example.TableauAffichage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class GameMockTest {
    private GameMock game;
    private TableauAffichage tableauAffichage;

    @BeforeEach
    void setUp() {
        tableauAffichage = Mockito.mock(TableauAffichage.class);
        game = new GameMock(tableauAffichage);
    }

    // test de la méthode seConnecter()
    @Test
    @DisplayName("seConnecter() doit être appelée une fois")
    void testSeConnecter() {
        verify(tableauAffichage).seConnecter();
    }

    // test de la méthode showSpare()
    @Test
    @DisplayName("showSpare() doit être appelée une fois")
    void testShowSpare() {
        game.roll(5);
        game.roll(5);
        verify(tableauAffichage).showSpare();
    }
    // test de la méthode showStrike()
    @Test
    @DisplayName("showStrike(PREMIER) doit être appelée une fois")
    void testShowStrikePremier() {
        game.roll(10);
        verify(tableauAffichage).showStrike(TableauAffichage.StrikeSerie.PREMIER);
    }
    // test de la méthode showStrike()
    @Test
    @DisplayName("showStrike(SECOND) doit être appelé lors du deuxième strike")
    void testShowStrikeSecond() {
        game.roll(10);
        game.roll(10);
        verify(tableauAffichage).showStrike(TableauAffichage.StrikeSerie.SECOND);
    }
    // test de la méthode showStrike()
    @Test
    @DisplayName("showStrike(TROISIEME_ET_PLUS) doit être appelée une fois")
    void testShowStrikeTroisiemeEtPlus() {
        game.roll(10);
        game.roll(10);
        game.roll(10);
        verify(tableauAffichage).showStrike(TableauAffichage.StrikeSerie.TROISIEME_ET_PLUS);
    }

    // test de la méthode bestScore()
    @Test
    @DisplayName("affichage bestScores")
    void testShowBestScore() {
        // préparer les meilleures score simulés
        List<Integer> bestScore = Arrays.asList(100, 150, 200);
        when(tableauAffichage.bestScores()).thenReturn(bestScore);
        //appeler la méthode qui utilise bestScore()
        game.endGame();
        // vérification appel de la méthode bestScores
        verify(tableauAffichage).bestScores();
    }

    // test définition meilleure score + update de la list meilleures scores
    @Test
    @DisplayName("mise à jour des meilleurs scores")
    void testUpdateBestScores() {
        // Préparer les meilleurs scores simulés
        List<Integer> bestScores = Arrays.asList(100, 150, 200);
        when(tableauAffichage.bestScores()).thenReturn(bestScores);

        // Simuler des rolls pour générer un score suffisamment élevé
        game.roll(10); // Frame 1: Strike
        game.roll(10); // Frame 2: Strike
        game.roll(10); // Frame 3: Strike
        game.roll(10); // Frame 4: Strike
        game.roll(10); // Frame 5: Strike
        game.roll(10); // Frame 6: Strike
        game.roll(10); // Frame 7: Strike
        game.roll(10); // Frame 8: Strike
        game.roll(10); // Frame 9: Strike
        game.roll(10); // Frame 10: Strike
        game.roll(10); // Bonus roll 1
        game.roll(10); // Bonus roll 2

        // Appeler la méthode endGame qui devrait mettre à jour les meilleurs scores
        game.endGame();

        // Vérifier que la méthode updateBestScores() a été appelée si le score est meilleur
        verify(tableauAffichage).updateBestScores(game.score());
    }
}
