package utils;

import org.junit.Test;

public class FilterMatch {

  public static enum SessionFilterExcludeService {

    LOGIN_METHOD("\\/clearview\\/v1\\/edibles\\/login"),
    FORGOT_PASSWORD("\\/clearview\\/v1\\/edibles\\/user\\/generate\\/password"),
    VALIDATE_URL_METHOD("\\/clearview\\/v1\\/edibles\\/token\\/validate"),
    REGISTER_USER_METHOD("\\/clearview\\/v1\\/edibles\\/user\\/register"),
    GET_USER_METHOD("\\/clearview\\/v1\\/edibles\\/getUserDetails"),
    GET_COUNTRY_LIST_METHOD("\\/clearview\\/v1\\/edibles\\/country"),
    GET_USER_DETAILS_METHOD("\\/clearview\\/v1\\/edibles\\/user"),
    REGISTER_COMPANY_DETAILS_METHOD("\\/clearview\\/v1\\/edibles\\/company"),
    REGISTER_PERSON_DETAILS_METHOD("\\/clearview\\/v1\\/edibles\\/person"),
    RELATE_USER("\\/clearview\\/v1\\/edibles\\/relate\\/user"),
    REINDEX("\\/clearview\\/v1\\/edibles\\/products\\/index"),
    REGISTER_SUPPLIER("\\/clearview\\/v1\\/edibles\\/suppliers"),
    INT_TEST_SYSTEM_TEMPLATES("\\/clearview\\/v1\\/tests\\/edibles-test\\/getSystemTemplates"),
    FILE_UPLOAD_RETAILER_TEST("\\/clearview\\/v1\\/tests\\/edibles-test\\/upload\\/retailerProductFileUpload"),
    TEST_UPDATE_CART("\\/clearview\\/v1\\/tests\\/edibles-test\\/\\w+");

    private String value;

    SessionFilterExcludeService(String value) {
      this.value = value;
    }

    public String toString() {
      return value;
    }

    /**
     * matches the request uri with the exclude urls
     * 
     * @param uri
     * @return
     */
    public static boolean filterMatch(String uri) {

      SessionFilterExcludeService[] ecludeURLs = SessionFilterExcludeService.values();

      for (int i = 0; i < ecludeURLs.length; i++) {
        // logger.debug("URI: {}, excluded: {}", uri, ecludeURLs[i].toString());
        if (uri.matches(ecludeURLs[i].toString())) {
          return true;
        }
      }
      return false;
    }
  }

  @Test
  public void testFilterExamples() {

    String uri1 = "/clearview/v1/tests/edibles-test/testUpdateCart";
    String uri2 = "/clearview/v1/tests/edibles-test/testProductPlan";
    String uri3 = "/clearview/v1/tests/edibles-test/mock/test2";
    String uri4 = "/clearview/v1/tests/edibles-test/mock/testMockWorkflow";

    System.out.println(uri1 + " : " + SessionFilterExcludeService.filterMatch(uri1));
    System.out.println(uri2 + " : " + SessionFilterExcludeService.filterMatch(uri2));
    System.out.println(uri3 + " : " + SessionFilterExcludeService.filterMatch(uri3));
    System.out.println(uri4 + " : " + SessionFilterExcludeService.filterMatch(uri4));
  }

}
