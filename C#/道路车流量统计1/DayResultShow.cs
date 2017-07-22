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
    public partial class DayResultShow : Form
    {
        public DayResultShow()
        {
            InitializeComponent();
        }
        private void init()
        {
            label5.Text = Global.dayFeature;
            label6.Text = "" + Global.numberOfFrame;
            label7.Text = "" + Global.numberOfFrame / 30 + "秒";
            label8.Text = ""+Global.sum;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Hide();
            new 欢迎().Show();
        }

        private void DayResultShow_Load(object sender, EventArgs e)
        {
            init();
        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {

        }
    }
}
