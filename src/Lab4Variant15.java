import java.util.ArrayList;
import java.util.List;

class Letter {
    private char value;

    public Letter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

class Punctuation {
    private String value;

    public Punctuation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

class Word {
    private List<Letter> letters;

    public Word(String wordString) {
        letters = new ArrayList<>();
        for (char c : wordString.toCharArray()) {
            letters.add(new Letter(c));
        }
    }

    public int length() {
        return letters.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter letter : letters) {
            sb.append(letter.getValue());
        }
        return sb.toString();
    }
}

class Sentence {
    private List<Object> sentenceElements;

    public Sentence() {
        sentenceElements = new ArrayList<>();
    }

    public void addElement(Object element) {
        sentenceElements.add(element);
    }

    public void replaceWords(int length, String replacement) {
        for (int i = 0; i < sentenceElements.size(); i++) {
            Object element = sentenceElements.get(i);
            if (element instanceof Word) {
                Word word = (Word) element;
                if (word.length() == length) {
                    sentenceElements.set(i, new Word(replacement));
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object element : sentenceElements) {
            sb.append(element.toString());
        }
        return sb.toString();
    }
}

class Text {
    private List<Sentence> sentences;

    public Text(String rawText) {
        sentences = new ArrayList<>();
        parseText(rawText);
    }

    private void parseText(String rawText) {
        String cleanedText = rawText.replaceAll("[\\t\\s]+", " ");

        Sentence currentSentence = new Sentence();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < cleanedText.length(); i++) {
            char c = cleanedText.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                currentWord.append(c);
            } else {
                if (currentWord.length() > 0) {
                    currentSentence.addElement(new Word(currentWord.toString()));
                    currentWord.setLength(0);
                }

                currentSentence.addElement(new Punctuation(String.valueOf(c)));

                if (c == '.' || c == '!' || c == '?') {
                    sentences.add(currentSentence);
                    currentSentence = new Sentence();

                    if (i + 1 < cleanedText.length() && cleanedText.charAt(i + 1) == ' ') {
                        i++;
                    }
                }
            }
        }

        if (currentWord.length() > 0) {
            currentSentence.addElement(new Word(currentWord.toString()));
        }
        if (currentSentence.toString().length() > 0) {
            sentences.add(currentSentence);
        }
    }

    public void processReplacement(int targetLength, String replacement) {
        for (Sentence sentence : sentences) {
            sentence.replaceWords(targetLength, replacement);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence sentence : sentences) {
            sb.append(sentence.toString());
            if (sentences.indexOf(sentence) < sentences.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}

public class Lab4Variant15 {
    public static void main(String[] args) {
        String rawInput = "This is a    simple test, providing some data for the lab task.";
        int targetLength = 4;
        String replacementString = "CODE";

        System.out.println("Original String: " + rawInput);
        System.out.println("Target Length: " + targetLength);
        System.out.println("Replacement: " + replacementString);
        System.out.println("--------------------------------------------------");

        try {
            Text textObject = new Text(rawInput);
            System.out.println("Text Object (parsed): " + textObject);

            textObject.processReplacement(targetLength, replacementString);

            System.out.println("Result Text: " + textObject);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
