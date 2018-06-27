package com.pablocontreras.resttesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablocontreras.resttesting.controller.ClienteController;
import com.pablocontreras.resttesting.entity.Cliente;
import com.pablocontreras.resttesting.repository.ClienteRepositorio;
import com.pablocontreras.resttesting.service.ClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.theInstance;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(MockitoJUnitRunner.class)
public class RestTestingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }
    @Test
    public void getClientes() throws Exception{

        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(new Cliente());
        clienteList.add(new Cliente());

        when(clienteService.listar()).thenReturn((List)clienteList);
        mockMvc.perform(get("/api/clientes")).andExpect(status().isOk());

    }
    @Test
    public void createCliente() throws Exception{
        Cliente mockCliente = new Cliente();
        mockCliente.setId(3L);
        mockCliente.setRut("206284293");
        mockCliente.setNombre("Suzan");
        mockCliente.setApellido("Contreras");

        ObjectMapper mapper = new ObjectMapper();

        String inputJson = mapper.writeValueAsString(mockCliente);

        String URI =  "/api/clientes";

        mockMvc.perform(get("/api/clientes")).andExpect(status().isOk());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8).content(inputJson);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        int status = response.getStatus();
        Assert.assertEquals(200,status);


    }
}
