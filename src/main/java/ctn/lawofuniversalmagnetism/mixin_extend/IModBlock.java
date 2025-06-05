package ctn.lawofuniversalmagnetism.mixin_extend;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

public interface IModBlock {
	void triggerUpdate(BlockState state, ServerLevel level, BlockPos pos);
}
