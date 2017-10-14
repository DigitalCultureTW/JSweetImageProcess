/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.digitalculture.test;

import static def.dom.Globals.document;
import def.dom.HTMLImageElement;
import static def.jquery.Globals.$;

/**
 *
 * @author Jonathan
 */
public class Main {

    public static void main(String[] args) {
        $(document).ready((o) -> {
            HTMLImageElement img = (HTMLImageElement) document.createElement("img");
            img.src = "http://wm.localstudies.tw/element/student.jpg";
            img.onload = (t) -> {
                $("body").append(img);
                $(img).css("width", 300).css("height", "auto");
                System.out.println("1");
                ExifReader.getOrientation(img, (Integer ori) -> {
                    $("#ori").text(ori);
                    switch (ori) {
                        case 1:
                            $(img).css("image-orientation", "0deg");
                            break;
                        case 3:
                            $(img).css("image-orientation", "180deg");
                            break;
                        case 6:
                            $(img).css("image-orientation", "90deg");
                            break;
                        case 8:
                            $(img).css("image-orientation", "-90deg");
                            break;
                    }
//                    return null;
                });
                return null;
            };
            return null;
        });

    }
}
