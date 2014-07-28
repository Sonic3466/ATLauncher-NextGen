package com.atlauncher;

import com.atlauncher.utils.CLIParser;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

public final class ATLauncher{
    public static ForkJoinPool TASKS = new ForkJoinPool();

    private static Injector injector;

    public static void main(String... args)
    throws Exception{
        try{
            CLIParser parser = new CLIParser(args);
            String pC = parser.get("provider");
            AbstractModule module = genModule(pC);
            if(module == null){
                module = regenModule(pC);
            }
            injector = Guice.createInjector(module);

            Settings.updateLauncherFiles();
        } catch(Exception ex){
            ex.printStackTrace(System.out);
        }
    }

    public static Injector getInjector(){
        return injector;
    }

    @SuppressWarnings("unchecked")
    private static AbstractModule regenModule(String classpath){
        try{
            Path libs = Paths.get(System.getProperty("user.dir"), "libs");
            URL[] jars = new URL[]{
                    libs.resolve("atl_provider.jar").toUri().toURL()
            };

            URLClassLoader loader = URLClassLoader.newInstance(jars);
            Class<AbstractModule> moduleClass = (Class<AbstractModule>) loader.loadClass(classpath);
            Constructor<AbstractModule> moduleConstructor = moduleClass.getDeclaredConstructor();
            moduleConstructor.setAccessible(true);
            return moduleConstructor.newInstance();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    private static AbstractModule genModule(String classPath){
        try{
            Class<AbstractModule> moduleClass = (Class<AbstractModule>) Class.forName(classPath);
            Constructor<AbstractModule> moduleConstructor = moduleClass.getDeclaredConstructor();
            moduleConstructor.setAccessible(true);
            return moduleConstructor.newInstance();
        } catch(Exception ex){
            return null;
        }
    }
}