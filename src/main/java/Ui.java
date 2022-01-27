/**
 * Utility class that deals with common messages and outputs to terminal.
 */
public class Ui {
    
    public static final String LINE = "----------------------------------------\n";
    public static final String GREETING_MESSAGE = "Wow! Hello! I'm Duke.\nWhat can I do for you?";
    public static final String UNKNOWN_COMMAND_MESSAGE = "OoPs! I don't know what that means :P";
    public static final String READ_WRITE_ERROR_MESSAGE =  "Something went wrong with the hard disk!";
    public static final String CORRUPTED_SAVE_MESSAGE = 
            "File is corrupted and Duke is unable to restore data from previous sessions.\n" 
            + "Resetting contents of save-file.";

    /**
     * Wraps messages generated by Duke with fancy text UI elements and prints them to console.
     * 
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println(chatBox(message));
    }

    /**
     * Wraps a given text in a box to be printed
     * 
     * @param givenText
     * @return
     */
    public static String chatBox(String givenText) {
        StringBuilder box = new StringBuilder();
        box.append(LINE);
        box.append(newLineAfter(givenText));
        box.append(LINE);

        return box.toString();
    }

    /**
     * Ends a given string with a '\n' character if it does not have one.
     * 
     * @param text The String to be modified.
     * @return The modified string.
     */
    public static String newLineAfter(String text) {
        if (text.charAt(text.length() - 1) != '\n') {
            return text + '\n';
        }
        
        return text;
    }

    /**
     * Concatenates 2 message separated by a blank line.
     * 
     * @param m1 The first message
     * @param m2 The second message
     * @return Concatenated message
     */
    public static String mergeMessages(String m1, String m2) {
        return newLineAfter(m1) + "\n" + m2;
    }
}