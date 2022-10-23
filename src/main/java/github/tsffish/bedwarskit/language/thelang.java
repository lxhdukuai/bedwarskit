package github.tsffish.bedwarskit.language;

import github.tsffish.bedwarskit.config.MainConfigHandler;

public class thelang {
    public static String reloaded = "";
    public static void loadLanguage(){
        reloaded = MainConfigHandler.langReloaded;
    }
}
