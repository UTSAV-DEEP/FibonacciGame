package com.company.services;

import com.company.common.Direction;
import com.company.exceptions.Fibo2048Exception;

public interface GameOperations {
    public void move(Direction direction) throws Fibo2048Exception;
    public String[] collapseLine(String []line) throws Fibo2048Exception;
}
