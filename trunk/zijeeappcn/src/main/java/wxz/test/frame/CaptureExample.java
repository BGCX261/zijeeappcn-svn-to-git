//package wxz.test.frame;
//
//import java.io.*;
//import javafx.ui.*;
//import javafx.ui.canvas.*;
//import javafx.ui.filter.*;
//import java.awt.Robot;
//import java.awt.Rectangle;
//import java.awt.image.RenderedImage;
//import javax.imageio.ImageIO;
//import java.lang.System;
//class CaptureExample extends CompositeNode{
//attribute lx: Integer;
//attribute ly: Integer;
//operation CaptureExample();
//attribute CaptureExample.lx = 0;
//attribute CaptureExample.ly = 0;
//operation saveCapture(lx_copy:Integer, ly_copy:Integer) {
//    var robot = new Robot();
//    var rect = new Rectangle (lx_copy, ly_copy, 50, 50);
//    var BI=robot.createScreenCapture(rect);
//    var file = new File(".//capture.jpg");
//    ImageIO.write((RenderedImage)BI, "jpg", file);
//     }
//function CaptureExample.composeNode() =Group{
//      transform: []
//      content:[ImageView {
//               transform: []
//              image: Image { url: ".//app//Sunset.gif" }
//      cursor: DEFAULT
//      onMouseClicked: operation(e:CanvasMouseEvent) {
//                    saveCapture(e.source.XOnScreen,e.source.YOnScreen);
//                      }
//      onMouseMoved: operation(e:CanvasMouseEvent) {
//                           lx = e.x; ly = e.y;
//                             }
//       },
//               Rect{
//               x: bind lx y: bind ly width: 50 height:50 strokeWidth: 1 stroke: black }]
//               };
//Frame {
//   centerOnScreen: true
//   visible: true
//   height: 230
//   width: 300
//   title: "Capture the screen..."
//   onClose: operation() {System.exit(0);}
//   content: ScrollPane {
//              background: white
//              view: Canvas {
//              background: black
//              cursor: DEFAULT
//              content: CaptureExample }
//   }
//}