function resizeVideo()
reader=VideoReader('DaytimeTracking\test2.1.avi');
writer=VideoWriter('test');
open(writer);
n=reader.NumberOfFrame;
for i=1:n
    frame=reader.read(i);
    result=imresize(frame,[480,640]);
    writeVideo(writer,result);
    imshow(result);
end
close(writer);
end