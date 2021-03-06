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
public class BiggerOrEqualToConditionTest {
  
  /**
   * Test of isTrue method, of class BiggerOrEqualToCondition.
   */
  @Test
  public void testIsTrue() {
    IDob dob1 = new Dob();
    dob1.attr("attr1", "string");
    
    IDob dob2 = new Dob();
    dob2.attr("attr1", 200);
    
    IDob dob3 = new Dob();
    dob3.attr("attr1", 201);
    
    IDob dob4 = new Dob();
    dob4.attr("attr1", 199);    
    
    ICondition condition = new BiggerOrEqualToCondition("attr1", 200);
    
    assertFalse(condition.isTrue(dob1));
    assertTrue(condition.isTrue(dob2));
    assertTrue(condition.isTrue(dob3));
    assertFalse(condition.isTrue(dob4));
  }
}
