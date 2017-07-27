using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using System.Threading;
namespace 道路车流量统计
{
    public partial class DaytimeDetect : Form
    {
        int numberOfFrame = Global.numberOfFrame;
        string nameWithoutPath = Global.nameWithoutPath;
        private ProgressBar progressBar = new ProgressBar();
        private delegate void funHandle(int nValue);
        private funHandle myHandle = null;
        bool Flag = false;
        public DaytimeDetect()
        {
            InitializeComponent();
            Control.CheckForIllegalCrossThreadCalls = false;
            this.button1.Enabled = false;
        }

        private void DaytimeDetect_Load(object sender, EventArgs e)
        {
            System.Threading.Thread thread = new System.Threading.Thread(new System.Threading.ThreadStart(ThreadFun));
            thread.Start();
            Thread juge = new Thread(JugeWetherShowImage);
            juge.Start();
        }
        private void JugeWetherShowImage()
        {
            while (!Flag) ;
            Thread s = new Thread(showImage);
            s.Name = "显示视频";
            s.Start();
        }
    
        private void showImage()
        {
            label1.Text = "开始";
            Thread.Sleep(2000);

            int i = 1;

            while (i != numberOfFrame)
            {

                string name = "./image/Result" + nameWithoutPath + i + ".jpg";
                string nextname = "./image/Result" + nameWithoutPath + (i + 1) + ".jpg";
                label2.Text = "正在处理:" + name;

                if (File.Exists(@nextname))
                {

                    System.Drawing.Image img = System.Drawing.Image.FromFile(name);
                    System.Drawing.Image bmp = new System.Drawing.Bitmap(img);
                    img.Dispose();
                    this.pictureBox1.Image = bmp;
                    File.Delete(name);
                    i = i + 1;
                }

                Thread.Sleep(30);
            }
            File.Delete("./image/Result" + nameWithoutPath + Global.numberOfFrame + ".jpg");

            this.button1.Enabled = true;
            this.button2.Enabled = true;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            欢迎 欢 = new 欢迎();
            欢.Show();
            this.Hide();
        }

        private void showProgressBar()
        {

        }
        private void ShowProgressBar()
        {
            myHandle = new funHandle(progressBar.SetProgressValue);
            this.progressBar.ShowDialog();
        }

        private void ThreadFun()
        {
            MethodInvoker mi = new MethodInvoker(ShowProgressBar);
            this.BeginInvoke(mi);

            System.Threading.Thread.Sleep(1000); // sleep to show window  

            for (int i = 0; i < 1000; ++i)
            {
                System.Threading.Thread.Sleep(10);
                // 这里直接调用代理  
                this.Invoke(this.myHandle, new object[] { (i / 10) });

            }
            Flag = true;
        }

        private void label2_TextChanged(object sender, EventArgs e)
        {
            if (this.label2.Text == "结束")
            {
                this.Hide();
                new DayResultShow().Show();
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Hide();
            new DayResultShow().Show();
        }  
    }
}
