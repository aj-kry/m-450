package ch.tbz.m450.controller;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService service;

    private Address a1, a2;

    @BeforeEach
    void setUp() {
        a1 = new Address();
        a1.setFirstname("Max");
        a1.setLastname("Meier");

        a2 = new Address();
        a2.setFirstname("Anna");
        a2.setLastname("Alp");
    }

    @Test
    void getAll_returnsOk() throws Exception {
        // Controller ruft service.getAll()
        when(service.getAll()).thenReturn(List.of(a2, a1));

        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        // optional: .andExpect(jsonPath("$[0].id").value(2));
    }

    @Test
    void getById_found() throws Exception {
        // Controller ruft service.getAddress(int)
        when(service.getAddress(1)).thenReturn(Optional.of(a1));

        mockMvc.perform(get("/address/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getById_notFound() throws Exception {
        when(service.getAddress(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/address/99"))
                .andExpect(status().isNotFound());
    }
}
