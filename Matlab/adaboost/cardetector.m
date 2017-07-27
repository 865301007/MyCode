
function [numberofcars]=cardetector()
position=[10,200,600,150];
fileName='daytime3.MOV';
modelName='carRearDetector06.xml';
%fileName='D:\DataSet\rear1.MOV';
detector = vision.CascadeObjectDetector(modelName,'MergeThreshold',20,'UseROI',true);
%img = imread('test2.png');
%addpath('./resouce');
addpath('./image')
tracks = initializeTracks();
nextId=1;
videoReader = VideoReader(fileName);
% foreground = step(foregroundDetector, frame);
num=videoReader.NumberOfFrame
for ii = 1:num
    frame = videoReader.read(ii);
    
    [centroids,bboxes]=detectObjects(frame);
    predicNewLocationOfTracks();
    [assignments,unassignedTracks,unassignedDetections]=detectionToTrackAssigment();
    updateAssignedTracks();
    updateUnassignedTracks();
    
    deleteLostTracks();
    createNewTracks();
    frame=insertShape(frame,'Rectangle',position,'LineWidth', 5);
    for j=1:length(tracks)
        bbox=tracks(j).bbox;
        lable=tracks(j).id;
        % if(tracks(j).notvisiable==false)
        frame = insertObjectAnnotation(frame, 'rectangle',bbox,lable,'Color','red'); %将跟踪结果显示出来
        %end
        
        
        %  detectedImg = insertObjectAnnotation(img,'rectangle',bbox,'car');
        %  detectedImg=insertShape(detectedImg,'Rectangle',position,'LineWidth', 2);
        %  imshow(detectedImg);
    end
  %  [~,name,~]=fileparts(fileName);
   % imwrite(frame,['./image/Result',name,num2str(ii),'.jpg']);
      imshow(frame);
end
y=0;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    function tracks = initializeTracks()
        % create an empty array of tracks
        tracks = struct(...
            'id', {}, ...
            'bbox', {}, ...
            'kalmanFilter', {}, ...
            'age', {}, ...
            'totalVisibleCount', {}, ...
            'consecutiveInvisibleCount', {},...
            'notvisiable',{});
    end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    function [centroids, bboxes] = detectObjects(frame)
        bboxes=step(detector,frame,position);
        centroids=zeros(0,2);
        if(size(bboxes,1)>0)
            for i=1:size(bboxes,1);
                centroids(i,:)=[bboxes(i,1)+bboxes(i,3)/2,bboxes(i,2)+bboxes(i,4)/2];
            end
        end
    end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    function predicNewLocationOfTracks()
        for i=1:length(tracks)
            bbox=tracks(i).bbox;
            predictedCentroid=predict(tracks(i).kalmanFilter);
            predictedpoint=[predictedCentroid(1)-bbox(3)/2,predictedCentroid(2)-bbox(4)/2];
            tracks(i).bbox=[predictedpoint,bbox(3:4)];
        end
    end
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    function [assignments,unassignedTracks,unassignedDetections]=detectionToTrackAssigment()
        nTracks=length(tracks);
        nDetections=size(centroids,1);
        cost=zeros(nTracks,nDetections);
        for i=1:nTracks    %计算花费矩阵
            cost(i,:)=distance(tracks(i).kalmanFilter,centroids);
        end
        costOfNonAssignment=40;
        [assignments,unassignedTracks,unassignedDetections]=assignDetectionsToTracks(cost,costOfNonAssignment);
        %。。。
        
    end
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    function updateAssignedTracks()
        numAssignedTracks=size(assignments,1);
        for i=1:numAssignedTracks
            trackIdx=assignments(i,1);
            detectionIdx=assignments(i,2);
            centroid=centroids(detectionIdx,:);
            bbox=bboxes(detectionIdx,:);
            correct(tracks(trackIdx).kalmanFilter,centroid);
            tracks(trackIdx).bbox=bbox;
            track(trackIdx).notvisiable=false;
        end
    end
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    function updateUnassignedTracks()
        for i=1:length(unassignedTracks)
            ind=unassignedTracks(i);
            tracks(ind).age=tracks(ind).age+1;
            tracks(ind).consecutiveInvisibleCount=tracks(ind).consecutiveInvisibleCount+1;
            tracks(ind).notvisiable=true;
            %计算目标没有没匹配所经历的帧数
        end
    end
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    function deleteLostTracks()
        if isempty(tracks)
            return ;
        else
            invisibleForTooLong=8;
            
            lostInds = [tracks(:).consecutiveInvisibleCount] >= invisibleForTooLong;
            tracks = tracks(~lostInds);
            %for i=1:length(unassignedTracks)
            %    ind=unassignedTracks(i);
            %    if(tracks(ind).consecutiveInvisibleCount> invisibleForTooLong|tracks(ind).bbox(1,2)>(position(1,2)+position(1,4)))
            %        %当目标未出现的帧数过多或者目标不在虚拟线框中时，删除目标
            %       tracks=tracks(~ind);
            %   end
            %end
        end
    end
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    function createNewTracks()
        centroids=centroids(unassignedDetections,:);
        bboxs=bboxes(unassignedDetections,:);
        
        for i=1:size(centroids,1)
            centroid=centroids(i,:);
            bbox=bboxs(i,:);
            if(bbox(1,2)>position(1,2)+30) %此处为了防止已经进入虚拟线框中被计数之后目标丢失后又重新显现而进行重复计数
                return;
            end
            kalmanFilter=configureKalmanFilter('ConstantVelocity',centroid,[50,15],[50,10],50);
            newTrack=struct('id',nextId,'bbox',bbox,'kalmanFilter',kalmanFilter,...
                'age',1,'totalVisibleCount',1,'consecutiveInvisibleCount',0,'notvisiable',false);
            tracks(end+1)=newTrack;
            nextId=nextId+1;
        end
    end
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


numberofcars=nextId-1;
end
