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

/**
 * Numeric conditon is an abstract base class for numeric condition, containing
 * reusable function for processing numeric data.
 *
 * @author jenss
 */
public abstract class NumericCondition {

  /**
   * Checks if a specific string is possible to treat as numeric data or not.
   *
   * @param str The string we want to check if can be treated as numeric data.
   * @return If the string could be treated as numeric data or not.
   */
  protected static boolean isNumeric(String str) {
    if (str == null) {
      return false;
    }

    try {
      double d = getNumeric(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  /**
   * Returns the numeric value for specific string. You need to make sure that
   * the string can be converted to a number before calling this function.
   * If the string could not be converted to a number, and exception will be thrown.
   *
   * @param str The string we want to convert to numeric data.
   * @return The numeric data, created from the string.
   */
  protected static double getNumeric(String str) {
    return Double.parseDouble(str);
  }
}
