using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MathWorks.MATLAB.NET.Utility;
using MathWorks.MATLAB.NET.Arrays;

namespace 道路车流量统计
{
  public static  class Global
    {
      public static string fileName;
      public static MWArray fileName_Matlab;
      public static string nameWithoutPath;
      public static int numberOfFrame;
      public static int[] position = new int[4];
      public static int sum;
      public static bool daytime;
      public static string task;
      public static string dayFeature;
      public static string nightSpace;
      public static string nightAglorith;
      public static void setFileName(string s)
          {
              fileName = s;
              fileName_Matlab = (MWArray)s;
          }
    }
}
