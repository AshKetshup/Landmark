package com.ashketshup.Landmark.TUI;

import java.io.IOException;
import java.util.Scanner;

/**
 * The type Input.
 */
public class Input {
    /**
     * Request input as a string.
     *
     * @return the string
     * @throws IOException the IOException
     */
    public static String asString() throws IOException{
        return new Scanner(System.in).nextLine();
    }

    /**
     * As int int.
     *
     * @return the int
     * @throws IOException the IOException
     */
    public static int asInt() throws IOException {
        return Integer.parseInt(asString().trim());
    }

    /**
     * As double double.
     *
     * @return the double
     * @throws IOException the IOException
     */
    public static double asDouble() throws IOException {
        return Double.parseDouble(asString().trim());
    }

    /**
     * As float float.
     *
     * @return the float
     * @throws IOException the IOException
     */
    public static float asFloat() throws IOException {
        return Float.parseFloat(asString().trim());
    }

    /**
     * As boolean boolean.
     *
     * @return the boolean
     * @throws IOException the IOException
     */
    public static boolean asBoolean() throws IOException {
        return Boolean.parseBoolean(asString().trim());
    }

    /**
     * As char char.
     *
     * @return the char
     * @throws IOException the IOException
     */
    public static char asChar() throws IOException {
        return asString().charAt(0);
    }

    /**
     * As byte byte.
     *
     * @return the byte
     * @throws IOException the IOException
     */
    public static byte asByte() throws IOException {
        return Byte.parseByte(asString().trim());
    }

    /**
     * As short short.
     *
     * @return the short
     * @throws IOException the IOException
     */
    public static short asShort() throws IOException {
        return Short.parseShort(asString().trim());
    }

    /**
     * As long long.
     *
     * @return the long
     * @throws IOException the IOException
     */
    public static long asLong() throws IOException {
        return Long.parseLong(asString().trim());
    }

    /**
     * Try as string string.
     *
     * @return the string
     */
    public static String tryAsString() {
        String s = "";

        try {
            s = asString();
        } catch (IOException e) {
            Notifications.createWarning("Error while reading string from buffer.");
        }

        return s;
    }

    /**
     * Try as int int.
     *
     * @return the int
     */
    public static int tryAsInt() {
        while (true) {
            try {
                return asInt();
            } catch (IOException e) {
                Notifications.createWarning("Error while reading integer from buffer.");
            }
        }
    }

    /**
     * Try as double double.
     *
     * @return the double
     */
    public static double tryAsDouble() {
        while (true) {
            try {
                return asDouble();
            } catch (IOException e) {
                Notifications.createWarning("Error while reading double from buffer.");
            }
        }
    }

    /**
     * Try as float float.
     *
     * @return the float
     */
    public static float tryAsFloat() {
        while (true) {
            try {
                return asFloat();
            } catch (IOException e) {
                Notifications.createWarning("Error while reading float from buffer.");
            }
        }
    }

    /**
     * Try as boolean boolean.
     *
     * @return the boolean
     */
    public static boolean tryAsBoolean() {
        while (true) {
            try {
                return asBoolean();
            } catch (IOException e) {
                Notifications.createWarning("Error while reading boolean from buffer.");
            }
        }
    }

    /**
     * Try as char char.
     *
     * @return the char
     */
    public static char tryAsChar() {
        while (true) {
            try {
                return asChar();
            } catch (IOException e) {
                Notifications.createWarning("Error while reading char from buffer.");
            }
        }
    }

    /**
     * Try as byte byte.
     *
     * @return the byte
     */
    public static byte tryAsByte() {
        while (true) {
            try {
                return asByte();
            } catch (IOException e) {
                Notifications.createWarning("Error while reading byte from buffer.");
            }
        }
    }

    /**
     * Try as short short.
     *
     * @return the short
     */
    public static short tryAsShort() {
        while (true) {
            try {
                return asShort();
            } catch (IOException e) {
                Notifications.createWarning("Error while reading short from buffer.");
            }
        }
    }

    /**
     * Try as long long.
     *
     * @return the long
     */
    public static long tryAsLong() {
        while (true) {
            try {
                return asLong();
            } catch (IOException e) {
                Notifications.createWarning("Error while reading short from buffer.");
            }
        }
    }

    /**
     * Read string string.
     *
     * @param prompt the prompt
     * @return the string
     */
    public static String readString(String prompt) {
            Output.write(prompt + "  ");

            return tryAsString();
        }

    /**
     * Read int integer.
     *
     * @param prompt the prompt
     * @return the integer
     */
    public static Integer readInt(String prompt) {
        Output.write(prompt + "  ");

        return tryAsInt();
    }

    /**
     * Read float float.
     *
     * @param prompt the prompt
     * @return the float
     */
    public static Float readFloat(String prompt) {
        Output.write(prompt + "  ");

        return tryAsFloat();
    }

    /**
     * Read double double.
     *
     * @param prompt the prompt
     * @return the double
     */
    public static Double readDouble(String prompt) {
        Output.write(prompt + "  ");

        return tryAsDouble();
    }

    /**
     * Read boolean boolean.
     *
     * @param prompt the prompt
     * @return the boolean
     */
    public static Boolean readBoolean(String prompt) {
            Output.write(prompt + "  ");

            return tryAsBoolean();
        }

    /**
     * Read hidden string.
     *
     * @param prompt the prompt
     * @return the string
     */
    public static String readHidden(String prompt) {
        return String.valueOf(System.console().readPassword());
    }
}
