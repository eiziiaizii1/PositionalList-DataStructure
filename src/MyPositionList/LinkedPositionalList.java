package MyPositionList;

public class LinkedPositionalList<T> implements PositionalList<T>{
    private static class Node<T> implements Position<T>{
        private T data;
        private Node<T> prev;
        private Node<T> next;

        public Node(T t, Node<T> prev, Node<T> next){
            data = t;
            this.prev = prev;
            this.next = next;
        }
        public Node<T> getPrev(){
            return prev;
        }
        public Node<T> getNext(){
            return next;
        }
        public void setData(T t){
            data = t;
        }
        public void setPrev(Node<T> prev){
            this.prev = prev;
        }
        public void setNext(Node<T> next){
            this.next = next;
        }
        @Override
        public T getElement() throws IllegalStateException {
            if(next == null) throw new IllegalStateException("Position is not valid");
            return data;
        }
    }

    private final Node<T> header;
    private final Node<T> trailer;
    private int size = 0;

    public LinkedPositionalList(){
        header = new Node<>(null,null,null);
        trailer = new Node<>(null,header,null);
        header.setNext(trailer);
    }

    // Cast Position to Node
    private Node<T> validate(Position<T> p) throws IllegalArgumentException{
        if(!(p instanceof Node))
            throw new IllegalArgumentException("Invalid Position");
        Node<T> node = (Node<T>) p;
        if(node.getNext() == null)
            throw new IllegalArgumentException("p is not in th list");
        return node;
    }

    // Cast Node to Position
    private Position<T> position(Node<T> node){
        if(node == header || node == trailer)
            return null;
        return node; // Returns node as Position
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<T> first() {
        return header.getNext();
    }

    @Override
    public Position<T> last() {
        return trailer.getPrev();
    }

    @Override
    public Position<T> before(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return position(node.getPrev());
    }

    @Override
    public Position<T> after(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return position(node.getNext());
    }

    private Position<T> addBetween(T t, Node<T> predecessor, Node<T> successor){
        Node<T> node = new Node<>(t,predecessor,successor);
        predecessor.setNext(node);
        successor.setPrev(node);
        size++;
        return node;
    }
    @Override
    public Position<T> addFist(T t) {
        return addBetween(t,header,header.getNext());
    }

    @Override
    public Position<T> addLast(T t) {
        return addBetween(t,trailer.getPrev(),trailer);
    }

    @Override
    public Position<T> addBefore(Position<T> p, T t) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return addBetween(t,node.getPrev(),node);
    }

    @Override
    public Position<T> addAfter(Position<T> p, T t) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return addBetween(t,node,node.getNext());
    }

    @Override
    public T set(Position<T> p, T t) throws IllegalArgumentException {
        Node<T> node = validate(p);
        T prevData = node.getElement();
        node.setData(t);
        return prevData;
    }

    @Override
    public T remove(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        size--;
        T removedData = node.getElement();
        node.setNext(null);
        node.setPrev(null);
        node.setData(null);
        return removedData;
    }
}
