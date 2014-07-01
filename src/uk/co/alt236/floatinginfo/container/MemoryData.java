package uk.co.alt236.floatinginfo.container;

import java.lang.reflect.Field;

import android.os.Debug.MemoryInfo;

public class MemoryData {
	/** The private clean pages used by dalvik heap. */
	/** @hide We may want to expose this, eventually. */
	private final int dalvikPrivateClean;
	/** The private dirty pages used by dalvik heap. */
	private final int dalvikPrivateDirty;
	/**
	 * The proportional set size for dalvik heap. (Doesn't include other Dalvik
	 * overhead.)
	 */
	private final int dalvikPss;
	/** The shared clean pages used by dalvik heap. */
	/** @hide We may want to expose this, eventually. */
	private final int dalvikSharedClean;
	/** The shared dirty pages used by dalvik heap. */
	private final int dalvikSharedDirty;
	/** The proportional set size that is swappable for dalvik heap. */
	/** @hide We may want to expose this, eventually. */
	private final int dalvikSwappablePss;
	/** The dirty dalvik pages that have been swapped out. */
	/** @hide We may want to expose this, eventually. */
	private final int dalvikSwappedOut;

	/** The private clean pages used by the native heap. */
	/** @hide We may want to expose this, eventually. */
	private final int nativePrivateClean;
	/** The private dirty pages used by the native heap. */
	private final int nativePrivateDirty;
	/** The proportional set size for the native heap. */
	private final int nativePss;
	/** The shared clean pages used by the native heap. */
	/** @hide We may want to expose this, eventually. */
	private final int nativeSharedClean;
	/** The shared dirty pages used by the native heap. */
	private final int nativeSharedDirty;
	/** The proportional set size that is swappable for the native heap. */
	/** @hide We may want to expose this, eventually. */
	private final int nativeSwappablePss;
	/** The dirty native pages that have been swapped out. */
	/** @hide We may want to expose this, eventually. */
	private final int nativeSwappedOut;

	/** The private clean pages used by everything else. */
	/** @hide We may want to expose this, eventually. */
	private final int otherPrivateClean;
	/** The private dirty pages used by everything else. */
	private final int otherPrivateDirty;
	/** The proportional set size for everything else. */
	private final int otherPss;
	/** The shared clean pages used by everything else. */
	/** @hide We may want to expose this, eventually. */
	private final int otherSharedClean;
	/** The shared dirty pages used by everything else. */
	private final int otherSharedDirty;
	/** The proportional set size that is swappable for everything else. */
	/** @hide We may want to expose this, eventually. */
	private final int otherSwappablePss;
	/** The dirty pages used by anyting else that have been swapped out. */
	/** @hide We may want to expose this, eventually. */
	private final int otherSwappedOut;

	public MemoryData(final MemoryInfo mi) {
		dalvikPrivateClean = getValueReflectively(mi, "dalvikPrivateClean");
		dalvikPrivateDirty = mi.dalvikPrivateDirty;
		dalvikPss = mi.dalvikPss;
		dalvikSharedClean = getValueReflectively(mi, "dalvikSharedClean");
		dalvikSharedDirty = mi.dalvikSharedDirty;
		dalvikSwappablePss = getValueReflectively(mi, "dalvikSwappablePss");
		dalvikSwappedOut = getValueReflectively(mi, "dalvikSwappedOut");

		nativePrivateClean = getValueReflectively(mi, "nativePrivateClean");
		nativePrivateDirty = mi.nativePrivateDirty;
		nativePss = mi.nativePss;
		nativeSharedClean = getValueReflectively(mi, "nativeSharedClean");
		nativeSharedDirty = mi.nativeSharedDirty;
		nativeSwappablePss = getValueReflectively(mi, "nativeSwappablePss");
		nativeSwappedOut = getValueReflectively(mi, "nativeSwappedOut");

		otherPrivateClean = getValueReflectively(mi, "otherPrivateClean");
		otherPrivateDirty = mi.otherPrivateDirty;
		otherPss = mi.otherPss;
		otherSharedClean = getValueReflectively(mi, "otherSharedClean");
		otherSharedDirty = mi.otherSharedDirty;
		otherSwappablePss = getValueReflectively(mi, "otherSwappablePss");
		otherSwappedOut = getValueReflectively(mi, "otherSwappedOut");
	}

	public int getDalvikPrivateClean() {
		return dalvikPrivateClean;
	}

	public int getDalvikPrivateDirty() {
		return dalvikPrivateDirty;
	}

	public int getDalvikPss() {
		return dalvikPss;
	}

	public int getDalvikSharedClean() {
		return dalvikSharedClean;
	}

	public int getDalvikSharedDirty() {
		return dalvikSharedDirty;
	}

	public int getDalvikSwappablePss() {
		return dalvikSwappablePss;
	}

	public int getDalvikSwappedOut() {
		return dalvikSwappedOut;
	}

	public int getNativePrivateClean() {
		return nativePrivateClean;
	}

	public int getNativePrivateDirty() {
		return nativePrivateDirty;
	}

	public int getNativePss() {
		return nativePss;
	}

	public int getNativeSharedClean() {
		return nativeSharedClean;
	}

	public int getNativeSharedDirty() {
		return nativeSharedDirty;
	}

	public int getNativeSwappablePss() {
		return nativeSwappablePss;
	}

	public int getNativeSwappedOut() {
		return nativeSwappedOut;
	}

	public int getOtherPrivateClean() {
		return otherPrivateClean;
	}

	public int getOtherPrivateDirty() {
		return otherPrivateDirty;
	}

	public int getOtherPss() {
		return otherPss;
	}

	public int getOtherSharedClean() {
		return otherSharedClean;
	}

	public int getOtherSharedDirty() {
		return otherSharedDirty;
	}

	public int getOtherSwappablePss() {
		return otherSwappablePss;
	}

	public int getOtherSwappedOut() {
		return otherSwappedOut;
	}

	private int getValueReflectively(final MemoryInfo mi, final String name) {
		if(mi != null){
			final Class<?> clazz = mi.getClass();
			final Field field;
			try {
				field = clazz.getField(name);
				return field.getInt(mi);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}
		}
		return -1;
	}

}