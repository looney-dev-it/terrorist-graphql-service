package com.ehb.javaadvanced.TerroristBE.subscription;

import com.ehb.javaadvanced.TerroristBE.domain.TerroristHit;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristHitEntity;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristHitMapper;
import com.ehb.javaadvanced.TerroristBE.persistence.UserEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;

@Controller
public class TerroristHitSubscription {

    private final Sinks.Many<TerroristHitEntity> sink =
            Sinks.many().replay().all();

    @SubscriptionMapping
    public Flux<TerroristHit> terroristHitAdded() {
        return sink.asFlux().map(TerroristHitMapper::toDomain);
    }

    public void publishHit(TerroristHitEntity hit) {
        sink.tryEmitNext(hit);
    }
}
