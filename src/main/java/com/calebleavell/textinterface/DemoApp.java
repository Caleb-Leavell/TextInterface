package com.calebleavell.textinterface;

import com.calebleavell.textinterface.scenes.*;
import java.lang.reflect.Method;

/**
 * This is a simple random number generator. The user inputs the maximum number they want
 * generated, and the program provides a random integer smaller than that number
 * (and greather than 0)
 */
public final class DemoApp {

    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {

        //Create the app to house every scene
        TextApplication app = new TextApplication.Builder().build();

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
                                                   
                                                //generate the random number
                                                int max = Integer.parseInt(input.getInput());
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
                                                /**
                                                 * Note that a non-reflective version of the method is used here
                                                 * This is technically more safe (although the reflective method is not unsafe, persay)
                                                 * 
                                                 * The reflective version is only necessary when we want to use class-specific methods
                                                 */
                                                Scene rngScene = app.getChild("random-number-generator");

                                                //reruns the scene until the user terminates it
                                                rngScene.run();
                                        }
                                )
                                .build())

                .build();


        //Set the home of the app and run
        app.setHome(randomNumberGenerator);
        app.run();
    }

}
