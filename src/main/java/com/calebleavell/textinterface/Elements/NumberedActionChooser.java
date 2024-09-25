package com.calebleavell.textinterface.Elements;

import java.util.List;
import java.util.ArrayList;

public class NumberedActionChooser extends Scene{
    NumberedList choiceList;
    TextInput userInput;
    List<Runnable> events;
    Integer maxRetries;

    public NumberedActionChooser(NumberedList choiceList, TextInput userInput, List<Runnable> events, Integer maxRetries, String name, List<Scene> children, final String ID) {
        super(name, children, ID);

        if(choiceList == null) this.choiceList = new NumberedList();
            else this.choiceList = choiceList;

        if(userInput == null) this.userInput = new TextInput("Enter the Number of Your Choice: ", null, null, null);
            else this.userInput = userInput;

        if(events == null) this.events = new ArrayList<Runnable>();
            else this.events = events;

        if(maxRetries == null) this.maxRetries = 3;
            else this.maxRetries = maxRetries;

        if(choiceList.getElements().size() != events.size()) {
            throw new IndexOutOfBoundsException("There must be one choice for every event, and vice versa");
        }
    }

    @Override
    public void display() {
        choiceList.display();
        userInput.display();

        //ensure proper input
        int i = 0;
        while (Integer.parseInt(userInput.getInput()) >= events.size()) {
            if(i > maxRetries) {
                System.out.println("Max Retries Reached, Returning...");
                return;
            }
            System.out.println("Invalid Input, Retrying...");
            userInput.display();

            i ++;
        }
    
        events.get(Integer.parseInt(userInput.getInput())).run();
    }
}
