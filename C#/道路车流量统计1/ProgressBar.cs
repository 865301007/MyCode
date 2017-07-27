using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Threading;
namespace 道路车流量统计
{
    public partial class ProgressBar : Form
    {
        public ProgressBar()
        {
            Control.CheckForIllegalCrossThreadCalls = false;
            InitializeComponent();
        }

        private void ProgressBar_Load(object sender, EventArgs e)
        {
            
        }

        public void SetProgressValue(int value)
        {
            this.progressBar1.Value = value;
            this.label1.Text = "Progress :" + value.ToString() + "%";

            // 这里关闭，比较好，呵呵！  
            if (value == this.progressBar1.Maximum - 1) this.Close();
        }  

        private void label1_Click(object sender, EventArgs e)
        {

        }  
    }
}
