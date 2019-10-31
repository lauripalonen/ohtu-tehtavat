package ohtuesimerkki;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Before;


public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers(){
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchReturnsCorrectPlayer() {
        String result = stats.search("Semenko").toString();

        assertEquals(readerStub.getPlayers().get(0).toString(), result);
    }

    @Test
    public void unsuccesfullSearchReturnsNull() {
        Player result = stats.search("pasi");

        assertNull(result);
    }

    @Test
    public void teamSearchReturnsCorrectPlayers() {
        List<Player> result = stats.team("EDM");

        StringBuilder resultString = new StringBuilder();

        result.forEach(player -> resultString.append(player.toString()));

        StringBuilder expectedString = new StringBuilder();

        expectedString.append(readerStub.getPlayers().get(0));
        expectedString.append(readerStub.getPlayers().get(2));
        expectedString.append(readerStub.getPlayers().get(4));

        assertEquals(expectedString.toString(), resultString.toString());

    }

    @Test
    public void correctTopScorerIsReturned() {
        List<Player> result = stats.topScorers(0);

        StringBuilder resultString = new StringBuilder();

        result.forEach(player -> resultString.append(player.toString()));

        StringBuilder expectedString = new StringBuilder();

        expectedString.append(readerStub.getPlayers().get(4));

        System.out.println(resultString.toString());
        System.out.println(expectedString.toString());

        assertEquals(expectedString.toString(), resultString.toString());

    }



}