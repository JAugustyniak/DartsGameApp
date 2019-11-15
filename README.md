# DartsGameApp
Java web application for a darts game system

## Built With

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Spring](https://spring.io/)
* [Hibernate](https://hibernate.org/)
* [JavaScript](https://www.javascript.com/)
* [MySql](https://www.mysql.com/)

## Authors
* Joanna Augustyniak  - [GitHub account](https://github.com/JAugustyniak)
* Mikołaj Gronowski - [GitHub account](https://github.com/MGroniu)

## Description
Functionalities:

* Selecting the number of players
![](numberofplayers.gif)


* Giving nicknames to players
![](enternickname.gif)

* Each player has three throws in a round
![](addpoints.gif)

* For a player to win it must reach 301 points
* If 301 points are passed by the player, the queue falls on the next player
* The winners table shows all players' previous wins and the round in which they won
* In the table you can also choose 1, 5, 10 or 15 recent winners
* By using the show button it is possible to view the history of the match in which the player won

All results are saved to the MySQL database
The values entered by the user are validated.

November 2019.
