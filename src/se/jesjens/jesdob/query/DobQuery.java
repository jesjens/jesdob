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

import java.util.ArrayList;
import java.util.List;

import se.jesjens.jesdob.DobCollection;
import se.jesjens.jesdob.IDob;
import se.jesjens.jesdob.IDobCollection;
import se.jesjens.jesdob.query.condition.*;

/**
 * A dob query contains one or more conditions, or requirements. These
 * conditions can be used for testing one or several dobs - which dobs fullfils
 * the conditions, and which dobs do not?
 *
 * It is possible to use dob queries for filtering dob collections, removing the
 * dobs that doesn't fulfill the specified conditions/requirements.
 *
 * @author jenss
 */
public class DobQuery implements IDobQuery {

  private final List<ICondition> conditions = new ArrayList<ICondition>();

  /**
   * Parameterless constructor.
   */
  public DobQuery() {
  }

  /**
   * Adds a is-condition to the query. This condition will check if a Dob
   * contains a specific boolean attribute, and if this attribute is true.
   *
   * If the dob does not contain the attribute, the condition will return false.
   * If the dob contains the attribute, but the attribute is not true, the
   * condition will return false. If the dob contains the attribute, and the
   * attribute is true, the condition will return true.
   *
   * @param attribute The boolean attribute we want to check for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery is(String attribute) {
    conditions.add(new IsCondition(attribute));

    return this;
  }

  /**
   * Adds a is-not-condition to the query. This condition will check if a Dob
   * contains a specific boolean attribute, and if this attribute is false.
   *
   * If the dob does not contain the attribute, the condition will return true.
   * If the dob contains the attribute, but the attribute is not true, the
   * condition will return true. If the dob contains the attribute, and the
   * attribute is true, the condition will return false.
   *
   * @param attribute The boolean attribute we want to check for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery isNot(String attribute) {
    conditions.add(new IsNotCondition(attribute));

    return this;
  }

  /**
   * Adds a has-attribute-condition to the query. This will check if a Dob has a
   * specific attribute or not.
   *
   * If the dob has the attribute, the condition will return true. If the dob
   * has not the attribute, the condition will return false.
   *
   * @param attribute The attribute we want to check for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery hasAttr(String attribute) {
    conditions.add(new HasAttributeCondition(attribute));

    return this;
  }

  /**
   * Adds a has-not-attribute-condition to the query. This will check if a Dob
   * lacks a specific attribute, or not.
   *
   * If the dob has the attribute, the condition will return false. If the dob
   * has not the attribute, the condition will return true.
   *
   * @param attribute The attribute we want to check for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery hasNotAttr(String attribute) {
    conditions.add(new HasNotAttributeCondition(attribute));

    return this;
  }

  /**
   * Adds a equals-condition to the query. This will check if the dob has a
   * specific attribute, and if this attribute has got a specific value or not.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return false. If the dob has the attribute, and
   * the value equals the supplied value, the condition will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery equals(String attribute, String value) {
    conditions.add(new EqualsCondition(attribute, value));

    return this;
  }

  /**
   * Adds a equals-condition to the query. This will check if the dob has a
   * specific attribute, and if this attribute has got a specific value or not.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return false. If the dob has the attribute, and
   * the value equals the supplied value, the condition will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery equals(String attribute, int value) {
    conditions.add(new EqualsCondition(attribute, String.valueOf(value)));

    return this;
  }

  /**
   * Adds a equals-condition to the query. This will check if the dob has a
   * specific attribute, and if this attribute has got a specific value or not.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return false. If the dob has the attribute, and
   * the value equals the supplied value, the condition will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery equals(String attribute, float value) {
    conditions.add(new EqualsCondition(attribute, String.valueOf(value)));

    return this;
  }

  /**
   * Adds a not-equals-condition to the query. This will check if the dob if dob
   * hasn't a specific value for a specific attribute.
   *
   * If the dob has not the attribute, the condition will return true. If the
   * dob has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return true. If the dob has the attribute, and
   * the value equals the supplied value, the condition will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to not have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery notEquals(String attribute, int value) {
    conditions.add(new NotEqualsCondition(attribute, String.valueOf(value)));

    return this;
  }

  /**
   * Adds a not-equals-condition to the query. This will check if the dob if dob
   * hasn't a specific value for a specific attribute.
   *
   * If the dob has not the attribute, the condition will return true. If the
   * dob has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return true. If the dob has the attribute, and
   * the value equals the supplied value, the condition will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to not have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery notEquals(String attribute, float value) {
    conditions.add(new NotEqualsCondition(attribute, String.valueOf(value)));

    return this;
  }

  /**
   * Adds a not-equals-condition to the query. This will check if the dob if dob
   * hasn't a specific value for a specific attribute.
   *
   * If the dob has not the attribute, the condition will return true. If the
   * dob has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return true. If the dob has the attribute, and
   * the value equals the supplied value, the condition will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to not have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery notEquals(String attribute, String value) {
    conditions.add(new NotEqualsCondition(attribute, value));

    return this;
  }

  /**
   * Adds a contains-condition to the query. This will check if the dob has a
   * specific attribute, and if this attribute contains a specific char
   * sequence.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return false. If the dob has the attribute, and
   * the value equals the supplied value, the condition will return true. If the
   * dob has the attribute, and the value contains the supplied value, the
   * condition will return true.
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
  @Override
  public IDobQuery contains(String attribute, String value) {
    conditions.add(new AttrContainsCondition(attribute, value));

    return this;
  }

  /**
   * Adds a contains-condition to the query. This will check if the dob has a
   * specific attribute, and if this attribute doesn't contains a specific char
   * sequence.
   *
   * If the dob has not the attribute, the condition will return true. If the
   * dob has the attribute, but the value of the attribute is not the supplied
   * value, the condition will return true. If the dob has the attribute, and
   * the value equals the supplied value, the condition will return false. If
   * the dob has the attribute, and the value contains the supplied value, the
   * condition will return false.
   *
   * Example: value = "def" "abcdefghijklm" - false, contains "def". "def" -
   * false, contains "def". "abcde" - true, does not contain "def".
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we expect the attribute to not have.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery containsNot(String attribute, String value) {
    conditions.add(new AttrContainsNotCondition(attribute, value));

    return this;
  }

  /**
   * Adds a bigger-than-condition to the query. This will check if a specific
   * numerical attribute is bigger than a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, and the value is lesser than supplied value, the
   * condition will return false. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return false. If the dob has
   * the attribute, and the value is bigger the supplied value, the condition
   * will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery bigger(String attribute, int value) {
    conditions.add(new BiggerThanCondition(attribute, value));

    return this;
  }

  /**
   * Adds a lesser-than-condition to the query. This will check if a specific
   * numerical attribute is lesser than a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, and the value is lesser than supplied value, the
   * condition will return true. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return false. If the dob has
   * the attribute, and the value is bigger the supplied value, the condition
   * will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery lesser(String attribute, int value) {
    conditions.add(new LesserThanCondition(attribute, value));

    return this;
  }

  /**
   * Adds a bigger-or-equal-to-condition to the query. This will check if a
   * specific numerical attribute is bigger than, or equal to, specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, and the value is lesser than supplied value, the
   * condition will return false. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return true. If the dob has
   * the attribute, and the value is bigger the supplied value, the condition
   * will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery biggerOrEqual(String attribute, int value) {
    conditions.add(new BiggerOrEqualToCondition(attribute, value));

    return this;
  }

  /**
   * Adds a lesser-or-equal-to-condition to the query. This will check if a
   * specific numerical attribute is lesser than, or equal to, a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, and the value is lesser than supplied value, the
   * condition will return true. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return true. If the dob has
   * the attribute, and the value is bigger the supplied value, the condition
   * will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery lesserOrEqual(String attribute, int value) {
    conditions.add(new LesserOrEqualToCondition(attribute, value));

    return this;
  }

  /**
   * Adds a bigger-than-condition to the query. This will check if a specific
   * numerical attribute is bigger than a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, and the value is lesser than supplied value, the
   * condition will return false. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return false. If the dob has
   * the attribute, and the value is bigger the supplied value, the condition
   * will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery bigger(String attribute, double value) {
    conditions.add(new BiggerThanCondition(attribute, value));

    return this;
  }

  /**
   * Adds a lesser-than-condition to the query. This will check if a specific
   * numerical attribute is lesser than a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, and the value is lesser than supplied value, the
   * condition will return true. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return false. If the dob has
   * the attribute, and the value is bigger the supplied value, the condition
   * will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery lesser(String attribute, double value) {
    conditions.add(new LesserThanCondition(attribute, value));

    return this;
  }

  /**
   * Adds a bigger-or-equal-to-condition to the query. This will check if a
   * specific numerical attribute is bigger than, or equal to, specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, and the value is lesser than supplied value, the
   * condition will return false. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return true. If the dob has
   * the attribute, and the value is bigger the supplied value, the condition
   * will return true.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery biggerOrEqual(String attribute, double value) {
    conditions.add(new BiggerOrEqualToCondition(attribute, value));

    return this;
  }

  /**
   * Adds a lesser-or-equal-to-condition to the query. This will check if a
   * specific numerical attribute is lesser than, or equal to, a specific value.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, and the value is lesser than supplied value, the
   * condition will return true. If the dob has the attribute, and the value
   * equals the supplied value, the condition will return true. If the dob has
   * the attribute, and the value is bigger the supplied value, the condition
   * will return false.
   *
   * @param attribute The attribute we want to examine.
   * @param value The value we want to compare with.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery lesserOrEqual(String attribute, double value) {
    conditions.add(new LesserOrEqualToCondition(attribute, value));

    return this;
  }

  /**
   * Adds a is-attribute-numeric-condition to the query. This will check if a
   * specific attribute is numeric or not.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, but the attribute is not numeric, the condition will
   * return false. If the dob has the attribute and the attribute is numeric,
   * the condition will return true.
   *
   * @param attribute The attribute we want to check type for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery isNumeric(String attribute) {
    conditions.add(new IsNumericCondition(attribute));

    return this;
  }

  /**
   * Adds a is-not-attribute-numeric-condition to the query. This will check if
   * a specific attribute is not numeric or if it is.
   *
   * If the dob has not the attribute, the condition will return false. If the
   * dob has the attribute, but the attribute is not numeric, the condition will
   * return true. If the dob has the attribute and the attribute is numeric, the
   * condition will return false.
   *
   * @param attribute The attribute we want to check type for.
   * @return Self; the IDobQuery-object, to enable chaining.
   */
  @Override
  public IDobQuery isNotNumeric(String attribute) {
    conditions.add(new IsNotNumericCondition(attribute));

    return this;
  }

  /**
   * Takes a IDobCollection as an argument, and returns a new IDobCollection
   * with all IDobs that fullfilled the requirements specified in the query.
   *
   * @param dobCollection The collection we want to filter, with the
   * requirements/conditions specified in the query.
   * @return The new filtered IDobCollection.
   */
  @Override
  public IDobCollection filter(IDobCollection dobCollection) {
    IDobCollection newDobCollection = new DobCollection();

    for (IDob dob : dobCollection) {
      if (isTrue(dob)) {
        newDobCollection.add(dob);
      }
    }

    return newDobCollection;
  }

  /**
   * Takes a specific IDob as an argument, and returns true if the IDob
   * fullfills all requirements specified in the query.
   *
   * @param dob The Dob we want to test.
   * @return True or false, depening of if the dob fullfills all requirements in
   * the query, or not.
   */
  @Override
  public boolean isTrue(IDob dob) {
    boolean isTrue = false;

    for (ICondition condition : conditions) {
      if (condition.isTrue(dob)) {
        isTrue = true;
      } else {
        isTrue = false;

        break;
      }
    }

    return isTrue;
  }
}
