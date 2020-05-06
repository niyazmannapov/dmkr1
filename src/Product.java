import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Product {
    private String basket;
    private String id;
    private int count=1;

    public void inc(){
        this.count+=1;
    }

    public int gethash(){
        return Integer.parseInt(id.substring(7,10));
    }
}
