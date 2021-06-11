package net.dragonloot.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class DragonHelmetModel extends Model {
	private final ModelPart root;
	private final ModelPart base;
	private final ModelPart hornLU_r1;
	private final ModelPart hornLD_r1;
	private final ModelPart hornRD_r1;

	public DragonHelmetModel(ModelPart root) {
		super(RenderLayer::getArmorCutoutNoCull);
		this.root = root;
		this.base = root.getChild("base");
		this.hornLU_r1 = base.getChild("hornLU_r1");
		this.hornLD_r1 = base.getChild("hornLD_r1");
		this.hornRD_r1 = base.getChild("hornRD_r1");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData modelPartData2 = modelPartData.addChild("base", ModelPartBuilder.create().uv(32, 8)
				.cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 0.0F).uv(19, 28).cuboid(-3.0F, -7.0F, -6.99F, 6.0F, 4.0F, 0.0F)
				.uv(0, 16).cuboid(3.0F, -7.0F, -6.99F, 0.0F, 4.0F, 3.0F).uv(0, 16)
				.cuboid(-3.0F, -7.0F, -6.99F, 0.0F, 4.0F, 3.0F).uv(8, 17).cuboid(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F)
				.uv(32, 16).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 0.0F, 8.0F).uv(0, 29)
				.cuboid(-3.0F, -7.0F, -6.99F, 6.0F, 0.0F, 3.0F).uv(48, 0).cuboid(4.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F)
				.uv(48, 0).cuboid(-4.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F).uv(48, 0)
				.cuboid(-4.0F, -8.0F, 4.0F, 8.0F, 8.0F, 0.0F), ModelTransform.pivot(0.0F, 24.0F, 1.0F));
		modelPartData2.addChild("hornLU_r1", ModelPartBuilder.create().uv(24, 0)
				.cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 6.0F).cuboid(4.8F, 0.0F, 0.0F, 2.0F, 2.0F, 6.0F),
				ModelTransform.pivot(-2.9F, -8.0F, 1.0F));
		modelPartData2.addChild("hornLD_r1",
				ModelPartBuilder.create().uv(34, 0).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F),
				ModelTransform.pivot(-3.0F, -5.0F, 2.0F));
		modelPartData2.addChild("hornRD_r1",
				ModelPartBuilder.create().uv(34, 0).cuboid(-2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F),
				ModelTransform.pivot(4.0F, -5.0F, 2.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red,
			float green, float blue, float alpha) {
		this.hornLU_r1.pitch = 0.7854F;
		this.hornLD_r1.yaw = -0.3927F;
		this.hornRD_r1.yaw = 0.3927F;
		this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
