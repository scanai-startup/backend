package com.scanai.api.controllers;

import com.scanai.api.domain.tracking.DTO.DadosTrackingMostro;
import com.scanai.api.domain.tracking.DTO.DadosTrackingRotulo;
import com.scanai.api.domain.tracking.DTO.DadosTrackingVinho;
import com.scanai.api.services.implement.TrackingMostroService;
import com.scanai.api.services.implement.TrackingRotuloService;
import com.scanai.api.services.implement.TrackingVinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    TrackingMostroService trackingMostroService;
    @Autowired
    TrackingVinhoService trackingVinhoService;
    @Autowired
    TrackingRotuloService trackingRotuloService;

    @PostMapping("/mostro/{id}")
    public DadosTrackingMostro trackingMostro(@PathVariable Long id){
        return trackingMostroService.TrackingMostro(id);

    }
    @PostMapping("/vinho/{id}")
    public DadosTrackingVinho trackingVinho(@PathVariable Long id){
        return trackingVinhoService.trackingVinho(id);
    }
    @PostMapping("/rotulo/{id}")
    public DadosTrackingRotulo trackingRotulo(@PathVariable Long id){
        return trackingRotuloService.trackingRotulo(id);
    }
}
