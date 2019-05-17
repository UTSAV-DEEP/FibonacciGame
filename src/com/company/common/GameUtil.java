package com.company.common;

import com.company.exceptions.Fibo2048Exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameUtil {

    private List<Long> fib = new ArrayList<>();

    public GameUtil() throws Fibo2048Exception {
        fib.add(0L);
        fib.add(1L);
        Long a = 0L, b = 1L, c;
        c = a + b;
        while (c > 0) {
            fib.add(c);
            a = b;
            b = c;
            c = a + b;
        }
        if(fib.size()+1<Constants.GAME_ROWS*Constants.GAME_COLUMNS){
            throw new Fibo2048Exception("Game size too large");
        }
    }


    public boolean isFibonacci(long n) throws Fibo2048Exception {
        int size = fib.size();
        if (n < 0 || n > fib.get(size - 1)) {
            throw new Fibo2048Exception("Number out of Range");
        }
        int idx = Arrays.binarySearch(fib.toArray(), n);
        return (idx >= 0 && idx < size && fib.get(idx) == n);
    }

    public void userWonCheck(long n) throws Fibo2048Exception {
        if(fib.get(Constants.GAME_ROWS*Constants.GAME_COLUMNS) == n){
            throw new Fibo2048Exception("You Won");
        }
    }
}
