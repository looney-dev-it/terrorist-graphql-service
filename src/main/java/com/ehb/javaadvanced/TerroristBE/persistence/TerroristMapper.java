package com.ehb.javaadvanced.TerroristBE.persistence;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import org.springframework.beans.BeanUtils;

public class TerroristMapper {

    public static TerroristEntity toEntity(Terrorist t){
        TerroristEntity e = new TerroristEntity();
        BeanUtils.copyProperties(t,e);
        return e;
    }

    public static Terrorist toDomain(TerroristEntity e) {
        Terrorist t = new Terrorist();
        BeanUtils.copyProperties(e,t);
        return t;
    }
}
