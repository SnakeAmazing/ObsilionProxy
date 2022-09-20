package es.obsilion.proxy.bungee.file;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.util.List;

public class YAMLFile {

    private static final String YAML_EXTENSION = ".yml";
    private static final char CHAT_COLOR_DEFAULT_CODE = '&';

    private final Plugin plugin;
    private final String name;
    private final String fileName;
    private final File file;
    private Configuration configuration;

    public YAMLFile(Plugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        fileName = name + YAML_EXTENSION;
        file = new File(plugin.getDataFolder(), fileName);

        try {
            loadDefaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadDefaultConfiguration() throws IOException {
        if (!file.exists()) {
            file.createNewFile();

            try (InputStream inputStream = plugin.getResourceAsStream(fileName)) {
                if (inputStream != null) {
                    OutputStream outputStream = new FileOutputStream(file);
                    ByteStreams.copy(inputStream, outputStream);
                    System.out.println("[*] [Copy]");
                }
            }
        }
        configuration = YamlConfiguration.getProvider(YamlConfiguration.class).
                load(file);
    }

    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes(CHAT_COLOR_DEFAULT_CODE, configuration.getString(path));
    }

    public String getStringWithoutColor(String path) {
        return configuration.getString(path);
    }

    public int getInt(String path) {
        return configuration.getInt(path);
    }

    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    public List<String> getColoredStringList(String path) {
        List<String> list = configuration.getStringList(path);
        list.replaceAll(s -> ChatColor.translateAlternateColorCodes(CHAT_COLOR_DEFAULT_CODE, s));

        return list;
    }

    public void set(String path, Object object) {
        configuration.set(path, object);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void save() {
        try {
            YamlConfiguration.getProvider(YamlConfiguration.class)
                    .save(configuration, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            YamlConfiguration.getProvider(YamlConfiguration.class)
                    .load(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
