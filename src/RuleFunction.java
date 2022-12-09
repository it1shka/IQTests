public class RuleFunction<T> {
    private final T[] range;
    private final int offset;

    public RuleFunction(T[] range, int offset) {
        this.range = range;
        this.offset = offset;
    }

    public T get(int x) {
        var index = (offset + x) % range.length;
        return range[index];
    }
}
