package ForUser;

import com.sun.deploy.nativesandbox.NativeSandboxBroker;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import jdk.nashorn.internal.ir.IdentNode;

import java.util.Random;

public class UserBotSendAnswer {
    public void sendMessage(String message, VkApiClient vk, UserActor userActor, String attachId, Integer userId, Random random, String answer, boolean havePhoto) throws ClientException, ApiException {
        if (havePhoto == true) {
            if (message.equals("Пришли урода")) {
                vk.messages().send(userActor).attachment(attachId).userId(userId).randomId(random.nextInt()).execute();
                System.out.println("выполнил");
            }

            if (message.equals("Папей говна")) {
                vk.messages().send(userActor).attachment(attachId).userId(userId).randomId(random.nextInt()).execute();
                System.out.println("выполнил2");
            }
        } else {
            if (message.equals("Скажи 300")) {
                vk.messages().send(userActor).message(answer).userId(userId).randomId(random.nextInt()).execute();
            }

        }
    }
}
