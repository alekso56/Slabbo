package xyz.mackan.Slabbo.types;

import org.bukkit.Bukkit;

public class ServerVersion {
    private static final int[] current = parse(Bukkit.getBukkitVersion());

    private static int[] parse (String version) {
        String[] parts = version.split("-")[0].split("\\.");

        int[] parsed = new int[3];

        // Paper 26.x reports e.g. "26.3.build.190-stable": stop at the first non-numeric part.
        for (int i = 0; i < Math.min(3, parts.length); i++) {
            if (!parts[i].matches("\\d+")) break;
            parsed[i] = Integer.parseInt(parts[i]);
        }

        return parsed;
    }

    public static boolean isSameOrLater (int major, int minor, int patch) {
        int[] target = { major, minor, patch };

        for (int i = 0; i < 3; i++) {
            if (current[i] != target[i]) return current[i] > target[i];
        }

        return true;
    }
}