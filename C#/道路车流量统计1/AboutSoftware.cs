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
    public partial class 关于 : Form
    {
        public 关于()
        {
            InitializeComponent();
        }

        private void AboutSoftware_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {

            new 欢迎().Show();
            this.Hide();
        }
    }
}
