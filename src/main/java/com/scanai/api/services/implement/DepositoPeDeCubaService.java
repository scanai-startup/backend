package com.scanai.api.services.implement;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosDetalhamentoDepositoPeDeCuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosTrasfegaDepositoPeDeCuba;
import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosDetalhamentoPeDeCuba;
import com.scanai.api.infra.exceptions.customExceptions.BadRequest;
import com.scanai.api.repositories.DepositoPeDeCubaRepository;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.repositories.PeDeCubaRepository;
import com.scanai.api.services.DepositoPeDeCubaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DepositoPeDeCubaService implements DepositoPeDeCubaServiceInterface {

    @Autowired
    DepositoPeDeCubaRepository depositoPedecubaRepository;

    @Autowired
    DepositoRepository depositoRepository;

    @Autowired
    PeDeCubaService peDeCubaService;

    @Autowired
    PeDeCubaRepository peDeCubaRepository;

    public DadosDetalhamentoDepositoPeDeCuba register(DadosCadastroDepositoPeDeCuba data) {
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()).isPresent() || depositoRepository.existsMostroAtivo(data.fkdeposito()).isPresent()){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }

        var newDepositopedecuba = new Depositopedecuba(data);
        depositoPedecubaRepository.save(newDepositopedecuba);

        return new DadosDetalhamentoDepositoPeDeCuba(newDepositopedecuba);
    }

    public void setDataFim(Long fkpedecuba){
        Optional<Depositopedecuba> depositoPeDeCubaOpt = depositoPedecubaRepository.findByFkpedecubaAndDatafimIsNull(fkpedecuba);

        if(depositoPeDeCubaOpt.isEmpty()){
            throw new BadRequest("Deposito Pe De Cuba not found");
        }

        Depositopedecuba depositopedecuba = depositoPeDeCubaOpt.get();

        depositopedecuba.setDatafim(LocalDate.now());
        depositoPedecubaRepository.save(depositopedecuba);
    }

    //TODO refatorar este metodo para seguir os principios do single responsibility e aplicar de maneira correta o Optional
    public DadosDetalhamentoDepositoPeDeCuba trasfegaPedecuba(DadosTrasfegaDepositoPeDeCuba data) {
        Optional<Depositopedecuba> depositoPedecubaDestino = depositoRepository.existsPeDeCubaAtivo(data.fkdeposito());
        Optional<Depositopedecuba> depositopedecubaOrigem = depositoPedecubaRepository.findByFkpedecubaAndDatafimIsNull(data.fkpedecuba());
        //TODO ajeitar para chamar pelo service!!!
        Pedecuba pedecubaOrigem = peDeCubaRepository.getReferenceById(data.fkpedecuba());
        LocalDate now = LocalDate.now();

        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()).isPresent() || depositoRepository.existsMostroAtivo(data.fkdeposito()).isPresent()){
            throw new DataIntegrityViolationException("Impossível realizar trasfega, o deposito já contém outro produto ativo");

        } else if(depositoPedecubaDestino != null){ //case mistura
            //TODO ajeitar para chamar pelo service!!!
            Pedecuba pedecubaDestino = peDeCubaRepository.getReferenceById(depositoPedecubaDestino.get().getFkpedecuba());

            pedecubaOrigem.setFkpedecuba(pedecubaDestino.getId());
            depositoPedecubaDestino.get().setDatafim(now);
            pedecubaDestino.setDatafimfermentacao(now);
            depositopedecubaOrigem.get().setDatafim(now);
            pedecubaOrigem.setVolume(data.volumechegada());

            var newDepositopedecuba = new Depositopedecuba(new DadosCadastroDepositoPeDeCuba(data.fkpedecuba(), data.fkdeposito(), now, data.fkfuncionario()));
            depositoPedecubaRepository.save(newDepositopedecuba);

            return new DadosDetalhamentoDepositoPeDeCuba(newDepositopedecuba);
        } else {//case vazio
            var newDepositopedecuba = new Depositopedecuba(new DadosCadastroDepositoPeDeCuba(data.fkpedecuba(), data.fkdeposito(), now, data.fkfuncionario()));
            depositopedecubaOrigem.get().setDatafim(now);
            pedecubaOrigem.setVolume(data.volumechegada());
            depositoPedecubaRepository.save(newDepositopedecuba);

            return new DadosDetalhamentoDepositoPeDeCuba(newDepositopedecuba);
        }
    }
}
