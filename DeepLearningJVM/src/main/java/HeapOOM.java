import java.util.ArrayList;
import java.util.List;

/**
 * jvm args: -Xms20m -Xmx30m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    static class  OOMObject{

    }
    public static void main(String[] args) {
	// write your code here
        List<OOMObject> oomObjects = new ArrayList<OOMObject>();
            while (true){
            oomObjects.add(new OOMObject());
        }
    }
}
