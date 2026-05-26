package me.nikl.gamebox.nms;

/**
 * Backwards-compatible entry point for modules compiled against the old NMS API.
 */
public final class NmsFactory {
  private static final NmsUtility NMS_UTILITY = new NmsUtility();

  private NmsFactory() {
  }

  public static NmsUtility getNmsUtility() {
    return NMS_UTILITY;
  }
}
