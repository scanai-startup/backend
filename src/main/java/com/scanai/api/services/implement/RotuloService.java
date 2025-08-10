package com.scanai.api.services.implement;

import com.scanai.api.domain.rotulo.DTO.DadosAtualizarRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosCadastroRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosDetalhamentoRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosListagemRotulo;
import com.scanai.api.domain.rotulo.Rotulo;
import com.scanai.api.repositories.RotuloRepository;
import com.scanai.api.services.RotuloServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotuloService implements RotuloServiceInterface {

    @Autowired
    RotuloRepository rotuloRepository;

    @Transactional
    public DadosDetalhamentoRotulo register(DadosCadastroRotulo dados){
        return new DadosDetalhamentoRotulo(rotuloRepository.save(new Rotulo(dados)));
    }

    public List<DadosListagemRotulo> listAll(){
        return rotuloRepository.findAllByValidTrue().stream().map(DadosListagemRotulo::new).toList();
    }

    public DadosDetalhamentoRotulo getElement(Long id){
        return new DadosDetalhamentoRotulo(rotuloRepository.getReferenceById(id));

    }

    @Transactional
    public void hardDelete(Long id){
        rotuloRepository.deleteById(id);

    }

    @Transactional
    public DadosDetalhamentoRotulo update(DadosAtualizarRotulo dados) {
        Rotulo rotulo = rotuloRepository.getReferenceById(dados.id());

        rotulo.setNome(dados.nome());
        rotulo.setTipo(dados.tipo());
        rotuloRepository.save(rotulo);

        return new DadosDetalhamentoRotulo(rotulo);
    }
}
