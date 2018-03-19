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
 * Condition for checking if an IDob contains a specific attribute or not.
 *
 * @author jenss
 */
public class HasAttributeCondition implements ICondition {
  private final String attribute;

  /**
   * Constructor.
   *
   * @param attribute The attribute we want to check for.
   */
  public HasAttributeCondition(String attribute) {
    this.attribute = attribute;
  }

  /**
   * This Condition will check if a Dob has a specific attribute or not.
   *
   * If the dob has the attribute, the condition will return true.
   * If the dob has not the attribute, the condition will return false.
   * @param dob The IDob to test.
   * @return True or false, depending on if the condition is true for the
   * IDob-object or not.
   */
  @Override
  public boolean isTrue(IDob dob) {
    return dob.hasAttr(attribute);
  }
}
