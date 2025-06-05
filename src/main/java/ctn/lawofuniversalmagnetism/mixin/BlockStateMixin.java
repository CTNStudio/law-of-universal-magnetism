package ctn.lawofuniversalmagnetism.mixin;

import com.mojang.serialization.MapCodec;
import ctn.lawofuniversalmagnetism.api.ForwardingMagnetismHandler;
import ctn.lawofuniversalmagnetism.mixin_extend.IModBlockState;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.common.extensions.IBlockStateExtension;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(BlockState.class)
@Implements(@Interface(iface = IModBlockState.class, prefix = "loumImbs$"))
public abstract class BlockStateMixin extends BlockBehaviour.BlockStateBase
		implements IBlockStateExtension, IModBlockState {
	@Unique
	private CompoundTag loum$nbt = new CompoundTag();

	protected BlockStateMixin(Block owner,
			Reference2ObjectArrayMap<Property<?>, Comparable<?>> values,
			MapCodec<BlockState> propertiesCodec) {
		super(owner, values, propertiesCodec);
	}

	@Inject(method = "asState", at = @At("HEAD"))
	protected void asState(CallbackInfoReturnable<BlockState> cir) {

	}

	@Inject(method = "<init>", at = @At("RETURN"))
	public void BlockState(Block block, Reference2ObjectArrayMap<Property<?>, Comparable<?>> p_326238_, MapCodec<BlockState> p_61044_, CallbackInfo ci) {}

	@Unique
	public CompoundTag loumImbs$getNbt() {
		if (Objects.isNull(this.loum$nbt)) {
			this.loum$nbt = new CompoundTag();
		}
		return this.loum$nbt;
	}

	@Unique
	public void loumImbs$setNbt(CompoundTag nbt) {
		this.loum$nbt = nbt;
		triggerUpdate();
	}

	@Unique
	public CompoundTag loumImbs$getMagnetismStorageNbt() {
		return loum$nbt.getCompound("magnetismStorage");
	}

	@Unique
	public void loumImbs$setMagnetismStorageNbt(ForwardingMagnetismHandler forwardingMagnetismHandler) {
		loum$nbt.put("magnetismStorage", forwardingMagnetismHandler.createNbt());
	}

	@Unique
	public void loumImbs$triggerUpdate() {

	}

	@Unique
	public float loumImbs$getMagnetismValue() {
		return getMagnetismStorageNbt().getFloat("magnetismValue");
	}

	@Unique
	public void loumImbs$setMagnetismValue(float magnetismValue) {
		getMagnetismStorageNbt().putFloat("magnetismValue", magnetismValue);
		triggerUpdate();
	}

	@Unique
	public boolean loumImbs$isBipolar() {
		return getMagnetismStorageNbt().getBoolean("isBipolar");
	}

	@Unique
	public void loumImbs$setIsBipolar(boolean isBipolar) {
		getMagnetismStorageNbt().putBoolean("isBipolar", isBipolar);
		triggerUpdate();
	}

	@Unique
	public float loumImbs$getAttenuationFactor() {
		return getMagnetismStorageNbt().getFloat("attenuationFactor");
	}

	@Unique
	public void loumImbs$setAttenuationFactor(float attenuationFactor) {
		getMagnetismStorageNbt().putFloat("attenuationFactor", attenuationFactor);
		triggerUpdate();
	}

	@Unique
	public float loumImbs$getxRot() {
		return getMagnetismStorageNbt().getFloat("xRot");
	}

	@Unique
	public void loumImbs$setxRot(float xRot) {
		getMagnetismStorageNbt().putFloat("xRot", xRot);
		triggerUpdate();
	}

	@Unique
	public float loumImbs$getyRot() {
		return getMagnetismStorageNbt().getFloat("yRot");
	}

	@Unique
	public void loumImbs$setyRot(float yRot) {
		getMagnetismStorageNbt().putFloat("yRot", yRot);
		triggerUpdate();
	}
}
