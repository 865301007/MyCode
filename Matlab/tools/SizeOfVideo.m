function [y]=SizeOfVideo(filename)
y=VideoReader(filename).NumberOfFrame;
end