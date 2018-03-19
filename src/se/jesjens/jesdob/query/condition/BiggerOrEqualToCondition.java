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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package se.jesjens.jesdob.query.condition;

import se.jesjens.jesdob.IDob;

/**
 * A Condition for checking if a IDob contains a specific attribute, and if the
 * value of this attribute is numeric and bigger or equal to a specific value.
 *
 * @author jenss
 */
public class BiggerOrEqualToCondition extends NumericCondition implements ICondition {

  private final String attribute;
  private final double value;

  /**
   * Constructor.
   *
   * @param attribute The attribute we want to check.
   * @param value The numeric value we want to check if the attribute is bigger
   * than, or equal to.
   */
  public BiggerOrEqualToCondition(String attribute, double value) {
    this.attribute = attribute;
    this.value = value;
  }

  /**
   * This Condition will check if a specific numerical attribute is bigger than, or equal
   * to, a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, and the value is lesser than supplied value, the
   * condition will return false. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return true. If the dob has
   * the attribute, and the value is bigger the supplied value, the condition
   * will return true.
   *
   * @param dob The IDob to test.
   * @return True or false, depending on if the condition is true for the
   * IDob-object or not.
   */
  @Override
  public boolean isTrue(IDob dob) {
    String currentValue = dob.attr(attribute);

    boolean hasAttribute = dob.hasAttr(attribute);
    boolean isNumeric = isNumeric(currentValue);

    if (hasAttribute && isNumeric && (getNumeric(currentValue) >= value)) {
      return true;
    } else {
      return false;
    }
  }
}
