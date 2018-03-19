/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jesjens.jesdob;

import data.ResourceReader;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author jenss
 */
public class DobTest {
  private final ResourceReader resourceReader = new ResourceReader();
  
  public DobTest() {
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

  @Test
  public void testAttrString() {
    IDob dob = new Dob();
    dob.attr("text1", "abc 1 2 3");
    dob.attr("text2", "123 abc");
    
    assertEquals("abc 1 2 3", dob.attr("text1"));
    assertEquals("123 abc", dob.attr("text2"));
    assertEquals(null, dob.attr("text3"));
  }  
  
   @Test
  public void testAttrFloat() {
    IDob dob = new Dob();
    dob.attr("float_1", 0.4f);
    dob.attr("float_2", 78.323f);
    
    assertEquals("0.4", dob.attr("float_1"));
    assertEquals("78.323", dob.attr("float_2"));
    assertEquals(null, dob.attr("float_3"));
  }  
   
   @Test
  public void testAttrInt() {
    IDob dob = new Dob();
    dob.attr("int_1", 42);
    dob.attr("int_2", 100);
    
    assertEquals("42", dob.attr("int_1"));
    assertEquals("100", dob.attr("int_2"));
    assertEquals(null, dob.attr("int_3"));
  }    
  
  /**
   * Test of is method, of class Dob.
   */
  @Test
  public void testIs() {
    IDob dob = new Dob();
    dob.attr("very_good", true);
    dob.attr("very_bad", false);
    
    assertEquals(true, dob.is("very_good"));
    assertEquals(false, dob.is("very_bad"));
    assertEquals(false, dob.is("attribute_not_set"));
  }

  /**
   * Test of hasAttr method, of class Dob.
   */
  @Test
  public void testHasAttr() {
    IDob dob = new Dob();
    dob.attr("existing_attribute", "value");
    
    assertEquals(true, dob.hasAttr("existing_attribute"));
    assertEquals(false, dob.hasAttr("non_existing_attribute"));
  }

  /**
   * Test of removeAttr method, of class Dob.
   */
  @Test
  public void testRemoveAttr() {
    IDob dob = new Dob();
    dob.attr("my_attribute", "value");
    
    assertEquals(true, dob.hasAttr("my_attribute"));
    
    dob.removeAttr("my_attribute");
    
    assertEquals(false, dob.hasAttr("my_attribute"));
  }

  /**
   * Test of toJson method, of class Dob.
   */
  @Test
  public void testToJson() {
    IDob dob = new Dob();
    dob.attr("boolean1", true);
    dob.attr("integer1", 5);
    dob.attr("string1", "my first string");
    dob.attr("float1", 0.4f);
    dob.attr("boolean2", true);
    dob.attr("integer2", 8);
    dob.attr("string2", "my second string");
    dob.attr("float2", 3.4f);
    
    assertEquals(resourceReader.getStringFromResource("dob-nodatatypes.json"), dob.toJson());
  }

  /**
   * Test of toXml method, of class Dob.
   */
  @Test
  public void testToXml() {
    IDob dob = new Dob();
    dob.attr("boolean1", true);
    dob.attr("integer1", 5);
    dob.attr("string1", "my first string");
    dob.attr("float1", 0.4f);
    dob.attr("boolean2", true);
    dob.attr("integer2", 8);
    dob.attr("string2", "my second string");
    dob.attr("float2", 0.4f);
  
    assertEquals(resourceReader.getStringFromResource("dob-nodatatypes.xml"), dob.toXml());
  }
  
  /**
   * Test of toXml method, of class Dob.
   */
  @Test
  public void testToDob() {
    IDob dob = new Dob();
    dob.attr("boolean1", true);
    dob.attr("integer1", 5);
    dob.attr("string1", "my first string");
    dob.attr("float1", 0.4f);
    dob.attr("boolean2", true);
    dob.attr("integer2", 8);
    dob.attr("string2", "my second string");
    dob.attr("float2", 3.4f);
    
    assertEquals(resourceReader.getStringFromResource("dob.dob"), dob.toDob());
  }  
}
