public class Lab2Variant15 {

    public static void main(String[] args) {
        StringBuilder text = new StringBuilder("This is a simple test, providing some data for the lab task.");
        int targetLength = 4;
        String replacement = "CODE";

        System.out.println("Original text: " + text);
        System.out.println("Target length: " + targetLength);
        System.out.println("Replacement: " + replacement);
        System.out.println("--------------------------------------------------");

        try {
            int i = 0;
            while (i < text.length()) {
                if (Character.isLetterOrDigit(text.charAt(i))) {
                    int start = i;
                    while (i < text.length() && Character.isLetterOrDigit(text.charAt(i))) {
                        i++;
                    }
                    int end = i;
                    int wordLength = end - start;

                    if (wordLength == targetLength) {
                        text.replace(start, end, replacement);
                        i = start + replacement.length();
                    }
                } else {
                    i++;
                }
            }

            System.out.println("Result text:   " + text);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}