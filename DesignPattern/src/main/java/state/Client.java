package state;

/**
 * Created by marco on 16/3/7.
 */
public class Client {
    public static void main(String[] args) {

        Context context = new Context();
        context.setLiftState(new ClosingState());
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
