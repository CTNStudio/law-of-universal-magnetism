package ctn.lawofuniversalmagnetism.mixin;

import ctn.lawofuniversalmagnetism.mixin_extend.MagneticBehavior;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
@Implements(@Interface(iface = MagneticBehavior.class, prefix = "loUM$"))
public abstract class BlockMixin extends BlockBehaviour implements ItemLike, IBlockExtension, MagneticBehavior {
	@Shadow
	public abstract StateDefinition<Block, BlockState> getStateDefinition();

	public BlockMixin(Properties properties) {
		super(properties);
	}

	@Inject(method = "<init>*", at = @At("RETURN"))
	public void Block(Properties properties, CallbackInfo ci) {
	}
}
