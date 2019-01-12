import ForGroup.GroupBotManager;
import ForUser.UserBotMessageManager;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

public class VkBot {


    public void botManager(VkApiClient vk,UserActor userActor, GroupActor groupActor,int botType) throws InterruptedException, ClientException, ApiException, IOException {
        if (botType == 1){
            groupBotManager(vk,groupActor,userActor);
        }else{
            userBotManager(vk, userActor);
        }
    }

    private void userBotManager(VkApiClient vk, UserActor userActor) throws InterruptedException, ClientException, ApiException, IOException {
        final Random random = new Random();

        while (true) {

            Thread.sleep(750);

            UserBotMessageManager userBotMessageManager = new UserBotMessageManager();
            userBotMessageManager.messageReader(vk, userActor ,random);
        }
    }

    private void  groupBotManager(VkApiClient vk,GroupActor groupActor,UserActor userActor) throws InterruptedException, ClientException, ApiException, IOException {
        while (true){
            GroupBotManager groupBotManager = new GroupBotManager();
            groupBotManager.GroupBotManager(vk,groupActor,userActor);
            Thread.sleep(15 * 60 * 1000); // типо каждые 15 минут постит фото
        }
    }
}
