This project contains an implementation of Work Queues with RabbitMQ library.

Requirements:
- Maven
- Java17 or higher
- RabbitMQ server running locally (Docker) or remotely
- 
The Producer simulates the sending of an email to all listening workers in the queue email_queue, available on [localhost](http://localhost:15672/#/queues) 
When running the Producer class in the terminal, be sure to add an integer that will signal the number of dots anticipated by the Worker, telling it how many seconds it will have to sleep for.
This symbolizes the task that the Worker has been told to do by the Producer. If no integer is given, the Worker finishes its job.

- The program is compiled and packaged using the collowing command: `mvn clean compile package`
- Make sure a packaged .jar file `DevoirRabbitMQ-1.0-SNAPSHOT.jar` is present under /target repository
- Use `java -cp target/DevoirRabbitMQ-1.0-SNAPSHOT.jar org.example.Worker` to run the worker (open as many terminals as workers being run separately)
- Use `java -cp target/DevoirRabbitMQ-1.0-SNAPSHOT.jar org.example.Producer 2` to run the producer, using any integer as an argument for the class
