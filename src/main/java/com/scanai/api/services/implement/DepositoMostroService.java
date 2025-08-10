package com.scanai.api.services.implement;

import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosDetalhamentoDepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosTrasfegaDepositoMostro;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.mostro.dto.DadosDetalhamentoMostro;
import com.scanai.api.infra.exceptions.customExceptions.BadRequest;
import com.scanai.api.repositories.DepositoMostroRepository;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.repositories.MostroRepository;
import com.scanai.api.services.DepositoMostroServiceInterface;
import com.scanai.api.services.MostroServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DepositoMostroService implements DepositoMostroServiceInterface {

    @Autowired
    DepositoMostroRepository depositoMostroRepository;

    @Autowired
    DepositoRepository depositoRepository;

    @Autowired
    MostroServiceInterface mostroService;

    @Autowired
    MostroRepository mostroRepository;

    public DadosDetalhamentoDepositoMostro register(DadosCadastroDepositoMostro data) {
        return new DadosDetalhamentoDepositoMostro(depositoMostroRepository.save(new DepositoMostro(data)));
    }

    //TODO refatorar este metodo para seguir os principios do single responsibility
    public DadosDetalhamentoDepositoMostro trasfegaMostro(DadosTrasfegaDepositoMostro data) {
        Optional<DepositoMostro> depositoMostroExistenteOpt = depositoRepository.existsMostroAtivo(data.fkdeposito());

        //TODO na refatoŗaçao ver sobre esse cara ser null
        DepositoMostro depositoMostroExistente = depositoMostroExistenteOpt.get();

        //TODO ajeitar para utilizar apenas service
        Mostro mostroOrigem = mostroRepository.getReferenceById(data.fkmostro());

        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()).isPresent() || depositoRepository.existsPeDeCubaAtivo(data.fkdeposito()).isPresent()){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");

        }else if(depositoMostroExistente != null){ // cases de mistura
            //TODO ajeitar para utilizar apenas service
            Mostro mostroDestino = mostroRepository.getReferenceById(depositoMostroExistente.getFkmostro());

            if(data.volumetrasfega() == mostroOrigem.getVolume()){//case volume total
                Optional<DepositoMostro> depositoOrigem = depositoMostroRepository.findByFkmostroAndDatafimIsNull(data.fkmostro());
                depositoOrigem.get().setDatafim(LocalDate.now());

                DepositoMostro depositoMisturaMostro = this.mixMostros(data.fkdeposito(), data.fkfuncionario(), mostroOrigem, mostroDestino,
                        depositoMostroExistente, data.volumetrasfega(), data.volumechegada());

                depositoMostroRepository.save(depositoMisturaMostro);

                return new DadosDetalhamentoDepositoMostro(depositoMisturaMostro);

            }else if(data.volumetrasfega() < mostroOrigem.getVolume()){//case volume parcial
                float volumeMostroFilho = data.volumechegada() - mostroDestino.getVolume();

                Mostro mostroFilho = mostroService.createMostroFilho(data.fkmostro(), volumeMostroFilho, data.volumetrasfega(), data.fkfuncionario());

                DepositoMostro depositoMisturaMostro = this.mixMostros(data.fkdeposito(), data.fkfuncionario(), mostroFilho, mostroDestino, depositoMostroExistente,
                        data.volumetrasfega(), data.volumechegada());
                depositoMostroRepository.save(depositoMisturaMostro);

                return new DadosDetalhamentoDepositoMostro(depositoMisturaMostro);

            }else {
                throw new DataIntegrityViolationException("Impossível realizar trasfega com volume maior que o existente");

            }
        }else if(data.volumetrasfega() != mostroOrigem.getVolume()){ // case deposito vazio volume parcial
            float volumeMostroFilho = data.volumechegada();

            Mostro mostroFilho = mostroService.createMostroFilho(data.fkmostro(), volumeMostroFilho, data.volumetrasfega(), data.fkfuncionario());
            var newDepositomostro = new DepositoMostro(new DadosCadastroDepositoMostro(mostroFilho.getId(), data.fkdeposito(), LocalDate.now(), data.fkfuncionario()));
            depositoMostroRepository.save(newDepositomostro);

            return new DadosDetalhamentoDepositoMostro(newDepositomostro);

        } else{ // case deposito vazio volume total
            DepositoMostro depositoOrigem = depositoMostroRepository.findByFkmostroAndDatafimIsNull(data.fkmostro()).get();

            if(depositoOrigem != null){
                depositoOrigem.setDatafim(LocalDate.now());
            }

            mostroOrigem.setVolume(data.volumechegada());
            var newDepositomostro = new DepositoMostro(new DadosCadastroDepositoMostro(data.fkmostro(), data.fkdeposito(), LocalDate.now(), data.fkfuncionario()));
            depositoMostroRepository.save(newDepositomostro);

            return new DadosDetalhamentoDepositoMostro(newDepositomostro);
        }
    }

    public DadosDetalhamentoDepositoMostro findDepositoMostroByFkdepositoFkmostro(Long fkdeposito, Long fkmostro){
        Optional<DepositoMostro> depositoMostro = depositoMostroRepository.findByFkdepositoAndFkmostro(fkdeposito, fkmostro);

        if(depositoMostro.isEmpty()){
            throw new EntityNotFoundException("Deposito Mostro not found");
        }

        return new DadosDetalhamentoDepositoMostro(depositoMostro.get());
    }

    public void softDelete(Long fkdeposito, Long fkmostro){
        Optional<DepositoMostro> depositoMostroOpt = depositoMostroRepository.findByFkdepositoAndFkmostro(fkdeposito, fkmostro);

        if(depositoMostroOpt.isEmpty()){
            throw new BadRequest("Not valid deposito Id");
        }

        DepositoMostro depositoMostro = depositoMostroOpt.get();

        depositoMostro.setDatafim(LocalDate.now());
        depositoMostroRepository.save(depositoMostro);
    }

    public void setDataFim(Long fkmostro){
        Optional<DepositoMostro> depositoMostroOpt = depositoMostroRepository.findByFkmostroAndDatafimIsNull(fkmostro);

        if(depositoMostroOpt.isEmpty()){
            throw new BadRequest("Not valid deposito Id");
        }

        DepositoMostro depositoMostro = depositoMostroOpt.get();
        depositoMostro.setDatafim(LocalDate.now());
        depositoMostroRepository.save(depositoMostro);
    }

    //TODO revisar metodo e verificar funcionamento com e sem o atributo volumeTrasfega
    private DepositoMostro mixMostros(Long idDepositoDestino, Long idFuncionario, Mostro mostroOrigem, Mostro mostroDestino, DepositoMostro depositoMostroExistente, float volumeTrasfega, float volumeChegada){
        LocalDate now = LocalDate.now();
        mostroOrigem.setFimfermentacao(now);
        mostroDestino.setFimfermentacao(now);
        mostroOrigem.setValid(false);
        mostroDestino.setValid(false);
        DadosDetalhamentoMostro newMostro = mostroService.register(new DadosCadastroMostro(idFuncionario, volumeChegada, mostroOrigem.getId(), mostroDestino.getId()));
        depositoMostroExistente.setDatafim(now);
        return new DepositoMostro(new DadosCadastroDepositoMostro(newMostro.id(), idDepositoDestino, now, idFuncionario));
    }

}
