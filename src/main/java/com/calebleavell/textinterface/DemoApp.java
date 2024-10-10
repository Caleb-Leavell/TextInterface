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

        /**
         * It is recommended that all scenes be placed inside a ContainerScene
         * for improved modularity
         */
        ContainerScene randomNumberGenerator = new ContainerScene.Builder()
                .name("random-number-generator")
                .children(
                        //title
                        new TextScene.Builder()
                                .text("\nRandom Number Generator\n")
                                .name("random-number-generator-title").build(),
                        //get input
                        new TextInputScene.Builder()
                                .displayText("Maximum Number (or -1 to quit): ")
                                .name("random-number-generator-input").build(),
                        //display output
                        new TextScene.Builder()
                                .text("No number generated!") // default text
                                .name("random-number-generator-output").build())
                .build();

        /**
         * Add a function to the input,
         * that updates the output to display the random number
         */
        randomNumberGenerator.getChildByName("random-number-generator-input").getFunctions().add(
                () -> {
                    Scene input = randomNumberGenerator.getChildByName("random-number-generator-input");
                    Scene output = randomNumberGenerator.getChildByName("random-number-generator-output");

                    /**
                     * Reflection is used to call class-specific methods not provided by the Scene interface
                     * 
                     * Note that using methods not belonging to a class is not caught at compile-time
                     * so it is important to ensure you are only using the proper methods
                     * 
                     * However, the benefit outweighs the cost as now this function is completely bound to the scene
                     */
                    Method getInput = input.getClass().getDeclaredMethod("getInput");
                    Method setText = output.getClass().getDeclaredMethod("setText", String.class);

                    Integer userInput = Integer.parseInt(getInput.invoke(input).toString());

                    if (userInput < 0) {
                        randomNumberGenerator.terminate();
                        return;
                    }

                    int randomNumber = new java.util.Random().nextInt(userInput);
                    setText.invoke(output, "Generated Number: " + randomNumber);
                    output.run();
                    randomNumberGenerator.run();
                });

        /**
         * Technically, using the TextApplication class is not required
         *
         * However, it makes the code more readable and clear
         */
        TextApplication RNGApp = new TextApplication.Builder().home(randomNumberGenerator).build();
        RNGApp.run();
    }

}
