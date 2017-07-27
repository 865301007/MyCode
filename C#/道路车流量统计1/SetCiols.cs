using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Windows.Forms;
using NightDetect;
using MathWorks.MATLAB.NET.Arrays;
using MathWorks.MATLAB.NET.Utility;
using System.IO;
using HeadlightTracking;
namespace 道路车流量统计
{
    public partial class SetCiols : Form
    {
        private bool m_bMouseDown = false;
        private Point m_ptStart = new Point(0, 0);
        private Point m_ptEnd = new Point(0, 0);
        private int X, Y, W, H;
        public SetCiols()
        {
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("./image/" + Global.nameWithoutPath + 1 + ".jpg");
            this.button1.Enabled = false;
        }

        private void SetCiols_Load(object sender, EventArgs e)
        {

        }

        private void pictureBox1_MouseDown(object sender, MouseEventArgs e)
        {
            if (e.Button != MouseButtons.Left)
            {
                return;
            }

            if (!m_bMouseDown)
            {
                m_ptStart = new Point(e.X, e.Y);
                m_ptEnd = new Point(e.X, e.Y);
            }
            m_bMouseDown = !m_bMouseDown;  
        }

        private void pictureBox1_MouseUp(object sender, MouseEventArgs e)
        {
            if (e.Button != MouseButtons.Left)
            {
                return;
            }

            if (m_ptStart.X >= 0 && m_ptEnd.X >= 0
                 && m_ptStart.Y >= 0 && m_ptEnd.Y >= 0
                 && m_ptStart.X <= 254 && m_ptEnd.X <= 254
                 && m_ptStart.Y <= 163 && m_ptEnd.Y <= 163)
            {
                m_ptEnd = new Point(e.X, e.Y);
                m_bMouseDown = !m_bMouseDown;
                this.pictureBox1.Refresh();
            }
            else
            {
                m_ptEnd = m_ptStart;
                m_bMouseDown = !m_bMouseDown;
             //   this.pictureBox1.Refresh();
            }  
        }

        private void pictureBox1_Paint(object sender, PaintEventArgs e)
        {
            if (m_ptStart.Equals(m_ptEnd)) return;

            //    e.Graphics.DrawLine(System.Drawing.Pens.Red, m_ptStart, m_ptEnd);

            //画矩形加上以下六行  
            if (m_ptEnd.X - m_ptStart.X < 0 || m_ptEnd.Y - m_ptStart.Y < 0)
            {
                return;
            }

            e.Graphics.DrawRectangle(System.Drawing.Pens.Yellow, m_ptStart.X, m_ptStart.Y, m_ptEnd.X - m_ptStart.X, m_ptEnd.Y - m_ptStart.Y);
            label2.Text = "" + m_ptStart.X + ":" + m_ptStart.Y + ":" + (m_ptEnd.X - m_ptStart.X) + ":" + (m_ptEnd.Y - m_ptStart.Y);
            this.X = m_ptStart.X*4/3;
            this.Y = m_ptStart.Y*4/3;
            this.W = (m_ptEnd.X - m_ptStart.X)*4/3;
            this.H = (m_ptEnd.Y - m_ptStart.Y)*4/3;
            this.textBox1.Text = m_ptStart.X.ToString();
            this.textBox2.Text = m_ptStart.Y.ToString();
            this.textBox3.Text = (m_ptEnd.X - m_ptStart.X).ToString();
            this.textBox4.Text = (m_ptEnd.Y - m_ptStart.Y).ToString();
            this.button1.Enabled = true;
        }

        private void pictureBox1_MouseMove(object sender, MouseEventArgs e)
        {
            if (e.Button != MouseButtons.Left)
            {
                return;
            }

            m_ptEnd = new Point(e.X, e.Y);
            this.pictureBox1.Refresh();  
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Global.position[0] = this.X;
            Global.position[1] = this.Y;
            Global.position[2] = this.W;
            Global.position[3] = this.H;

            if (!Global.daytime)
            {
                Thread s = new Thread(prosse);
                s.Name = "MatlabDetect";
                s.Start();
                this.Hide();
                new NightTimeDetect().Show();
            }
            else
            {
                DaytimeDetect dd=new DaytimeDetect();
                dd.Show();
                this.Hide();
            }
        }

        private void prosse()
        {
            string name = "./image/Result" + Global.nameWithoutPath + (Global.numberOfFrame/2) + ".jpg";

            if (File.Exists(@name))
            {

            }
            else
            {
                if (Global.task.Equals("尾灯"))
                    Global.sum = (int)(MWNumericArray)new NightDetect.NightDetecter().NightDetect(Global.fileName_Matlab, (MWNumericArray)Global.position);
                else
                    Global.sum = (int)(MWNumericArray)new HeadlightTracking.HeadLight().HeadlightTracking(Global.fileName_Matlab, (MWNumericArray)Global.position);

            }
            }

        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }
    }
}
