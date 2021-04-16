package net.dragonloot.entity.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class DragonHelmetModel extends Model {
	private final ModelPart base;
	private final ModelPart HornLU_r1;
	private final ModelPart HornLD_r1;
	private final ModelPart HornRD_r1;

	public DragonHelmetModel() {
		super(RenderLayer::getEntityCutout);
		base = new ModelPart(64, 32, 0, 0);
		base.setPivot(0.0F, 24.0F, 1.0F);
		base.setTextureOffset(32, 8).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 0.0F, 0.0F, false);
		base.setTextureOffset(19, 28).addCuboid(-3.0F, -7.0F, -6.99F, 6.0F, 4.0F, 0.0F, 0.0F, false);
		base.setTextureOffset(0, 16).addCuboid(3.0F, -7.0F, -6.99F, 0.0F, 4.0F, 3.0F, 0.0F, false);
		base.setTextureOffset(0, 16).addCuboid(-3.0F, -7.0F, -6.99F, 0.0F, 4.0F, 3.0F, 0.0F, false);
		base.setTextureOffset(8, 17).addCuboid(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, 0.0F, false);
		base.setTextureOffset(32, 16).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 0.0F, 8.0F, 0.0F, false);
		base.setTextureOffset(0, 29).addCuboid(-3.0F, -7.0F, -6.99F, 6.0F, 0.0F, 3.0F, 0.0F, false);
		base.setTextureOffset(48, 0).addCuboid(4.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, false);
		base.setTextureOffset(48, 0).addCuboid(-4.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, false);
		base.setTextureOffset(48, 0).addCuboid(-4.0F, -8.0F, 4.0F, 8.0F, 8.0F, 0.0F, 0.0F, false);

		HornLU_r1 = new ModelPart(64, 32, 0, 0);
		HornLU_r1.setPivot(-2.9F, -8.0F, 1.0F);
		base.addChild(HornLU_r1);
		HornLU_r1.setTextureOffset(24, 0).addCuboid(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		HornLU_r1.setTextureOffset(24, 0).addCuboid(4.8F, 0.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		HornLD_r1 = new ModelPart(64, 32, 0, 0);
		HornLD_r1.setPivot(-3.0F, -5.0F, 2.0F);
		base.addChild(HornLD_r1);
		HornLD_r1.setTextureOffset(34, 0).addCuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		HornRD_r1 = new ModelPart(64, 32, 0, 0);
		HornRD_r1.setPivot(4.0F, -5.0F, 2.0F);
		base.addChild(HornRD_r1);
		HornRD_r1.setTextureOffset(34, 0).addCuboid(-2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red,
			float green, float blue, float alpha) {
		this.HornLU_r1.pitch = 0.7854F;
		this.HornLD_r1.yaw = -0.3927F;
		this.HornRD_r1.yaw = 0.3927F;
		this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
