package utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class SecurityExamples {

  private static String secret = "0C6A0511-B62C-4577-B707-BC632ACC5189";

  private String getHmacHash(String secret, String data) {

    String algorithm = "HmacSHA256";

    try {
      Mac sha256_HMAC = Mac.getInstance(algorithm);
      SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), algorithm);
      sha256_HMAC.init(secret_key);
      String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes()));
      return hash;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  @Test
  public void testHmacHash() {
    String message =
          "Mehvsdkjhavihvikhikbsvhikufvhsbkubf hiu bhiubfh iuf hiuh iu hiu bjha bhj "
                + "bhb ui biu bssage SCI team will generate an access key which would be "
                + "required to be sent on every API request (X-UL-Auth as a HTTP header) "
                + "SCI team will generate an access key which would be required to be sent "
                + "on every API request (X-UL-Auth as a HTTP header) SCI team will generate an "
                + "access key which would be required to be sent on every API "
                + "request (X-UL-Auth as a HTTP header) SCI team will generate an access key "
                + "which would be required to be sent on every API request (X-UL-Auth as a HTTP header) "
                + "SCI team will generate an access key which would be required to be sent "
                + "on every API request (X-UL-Auth as a HTTP header)";
    System.out.println(getHmacHash(secret, message));
  }

  @Test
  public void testHmacHashWithUrl() {
    String message = "/api/v1/products/MATERIALS/1bb2d5f3-b837-4895-a2ef-7513485b2d23recursive=true";
    System.out.println(getHmacHash(secret, message));
  }

  @Test
  public void testHmacHashWithJson() {
    String message = "Message";
    System.out.println(getHmacHash(secret, message));
  }

}
