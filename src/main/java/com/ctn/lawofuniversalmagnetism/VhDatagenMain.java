package com.ctn.lawofuniversalmagnetism;

import com.ctn.lawofuniversalmagnetism.datagen.LoUMTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static com.ctn.lawofuniversalmagnetism.LoUM.LOUM_ID;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = LOUM_ID)
public class VhDatagenMain {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		LoUMTags.Block blockTags = new LoUMTags.Block(output, lookupProvider, fileHelper);
		generator.addProvider(event.includeClient(), blockTags);
		generator.addProvider(event.includeClient(), new LoUMTags.Item(output, lookupProvider, blockTags.contentsGetter(), fileHelper));

	}
}
