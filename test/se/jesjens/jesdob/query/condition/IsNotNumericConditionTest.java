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
public class IsNotNumericConditionTest {

  /**
   * Test of isTrue method, of class IsNotNumericCondition.
   */
  @Test
  public void testIsTrue() {
    IDob dob1 = new Dob();

    IDob dob2 = new Dob();
    dob2.attr("attr1", true);

    IDob dob3 = new Dob();
    dob3.attr("attr1", 234234);

    IDob dob4 = new Dob();
    dob4.attr("attr1", 0.4f);

    IDob dob5 = new Dob();
    dob5.attr("attr1", "not very numeric");
    
    IDob dob6 = new Dob();
    dob6.attr("attr1", "432");    

    ICondition condition = new IsNotNumericCondition("attr1");

    assertTrue(condition.isTrue(dob1));
    assertTrue(condition.isTrue(dob2));
    assertFalse(condition.isTrue(dob3));
    assertFalse(condition.isTrue(dob4));
    assertTrue(condition.isTrue(dob5));
    assertFalse(condition.isTrue(dob6));
  }
}
