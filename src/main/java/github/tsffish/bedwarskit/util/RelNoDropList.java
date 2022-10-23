package github.tsffish.bedwarskit.util;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.config.MainConfigHandler.NoDropList;

public class RelNoDropList {
    public static List<Material> noDropList = new ArrayList<>();
    public static void loadRelNoDropList(){
        for (String string : NoDropList){
            noDropList.add(Material.getMaterial(string));
        }
    }
}
