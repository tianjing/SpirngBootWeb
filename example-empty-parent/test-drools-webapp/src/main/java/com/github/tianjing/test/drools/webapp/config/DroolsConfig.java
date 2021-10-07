package com.github.tianjing.test.drools.webapp.config;

import org.drools.core.io.impl.ReaderResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tgtools.exceptions.APPErrorException;
import tgtools.util.StringUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author 田径
 * @date 2020-05-02 20:05
 * @desc
 **/
@Configuration
public class DroolsConfig {
    /**
     * rule文件的存放位置
     */
    private static final String RULES_PATH = "rules/";


    /**
     * 获取到的KieServices其实是一个单例
     */
    private final KieServices kieServices = KieServices.Factory.get();

    @Bean
    public KieFileSystem kieFileSystem() throws IOException, APPErrorException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] files = resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
        String path = null;
        for (Resource file : files) {

            path = RULES_PATH + file.getFilename();
            //kieFileSystem.write(ResourceFactory.newClassPathResource(path, "UTF-8"));
            String vContent = StringUtil.parseInputStream(new FileInputStream(file.getFile()), "UTF-8");
            ReaderResource vReaderResource =new ReaderResource(new StringReader(vContent));
            vReaderResource.setTargetPath("src/main/resources/fdsafad/"+ file.getFilename());
            kieFileSystem.write(vReaderResource);
            //kieFileSystem.write("src/main/resources/fdsafad/"+ file.getFilename(), vContent);
        }
        return kieFileSystem;
    }

    @Bean
    public KieContainer kieContainer(KieFileSystem pKieFileSystem) throws IOException {
        KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        KieBuilder kieBuilder = kieServices.newKieBuilder(pKieFileSystem);
        kieBuilder.buildAll();
        return kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
    }


    @Bean
    public KieSession kieSession(KieContainer pKieContainer) throws IOException {
        return pKieContainer.newKieSession();
    }


}
