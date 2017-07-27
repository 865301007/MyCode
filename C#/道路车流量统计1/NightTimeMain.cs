using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MathWorks.MATLAB.NET.Arrays;
using MathWorks.MATLAB.NET.Utility;
using SizeOfVideo;
using System.Threading;
using System.IO;
using Video2Image;
namespace 道路车流量统计
{
    public partial class NightTimeMain : Form
    {

        MWArray filename1;
        int numberOfFrame;
        string nameWithoutPath;
        public NightTimeMain()
        {
            Control.CheckForIllegalCrossThreadCalls = false;
            InitializeComponent();
            bt_OK.Enabled = false;
           
        }

        private void bt_SelectVideo_Click(object sender, EventArgs e)
        {
            selectVideo();
        }

        private void selectVideo()                             //选择视频
        {
           
            OpenFileDialog fileDialog = new OpenFileDialog();
            fileDialog.Multiselect = true;
            fileDialog.Title = "请选择文件";
            if (Global.nightAglorith=="基于前车灯")
            {
                fileDialog.Filter = "视频文件|*前灯*.avi;*前灯*.mov;*前灯*.mp4";
                Global.task = "前灯";
            }
            else
            {
                fileDialog.Filter = "视频文件|*尾灯*.avi;*尾灯*.mov;*尾灯*.mp4";
                Global.task = "尾灯";
            }
                if (fileDialog.ShowDialog() == DialogResult.OK)
            {
                string file = fileDialog.FileName;
                
                Global.setFileName(file);
                filename1  = file;
                nameWithoutPath = Path.GetFileNameWithoutExtension(file);                 //获取文件名
                label1.Text = nameWithoutPath;
                Global.nameWithoutPath = nameWithoutPath;
                Thread s1 = new Thread(prossImage);
                s1.Start();
           //     MessageBox.Show("已选择文件:" + file, "选择文件提示", MessageBoxButtons.OK, MessageBoxIcon.Information);

                bt_SelectVideo.Enabled = false;                            //选择按钮不可用
            }
            ShowVideo();
        }

        private void ShowVideo()
        {
            
            Thread s2 = new Thread(showImage);    
            s2.Start();
        }

        private void prossImage()
        {
            MWNumericArray num = (MWNumericArray)new MyMatlab().SizeOfVideo(Global.fileName_Matlab);
            numberOfFrame = (int)num;
            Global.numberOfFrame = numberOfFrame;
           label1.Text = "开始处理";
           if (File.Exists(@"./image/" + nameWithoutPath + numberOfFrame + ".jpg")) ;   //如果文件存在证明视频已经载入
           else
               new Video2ImageClass().Video2Image(filename1);                       
           label1.Text = "处理完成";
           bt_OK.Enabled = true;
           bt_SelectVideo.Enabled = true;
        }
        private void showImage()
        {

            Thread.Sleep(2000);
        
            int i = 1;
        
            while (i != numberOfFrame)
            {
               
                string name = "./image/"+nameWithoutPath + i + ".jpg";
                string nextname = "./image/" + nameWithoutPath + (i+1) + ".jpg";
                label2.Text = name;
                if (File.Exists(@nextname))
                {
                    pictureBoxShowVideo.Image=Image.FromFile(name);
                    
                    i = i + 1;
                }
                Thread.Sleep(30);
            }
        }

        private void bt_OK_Click(object sender, EventArgs e)
        {
            SetCiols s = new SetCiols();
            this.Hide();
            s.Show();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {
          
        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Hide();
            new NightAlgorithmSelect().Show();
        }
    }
}
