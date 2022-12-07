package com.finance_tracker;

import java.io.IOException;

import com.finance_tracker.backendlogic.Mediator;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        Mediator mainMediator = new Mediator();
        mainMediator.run();


    }
}
