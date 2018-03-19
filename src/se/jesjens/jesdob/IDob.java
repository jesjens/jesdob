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
package se.jesjens.jesdob;

/**
 * A IDob is a dynamic object, containing various kinds of data.
 * <p>
 * It is possible to add attributes to a dob, and to assign values to these attributes.
 * It is possible to read back the values for a specific attrbitutes.
 * <p>
 * IDobs can be exported to text as json-format, xml-format or dob-format. They can
 * then be saved to files and read as dynamic objects when they are needed.
 *
 * @author jenss
 */
public interface IDob {

  /**
   * Returns the value of a specific attribute.
   *
   * @param attribute The specific attribute.
   * @return The value of the attribute.
   */
  public String attr(final String attribute);

  /**
   * Sets a specific attribute to a specific String value.
   *
   * @param attribute The specific attribute.
   * @param value The specific String value.
   * @return The dob object, to enable method chaining.
   */
  public IDob attr(final String attribute, final String value);

  /**
   * Sets a specific attribute to a specific boolean value.
   *
   * @param attribute The specific attribute.
   * @param value The specific boolean value.
   * @return The dob object, to enable method chaining.
   */
  public IDob attr(final String attribute, final boolean value);

  /**
   * Sets a specific attribute to a specific int value.
   *
   * @param attribute The specific attribute.
   * @param value The specific int value.
   * @return The dob object, to enable method chaining.
   */
  public IDob attr(final String attribute, final int value);

  /**
   * Sets a specific attribute to a specific float value.
   *
   * @param attribute The specific attribute.
   * @param value The specific float value.
   * @return The dob object, to enable method chaining.
   */
  public IDob attr(final String attribute, final float value);

  /**
   * Checks if a specific boolean attribute is true of false.
   *
   * @param attribute The specific attribute.
   * @return If the attribute is true or false.
   */
  public boolean is(final String attribute);

  /**
   * Checks if the Dob object contains a specific attribute or not.
   *
   * @param attribute The specific attribute
   * @return True or false, depending on if the attribute exists or not.
   */
  public boolean hasAttr(final String attribute);

  /**
   * Removes a specific attribute from the Dob.
   *
   * @param attribute The specific attribute.
   * @return The dob object, to enable method chaining.
   */
  public IDob removeAttr(final String attribute);

  /**
   * Exports the Dob object data in json-format.
   *
   * @return The Dob object data in json-format.
   */
  public String toJson();

  /**
   * Exports the Dob object data in xml-format.
   *
   * @return The Dob object data in xml-format.
   */
  public String toXml();

  /**
   * Exports the Dob object data in dob-format.
   *
   * @return The Dob object data in dob-format.
   */
  public String toDob();
}
