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
    public partial class NightResultShow : Form
    {
        public NightResultShow()
        {
            InitializeComponent();
          
        }

        private void init()
        {
            label6.Text = Global.nightAglorith;
            label7.Text = Global.nightSpace;
            label8.Text = ""+Global.numberOfFrame;
            label9.Text = "" + (Global.numberOfFrame / 30) + "秒";
            label10.Text = "" + Global.sum;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Hide();
            new 欢迎().Show();
        }

        private void NightResultShow_Load(object sender, EventArgs e)
        {
            init();
        }

        private void label9_Click(object sender, EventArgs e)
        {

        }
    }
}
