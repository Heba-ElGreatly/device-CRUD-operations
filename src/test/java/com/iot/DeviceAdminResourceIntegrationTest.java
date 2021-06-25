package com.iot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.admin.dao.DeviceAdminRepository;
import com.iot.dto.SIMCardDto;
import com.iot.model.SIMCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrackingDevices.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceAdminResourceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private Integer port;

    @Autowired
    private DeviceAdminRepository adminRepository;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void getWaitingForActivationDevicesTest() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<SIMCardDto>> entity = new HttpEntity(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(getRootUrl() + "/admin/", HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());
        assertEquals(OK, response.getStatusCode());
//        assertNotNull(response.getBody());
    }

    @Test
    public void getDeviceByIdTest() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<SIMCardDto>> entity = new HttpEntity(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(getRootUrl() + "/admin/get/" + 2, HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());
        SIMCard device = new ObjectMapper().readValue(response.getBody(), SIMCard.class);
        assertEquals(OK, response.getStatusCode());
//        assertEquals("008342123", device.getSimNumber());
//        assertNotNull(response.getBody());
    }

    @Test
    public void updateDeviceStatusTest() throws JsonProcessingException {
        SIMCard device, updatedDevice = null;
        Integer userId = 1, deviceId = 2;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(getRootUrl() + "/admin/get/" + deviceId, HttpMethod.GET, entity, String.class);
        device = new ObjectMapper().readValue(response.getBody(), SIMCard.class);
        device.setStatus("deactivated");
        restTemplate.put(getRootUrl() + "/admin/update-device?userId=" + userId, device);
        Optional<SIMCard> simCardFromDB = adminRepository.findById(deviceId);
        assertEquals("deactivated",simCardFromDB.get().getStatus());
    }

    @Test
    public void deleteDeviceByDeviceIdTest() {
        SIMCard device = null;
        Integer deviceId = 2, userId = 1;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        restTemplate.delete(getRootUrl() + "/admin/remove-device?userId="+userId+"&deviceId="+deviceId);
        ResponseEntity<String> response =
                restTemplate.exchange(getRootUrl() + "/admin/get/" + deviceId, HttpMethod.GET, entity, String.class);
        assertEquals(NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deviceCantBeDeletedTest() throws JsonProcessingException {
        Integer deviceId = 2, userId = 2;
        SIMCard device;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        restTemplate.delete(getRootUrl() + "/admin/remove-device?userId=" + userId + "&deviceId=" + deviceId);
        ResponseEntity<String> response =
                restTemplate.exchange(getRootUrl() + "/admin/get/" + deviceId, HttpMethod.GET, entity, String.class);
        device = new ObjectMapper().readValue(response.getBody(), SIMCard.class);
        assertNotNull(device);
    }
}
