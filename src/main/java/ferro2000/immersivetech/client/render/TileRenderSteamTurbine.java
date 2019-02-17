package ferro2000.immersivetech.client.render;

import org.lwjgl.opengl.GL11;

import blusunrize.immersiveengineering.api.IEProperties;
import blusunrize.immersiveengineering.client.ClientUtils;
import ferro2000.immersivetech.common.ITContent;
import ferro2000.immersivetech.common.blocks.metal.tileentities.TileEntitySteamTurbine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;

public class TileRenderSteamTurbine extends TileEntitySpecialRenderer<TileEntitySteamTurbine> {
	
	@Override
	public void render(TileEntitySteamTurbine te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		
		if(!te.formed || te.isDummy() || !te.getWorld().isBlockLoaded(te.getPos(), false)) {
			return;
		}
		
		final BlockRendererDispatcher blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
		BlockPos blockPos = te.getPos();
		IBlockState state = getWorld().getBlockState(blockPos);
		
		if(state.getBlock() != ITContent.blockMetalMultiblock) {
			return;
		}
		
		state = state.getActualState(getWorld(), blockPos);
		state = state.withProperty(IEProperties.DYNAMICRENDER, true);
		IBakedModel model = blockRenderer.getBlockModelShapes().getModelForState(state);
		
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder worldRenderer = tessellator.getBuffer();
		
		ClientUtils.bindAtlas();
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.translate(.5, .5, .5);

		RenderHelper.disableStandardItemLighting();
		GlStateManager.blendFunc(770, 771);
		GlStateManager.enableBlend();
		GlStateManager.disableCull();
		
		if(Minecraft.isAmbientOcclusionEnabled()) {
			GlStateManager.shadeModel(7425);
		}else {
			GlStateManager.shadeModel(7424);
		}
		
		GlStateManager.rotate(te.getAnimation().getAnimationRotation() + (te.getAnimation().getAnimationStep()*partialTicks), te.facing.getXOffset(), 0, te.facing.getZOffset());
		
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		worldRenderer.setTranslation( -.5-blockPos.getX(), -.5-blockPos.getY(),  -.5-blockPos.getZ());
		worldRenderer.color(255, 255, 255, 255);
		blockRenderer.getBlockModelRenderer().renderModel(te.getWorld(), model, state, blockPos, worldRenderer,true);
		worldRenderer.setTranslation(0.0D, 0.0D, 0.0D);
		tessellator.draw();
		
		RenderHelper.enableStandardItemLighting();
		
		GlStateManager.popMatrix();
		
	}

}
