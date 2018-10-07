package net.javacode.eshop.controller;

import net.javacode.eshop.entity.Cat;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.*;

public class CatControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";

    @Before
    public void init() {
        System.out.println("init");
    }

    @BeforeClass
    public static void globalInit() {
        System.out.println("global init");
    }

    @Test
    public void addCat() {
        Cat cat = createdCat();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Cat> entity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                cat.getId()
        );

        assertEquals("OK", entity.getStatusCode().getReasonPhrase());
        Cat recivedCat = entity.getBody();
        assertNotNull(recivedCat);
        assertEquals(cat.getId(), recivedCat.getId());
        assertNotNull(recivedCat.getDescription());
    }

    @Test
    public void deleteCat() {
        Cat cat = createdCat();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + DELETE + "/?id={id}",
                HttpMethod.DELETE,
                null,
                Cat.class,
                cat.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Cat deletedCat = responseEntity.getBody();
        assertNotNull(deletedCat.getName());

        ResponseEntity<Cat> responseForDeletedCat = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                deletedCat.getId()
        );

        assertEquals("OK", responseForDeletedCat.getStatusCode().getReasonPhrase());
        assertNull(responseForDeletedCat.getBody());
    }

    @Test
    public void getAllCats() {
        createdCat();
        createdCat();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Cat>> responseEntity = restTemplate.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {
                }
        );

        List<Cat> catList = responseEntity.getBody();

        assertNotNull(catList.get(0));
        assertNotNull(catList.get(1));

    }

    @Test
    public void updateCat() {
        Cat cat = createdCat();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat forUpdateCat = updatedCat(cat.getId());

        HttpEntity<Cat> httpEntity = new HttpEntity<>(forUpdateCat, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cat> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Cat.class
        );

        Cat updatedCat = responseEntity.getBody();
        assertEquals(cat.getId(), updatedCat.getId());
        assertNotEquals(cat.getName(), updatedCat.getName());
        assertNotEquals(cat.getDescription(), updatedCat.getDescription());

    }

    private Cat updatedCat(Long id) {
        Cat cat = new Cat();
        cat.setId(id);
        cat.setName("Updated Barsik");
        cat.setDescription("updated Description");
        return cat;
    }

    private Cat createdCat() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat();

        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, headers);
        RestTemplate template = new RestTemplate();

        Cat createdCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Cat.class
        ).getBody();

        assertNotNull(createdCat);
        assertEquals(cat.getName(), createdCat.getName());

        return createdCat;
    }

    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("Barsik");
        cat.setDescription("happy");
        return cat;
    }

    @After
    public void clean() {
        System.out.println("clean");
    }

    @AfterClass
    public static void globalClean() {
        System.out.println("global clean");
    }
}
