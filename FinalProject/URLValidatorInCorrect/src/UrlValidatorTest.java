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
	   System.out.println("Test:  http://www.amazon.com Expected : true " + ", Actual : " + urlVal.isValid("http://www.amazon.com"));
	   System.out.println("Test:  http://www..com Expected : false " + ", Actual : " + urlVal.isValid("http://www..com"));
	   System.out.println("Test:  http:// Expected : false " + ", Actual : " + urlVal.isValid("http://"));
	   System.out.println("Test:  http://www.amazon.com:3000 Expected : true " + ", Actual : " + urlVal.isValid("http://www.amazon.com:3000"));
	   System.out.println("Test:  http://www.amazon.com:80 Expected : true " + ", Actual : " + urlVal.isValid("http://www.amazon.com:80"));
	   System.out.println("Test:  http://www.amazon.com Expected : true " + ", Actual : " + urlVal.isValid("http://www.amazon.com"));
	   System.out.println("Test:  http://www.amazon.com Expected : true " + ", Actual : " + urlVal.isValid("http://www.amazon.com"));
	   
   }
   
   
   public void testSchemePartition()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   //known valid authority
	   String validAuthority = "www.amazon.com";
	   //schemes to test
	   String[] schemeTest = {"http://", "https://", "http:///", "http:://", "ftp://", "fttp://", "https", "http", "hello"};
	   //corresponds to the above scheme
	   Boolean[] valid = {true, true, false, false, true, false, false, false, false};
	
	   System.out.println("Testing URL schemes:");
	   for (int i = 0; i < 9; i++) {
		   String testing = schemeTest[i] + validAuthority;
		   Boolean expectedVal = valid[i];
		   System.out.println(testing+ "\t" + " Expected:"+ expectedVal + "\t" + " Actual:" + urlVal.isValid(testing));
	   }
	   System.out.println("End URL schemes test.");
	   
   }
   
   public void testAuthorityPartition(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String validScheme = "https://";
	   String[] authorityTest = {"www.amazon.com", "www.amazon.co", "www.amazon", "www.amazon.gov",
			   					"www.amazon.", "amazon.com", "amazon.mil", "255.255.255.255", "256.255.255.255", "300.300.300.300",
			   					"255"};
	   Boolean[] valid = {true, true, false, true, false, true, true, true, false, false, false};
	   for (int i = 0; i < 11 ; i++) {
		   String testing = validScheme + authorityTest[i];
		   Boolean expectedVal = valid[i];
		   System.out.println(testing+ "\t" + " Expected:"+ expectedVal + "\t" + " Actual:" + urlVal.isValid(testing));
	   }
   }
   
   
   public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {
		   
	   }
   }
   
   public void testAnyOtherUnitTest()
   {
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   

}
