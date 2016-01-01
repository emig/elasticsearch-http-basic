package com.asquera.elasticsearch.plugins.http;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.settings.Settings;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Florian Gilcher (florian.gilcher@asquera.de)
 */
public class HttpBasicServerPlugin extends Plugin {

    private boolean enabledByDefault = true;
    private final Settings settings;

    @Inject public HttpBasicServerPlugin(Settings settings) {
        this.settings = settings;
    }

    @Override public String name() {
        return "http-basic-server-plugin";
    }

    @Override public String description() {
        return "HTTP Basic Server Plugin";
    }

    @Override
    public Collection<Module> nodeModules() {
    Collection<Module> modules = new ArrayList<>();
    if (settings.getAsBoolean("http.basic.enabled", enabledByDefault)) {
        modules.add(new HttpBasicServerModule(settings));
    }
    return modules;
    }

    @Override public Settings additionalSettings() {
        if (settings.getAsBoolean("http.basic.enabled", enabledByDefault)) {
            return Settings.builder().
                    put("http.enabled", false).
                    build();
        } else {
            return Settings.builder().EMPTY_SETTINGS;
        }
    }
}
