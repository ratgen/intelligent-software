package PlayerTest;

import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.player.PlayerPlugin;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 *  Testing the player class
 * @author peter
 */

public class PlayerTest{
	
	@Test
	public void createPlayer(){
		PlayerPlugin pp = new PlayerPlugin();
        GameData gameData = mock(GameData.class); 
        World world = new World();

        when(gameData.getDisplayHeight()).thenReturn(400);
        when(gameData.getDisplayWidth()).thenReturn(400);
        
        pp.start(gameData, world);
        
        assertEquals( 1, world.getEntities().size());
	}	
}