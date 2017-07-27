namespace 道路车流量统计
{
    partial class 欢迎
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.bt_Daytime = new System.Windows.Forms.Button();
            this.bt_NightTime = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // bt_Daytime
            // 
            this.bt_Daytime.BackColor = System.Drawing.Color.Transparent;
            this.bt_Daytime.Cursor = System.Windows.Forms.Cursors.Hand;
            this.bt_Daytime.FlatAppearance.BorderSize = 0;
            this.bt_Daytime.FlatAppearance.MouseDownBackColor = System.Drawing.Color.Transparent;
            this.bt_Daytime.FlatAppearance.MouseOverBackColor = System.Drawing.Color.Transparent;
            this.bt_Daytime.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.bt_Daytime.Location = new System.Drawing.Point(631, 381);
            this.bt_Daytime.Name = "bt_Daytime";
            this.bt_Daytime.Size = new System.Drawing.Size(195, 49);
            this.bt_Daytime.TabIndex = 1;
            this.bt_Daytime.UseVisualStyleBackColor = false;
            this.bt_Daytime.Click += new System.EventHandler(this.bt_Start_Click);
            // 
            // bt_NightTime
            // 
            this.bt_NightTime.BackColor = System.Drawing.Color.Transparent;
            this.bt_NightTime.Cursor = System.Windows.Forms.Cursors.Hand;
            this.bt_NightTime.FlatAppearance.BorderSize = 0;
            this.bt_NightTime.FlatAppearance.MouseDownBackColor = System.Drawing.Color.Transparent;
            this.bt_NightTime.FlatAppearance.MouseOverBackColor = System.Drawing.Color.Transparent;
            this.bt_NightTime.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.bt_NightTime.ForeColor = System.Drawing.Color.White;
            this.bt_NightTime.Location = new System.Drawing.Point(133, 381);
            this.bt_NightTime.Name = "bt_NightTime";
            this.bt_NightTime.Size = new System.Drawing.Size(195, 49);
            this.bt_NightTime.TabIndex = 2;
            this.bt_NightTime.UseVisualStyleBackColor = false;
            this.bt_NightTime.Click += new System.EventHandler(this.bt_NightTime_Click);
            // 
            // button1
            // 
            this.button1.BackColor = System.Drawing.Color.Transparent;
            this.button1.Cursor = System.Windows.Forms.Cursors.Hand;
            this.button1.FlatAppearance.BorderSize = 0;
            this.button1.FlatAppearance.MouseDownBackColor = System.Drawing.Color.Transparent;
            this.button1.FlatAppearance.MouseOverBackColor = System.Drawing.Color.Transparent;
            this.button1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.button1.Font = new System.Drawing.Font("华文细黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.button1.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.button1.Location = new System.Drawing.Point(880, 0);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(81, 36);
            this.button1.TabIndex = 3;
            this.button1.Text = "关于软件";
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // 欢迎
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = global::道路车流量统计.Properties.Resources.form1;
            this.ClientSize = new System.Drawing.Size(960, 539);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.bt_NightTime);
            this.Controls.Add(this.bt_Daytime);
            this.Name = "欢迎";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button bt_Daytime;
        private System.Windows.Forms.Button bt_NightTime;
        private System.Windows.Forms.Button button1;
    }
}

