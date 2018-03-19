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
 * A Condition for checking if a IDob contains a specific attribute,
 * and if this attribute contains a specific value or not.
 *
 * @author jenss
 */
public class AttrContainsCondition implements ICondition {
  private final String attribute;
  private final String value;

  /**
   * Constructor.
   *
   * @param attribute The attribute we want to check the value for.
   * @param value The value we want to check if the attribute contains.
   */
  public AttrContainsCondition(String attribute, String value) {
    this.attribute = attribute;
    this.value = value;
  }

  /**
   * Returns true or false, depending on if the supplied IDob-object
   * contains the specified attribute, and if this attribute contains the
   * supplied value.
   *
   * If the dob has not the attribute, the condition will return false.
   * If the dob has the attribute, but the value of the attribute is not the supplied value, the rule will return false.
   * If the dob has the attribute, and the value equals the supplied value, the rule will return true.
   * If the dob has the attribute, and the value contains the supplied value, the rule will return true.
   *
   * The dob-attribute-values doesn't have to equal the supplied value exactly, but can
   * contain the value.
   *
   * Example:
   * value = "def"
   * "abcdefghijklm" - true, contains "def".
   * "def" - true, contains "def".
   * "abcde" - false, does not contain "def".
   *
   * @param dob The IDob to test.
   * @return True or false, depending on if the condition is true for the
   * IDob-object or not.
   */
  @Override
  public boolean isTrue(IDob dob) {
    return (dob.hasAttr(attribute)
            && dob.attr(attribute).contains(value));
  }

}
