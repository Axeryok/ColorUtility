package com.Axeryok.ColorUtility;

import org.apache.logging.log4j.Level;

import net.minecraftforge.fml.common.FMLLog;

public class ModLog {
	public static void log(String msg,Object...data){
		FMLLog.log("ColorUtility", Level.INFO, msg, data);
	}
}
