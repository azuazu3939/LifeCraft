package azuazu3939.lifecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LifeCraftCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        try {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (!player.hasPermission("LifeCraft.Command.LifeCraft")) {
                    player.sendMessage(ChatColor.RED + "権限がありません。");
                    return true;
                }

                if (args[0].equalsIgnoreCase("open")) {
                    new CreateInventory().step1(player);
                    player.sendMessage(ChatColor.GREEN + "LifeCraftメニューを開きました。");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "このコマンドはプレイヤー実行専用コマンドです。");
                return true;
            }
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "エラーが発生しました。");
            return true;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("LifeCraft.Command.LifeCraft") ) {

                List<String> list = new ArrayList<>();
                list.add("open");
                return list;
            }
        }
        return null;
    }
}
