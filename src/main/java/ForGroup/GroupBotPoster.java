package ForGroup;

import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoUpload;
import com.vk.api.sdk.objects.photos.responses.WallUploadResponse;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class GroupBotPoster {
    public void groupBotPoster(VkApiClient vk, UserActor userActor, GroupActor groupActor) throws ClientException, ApiException, IOException, InterruptedException {

        GetPhotoToGroupBot getPhotoToGroupBot = new GetPhotoToGroupBot();

        String bUrl = getPhotoToGroupBot.getPhotoToGroupBot(vk, userActor);

        System.out.println(bUrl);


            File file = new File("D:\\Buffer.jpg");
            URL url = new URL(bUrl);
            BufferedImage img = ImageIO.read(url);
            ImageIO.write(img, "jpg", file);

            PhotoUpload serverResponse = vk.photos().getWallUploadServer(userActor).execute();
            WallUploadResponse uploadResponse = vk.upload().photoWall(serverResponse.getUploadUrl(), file).execute();
            List<Photo> photoList = vk.photos().saveWallPhoto(userActor, uploadResponse.getPhoto())
                    .server(uploadResponse.getServer())
                    .hash(uploadResponse.getHash())
                    .execute();

            Photo photo = photoList.get(0);
            String attachId = "photo" + photo.getOwnerId() + "_" + photo.getId();

            Wall wall = new Wall(vk);
            wall.post(userActor).ownerId(-173453365).fromGroup(true).postId(0).message("").attachments(attachId).execute();
        }
    }

