function [] = ResizeImage ()  %参数m表示长宽比
file_path = 'D:\DataSet\样本\中间车道\';
file_new_path = 'D:\DataSet\样本\64\';
img_path_list = dir (strcat(file_path,'*.jpg'));
img_num = length (img_path_list);
for i=1:img_num
    image_name = img_path_list(i).name;
    image = imread(strcat(file_path,image_name));
    image1 = imresize(image,[64 64]);
    imwrite(image1 ,strcat(file_new_path,image_name)); 
end
end
