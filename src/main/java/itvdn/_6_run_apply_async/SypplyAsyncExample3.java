package itvdn._6_run_apply_async;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class SypplyAsyncExample3 {
      public static void main(String[] args) {
            while (true) {
                  try(Scanner scanner = new Scanner(System.in)) {
                        System.out.println("Enter file name");
                        String fileName = scanner.nextLine();

                        CompletableFuture<List<Integer>> getIntegerListFromFile = CompletableFuture.supplyAsync(() -> {
                              List<Integer> result = new ArrayList<>();
                              File file = new File("src/main/resources/" + fileName);
                              try (Scanner myReader = new Scanner(file)) {
                                    while (myReader.hasNextLine()) {
                                          result.add(Integer.valueOf(myReader.nextLine()));
                                    }
                              } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                              }
                              return result;
                        });

                        System.out.println("1 - print min and max values");
                        System.out.println("1 - print sum all values");
                        System.out.println("1 - print min and max values");

                  }
            }




      }
}
