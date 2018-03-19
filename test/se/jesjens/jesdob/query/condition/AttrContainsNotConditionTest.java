/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jesjens.jesdob.query.condition;

import org.junit.*;
import static org.junit.Assert.*;
import se.jesjens.jesdob.Dob;
import se.jesjens.jesdob.IDob;

/**
 *
 * @author jenss
 */
public class AttrContainsNotConditionTest {

  /**
   * Test of isTrue method, of class AttrContainsNotCondition.
   */
  @Test
  public void testIsTrue() {
    IDob dob1 = new Dob();
    dob1.attr("attr1", "string number 1");
    
    IDob dob2 = new Dob();
    dob2.attr("attr1", "string 1");
    
    IDob dob3 = new Dob(); 
    
    ICondition condition = new AttrContainsNotCondition("attr1", "number 1");
    
    assertFalse(condition.isTrue(dob1));
    assertTrue(condition.isTrue(dob2));
    assertTrue(condition.isTrue(dob3));
  }
}
