package cn.elytra.mod.bandittweaker;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.lang.reflect.Method;

public class CommandBT extends CommandBase {
    @Override
    public String getName() { return "bt"; }

    @Override
    public String getUsage(ICommandSender sender) { return "/bt chunk <n> | /bt max <n> | /bt speed <n> | /bt status"; }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new TextComponentString(getUsage(sender)));
            return;
        }
        try {
            switch (args[0].toLowerCase()) {
                case "chunk":
                    int c = Integer.parseInt(args[1]);
                    boolean ok1 = setVeinDefaultChunkSize(c);
                    sender.sendMessage(new TextComponentString(ok1?"Default chunk size set to "+c:"Failed to set chunk size"));
                    break;
                case "max":
                    int m = Integer.parseInt(args[1]);
                    boolean ok2 = setVeinDefaultMaxSize(m);
                    sender.sendMessage(new TextComponentString(ok2?"Default max size set to "+m:"Failed to set max size"));
                    break;
                case "speed":
                    int s = Integer.parseInt(args[1]);
                    boolean ok3 = setVeinMiningMaxCountLimit(s);
                    sender.sendMessage(new TextComponentString(ok3?"Executor max count set to "+s+" (affects throughput)":"Failed to set speed/executor count"));
                    break;
                case "status":
                    sender.sendMessage(new TextComponentString(getStatus()));
                    break;
                default:
                    sender.sendMessage(new TextComponentString(getUsage(sender)));
            }
        } catch (Exception e) {
            sender.sendMessage(new TextComponentString("Error: " + e.getMessage()));
            e.printStackTrace();
        }
    }

    @Override
    public int getRequiredPermissionLevel() { return 2; } // ops only

    private boolean setVeinDefaultChunkSize(int v) {
        try {
            Class<?> cls = Class.forName("cn.elytra.mod.bandit.common.mining.VeinMiningHandler");
            Method m = cls.getDeclaredMethod("setDefaultChunkSize", int.class);
            m.setAccessible(true);
            m.invoke(null, v);
            return true;
        } catch (Exception e) {
            try {
                Class<?> cls = Class.forName("cn.elytra.mod.bandit.common.mining.VeinMiningHandler$Companion");
                Method m = cls.getDeclaredMethod("setDefaultChunkSize", int.class);
                m.setAccessible(true);
                m.invoke(cls, v);
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }

    private boolean setVeinDefaultMaxSize(int v) {
        try {
            Class<?> cls = Class.forName("cn.elytra.mod.bandit.common.mining.VeinMiningHandler");
            Method m = cls.getDeclaredMethod("setDefaultMaxSize", int.class);
            m.setAccessible(true);
            m.invoke(null, v);
            return true;
        } catch (Exception e) {
            try {
                Class<?> cls = Class.forName("cn.elytra.mod.bandit.common.mining.VeinMiningHandler$Companion");
                Method m = cls.getDeclaredMethod("setDefaultMaxSize", int.class);
                m.setAccessible(true);
                m.invoke(cls, v);
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }

    private boolean setVeinMiningMaxCountLimit(int v) {
        try {
            Class<?> ctx = Class.forName("cn.elytra.mod.bandit.common.mining.Context");
            Method m = ctx.getDeclaredMethod("setVeinMiningMaxCountLimit", int.class);
            m.setAccessible(true);
            m.invoke(null, v);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getStatus() {
        StringBuilder sb = new StringBuilder();
        try {
            Class<?> cls = Class.forName("cn.elytra.mod.bandit.common.mining.VeinMiningHandler");
            Method g1 = cls.getDeclaredMethod("getDefaultChunkSize");
            Method g2 = cls.getDeclaredMethod("getDefaultMaxSize");
            g1.setAccessible(true); g2.setAccessible(true);
            Object v1 = g1.invoke(null);
            Object v2 = g2.invoke(null);
            sb.append("chunk=").append(v1).append(" max=").append(v2).append('\n');
        } catch (Exception e) {
            sb.append("chunk/max: unavailable\n");
        }
        try {
            Class<?> ctx = Class.forName("cn.elytra.mod.bandit.common.mining.Context");
            Method g3 = ctx.getDeclaredMethod("getVeinMiningMaxCountLimit");
            g3.setAccessible(true);
            Object v3 = g3.invoke(null);
            sb.append("executorMax=").append(v3).append('\n');
        } catch (Exception e) {
            sb.append("executorMax: unavailable\n");
        }
        return sb.toString();
    }
}
