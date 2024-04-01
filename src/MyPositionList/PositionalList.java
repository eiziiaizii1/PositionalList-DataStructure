package MyPositionList;

public interface PositionalList <T>{
    int getSize();
    boolean isEmpty();

    Position<T> first();
    Position<T> last();
    Position<T> before(Position<T> p) throws IllegalArgumentException;;
    Position<T> after(Position<T> p) throws IllegalArgumentException;;
    Position<T> addFist(T t);
    Position<T> addLast(T t);
    Position<T> addBefore(Position<T> p,T t) throws IllegalArgumentException;;
    Position<T> addAfter (Position<T> p, T t) throws IllegalArgumentException;;
    T set(Position<T> p, T t) throws IllegalArgumentException;;
    T remove(Position<T> p) throws IllegalArgumentException;;
}
