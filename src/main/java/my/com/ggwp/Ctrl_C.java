package my.com.ggwp;

import java.awt.*;
import java.awt.datatransfer.*;

public class Ctrl_C implements ClipboardOwner
{
    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents)
    {
    }

    public void setClipboard(String string)
    {
        if(!(string.equals("")))
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(string), this);
    }
}