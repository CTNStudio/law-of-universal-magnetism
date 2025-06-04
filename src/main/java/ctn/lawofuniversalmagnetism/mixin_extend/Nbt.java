package ctn.lawofuniversalmagnetism.mixin_extend;

import ctn.lawofuniversalmagnetism.api.MagnetismStorage;
import net.minecraft.nbt.CompoundTag;

public interface Nbt {
	CompoundTag getNbt();

	MagnetismStorage getMagnetismStorage();

	/**
	 * 请在这获取和修改
	 */
	default CompoundTag getMagnetismStorageNbt() {
		return getNbt().getCompound("magnetismStorage");
	}

	default void setMagnetismStorageNbt(CompoundTag nbt) {
		getNbt().put("magnetismStorage", nbt);
	}

	default void setMagnetismStorageNbt(MagnetismStorage magnetismStorage) {
		getNbt().put("magnetismStorage", magnetismStorage.createNbt());
	}
}
