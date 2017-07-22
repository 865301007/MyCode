using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace 道路车流量统计
{
    public partial class NightAlgorithmSelect : Form
    {
        public NightAlgorithmSelect()
        {
            InitializeComponent();
        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Global.nightAglorith = "基于前车灯";
            Global.nightSpace = "RGB颜色空间";
            this.Hide();
            new NightTimeMain().Show();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.groupBox2.Visible = true;
            Global.nightAglorith = "基于后车灯";
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Global.nightSpace = "RGB颜色空间";
            this.Hide();
            new NightTimeMain().Show();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            Global.nightSpace = "HSV颜色空间";
            this.Hide();
            new NightTimeMain().Show();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            Global.nightSpace = "Lab颜色空间";
            this.Hide();
            new NightTimeMain().Show();
        }

        private void groupBox2_Enter(object sender, EventArgs e)
        {

        }



    }
}
