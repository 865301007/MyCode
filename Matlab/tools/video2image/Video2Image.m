function [u]=Video2Image(filename)
obj=VideoReader(filename);
num=obj.NumberOfFrame;
for k=1:20:num
    frame=obj.read(k);
    addpath('./image');
    [~,name,~]=fileparts(filename);
    imwrite(frame,['./image/',name,num2str(k),'.jpg']);
    u=0;
end
end