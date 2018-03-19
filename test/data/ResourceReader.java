/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.*;

/**
 *
 * @author jenss
 */
public class ResourceReader {
  public String getStringFromResource(String resourceName) {
    InputStream is = getClass().getResourceAsStream(resourceName);
    String string = getStringFromStream(is, 400);
    
    return string;
  }
  
  private String getStringFromStream(final InputStream is, final int bufferSize) {
    final char[] buffer = new char[bufferSize];
    final StringBuilder out = new StringBuilder();
    try {
      final Reader in = new InputStreamReader(is, "UTF-8");
      try {
        for (;;) {
          int rsz = in.read(buffer, 0, buffer.length);
          if (rsz < 0) {
            break;
          }
          out.append(buffer, 0, rsz);
        }
      } catch (IOException ex) {
        /*
         * ...
         */
      } finally {
        try {
          in.close();
        } catch (IOException ex) { /*
           * ...
           */ }
      }
    } catch (UnsupportedEncodingException ex) {
      /*
       * ...
       */
    }
    return out.toString();
  }    
}
