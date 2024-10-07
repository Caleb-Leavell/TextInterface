package com.calebleavell.textinterface;

import com.calebleavell.textinterface.scenes.*;
import java.lang.reflect.Method;

/**
 * Hello world!
 */
public final class DemoApp {

    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {

        ContainerScene randomNumberGenerator = new ContainerScene.Builder()
                .name("random-number-generator")
                .children(
                        new TextScene.Builder()
                                .text("\nRandom Number Generator\n")
                                .name("random-number-generator-title").build(),
                        new TextInputScene.Builder()
                                .displayText("Maximum Number (or -1 to quit): ")
                                .name("random-number-generator-input").build(),
                        new TextScene.Builder()
                                .text("No number generated!") // default text
                                .name("random-number-generator-output").build())
                .build();

        randomNumberGenerator.getChildByName("random-number-generator-input").getFunctions().add(
                () -> {
                    Scene input = randomNumberGenerator.getChildByName("random-number-generator-input");
                    Scene output = randomNumberGenerator.getChildByName("random-number-generator-output");

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

        TextApplication RNGApp = new TextApplication.Builder().home(randomNumberGenerator).build();
        RNGApp.run();
    }

}
