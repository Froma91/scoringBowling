import org.example.GameMock;
import org.example.TableauAffichage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.Arrays;

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
    @DisplayName("showSpare doit être appelée une fois")
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
        verify(tableauAffichage).showStrike(TableauAffichage.StrikeSerie.PREMIER);
        verify(tableauAffichage).showStrike(TableauAffichage.StrikeSerie.SECOND);
    }

    // test de la méthode showStrike()
    @Test
    @DisplayName("showStrike(TROISIEME_ET_PLUS) doit être appelée une fois")
    void testShowStrikeTroisiemeEtPlus() {
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        verify(tableauAffichage).showStrike(TableauAffichage.StrikeSerie.PREMIER);
        verify(tableauAffichage).showStrike(TableauAffichage.StrikeSerie.SECOND);
        verify(tableauAffichage, VerificationModeFactory.times(4)).showStrike(TableauAffichage.StrikeSerie.TROISIEME_ET_PLUS);
    }

    // test de la méthode updateBestScores()
    @Test
    @DisplayName("updateBestScores doit être appelée une fois")
    void testUpdateBestScores() {
        when(tableauAffichage.bestScores()).thenReturn(Arrays.asList(5,6,7));
        game.roll(10);
        game.endGame();
        verify(tableauAffichage).updateBestScores(10);
    }
}
