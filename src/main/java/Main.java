import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import com.opencsv.CSVReader;

public class Main {

  public static void main(String args[]) {

    System.out.println("Please enter input");

    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();
    Parser parser = new Parser();

    input.toCharArray();

    List<String[]> cityNameList = new ArrayList<>();
    FileReader filereader = null;
    try {
      filereader =
          new FileReader("/home/chaipoint/Downloads/world-cities_zip/archive/world-cities.csv");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    @SuppressWarnings("resource")
    CSVReader csvReader = new CSVReader(filereader);
    try {
      cityNameList = csvReader.readAll();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Set<String> listOfCities = new HashSet<>();
    for (String[] s : cityNameList) {
      for (String a : s) {
        for (String val : input.split(" ")) {
          if (val.toLowerCase().contentEquals(a.toLowerCase())) {
            listOfCities.add(val);
          }
        }
      }
    }
    System.out.println();
    if (!listOfCities.isEmpty()) {
      if (listOfCities.size() < 2) {
        System.out.print("Location: ");
        for (String city : listOfCities) {
          System.out.print(city);
        }
      } else {
        System.out.println("Locations: ");
        for (String city : listOfCities) {
          System.out.println(city);
        }
      }
    }
    System.out.println();

    List<Date> dates = new ArrayList<>();
    List<DateGroup> groups = parser.parse(input);
    for (DateGroup group : groups) {

      dates = group.getDates();
      processDate(group);

    }
    for (Date date : dates) {
      convertingDate(date);
    }

  }

  private static void convertingDate(Date date) {
    String dateString = date.toString();
    int i = 0;
    dateString.toCharArray();
    for (; i < dateString.length();) {

      String day = "";
      day.toCharArray();
      while (i < 3) {
        if ((dateString.charAt(i) != ' ')) {
          day += (dateString.charAt(i));
          i++;
        }
      }
      if (i < 5) {
        System.out.println("Day= " + day);
        i++;
      }

      String inputDate = "";
      inputDate.toCharArray();
      while (i < 10) {
        inputDate += dateString.charAt(i);
        i++;
      }
      String time = "";
      time.toCharArray();
      while (i < 16) {
        time += dateString.charAt(i);
        i++;
      }
      if (i < 17) {
        Format f = new SimpleDateFormat("kk:mm");
        String strResult = f.format(new Date());
        if (!time.contains(strResult)) {
          System.out.println("Time = " + time);
        }
      }
      while (i < 23) {
        i++;
      }
      while (i < dateString.length()) {
        inputDate += dateString.charAt(i);
        i++;
      }
      if (i == dateString.length()) {
        System.out.println("Date= " + inputDate);
      }
    }
  }

  @SuppressWarnings({ "unused", "rawtypes" })
  private static void processDate(DateGroup group) {
    int line = group.getLine();
    int column = group.getPosition();
    String matchingValue = group.getText();
    String syntaxTree = group.getSyntaxTree().toStringTree();
    Map parseMap = group.getParseLocations();
    boolean isRecurreing = group.isRecurring();
    Date recursUntil = group.getRecursUntil();
  }

}
