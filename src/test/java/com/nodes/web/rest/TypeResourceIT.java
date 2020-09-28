package com.nodes.web.rest;

import com.nodes.AbstractNeo4jIT;
import com.nodes.Nodes2020App;
import com.nodes.domain.Type;
import com.nodes.repository.TypeRepository;
import com.nodes.service.TypeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TypeResource} REST controller.
 */
@SpringBootTest(classes = Nodes2020App.class)
@ExtendWith(AbstractNeo4jIT.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private TypeService typeService;

    @Autowired
    private MockMvc restTypeMockMvc;

    private Type type;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Type createEntity() {
        Type type = new Type()
            .name(DEFAULT_NAME);
        return type;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Type createUpdatedEntity() {
        Type type = new Type()
            .name(UPDATED_NAME);
        return type;
    }

    @BeforeEach
    public void initTest() {
        typeRepository.deleteAll();
        type = createEntity();
    }

    @Test
    public void createType() throws Exception {
        int databaseSizeBeforeCreate = typeRepository.findAll().size();
        // Create the Type
        restTypeMockMvc.perform(post("/api/types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(type)))
            .andExpect(status().isCreated());

        // Validate the Type in the database
        List<Type> typeList = typeRepository.findAll();
        assertThat(typeList).hasSize(databaseSizeBeforeCreate + 1);
        Type testType = typeList.get(typeList.size() - 1);
        assertThat(testType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    public void createTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeRepository.findAll().size();

        // Create the Type with an existing ID
        type.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeMockMvc.perform(post("/api/types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(type)))
            .andExpect(status().isBadRequest());

        // Validate the Type in the database
        List<Type> typeList = typeRepository.findAll();
        assertThat(typeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeRepository.findAll().size();
        // set the field null
        type.setName(null);

        // Create the Type, which fails.


        restTypeMockMvc.perform(post("/api/types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(type)))
            .andExpect(status().isBadRequest());

        List<Type> typeList = typeRepository.findAll();
        assertThat(typeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllTypes() throws Exception {
        // Initialize the database
        typeRepository.save(type);

        // Get all the typeList
        restTypeMockMvc.perform(get("/api/types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    public void getType() throws Exception {
        // Initialize the database
        typeRepository.save(type);

        // Get the type
        restTypeMockMvc.perform(get("/api/types/{id}", type.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    public void getNonExistingType() throws Exception {
        // Get the type
        restTypeMockMvc.perform(get("/api/types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateType() throws Exception {
        // Initialize the database
        typeService.save(type);

        int databaseSizeBeforeUpdate = typeRepository.findAll().size();

        // Update the type
        Type updatedType = typeRepository.findById(type.getId()).get();
        updatedType
            .name(UPDATED_NAME);

        restTypeMockMvc.perform(put("/api/types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedType)))
            .andExpect(status().isOk());

        // Validate the Type in the database
        List<Type> typeList = typeRepository.findAll();
        assertThat(typeList).hasSize(databaseSizeBeforeUpdate);
        Type testType = typeList.get(typeList.size() - 1);
        assertThat(testType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    public void updateNonExistingType() throws Exception {
        int databaseSizeBeforeUpdate = typeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeMockMvc.perform(put("/api/types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(type)))
            .andExpect(status().isBadRequest());

        // Validate the Type in the database
        List<Type> typeList = typeRepository.findAll();
        assertThat(typeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteType() throws Exception {
        // Initialize the database
        typeService.save(type);

        int databaseSizeBeforeDelete = typeRepository.findAll().size();

        // Delete the type
        restTypeMockMvc.perform(delete("/api/types/{id}", type.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Type> typeList = typeRepository.findAll();
        assertThat(typeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
