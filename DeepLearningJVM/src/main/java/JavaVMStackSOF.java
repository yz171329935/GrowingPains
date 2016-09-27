/**
 * Created by marco on 14/11/22.
 * jvm args: -Xss128k
 */
public class JavaVMStackSOF {

    private  int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable{
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length:"+javaVMStackSOF.stackLength);
            throw e;
        }
    }
}
