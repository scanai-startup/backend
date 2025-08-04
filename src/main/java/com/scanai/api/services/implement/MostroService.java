package com.scanai.api.services.implement;

import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.mostro.dto.DadosListagemMostro;
import com.scanai.api.repositories.MostroRepository;
import com.scanai.api.services.MostroServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MostroService implements MostroServiceInterface {

    @Autowired
    MostroRepository repository;

    public Mostro register(DadosCadastroMostro data) {
        var newMostro = new Mostro(data);
        repository.save(newMostro);
        return newMostro;
    }

    public void softDelete(Long id) {
        Mostro mostro = repository.getReferenceById(id);
        mostro.setValid(false);
    }

    public void activate(Long id) {
        Mostro mostro = repository.getReferenceById(id);
        mostro.setValid(true);
    }

    public Mostro getElement(Long id) {
        return repository.getReferenceById(id);
    }

    public List<DadosListagemMostro> getAll() {
        return repository.findAllByValidTrue().stream().map(DadosListagemMostro::new).toList();
    }

    public Mostro createMostroFilho(Long idMostroOrigem, float volumeMostroFilho, float volumeRetiradoMostroOrigem, Long idFuncionario) {
        Mostro mostroOrigem = repository.getReferenceById(idMostroOrigem);
        mostroOrigem.setVolume(mostroOrigem.getVolume() - volumeRetiradoMostroOrigem);
        Mostro mostroFilho = new Mostro(new DadosCadastroMostro(idFuncionario, volumeMostroFilho, mostroOrigem.getId(), null));
        repository.save(mostroFilho);
        return mostroFilho;
    }
}
