package ctn.lawofuniversalmagnetism.mixin;

import ctn.lawofuniversalmagnetism.mixin_extend.IModBlockState;
import net.minecraft.core.HolderGetter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author AmarokIce Amaruq·Illaujaq
 */
@Mixin(NbtUtils.class)
public abstract class NbtUtilsMixin {

	@Inject(method = "writeBlockState", at = @At("RETURN"), cancellable = true)
	private static void loum$onSaveBlockState(BlockState state, CallbackInfoReturnable<CompoundTag> cir) {
		var blockStateNbt = ((IModBlockState) state).getNbt();
		var dataNbt = cir.getReturnValue();
		dataNbt.put("loum$nbt", blockStateNbt);
		cir.setReturnValue(dataNbt);
	}

	@Inject(method = "readBlockState", at = @At("RETURN"), cancellable = true)
	private static void loum$onReadBlockState(HolderGetter<Block> blockGetter, CompoundTag tag, CallbackInfoReturnable<BlockState> cir) {
		var blockStateNbt = tag.contains("loum$nbt", 10) ? tag.getCompound("loum$nbt") : new CompoundTag();
		var blockState = cir.getReturnValue();
		((IModBlockState) blockState).setNbt(blockStateNbt);
		cir.setReturnValue(blockState);
	}
}
