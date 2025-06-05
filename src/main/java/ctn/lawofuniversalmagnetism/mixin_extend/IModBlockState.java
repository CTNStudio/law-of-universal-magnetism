package ctn.lawofuniversalmagnetism.mixin_extend;

import ctn.lawofuniversalmagnetism.api.ForwardingMagnetismHandler;
import net.minecraft.nbt.CompoundTag;

public interface IModBlockState {
	CompoundTag getNbt();

	void setNbt(CompoundTag nbt);

	CompoundTag getMagnetismStorageNbt();

	void setMagnetismStorageNbt(ForwardingMagnetismHandler forwardingMagnetismHandler);

	void triggerUpdate();

	float getMagnetismValue();

	void setMagnetismValue(float magnetismValue);

	boolean isBipolar();

	void setIsBipolar(boolean isBipolar);

	float getAttenuationFactor();

	void setAttenuationFactor(float attenuationFactor);

	float getxRot();

	void setxRot(float xRot);

	float getyRot();

	void setyRot(float yRot);
}
