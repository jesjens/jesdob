/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jesjens.jesdob;

import data.ResourceReader;
import java.util.Iterator;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import se.jesjens.jesdob.query.DobQuery;

/**
 *
 * @author jenss
 */
public class DobCollectionTest {

  private final ResourceReader resourceReader = new ResourceReader();

  /**
   * Test of add method, of class DobCollection.
   */
  @Test
  public void testAdd_IDob() {
    IDobCollection dobCollection = new DobCollection();
    IDob dob = new Dob();

    dobCollection.add(dob);
    IDob theSameDob = dobCollection.get(0);

    assertEquals(dob, theSameDob);
  }

  /**
   * Test of add method, of class DobCollection.
   */
  @Test
  public void testAdd_DobCollection() {
    DobCollection dobCollection1 = new DobCollection();
    DobCollection dobCollection2 = new DobCollection();
    IDob dob1 = new Dob();
    IDob dob2 = new Dob();

    dobCollection1.add(dob1);
    dobCollection2.add(dob2);
    dobCollection2.add(dobCollection1);

    assertEquals(2, dobCollection2.size());
    assertEquals(dob1, dobCollection2.get(1));
    assertEquals(dob2, dobCollection2.get(0));
  }

  /**
   * Test of size method, of class DobCollection.
   */
  @Test
  public void testSize() {
    IDobCollection dobCollection = new DobCollection();
    assertEquals(0, dobCollection.size());

    dobCollection.add(new Dob());
    assertEquals(1, dobCollection.size());

    dobCollection.add(new Dob());
    assertEquals(2, dobCollection.size());
  }

  /**
   * Test of isEmpty method, of class DobCollection.
   */
  @Test
  public void testIsEmpty() {
    IDobCollection dobCollection = new DobCollection();
    assertTrue(dobCollection.isEmpty());

    dobCollection.add(new Dob());
    assertFalse(dobCollection.isEmpty());
  }

  /**
   * Test of toList method, of class DobCollection.
   */
  @Test
  public void testToList() {
    IDobCollection dobCollection = new DobCollection();

    IDob dob1 = new Dob();
    IDob dob2 = new Dob();

    dobCollection.add(dob1);
    dobCollection.add(dob2);

    List<IDob> dobsList = dobCollection.toList();

    assertEquals(2, dobsList.size());
    assertEquals(dob1, dobsList.get(0));
    assertEquals(dob2, dobsList.get(1));
  }

  /**
   * Test of toJson method, of class DobCollection.
   */
  @Test
  public void testToJson() {
    IDobCollection dobCollection = new DobCollection();
    IDob dob1 = new Dob();
    IDob dob2 = new Dob();
    IDob dob3 = new Dob();

    dob1.attr("id", "1");
    dob2.attr("id", "2");
    dob3.attr("id", "3");

    dob1.attr("string", "text 1");
    dob2.attr("string", "text 2");
    dob3.attr("string", "text 3");

    dobCollection.add(dob1);
    dobCollection.add(dob2);
    dobCollection.add(dob3);

    String json = resourceReader.getStringFromResource("/data/dobcollection.json");
    json = json.replaceAll("\\r?\\n", ""); // remove all newlines
    String jsonFromCollection = dobCollection.toJson().replaceAll("\\r?\\n", ""); // remove all newlines

    assertEquals(json, jsonFromCollection);
  }

  /**
   * Test of toXml method, of class DobCollection.
   */
  @Test
  public void testToXml() {
    IDobCollection dobCollection = new DobCollection();
    IDob dob1 = new Dob();
    IDob dob2 = new Dob();
    IDob dob3 = new Dob();

    dob1.attr("id", "1");
    dob2.attr("id", "2");
    dob3.attr("id", "3");

    dob1.attr("string", "text 1");
    dob2.attr("string", "text 2");
    dob3.attr("string", "text 3");

    dobCollection.add(dob1);
    dobCollection.add(dob2);
    dobCollection.add(dob3);

    String xml = resourceReader.getStringFromResource("/data/dobcollection.xml");
    xml = xml.replaceAll("\\r?\\n", ""); // remove all newlines
    String xmlFromCollection = dobCollection.toXml().replaceAll("\\r?\\n", ""); // remove all newlines

    assertEquals(xml, xmlFromCollection);
  }

  @Test
  public void testToDob() {
    IDobCollection dobCollection = new DobCollection();
    IDob dob1 = new Dob();
    IDob dob2 = new Dob();
    IDob dob3 = new Dob();

    dob1.attr("id", "1");
    dob2.attr("id", "2");
    dob3.attr("id", "3");

    dob1.attr("string", "text 1");
    dob2.attr("string", "text 2");
    dob3.attr("string", "text 3");

    dobCollection.add(dob1);
    dobCollection.add(dob2);
    dobCollection.add(dob3);

    String dob = resourceReader.getStringFromResource("/data/dobcollection.dob");
    dob = dob.replaceAll("\\r?\\n", ""); // remove all newlines
    String dobFromCollection = dobCollection.toDob().replaceAll("\\r?\\n", ""); // remove all newlines

    assertEquals(dob, dobFromCollection);
  }

  /**
   * Test of filter method, of class DobCollection.
   */
  @Test
  public void testFilterNumbers() {
    IDobCollection dobCollection = new DobCollection();

    dobCollection.add(Dob.create("[val='1']")).add(Dob.create("[val='2']")).add(Dob.create("[val='3.9']")).add(Dob.create("[val='4']"));

    IDobCollection filteredCollection = dobCollection.filter(
            new DobQuery().biggerOrEqual("val", 2).lesser("val", 4));

    assertEquals(2, filteredCollection.size());
  }

  /**
   * Test of iterator method, of class DobCollection.
   */
  @Test
  public void testIterator() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(new Dob());
    dobCollection.add(new Dob());

    int numberOfDobs = 0;

    Iterator<IDob> iterator = dobCollection.iterator();
    while (iterator.hasNext()) {
      numberOfDobs += 1;
      iterator.next();
    }

    assertEquals(2, numberOfDobs);
  }

  @Test
  public void testGet() {
    IDobCollection dobCollection = new DobCollection(
            "[id='1' attr='abc']",
            "[id='2' attr='def']");

    assertEquals("abc", dobCollection.get("id", "1").attr("attr"));
    assertEquals("def", dobCollection.get("id", "2").attr("attr"));
    assertEquals(null, dobCollection.get("id", "3"));

    dobCollection = new DobCollection(
            "[integ-parameter='person' index='0' default-value='' display-name='Anställd']",
            "[integ-parameter='year' index='1' default-value='2013' display-name='År']");

    assertEquals(2, dobCollection.size());
    assertEquals("person", dobCollection.get("index", "0").attr("integ-parameter"));
    assertEquals("year", dobCollection.get("index", "1").attr("integ-parameter"));
  }

  @Test
  public void testGetAll() {
    IDobCollection dobCollection = new DobCollection(
            "[id='1' attr='abc']",
            "[id='3' attr='def']",
            "[id='4' attr='abc']",
            "[id='5' attr='abc']",
            "[id='6' attr='ghi']");

    IDobCollection filteredCollection = dobCollection.getAll("attr", "abc");
    assertEquals(3, filteredCollection.size());

    IDobCollection noEntries = dobCollection.getAll("attr",
            "no entries with this value");
    assertEquals(0, noEntries.size());
  }

  @Test
  public void testClear() {
    IDobCollection dobCollection = new DobCollection(
            "[id='1' attr='abc']",
            "[id='3' attr='def']",
            "[id='4' attr='abc']",
            "[id='5' attr='abc']",
            "[id='6' attr='ghi']");


    assertEquals("abc", dobCollection.get("id", "1").attr("attr"));
    assertEquals("def", dobCollection.get("id", "3").attr("attr"));
    assertEquals("abc", dobCollection.get("id", "4").attr("attr"));
    assertEquals("abc", dobCollection.get("id", "5").attr("attr"));
    assertEquals("ghi", dobCollection.get("id", "6").attr("attr"));
    assertEquals(5, dobCollection.size());

    dobCollection.clear();

    assertEquals(null, dobCollection.get("id", "1"));
    assertEquals(null, dobCollection.get("id", "3"));
    assertEquals(null, dobCollection.get("id", "4"));
    assertEquals(null, dobCollection.get("id", "5"));
    assertEquals(null, dobCollection.get("id", "6"));
    assertEquals(0, dobCollection.size());
  }

  @Test
  public void testRemove() {
    IDobCollection dobCollection = new DobCollection(
            "[id='1' attr='abc']",
            "[id='3' attr='def']",
            "[id='4' attr='abc']",
            "[id='5' attr='abc']",
            "[id='6' attr='ghi']");

    assertEquals("abc", dobCollection.get("id", "1").attr("attr"));
    assertEquals("def", dobCollection.get("id", "3").attr("attr"));
    assertEquals("abc", dobCollection.get("id", "4").attr("attr"));
    assertEquals("abc", dobCollection.get("id", "5").attr("attr"));
    assertEquals("ghi", dobCollection.get("id", "6").attr("attr"));

    dobCollection.remove("id", "1");

    assertEquals(null, dobCollection.get("id", "1"));
    assertEquals("def", dobCollection.get("id", "3").attr("attr"));
    assertEquals("abc", dobCollection.get("id", "4").attr("attr"));
    assertEquals("abc", dobCollection.get("id", "5").attr("attr"));
    assertEquals("ghi", dobCollection.get("id", "6").attr("attr"));

    dobCollection.remove("attr", "abc");
    assertEquals("def", dobCollection.get("id", "3").attr("attr"));
    assertEquals(null, dobCollection.get("id", "4"));
    assertEquals(null, dobCollection.get("id", "5"));
    assertEquals("ghi", dobCollection.get("id", "6").attr("attr"));

    dobCollection.remove("id", "6");

    assertEquals("def", dobCollection.get("id", "3").attr("attr"));
    assertEquals(null, dobCollection.get("id", "4"));
    assertEquals(null, dobCollection.get("id", "5"));
    assertEquals(null, dobCollection.get("id", "6"));

    dobCollection.remove("attr", "def");

    assertEquals(null, dobCollection.get("id", "3"));
    assertEquals(null, dobCollection.get("id", "4"));
    assertEquals(null, dobCollection.get("id", "5"));
    assertEquals(null, dobCollection.get("id", "6"));
  }
}
