Learned from https://heartbeat.fritz.ai/a-guide-to-preparing-opencv-for-android-4e9532677809

OpenCV is a vision library built for doing complex, real-time operations on images.
 
The resource image is read as an OpenCV Mat named img. Then the Mat is filtered according to the Canny() method inside the Imgproc class and the filtered image is stored into the img_result Mat.
This Mat is then converted into a bitmap image using the matToBitmap() method. Finally, the converted bitmap image is set as the image displayed on the ImageView using the setImageBitmap() method. The result after filtering the image is shown in the next figure.

![alt text](https://github.com/markpairdha/OpenCV-Android-Projects/blob/master/01.OpenCVBasics/shot1.png)
![alt text](https://github.com/markpairdha/OpenCV-Android-Projects/blob/master/01.OpenCVBasics/shot2.png)
