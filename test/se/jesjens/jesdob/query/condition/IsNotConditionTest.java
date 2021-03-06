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
public class IsNotConditionTest {

  /**
   * Test of isTrue method, of class IsNotCondition.
   */
  @Test
  public void testIsTrue() {
    IDob dob1 = new Dob();

    IDob dob2 = new Dob();
    dob2.attr("attr1", true);

    IDob dob3 = new Dob();
    dob3.attr("attr1", false);

    IDob dob4 = new Dob();
    dob4.attr("attr1", "hej");

    IDob dob5 = new Dob();
    dob5.attr("attr1", 1);

    ICondition condition = new IsNotCondition("attr1");

    assertTrue(condition.isTrue(dob1));
    assertFalse(condition.isTrue(dob2));
    assertTrue(condition.isTrue(dob3));
    assertTrue(condition.isTrue(dob4));
    assertTrue(condition.isTrue(dob5));
  }
}
