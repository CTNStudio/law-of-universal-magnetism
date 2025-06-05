package ctn.lawofuniversalmagnetism.mixin_extend;

import ctn.lawofuniversalmagnetism.api.ForwardingMagnetismHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockBehaviour;

public interface IModProperties {
	BlockBehaviour.Properties getThis();

	float getRandomAttenuation();

	BlockBehaviour.Properties setRandomAttenuation(float randomAttenuation);

	boolean getIsBipolar();

	BlockBehaviour.Properties setIsBipolar(boolean isBipolar);

	float getMagnetismValue();

	BlockBehaviour.Properties setMagnetismValue(float magnetismValue);

	float getxRot();

	BlockBehaviour.Properties setxRot(float xRot);

	float getyRot();

	BlockBehaviour.Properties setyRot(float yRot);

	float getAttenuationFact();

	BlockBehaviour.Properties setAttenuationFactor(float attenuationFactor);

	BlockBehaviour.Properties setMagnetismStorage(float randomAttenuation, ForwardingMagnetismHandler forwardingMagnetismHandler);

	BlockBehaviour.Properties setMagnetismStorage(float randomAttenuation, float magnetismValue, float xRot, float yRot, boolean isBipolar, float attenuationFactor);

	ForwardingMagnetismHandler getMagnetismStorage();

	CompoundTag getMagnetismStorageNbt();
}
