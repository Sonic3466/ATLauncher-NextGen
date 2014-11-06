package com.atlauncher.ui.diag;

import com.atlauncher.ATLauncher;
import com.atlauncher.obj.Pack;
import com.atlauncher.ui.comp.TitledList;
import com.atlauncher.ui.frame.ATLauncherFrame;
import com.atlauncher.ui.panel.DialogBackPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public final class ViewModpackDialog
extends JDialog{
    private final TitledList<String> optional = new TitledList<>("Optional Mods");
    private final TitledList<String> required = new TitledList<>("Required Mods");

    public ViewModpackDialog(Pack pack){
        super(ATLauncher.getFrame(), pack.name, ModalityType.APPLICATION_MODAL);
        this.setUndecorated(true);
        this.setContentPane(new DialogBackPanel());
        this.getContentPane().add(new TopPanel(pack), BorderLayout.NORTH);
        this.getContentPane().add(new BottomPanel(), BorderLayout.SOUTH);
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, required, optional);
        splitter.setResizeWeight(0.5);
        splitter.setEnabled(false);
        splitter.setBorder(BorderFactory.createEmptyBorder());
        this.getContentPane().add(splitter, BorderLayout.CENTER);
        ATLauncherFrame parent = ATLauncher.getFrame();
        this.setSize(new Dimension(parent.getWidth() / 2, parent.getHeight() / 2));
        this.setLocation(parent.getX() + ((parent.getWidth() - this.getWidth()) / 2), parent.getY() + ((parent.getHeight() - this.getHeight()) / 2));
        parent.blur();
    }

    @Override
    public void dispose(){
        ATLauncher.getFrame().clear();
        super.dispose();
    }

    private final class TopPanel
    extends JPanel{
        private TopPanel(Pack pack){
            super(new FlowLayout(FlowLayout.CENTER));
            this.add(new JLabel(pack.name));
        }
    }

    private final class BottomPanel
    extends JPanel{
        private BottomPanel(){
            super(new FlowLayout(FlowLayout.CENTER));
            this.add(new JButton("Exit"){{
                this.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        dispose();
                    }
                });
            }});
        }
    }
}