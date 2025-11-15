
package com.mundomagico.api.service;

import org.springframework.lang.NonNull;
import com.mundomagico.api.model.Brinquedo;
import com.mundomagico.api.repository.BrinquedoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BrinquedoService {

    private final BrinquedoRepository brinquedoRepository;

    @Autowired
    public BrinquedoService(BrinquedoRepository brinquedoRepository) {
        this.brinquedoRepository = brinquedoRepository;
    }


    @NonNull 
    public Brinquedo salvarBrinquedo(Brinquedo   brinquedo) {
        return brinquedoRepository.save(brinquedo);
    }
    


    public List<Brinquedo> listarTodosBrinquedos() {
        return brinquedoRepository.findAll();
    }

    public void excluirBrinquedo(@NonNull Long id) {
    brinquedoRepository.deleteById(id);
}

}