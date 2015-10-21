package utils;

import org.junit.Test;

public class MathUtils {

  @Test
  public void testMathRound() {

    int price = 351800;
    price = (int) Math.round((price + 25000 - 55000) * 1.2 + 55000);

    System.out.println("Price: " + price);

  }

}
