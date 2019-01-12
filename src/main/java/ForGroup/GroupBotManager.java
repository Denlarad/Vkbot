package ForGroup;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.io.IOException;

public class GroupBotManager {
    public void GroupBotManager(VkApiClient vk, GroupActor groupActor, UserActor userActor) throws ClientException, ApiException, IOException, InterruptedException {

     GroupBotPoster groupBotPoster = new GroupBotPoster();
     groupBotPoster.groupBotPoster(vk,userActor,groupActor);
    }
}
