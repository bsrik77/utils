package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class StringUtils {

  public static final String UUID_REGEX = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";
  private static final String APPS_REGEX = "(WERCS|GIGA|ULEGG):(MATERIALS:)*[{id}|0-9]+";

  @Test
  public void testStream() {

    List<String> list = new ArrayList<String>();
    String[] strings = list.stream().toArray(String[]::new);

    System.out.println("Strings :: " + strings);
  }

  @Test
  public void testStringReplace() {

    String test1 = "123.45";
    String test2 = "-123.45";

    System.out.println("Test1: " + addCurrencyString("USD", test1));
    System.out.println("Test1: " + addCurrencyString("USD", test2));
  }

  private static String addCurrencyString(String currency, String price) {
    if (price != null) {
      Double priceVal = Double.valueOf(price);
      if (currency.equals("USD") && priceVal < 0) {
        return price.replace("-", "-$");
      } else if (currency.equals("USD") && priceVal > 0) {
        return "$" + price;
      }
    }
    return price;
  }

  @Test
  public void encodingStrings() throws UnsupportedEncodingException {

    String data = "GIGA:MATERIALS:58541";
    String encodedData = Base64.encodeBase64String(data.getBytes("UTF-8"));
    System.out.println("Encoded string:: " + encodedData);

    encodedData = Base64.encodeBase64String(encodedData.getBytes("UTF-8"));
    System.out.println("Encoded string 2:: " + encodedData);
  }

  @Test
  public void urlEncodingStrings() throws UnsupportedEncodingException {

    String data = "GIGA:MATERIALS:58541";
    String encodedData = URLEncoder.encode(data, "UTF-8");
    System.out.println("Encoded string:: " + encodedData);

    String decodedData = URLDecoder.decode(encodedData, "UTF-8");
    System.out.println("Decoded string:: " + decodedData);
  }

  @Test
  public void testSubstrings() {

    String testStr1 = "/v1/test/url/withoutint";
    String requestUri = "/v1/test/url/withint/12str";

    if (testStr1.lastIndexOf("/") != -1) {
      System.out.println(testStr1 + ":" + testStr1.substring(testStr1.lastIndexOf("/") + 1).matches("[a-zA-Z]+"));
    }

    if (requestUri.lastIndexOf("/") != -1) {
      System.out.println(requestUri + ":" + requestUri.substring(testStr1.lastIndexOf("/") + 1).matches("[a-zA-Z]+"));
    }

    // Track all GET requests with a common URL
    int lastIndex = requestUri.lastIndexOf("/");
    if (lastIndex != -1) {
      String endStr = requestUri.substring(requestUri.lastIndexOf("/") + 1);
      if (!endStr.matches("[a-zA-Z]+")) {
        requestUri = requestUri.substring(0, lastIndex);
        System.out.println("Modifying the requesturi to :: " + requestUri);
      }
    }

  }

  @Test
  public void testRegexReplace() {
    String testStr1 = "/api/v1/carts";
    String testStr2 = "/api/v1/companies/4be1e966-c02d-403f-b680-fa9b25ca738d/facility";
    String testStr3 = "/api/v1/companies/GIGA:MATERIALS:123/facility";
    String testStr4 = "/api/v1/companies/ULEGG:MATERIALS:12345/facility";
    String testStr5 = "/api/v1/companies/WERCS:4be1e966-c02d-403f-b680-fa9b25ca738d/facility";

    System.out.println("Replaced:: " + testStr1.replaceAll(UUID_REGEX, "{id}").replaceAll(APPS_REGEX, "{id}"));
    System.out.println("Replaced:: " + testStr2.replaceAll(UUID_REGEX, "{id}").replaceAll(APPS_REGEX, "{id}"));
    System.out.println("Replaced:: " + testStr3.replaceAll(UUID_REGEX, "{id}").replaceAll(APPS_REGEX, "{id}"));
    System.out.println("Replaced:: " + testStr4.replaceAll(UUID_REGEX, "{id}").replaceAll(APPS_REGEX, "{id}"));
    System.out.println("Replaced:: " + testStr5.replaceAll(UUID_REGEX, "{id}").replaceAll(APPS_REGEX, "{id}"));
  }

  @Test
  public void testSplit() {
    String testsTokenDetails = "72ba54fa-ba0b-4bcc-b71a-e24e93ac96bf|hello:wercs_smart|"
          + "hello:wercs_smart|1393027633887|1481529600000|AUTHCODE|"
          + "caeba37b-5524-491c-8352-33f5a1f68e5b|991a1932-bb75-4643-a722-3cc95480ed8b";
    String[] tokens = testsTokenDetails.split("\\|");

    System.out.println("Tokens: " + tokens.toString());

    /*
     * Token token = new Token(tokens[0]); token.setAppname(tokens[1]); token.setCreatedBy(tokens[2]);
     * token.setCreatedOn(new Date(Long.parseLong(tokens[3]))); token.setExpiresOn(new Date(Long.parseLong(tokens[4])));
     * token.setType(tokens[5]); token.setUserid(tokens[6]); token.setPrivateKey(tokens[7]); token.setAuthParams(new
     * HashMap<String, String>() { { put(AuthType.AUTH_TYPE, AuthType.BASIC.toString()); put("sessionExpiry",
     * Long.toString(20 * 60 * 1000L)); } });
     */
  }

  @Test
  public void testSplit2() {
    String pattern2 = "test name:798787877";

    String productName = "test name";

    String exId = null;
    String[] arr = pattern2.split(":");
    if (arr.length == 2) {
      if (arr[0].equals(productName)) {
        exId = arr[1];
      }
    }

    System.out.println("Split value: " + exId);
  }

  @Test
  public void testSplit3() {
    String appname = "hello:wercs_smart";
    String appname2 = "meds";

    System.out.println("Appname1: " + appname == null ? null : appname.substring(appname.indexOf(':') + 1));
    System.out.println("Username1: " + appname == null ? null : appname.substring(0, appname.indexOf(':')));

    System.out.println("Appname2: " + appname2 == null ? null : appname2.substring(appname2.indexOf(':') + 1));
    System.out.println("Username2: " + appname2 == null ? null : appname2.substring(0, appname2.indexOf(':')));

  }

  public String parsedAppName(String appname) {
    return appname == null ? null : appname.substring(appname.indexOf(':') + 1);
  }

  public String parsedUserName(String appname) {
    return appname == null ? null : appname.substring(0, appname.indexOf(':'));
  }

}
