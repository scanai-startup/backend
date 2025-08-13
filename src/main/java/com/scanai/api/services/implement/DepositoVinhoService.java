package com.scanai.api.services.implement;

import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.domain.depositovinho.dto.DadosTrasfegaDepositoVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.infra.exceptions.customExceptions.BadRequest;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.repositories.DepositoVinhoRepository;
import com.scanai.api.repositories.VinhoRepository;
import com.scanai.api.services.DepositoVinhoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DepositoVinhoService implements DepositoVinhoServiceInterface {

    @Autowired
    DepositoVinhoRepository depositoVinhoRepository;

    @Autowired
    DepositoRepository depositoRepository;

    @Autowired
    VinhoRepository vinhoRepository;

    public Depositovinho register(DadosCadastroDepositoVinho data) {
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()).isPresent() || depositoRepository.existsPeDeCubaAtivo(data.fkdeposito()).isPresent() || depositoRepository.existsMostroAtivo(data.fkdeposito()) != null){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }
        var newDepositovinho = new Depositovinho(data);
        depositoVinhoRepository.save(newDepositovinho);
        return newDepositovinho;
    }

    //TODO refatorar este método
    public Depositovinho trasfegaVinho(DadosTrasfegaDepositoVinho data) {
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()).isPresent() || depositoRepository.existsPeDeCubaAtivo(data.fkdeposito()).isPresent() || depositoRepository.existsMostroAtivo(data.fkdeposito()) != null){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }

        //case unico, volume total por enquanto
        Optional<Depositovinho> depositoVinhoOrigemOpt = depositoVinhoRepository.findByFkvinhoAndDatafimIsNull(data.fkvinho());

        if(depositoVinhoOrigemOpt.isEmpty()){
            throw new BadRequest("Deposito de origem not found");
        }

        Depositovinho depositoVinhoOrigem = depositoVinhoOrigemOpt.get();

        Vinho vinhoOrigem = vinhoRepository.getReferenceById(data.fkvinho());
        depositoVinhoOrigem.setDatafim(LocalDate.now()); //finaliza fermentação
        vinhoOrigem.setVolume(data.volumechegada()); //atualiza com perdas
        var newDepositovinho = new Depositovinho(new DadosCadastroDepositoVinho(data.fkvinho(), data.fkdeposito(), LocalDate.now(), data.fkfuncionario()));
        depositoVinhoRepository.save(newDepositovinho);
        return newDepositovinho;
    }

    public void setDataFim(Long fkvinho){
        Optional<Depositovinho> depositoVinhoOrigemOpt = depositoVinhoRepository.findByFkvinhoAndDatafimIsNull(fkvinho);

        if(depositoVinhoOrigemOpt.isEmpty()){
            throw new BadRequest("Deposito de origem not found");
        }

        Depositovinho depositoVinhoOrigem = depositoVinhoOrigemOpt.get();
        depositoVinhoOrigem.setDatafim(LocalDate.now());
        depositoVinhoRepository.save(depositoVinhoOrigem);
    }

}
