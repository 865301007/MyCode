Frontdetector=vision.CascadeObjectDetector('carRearDetector06.xml','MergeThreshold',1, 'MaxSize',[256 128], 'MinSize' ,[64 32]);
%videoFrame=imread('C:\Users\Lenovo\Desktop\课业\code\级联模型\image\daytime3421.jpg');
videoFrame=imread('C:\Users\Lenovo\Desktop\课业\code\image\daytime31421.jpg');
Frontbbox=step(Frontdetector,videoFrame);

Frame=insertShape(videoFrame,'rectangle',Frontbbox,'color','red');

imshow(Frame);