package com.Axeryok.ColorUtility;

import java.util.List;
import net.minecraft.client.gui.GuiButton;

public class GuiFormatButton
  extends GuiButton
{
  public String code;
  public char charCode;
  
  public GuiFormatButton(int id, int width, int position, String code, String display)
  {
    super(id, width - (position / 11 + 1) * 40, position % 11 * 20 + 15, 20, 20, "§".concat(code).concat(display));
    this.code = "§".concat(code);
    this.charCode=this.code.charAt(1);
  }
  
  public static void editFormatButtonList(List currentButtonList, int baseid, int width)
  {
    List list = currentButtonList;
    String[] ColorCode = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "k", "l", "m", "n", "o", "r" };
    
    int i = 0;
    while (i != ColorCode.length)
    {
      list.add(new GuiFormatButton(baseid + i, width, i, ColorCode[i], ColorCode[i].equals("r") ? "reset" : "A"));
      i++;
    }
  }
}
