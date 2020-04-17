package tictactoe;

public enum PossibleValues {
    X('X'), O('O'), BLANK('_');


    private char charValue;

    PossibleValues(char charValue) {
        this.charValue = charValue;
    }

    public static PossibleValues withCharValue(char charValue) {
        switch (charValue) {
            case 'X' :
                return PossibleValues.X;
            case 'O' :
                return PossibleValues.O;
            case '_' :
            case ' ' :
                return PossibleValues.BLANK;
            default :
                throw new IllegalArgumentException("Can't convert this char to a value!");
        }
    }

    @Override
    public String toString() {
        return "" + this.charValue;
    }
}
