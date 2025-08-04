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
    MostroRepository mostroRepository;

    public Mostro register(DadosCadastroMostro data) {
        var newMostro = new Mostro(data);
        mostroRepository.save(newMostro);
        return newMostro;
    }

    public void softDelete(Long id) {
        Mostro mostro = mostroRepository.getReferenceById(id);
        mostro.setValid(false);
    }

    public void activate(Long id) {
        Mostro mostro = mostroRepository.getReferenceById(id);
        mostro.setValid(true);
    }

    public Mostro getElement(Long id) {
        return mostroRepository.getReferenceById(id);
    }

    public List<DadosListagemMostro> getAll() {
        return mostroRepository.findAllByValidTrue().stream().map(DadosListagemMostro::new).toList();
    }

    //TODO verificar como encapsular melhor este metodo, talvez colocar direto na entidade nao sei
    public Mostro createMostroFilho(Long idMostroOrigem, float volumeMostroFilho, float volumeRetiradoMostroOrigem, Long idFuncionario) {
        Mostro mostroOrigem = mostroRepository.getReferenceById(idMostroOrigem);
        mostroOrigem.setVolume(mostroOrigem.getVolume() - volumeRetiradoMostroOrigem);
        Mostro mostroFilho = new Mostro(new DadosCadastroMostro(idFuncionario, volumeMostroFilho, mostroOrigem.getId(), null));
        mostroRepository.save(mostroFilho);
        return mostroFilho;
    }
}
