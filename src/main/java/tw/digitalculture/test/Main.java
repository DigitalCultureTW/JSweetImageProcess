/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.digitalculture.test;

import def.dom.Blob;
import static def.dom.Globals.alert;
import def.dom.HTMLImageElement;
import def.dom.XMLHttpRequest;
import static def.dom.Globals.document;
import def.dom.HTMLElement;
import static def.jquery.Globals.$;
import java.util.function.Consumer;

/**
 *
 * @author Jonathan
 */
public class Main {

    public static void main(String[] args) {
        $(document).ready(() -> {
//            String url = "http://data.digitalculture.tw/taichung/sites/default/files/object/photo/104%E8%87%BA%E4%B8%AD%E5%B8%82%E5%9F%8E%E4%B8%AD%E5%9F%8E%E9%A2%A8%E8%8F%AF%E9%87%8D%E7%8F%BE%20%E4%BD%B3%E4%BD%9C%20%E5%90%B3%E4%BB%81%E7%99%BC%20086-10%20%E5%8F%B0%E4%B8%AD%E8%8D%89%E6%82%9F%E9%81%93%E5%9C%B0%E6%A8%99%E4%B9%8B%E6%99%AF%E8%A7%80_0.JPG";
//            String url = "http://wm.localstudies.tw/element/student.jpg";
            String url = "http://data.digitalculture.tw/taichung/sites/default/files/object/photo/%E8%87%BA%E4%B8%AD%E5%B8%82%E7%A5%9E%E5%B2%A1%E5%8D%80%E5%B2%B8%E8%A3%A1%E5%9C%8B%E5%B0%8F%E5%85%A7%E7%9A%84%E5%8F%A4%E7%A2%91%E4%BA%AD%E4%B8%AD%E6%89%80%E4%BF%9D%E5%AD%98%E5%8F%A4%E7%A2%912-%E5%9C%96%EF%BC%9A%E6%B3%93%E7%BF%94.JPG";

            ExifReader.getOrientation(url, (Integer ori) -> {
                HTMLImageElement img = (HTMLImageElement) document.createElement("img");
                img.src = url;
                img.onload = (e) -> {
                    String rotation = "0";
                    switch (ori) {
                        case 1:
                            break;
                        case 3:
                            rotation = "180";
                            break;
                        case 6:
                            rotation = "90";
                            break;
                        case 8:
                            rotation = "-90";
                    }
                    $("#ori").text(ori);
                    HTMLElement div = document.createElement("div");
                    $(div).append(img);
                    $(div).css("width", "300px").css("height", "300px")
                            .css("background", "black");
                    $(img)
                            .css("-webkit-transform", "rotate(" + rotation + "deg)")
                            .css("-moz-transform", "rotate(" + rotation + "deg)")
                            .css("-ms-transform:", "rotate(" + rotation + "deg)")
                            .css("-o-transform", "rotate(" + rotation + "deg)")
                            .css("transform", "rotate(" + rotation + "deg)")
                            .css("width", "100%").css("height", "100%")
                            .css("object-fit", "contain")
                            .css("float", "left").css("margin", "auto");
                    $("#display").append(div);
                    return null;
                };
            });
            return null;
        });
    }

    public static void loadXHR(String url, Consumer<Blob> callback) {
        try {
            XMLHttpRequest xhr = new XMLHttpRequest();
            xhr.open("GET", url, true);
//                xhr.withCredentials = true;
//            xhr.setRequestHeader("Content-type", "application/xml");
            xhr.responseType = "blob";
            xhr.onerror = (e) -> {
                alert("Network error.");
                return null;
            };
            xhr.onload = (e) -> {
                if (xhr.status == 200) {
                    callback.accept((Blob) xhr.response);
                } else {
                    alert("Loading error:" + xhr.statusText);
                }
                return null;
            };
            xhr.send();
        } catch (Exception e) {
            alert(e.getMessage());
        }
    }
}
