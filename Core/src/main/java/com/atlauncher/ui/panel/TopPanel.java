package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.event.UpdateCentralEvent;
import com.atlauncher.event.UpdateSettingsEvent;
import com.atlauncher.ui.comp.ExitButton;
import com.atlauncher.ui.comp.MinimizeButton;
import com.atlauncher.ui.comp.SocialMedia;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class TopPanel
extends JPanel{
    public TopPanel(){
        super(new BorderLayout());
        this.setOpaque(false);
        this.add(new RightPanel(), BorderLayout.EAST);
        this.add(new LeftPanel(), BorderLayout.WEST);
        this.add(new BottomPanel(), BorderLayout.SOUTH);
    }

    private static final class BottomPanel
    extends JPanel{
        private BottomPanel(){
            super(new FlowLayout(FlowLayout.LEFT, 0, 0));
            this.setOpaque(false);
            for(SocialMedia sm : SocialMedia.values()){
                JButton button = sm.button();
                button.setBorder(BorderFactory.createEmptyBorder(0, 25, 5, 0));
                this.add(button);
            }
        }
    }

    private static final class LeftPanel
    extends JPanel{
        private final JLabel header = new JLabel("ATLauncher - News", JLabel.LEFT);
        {
            this.header.setFont(new Font(Font.SANS_SERIF, Font.BOLD|Font.ITALIC, 24));
            this.header.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 5));
        }

        private LeftPanel(){
            super(new FlowLayout(FlowLayout.LEFT, 0, 0));
            this.setOpaque(false);
            ATLauncher.EVENT_BUS.register(this);
            this.add(this.header);
        }

        @Subscribe
        public void onTitleChange(UpdateCentralEvent e){
            this.header.setText("ATLauncher - " + e.title);
        }

        @Subscribe
        public void onSettingsChanged(UpdateSettingsEvent e){
            this.header.setText("ATLauncher - Settings - " + e.title);
        }
    }

    private static final class RightPanel
    extends JPanel{
        private RightPanel(){
            super(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            this.setOpaque(false);
            this.add(new MinimizeButton());
            this.add(new ExitButton());
        }
    }
}