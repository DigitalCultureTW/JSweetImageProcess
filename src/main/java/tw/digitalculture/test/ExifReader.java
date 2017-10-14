package tw.digitalculture.test;

import def.dom.HTMLImageElement;
import static def.exif_js.Globals.EXIF;
import java.util.function.Consumer;
import static jsweet.util.Lang.function;

/**
 *
 * @author Jonathan
 */
public class ExifReader {

    public static void getOrientation(HTMLImageElement img, Consumer<Integer> callback) {
        System.out.println("2");
        System.out.println(img.src);
        System.out.println(EXIF);
        System.out.println(EXIF.getAllTags(img));
        System.out.println(EXIF.getTag(img, "Orientation"));
        System.out.println(EXIF.getTag(img, "orientation"));
        EXIF.getData(img.src, function((o) -> {
            System.out.println("3");
            System.out.println("EXIF= " + EXIF);
            System.out.println(EXIF.getTag(img, "orientation"));
            System.out.println(EXIF.getTag(img, "Orientation"));
            System.out.println(EXIF.getAllTags(img));
            callback.accept((Integer) EXIF.getTag(img, "Orientation"));;
            return null; //To change body of generated lambdas, choose Tools | Templates.
        }));
    }
}
