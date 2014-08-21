package com.atlauncher.utils;

import com.atlauncher.obj.Pack;

import java.util.Comparator;

public final class PacksComparator
implements Comparator<Pack>{
    @Override
    public int compare(Pack o1, Pack o2){
        if(o1.position == o2.position){
            return 0;
        } else if(o1.position > o2.position){
            return 1;
        } else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj){
        return false;
    }
}