package com.pablocontreras.resttesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablocontreras.resttesting.controller.ClienteController;
import com.pablocontreras.resttesting.model.Cliente;
import com.pablocontreras.resttesting.repository.ClienteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClienteController.class, secure = false)
public class RestTestingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClienteRepository clienteRepository;

	private List<Cliente> getListStubData(){
	    List<Cliente> lista = new ArrayList<Cliente>();
	    lista.add(getStubData());
	    return lista;
    }

    private Cliente getStubData(){
	    Cliente cliente = new Cliente();
	    cliente.setIdCliente(3);
	    cliente.setRutCliente("174592225");
	    cliente.setNombre("Pablo");
	    cliente.setApellido("Contreras");
	    return cliente;
    }

	@Before
    public void setUp(){
	    MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testGetClientes() throws Exception {
        List<Cliente> lista = getListStubData();

        when(clienteRepository.findAll()).thenReturn(lista);

        String uri = "/api/clientes";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        String content = result.getResponse().getContentAsString();

        int status = result.getResponse().getStatus();

        verify(clienteRepository,times(1)).findAll();

        Assert.assertEquals("falló: se esperaba status code 200",200,status);

        Assert.assertTrue("falló: se esperaba contenido en el cuerpo de la respuesta",content.trim().length()>0);
    }
	@Test
	public void testAddCliente() throws Exception {
        Cliente cliente = getStubData();

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        ObjectMapper mapper = new ObjectMapper();
        String uri = "/api/clientes";
        String inputJson =mapper.writeValueAsString(cliente);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        int status = result.getResponse().getStatus();

        verify(clienteRepository,times(1)).save(any(Cliente.class));

        Assert.assertEquals("falló: se esperaba status code 201",201,status);
        Assert.assertTrue("falló: contenido del cuerpo es nulo",content.trim().length()>0);

        Cliente clienteCreado = mapper.readValue(content,Cliente.class);

        Assert.assertNotNull("error: cliente nulo",clienteCreado);
        Assert.assertNotNull("error: id esperado no nulo",clienteCreado.getIdCliente());
        Assert.assertEquals("error: nombre no coincide",cliente.getNombre(),clienteCreado.getNombre());
	}
}
