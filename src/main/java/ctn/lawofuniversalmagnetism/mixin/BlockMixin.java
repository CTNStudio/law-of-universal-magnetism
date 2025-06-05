package ctn.lawofuniversalmagnetism.mixin;

import ctn.lawofuniversalmagnetism.mixin_extend.IModBlock;
import ctn.lawofuniversalmagnetism.mixin_extend.IModBlockState;
import ctn.lawofuniversalmagnetism.mixin_extend.IModProperties;
import ctn.lawofuniversalmagnetism.mixin_extend.MagneticBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
@Implements({
		@Interface(iface = MagneticBehavior.class, prefix = "loumMb$"),
		@Interface(iface = IModBlock.class, prefix = "loumImb$")
})
public abstract class BlockMixin extends BlockBehaviour implements ItemLike, IBlockExtension, MagneticBehavior, IModBlock {
	@Unique
	public  float      loum$randomAttenuation;
	@Shadow
	private BlockState defaultBlockState;

	public BlockMixin(Properties properties) {
		super(properties);
	}

	@Inject(method = "<init>", at = @At("RETURN"))
	public void Block(Properties properties, CallbackInfo ci) {
		var iModProperties = (IModProperties) properties;
		loum$randomAttenuation = iModProperties.getRandomAttenuation();
		((IModBlockState) defaultBlockState).setMagnetismStorageNbt(iModProperties.getMagnetismStorage());
	}

	@Unique
	public void loumImb$triggerUpdate(BlockState state, ServerLevel level, BlockPos pos) {

	}

}
