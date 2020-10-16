package ru.mail.techpark.android;

import android.os.Bundle;

class NumbersSource {

    private static final NumbersSource ourInstance = new NumbersSource();
    private int numbers;

    private NumbersSource() {
        numbers = 100;
    }

    static NumbersSource getInstance() {
        return ourInstance;
    }

    //get item with array-based indexing
    public NumberModel getItem(int index) {
        int number = index + 1;
        int color;
        if (number % 2 == 0)
            color = R.color.red;
        else
            color = R.color.blue;
        return new NumberModel(number,color);
    }

    public int getSize() {
        return numbers;
    }

    public void addItems(int numOfElements) {
        numbers += numOfElements;
    }

    public static class NumberModel {
        public static final String NUMBER = "MODEL_NUMBER";
        public static final String COLOR = "MODEL_COLOR";
        int mColorId;
        int mNumber;

        public NumberModel(int number, int colorId) {
            this.mColorId = colorId;
            this.mNumber = number;
        }

        public int getNumber() {
            return mNumber;
        }

        public int getColorId() {
            return mColorId;
        }

        //put this model to Bundle
        public void putToBundle(Bundle bundle){
            bundle.putInt(NUMBER,getNumber());
            bundle.putInt(COLOR, getColorId());
        }

        //get this model from Bundle
        public static NumberModel getFromBundle(Bundle bundle){
            int number = bundle.getInt(NUMBER);
            int color = bundle.getInt(COLOR);
            return new NumberModel(number,color);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NumberModel number);
    }
}
