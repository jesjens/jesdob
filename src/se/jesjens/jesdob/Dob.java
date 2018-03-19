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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A Dob is a dynamic object, containing various kinds of data.
 * <p>
 * It is possible to add attributes to a dob, and to assign values to these attributes.
 * It is possible to read back the values for a specific attrbitutes.
 * <p>
 * Dobs can be exported to text as json-format, xml-format or dob-format. They can
 * then be saved to files and read as dynamic objects when they are needed.
 *
 * @author jenss
 */
public class Dob implements IDob {

  private final Map<String, String> map = new HashMap<String, String>();
  public static String newline = System.getProperty("line.separator");

  /**
   * Parameterless constructor.
   */
  public Dob() {
  }

  /**
   * Returns a dob from a dob data string.
   *
   * @param dobData The dob data.
   * @return The new Dob object.
   */
  public static IDob create(String dobData) {
    return DobFactory.getDob(DobFactory.DataFormat.DOB, dobData);
  }

  /**
   * Returns the value of a specific attribute.
   *
   * @param attribute The specific attribute.
   * @return The value of the attribute.
   */
  @Override
  public String attr(final String attribute) {
    return map.get(attribute);
  }

  /**
   * Checks if a specific boolean attribute is true of false.
   *
   * @param attribute The specific attribute.
   * @return If the attribute is true or false.
   */
  @Override
  public boolean is(final String attribute) {
    return "true".equals(map.get(attribute)) ? true : false;
  }

  /**
   * Sets a specific attribute to a specific String value.
   *
   * @param attribute The specific attribute.
   * @param value The specific String value.
   * @return The dob object, to enable method chaining.
   */
  @Override
  public IDob attr(final String attribute, final String value) {
    map.put(attribute, value);

    return this;
  }

  /**
   * Sets a specific attribute to a specific boolean value.
   *
   * @param attribute The specific attribute.
   * @param value The specific boolean value.
   * @return The dob object, to enable method chaining.
   */
  @Override
  public IDob attr(String attribute, boolean isTrue) {
    String stringValue = isTrue ? "true" : "false";
    map.put(attribute, stringValue);

    return this;
  }

  /**
   * Sets a specific attribute to a specific int value.
   *
   * @param attribute The specific attribute.
   * @param value The specific int value.
   * @return The dob object, to enable method chaining.
   */
  @Override
  public IDob attr(String attribute, int value) {
    removeAttr(attribute);

    map.put(attribute, String.valueOf(value));

    return this;
  }

  /**
   * Sets a specific attribute to a specific float value.
   *
   * @param attribute The specific attribute.
   * @param value The specific float value.
   * @return The dob object, to enable method chaining.
   */
  @Override
  public IDob attr(String attribute, float value) {
    removeAttr(attribute);

    map.put(attribute, String.valueOf(value));

    return this;
  }

  /**
   * Checks if the Dob object contains a specific attribute or not.
   *
   * @param attribute The specific attribute
   * @return True or false, depending on if the attribute exists or not.
   */
  @Override
  public boolean hasAttr(final String attribute) {
    return map.containsKey(attribute);
  }

  /**
   * Removes a specific attribute from the Dob.
   *
   * @param attribute The specific attribute.
   * @return The dob object, to enable method chaining.
   */
  @Override
  public IDob removeAttr(final String attribute) {
    map.remove(attribute);

    return this;
  }

  /**
   * Exports the Dob object data in json-format.
   *
   * @return The Dob object data in json-format.
   */
  @Override
  public String toJson() {
    return format("{", ": ", "\"", "\"", ", ", "}");
  }

  /**
   * Exports the Dob object data in dob-format.
   *
   * @return The Dob object data in dob-format.
   */
  @Override
  public String toDob() {
    return format("[", "=", "", "'", " ", "]");
  }

  /**
   * Exports the Dob object data in xml-format.
   *
   * @return The Dob object data in xml-format.
   */
  @Override
  public String toXml() {
    return format("<dob ", "=", "", "\"", " ", " />");
  }

  /**
   * Exports the Dob object data to a custom data format.
   *
   * @param postStart The char sequence used for starting data object. E.g. json: '{', xml: '<dob'
   * @param assignChar The char sequence used to assigning a value. E.g. json: ':', xml: '='
   * @param keySurroundChar The char sequence used to surround a key. E.g. json: '"', xml: ''
   * @param valueSurroundChar The char sequence used to surround a value. E.g. json: '"', xml: '"'
   * @param postSeparator The char used to separate key-value-pairs. E.g. json: ',', xml: ' '
   * @param postEnd The char sequenced used for ending a data object: E.g. json: '}', xml: ' />'
   * @return The data from the Dob object, formated in a custom format.
   */
  private String format(String postStart, String assignChar,
          String keySurroundChar, String valueSurroundChar,
          String postSeparator, String postEnd) {
    StringBuilder sb = new StringBuilder();

    sb.append(postStart);

    Iterator it = map.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, String> pairs = (Map.Entry) it.next();

      sb.append(keySurroundChar).append(pairs.getKey()).append(keySurroundChar);
      sb.append(assignChar);
      sb.append(valueSurroundChar).append(pairs.getValue()).append(valueSurroundChar);

      if (it.hasNext()) {
        sb.append(postSeparator);
      }
    }

    sb.append(postEnd);

    return sb.toString();
  }
}