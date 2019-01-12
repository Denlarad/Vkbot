package ForUser;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Dialog;
import com.vk.api.sdk.objects.messages.responses.GetDialogsResponse;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoUpload;
import com.vk.api.sdk.objects.photos.responses.MessageUploadResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageHeader {
    public void messageREader (VkApiClient vk, UserActor userActor, Random random) throws ClientException, ApiException {
        GetDialogsResponse response = vk.messages().getDialogs(userActor).count(1).execute();


        for (Dialog dialog : response.getItems()) {
            List<File> files = new ArrayList<File>();

            int photoindex = 1;

            files.add(new File("D:\\2.jpg"));
            files.add(new File("D:\\1.jpg"));

            Integer userId = dialog.getMessage().getUserId();
            String message = dialog.getMessage().getBody();

            if (message.equals("Папей говна")) {
                photoindex = 1;
            } else {
                photoindex = 0;
            }



            PhotoUpload serverResponse = vk.photos().getMessagesUploadServer(userActor).execute();
            MessageUploadResponse uploadResponse = vk.upload().photoMessage(serverResponse.getUploadUrl(),files.get(photoindex)).execute();

            List<Photo> photoList = vk.photos().saveMessagesPhoto(userActor, uploadResponse.getPhoto())
                    .server(uploadResponse.getServer())
                    .hash(uploadResponse.getHash())
                    .execute();

            Photo photo = photoList.get(0);
            String attachId = "photo" + photo.getOwnerId() + "_" + photo.getId();

            if (message.equals("Пришли урода")) {
                vk.messages().send(userActor).attachment(attachId).userId(userId).randomId(random.nextInt()).execute();
                System.out.println("выполнил");
            }

            if (message.equals("Папей говна")) {
                vk.messages().send(userActor).attachment(attachId).userId(userId).randomId(random.nextInt()).execute();
                System.out.println("выполнил2");
            }

        }
    }
}
