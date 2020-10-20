package ru.mail.techpark.android;

import android.os.Bundle;

public final class NumbersSource {

    private static final NumbersSource OUR_INSTANCE = new NumbersSource();
    public static final String DATA_SIZE = "NUMBERS_SIZE";
    private int mNumbers;

    private NumbersSource() {
        setSize(100);
    }

    static NumbersSource getInstance() {
        return OUR_INSTANCE;
    }

    public static void onSave(Bundle bundle){
        bundle.putInt(DATA_SIZE,getInstance().getSize());
    }


    public static void onRestore(Bundle bundle){
        int size = bundle.getInt(DATA_SIZE);
        getInstance().setSize(size);
    }

    //get item with array-based indexing
    public NumberEntity getItem(int index) {
        int number = index + 1;
        int color;
        if (number % 2 == 0)
            color = R.color.red;
        else
            color = R.color.blue;
        return new NumberEntity(number,color);
    }

    private void setSize(int size){
        mNumbers = size;
    }

    public int getSize() {
        return mNumbers;
    }

    public void addItems(int numOfElements) {
        mNumbers += numOfElements;
    }

    public static class NumberEntity {
        public static final String NUMBER = "ENTITY_NUMBER";
        public static final String COLOR = "ENTITY_COLOR";
        int mColorId;
        int mNumber;

        public NumberEntity(int number, int colorId) {
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
        public static NumberEntity getFromBundle(Bundle bundle){
            int number = bundle.getInt(NUMBER);
            int color = bundle.getInt(COLOR);
            return new NumberEntity(number,color);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NumberEntity numberEntity);
    }
}
