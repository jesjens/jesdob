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
public class HasNotAttributeConditionTest {

  /**
   * Test of isTrue method, of class HasNotAttributeCondition.
   */
  @Test
  public void testIsTrue() {
    IDob dob1 = new Dob();
    
    IDob dob2 = new Dob();
    dob2.attr("attr1", "200");  
    
    ICondition condition = new HasNotAttributeCondition("attr1");
    
    assertTrue(condition.isTrue(dob1));
    assertFalse(condition.isTrue(dob2));
  }
}
