package ForUser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetPhoto {
    public static File getPhoto(int photoIndex){
        List<File> files = new ArrayList<File>();

        files.add(new File("D:\\2.jpg"));
        files.add(new File("D:\\1.jpg"));
        files.add(new File("D:\\3.jpg"));

        return files.get(photoIndex);
    }
}
