package com.Axeryok.ColorUtility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import com.Axeryok.ColorUtility.adapter.ChatAllowedCharactersAdapter;
import com.Axeryok.ColorUtility.adapter.GuiEditSignAdapter;
import com.Axeryok.ColorUtility.adapter.NetHandlerPlayServerAdapter;

import net.minecraft.launchwrapper.IClassTransformer;

public class ColorUtilityTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (transformedName.equals("net.minecraft.network.NetHandlerPlayServer")){
			ModLog.log("Open NetHandlerPlayServer.");
			
			return this.transformNetHandlerPlayServer(basicClass);
		}
		if (transformedName.equals("net.minecraft.util.ChatAllowedCharacters")){
			ModLog.log("Open ChatAllowedCharacters.");
			
			return this.transformChatAllowedCharacters(basicClass);
		}
		if (transformedName.equals("net.minecraft.client.gui.inventory.GuiEditSign")){
			ModLog.log("open GuiEditSign");
			
			return this.transformGuiEditSign(basicClass);
		}
		return basicClass;
	}
	
	private byte[] transformNetHandlerPlayServer(byte[] bytes){
		ClassReader cr=new ClassReader(bytes);
		ClassWriter cw =new ClassWriter(1);
		ClassVisitor cv= new NetHandlerPlayServerAdapter(cw);
		cr.accept(cv, 0);
		return cw.toByteArray();
		
	}
	
	private byte[] transformChatAllowedCharacters(byte[] bytes){
		ClassReader cr=new ClassReader(bytes);
		ClassWriter cw =new ClassWriter(1);
		ClassVisitor cv= new ChatAllowedCharactersAdapter(cw);
		cr.accept(cv, 0);
		return cw.toByteArray();
		
	}
	
	private byte[] transformGuiEditSign(byte[] bytes){
		ClassReader cr=new ClassReader(bytes);
		ClassWriter cw =new ClassWriter(1);
		ClassVisitor cv= new GuiEditSignAdapter(cw);
		cr.accept(cv, 0);
		return cw.toByteArray();
		
	}
	
}
