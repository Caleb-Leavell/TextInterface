package com.calebleavell.textinterface;

import com.calebleavell.textinterface.scenes.*;

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
         * Calculate the random number from the given input and store it in
         * this variable
         */
        InputListener<String> randomNumber = new InputListener<String>(input -> {
                int max;

                try {
                        max = Integer.parseInt(input);
                }
                catch(NumberFormatException e) {
                        return "Error: Invalid Number!";
                }

                //terminate if max is negative
                if(max <= 0) {
                        app.terminate();
                        System.out.println("Exiting...");
                        return null;
                }

                //get random number
                Integer output = new java.util.Random().nextInt(max);      
                
                return "Generated Number: " + output.toString();
        });

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

                        //Generate the random number
                        new TextInputScene.Builder()
                                .displayText("\nMaximum Number (or -1 to quit): ")
                                .name("random-number-generator-input")
                                .addListener(randomNumber)
                                /**
                                 * These are added as children so that, whenever we jump 
                                 * to the input, the output and
                                 * selector are also run
                                 */
                                .children(
                                //display output
                                new TextScene.Builder()
                                        .text(randomNumber)
                                        .name("random-number-generator-output")
                                        .children(
                                                
                                        )
                                        .build(),
                                //ask to exit or terminate
                                new NumberedSceneSelectorScene.Builder()
                                        .name("random-number-generator-selector")
                                        .listText("Generate another number", "Exit")
                                        .sceneList(app, "random-number-generator-input", "terminate")
                                        .build()                                        
                                )
                                .build(),

                        //terminate
                        new TextScene.Builder()
                                .name("terminate")
                                .text("Exiting...")
                                .functions(() -> app.terminate())
                                .build())

                .build();


        //Set the home of the app and run
        app.setHome(randomNumberGenerator);
        app.run();
    }

}
