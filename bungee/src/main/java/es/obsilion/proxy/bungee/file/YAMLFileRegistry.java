package es.obsilion.proxy.bungee.file;

import com.google.inject.Module;
import com.google.inject.name.Names;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class YAMLFileRegistry {

    private final Map<String, YAMLFile> files = new HashMap<>();

    public YAMLFileRegistry bind(String name, YAMLFile file) {
        files.put(name, file);

        return this;
    }

    public Optional<YAMLFile> get(String name) {
        return Optional.ofNullable(files.get(name));
    }

    public Module build() {
        return binder -> files.forEach((name, file) -> binder.bind(YAMLFile.class)
                .annotatedWith(Names.named(name)).toInstance(file));
    }
}
