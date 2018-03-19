/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jesjens.jesdob;

import data.ResourceReader;
import org.junit.*;
import static org.junit.Assert.*;
import se.jesjens.jesload.*;

/**
 *
 * @author jenss
 */
public class DobFactoryTest {

  private final ResourceReader resourceReader = new ResourceReader();

  /**
   * Test of fromXml method, of class DobFactory.
   */
  @Test
  public void testDobsFromXmlResource() throws UnsupportedFormatException, CouldNotLoadResourceException {
    DobFactory dobFactory = new DobFactory();

    IDobCollection dobCollection = dobFactory.getDobsFromResource("/data/dobcollection.xml");

    assertDobCollection(dobCollection);
  }

  /**
   * Test of fromXml method, of class DobFactory.
   */
  @Test
  public void testDobsFromJsonResource() throws UnsupportedFormatException, CouldNotLoadResourceException {
    DobFactory dobFactory = new DobFactory();

    IDobCollection dobCollection = dobFactory.getDobsFromResource("/data/dobcollection.json");

    assertDobCollection(dobCollection);
  }

  /**
   * Test of fromXml method, of class DobFactory.
   */
  @Test
  public void testUnsupportedFormatException() throws CouldNotLoadResourceException {
    DobFactory dobFactory = new DobFactory();

    try {
      IDobCollection dobCollection = dobFactory.getDobsFromResource("/data/file.unsupported");
      fail();
    } catch (UnsupportedFormatException ex) {
    }
  }

  /**
   * Test of fromXml method, of class DobFactory.
   */
  @Test
  public void testDobsFromXml() {
    String xml = resourceReader.getStringFromResource("dobcollection.xml");
    DobFactory dobFactory = new DobFactory();

    IDobCollection dobCollection = dobFactory.getDobs(
            DobFactory.DataFormat.XML, xml);

    assertDobCollection(dobCollection);
  }

  /**
   * Test of fromXml method, of class DobFactory.
   */
  @Test
  public void testDobsFromJson() {
    String json = resourceReader.getStringFromResource("dobcollection.json");
    DobFactory dobFactory = new DobFactory();

    IDobCollection dobCollection = dobFactory.getDobs(
            DobFactory.DataFormat.JSON, json);

    assertDobCollection(dobCollection);
  }

  private void assertDobCollection(IDobCollection dobCollection) {
    IDob dob1 = dobCollection.get(0);
    IDob dob2 = dobCollection.get(1);
    IDob dob3 = dobCollection.get(2);

    assertEquals(3, dobCollection.size());
    assertEquals("1", dob1.attr("id"));
    assertEquals("2", dob2.attr("id"));
    assertEquals("3", dob3.attr("id"));
    assertEquals("text 1", dob1.attr("string"));
    assertEquals("text 2", dob2.attr("string"));
    assertEquals("text 3", dob3.attr("string"));
  }

  /**
   * Test of fromXml method, of class DobFactory.
   */
  @Test
  public void testDobFromXml() {
    String xml = resourceReader.getStringFromResource("dob-nodatatypes.xml");
    DobFactory dobFactory = new DobFactory();

    IDob dob = dobFactory.getDob(DobFactory.DataFormat.XML, xml);
    assertEquals("0.4", dob.attr("float2"));
    assertEquals("5", dob.attr("integer1"));
    assertEquals("0.4", dob.attr("float1"));
    assertEquals("my second string", dob.attr("string2"));
    assertEquals("my first string", dob.attr("string1"));
    assertEquals("8", dob.attr("integer2"));
    assertEquals("true", dob.attr("boolean2"));
    assertEquals("true", dob.attr("boolean1"));
    assertEquals(true, dob.is("boolean2"));
  }

  /**
   * Test of fromJson method, of class DobFactory.
   */
  @Test
  public void testDobFromJson() {
    String json = resourceReader.getStringFromResource("dob-nodatatypes.json");
    DobFactory dobFactory = new DobFactory();

    IDob dob = dobFactory.getDob(DobFactory.DataFormat.JSON, json);
    assertEquals("3.4", dob.attr("float2"));
    assertEquals("5", dob.attr("integer1"));
    assertEquals("0.4", dob.attr("float1"));
    assertEquals("my second string", dob.attr("string2"));
    assertEquals("my first string", dob.attr("string1"));
    assertEquals("8", dob.attr("integer2"));
    assertEquals("true", dob.attr("boolean2"));
    assertEquals("true", dob.attr("boolean1"));
    assertEquals(true, dob.is("boolean2"));
  }

  /**
   * Test of fromJson method, of class DobFactory.
   */
  @Test
  public void testDobFromDob() {
    String data = resourceReader.getStringFromResource("dob.dob");
    IDob dob = DobFactory.getDob(DobFactory.DataFormat.DOB, data);

    assertEquals("3.4", dob.attr("float2"));
    assertEquals("5", dob.attr("integer1"));
    assertEquals("0.4", dob.attr("float1"));
    assertEquals("my second string", dob.attr("string2"));
    assertEquals("my first string", dob.attr("string1"));
    assertEquals("8", dob.attr("integer2"));
    assertEquals("true", dob.attr("boolean2"));
    assertEquals("true", dob.attr("boolean1"));
    assertEquals(true, dob.is("boolean2"));
  }

  @Test
  public void testDobsWithWhitespace() {
    String data = resourceReader.getStringFromResource("dobcollection-spaces.dob");
    IDobCollection dobCollection = DobFactory.getDobs(DobFactory.DataFormat.DOB, data);

    assertDobCollection(dobCollection);
  }

  @Test
  public void testDobsWithLineBreaks() {
    String data = resourceReader.getStringFromResource("dobcollection-linebreaks.dob");
    IDobCollection dobCollection = DobFactory.getDobs(DobFactory.DataFormat.DOB, data);

    assertDobCollection(dobCollection);
  }

  @Test
  public void testDobsWithEmptyValues() {
    String data = resourceReader.getStringFromResource("dob-empty-values.dob");
    IDob dob = DobFactory.getDob(DobFactory.DataFormat.DOB, data);

    assertEquals("3.4", dob.attr("float2"));
    assertEquals("", dob.attr("integer1"));
    assertEquals("0.4", dob.attr("float1"));
    assertEquals("", dob.attr("string2"));
    assertEquals("my first string", dob.attr("string1"));
    assertEquals("8", dob.attr("integer2"));
    assertEquals("", dob.attr("boolean2"));
    assertEquals("true", dob.attr("boolean1"));
    assertEquals(true, dob.is("boolean1"));
    assertEquals(false, dob.is("boolean2"));
  }

  @Test
  public void testDobComments() {
    String data = resourceReader.getStringFromResource("dobcollection_comments.dob");
    IDobCollection dobCollection = DobFactory.getDobs(DobFactory.DataFormat.DOB, data);

    IDob dob1 = dobCollection.get(0);
    IDob dob2 = dobCollection.get(1);
    IDob dob3 = dobCollection.get(2);

    assertEquals("1", dob1.attr("id"));
    assertEquals("text 1", dob1.attr("string"));
    assertEquals("2", dob2.attr("id"));
    assertEquals("text 2", dob2.attr("string"));
    assertEquals("3", dob3.attr("id"));
    assertEquals("text 3", dob3.attr("string"));
  }

  @Test
  public void testDobWithApostrophe() {
    IDob dob = DobFactory.getDob(DobFactory.DataFormat.DOB,
            "[text1='one boy\\'s hat' text2='A \\'quote\\']");

    assertEquals("one boy's hat", dob.attr("text1"));
    assertEquals("A 'quote'", dob.attr("text2"));
  }

  @Test
  public void testDobWithEquals() {
    IDob dob = DobFactory.getDob(DobFactory.DataFormat.DOB,
            "[text1='e=mc2' text2='1 + 1 = 2']");

    assertEquals("e=mc2", dob.attr("text1"));
    assertEquals("1 + 1 = 2", dob.attr("text2"));
  }
}
