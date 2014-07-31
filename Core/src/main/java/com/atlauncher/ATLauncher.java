package com.atlauncher;

import com.atlauncher.plaf.ATLLookAndFeel;
import com.atlauncher.ui.ATLauncherFrame;
import com.atlauncher.utils.CLIParser;
import com.atlauncher.utils.ProviderClassLoader;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.lang.reflect.Constructor;
import java.util.concurrent.ForkJoinPool;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class ATLauncher{
    public static final ForkJoinPool TASKS = new ForkJoinPool();
    public static final EventBus EVENT_BUS = new EventBus();

    private static Injector injector;
    private static ATLauncherFrame frame;

    public static void main(String... args)
    throws Exception{
        UIManager.setLookAndFeel(ATLLookAndFeel.class.getName());

        try{
            CLIParser parser = new CLIParser(args);
            String pC = parser.get("provider");
            AbstractModule module = genModule(pC);
            if(module == null){
                module = regenModule(pC);
            }
            injector = Guice.createInjector(module);

            Settings.updateLauncherFiles();

            frame = new ATLauncherFrame();
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    frame.setVisible(true);
                }
            });
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
            ProviderClassLoader loader = new ProviderClassLoader();
            loader.emptyProviderJar();
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