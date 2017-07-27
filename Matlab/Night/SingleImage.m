
obj.blobAnalyser = vision.BlobAnalysis('BoundingBoxOutputPort', true, ...
    'AreaOutputPort', true, 'CentroidOutputPort', true, ...
    'MinimumBlobArea', 50);
obj.position = [10 280  600 80];
frame=imread('test142.jpg');
R=frame(:,:,1);
G=frame(:,:,2);
B=frame(:,:,3);
[m,n,c]=size(frame);

for i=1:m
    for j=1:n
        C(i,j)=R(i,j)-max(G(i,j),B(i,j))-2*abs(G(i,j)-B(i,j));
    end
end
thresh=graythresh(C);
gray=im2bw(C,thresh);
imshow(gray);
[area, centroids, bboxes] = obj.blobAnalyser.step(gray);

videoFrame=insertShape(frame,'Rectangle',obj.position,'LineWidth', 5);
videoFrame=insertShape(videoFrame,'Rectangle',bboxes,'LineWidth', 2,'Color','Red');
lightbboxes=zeros(0,4);             %初始化每个被检测到的车灯的 bbox centoid
lightcentroids=zeros(0,2);
lightarea=zeros(0,1);

j=1;
[m,n]=size(bboxes);
bboxes
for i=1:m    
    %将处于虚拟线圈中的符合条件的联通区域赋值给lightcentroids和lightbboxes
    
    if(bboxes(i,2)>obj.position(2)&&bboxes(i,2)<(obj.position(2)+obj.position(4))&&single(bboxes(i,3))/single(bboxes(i,4))<3&&single(bboxes(i,3))/single(bboxes(i,4))>1/3)
        lightcentroids(j,:)=centroids(i,:);
        lightbboxes(j,:)=bboxes(i,:);
        lightarea(j,:)=area(i,:);
        j=j+1;
    end
end
car=zeros(0,4);
n=1;
lightbboxes
videoFrame=insertShape(videoFrame,'Rectangle',lightbboxes,'LineWidth', 2);

[m,~]=size(lightbboxes);
flag=ones(1,m);
for i=1:m-1         %车灯匹配  产生car表示被检测到的车辆
    for j=i+1:m;
        d=lightcentroids(j,1)-lightcentroids(i,1);
        k=lightarea(j,:)/lightarea(i,:);
        if(flag(i)&flag(j)&d< 280)
            w=lightbboxes(j,1)+lightbboxes(j,3)-lightbboxes(i,1);
            h=max(lightbboxes(i,4),lightbboxes(j,2)+lightbboxes(j,4)-lightbboxes(i,2));
            car(n,:)=[lightbboxes(i,1),lightbboxes(i,2),w,h];
            flag(i)=0;
            flag(j)=0;
            n=n+1;
        end
    end
end

bboxes=car;     %将car赋值给bboxes
centroids=[bboxes(:,1)+bboxes(:,3)/2,bboxes(:,2)+bboxes(:,4)/2];  %计算图心
videoFrame=insertShape(videoFrame,'Rectangle',bboxes,'LineWidth', 2);

subplot(121),imshow(videoFrame);
subplot(122),imshow(gray);
