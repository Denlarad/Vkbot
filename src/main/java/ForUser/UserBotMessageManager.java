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
import org.apache.commons.io.FileUtils;
import org.asynchttpclient.uri.Uri;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class UserBotMessageManager {
    public void messageReader(VkApiClient vk, UserActor userActor, Random random) throws ClientException, ApiException, IOException {

        GetDialogsResponse response = vk.messages().getDialogs(userActor).count(1).execute();

        UserBotSendAnswer userBotSendAnswer = new UserBotSendAnswer();

        for (Dialog dialog : response.getItems()) {

            Integer userId = dialog.getMessage().getUserId();
            String message = dialog.getMessage().getBody();

            if (!message.equals("")) {

                String answer = getAnswer(message);
                File file = getPhotoAnswer(message);

                System.out.println(file);

                if (file != null) {
                    PhotoUpload serverResponse = vk.photos().getMessagesUploadServer(userActor).execute();
                    MessageUploadResponse uploadResponse = vk.upload().photoMessage(serverResponse.getUploadUrl(), file).execute();

                    List<Photo> photoList = vk.photos().saveMessagesPhoto(userActor, uploadResponse.getPhoto())
                            .server(uploadResponse.getServer())
                            .hash(uploadResponse.getHash())
                            .execute();

                    Photo photo = photoList.get(0);
                    String attachId = "photo" + photo.getOwnerId() + "_" + photo.getId();


                    userBotSendAnswer.sendMessage(message, vk, userActor, attachId, userId, random, "", true);
                } else {
                    userBotSendAnswer.sendMessage(message, vk, userActor, "", userId, random, answer, false);
                }

            }
        }
    }

    private String getAnswer(String message) {
        if (message.equals("Скажи 300")) {
            return "ПАШОЛ В ЖОПУ";
        }
        if (message.equals("Папей говна")) {
            return "";
        }
        if (message.equals("Пришли урода")) {
            return "";
        }

        return "чё за команда?";
    }

    private File getPhotoAnswer(String message) throws IOException {
        if (message.equals("Папей говна")) {
            return GetPhoto.getPhoto(1);
        }
        if (message.equals("Пришли урода")) {
            return GetPhoto.getPhoto(0);

        }
        if (message.equals("Скажи 300")) {
            return null;
        }
        return GetPhoto.getPhoto(2);
    }

}

