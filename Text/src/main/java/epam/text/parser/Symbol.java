package epam.text.parser;

import epam.text.part.IPart;

public class Symbol implements IPart {
    private char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    @Override
    public void addElement(IPart part) {
        throw new UnsupportedOperationException();
    }
}
