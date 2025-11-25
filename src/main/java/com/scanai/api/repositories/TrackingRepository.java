package com.scanai.api.repositories;

import com.scanai.api.domain.tracking.DTO.DadosTrackingMostro;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackingRepository {
    @Query(value = """
            SELECT M.id, M.volume, M1.id, M1.volume, M2.id, M2.volume, U.id, R
            
            
            
            """, nativeQuery = true)
    List<DadosTrackingMostro> trackingMosto();
}
