package com.atlauncher;

import com.atlauncher.event.UpdateI18NEvent;
import com.atlauncher.plaf.ATLLookAndFeel;
import com.atlauncher.ui.diag.LoginDialog;
import com.atlauncher.ui.frame.ATLauncherFrame;
import com.atlauncher.utils.CLIParser;
import com.atlauncher.utils.ProviderClassLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//TODO: Improve boot time?
public final class ATLauncher{
    public static final ExecutorService TASKS = Executors.newCachedThreadPool();
    public static final Logger LOGGER = LogManager.getLogger();
    public static final EventBus EVENT_BUS = new EventBus();

    private static Injector injector;
    private static ATLauncherFrame frame;

    public static void main(String... args)
    throws Exception{
        LOGGER.info("Setting LookAndFeel");
        UIManager.setLookAndFeel(new ATLLookAndFeel());

        try{
            CLIParser parser = new CLIParser(args);
            String pC = parser.get("provider");
            AbstractModule module = regenModule(pC);
            injector = Guice.createInjector(module);

            if(!Boolean.valueOf(parser.get("developer"))){
                Settings.updateLauncherFiles();
            } else{
                LOGGER.debug("In developer mode");
            }

            frame = new ATLauncherFrame();
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    frame.setVisible(true);
                }
            });

            ATLauncher.EVENT_BUS.post(new UpdateI18NEvent());

            if(Settings.properties.getProperty("lastAccount") != null){
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        new LoginDialog().setVisible(true);
                    }
                });
            }
        } catch(Exception ex){
            ex.printStackTrace(System.out);
        }
    }

    public static ATLauncherFrame getFrame(){
        return frame;
    }

    public static Injector getInjector(){
        return injector;
    }

    @SuppressWarnings("unchecked")
    private static AbstractModule regenModule(String classpath){
        try{
            ProviderClassLoader loader = new ProviderClassLoader();
            Class<AbstractModule> moduleClass = (Class<AbstractModule>) loader.loadClass(classpath);
            Constructor<AbstractModule> moduleConstructor = moduleClass.getDeclaredConstructor();
            moduleConstructor.setAccessible(true);
            return moduleConstructor.newInstance();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}