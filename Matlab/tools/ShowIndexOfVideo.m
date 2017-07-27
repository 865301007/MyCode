function [u]=ShowIndexOfVideo(filename,n)
obj=VideoReader(filename);
    frame=obj.read(n);
    addpath('./image');
    imwrite(frame,['./image/filename',num2str(n),'.jpg']);
    u=0;
end