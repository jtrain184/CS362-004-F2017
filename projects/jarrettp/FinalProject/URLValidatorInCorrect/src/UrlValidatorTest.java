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

   
   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	  
	   System.out.println("Test:  http://www.amazon.com?action=view Expected : true " + ", Actual : " + urlVal.isValid("http://www.amazon.com?action=view"));
	   assertTrue(urlVal.isValid("http://www.amazon.com?action=view"));
	   
	   System.out.println("Test:  https://256.255.255.255 Expected : false " + ", Actual : " + urlVal.isValid("https://256.255.255.255"));
	   assertFalse(urlVal.isValid("https://256.255.255.255"));
	   
	   System.out.println("Test:  http://www.amazon.com Expected : true " + ", Actual : " + urlVal.isValid("http://www.amazon.com"));
	   assertTrue(urlVal.isValid("http://www.amazon.com"));
	   System.out.println("Test:  http://www..com Expected : false " + ", Actual : " + urlVal.isValid("http://www..com"));
	   assertFalse(urlVal.isValid("http://www..com"));
	   System.out.println("Test:  http:// Expected : false " + ", Actual : " + urlVal.isValid("http://"));
	   assertFalse(urlVal.isValid("http://"));
	   System.out.println("Test:  http://www.amazon.com:3000 Expected : true " + ", Actual : " + urlVal.isValid("http://www.amazon.com:3000"));
	   assertTrue(urlVal.isValid("http://www.amazon.com:3000"));
	   System.out.println("Test:  http://www.amazon.com:80 Expected : true " + ", Actual : " + urlVal.isValid("http://www.amazon.com:80"));
	   assertTrue(urlVal.isValid("http://www.amazon.com:80"));
   }
   
   
   public void testschemePartition()
   {
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       //known valid authority
       String validAuthority = "www.amazon.com";
       //schemes to test
       String[] schemeTest = {"http://", "https://", "http:///", "http:://", "ftp://", "fttp://", "https", "http", "hello"};
       //corresponds to the above scheme
       boolean [] valid = {true, true, false, false, true, false, false, false, false};
   
       System.out.println("Testing URL schemes:");
       for (int i = 0; i < 9; i++) {
           String testing = schemeTest[i] + validAuthority;
           boolean expectedVal = valid[i];
           System.out.println(testing+ "\t" + " Expected:"+ expectedVal + "\t" + " Actual:" + urlVal.isValid(testing));
           assertEquals(expectedVal,urlVal.isValid(testing));
       }
       System.out.println();
   }
   
   public void testauthorityPartition()
   {
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       String validScheme = "https://";
       String[] authorityTest = {"www.amazon.com", "www.amazon.co", "www.amazon", "www.amazon.gov",
                                "www.amazon.", "amazon.com", "amazon.mil", "255.255.255.255", "256.255.255.255", "300.300.300.300",
                                "255"};
       boolean[] valid = {true, true, false, true, false, true, true, true, false, false, false};
       System.out.println("Testing URL Authorities:");
       for (int i = 0; i < 11 ; i++) {
           String testing = validScheme + authorityTest[i];
           boolean expectedVal = valid[i];
           System.out.println(testing+ "\t" + " Expected:"+ expectedVal + "\t" + " Actual:" + urlVal.isValid(testing));
           assertEquals(expectedVal, urlVal.isValid(testing));
       }
       System.out.println("");
   }
   
   public void testPortPartition()
   {
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       System.out.println("Testing URL Ports:");
       String validScheme = "http://";
       String validAuthority = "www.amazon.com";
       
       String[] portTest = {":80", ":0", ":1", ":300", ":3000", ":65535", ":70000", ":25", ":100000", ":-1"};
       boolean[] validity = {true, true, true, true, true, true, false, true, false, false};
       
       for (int i = 0; i < 9; i++) {
           String testing = validScheme + validAuthority + portTest[i];
           boolean expectedVal = validity[i];
           System.out.println(testing+ "\t" + " Expected:"+ expectedVal + "\t" + " Actual:" + urlVal.isValid(testing));
           assertEquals(expectedVal, urlVal.isValid(testing));
       }
       System.out.println("");
   }
   
   public void testPathPartition()
   {
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       System.out.println("Testing URL Paths:");
       String validScheme = "http://";
       String validAuthority = "www.amazon.com";
       
       String[] pathTest = {"", "/", "/..", "/file", "/file/file1", "/testfile//testfile1", "/123"};
       boolean[] validity = {true, true, false, true, true, false, true,};
       
       for (int i = 0; i < 7; i++) {
           String testing = validScheme + validAuthority + pathTest[i];
           boolean expectedVal = validity[i];
           System.out.println(testing+ "\t" + " Expected:"+ expectedVal + "\t" + " Actual:" + urlVal.isValid(testing));
           assertEquals(expectedVal, urlVal.isValid(testing));
       }
       
       System.out.println("");
   }
   
   public void testQueryPartition()
   {
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       System.out.println("Testing URL Queries:");
       
       String validScheme = "http://";
       String validAuthority = "www.amazon.com";
       
       String[] queryTest = {"", "?action=view", "?cid=100", "?password=myPassword", "?action="};
       boolean[] validity = {true, true, true, true, true};
       
       for (int i = 0; i < 5; i++) {
           String testing = validScheme + validAuthority + queryTest[i];
           boolean expectedVal = validity[i];
           System.out.println(testing+ "\t" + " Expected:"+ expectedVal + "\t" + " Actual:" + urlVal.isValid(testing));
           assertEquals(expectedVal, urlVal.isValid(testing));
       }
       
       System.out.println("");
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


   public void testIsValid(){
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
