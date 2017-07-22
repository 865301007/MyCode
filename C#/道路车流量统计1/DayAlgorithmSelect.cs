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
    public partial class DayAlgorithmSelect : Form
    {
        public DayAlgorithmSelect()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Global.dayFeature = "HOG特征";
            this.Hide();
            new DaytimeSelect().Show();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Global.dayFeature = "LBP特征";
            this.Hide();
            new DaytimeSelect().Show();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Global.dayFeature = "Haar特征";
            this.Hide();
            new DaytimeSelect().Show();
        }


    }
}
