package com.finance_tracker;

import com.finance_tracker.backendlogic.Mediator;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Mediator mainMediator = new Mediator();
        mainMediator.run();


    }
}
