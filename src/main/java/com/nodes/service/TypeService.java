package com.nodes.service;

import com.nodes.domain.Type;
import com.nodes.repository.TypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Type}.
 */
@Service
public class TypeService {

    private final Logger log = LoggerFactory.getLogger(TypeService.class);

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    /**
     * Save a type.
     *
     * @param type the entity to save.
     * @return the persisted entity.
     */
    public Type save(Type type) {
        log.debug("Request to save Type : {}", type);
        return typeRepository.save(type);
    }

    /**
     * Get all the types.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<Type> findAll(Pageable pageable) {
        log.debug("Request to get all Types");
        return typeRepository.findAll(pageable);
    }


    /**
     * Get one type by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Type> findOne(String id) {
        log.debug("Request to get Type : {}", id);
        return typeRepository.findById(id);
    }

    /**
     * Delete the type by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Type : {}", id);
        typeRepository.deleteById(id);
    }
}
