# Text Interface

While GUIs are the most popular form of User Interface, there are many applications that are better suited for running purely in the terminal in a text-based format.
Possible reasons for this are:
- The program is simple and doesn't require a GUI
- The program is designed to run in an ecosystem that doesn't support GUIs
- The program requires efficient runtime that would be hindered by a GUI

However, building a text interface in native Java often leads to expansive, unscalable designs that slow down development.

This TextInterface library is designed to streamline the interface development process by providing a system that handles many of the repetitive tasks required by text interfaces.

This library focuses on:
- Acclerating devlopment by providing tools to quickly create text interfaces
- Improving the readability and maintainability of the codebase by providing a clean, modular approach to building text interfacces
- Allowing developers to design the way that is best suited for their system

The core unit of the library is the Scene. All scenes are built up using a Builder Design pattern. Every scene can also have child scenes, which are then attatched to the scene. Scenes can also have functions attatched to them.

To make the design as modular as possible, it is recommended to utilize reflection to access scene methods inside functions.
Note that this is optional, and it is possible to do develop without reflection. Reflection simply improves the readability of the code.

A [Demo Application](https://github.com/Caleb-Leavell/TextInterface/blob/main/src/main/java/com/calebleavell/textinterface/DemoApp.java) has been provided to show the recommended development style.
This Demo Application implements a simple random number generator that continuously takes a maximum number from the user, then displays the generated number. We will run through it here.

First, we create a new TextApplication. Here we see that the classes in this library utilize the Builder Design pattern. This gives the developer control over which fields
to initialize, as well as what order to initialize them in. Here, we don't need to initialize any fields immediately. 
We must also ensure that main throws Exception, as Scene functions also throw Exception.

```Java
public static void main(String[] args) throws Exception {
        //Create the app to house every scene
        TextApplication app = new TextApplication.Builder().build();
}
```

Next, we will create the scene for the Random Number Generator. This can essentially all be done at once, all stored in one main variable.

```Java
    /**
     * Having a ContainerScene as the base of the scene 
     * leads to improved modularity
     * 
     * Also note that, since the Builder Design Patter is utilized,
     * the developer has control over which fields to initialize
     * and what order to initialize them in
     */
    ContainerScene randomNumberGenerator = new ContainerScene.Builder()
            .name("random-number-generator")
            .children(
                    //title
                    new TextScene.Builder()
                            .text("\nRandom Number Generator\n")
                            .name("random-number-generator-title")
                            .build(),
                    //get input
                    new TextInputScene.Builder()
                            .displayText("Maximum Number (or -1 to quit): ")
                            .name("random-number-generator-input")
                            .functions(
                                    () -> {
                                            /**
                                             * A reflective method is used to return the child in a non-polymorphic type
                                             * This allows for the calling of class-specific methods
                                             * 
                                             * Note
                                             */
                                            TextInputScene input = app.getChild("random-number-generator-input", TextInputScene.class);
                                            TextScene output = app.getChild("random-number-generator-output", TextScene.class);
                                               
                                            /**
                                             * Note that a non-reflective version of the method is used here
                                             * This is technically more safe (although the reflective method is not unsafe, persay)
                                             * 
                                             * The reflective version is only necessary when we want to use class-specific methods
                                             */
                                            Scene rngScene = app.getChild("random-number-generator");

                                            //generate the random number
                                            int max = Integer.parseInt(input.getInput());
                                            
                                            //terminate if max is negative
                                            if(max <= 0) {
                                                    rngScene.terminate();
                                                    return;
                                            }

                                            int randomNumber = new java.util.Random().nextInt(max);
            
                                            //update the output to show the random number
                                            output.setText("Generated Number: " + randomNumber);
                                       
                                    }
                            )
                            .build(),
                    //display output
                    new TextScene.Builder()
                            .text("No number generated!") // default text
                            .name("random-number-generator-output")
                            .functions(
                                    () -> {
                                            Scene rngScene = app.getChild("random-number-generator");

                                            //reruns the scene until the user terminates it
                                            rngScene.run();
                                    }
                            )
                            .build())

            .build();
```
Note that, if the app was larger scale, it may be a good idea to split this up into multiple different ContainerScenes and adding them as children to a home, as readability may decline with more than two layers of children.


Finally, we add the scene as the home of the app and run.

```Java
    //Set the home of the app and run
    app.setHome(randomNumberGenerator);
    app.run();
```

Here is the output of the app:

![image](https://github.com/user-attachments/assets/fb6f9f39-9db6-40bf-82bd-2525158a9948)

