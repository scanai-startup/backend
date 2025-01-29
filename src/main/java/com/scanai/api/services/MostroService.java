package com.scanai.api.services;

import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.repositories.MostroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MostroService {

    @Autowired
    MostroRepository repository;


    public Mostro register(DadosCadastroMostro data) {
        var newMostro = new Mostro(data);
        repository.save(newMostro);
        return newMostro;
    }

    public void softDelete(Mostro mostro) {
        mostro.setValid(false);
    }

    public void activate(Mostro mostro) {
        mostro.setValid(true);
    }

    public Mostro getElement(Long id) {
        return repository.getReferenceById(id);
    }

    public Mostro createMostroFilho(Long idMostroOrigem, float volumeMostroFilho, float volumeRetiradoMostroOrigem, Long idFuncionario) {
        Mostro mostroOrigem = repository.getReferenceById(idMostroOrigem);
        mostroOrigem.setVolume(mostroOrigem.getVolume() - volumeRetiradoMostroOrigem);
        Mostro mostroFilho = new Mostro(new DadosCadastroMostro(idFuncionario, volumeMostroFilho, mostroOrigem.getId(), null));
        repository.save(mostroFilho);
        return mostroFilho;
    }
}
