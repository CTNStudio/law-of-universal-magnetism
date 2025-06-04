package ctn.lawofuniversalmagnetism.mixin;

import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import ctn.lawofuniversalmagnetism.api.MagnetismStorage;
import ctn.lawofuniversalmagnetism.mixin_extend.Nbt;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.extensions.IBlockStateExtension;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockState.class)
@Implements(@Interface(iface = Nbt.class, prefix = "loUM$"))
public abstract class BlockStateMixin extends BlockBehaviour.BlockStateBase implements IBlockStateExtension, Nbt {
	@Unique
	private final CompoundTag loUM$nbt = new CompoundTag();
	@Unique
	private MagnetismStorage loUM$magnetismStorage;

	protected BlockStateMixin(Block owner,
			Reference2ObjectArrayMap<Property<?>, Comparable<?>> values,
			MapCodec<BlockState> propertiesCodec) {
		super(owner, values, propertiesCodec);
	}

	@Inject(method = "<init>", at = @At("RETURN"))
	public void BlockState(Block block, Reference2ObjectArrayMap<Property<?>, Comparable<?>> p_326238_, MapCodec<BlockState> p_61044_, CallbackInfo ci) {
		loUM$magnetismStorage = MagnetismStorage.EMPTY;
	}

	@Unique
	public CompoundTag loUM$getNbt() {
		return loUM$nbt;
	}

	@Unique
	public MagnetismStorage loUM$getMagnetismStorage() {
		return loUM$magnetismStorage;
	}
}
