package ctn.lawofuniversalmagnetism.api;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class MagnetismStorage {
	public static final float   DEGREES_TO_RADIANS = (float) Math.PI / 180F;
	private final       boolean isBipolar;
	private             float   magnetismValue;
	private             float   xRot;
	private             float   yRot;
	private             float   attenuationFactor;

	public static final MagnetismStorage EMPTY = new MagnetismStorage(0, 0, 0, false, 0);

	/**
	 * @param magnetismValue    位于中心的强度
	 * @param yRot              y角度
	 * @param xRot              x角度
	 * @param isBipolar         是否为双磁极
	 * @param attenuationFactor 位于中心的穿透能力
	 */
	public MagnetismStorage(float magnetismValue, float xRot, float yRot, boolean isBipolar, float attenuationFactor) {
		this.setMagnetismValue(magnetismValue);
		this.setxRot(xRot);
		this.setyRot(yRot);
		this.isBipolar = isBipolar;
		this.setAttenuationFactor(attenuationFactor);
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

	public static MagnetismStorage createMagnetismStorage(CompoundTag nbt) {
		if (!nbt.contains("magnetismStorage")) {
			return null;
		}
		nbt = nbt.getCompound("magnetismStorage");
		return new MagnetismStorage(
				nbt.getFloat("magnetismValue"),
				nbt.getFloat("xRot"),
				nbt.getFloat("yRot"),
				nbt.getBoolean("isBipolar"),
				nbt.getFloat("attenuationFactor")
		);
	}

	/**
	 * 获取衰减后的磁力强度
	 *
	 * @param pos 自己坐标
	 * @param targetVec3 目标坐标
	 * @return 衰减后的磁力强度
	 */
	public float calculateAttenuatedMagnetism(Vec3 pos,Vec3 targetVec3) {
		return getMagnetismValue() - getAttenuationFactor() * (float) pos.distanceTo(targetVec3);
	}

	public float magnetismValue() {
		return getMagnetismValue();
	}

	public float xRot() {
		return getxRot();
	}

	public float yRot() {
		return getyRot();
	}

	public boolean isBipolar() {
		return isBipolar;
	}

	public float attenuationFactor() {
		return getAttenuationFactor();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (MagnetismStorage) obj;
		return Float.floatToIntBits(this.getMagnetismValue()) == Float.floatToIntBits(that.getMagnetismValue()) &&
		       Float.floatToIntBits(this.getxRot()) == Float.floatToIntBits(that.getxRot()) &&
		       Float.floatToIntBits(this.getyRot()) == Float.floatToIntBits(that.getyRot()) &&
		       this.isBipolar == that.isBipolar &&
		       Float.floatToIntBits(this.getAttenuationFactor()) == Float.floatToIntBits(that.getAttenuationFactor());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMagnetismValue(), getxRot(), getyRot(), isBipolar, getAttenuationFactor());
	}

	@Override
	public String toString() {
		return "MagnetismStorage[" +
		       "magnetismValue=" + getMagnetismValue() + ", " +
		       "xRot=" + getxRot() + ", " +
		       "yRot=" + getyRot() + ", " +
		       "isBipolar=" + isBipolar + ", " +
		       "attenuationFactor=" + getAttenuationFactor() + "]";
	}

	public CompoundTag createNbt() {
		CompoundTag nbt = new CompoundTag();
		nbt.putFloat("magnetismValue", getMagnetismValue());
		nbt.putFloat("xRot", getxRot());
		nbt.putFloat("yRot", getyRot());
		nbt.putBoolean("isBipolar", isBipolar);
		nbt.putFloat("attenuationFactor", getAttenuationFactor());
		return nbt;
	}

	public void triggerUpdate() {}

	public void update(CompoundTag nbt) {
		this.setMagnetismValue(nbt.getFloat("magnetismValue"));
		this.setxRot(nbt.getFloat("xRot"));
		this.setyRot(nbt.getFloat("yRot"));
		this.setAttenuationFactor(nbt.getFloat("attenuationFactor"));
		triggerUpdate();
	}


	public void write(CompoundTag nbt) {
		nbt.putFloat("magnetismValue", this.getMagnetismValue());
		nbt.putFloat("xRot", this.getxRot());
		nbt.putFloat("yRot", this.getyRot());
		nbt.putFloat("attenuationFactor", this.getAttenuationFactor());
	}

	public float getMagnetismValue() {
		return magnetismValue;
	}

	public void setMagnetismValue(float magnetismValue) {
		this.magnetismValue = magnetismValue;
		triggerUpdate();
	}

	public float getxRot() {
		return xRot;
	}

	public void setxRot(float xRot) {
		this.xRot = getRot(xRot);
		triggerUpdate();
	}

	public float getyRot() {
		return yRot;
	}

	public void setyRot(float yRot) {
		this.yRot = getRot(yRot);
		triggerUpdate();
	}

	public float getAttenuationFactor() {
		return attenuationFactor;
	}

	public void setAttenuationFactor(float attenuationFactor) {
		this.attenuationFactor = attenuationFactor;
		triggerUpdate();
	}
}
