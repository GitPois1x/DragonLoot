package net.dragonloot.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class DragonHelmetModel extends Model {

    private final ModelPart root;
    private final ModelPart HornLD;
    private final ModelPart HornRD;
    private final ModelPart HornLU;

    public DragonHelmetModel(ModelPart root) {
        super(RenderLayer::getArmorCutoutNoCull);
        this.root = root.getChild("root");
        this.HornLU = this.root.getChild("HornLU");
        this.HornRD = this.root.getChild("HornRD");
        this.HornLD = this.root.getChild("HornLD");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData1 = modelPartData.addChild("root", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.1F)).uv(0, 16).cuboid(-3.0F, -7.0F, -7.0F, 6.0F, 4.0F, 3.0F), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        modelPartData1.addChild("HornLD", ModelPartBuilder.create().uv(34, 0).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F), ModelTransform.pivot(-3.0F, -5.0F, 3.0F));
        modelPartData1.addChild("HornRD", ModelPartBuilder.create().uv(34, 0).cuboid(-2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F), ModelTransform.pivot(4.0F, -5.0F, 3.0F));
        modelPartData1.addChild("HornLU", ModelPartBuilder.create().uv(24, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 6.0F).uv(24, 0).cuboid(4.8F, 0.0F, 0.0F, 2.0F, 2.0F, 6.0F), ModelTransform.pivot(-2.9F, -8.0F, 2.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.HornLU.pitch = 0.7854F;
        this.HornRD.yaw = 0.3927F;
        this.HornLD.yaw = -0.3927F;
        this.root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
