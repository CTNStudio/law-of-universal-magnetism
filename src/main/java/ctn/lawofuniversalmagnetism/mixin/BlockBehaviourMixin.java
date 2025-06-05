package ctn.lawofuniversalmagnetism.mixin;

import ctn.lawofuniversalmagnetism.mixin_extend.IModBlockBehaviour;
import ctn.lawofuniversalmagnetism.mixin_extend.IModBlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
@Implements(@Interface(iface = IModBlockBehaviour.class, prefix = "loumImbb$"))
public abstract class BlockBehaviourMixin implements FeatureElement, IModBlockBehaviour {
	@Unique
	public int loum$randomAttenuation;

	@Inject(method = "<init>", at = @At("RETURN"))
	public void BlockBehaviour(BlockBehaviour.Properties properties, CallbackInfo ci) {
	}

	@Inject(method = "randomTick", at = @At("HEAD"))
	protected void loum$randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
		var iModBlockState = (IModBlockState) state;
		if (loum$randomAttenuation != 0) {
			var magnetismValue = iModBlockState.getMagnetismValue();
			if (magnetismValue != 0) {
				iModBlockState.setMagnetismValue(magnetismValue + (magnetismValue > 0 ? -loum$randomAttenuation : loum$randomAttenuation));
			}
		}
	}
}
