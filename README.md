This project contains an implementation of Work Queues with RabbitMQ library.

Requirements:
- Maven
- Java17 or higher
- RabbitMQ server running locally (Docker) or remotely
 
The Producer simulates the sending of an email to all listening workers in the queue email_queue, available on [localhost](http://localhost:15672/#/queues) 
When running the Producer class in the terminal, be sure to add an email subject and body as Strings in the command.
Once the email received, the Worker prints the Email object.

- The program is compiled and packaged using the collowing command: `mvn clean compile package`
- Make sure a packaged .jar file `DevoirRabbitMQ-1.0-SNAPSHOT.jar` is present under /target repository
- Use `java -cp target/DevoirRabbitMQ-1.0-SNAPSHOT.jar org.example.Worker` to run the worker (open as many terminals as workers being run separately)
- Use `java -cp target/DevoirRabbitMQ-1.0-SNAPSHOT.jar org.example.Producer "Subject" "Body"` to run the producer

![image](https://github.com/user-attachments/assets/61a71d28-3f6e-4600-af4d-b17096dd8d82)
![image](https://github.com/user-attachments/assets/5327586c-a20d-47d0-84ff-c33b00dad70a)
