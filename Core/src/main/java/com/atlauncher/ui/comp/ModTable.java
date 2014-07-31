package com.atlauncher.ui.comp;

import com.atlauncher.obj.Mod;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public final class ModTable
extends JTable{
    public ModTable(Mod... mods){
        super(new ModTableModel(mods));
    }

    private static final class ModTableModel
    extends AbstractTableModel{
        private final Mod[] mods;

        private ModTableModel(Mod... mods){
            this.mods = mods;
        }

        @Override
        public int getRowCount(){
            return this.mods.length;
        }

        @Override
        public int getColumnCount(){
            return 2;
        }

        @Override
        public String getColumnName(int col){
            return (col == 0 ? "Name" : "Description");
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex){
            return (columnIndex == 0 ? this.mods[rowIndex].name : this.mods[rowIndex].description);
        }

        @Override
        public boolean isCellEditable(int row, int col){
            return false;
        }
    }
}
