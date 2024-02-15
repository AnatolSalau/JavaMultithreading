package itvdn._2_callable_future.sample.load_files;

import java.io.*;

public class FileUtils {
      public static void getStatistic(String resultReport, String pathToFile) {
          String[] strings = readFromFile(pathToFile);
          String report = createReport(strings);
          writeToFile(report, resultReport);
      }
      private static String[] readFromFile(String fromFilePath) {
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFilePath))){
                  int value = bufferedReader.read();
                  while (value != - 1) {
                        stringBuilder.append((char) value);
                        value = bufferedReader.read();
                  }

                  String wordsWithoutEnter = stringBuilder.toString()
                        .replaceAll("\n","")
                        .replaceAll("\r","");

                  String[] splitedWords = wordsWithoutEnter.split(",");
                  
                  return splitedWords;
            } catch (FileNotFoundException e) {
                  throw new RuntimeException(e);
            } catch (IOException e) {
                  throw new RuntimeException(e);
            }
      }
      private static String createReport(String[] splitArray) {
            int supply = 0;
            int buy = 0;
            for (int i = 0; i < splitArray.length; i++) {
                  if (splitArray[i].startsWith("supply")) {
                        String substring = splitArray[i].substring(6);
                        supply += Integer.parseInt(substring);
                  } else if (splitArray[i].startsWith("buy")) {
                        String substring = splitArray[i].substring(3);
                              buy += Integer.parseInt(substring);
                  }
            }
            String report = "supply = " + supply + "\nbuy = " + buy + "\nresult = " + (buy - supply);
            return report;
      }

      private static void writeToFile(String resultReport, String pathToFile) {
            File file = new File(pathToFile);
            try {
                  file.createNewFile();
            } catch (IOException e) {
                  throw new RuntimeException(e);
            }

            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                  bufferedWriter.write(resultReport);
            } catch (IOException e) {
                  throw new RuntimeException(e);
            }
      }
}
