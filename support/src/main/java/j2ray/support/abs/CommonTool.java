package j2ray.support.abs;

public class CommonTool {
    public static OSType getOSType() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return OSType.WINDOWS;
        } else if (os.contains("mac")) {
            return OSType.MACOS;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return OSType.LINUX;
        } else if (os.contains("sunos")) {
            return OSType.LINUX;
        } else {
            return OSType.UNKNOWN;
        }
    }
}
