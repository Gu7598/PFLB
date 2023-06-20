package com.company;

import java.io.FileReader;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Throwable {
        try {

            String[] circle_info = new String[0],
                    point_info = new String[0];
            FileReader path_circle = new FileReader(args[0]),
                    path_point = new FileReader(args[1]);
            Scanner scan_circle = new Scanner(path_circle),
                    scan_point = new Scanner(path_point);

            while (scan_circle.hasNextLine()) {
                circle_info = Arrays.copyOf(circle_info, circle_info.length + 1);
                circle_info[circle_info.length-1] = scan_circle.nextLine();
            }

            while (scan_point.hasNextLine()) {
                point_info = Arrays.copyOf(point_info, point_info.length + 1);
                point_info[point_info.length-1] = scan_point.nextLine();
            }

            String[] circle_center = circle_info[0].split(" "),
                    point_xy = new String[2];
            Double d;

            for (var point : point_info) {
                point_xy = point.split(" ");
                d = Math.pow(Math.pow(Double.parseDouble(point_xy[0]) - Double.parseDouble(circle_center[0]), 2) +
                        Math.pow(Double.parseDouble(point_xy[1]) - Double.parseDouble(circle_center[1]), 2), 0.5);
            
                if (d == Double.parseDouble(circle_info[1]))
                    System.out.println(0);
                else if (d < Double.parseDouble(circle_info[1]))
                    System.out.println(1);
                else
                    System.out.println(2);
            }
          
        }
        catch (Throwable e) {
            System.out.println(e);
        }
       
        finally {
            System.out.println("\nI'm finished");
        }
       
    }
}
