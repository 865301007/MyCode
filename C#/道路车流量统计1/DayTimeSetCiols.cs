using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MathWorks.MATLAB.NET.Utility;
using MathWorks.MATLAB.NET.Arrays;
using System.IO;
using System.Threading;
using cardetector;
namespace 道路车流量统计
{
    public partial class DayTimeSetCiols : Form
    {
        private bool m_bMouseDown = false;
        private Point m_ptStart = new Point(0, 0);
        private Point m_ptEnd = new Point(0, 0);
        private int X, Y, W, H;
        private string modelName = "carRearDetector06.xml";
        public DayTimeSetCiols()
        {
            InitializeComponent();
             pictureBox1.Image = Image.FromFile("./image/" + Global.nameWithoutPath + 1 + ".jpg");
            this.button1.Enabled = false;
            Control.CheckForIllegalCrossThreadCalls = false;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Global.position[0] = this.X;
            Global.position[1] = this.Y;
            Global.position[2] = this.W;
            Global.position[3] = this.H;
            Thread s = new Thread(prosse);
            s.Name = "DayTimeDetect";
            s.Start();
            this.Hide();
            new DaytimeDetect().Show();
        }
        private void prosse()
        {
            string name = "./image/Result" + Global.nameWithoutPath + (Global.numberOfFrame / 2) + ".jpg";

            if (File.Exists(@name))
            {
                MessageBox.Show(Global.nameWithoutPath);
            }
            else
            Global.sum = (int)(MWNumericArray)new cardetector.DayTimeDetect().cardetector(Global.fileName_Matlab, modelName, (MWNumericArray)Global.position);
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
            this.X = m_ptStart.X * 4 / 3;
            this.Y = m_ptStart.Y * 4 / 3;
            this.W = (m_ptEnd.X - m_ptStart.X) * 4 / 3;
            this.H = (m_ptEnd.Y - m_ptStart.Y) * 4 / 3;
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

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            
           
        }

        private void textBox1_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!Char.IsNumber(e.KeyChar) && e.KeyChar != (char)8)
            {
                e.Handled = true;
            }
        }

        private void textBox1_Leave(object sender, EventArgs e)
        {
          
        }
    }
}
