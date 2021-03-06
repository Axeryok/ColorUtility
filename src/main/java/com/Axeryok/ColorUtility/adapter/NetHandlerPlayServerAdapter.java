package com.Axeryok.ColorUtility.adapter;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.Axeryok.ColorUtility.DeobfuscationHelper;
import com.Axeryok.ColorUtility.ModLog;

public class NetHandlerPlayServerAdapter extends ClassVisitor{
	String className="net.minecraft.network.NetHandlerPlayServer";
	static String modifyingMethodName;
	public NetHandlerPlayServerAdapter(ClassVisitor cv){
		super(Opcodes.ASM5,cv);
	}
	
	/* TextFormatting.getTextWithoutFormattingCodes 相当を com.Axeryok.ColorUtility.ColorUtility.returnSameObjectへ変更*/
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
		if("processUpdateSign".equals(name)||"func_147343_a".equals(DeobfuscationHelper.mapMethodName(className, name, desc))){
			modifyingMethodName=name;
			return new MethodVisitor(Opcodes.ASM5, super.visitMethod(access, name, desc, signature, exceptions)){
				@Override
				public void visitMethodInsn(int opcode, String owner, String name, String desc,boolean itf){
					if("getTextWithoutFormattingCodes".equals(name)||"func_110646_a".equals(DeobfuscationHelper.mapMethodName("net.minecraft.util.text.TextFormatting", name, desc))||"func_110646_a".equals(DeobfuscationHelper.mapMethodName("net.minecraft.util.EnumChatFormatting", name, desc))){
						ModLog.log("Modyfing so that "+modifyingMethodName+" uses com.Axeryok.ColorUtility.returnSameObject instead of "+name+".");
						super.visitMethodInsn(opcode, "com/Axeryok/ColorUtility/ColorUtility", "returnSameObject", desc,false);
					}
					else {
						super.visitMethodInsn(opcode, owner, name, desc,itf);
					}
				}
			};
		}
		return super.visitMethod(access, name, desc, signature, exceptions);
	}
}
