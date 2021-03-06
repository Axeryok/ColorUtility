package com.Axeryok.ColorUtility.adapter;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.Axeryok.ColorUtility.DeobfuscationHelper;
import com.Axeryok.ColorUtility.ModLog;

public class GuiEditSignAdapter extends ClassVisitor {
	String className="net.minecraft.client.gui.inventory.GuiEditSign";
	static String modifyingMethodName;
	public GuiEditSignAdapter(ClassVisitor cv) {
		super(Opcodes.ASM5,cv);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
		/* initGui末尾にcom.Axeryok.ColorUtility.editButtonList(this,this.buttonList)を挿入 */
		if("initGui".equals(name)||"func_73866_w_".equals(DeobfuscationHelper.mapMethodName(className, name, desc))){
			modifyingMethodName=name;
			return new MethodVisitor(Opcodes.ASM5, super.visitMethod(access, name, desc, signature, exceptions)){
				@Override
				public void visitInsn(int opcode){
					if(opcode==Opcodes.RETURN){
						ModLog.log("com.Axeryok.ColorUtility.editButtonList method has been added at end of "+modifyingMethodName+".");
						super.visitIntInsn(Opcodes.ALOAD, 0);
						super.visitIntInsn(Opcodes.ALOAD, 0);
						super.visitFieldInsn(Opcodes.GETFIELD, "net/minecraft/client/gui/inventory/GuiEditSign", "field_146292_n", "Ljava/util/List;");
						super.visitMethodInsn(Opcodes.INVOKESTATIC, "com/Axeryok/ColorUtility/ColorUtility", "editButtonList", "(Lnet/minecraft/client/gui/GuiScreen;Ljava/util/List;)V",false);
					}
					super.visitInsn(opcode);
				}
			};
		}
		/* actionPerformed末尾にcom.Axeryok.ColorUtility.performColorCode(this,this.button)を挿入 */
		else if("actionPerformed".equals(name)||"func_146284_a".equals(DeobfuscationHelper.mapMethodName(className, name, desc))){
			modifyingMethodName=name;
			return new MethodVisitor(Opcodes.ASM5, super.visitMethod(access, name, desc, signature, exceptions)){
				@Override
				public void visitCode(){
					//if(opcode==Opcodes.RETURN){
						ModLog.log("com.Axeryok.ColorUtility.performColorCode method has been added at end of "+modifyingMethodName+".");
						super.visitIntInsn(Opcodes.ALOAD, 0);
						super.visitIntInsn(Opcodes.ALOAD,1);
						super.visitMethodInsn(Opcodes.INVOKESTATIC, "com/Axeryok/ColorUtility/ColorUtility", "performColorCode", "(Lnet/minecraft/client/gui/GuiScreen;Lnet/minecraft/client/gui/GuiButton;)V",false);
					//}
					super.visitCode();
				}
			};
		}
		return super.visitMethod(access, name, desc, signature, exceptions);
	}
	
}
