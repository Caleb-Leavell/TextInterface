package com.calebleavell.textinterface.scenes;

/**
 * Presents a list of items to choose from, then the user picks an option based on the corresponding number
 */
public class NumberedListInputScene extends GenericScene implements Inputtable<Integer> {
    private NumberedListScene numberedList;
    private TextInputScene inputGetter;
    private Integer input;

    public NumberedListScene getNumberedList() {
        return numberedList;
    }
    public void setList(NumberedListScene numberedList) {
        this.numberedList = numberedList;
    }
    

    public TextInputScene getInputGetter() {
        return inputGetter;
    }
    public void setInputGetter(TextInputScene inputGetter) {
        this.inputGetter = inputGetter;
    }

    @Override
    public Integer getInput() {
        return input;
    }

    @Override
    public void run() throws Exception {
        numberedList.run();
        inputGetter.run();
        input = Integer.parseInt(inputGetter.getInput());

        super.run();
    }

    public NumberedListInputScene(Builder builder) {
        super(builder);
        this.numberedList = builder.numberedList;
        this.inputGetter = builder.inputGetter;
    }

    public static class Builder extends GenericScene.Builder<Builder> {
        private NumberedListScene numberedList = new NumberedListScene.Builder().build();
        private TextInputScene inputGetter = new TextInputScene.Builder().build();

        public Builder list(NumberedListScene list) {
            this.numberedList = list;
            return self();
        }
        public Builder list(String...list) {
            this.numberedList = new NumberedListScene.Builder().list(list).build();
            return self();
        }
        public Builder inputGetter(TextInputScene inputGetter) {
            this.inputGetter = inputGetter;
            return self();
        }

        public NumberedListInputScene build() {
            return new NumberedListInputScene(this);
        }
    }
}
