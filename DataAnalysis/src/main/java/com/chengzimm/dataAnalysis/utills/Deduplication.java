package com.chengzimm.dataAnalysis.utills;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Deduplication {

    public static List<String> deduplication(List<String> list){
        List<String> temp = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (String str : list){
            if (!set.contains(str)){
                set.add(str);
                temp.add(str);
            }
        }
        return temp;
    }
}
