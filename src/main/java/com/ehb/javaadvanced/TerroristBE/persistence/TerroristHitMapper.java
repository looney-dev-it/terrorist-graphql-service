package com.ehb.javaadvanced.TerroristBE.persistence;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import com.ehb.javaadvanced.TerroristBE.domain.TerroristHit;
import org.springframework.beans.BeanUtils;

/**************
 * Mapper from Entity to Domain and vice versa
 */

public class TerroristHitMapper {

    public static TerroristHitEntity toEntity(TerroristHit t){
        TerroristHitEntity e = new TerroristHitEntity();
        BeanUtils.copyProperties(t,e);
        return e;
    }

    public static TerroristHit toDomain(TerroristHitEntity e) {
        TerroristHit t = new TerroristHit();
        t.setId(e.getId());
        t.setHit_date(e.getHit_date().toString());
        t.setUser(UserMapper.toDomain(e.getUser()));
        t.setHit_criteria(e.getHit_criteria());
        t.setHit_content(e.getHit_content());
        return t;
    }
}
