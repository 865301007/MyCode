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
    public partial class 欢迎 : Form
    {
        public 欢迎()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void bt_Start_Click(object sender, EventArgs e)
        {
            Global.daytime = true;
            new DayAlgorithmSelect().Show();
            this.Hide();
        }

        private void bt_NightTime_Click(object sender, EventArgs e)
        {
            Global.daytime = false;

            new NightAlgorithmSelect().Show();

            this.Hide();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            new 关于().Show();
            this.Hide();
        }
    }
}
