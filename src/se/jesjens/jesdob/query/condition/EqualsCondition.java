/**
 * Copyright (C) 2012 - 2013 Jens Stääf
 *
 * JeSDOb (Jens Simple Dynamic Objects)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package se.jesjens.jesdob.query.condition;

import se.jesjens.jesdob.IDob;

/**
 * A Condition for checking if an IDob contains a specific attribute, and if the
 * value equals a specific value.
 *
 * @author jenss
 */
public class EqualsCondition implements ICondition {
  private final String attribute;
  private final String value;

  /**
   * Constructor.
   *
   * @param attribute The attribute we want to check.
   * @param value The numeric value we want to check if the attribute equals.
   */
  public EqualsCondition(String attribute, String value) {
    this.attribute = attribute;
    this.value = value;
  }

  /**
   * This condition will check if the dob has a specific attribute, and if this
   * attribute has got a specific value or not.
   *
   * If the dob has not the attribute, the condition will return false.
   * If the dob has the attribute, but the value of the attribute is not the supplied value, the condition will return false.
   * If the dob has the attribute, and the value equals the supplied value, the condition will return true.
   *
   * @param dob The IDob to test.
   * @return True or false, depending on if the condition is true for the
   * IDob-object or not.
   */
  @Override
  public boolean isTrue(IDob dob) {
    return value.equals(dob.attr(attribute));
  }
}