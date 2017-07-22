clear
clc
% 样本数量15*11
people_count=15;
face_count_per_people=11;
% 训练比率,70%时识别正确率为96.67%,设置为75%识别正确率可达100%
training_ratio=.75;
% 能量
energy=90;
training_count=floor(face_count_per_people*training_ratio);
training_samples=[];
path_mask='pca_face_rec\\%03d\\%02d.jpg';

% 训练
for i=1:people_count
    for j=1:training_count
        img=im2double(imread(sprintf(path_mask,i,j)));
        imshow(img);
        img=imresize(img,[10 10]); % 归一化至50*50
      
        if ndims(img)==3
            img=rgb2gray(img);
        end
        training_samples=[training_samples;img(:)'];
    end
end
mu=mean(training_samples);
[coeff,scores,~,~,explained]=pca(training_samples);
idx=find(cumsum(explained)>energy,1);
coeff=coeff(:,1:idx);
scores=scores(:,1:idx);

% 测试
acc_count=0;
for i=1:people_count
    for j=training_count+1:face_count_per_people
        img=im2double(imread(sprintf(path_mask,i,j)));
        img=imresize(img,[10 10]);
        if ndims(img)==3
            img=rgb2gray(img);
        end
        score=(img(:)'-mu)/coeff';
        [~,idx]=min(sum((scores-repmat(score,size(scores,1),1)).^2,2));
        if ceil(idx/training_count)==i
            acc_count=acc_count+1;
        end
    end
end
test_count=(people_count*(face_count_per_people-training_count));
acc_ratio=acc_count/test_count;
fprintf('测试样本数量:%d,正确识别率:%2.2f%%',test_count,acc_ratio*100)