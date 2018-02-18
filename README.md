#Movie Theater Manager

* [Short description](#short-descr)
* [How to run and test](#quick-install)


## Short description
Application allows to manage creating Events in different Auditoriums.
You can:
* create Event
* create Auditoriums for Event
* create a pool of seats and VIP seats in chosen Auditorium
* book a set of tickets
* for sure you you can rate a movie
* prolong your Event for multiple days and etc. 

As a manager of Movie Theater Manager you can manipulate all this data.
For sure, all operations are verified and validated when they occur.  

As a user you can add new Event and setup the following details:
* set a list of dates (airDates) with Auditoriums for planning Events 
* set a rating for this Event: high, medium, low
* base price for a ticket

Also you have the opportunity to manipulate Auditoriums by adding them with setting:
* name
* number of seats
* VIP seats

Each potential User with name, email and id can book a ticket on Event using BookingService with following API:
* remove
* getUserByEmail
* save

We have some additional service DiscountService for getting discount during purchases.

## Spring Core

###HW1

1. __DONE__ Add_UserServiceImpl, UserRepository interface and memory implementation
2. __DONE__ Add EventServiceImpl, EventRepository interface and memory implementation
3. __DONE__ All services should be provided by tests

###HW2
1. __DONE__ Add AuditoriumServiceImpl, AuditoriumRepository and memory implementation
2. __DONE__ Add BookingServiceImpl, TicketRepository and memory implementation
3. __DONE__ Add simple DiscountServiceImpl. 10% for buying more 10 tickets, 20% for 20 tickets and 30% for more than 50 tickets. At the next versions (HW3) Discount service will be improved by strategies
4. __DONE__ All services should be provided by tests
5. __IN_PROGRESS__ Provide Command Line Interface for adding entities and reading all entities
6. __DONE__ Replace Xml Spring configuration by AppConfig as a Spring Configuration annotated class
7. __DONE__ Add CounterAspect and provide tests
8. __DONE__ Add DiscountAspect and provide tests
9. __DONE__ Add LuckyWinnerAspect and provide tests

###HW3
1. __DONE__ Fix Discount service logic by adding two strategies: TicketCountStrategy and BirthdayStrategy
3. __DONE__ Fix pointcuts in Aspects. Now they targets interfaces
4. __DONE__ Fix DiscountAspect by calculating how many times each discount was given total and for user
5. __DONE__ Fix Booking service. Total price corrected: price depends on EventRating and vipSeat
6. __DONE__ Add DAO implementations that uses JdbcTemplate
7. __DONE__ Add sql scripts for initialization and populating DB
8. __DONE__ Implement DAO object to store all Aspect counters into the database

####How to test
1. install maven
2. go to project folder by "cd" command in Terminal (Mac) or any Command Line Commander (Windows)
3. mvn clean test

##Spring Advanced
###HW1

1. __DONE__ Configure dispatcher servlet and Spring MVC Context
2. __DONE__ Implement UserController and all necessary ftl views
3. __DONE__ Implement EventController and all necessary ftl views
4. __DONE__ Implement AuditoriumController and all necessary ftl views
5. __DONE__ Implement BookingController and all necessary views
6. __DONE__ Implement generic Exception handler (ExceptionController)
7. __DONE__ Implement download PDF link for getting list of tickets
8. __DONE__ Provide multipart upload file (users, events)



## How to run and test

__Option__ (FAST)
1. install maven
2. go to project folder
3. mvn tomcat7:run
4. go http://localhost:8080/movie

__Option__ (LONG)
1. install maven
2. install tomcat9
3. go to tomcat9/bin folder
4. using Command Line execute catalina.bat start
5. go to project folder
6. execute command: mvn clean package
7. alternatively for ignoring tests execute: mvn clean package -DskipTests
8. put generated project/target/movie.war file in tomcat9/webapps folder
9. open url in browser: http://localhost:8080/movie/
10. have fun by creating and modifying users, events and tickets
