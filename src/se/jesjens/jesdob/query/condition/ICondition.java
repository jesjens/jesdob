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
 * An ICondition encapsulates some kind of condition, or rule, that is either
 * true or false for a IDob-object. A ICondition can be used for testing IDobs.
 *
 * @author jenss
 */
public interface ICondition {

  /**
   * Returns true or false, depending on if the supplied IDob-object is
   * fullfilling the condition/rule or not.
   *
   * @param dob The IDob to test.
   * @return True or false, depending on if the condition is true for the
   * IDob-object or not.
   */
  boolean isTrue(IDob dob);
}
