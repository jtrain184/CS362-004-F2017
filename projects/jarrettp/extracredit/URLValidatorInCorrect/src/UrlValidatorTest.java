/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;


/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
 // Create expected results pairs for tests from partition inputs above and others

 //  Array of expected scheme results

   ResultPair[] testUrlScheme = {
           new ResultPair("http://", true),
           new ResultPair("https://", true),
           new ResultPair("http:///", false),
           new ResultPair("http:://", false),
           new ResultPair("ftp://", false),
           new ResultPair("https", false),
           new ResultPair("http", false),
           new ResultPair("hello", false),
           
           new ResultPair("", false),
           new ResultPair(" ", false)

   };


// Array of expected authority results
   
   ResultPair[] testAuthorityAndPort = {
           new ResultPair("www.amazon.com", true),
           new ResultPair("www.amazon.co", true),
           new ResultPair("www.amazon", false),
           new ResultPair("www.amazon.gov", true),
           new ResultPair("www.amazon.", false),
           new ResultPair("amazon.com", true),
           new ResultPair("amazon.mil", true),
           new ResultPair("255.255.255.255", true),
           new ResultPair("256.255.255.255", false),
           new ResultPair("300.300.300.300", false),
           new ResultPair("255", false),
           
           // Test some port numbers
           new ResultPair("www.amazon.com:30", true),
           new ResultPair("www.amazon.com:3000", true),
           new ResultPair("www.amazon.com:300000", false)
           
   };

   String[] pathTest = {"", "/", "/..", "/file", "/file/file1", "/testfile//testfile1", "/123"};
   Boolean[] validity = {true, true, false, true, true, false, true,};
   
   
// Array of expected path results
   
   ResultPair[] testPath = {
           new ResultPair("", true),
           new ResultPair("/", true),
           new ResultPair("/..", false),
           new ResultPair("/file", true),
           new ResultPair("/file/file1", true),
           new ResultPair("/testfile//testfile1", false),
           new ResultPair("/123", true)
   };


   public void testIsValidRandom(){
       UrlValidator urlVal = new UrlValidator(null, null, (UrlValidator.ALLOW_ALL_SCHEMES + UrlValidator.ALLOW_LOCAL_URLS));

    //builds a valid url from the scheme, authority and optional port, and path
       
       for(int i = 0; i < testUrlScheme.length; i++) {
           for(int j = 0; j < testAuthorityAndPort.length; j++) {
               for(int k = 0; k < testPath.length; k++) {

                       boolean expected = testUrlScheme[i].valid &&
                    		   testAuthorityAndPort[j].valid &&
                               testPath[k].valid;

                       String testUrl = new StringBuilder(255).append(testUrlScheme[i].item)
                               .append(testAuthorityAndPort[j].item)
                               .append(testPath[k].item)
                               .toString();
      
                       // Actual result
                       boolean actual = urlVal.isValid(testUrl);

                       if(actual !=expected) {
                           System.out.println("Failed: " + testUrl + "    Actual Result: " + actual);
                           System.out.println("    expected result: " + expected);
                           assertEquals(expected, actual);
                       }
                       else {
                           assertEquals(expected, actual);
                       }

               } // end of path builder loop
          } // end of authority/port builder loop
      } //end of scheme builder loop
   } // end of testIsValid function

}
