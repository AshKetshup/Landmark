package com.ashketshup.TUI;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Input {
    /**
     * Request input as a string.
     *
     * @return the string
     */
    public static String asString() throws IOException{
        return new Scanner(System.in).nextLine();
    }

    public static int asInt() throws IOException {
        return Integer.parseInt(asString().trim());
    }

    public static double asDouble() throws IOException {
        return Double.parseDouble(asString().trim());
    }

    public static float asFloat() throws IOException {
        return Float.parseFloat(asString().trim());
    }

    public static boolean asBoolean() throws IOException {
        return Boolean.parseBoolean(asString().trim());
    }

    public static char asChar() throws IOException {
        return asString().charAt(0);
    }

    public static byte asByte() throws IOException {
        return Byte.parseByte(asString().trim());
    }

    public static short asShort() throws IOException {
        return Short.parseShort(asString().trim());
    }

    public static long asLong() throws IOException {
        return Long.parseLong(asString().trim());
    }

    public static String tryAsString() {
        String s = "";

        try {
            s = asString();
        } catch (IOException e) {
            Alarms.createWarning("Error while reading string from buffer.");
        }

        return s;
    }

    public static int tryAsInt() {
        while (true) {
            try {
                return asInt();
            } catch (IOException e) {
                Alarms.createWarning("Error while reading integer from buffer.");
            }
        }
    }

    public static double tryAsDouble() {
        while (true) {
            try {
                return asDouble();
            } catch (IOException e) {
                Alarms.createWarning("Error while reading double from buffer.");
            }
        }
    }

    public static float tryAsFloat() {
        while (true) {
            try {
                return asFloat();
            } catch (IOException e) {
                Alarms.createWarning("Error while reading float from buffer.");
            }
        }
    }

    public static boolean tryAsBoolean() {
        while (true) {
            try {
                return asBoolean();
            } catch (IOException e) {
                Alarms.createWarning("Error while reading boolean from buffer.");
            }
        }
    }

    public static char tryAsChar() {
        while (true) {
            try {
                return asChar();
            } catch (IOException e) {
                Alarms.createWarning("Error while reading char from buffer.");
            }
        }
    }

    public static byte tryAsByte() {
        while (true) {
            try {
                return asByte();
            } catch (IOException e) {
                Alarms.createWarning("Error while reading byte from buffer.");
            }
        }
    }

    public static short tryAsShort() {
        while (true) {
            try {
                return asShort();
            } catch (IOException e) {
                Alarms.createWarning("Error while reading short from buffer.");
            }
        }
    }

    public static long tryAsLong() {
        while (true) {
            try {
                return asLong();
            } catch (IOException e) {
                Alarms.createWarning("Error while reading short from buffer.");
            }
        }
    }

    private static class EraserThread implements Runnable {
        private boolean stop;

        /**
         * @param prompt  the prompt displayed to the user
         */
        public EraserThread(String prompt) {
            Output.write(prompt + "  ");
        }

        /**
         * Begin masking... display asterisks (*)
         */
        public void run () {
            stop = true;
            while (stop) {
                Output.write("\010*");
                try {
                    Thread.currentThread().sleep(1);
                } catch(InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

        public void stopMasking() {
            this.stop = false;
        }
    }

    public static String readString(String prompt) {
            Output.write(prompt + "  ");

            return tryAsString();
        }
        public static Integer readInt(String prompt) {
            Output.write(prompt + "  ");

            return tryAsInt();
        }
        public static Float readFloat(String prompt) {
            Output.write(prompt + "  ");

            return tryAsFloat();
        }
        public static Double readDouble(String prompt) {
            Output.write(prompt + "  ");

            return tryAsDouble();
        }
        public static Boolean readBoolean(String prompt) {
            Output.write(prompt + "  ");

            return tryAsBoolean();
        }

    public static String readHidden(String prompt) {
        EraserThread eraserThread = new EraserThread(prompt);
        Thread mask = new Thread(eraserThread);

        mask.start();
        String password = tryAsString();
        eraserThread.stopMasking();

        return password;
    }
}
