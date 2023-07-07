package jsonObjects.purchaseOrder.createPurchaseOrder;

public class Sequence {
    private Integer charactersCount;
    private Integer numbersCount;
    private Integer start;

    public Sequence(Integer charactersCount, Integer numbersCount, Integer start) {
        this.charactersCount = charactersCount;
        this.numbersCount = numbersCount;
        this.start = start;
    }

    public Sequence() {
    }

    public Integer getCharactersCount() {
        return charactersCount;
    }

    public Integer getNumbersCount() {
        return numbersCount;
    }

    public Integer getStart() {
        return start;
    }

    public void setCharactersCount(Integer charactersCount) {
        this.charactersCount = charactersCount;
    }

    public void setNumbersCount(Integer numbersCount) {
        this.numbersCount = numbersCount;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
