package com.company;

public class Main {

    public static void main(String[] args) throws Throwable  {
        try {
            int valueMas = Integer.valueOf(args[0]), step = Integer.valueOf(args[1])-1;
             String res = "";

            int[] nums = new int[valueMas];

            for (int i = 0; i < valueMas; i++)
                nums[i] = i+1;

            int i = 0, len = nums.length - 1;

            do
            {
                res += nums[i];
                i += step;
                if (i > len)
                {
                    i = i - len - 1;
                }
            } while (nums[i] != 1);

            System.out.println(res);
        }
        catch (Throwable  e) {
            System.out.println(e);
        }
        finally {
            System.out.println("\nI'm finished");
        }
    }
}
