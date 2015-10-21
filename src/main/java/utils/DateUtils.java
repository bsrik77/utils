package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import com.datastax.driver.core.utils.UUIDs;

public class DateUtils {

  @Test
  public void testCurrentTime() {
    /*
     * long nano = System.nanoTime(); long milli = nano / 1000; long second = milli / 1000; long minute = second / 60;
     * long hour = minute / 60;
     * 
     * System.out.println("Current:: " + nano + " : " + milli + " : " + second + " : " + minute + " : " + hour);
     */

    Calendar now = Calendar.getInstance();
    int year = now.get(Calendar.YEAR);
    int month = now.get(Calendar.MONTH); // Note: zero based!
    int day = now.get(Calendar.DAY_OF_MONTH);
    int hour = now.get(Calendar.HOUR_OF_DAY);
    int minute = now.get(Calendar.MINUTE);
    int second = now.get(Calendar.SECOND);
    int millis = now.get(Calendar.MILLISECOND);

    System.out.printf("%d-%02d-%02d %02d:%02d:%02d.%03d", year, month + 1, day, hour, minute, second, millis);
  }

  public void testJodaTime() {
    DateTime now = new DateTime();

    System.out.println("Date:: " + DateTimeFormat.forPattern("yyyy-MM-dd").print(now));

    DateTimeFormatter secondfmt = DateTimeFormat.forPattern("yyyy-MM-dd-HH-mm-ss");
    System.out.println("Second:: " + secondfmt.print(now));
  }

  @Test
  public void getLongDate() throws ParseException {

    String string_date = "12-December-2016";
    SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
    Date d = f.parse(string_date);
    long milliseconds = d.getTime();
    System.out.println(string_date + " Time in long:: " + milliseconds);

    long millis = 1481529600000L;

    Date d1 = new Date(millis);
    String dateStr = f.format(d1);

    System.out.println(millis + " Time in string:: " + dateStr);
  }

  @Test
  public void jodaUtils() throws Exception {
    DateTimeFormatter dateFormat = DateTimeFormat.forPattern("G,C,Y,x,w,e,E,Y,D,M,d,a,K,h,H,k,m,s,S,z,Z");

    String dob = "2002-01-15";
    LocalTime localTime = new LocalTime();
    LocalDate localDate = new LocalDate();
    DateTime dateTime = new DateTime();
    LocalDateTime localDateTime = new LocalDateTime();
    DateTimeZone dateTimeZone = DateTimeZone.getDefault();

    System.out.println("dateFormatr : " + dateFormat.print(localDateTime));
    System.out.println("LocalTime : " + localTime.toString());
    System.out.println("localDate : " + localDate.toString());
    System.out.println("dateTime : " + dateTime.toString());
    System.out.println("localDateTime : " + localDateTime.toString());
    System.out.println("DateTimeZone : " + dateTimeZone.toString());
    System.out.println("Year Difference : " + Years.yearsBetween(DateTime.parse(dob), dateTime).getYears());
    System.out.println("Month Difference : " + Months.monthsBetween(DateTime.parse(dob), dateTime).getMonths());
  }

  @Test
  public void daysBetween() throws Exception {

    DateTime dateTime1 = new DateTime();
    DateTime dateTime2 = new DateTime(dateTime1.minusDays(10));

    System.out.println("Days between:: " + Days.daysBetween(dateTime2, dateTime1).getDays());
  }

  @Test
  public void uuidDateUtils() throws Exception {

    DateTime expiryDate = new DateTime(UUIDs.unixTimestamp(UUID.fromString("4a335cff-4828-11e5-7f7f-7f7f7f7f7f7f")));
    System.out.println("Expiry date:: " + expiryDate);

    int days = Days.daysBetween(new DateTime(), expiryDate).getDays();
    int expiryDays = days > 0 ? days : 0;

    System.out.println("Days between:: " + expiryDays);
  }
}
