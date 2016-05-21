package com.Axeryok.ColorUtility.adapter;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.Axeryok.ColorUtility.DeobfuscationHelper;
import com.Axeryok.ColorUtility.ModLog;

public class GuiEditSignAdapter extends ClassVisitor {
	String className="net.minecraft.client.gui.inventory.GuiEditSign";
	public GuiEditSignAdapter(ClassVisitor cv) {
		super(Opcodes.ASM4,cv);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
		/* initGui末尾にcom.Axeryok.ColorUtility.editButtonList(this,this.buttonList)を挿入 */
		if("func_73866_w_".equals(DeobfuscationHelper.mapMethodName(className, name, desc))){
			return new MethodVisitor(Opcodes.ASM4, super.visitMethod(access, name, desc, signature, exceptions)){
				@Override
				public void visitInsn(int opcode){
					if(opcode==Opcodes.RETURN){
						ModLog.log("Replace behavior.");
						super.visitVarInsn(Opcodes.ALOAD, 0);
						super.visitVarInsn(Opcodes.ALOAD, 0);
						super.visitFieldInsn(Opcodes.GETFIELD, "net/minecraft/client/gui/inventory/GuiEditSign", "field_146292_n", "Ljava/util/List;");
						super.visitMethodInsn(Opcodes.INVOKESTATIC, "com/Axeryok/ColorUtility/ColorUtility", "editButtonList", "(Lnet/minecraft/client/gui/GuiScreen;Ljava/util/List;)V");
						super.visitInsn(Opcodes.RETURN);
						return;
					}
					super.visitInsn(opcode);
				}
			};
		}
		/* performAcation末尾にcom.Axeryok.ColorUtility.performColorCode(this,this.button)を挿入 */
		else if("func_146284_a".equals(DeobfuscationHelper.mapMethodName(className, name, desc))){
			return new MethodVisitor(Opcodes.ASM4, super.visitMethod(access, name, desc, signature, exceptions)){
				@Override
				public void visitInsn(int opcode){
					if(opcode==Opcodes.RETURN){
						ModLog.log("Replace behavior.");
						super.visitVarInsn(Opcodes.ALOAD, 0);
						super.visitVarInsn(Opcodes.ALOAD,1);
						super.visitMethodInsn(Opcodes.INVOKESTATIC, "com/Axeryok/ColorUtility/ColorUtility", "performColorCode", "(Lnet/minecraft/client/gui/GuiScreen;Lnet/minecraft/client/gui/GuiButton;)V");
						super.visitInsn(Opcodes.RETURN);
						return;
					}
					super.visitInsn(opcode);
				}
			};
		}
		return super.visitMethod(access, name, desc, signature, exceptions);
	}
	
}
