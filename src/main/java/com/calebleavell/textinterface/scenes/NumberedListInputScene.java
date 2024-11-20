package com.calebleavell.textinterface.scenes;

/**
 * 
 * <p> NumberedListInputScene Class </p>
 * 
 * <p> Presents a list of items to choose from, then the user picks an option based
 * on the corresponding number </p>
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
 */
public class NumberedListInputScene extends GenericInputScene<Integer> {
    /**
     * The list of options
     */
    private NumberedListScene numberedList;

    /**
     * The user input collector
     */
    private TextInputScene inputGetter;

    /**
     * The actual user input
     */
    private Integer input;

    /**
     * @return The NumberedList of options
     */
    public NumberedListScene getNumberedList() {
        return numberedList;
    }

    /**
     * Set the NumberedList
     * @param numberedList The new NumberedList
     */
    public void setList(NumberedListScene numberedList) {
        this.numberedList = numberedList;
    }

    /**
     * @return The input collector, not the input itself
     */
    public TextInputScene getInputGetter() {
        return inputGetter;
    }

    /**
     * Set the input collector
     * @param inputGetter The new input collector
     */
    public void setInputGetter(TextInputScene inputGetter) {
        this.inputGetter = inputGetter;
    }

    /**
     * @return The user's input
     */
    @Override
    public Integer getInput() {
        return input;
    }

    /**
     * Execute the program in the following order:
     * 1. Numbered List
     * 2. Get Input
     * 3. Run super - runs children and functions
     */
    @Override
    public void run() throws Exception {
        numberedList.run();
        inputGetter.run();
        input = Integer.parseInt(inputGetter.getInput());

        super.run();
    }

    /**
     * Return a new instance based on the builder fields
     * @param builder The new NumberedListInputScene
     */
    public NumberedListInputScene(Builder builder) {
        super(builder);
        this.numberedList = builder.numberedList;
        this.inputGetter = builder.inputGetter;
    }

    /**
     * The Builder class
     */
    public static class Builder extends GenericInputScene.Builder<Integer, Builder> {

        /**
         * It defaults to the class-specified default for NumberedListScene and TextInputScene
         */
        private NumberedListScene numberedList = new NumberedListScene.Builder().build();
        private TextInputScene inputGetter = new TextInputScene.Builder().build();

        /**
         * @param list The list of options
         * @return self 
         */
        public Builder list(NumberedListScene list) {
            this.numberedList = list;
            return self();
        }

        /**
         * @param list The list of options (varargs)
         * @return self 
         */
        public Builder list(String... list) {
            this.numberedList = new NumberedListScene.Builder().list(list).build();
            return self();
        }

        /**
         * @param inputGetter The input collector
         * @return self 
         */
        public Builder inputGetter(TextInputScene inputGetter) {
            this.inputGetter = inputGetter;
            return self();
        }
        
        /**
         * Return a new instance of NumberedListScene with the specified fields
         */
        public NumberedListInputScene build() {
            return new NumberedListInputScene(this);
        }
    }
}
