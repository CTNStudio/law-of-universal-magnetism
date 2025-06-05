package ctn.lawofuniversalmagnetism.api;

import ctn.lawofuniversalmagnetism.mixin_extend.IModBlockState;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class ForwardingMagnetismHandler implements IModBlockState {
	public static final float                      DEGREES_TO_RADIANS = (float) Math.PI / 180F;
	public static final ForwardingMagnetismHandler EMPTY              = new ForwardingMagnetismHandler(0, false, 0, 0, 0);
	protected           CompoundTag                nbt;

	/**
	 * @param magnetismValue    位于中心的强度
	 * @param isBipolar         是否为双磁极
	 * @param xRot              x角度
	 * @param yRot              y角度
	 * @param attenuationFactor 位于中心的穿透能力
	 */
	public ForwardingMagnetismHandler(float magnetismValue, boolean isBipolar, float xRot, float yRot, float attenuationFactor) {
		nbt = new CompoundTag();
		this.setMagnetismValue(magnetismValue);
		this.setxRot(xRot);
		this.setyRot(yRot);
		this.setIsBipolar(isBipolar);
		this.setAttenuationFactor(attenuationFactor);
	}

	public ForwardingMagnetismHandler(CompoundTag nbt) {
		this.nbt = nbt;
	}

	/**
	 * 获取旋转向量
	 */
	public static Vec3 calculateViewVector(float xRot, float yRot) {
		float f = getRot(xRot) * DEGREES_TO_RADIANS;
		float f1 = -getRot(yRot) * DEGREES_TO_RADIANS;
		float f2 = Mth.cos(f1);
		float f3 = Mth.sin(f1);
		float f4 = Mth.cos(f);
		float f5 = Mth.sin(f);
		return new Vec3(f3 * f4, -f5, f2 * f4);
	}

	/**
	 * 角度转弧度
	 */
	public static float degreesToRadians(float angle) {
		return angle * DEGREES_TO_RADIANS;
	}

	/**
	 * 创建一个 MagnetismStorage
	 */
	public static ForwardingMagnetismHandler createMagnetismStorage(CompoundTag nbt) {
		return new ForwardingMagnetismHandler(
				nbt.getFloat("magnetismValue"),
				nbt.getBoolean("isBipolar"), nbt.getFloat("xRot"),
				nbt.getFloat("yRot"),
				nbt.getFloat("attenuationFactor")
		);
	}

	public static CompoundTag createNbt(ForwardingMagnetismHandler forwardingMagnetismHandler) {
		CompoundTag nbt = new CompoundTag();
		nbt.putFloat("magnetismValue", forwardingMagnetismHandler.getMagnetismValue());
		nbt.putFloat("xRot", forwardingMagnetismHandler.getxRot());
		nbt.putFloat("yRot", forwardingMagnetismHandler.getyRot());
		nbt.putBoolean("isBipolar", forwardingMagnetismHandler.isBipolar());
		nbt.putFloat("attenuationFactor", forwardingMagnetismHandler.getAttenuationFactor());
		return nbt;
	}

	public static float getRot(float rot) {
		if (!Float.isFinite(rot)) {
			Util.logAndPauseIfInIde("Invalid entity rotation: " + rot + ", discarding.");
			return 0;
		} else {
			if (rot < -180.0F) {
				rot += 360.0F;
			} else if (rot > 180.0F) {
				rot -= 360.0F;
			}
			return rot;
		}
	}

	/**
	 * 创建一个 NBT
	 */
	public CompoundTag createNbt() {
		CompoundTag nbt = new CompoundTag();
		nbt.putFloat("magnetismValue", getMagnetismValue());
		nbt.putFloat("xRot", getxRot());
		nbt.putFloat("yRot", getyRot());
		nbt.putBoolean("isBipolar", isBipolar());
		nbt.putFloat("attenuationFactor", getAttenuationFactor());
		return nbt;
	}

	/**
	 * 获取衰减后的磁力强度
	 *
	 * @param pos        自己坐标
	 * @param targetVec3 目标坐标
	 * @return 衰减后的磁力强度
	 */
	public float calculateAttenuatedMagnetism(Vec3 pos, Vec3 targetVec3) {
		return getMagnetismValue() - getAttenuationFactor() * (float) pos.distanceTo(targetVec3);
	}

	public static float calculateAttenuatedMagnetism(float magnetismValue, float attenuationFactor, Vec3 pos, Vec3 targetVec3) {
		return magnetismValue - attenuationFactor * (float) pos.distanceTo(targetVec3);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (ForwardingMagnetismHandler) obj;
		return Float.floatToIntBits(this.getMagnetismValue()) == Float.floatToIntBits(that.getMagnetismValue()) &&
		       Float.floatToIntBits(this.getxRot()) == Float.floatToIntBits(that.getxRot()) &&
		       Float.floatToIntBits(this.getyRot()) == Float.floatToIntBits(that.getyRot()) &&
		       this.isBipolar() == that.isBipolar() &&
		       Float.floatToIntBits(this.getAttenuationFactor()) == Float.floatToIntBits(that.getAttenuationFactor());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMagnetismValue(), getxRot(), getyRot(), isBipolar(), getAttenuationFactor());
	}

	@Override
	public String toString() {
		return "MagnetismStorage[" +
		       "magnetismValue=" + getMagnetismValue() + ", " +
		       "xRot=" + getxRot() + ", " +
		       "yRot=" + getyRot() + ", " +
		       "isBipolar=" + isBipolar() + ", " +
		       "attenuationFactor=" + getAttenuationFactor() + "]";
	}

	@Override
	public CompoundTag getNbt() {
		return nbt;
	}

	@Override
	public void setNbt(CompoundTag nbt) {
		this.nbt = nbt;
		triggerUpdate();
	}

	@Override
	public void triggerUpdate() {}

	/**
	 * 更新类
	 */
	public void update(CompoundTag nbt) {
		nbt = nbt.getCompound("magnetismStorage");
		this.setMagnetismValue(nbt.getFloat("magnetismValue"));
		this.setxRot(nbt.getFloat("xRot"));
		this.setyRot(nbt.getFloat("yRot"));
		this.setAttenuationFactor(nbt.getFloat("attenuationFactor"));
		triggerUpdate();
	}

	/**
	 * 保存到nbt
	 */
	public void write(CompoundTag nbt) {
		CompoundTag magnetismStorageNbt = createNbt();
		nbt.put("magnetismStorage", magnetismStorageNbt);
		triggerUpdate();
	}

	@Override
	public CompoundTag getMagnetismStorageNbt() {
		return nbt.getCompound("magnetismStorage");
	}

	@Override
	public void setMagnetismStorageNbt(ForwardingMagnetismHandler forwardingMagnetismHandler) {
		nbt.put("magnetismStorage", forwardingMagnetismHandler.createNbt());
	}

	@Override
	public float getMagnetismValue() {
		return getMagnetismStorageNbt().getFloat("magnetismValue");
	}

	@Override
	public void setMagnetismValue(float magnetismValue) {
		getMagnetismStorageNbt().putFloat("magnetismValue", magnetismValue);
		triggerUpdate();
	}

	@Override
	public boolean isBipolar() {
		return getMagnetismStorageNbt().getBoolean("isBipolar");
	}

	@Override
	public void setIsBipolar(boolean isBipolar) {
		getMagnetismStorageNbt().putBoolean("isBipolar", isBipolar);
		triggerUpdate();
	}

	@Override
	public float getAttenuationFactor() {
		return getMagnetismStorageNbt().getFloat("attenuationFactor");
	}

	@Override
	public void setAttenuationFactor(float attenuationFactor) {
		getMagnetismStorageNbt().putFloat("attenuationFactor", attenuationFactor);
		triggerUpdate();
	}

	@Override
	public float getxRot() {
		return getMagnetismStorageNbt().getFloat("xRot");
	}

	@Override
	public void setxRot(float xRot) {
		getMagnetismStorageNbt().putFloat("xRot", xRot);
		triggerUpdate();
	}

	@Override
	public float getyRot() {
		return getMagnetismStorageNbt().getFloat("yRot");
	}

	@Override
	public void setyRot(float yRot) {
		getMagnetismStorageNbt().putFloat("yRot", yRot);
		triggerUpdate();
	}
}
