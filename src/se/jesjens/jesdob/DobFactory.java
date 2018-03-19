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

package se.jesjens.jesdob;

import se.jesjens.jesload.CouldNotLoadResourceException;
import se.jesjens.jesload.FileDataLoader;
import se.jesjens.jesload.IDataLoader;
import se.jesjens.jesload.ResourceDataLoader;

/**
 *
 * @author jenss
 */
public class DobFactory {

  private static final IDataLoader resourceReader = new ResourceDataLoader();
  private static final IDataLoader fileReader = new FileDataLoader();
  private static String encoding = "UTF-8";

  /**
   * Data formats that can be used for creating Dob-objects.
   */
  public enum DataFormat {

    DOB,
    XML,
    JSON
  }

  /**
   * Sets the encoding used when reading Dob-data from files and/or resources.
   * If no encoding is set UTF-8 will be used as default encoding.
   *
   * @param encoding
   */
  public void setEncoding(String encoding) {
    DobFactory.encoding = encoding;
  }

  /**
   * Returns a new IDobCollection, read from file from a specific path.
   * <p>
   * The dob data in the file can be stored in dob-format, xml-format or json-format.
   * The file must use the correct extention (.dob, .json or .xml), since the
   * DobFactory is using the extention to determine the data type.
   *
   * @param filePath The path to the file containing the dob data.
   * @return The new IDobCollection, created from the file data.
   */
  public static IDobCollection getDobsFromFile(String filePath)
          throws UnsupportedFormatException, CouldNotLoadResourceException {
    String dobData = fileReader.load(filePath, encoding);
    String extention = filePath.substring(filePath.lastIndexOf("."));
    DataFormat dataFormat = getDataFormat(extention);

    IDobCollection dobCollection = getDobs(dataFormat, dobData);

    return dobCollection;
  }

  /**
   * Returns a new IDobCollection, read from file from a resource path.
   * <p>
   * The dob data in the file can be stored in dob-format, xml-format or json-format.
   * The file must use the correct extention (.dob, .json or .xml), since the
   * DobFactory is using the extention to determine the data type.
   *
   * @param resourcePath The path to the project resource.
   * @return The new IDobCollection, created from the file data.
   */
  public static IDobCollection getDobsFromResource(String resourcePath)
          throws UnsupportedFormatException, CouldNotLoadResourceException {
    String dobData = resourceReader.load(resourcePath, encoding);
    String extention = resourcePath.substring(resourcePath.lastIndexOf("."));
    DataFormat dataFormat = getDataFormat(extention);

    IDobCollection dobCollection = getDobs(dataFormat, dobData);

    return dobCollection;
  }

  /**
   * Returns a new IDobCollection from Dob-data-string.
   * <p>
   * The Dob data string can be formatted in dob-format, json-format or xml-format.
   *
   * @param dataFormat The format used in the data-string.
   * @param data The data-string containing the Dob-data.
   * @return A new IDobCollection, created from the supplied data-string.
   */
  public static IDobCollection getDobs(DataFormat dataFormat, String data) {
    IDobCollection dobCollection = new DobCollection();

    String splitChar = "]";
    if (dataFormat != DataFormat.DOB) {
      splitChar = "\\r?\\n";
    }

    String[] rows = data.split(splitChar); // split on new line

    if (dataFormat == DataFormat.XML) {
      rows[0] = "";
      rows[rows.length - 1] = "";
    }

    for (String row : rows) {
      if (row.length() > 0) {
        IDob dob = getDob(dataFormat, row);

        if (dob != null) {
          dobCollection.add(dob);
        }
      }
    }

    return dobCollection;
  }

  /**
   * Returns one specific IDob-object from Dob-data-string.
   * <p>
   * The Dob data string can be formatted in dob-format, json-format or xml-format.
   *
   * @param dataFormat The format used in the data-string.
   * @param data The data-string containing the Dob-data.
   * @return A new IDob, created from the supplied data-string.
   */
  public static IDob getDob(DataFormat dataFormat, String data) {
    String[] tokens = null;

    if (dataFormat == DataFormat.DOB) {
      tokens = tokenizeDob(data);
    } else if (dataFormat == DataFormat.XML) {
      tokens = tokenizeXml(data);
    } else if (dataFormat == DataFormat.JSON) {
      tokens = tokenizeJson(data);
    }

    if (tokens == null) {
      return null;
    } else {
      IDob dob = buildDob(tokens);

      return dob;
    }
  }

  private static DataFormat getDataFormat(String extention) throws UnsupportedFormatException {
    if ((".dob").equals(extention)) {
      return DataFormat.DOB;
    } else if (".json".equals(extention)) {
      return DataFormat.JSON;
    } else if (".xml".equals(extention)) {
      return DataFormat.XML;
    } else {
      throw new UnsupportedFormatException(extention);
    }
  }

  private static IDob buildDob(String[] tokens) {
    IDob dob = new Dob();

    int numberOfTokens = tokens.length;
    for (int i = 0; i < numberOfTokens; i += 2) {
      String token1 = tokens[i].trim();
      String token2 = (tokens.length >= i + 2) ? tokens[i + 1].trim() : "";

      dob.attr(token1, token2);
    }

    return dob;
  }

  private static String[] tokenizeJson(String string) {
    string = string.substring(1, string.length() - 1); // Remove { and }
    string = string.replace("\"", ""); // Remove all " and ,
    String[] tokens = string.split(": |, |:|,");

    return tokens;
  }

  private static String[] tokenizeXml(String string) {
    string = string.trim().substring(5, string.length() - 2).replace("/>", ""); // Remove <dob and />
    String[] tokens = string.split("=\"|\" ");

    return tokens;
  }

  private static String[] tokenizeDob(String string) {
    string = string.replaceAll("/\\*(?:.|[\\n\\r])*?\\*/", ""); // Remove comments
    string = string.replaceAll("(?m)^[ \t]*\r?\n", ""); // Remove all empty lines

    while (string.contains("  ")) {
      string = string.replace("  ", " ");
    }

    if (string.length() == 0) {
      return null;
    } else {
      string = string.replace("[", "").replace("]", ""); // Remove [ and ]
      string = string.replace("\\'", "$(escaped-apostrophe)");
      String[] tokens = string.split("='|' |'");

      int numberOfTokens = tokens.length;
      for (int i = 0; i < numberOfTokens; i += 1) {
        tokens[i] = tokens[i]
                .replace("$(escaped-apostrophe)", "'");
      }

      return tokens;
    }
  }
}