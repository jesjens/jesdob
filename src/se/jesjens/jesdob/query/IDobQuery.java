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
package se.jesjens.jesdob.query;

import se.jesjens.jesdob.IDob;
import se.jesjens.jesdob.IDobCollection;

/**
 *
 * @author jenss
 */
public interface IDobQuery {

  /**
   * Adds a is-condition to the query. This condition will check if a Dob contains a
   * specific boolean attribute, and if this attribute is true.
   *
   * If the dob does not contain the attribute, the condition will return false. If
   * the dob contains the attribute, but the attribute is not true, the condition
   * will return false. If the dob contains the attribute, and the attribute is
   * true, the condition will return true.
   *
   * @param attribute The boolean attribute we want to check for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery is(String attribute);

  /**
   * Adds a is-attribute-numeric-condition to the query. This will check if a
   * specific attribute is numeric or not.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, but the attribute is not numeric, the condition will return
   * false. If the dob has the attribute and the attribute is numeric, the condition
   * will return true.
   *
   * @param attribute The attribute we want to check type for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery isNumeric(String attribute);

  /**
   * Adds a is-not-attribute-numeric-condition to the query. This will check if a
   * specific attribute is not numeric or if it is.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, but the attribute is not numeric, the condition will return
   * true. If the dob has the attribute and the attribute is numeric, the condition
   * will return false.
   *
   * @param attribute The attribute we want to check type for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery isNotNumeric(String attribute);

  /**
   * Adds a is-not-condition to the query. This condition will check if a Dob contains a
   * specific boolean attribute, and if this attribute is false.
   *
   * If the dob does not contain the attribute, the condition will return true. If
   * the dob contains the attribute, but the attribute is not true, the condition
   * will return true. If the dob contains the attribute, and the attribute is
   * true, the condition will return false.
   *
   * @param attribute The boolean attribute we want to check for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery isNot(String attribute);

  /**
   * Adds a has-attribute-condition to the query. This will check if a Dob has a
   * specific attribute or not.
   *
   * If the dob has the attribute, the condition will return true. If the dob has not
   * the attribute, the condition will return false.
   *
   * @param attribute The attribute we want to check for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery hasAttr(String attribute);

  /**
   * Adds a has-not-attribute-condition to the query. This will check if a Dob lacks
   * a specific attribute, or not.
   *
   * If the dob has the attribute, the condition will return false. If the dob has
   * not the attribute, the condition will return true.
   *
   * @param attribute The attribute we want to check for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery hasNotAttr(String attribute);

  /**
   * Adds a equals-condition to the query. This will check if the dob has a specific
   * attribute, and if this attribute has got a specific value or not.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return false. If the dob has the attribute, and the
   * value equals the supplied value, the condition will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery equals(String attribute, String value);

  /**
   * Adds a equals-condition to the query. This will check if the dob has a specific
   * attribute, and if this attribute has got a specific value or not.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return false. If the dob has the attribute, and the
   * value equals the supplied value, the condition will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery equals(String attribute, int value);

  /**
   * Adds a equals-condition to the query. This will check if the dob has a specific
   * attribute, and if this attribute has got a specific value or not.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return false. If the dob has the attribute, and the
   * value equals the supplied value, the condition will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery equals(String attribute, float value);

  /**
   * Adds a not-equals-condition to the query. This will check if the dob if dob
   * hasn't a specific value for a specific attribute.
   *
   * If the dob has not the attribute, the condition will return true. If the dob has
   * the attribute, but the value of the attribute is not the supplied value,
   * the condition will return true. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to not have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery notEquals(String attribute, String value);

  /**
   * Adds a not-equals-condition to the query. This will check if the dob if dob
   * hasn't a specific value for a specific attribute.
   *
   * If the dob has not the attribute, the condition will return true. If the dob has
   * the attribute, but the value of the attribute is not the supplied value,
   * the condition will return true. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to not have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery notEquals(String attribute, int value);

  /**
   * Adds a not-equals-condition to the query. This will check if the dob if dob
   * hasn't a specific value for a specific attribute.
   *
   * If the dob has not the attribute, the condition will return true. If the dob has
   * the attribute, but the value of the attribute is not the supplied value,
   * the condition will return true. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to not have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery notEquals(String attribute, float value);

  /**
   * Adds a contains-condition to the query. This will check if the dob has a
   * specific attribute, and if this attribute contains a specific char
   * sequence.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return false. If the dob has the attribute, and the
   * value equals the supplied value, the condition will return true. If the dob has
   * the attribute, and the value contains the supplied value, the condition will
   * return true.
   *
   * The dob-attribute-values doesn't have to equal the supplied value exactly,
   * but can contain the value.
   *
   * Example: value = "def" "abcdefghijklm" - true, contains "def". "def" -
   * true, contains "def". "abcde" - false, does not contain "def".
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery contains(String attribute, String value);

  /**
   * Adds a contains-condition to the query. This will check if the dob has a
   * specific attribute, and if this attribute doesn't contains a specific char
   * sequence.
   *
   * If the dob has not the attribute, the condition will return true. If the dob has
   * the attribute, but the value of the attribute is not the supplied value,
   * the condition will return true. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return false. If the dob has the
   * attribute, and the value contains the supplied value, the condition will return
   * false.
   *
   * Example: value = "def" "abcdefghijklm" - false, contains "def". "def" -
   * false, contains "def". "abcde" - true, does not contain "def".
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to not have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery containsNot(String attribute, String value);

  /**
   * Adds a bigger-than-condition to the query. This will check if a specific
   * numerical attribute is bigger than a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, and the value is lesser than supplied value, the condition
   * will return false. If the dob has the attribute, and the value equals the
   * supplied value, the condition will return false. If the dob has the attribute,
   * and the value is bigger the supplied value, the condition will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery bigger(String attribute, int value);

  /**
   * Adds a lesser-than-condition to the query. This will check if a specific
   * numerical attribute is lesser than a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, and the value is lesser than supplied value, the condition
   * will return true. If the dob has the attribute, and the value equals the
   * supplied value, the condition will return false. If the dob has the attribute,
   * and the value is bigger the supplied value, the condition will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery lesser(String attribute, int value);

  /**
   * Adds a bigger-or-equal-to-condition to the query. This will check if a specific
   * numerical attribute is bigger than, or equal to, specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, and the value is lesser than supplied value, the condition
   * will return false. If the dob has the attribute, and the value equals the
   * supplied value, the condition will return true. If the dob has the attribute,
   * and the value is bigger the supplied value, the condition will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery biggerOrEqual(String attribute, int value);

  /**
   * Adds a lesser-or-equal-to-condition to the query. This will check if a specific
   * numerical attribute is lesser than, or equal to, a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, and the value is lesser than supplied value, the condition
   * will return true. If the dob has the attribute, and the value equals the
   * supplied value, the condition will return true. If the dob has the attribute,
   * and the value is bigger the supplied value, the condition will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery lesserOrEqual(String attribute, int value);

  /**
   * Adds a bigger-than-condition to the query. This will check if a specific
   * numerical attribute is bigger than a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, and the value is lesser than supplied value, the condition
   * will return false. If the dob has the attribute, and the value equals the
   * supplied value, the condition will return false. If the dob has the attribute,
   * and the value is bigger the supplied value, the condition will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery bigger(String attribute, double value);

  /**
   * Adds a lesser-than-condition to the query. This will check if a specific
   * numerical attribute is lesser than a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, and the value is lesser than supplied value, the condition
   * will return true. If the dob has the attribute, and the value equals the
   * supplied value, the condition will return false. If the dob has the attribute,
   * and the value is bigger the supplied value, the condition will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery lesser(String attribute, double value);

  /**
   * Adds a bigger-or-equal-to-condition to the query. This will check if a specific
   * numerical attribute is bigger than, or equal to, specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, and the value is lesser than supplied value, the condition
   * will return false. If the dob has the attribute, and the value equals the
   * supplied value, the condition will return true. If the dob has the attribute,
   * and the value is bigger the supplied value, the condition will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery biggerOrEqual(String attribute, double value);

  /**
   * Adds a lesser-or-equal-to-condition to the query. This will check if a specific
   * numerical attribute is lesser than, or equal to, a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the dob
   * has the attribute, and the value is lesser than supplied value, the condition
   * will return true. If the dob has the attribute, and the value equals the
   * supplied value, the condition will return true. If the dob has the attribute,
   * and the value is bigger the supplied value, the condition will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  public IDobQuery lesserOrEqual(String attribute, double value);

  /**
   * Takes a IDobCollection as an argument, and returns a new IDobCollection
   * with all IDobs that fullfilled the requirements specified in the query.
   *
   * @param dobCollection The collection we want to filter, with the
   * requirements/conditions specified in the query.
   * @return The new filtered IDobCollection.
   */
  public IDobCollection filter(IDobCollection dobCollection);

  /**
   * Takes a specific IDob as an argument, and returns true if the IDob fullfills
   * all requirements specified in the query.
   *
   * @param dob The Dob we want to test.
   * @return True or false, depening of if the dob fullfills all requirements in the query, or not.
   */
  public boolean isTrue(IDob dob);
}
