package utils;

public class RandomNumberGenerator {

    public static int generateOneNum(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

}
