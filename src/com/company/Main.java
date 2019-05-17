package com.company;

import com.company.common.Direction;
import com.company.exceptions.Fibo2048Exception;
import com.company.services.GameOperations;
import com.company.services.impl.GameOperationsImpl;

import java.util.Scanner;

public class Main {

    private static void playFibo2048() throws Fibo2048Exception {
        GameOperations gameOperations = new GameOperationsImpl();

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while(!input.equals("exit")){
            Direction direction = Direction.getValue(input);
            if(null != direction){
                gameOperations.move(direction);
            }
            input = sc.next();
        }
    }

    public static void main(String[] args) {
        try {
            playFibo2048();
        } catch (Fibo2048Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
