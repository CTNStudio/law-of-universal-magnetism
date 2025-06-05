package ctn.lawofuniversalmagnetism.registrar;

import ctn.lawofuniversalmagnetism.api.ForwardingMagnetismHandler;
import ctn.lawofuniversalmagnetism.mixin_extend.IModBlockState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import static ctn.lawofuniversalmagnetism.LoUM.LOUM_ID;

@EventBusSubscriber(modid = LOUM_ID, bus = EventBusSubscriber.Bus.MOD)
public class CapabilityRegistrationHandler {
	public static final BlockCapability<IModBlockState, Void> ITEM_HANDLER_BLOCK =
			BlockCapability.createVoid(ResourceLocation.fromNamespaceAndPath(LOUM_ID, "magnetism_storage"), IModBlockState.class);

	@SubscribeEvent
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlock(ITEM_HANDLER_BLOCK, (level, pos, state, entity, a) ->
						new ForwardingMagnetismHandler(((IModBlockState)state).getNbt()), Blocks.IRON_BLOCK
		);
	}
}