FILES
-----------------------------------------------------------------------------------------------------------

* ThreadedServer: The main server class

* ClientHandler: The client handeler

* Client: A basic client class with a switch case for user input

* Message: A message class with JSON serialisation / deserialisation

* Request: An abstract request class

* Response: An abstract response class

* OpenRequest: Establishes client's identity + creates a channel for the client

* PublishRequest: Publishes a message on the client's channel

* SubscribeRequest: Subscribes the client to the named channel

* UnsubscribeRequest: Unsubscribes the client from the named channel

* GetRequest: retrieves all messages that were published on any channel

* SuccessResponse: class that indicates that the request succeeded

* ErrorResponse: class indicates that the request was failed

* MessageListResponse: provides a list of messages


RUNNING THE SERVER
-----------------------------------------------------------------------------------------------------------

* Open a Windows command line or power shell.

* Change to the directory where the server.exe file resides.

* Starting the server: .\server

* Starting the server in debug mode: .\server -d

By default, the server listens on port 12345. (ignore the Windows firewall warning on startup.)

To stop the server, hit Ctrl-C in the server's command line window.


RUNNING THE CLIENT
-----------------------------------------------------------------------------------------------------------

* Open a Windows command line or power shell.

* Change to the directory where the client.exe file resides.

* Starting the client: .\client

By default, the client connects to a server running on localhost
and listening on port 12345.

To stop the client, hit Ctrl-C in the clien's command line window and ignore
the error messages.

Once the client is connected, it reads commands from the console.
Each command consists of a one letter word, followed by a white space and an argument.
Valid keywords are: 
	- "o"     to open a channel
	- "p"     to publish a message
	- "s"     to  subscribe to a channel
	- "u"     to unssubscribe from a channel
	- "g"     to get messages
Commands o, s and u expect a channel name as argument while p expects a message (string) and the command "g" expects no argument.

A different command than one of the above will show you an error message.

Sadly no GUI or additional extension were implemented in this project.


SAMPLE SESSION
-----------------------------------------------------------------------------------------------------------
S: The server has started, waiting for clients...
S: Connection accepted from client: 1
S: Connection accepted from client: 2

C1: o eddy
C1: ok
// created a channel names eddy as well

C2: o dirk
C2: ok
// created a channel names dirk as well

C1: p hello everyone
C1: ok
// Publishing a message

C2: p what's happening?
C2: ok
// Publishing a message

C2: s eddy
C2: ok
// dirk subscribed to eddy

C2: u eddy
C2: ok
// dirk unsubscribed from eddy

C1: g
C1: ok
// list of all the messages from his/her channel


