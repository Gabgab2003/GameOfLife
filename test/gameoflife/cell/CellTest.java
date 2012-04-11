/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.cell;

import gameoflife.cell.states.Dead;
import java.util.Random;
import gameoflife.cell.states.Alive;
import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mario
 */
public class CellTest {
    Cell cell;
    List<Cell> neighbours;
    Integer count;
    public CellTest() {
    }
    @Before
    public void setUp() 
    {
        this.cell = new Cell();
        this.neighbours = new ArrayList<Cell>();
        this.count = 0;
                for(int i =0;i<7;i++)
        {
            if(i!=4)
            {
                Cell neighbour = new Cell();
                neighbour.addObserver(cell);
                double rand = new Random().nextInt(100);
                if(rand%2==0)
                {
                    neighbour.setState(new Alive());
                    count++;
                }
                neighbours.add(neighbour);
            }
        }
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testNoOfAliveNeighbours()
    {
        System.out.println("testNoOfAliveNeighbours");
        assertEquals(count, cell.getNoOfAliveNeighbours());
    }
    
    @Test
    public void testNoOfAliveNeighboursAfterSettingOneAliveNeighbourDead()
    {
        System.out.println("testNoOfAliveNeighboursAfterSettingOneAliveNeighbourDead");
        for(Cell neighbour : neighbours)
        {
            if(neighbour.getState().getClass()==Alive.class)
            {
                neighbour.setState(new Dead());
                count--;
                break;
            }
        }
        assertEquals(count, cell.getNoOfAliveNeighbours());
    }
        @Test
    public void testNoOfAliveNeighboursAfterSettingOneDeadNeighbourAlive()
    {
        System.out.println("testNoOfAliveNeighboursAfterSettingOneDeadNeighbourAlive");
        for(Cell neighbour : neighbours)
        {
            if(neighbour.getState().getClass()==Dead.class)
            {
                neighbour.setState(new Alive());
                count++;
                break;
            }
        }
        assertEquals(count, cell.getNoOfAliveNeighbours());
    }
}
