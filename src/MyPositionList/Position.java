package MyPositionList;

public interface Position<T> {
    public T getElement() throws IllegalStateException;
}
