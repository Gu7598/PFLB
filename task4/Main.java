package com.company;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Throwable {
        try {
            FileReader path = new FileReader(args[0]);
            Scanner scan = new Scanner(path);
            int[] numbers = new int[0];
            int result = 0,check = 0, sr = 0, data;

            while (scan.hasNextLine()) {
                numbers = Arrays.copyOf(numbers, numbers.length+1);
                numbers[numbers.length-1] = Integer.parseInt(scan.nextLine());
            }

            Arrays.sort(numbers);

            for (var num: numbers) {
                if (num < 0)
                    check = 1;

                if (check == 1) {
                    if (num >= 0) {
                        sr = num;
                        break;
                    }
                } else {
                    data = IntStream.of(numbers).sum() % numbers.length;
                    if (data > numbers.length - data)
                        sr  = IntStream.of(numbers).sum() / numbers.length;
                    else
                        sr = IntStream.of(numbers).sum() / numbers.length + 1;
                }
            }

            for (var a: numbers) {
                if (a < sr)
                    result += sr - a;
                else
                    result += a - sr;
            }

            System.out.println(result);
        }
        catch (Throwable e) {
            System.out.println(e);
        }
        finally {
            System.out.println("\nI'm finished");
        }
    }
}
