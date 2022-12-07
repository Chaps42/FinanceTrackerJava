# FinanceTrackerJava
UI tool to mange and display finances


## OOAD5448: Project 7
by David Chaparro and Julia Kent

Java version: 17.0.1

--------------------------------

This project is a Java application for tracking, managing and displaying finances.

The user interacts with the application via a terminal to add/delete/update accounts or transactions.

The applicatin allows users to:
    - Record account balences overtime
    - Record transactions as reoccuring or one time
    - Save their records onto a file
    - Have interest rates applied to their accounts
    - Have transactionis automatically reoccur
    - View spending by category
    - View account balances over time


## How to Run

To run the game, execute `Main.java` within the project. The terminal will then prompt you to ...

If preferred, you may also create account or transaction information in a CSV file or spreadsheet which the application can read. Store files in the `user_data/` folder or the `user_data/accounts/` folder.

When prompted with a date, please enter the form of "01-Aug-2020"


## Demonstration

A demonstration of use and coding principles is saved in Here["Video Demonstration"](https://drive.google.com/file/d/18hG5BJmY6RLjHtQV4bEwTw_XCeYgscTp/view?usp=sharing)


## Identified OO Patterns

**Singleton Pattern** is used for the `Database`, the `Mapper`, and the `FileManager`.
All are lazy Singleton (waits till a thing is called and checks to see if it exists before returning itself).
This pattern ensures that there is only one instance of these classes which ensures nice memory usage and less room for errors.
This was particularly important for the `Database` (which stores all of the `Account` and `Transaction` data), and a convenience for the `Mapper` and `FileManager` (for which there is no need to have more than once since they store no attribute information).

**Builder Pattern** is used for `Accounts` and `Transactions`.
The Builder Pattern is a good alternative to passing in lots of arguments to an object's constructor.
Instead, we are able to sequentially call methods such as `addTransactionCategory()` in a way that passes in all of the same information to the final object, but makes it much more clear what each argument expects as input.
This was very appropriate for the `Account` class which has attributes: name, value, refords, interest rate information (rate, frequency, type, date last calculated). Similarly the `Transaction` class has a lot of Enums referring to if it recurs, if so, how often, and what category it is (as well as the typical name, date, amount, and account it comes out of or into).

**Data Mapper Pattern** is used for interacting with the `Database`.
Typical Data Mappers have functions for getting, searching, adding, and removing information from the Database.
Our `Mapper` class has methods that map to these purposes for Transactions and for Accounts.
There is search capability for Accounts or Transactions by name, by category, by type, and by one-time/recurring.
No other object interacts with the `Database` object, not even the `FileManger` (which too goes through the `Mapper`).

**Observer Pattern** is implemented by the mediator in order to keep a log of the
requests given to the Data handler facade and requests given to the UI. This way we have a
method of troubleshooting. Furthermore, if there is ever an update in the data, the observer will
notice a change and tell the UI to update the current displayed values. Both the data facade and
the UI facade will act as subjects for the observer, and the mediator/observer will record events
from both objects.

**Facade Pattern** is used to simplify connections to the UI and the Database. The
`DataFacade` is responsible for connecting with and returning various datatypes associated with
accounts and financial information. For instance, the facade will take a request to return us all
accounts that are considered assets, or debts. This allows us to access all the data without
going into our data structure to look for these objects ourselves. We will also have methods for
getting unique dates for display, and getting unique categories for use in the UI. The UI Facade has methods that will allow the UI to update the information that is
displayed. The data facade is largely a way for the UI to have a reference to the database,
because most commands will come from the UI itself.

**Mediator Pattern** acts as a bridge between the two facades. The mediator
is responsible for recording all of the messages passed between the two sides of the
application, and proves both facades with references to the other facade so they can send
messages across objects. There aren't many explicit methods in the mediator, as adding a
method for every single message would get complicated. By having the references to the two
facades allows us to connect any part of the application to the mediator so they can have
access to the data or UI.

**Command Pattern** acts as a way for the UI to interact with the database. The abstract command object acts as a template for all other commands, and each subclassed command contains the same execute function. The abstract command contains a reference to the Mediator, which in turn gives each subclassed command a reference to the data facade and the UI Facade, allowing for commands to control the program. The commands do not take the role of the mediator's job, and they frequently call getData() so the mediator can return the facade. 

**Strategy Pattern** acts as a way to specify the algorithms used when displaying information for the user. The abstract object GeneralList allows the creation of subclasses which treat the abstract commands differently. Each subclass such as AccountList and Transaction List fill out their own buildString method which allows them to create their UI. Each buildString method is different, but each UIClass is accessed by the UI facade to be controlled. In the UI Facades “updateUI()” method, the UI elements are iterated over polymorphically, thus the algorithm is changed just like the strategy pattern. 

## Changes to UML Diagram

There are many changes from our initial planning. During the planning phase, our understanding of the Builder Pattern was weak, so we had an abstract Builder class that our Transaction and Account Builders inherited from (though now it is clear that they share no methods or attributes, and only have their pattern in common).

We also simplified our objects a lot: there is no longer a Category class (this is handled by the Transaction's Enums), there are no longer separate OneTimeTransaction and RecurringTransaction classes (this is also handled by an Enum within Transaction), and there are no longer a Goal or Celebration classes (these were deemed a "nice to have" quality of the application that did not make it into our final project).

Other classes were more complicated (or more classes) than anticipated. The `Math` class, for instance, is now a series of classes (`CategoryMath`, `DateMath`, InterestMath`, 'RecurringTransactionMath`) because each one was large enough that it was worth making the code more navigate-able by separating them into their respective applications. Similarly the `Plotter` class became `LinePlotter` and `PiePlotter` because both classes use different libraries and imports. Both plotters have only one method call at this project iteration, but could easily be extended for more functionality (say comparing Account interest vs principal balance on a pie plot, or plotting category spending over time on a line plot). In both cases, the relationships shown on the UML are fairly unchanged.

For the UI, we instead did not complete a javaFX UI and decided to create our own text version. This resulted in us creating both a strategy pattern, and a Command patternt to help control the elements in the UI. This is different fromt the original JavaFX pattern because the Command and strategy pattern would have been encapsulated in the UI library. This way we were able to create our own framework for the UI and thus add more elements. 

The updated UML can be seen [here](FinanceTrackerClassDiagram_P7.drawio.png).

## Assumptions Made

Date math can get famously complicated. In some areas we were able to leverage existing Date libraries, but because it was hard to find the exact library best suited to each method (a lot of documentation that comes up is for deprecated libraries not available since Java8!), at one point when doing the math for recurring transactions, we assume that months are always 30 days and that years 365 days. This is accurate enough for this proof-of-concept application because our main priority, we assume, is to demonstrate patterns and class relationships, not to know how to handle February or leap years.

## Dependencies

This application uses the following libraries:
  - OpenCSV
  - XChart
  - JFreeChart

as well as many unlisted libraries that are included in Java 17.0.1 by default.

## Citations

Help with using XChart from [Knowm "XChart Example Code"](https://knowm.org/open-source/xchart/xchart-example-code/).

Help with JFreeChart from [Tutorials Point "JFreeChart Pie Chart"](https://www.tutorialspoint.com/jfreechart/jfreechart_pie_chart.htm).

Help with Writing to CSV files from [Baeldung "Open CSV"](https://www.baeldung.com/opencsv).

Help with Dates from [Java T Point "Get Current Date and Time in Java"](https://www.javatpoint.com/java-get-current-date) and [StackAbuse "How to Get the Number of Days Between Dates in Java"](https://stackabuse.com/how-to-get-the-number-of-days-between-dates-in-java/).

Help sorting Maps of Data from [Geeks for Geeks "How to Iterate Over a Treemap in Java"](https://www.geeksforgeeks.org/how-to-iterate-over-a-treemap-in-java/).

Help finding Current Directory ["How to get the current working directory in Java"](https://mkyong.com/java/how-to-get-the-current-working-directory-in-java/#:~:text=In%20Java%2C%20we%20can%20use,where%20your%20program%20was%20launched.)

Help creating a grid like formatter ["Class Formatter"](https://docs.oracle.com/javase/1.5.0/docs/api/java/util/Formatter.html#syntax)

Help fixing a scanner when skipping a line ["Why is Scanner skipping nextLine() after use of other next functions?"](https://www.geeksforgeeks.org/why-is-scanner-skipping-nextline-after-use-of-other-next-functions/)
