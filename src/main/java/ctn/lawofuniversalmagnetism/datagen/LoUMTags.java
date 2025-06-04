package ctn.lawofuniversalmagnetism.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static ctn.lawofuniversalmagnetism.LoUM.LOUM_ID;

public class LoUMTags {
	private static @NotNull ResourceLocation getResourceLocation(String name) {
		return ResourceLocation.fromNamespaceAndPath(LOUM_ID, name);
	}

	public static class Block extends BlockTagsProvider {

		public Block(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
				@Nullable ExistingFileHelper existingFileHelper) {
			super(output, lookupProvider, LOUM_ID, existingFileHelper);
		}

		protected static TagKey<net.minecraft.world.level.block.Block> createTag(String name) {
			return BlockTags.create(getResourceLocation(name));
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {

		}
	}

	public static class Item extends ItemTagsProvider {
		public Item(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
				CompletableFuture<TagLookup<net.minecraft.world.level.block.Block>> blockTags,
				@Nullable ExistingFileHelper existingFileHelper) {
			super(output, lookupProvider, blockTags, LOUM_ID, existingFileHelper);
		}

		protected static TagKey<net.minecraft.world.item.Item> createTag(String name) {
			return ItemTags.create(getResourceLocation(name));
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {

		}
	}
}
