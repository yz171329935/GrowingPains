import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 14/11/22.
 * jvm args: -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("aa").append("bb").toString();
        System.out.println(str2.intern() == str2);

        String str3 = "aabb".intern();
        System.out.println(str2.intern() == str3);


        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }

    }
}
