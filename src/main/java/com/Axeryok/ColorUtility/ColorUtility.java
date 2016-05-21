package com.Axeryok.ColorUtility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.VersionParser;

public class ColorUtility extends DummyModContainer {
	public ColorUtility(){
		super(new ModMetadata());
		ModMetadata meta=getMetadata();
		meta.name="ColorUtility";
		meta.modId="ColorUtility";
		meta.description="Make handling color code more easily.";
		meta.version="1.0.0";
		meta.authorList=Arrays.asList("Axer");
		meta.credits="";
		this.setEnabledState(true);
	}
	
	//TextFormatting.getTextWithoutFormattingCodes(String str)の代替
	public static String returnSameObject(String obj){
		return obj;
	}
	
	public static void editButtonList(GuiScreen obj,List<GuiButton> list){
		System.out.println(list);
		GuiFormatButton.editFormatButtonList(list, 20, obj.width);
	}
	
	public static void performColorCode(GuiScreen obj,GuiButton btn) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if(btn.id>=20&&btn.id<=42){
			if(obj instanceof GuiEditSign){
				Method m=obj.getClass().getDeclaredMethod("func_73869_a", char.class,int.class);
				m.setAccessible(true);
				m.invoke(obj,(char)167,(int)167);
				m.invoke(obj,(char)((GuiFormatButton)btn).charCode, (int)167);
			}
		}
	}
	
	@Override
	public List<ArtifactVersion> getDependencies()
	{
		LinkedList<ArtifactVersion> deps = new LinkedList<ArtifactVersion>();
		deps.add(VersionParser.parseVersionReference("after:CocoaInput"));
		return deps;
	}
	
	@Override
	public boolean registerBus(com.google.common.eventbus.EventBus bus, LoadController controller)
	  {
	    bus.register(this);
	    return true;
	  }
	
	@Subscribe
	  public void init(FMLInitializationEvent event){}
}
