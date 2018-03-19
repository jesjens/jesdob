/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jesjens.jesdob.query;

import org.junit.*;
import static org.junit.Assert.*;
import se.jesjens.jesdob.Dob;
import se.jesjens.jesdob.DobCollection;
import se.jesjens.jesdob.IDob;
import se.jesjens.jesdob.IDobCollection;

/**
 *
 * @author jenss
 */
public class DobQueryTest {

  public DobQueryTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of is method, of class DobQuery.
   */
  @Test
  public void testIs() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[good='true']"),
            Dob.create("[good='false']"),
            Dob.create("[good='true']"));

    assertEquals(2, dobCollection.filter(new DobQuery().is("good")).size());
  }

  /**
   * Test of isNot method, of class DobQuery.
   */
  @Test
  public void testIsNot() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[good='true']"),
            Dob.create("[good='false']"),
            Dob.create("[good='true']"));

    assertEquals(1, dobCollection.filter(new DobQuery().isNot("good")).size());
  }

  /**
   * Test of hasAttr method, of class DobQuery.
   */
  @Test
  public void testHasAttr() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='true']"),
            Dob.create("[good='false']"),
            Dob.create("[good='true']"));

    assertEquals(1, dobCollection.filter(new DobQuery().hasAttr("attr")).size());
  }

  /**
   * Test of hasNotAttr method, of class DobQuery.
   */
  @Test
  public void testHasNotAttr() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='true']"),
            Dob.create("[good='false']"),
            Dob.create("[good='true']"));

    assertEquals(2, dobCollection.filter(new DobQuery().hasNotAttr("attr")).size());
  }

  /**
   * Test of attrEquals method, of class DobQuery.
   */
  @Test
  public void testAttrEquals_String_String() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='abc']"),
            Dob.create("[attr='def']"),
            Dob.create("[attr='def']"));

    assertEquals(2, dobCollection.filter(new DobQuery().equals("attr", "def")).size());
  }

  /**
   * Test of attrEquals method, of class DobQuery.
   */
  @Test
  public void testAttrEquals_String_int() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='abc']"),
            Dob.create("[attr='1234']"),
            Dob.create("[attr='34']"));

    assertEquals(1, dobCollection.filter(new DobQuery().equals("attr", 1234)).size());
  }

  /**
   * Test of attrEquals method, of class DobQuery.
   */
  @Test
  public void testAttrEquals_String_float() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='abc']"),
            Dob.create("[attr='0.867']"),
            Dob.create("[attr='34']"));

    assertEquals(1, dobCollection.filter(new DobQuery().equals("attr", 0.867f)).size());
  }

  /**
   * Test of attrNotEquals method, of class DobQuery.
   */
  @Test
  public void testAttrNotEquals_String_int() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='fd']"),
            Dob.create("[attr='1234']"),
            Dob.create("[attr='34']"));

    assertEquals(2, dobCollection.filter(new DobQuery().notEquals("attr", 1234)).size());
  }

  /**
   * Test of attrNotEquals method, of class DobQuery.
   */
  @Test
  public void testAttrNotEquals_String_float() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='0.43']"),
            Dob.create("[attr='0.867']"),
            Dob.create("[attr='34']"));

    assertEquals(2, dobCollection.filter(new DobQuery().notEquals("attr", 0.867f)).size());
  }

  /**
   * Test of attrNotEquals method, of class DobQuery.
   */
  @Test
  public void testAttrNotEquals_String_String() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='abc']"),
            Dob.create("[attr='232']"),
            Dob.create("[attr='def']"));

    assertEquals(2, dobCollection.filter(new DobQuery().notEquals("attr", "def")).size());
  }

  /**
   * Test of attrContains method, of class DobQuery.
   */
  @Test
  public void testAttrContains() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='ni talar bra latin']"),
            Dob.create("[attr='ni talar inte bra latin']"),
            Dob.create("[attr='ni talar ju inte alls!']"));

    assertEquals(2, dobCollection.filter(new DobQuery().contains("attr", "bra latin")).size());
  }

  /**
   * Test of attrContainsNot method, of class DobQuery.
   */
  @Test
  public void testAttrContainsNot() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='ni talar bra latin']"),
            Dob.create("[attr='ni talar inte bra latin']"),
            Dob.create("[attr='ni talar ju inte alls!']"));

    assertEquals(1, dobCollection.filter(new DobQuery().containsNot("attr", "bra latin")).size());
  }

  /**
   * Test of attrBiggerThan method, of class DobQuery.
   */
  @Test
  public void testAttrBiggerThan_String_int() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='1001']"),
            Dob.create("[attr='1000']"),
            Dob.create("[attr='999']"),
            Dob.create("[attr='abc']"));

    assertEquals(1, dobCollection.filter(new DobQuery().bigger("attr", 1000)).size());
  }

  /**
   * Test of attrLesserThan method, of class DobQuery.
   */
  @Test
  public void testAttrLesserThan_String_int() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='1001']"),
            Dob.create("[attr='1000']"),
            Dob.create("[attr='999']"),
            Dob.create("[attr='abc']"));

    assertEquals(1, dobCollection.filter(new DobQuery().bigger("attr", 1000)).size());
  }

  /**
   * Test of attrBiggerOrEqualTo method, of class DobQuery.
   */
  @Test
  public void testAttrBiggerOrEqualTo_String_int() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='1001']"),
            Dob.create("[attr='1000']"),
            Dob.create("[attr='999']"),
            Dob.create("[attr='abc']"));

    assertEquals(2, dobCollection.filter(new DobQuery().biggerOrEqual("attr", 1000)).size());
  }

  /**
   * Test of attrLesserOrEqualTo method, of class DobQuery.
   */
  @Test
  public void testAttrLesserOrEqualTo_String_int() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='1001']"),
            Dob.create("[attr='1000']"),
            Dob.create("[attr='999']"),
            Dob.create("[attr='abc']"));

    assertEquals(2, dobCollection.filter(new DobQuery().lesserOrEqual("attr", 1000)).size());
  }

  /**
   * Test of attrBiggerThan method, of class DobQuery.
   */
  @Test
  public void testAttrBiggerThan_String_float() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='1000.97']"),
            Dob.create("[attr='1000.96']"),
            Dob.create("[attr='1000.95']"),
            Dob.create("[attr='abc']"));

    assertEquals(1, dobCollection.filter(new DobQuery().bigger("attr", 1000.96)).size());
  }

  /**
   * Test of attrLesserThan method, of class DobQuery.
   */
  @Test
  public void testAttrLesserThan_String_float() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='1000.97']"),
            Dob.create("[attr='1000.96']"),
            Dob.create("[attr='1000.95']"),
            Dob.create("[attr='abc']"));

    assertEquals(1, dobCollection.filter(new DobQuery().lesser("attr", 1000.96)).size());
  }

  /**
   * Test of attrBiggerOrEqualTo method, of class DobQuery.
   */
  @Test
  public void testAttrBiggerOrEqualTo_String_float() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='1000.97']"),
            Dob.create("[attr='1000.96']"),
            Dob.create("[attr='1000.95']"),
            Dob.create("[attr='abc']"));

    assertEquals(2, dobCollection.filter(new DobQuery().biggerOrEqual("attr", 1000.96)).size());
  }

  /**
   * Test of attrLesserOrEqualTo method, of class DobQuery.
   */
  @Test
  public void testAttrLesserOrEqualTo_String_float() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='1000.97']"),
            Dob.create("[attr='1000.96']"),
            Dob.create("[attr='1000.95']"),
            Dob.create("[attr='abc']"));

    assertEquals(2, dobCollection.filter(new DobQuery().lesserOrEqual("attr", 1000.96)).size());
  }

  /**
   * Test of isNumeric method, of class DobQuery.
   */
  @Test
  public void testIsNumeric() {
    IDobCollection dobCollection = new DobCollection();
    dobCollection.add(
            Dob.create("[attr='1000.97']"),
            Dob.create("[attr='1000.96abc']"),
            Dob.create("[attr='343']"),
            Dob.create("[attr='abc']"));

    assertEquals(2, dobCollection.filter(new DobQuery().isNumeric("attr")).size());
  }
}
