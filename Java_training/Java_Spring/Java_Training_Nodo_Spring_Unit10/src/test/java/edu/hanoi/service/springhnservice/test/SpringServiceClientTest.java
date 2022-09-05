package edu.hanoi.service.springhnservice.test;

import edu.hanoi.service.springhnservice.model.Group;
import edu.hanoi.service.springhnservice.model.User;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class SpringServiceClientTest {

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient httpClinet = builder.build();
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClinet));
    }

    @Test
    public void userTest() {
        String address = "http://localhost:8080/user/list";
        ResponseEntity<List> entity = restTemplate.getForEntity(address, List.class);
        List users = entity.getBody();
    }

    @Test
    public void groupTest() {
        String address = "http://localhost:8080/group/list";
        ResponseEntity<Group[]> entity = restTemplate.getForEntity(address, Group[].class);
        Group[] groups = entity.getBody();
        Assert.assertTrue(groups.length > 0);

        for (int i = 0; i < groups.length; i++) {
            System.out.println(groups[i].getId());
        }
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("nvhct2002vn");
        user.setPassword("nvhct2002vn");
        user.setEmail("viethien123vn@gmail.com");
        user.setAge(20);
        user.setGroupId(2);

        String address = "http://localhost:8080/user/add";
        ResponseEntity<String> entity = restTemplate.postForEntity(address, user, String.class);
        Assert.assertEquals(user.getUsername(), entity.getBody());
    }

    @Test
    public void getUser() {
        String address = "http://localhost:8080/user/nvhct2002vn";
        ResponseEntity<User> entity = restTemplate.getForEntity(address, User.class);
        Assert.assertEquals("nvhct2002vn", entity.getBody().getPassword());
    }

    @Test
    public void deleteUser() {
        String address = "http://localhost:8080/user/delete/nvhct2002vn";
        ResponseEntity<Void> delEntity = restTemplate.getForEntity(address, Void.class);

        String address2 = "http://localhost:8080/user/delete/nvhct2002vn";
        ResponseEntity<User> getEntity = restTemplate.getForEntity(address2, User.class);

        Assert.assertEquals(null, getEntity.getBody());
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUsername("nvhct20021vn");
        user.setPassword("hien2002vn");
        user.setEmail("hien2002vn@gmail.com");
        user.setAge(22);
        user.setGroupId(2);

        String address = "http://localhost:8080/user/update";
        ResponseEntity<Void> entity = restTemplate.postForEntity(address, user, Void.class);
    }
}
