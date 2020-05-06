
@Data
public class Pair {
    private Product product1;
    private Product product2;

    public int getHash1() {
        return (product1.gethash() + product2.gethash()) % 5;
    }

    public int getHash2() {
        return (product2.gethash() + 3 * product2.gethash()) % 5;
    }


    public boolean goodPair(){
        if ((product1.getCount()>2) && (product2.getCount()>2)){
            return true;
        }
        return false;
    }
}
