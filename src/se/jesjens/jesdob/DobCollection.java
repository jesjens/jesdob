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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.jesjens.jesdob.query.IDobQuery;

/**
 * A DobCollection is a collection containing one or more IDobs (dynamic objects).
 * It is possible to add objects to a collection, and to remove objects from
 * a collection.
 * <p>
 * It is possible to get IDobs from the collection, by index, or by searching a specific attribute and value.
 * You can also filter the DobCollection by using a IDobQuery-object.
 * This will make the IDob-object act more like a database.
 * <p>
 * DobCollections can be exported to text as json-format, xml-format or dob-format. They can
 * then be saved to files and read as dynamic objects when they are needed.
 *
 * @author jenss
 */
public class DobCollection implements IDobCollection, Iterable<IDob> {

  private final List<IDob> dobList = new ArrayList<IDob>();
  private int numberOfDobsInList = 0;

  /**
   * Parameterless constructor.
   */
  public DobCollection() {

  }

  /**
   * Constructor. Creates a new collection filled with a list of attached dobs.
   *
   * @param dobs The dobs we want to fill our collection with.
   */
  public DobCollection(IDob... dobs) {
    add(dobs);
  }

  /**
   * Constructor. Creates a new collection filled with dobs created from
   * string list with dob data.
   *
   * @param dobsData This dob data will be converted to dob, and added to the new collection.
   */
  public DobCollection(String... dobsData) {
    add(dobsData);
  }

  /**
   * Adds one or more dobs to the dob collection.
   *
   * @param dobs The dobs that should be added to the collection.
   * @return The DobCollection object, to enable chaining.
   */
  @Override
  public IDobCollection add(IDob... dobs) {
    for (IDob dob : dobs) {
      dobList.add(dob);
      numberOfDobsInList += 1;
    }

    return this;
  }

  /**
   * Adds the content of a dob collection to a dob collection.
   *
   * @param dobCollection The DobCollection we want to add the content from.
   * @return The DobCollection object, to enable chaining.
   */
  @Override
  public IDobCollection add(IDobCollection dobCollection) {
    for (IDob dob : dobCollection) {
      dobList.add(dob);
      numberOfDobsInList += 1;
    }

    return this;
  }

  /**
   * Adds one or more dobs from dob data-strings. The dob data-strings will
   * be converted to dobs and added to the collection.
   *
   * @param dobsData The dob data we want to convert to dobs and add to the collection.
   * @return The DobCollection object, to enable chaining.
   */
  public IDobCollection add(String... dobsData) {
    for (String dob : dobsData) {
      dobList.add(Dob.create(dob));
      numberOfDobsInList += 1;
    }

    return this;
  }

  /**
   * Removes all dobs where a specific attribute has a specific value. Returns
   * true if any objects were removed, and false if no objects were removed.
   *
   * @param attr The attribute we want to check for.
   * @param value The value we want to check for.
   * @return If any object were removed from the collection.
   */
  @Override
  public boolean remove(String attr, String value) {
    boolean didRemove = false;

    for (int i = numberOfDobsInList - 1; i >= 0; i -= 1) {
      IDob dob = dobList.get(i);

      if (value.equals(dob.attr(attr))) {
        dobList.remove(dob);

        numberOfDobsInList -= 1;
        didRemove = true;
      }
    }

    return didRemove;
  }

  /**
   * Returns a Dob from a specific index in the collection.
   *
   * @param index A specific Dob index of the collection.
   * @return The Dob from the specific index in the collection.
   */
  @Override
  public IDob get(int index) {
    return dobList.get(index);
  }

  /**
   * Returns the number of dobs in the collection.
   *
   * @return The number of dobs in the collection.
   */
  @Override
  public int size() {
    return numberOfDobsInList;
  }

  /**
   * Returns true or false, depending on if the collection is empty or not.
   *
   * @return True or false, depending on if the collection is empty or not.
   */
  @Override
  public boolean isEmpty() {
    return dobList.isEmpty();
  }

  /**
   * Returns a new list with dobs from the collection.
   *
   * @return A new list with dobs from the collection.
   */
  @Override
  public List<IDob> toList() {
    List<IDob> dobListCopy = new ArrayList<IDob>();

    for (IDob dob : dobList) {
      dobListCopy.add(dob);
    }

    return dobListCopy;
  }

  /**
   * Returns the content of the collection, in json-format.
   *
   * @return The content of the collection, in json-format.
   */
  @Override
  public String toJson() {
    StringBuilder sb = new StringBuilder();

    for (IDob dob : dobList) {
      sb.append(dob.toJson()).append("\n");
    }

    return sb.toString();
  }

  /**
   * Returns the content of the collection, in dob-format.
   *
   * @return The content of the collection, in dob-format.
   */
  @Override
  public String toDob() {
    StringBuilder sb = new StringBuilder();

    for (IDob dob : dobList) {
      sb.append(dob.toDob()).append("\n");
    }

    return sb.toString();
  }

  /**
   * Returns the content of the collection, in xml-format.
   *
   * @return The content of the collection, in xml-format.
   */
  @Override
  public String toXml() {
    StringBuilder sb = new StringBuilder();

    sb.append("<dobcollection>\n");

    for (IDob dob : dobList) {
      sb.append("  ").append(dob.toXml()).append("\n");
    }

    sb.append("</dobcollection>");

    return sb.toString();
  }

  /**
   * Returns the first dob in the collection where a specific attribute has
   * a specific value. If no such object exists in the collection, this method
   * will return null.
   *
   * @param attribute The specific attribute.
   * @param value The specific value.
   * @return A dob object where the specified attribute matches the specified value.
   */
  @Override
  public IDob get(String attribute, String value) {
    for (IDob dob : this) {
      if (value.equals(dob.attr(attribute))) {
        return dob;
      }
    }

    return null;
  }

  /**
   * Returns a new DobCollection containing all Dobs which have a particular
   * value for a particular attribute. If no such Dobs exists, an empty collection
   * will be returned.
   *
   * @param attribute The specific attribute.
   * @param value The specific value.
   * @return A new Dob collection containing all Dobs matching the search criterias.
   */
  @Override
  public IDobCollection getAll(String attribute, String value) {
    IDobCollection dobCollection = new DobCollection();

    for (IDob dob : this) {
      if (value.equals(dob.attr(attribute))) {
        dobCollection.add(dob);
      }
    }

    return dobCollection;
  }

  /**
   * Applies a filter on this DobCollection and returns the filtered version.
   * The filtering is done is by the applied IDobQuery object.
   *
   * @param query The query we want to use for filtering the collection.
   * @return The new collection, produced by filtering the original collection.
   */
  @Override
  public IDobCollection filter(IDobQuery query) {
    return query.filter(this);
  }

  /**
   * Removes all data from the DobCollection
   *
   * @return The DobCollection, to enable chaining.
   */
  public IDobCollection clear() {
    dobList.clear();
    numberOfDobsInList = 0;

    return this;
  }

  /**
   * Returns an iterator used for iterating Dobs in the DobCollection.
   *
   * @return a new iterator used for iterating Dobs in the DobCollection.
   */
  @Override
  public Iterator<IDob> iterator() {
    Iterator<IDob> it = new Iterator<IDob>() {

      private int currentIndex = 0;

      @Override
      public boolean hasNext() {
        return currentIndex < numberOfDobsInList
                && dobList.get(currentIndex) != null;
      }

      @Override
      public IDob next() {
        currentIndex += 1;

        return dobList.get(currentIndex - 1);
      }

      @Override
      public void remove() {
        dobList.remove(currentIndex);

        numberOfDobsInList -= 1;
      }
    };

    return it;
  }
}
