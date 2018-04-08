package com.test;

import com.test.service.TestPluginService;
import tgtools.plugin.IPlugin;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 19:54
 */
public class Plugin implements IPlugin {
    @Override
    public void load() throws Exception {
        tgtools.web.platform.Platform.addBean("testPluginService",TestPluginService.class);
    }

    @Override
    public void unload() throws Exception {
        tgtools.web.platform.Platform.removeBean("testPluginService");
    }

    @Override
    public Object execute(Object... params) throws Exception {
        ((TestPluginService)tgtools.web.platform.Platform.getBean("testPluginService")).test();
        return null;
    }
}
