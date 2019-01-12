
import ForGroup.PhotoGetter;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;


public class Main extends Application {


    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MainMenu.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) throws ClientException, ApiException, InterruptedException, IOException {
        launch(args);

            TransportClient transportClient = HttpTransportClient.getInstance();
            VkApiClient vk = new VkApiClient(transportClient);

        int botType = 1; //      0 - User bot.    1 - Group bot.

        PhotoGetter photoGetter = new PhotoGetter();


        GroupActor groupActor = new GroupActor(173453365,"173453365=6c082b2fcd6be18178e76fd5e37b4887e9e76cac72d7659dae196ff78fca67df0d943c00fd36bedbecf9f");
        UserActor userActor = new UserActor(495812590,"abe5eecd87579bd8cf49afbe73f0eceb0530fe40cb3676bebac491b169630ccd11e2d1db2670d973f575a");


        VkBot vkBot = new VkBot();

        vkBot.botManager(vk,userActor,groupActor,botType);

    }
}


