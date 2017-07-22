/*
* MATLAB Compiler: 6.2 (R2016a)
* Date: Fri Nov 11 15:50:39 2016
* Arguments: "-B" "macro_default" "-W" "dotnet:SizeOfVideo,MyMatlab,0.0,private" "-T"
* "link:lib" "-d" "C:\Users\Lenovo\Desktop\课业\软件著作\code\SizeOfVideo\for_testing"
* "-v"
* "class{MyMatlab:C:\Users\Lenovo\Desktop\课业\软件著作\code\ShowIndexOfVideo.m,C:\Users\L
* enovo\Desktop\课业\软件著作\code\ShowVideoTest.m,C:\Users\Lenovo\Desktop\课业\软件著作\c
* ode\SizeOfVideo.m}" 
*/
using System;
using System.Reflection;
using System.IO;
using MathWorks.MATLAB.NET.Arrays;
using MathWorks.MATLAB.NET.Utility;

#if SHARED
[assembly: System.Reflection.AssemblyKeyFile(@"")]
#endif

namespace SizeOfVideoNative
{

  /// <summary>
  /// The MyMatlab class provides a CLS compliant, Object (native) interface to the
  /// MATLAB functions contained in the files:
  /// <newpara></newpara>
  /// C:\Users\Lenovo\Desktop\课业\软件著作\code\ShowIndexOfVideo.m
  /// <newpara></newpara>
  /// C:\Users\Lenovo\Desktop\课业\软件著作\code\ShowVideoTest.m
  /// <newpara></newpara>
  /// C:\Users\Lenovo\Desktop\课业\软件著作\code\SizeOfVideo.m
  /// </summary>
  /// <remarks>
  /// @Version 0.0
  /// </remarks>
  public class MyMatlab : IDisposable
  {
    #region Constructors

    /// <summary internal= "true">
    /// The static constructor instantiates and initializes the MATLAB Runtime instance.
    /// </summary>
    static MyMatlab()
    {
      if (MWMCR.MCRAppInitialized)
      {
        try
        {
          Assembly assembly= Assembly.GetExecutingAssembly();

          string ctfFilePath= assembly.Location;

          int lastDelimiter= ctfFilePath.LastIndexOf(@"\");

          ctfFilePath= ctfFilePath.Remove(lastDelimiter, (ctfFilePath.Length - lastDelimiter));

          string ctfFileName = "SizeOfVideo.ctf";

          Stream embeddedCtfStream = null;

          String[] resourceStrings = assembly.GetManifestResourceNames();

          foreach (String name in resourceStrings)
          {
            if (name.Contains(ctfFileName))
            {
              embeddedCtfStream = assembly.GetManifestResourceStream(name);
              break;
            }
          }
          mcr= new MWMCR("",
                         ctfFilePath, embeddedCtfStream, true);
        }
        catch(Exception ex)
        {
          ex_ = new Exception("MWArray assembly failed to be initialized", ex);
        }
      }
      else
      {
        ex_ = new ApplicationException("MWArray assembly could not be initialized");
      }
    }


    /// <summary>
    /// Constructs a new instance of the MyMatlab class.
    /// </summary>
    public MyMatlab()
    {
      if(ex_ != null)
      {
        throw ex_;
      }
    }


    #endregion Constructors

    #region Finalize

    /// <summary internal= "true">
    /// Class destructor called by the CLR garbage collector.
    /// </summary>
    ~MyMatlab()
    {
      Dispose(false);
    }


    /// <summary>
    /// Frees the native resources associated with this object
    /// </summary>
    public void Dispose()
    {
      Dispose(true);

      GC.SuppressFinalize(this);
    }


    /// <summary internal= "true">
    /// Internal dispose function
    /// </summary>
    protected virtual void Dispose(bool disposing)
    {
      if (!disposed)
      {
        disposed= true;

        if (disposing)
        {
          // Free managed resources;
        }

        // Free native resources
      }
    }


    #endregion Finalize

    #region Methods

    /// <summary>
    /// Provides a single output, 0-input Objectinterface to the ShowIndexOfVideo MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <returns>An Object containing the first output argument.</returns>
    ///
    public Object ShowIndexOfVideo()
    {
      return mcr.EvaluateFunction("ShowIndexOfVideo", new Object[]{});
    }


    /// <summary>
    /// Provides a single output, 1-input Objectinterface to the ShowIndexOfVideo MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="filename">Input argument #1</param>
    /// <returns>An Object containing the first output argument.</returns>
    ///
    public Object ShowIndexOfVideo(Object filename)
    {
      return mcr.EvaluateFunction("ShowIndexOfVideo", filename);
    }


    /// <summary>
    /// Provides a single output, 2-input Objectinterface to the ShowIndexOfVideo MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="filename">Input argument #1</param>
    /// <param name="n">Input argument #2</param>
    /// <returns>An Object containing the first output argument.</returns>
    ///
    public Object ShowIndexOfVideo(Object filename, Object n)
    {
      return mcr.EvaluateFunction("ShowIndexOfVideo", filename, n);
    }


    /// <summary>
    /// Provides the standard 0-input Object interface to the ShowIndexOfVideo MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="numArgsOut">The number of output arguments to return.</param>
    /// <returns>An Array of length "numArgsOut" containing the output
    /// arguments.</returns>
    ///
    public Object[] ShowIndexOfVideo(int numArgsOut)
    {
      return mcr.EvaluateFunction(numArgsOut, "ShowIndexOfVideo", new Object[]{});
    }


    /// <summary>
    /// Provides the standard 1-input Object interface to the ShowIndexOfVideo MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="numArgsOut">The number of output arguments to return.</param>
    /// <param name="filename">Input argument #1</param>
    /// <returns>An Array of length "numArgsOut" containing the output
    /// arguments.</returns>
    ///
    public Object[] ShowIndexOfVideo(int numArgsOut, Object filename)
    {
      return mcr.EvaluateFunction(numArgsOut, "ShowIndexOfVideo", filename);
    }


    /// <summary>
    /// Provides the standard 2-input Object interface to the ShowIndexOfVideo MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="numArgsOut">The number of output arguments to return.</param>
    /// <param name="filename">Input argument #1</param>
    /// <param name="n">Input argument #2</param>
    /// <returns>An Array of length "numArgsOut" containing the output
    /// arguments.</returns>
    ///
    public Object[] ShowIndexOfVideo(int numArgsOut, Object filename, Object n)
    {
      return mcr.EvaluateFunction(numArgsOut, "ShowIndexOfVideo", filename, n);
    }


    /// <summary>
    /// Provides an interface for the ShowIndexOfVideo function in which the input and
    /// output
    /// arguments are specified as an array of Objects.
    /// </summary>
    /// <remarks>
    /// This method will allocate and return by reference the output argument
    /// array.<newpara></newpara>
    /// </remarks>
    /// <param name="numArgsOut">The number of output arguments to return</param>
    /// <param name= "argsOut">Array of Object output arguments</param>
    /// <param name= "argsIn">Array of Object input arguments</param>
    /// <param name= "varArgsIn">Array of Object representing variable input
    /// arguments</param>
    ///
    [MATLABSignature("ShowIndexOfVideo", 2, 1, 0)]
    protected void ShowIndexOfVideo(int numArgsOut, ref Object[] argsOut, Object[] argsIn, params Object[] varArgsIn)
    {
        mcr.EvaluateFunctionForTypeSafeCall("ShowIndexOfVideo", numArgsOut, ref argsOut, argsIn, varArgsIn);
    }
    /// <summary>
    /// Provides a single output, 0-input Objectinterface to the ShowVideoTest MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <returns>An Object containing the first output argument.</returns>
    ///
    public Object ShowVideoTest()
    {
      return mcr.EvaluateFunction("ShowVideoTest", new Object[]{});
    }


    /// <summary>
    /// Provides a single output, 1-input Objectinterface to the ShowVideoTest MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="filename">Input argument #1</param>
    /// <returns>An Object containing the first output argument.</returns>
    ///
    public Object ShowVideoTest(Object filename)
    {
      return mcr.EvaluateFunction("ShowVideoTest", filename);
    }


    /// <summary>
    /// Provides the standard 0-input Object interface to the ShowVideoTest MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="numArgsOut">The number of output arguments to return.</param>
    /// <returns>An Array of length "numArgsOut" containing the output
    /// arguments.</returns>
    ///
    public Object[] ShowVideoTest(int numArgsOut)
    {
      return mcr.EvaluateFunction(numArgsOut, "ShowVideoTest", new Object[]{});
    }


    /// <summary>
    /// Provides the standard 1-input Object interface to the ShowVideoTest MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="numArgsOut">The number of output arguments to return.</param>
    /// <param name="filename">Input argument #1</param>
    /// <returns>An Array of length "numArgsOut" containing the output
    /// arguments.</returns>
    ///
    public Object[] ShowVideoTest(int numArgsOut, Object filename)
    {
      return mcr.EvaluateFunction(numArgsOut, "ShowVideoTest", filename);
    }


    /// <summary>
    /// Provides an interface for the ShowVideoTest function in which the input and
    /// output
    /// arguments are specified as an array of Objects.
    /// </summary>
    /// <remarks>
    /// This method will allocate and return by reference the output argument
    /// array.<newpara></newpara>
    /// </remarks>
    /// <param name="numArgsOut">The number of output arguments to return</param>
    /// <param name= "argsOut">Array of Object output arguments</param>
    /// <param name= "argsIn">Array of Object input arguments</param>
    /// <param name= "varArgsIn">Array of Object representing variable input
    /// arguments</param>
    ///
    [MATLABSignature("ShowVideoTest", 1, 1, 0)]
    protected void ShowVideoTest(int numArgsOut, ref Object[] argsOut, Object[] argsIn, params Object[] varArgsIn)
    {
        mcr.EvaluateFunctionForTypeSafeCall("ShowVideoTest", numArgsOut, ref argsOut, argsIn, varArgsIn);
    }
    /// <summary>
    /// Provides a single output, 0-input Objectinterface to the SizeOfVideo MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <returns>An Object containing the first output argument.</returns>
    ///
    public Object SizeOfVideo()
    {
      return mcr.EvaluateFunction("SizeOfVideo", new Object[]{});
    }


    /// <summary>
    /// Provides a single output, 1-input Objectinterface to the SizeOfVideo MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="filename">Input argument #1</param>
    /// <returns>An Object containing the first output argument.</returns>
    ///
    public Object SizeOfVideo(Object filename)
    {
      return mcr.EvaluateFunction("SizeOfVideo", filename);
    }


    /// <summary>
    /// Provides the standard 0-input Object interface to the SizeOfVideo MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="numArgsOut">The number of output arguments to return.</param>
    /// <returns>An Array of length "numArgsOut" containing the output
    /// arguments.</returns>
    ///
    public Object[] SizeOfVideo(int numArgsOut)
    {
      return mcr.EvaluateFunction(numArgsOut, "SizeOfVideo", new Object[]{});
    }


    /// <summary>
    /// Provides the standard 1-input Object interface to the SizeOfVideo MATLAB
    /// function.
    /// </summary>
    /// <remarks>
    /// </remarks>
    /// <param name="numArgsOut">The number of output arguments to return.</param>
    /// <param name="filename">Input argument #1</param>
    /// <returns>An Array of length "numArgsOut" containing the output
    /// arguments.</returns>
    ///
    public Object[] SizeOfVideo(int numArgsOut, Object filename)
    {
      return mcr.EvaluateFunction(numArgsOut, "SizeOfVideo", filename);
    }


    /// <summary>
    /// Provides an interface for the SizeOfVideo function in which the input and output
    /// arguments are specified as an array of Objects.
    /// </summary>
    /// <remarks>
    /// This method will allocate and return by reference the output argument
    /// array.<newpara></newpara>
    /// </remarks>
    /// <param name="numArgsOut">The number of output arguments to return</param>
    /// <param name= "argsOut">Array of Object output arguments</param>
    /// <param name= "argsIn">Array of Object input arguments</param>
    /// <param name= "varArgsIn">Array of Object representing variable input
    /// arguments</param>
    ///
    [MATLABSignature("SizeOfVideo", 1, 1, 0)]
    protected void SizeOfVideo(int numArgsOut, ref Object[] argsOut, Object[] argsIn, params Object[] varArgsIn)
    {
        mcr.EvaluateFunctionForTypeSafeCall("SizeOfVideo", numArgsOut, ref argsOut, argsIn, varArgsIn);
    }

    /// <summary>
    /// This method will cause a MATLAB figure window to behave as a modal dialog box.
    /// The method will not return until all the figure windows associated with this
    /// component have been closed.
    /// </summary>
    /// <remarks>
    /// An application should only call this method when required to keep the
    /// MATLAB figure window from disappearing.  Other techniques, such as calling
    /// Console.ReadLine() from the application should be considered where
    /// possible.</remarks>
    ///
    public void WaitForFiguresToDie()
    {
      mcr.WaitForFiguresToDie();
    }



    #endregion Methods

    #region Class Members

    private static MWMCR mcr= null;

    private static Exception ex_= null;

    private bool disposed= false;

    #endregion Class Members
  }
}
