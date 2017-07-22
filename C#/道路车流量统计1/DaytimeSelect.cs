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
    public partial class DaytimeSelect : Form
    {

        MWArray filename1;
        int numberOfFrame;
        string nameWithoutPath;
        public DaytimeSelect()
        {
            InitializeComponent();
            Control.CheckForIllegalCrossThreadCalls = false;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            selectVideo();
            
        }

        private void selectVideo()                             //选择视频
        {

            OpenFileDialog fileDialog = new OpenFileDialog();
            fileDialog.Multiselect = true;
            fileDialog.Title = "请选择文件";
            fileDialog.Filter = "mov文件(*.mov*)|*.mov*|mp4文件(*.mp4*)|*.mp4*|avi文件(*.avi*)|*.avi*";
            if (fileDialog.ShowDialog() == DialogResult.OK)
            {
                string file = fileDialog.FileName;

                Global.setFileName(file);
                filename1 = file;
                nameWithoutPath = Path.GetFileNameWithoutExtension(file);                 //获取文件名
                label1.Text = nameWithoutPath;
                Global.nameWithoutPath = nameWithoutPath;
                Thread s1 = new Thread(prossImage);
                s1.Start();
                //     MessageBox.Show("已选择文件:" + file, "选择文件提示", MessageBoxButtons.OK, MessageBoxIcon.Information);

                button1.Enabled = false;                           //选择按钮不可用
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
            button1.Enabled = true;
            OK.Enabled = true;
        }
        private void showImage()
        {

            Thread.Sleep(2000);

            int i = 1;

            while (i != numberOfFrame)
            {

                string name = "./image/" + nameWithoutPath + i + ".jpg";
                string nextname = "./image/" + nameWithoutPath + (i + 1) + ".jpg";
                label1.Text = name;
                if (File.Exists(@nextname))
                {
                    pictureBox1.Image = Image.FromFile(name);

                    i = i + 1;
                }
                Thread.Sleep(30);
            }
        }

        private void OK_Click(object sender, EventArgs e)
        {
            DayTimeSetCiols st = new DayTimeSetCiols();
            st.Show();
            this.Hide();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Hide();
            new DayAlgorithmSelect().Show();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }
    }
}
