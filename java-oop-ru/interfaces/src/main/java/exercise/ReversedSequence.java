package exercise;

public class ReversedSequence implements CharSequence {
    private String original;

    public ReversedSequence(String original) {
        this.original = original;
    }

    @Override
    public int length() {
        return original.length();
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= original.length()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        // Возвращаем символ из перевернутой последовательности
        return original.charAt(original.length() - 1 - index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0 || end > original.length() || start > end) {
            throw new IndexOutOfBoundsException("Invalid start or end index");
        }
        // Возвращаем подпоследовательность из перевернутой последовательности
        StringBuilder subSequence = new StringBuilder(original.substring(original.length() - end, original.length() - start));
        return new ReversedSequence(subSequence.toString());
    }

    @Override
    public String toString() {
        // Возвращаем строку в обратном порядке
        return new StringBuilder(original).reverse().toString();
    }
}