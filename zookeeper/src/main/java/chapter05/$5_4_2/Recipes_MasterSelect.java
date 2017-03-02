package chapter05.$5_4_2;

import chapter05.$5_4_2.util.MyCuratorUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

/**
 * Created by marco on 16/3/28.
 */
public class Recipes_MasterSelect {

    private static final String master_path = "/curator_recipes_master_path";

    public static void main(String[] args) throws Exception{
        MyCuratorUtil.getClient().start();

        LeaderSelector selector = new LeaderSelector( MyCuratorUtil.getClient(),
                master_path,
                new LeaderSelectorListenerAdapter() {

                    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                        System.out.println("selector 成为master角色");
                        Thread.sleep(3000);
                        System.out.println("selector 完成master角色，释放master权利");
                    }
                });

        selector.autoRequeue();
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
