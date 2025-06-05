package ctn.lawofuniversalmagnetism.mixin;

import ctn.lawofuniversalmagnetism.api.ForwardingMagnetismHandler;
import ctn.lawofuniversalmagnetism.mixin_extend.IModProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockBehaviour.Properties.class)
@Implements(@Interface(iface = IModProperties.class, prefix = "loumImp$"))
public abstract class BlockBehaviour$PropertiesMixin implements IModProperties {
	@Unique
	public float   loum$randomAttenuation;
	@Unique
	public boolean loum$isBipolar;
	@Unique
	public float   loum$magnetismValue;
	@Unique
	public float   loum$xRot;
	@Unique
	public float   loum$yRot;
	@Unique
	public float   loum$attenuationFact;

	@Unique
	public float loumImp$getRandomAttenuation() {
		return loum$randomAttenuation;
	}

	@Unique
	public BlockBehaviour.Properties loumImp$setRandomAttenuation(float randomAttenuation) {
		loum$randomAttenuation = randomAttenuation;
		return getThis();
	}

	@Unique
	public BlockBehaviour.@NotNull Properties loumImp$getThis() {
		return (BlockBehaviour.Properties) (Object) this;
	}

	@Unique
	public boolean loumImp$getIsBipolar() {
		return loum$isBipolar;
	}

	@Unique
	public BlockBehaviour.Properties loumImp$setIsBipolar(boolean isBipolar) {
		loum$isBipolar = isBipolar;
		return getThis();
	}

	@Unique
	public float loumImp$getMagnetismValue() {
		return loum$magnetismValue;
	}

	@Unique
	public BlockBehaviour.Properties loumImp$setMagnetismValue(float magnetismValue) {
		loum$magnetismValue = magnetismValue;
		return getThis();
	}

	@Unique
	public float loumImp$getxRot() {
		return loum$xRot;
	}

	@Unique
	public BlockBehaviour.Properties loumImp$setxRot(float xRot) {
		loum$xRot = xRot;
		return getThis();
	}

	@Unique
	public float loumImp$getyRot() {
		return loum$yRot;
	}

	@Unique
	public BlockBehaviour.Properties loumImp$setyRot(float yRot) {
		loum$yRot = yRot;
		return getThis();
	}

	@Unique
	public float loumImp$getAttenuationFact() {
		return loum$attenuationFact;
	}

	@Unique
	public BlockBehaviour.Properties loumImp$setAttenuationFactor(float attenuationFactor) {
		loum$attenuationFact = attenuationFactor;
		return getThis();
	}

	@Unique
	public BlockBehaviour.Properties loumImp$setMagnetismStorage(float randomAttenuation, @NotNull ForwardingMagnetismHandler forwardingMagnetismHandler) {
		loum$randomAttenuation = randomAttenuation;
		loum$magnetismValue    = forwardingMagnetismHandler.getMagnetismValue();
		loum$xRot              = forwardingMagnetismHandler.getxRot();
		loum$yRot              = forwardingMagnetismHandler.getyRot();
		loum$isBipolar         = forwardingMagnetismHandler.isBipolar();
		loum$attenuationFact   = forwardingMagnetismHandler.getAttenuationFactor();
		return getThis();
	}

	@Unique
	public BlockBehaviour.Properties loumImp$setMagnetismStorage(float randomAttenuation, float magnetismValue,
			float xRot, float yRot, boolean isBipolar, float attenuationFactor) {
		loum$randomAttenuation = randomAttenuation;
		loum$magnetismValue    = magnetismValue;
		loum$xRot              = xRot;
		loum$yRot              = yRot;
		loum$isBipolar         = isBipolar;
		loum$attenuationFact   = attenuationFactor;
		return getThis();
	}

	@Unique
	public ForwardingMagnetismHandler loumImp$getMagnetismStorage() {
		return new ForwardingMagnetismHandler(loum$magnetismValue, loum$isBipolar, loum$xRot, loum$yRot, loum$attenuationFact);
	}

	@Unique
	public CompoundTag loumImp$getMagnetismStorageNbt() {
		return new ForwardingMagnetismHandler(loum$magnetismValue, loum$isBipolar, loum$xRot, loum$yRot, loum$attenuationFact).createNbt();
	}
}
