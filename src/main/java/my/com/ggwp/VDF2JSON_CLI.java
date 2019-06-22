package my.com.ggwp;

import java.io.*;

public class VDF2JSON_CLI
{
    public static void dispHelp()
    {
        System.out.println("Converts Valve Data Format (VDF) to JSON.\n");
        System.out.println("java -jar (jar name) </? | -? | /h | -h | /help | -help | --help>");
        System.out.println("java -jar (jar name) </gui | -gui | --gui> [inputfilename]");
        System.out.println("java -jar (jar name) <inputfilename> [outputfilename]\n");
        System.out.println("  /?, -?, /h, -h, /help, -help, --help");
        System.out.println("  \tDisplays this help. Overrides all other arguments.\n");
        System.out.println("  /gui, -gui, --gui");
        System.out.println("  \tStarts GUI. If inputfilename is also specified, when the GUI starts,");
        System.out.println("  \tit will load the file in the input text area.\n");
        System.out.println("If only inputfilename is specified, the output will be displayed on screen.");
        System.out.println("If no arguments are specified, it'll default to display this help.");
    }

    public static void main(String[] args)
    {
        if(args.length>0)
        {
            boolean help=false, gui=false;
            String inputfile="", outputfile="";
            for(String s: args)
            {
                if((s.equals("/?"))
                 ||(s.equals("-?"))
                 ||(s.toLowerCase().equals("/h"))
                 ||(s.toLowerCase().equals("-h"))
                 ||(s.toLowerCase().equals("/help"))
                 ||(s.toLowerCase().equals("-help"))
                 ||(s.toLowerCase().equals("--help")))
                    help=true;
                else if((s.toLowerCase().equals("/gui"))
                      ||(s.toLowerCase().equals("-gui"))
                      ||(s.toLowerCase().equals("--gui")))
                    gui=true;
                else if(inputfile.equals(""))
                    inputfile=s;
                else if(outputfile.equals(""))
                    outputfile=s;
            }
            if(help)
                dispHelp();
            else if(gui)
                VDF2JSON_GUI.startGUI(inputfile);
            else
            {
                try
                {
                    String outputstr=VDF2JSON_Common.convertFile2JSON(inputfile);
                    if(outputstr.equals(""))
                        System.out.println("[Error] Conversion failed.");
                    else
                    {
                        if(outputfile.equals(""))
                            System.out.println(outputstr);
                        else
                        {
                            OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(outputfile));
                            osw.write(outputstr, 0, outputstr.length());
                            osw.flush();
                        }
                    }
                }
                catch(IOException e)
                {
                    System.out.println("[Exception|VDF2JSON_CLI.main] Shit, something happened.");
                    System.out.println("------------------------------- Stack Trace Begin -------------------------------");
                    e.printStackTrace();
                    System.out.println("-------------------------------- Stack Trace End --------------------------------\n");
                }
            }
        }
        else
            dispHelp();
    }
}