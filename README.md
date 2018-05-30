# chitter
Software engineering exercise: Build a console-based social networking application.
### Exercise instructions
Implement a console-based social networking application (similar to [Twitter](https://twitter.com)) satisfying the scenarios below.
## Desired features of the application
#### Posting: Alice can publish messages to a personal timeline.
```
> Alice -> I love the weather today
> Bob -> Damn! We lost!
> Bob -> Good game though.
```
#### Reading: I can view Alice and Bob’s timelines.
```
> Alice
I love the weather today (5 minutes ago)
> Bob
Good game though. (1 minute ago)
Damn! We lost! (2 minutes ago)
```
#### Following: Charlie can subscribe to Alice’s and  Bob’s timelines, and view an aggregated list of all subscriptions.
```
> Charlie -> I'm in New York today! Anyone want to have a coffee?
> Charlie follows Alice
> Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
Alice - I love the weather today (5 minutes ago)
> Charlie follows Bob
> Charlie wall
Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
Bob - Good game though. (1 minute ago)
Bob - Damn! We lost! (2 minutes ago)
Alice - I love the weather today (5 minutes ago)
```
## Further instructions
* The application must use the console for input and output.
* Users submit commands to the application. There are four commands.
* “posting”, “reading”, etc. are not part of the commands; commands always start with the user’s name.

| Feature        | Command                                 |
| -------------- | --------------------------------------- |
| **posting**    | `user name` -> `message`                |
| **reading**    | `user name`                             |
| **following**  | `user name` follows `another user`      |
| **wall**       | `user name` wall                        |

* Handling of exceptions or invalid commands can be neglected for this exercise. It can be assumed that the user will
always type the correct commands (sunny day scenario).
* The application need not work over a network or across processes. For the purpose of this exercise, it can all be done in memory, assuming that users will all use the same terminal.
* Non-existing users should be created as they post their first message. The application should not start with a pre-defined list of users.
## Instructions on how to run the application
System prerequisites: [Java SE Runtime Environment 8 or higher](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

With an installed JRE (Java Runtime Environment), please navigate to the provided directory called 'run' and execute the following command on the command-line shell, sometimes called the command prompt or the terminal:
```
java -jar chitter.jar
```
For a deeper code analysis and a review of the test suites, please consider firing up your favourite IDE. The project should be easy to rebuild and repackage using [Apache Maven](https://maven.apache.org/download.cgi) as an automated build tool.
