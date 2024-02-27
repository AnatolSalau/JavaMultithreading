package itvdn._6_run_apply_async;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class SypplyAsyncExample3 {
      public static void main(String[] args) {
            int count = 1;
            try (Scanner scanner = new Scanner(System.in)) {
                  while (true) {
                        int tmpCount = count;
                        System.out.println("Enter file name: ");
                        /*String fileName = scanner.nextLine();*/
                        String fileName = "array1.txt";

                        CompletableFuture<List<Integer>> getIntegerListFromFile = CompletableFuture.supplyAsync(() -> {
                              List<Integer> result = new ArrayList<>();
                              File file = new File("src/main/resources/" + fileName);
                              try (Scanner myReader = new Scanner(file)) {
                                    while (myReader.hasNextLine()) {
                                          try {
                                                Thread.sleep(2);
                                          } catch (InterruptedException e) {
                                                throw new RuntimeException(e);
                                          }
                                          result.add(Integer.valueOf(myReader.nextLine()));
                                    }
                              } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                              }
                              return result;
                        });

                        System.out.println("1 - print min and max values");
                        System.out.println("2 - print sum all values");
                        System.out.println("3 - print average value");
                        System.out.println("Enter - another number");
                        System.out.println("Make a choice");
                        int choice = scanner.nextInt();
                        System.out.println("Your choice : " + choice);
                        if (!getIntegerListFromFile.isDone()) {
                              System.out.println("File " + fileName + " is reading...");
                        }

                        switch (choice) {
                              case 1:
                                    getIntegerListFromFile.thenAccept(result -> {
                                          System.err.println("Result " + tmpCount + ", File : "  + fileName + "send to mail");
                                          System.err.println("Min value : " + result.get(0));
                                          System.err.println("Max value : " + result.get(result.size() - 1));
                                    });
                                    break;
                              case 2:
                                    getIntegerListFromFile.thenAccept(result -> {
                                          BigInteger sum = BigInteger.valueOf(0);
                                          for (Integer integer : result) {
                                                sum = sum.add(BigInteger.valueOf(integer));
                                          }
                                          System.err.println("Result " + tmpCount + ", File : "  + fileName + "send to mail");
                                          System.err.println("Sum  : " + sum);
                                    });
                                    break;
                              case 3:
                                    getIntegerListFromFile.thenAccept(result -> {
                                          BigInteger sum = BigInteger.valueOf(0);
                                          BigInteger averageValue = BigInteger.valueOf(0);
                                          for (Integer integer : result) {
                                                sum = sum.add(BigInteger.valueOf(integer));
                                          }
                                          averageValue = sum.divide(BigInteger.valueOf(result.size()));
                                          System.err.println("Result " + tmpCount + ", File : "  + fileName + "send to mail");
                                          System.err.println("Average value  : " + averageValue);
                                    });
                                    break;
                              default:
                                    System.out.println("Exit");
                                    return;
                        }
                        count++;
                        scanner.nextLine();
                  }
            }

      }
}
