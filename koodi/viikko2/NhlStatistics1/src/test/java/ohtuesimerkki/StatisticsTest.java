/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    
    public StatisticsTest() {
    }
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
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
    public void setUp(){
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    } 
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    //new Player("Kurri",   "EDM", 37, 53)
    
    @Test
    public void searchReturnsTheCorrectPlayer() {
        Player p = stats.search("Kurri");
        assertTrue(p.getName().equals("Kurri") && p.getPoints() == 90);
    }
    
    @Test
    public void searchReturnsNullIfNotFound() {
        assertEquals(null, stats.search("Apina"));
    }
    
    @Test
    public void teamReturnsAllTheCorrectPlayers() {
        List<Player> edm = stats.team("EDM");
        assertEquals(3, edm.size());
        
        for(Player p : edm) {
            assertEquals("EDM", p.getTeam());
        }
    }
    
    @Test
    public void teamReturnsEmptyListIfNotFound() {
        assertTrue(stats.team("TEE").isEmpty());
    }
    
    //>= 0 --> 2->1, normally I'd change the code, but this seems more in line with the instructions
    @Test
    public void topScorersReturnsTheCorrectPlayersWithArgumentTwo() {
        for(Player p: stats.topScorers(1)) {
            assertTrue((p.getName().equals("Gretzky") && p.getPoints() == 124)
                        || (p.getName().equals("Lemieux") && p.getPoints() == 99));
        }
    }
    
    //>= 0 --> 1->0
    @Test
    public void topScorersReturnsTheCorrectPlayerWithArgumentOne() {
        Player p = stats.topScorers(0).get(0);
        assertTrue(p.getName().equals("Gretzky") && p.getPoints() == 124);
    }
    
    @Test
    public void topScorersReturnsEmptyListWithNegativeArgument() {
        assertTrue(stats.topScorers(-4).isEmpty());
    }
}
