package it.gruppogaspari.personalinfo.controller;

import it.gruppogaspari.personalinfo.TestHelper;
import it.gruppogaspari.personalinfo.exception.CustomExceptionHandler;
import it.gruppogaspari.personalinfo.exception.model.CustomErrorResponse;
import it.gruppogaspari.personalinfo.model.BasicInfo;
import it.gruppogaspari.personalinfo.model.BasicInfoRes;
import it.gruppogaspari.personalinfo.service.BasicInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PersonalInfoApplication.class)
@WebAppConfiguration
class BasicInfoControllerTest extends TestHelper {

    public MockMvc mvc;
    public BasicInfoService basicInfoService;

    @BeforeEach
    void setUp() {
        basicInfoService = Mockito.mock(BasicInfoService.class);
        BasicInfoController basicInfoController = new BasicInfoController(basicInfoService);
        mvc =  MockMvcBuilders.standaloneSetup(basicInfoController)
                .setControllerAdvice(new CustomExceptionHandler()) //This line is necessary if you want to call your handler in case of exception.
                .build();
    }


    @Test
    void saveBasicInfoTest_withSuccess() throws Exception {
        String uri = "/saveinfo";
        BasicInfo basicInfo = getBasicInfo();
        String basicInfoString = mapToJson(basicInfo);
        Mockito.when(basicInfoService.saveBasicInfo(any()))
                .thenReturn("Record Successfully Inserted with uniqueID : 1");

        MvcResult mvcResult = mvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(basicInfoString))
                .andExpect(status().isCreated())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Record Successfully Inserted with uniqueID : 1", content);

    }

    @Test
    void saveBasicInfoTest_with400_whenDetails_notPresent() throws Exception {
        String uri = "/saveinfo";
        BasicInfo basicInfo = getBasicInfoWithMissingDetails();
        String basicInfoString = mapToJson(basicInfo);

        MvcResult mvcResult = mvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(basicInfoString))
                .andExpect(status().isBadRequest())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        CustomErrorResponse errorRes = mapFromJson(content, CustomErrorResponse.class);
        assertEquals("Bad Request", errorRes.getDescription());
        assertEquals(4, errorRes.getErrors().size());
        assertEquals("firstName", errorRes.getErrors().stream().filter(e -> e.getName().equals("firstName"))
                .findFirst().get().getName());
        assertEquals("lastName", errorRes.getErrors().stream().filter(e -> e.getName().equals("lastName"))
                .findFirst().get().getName());
        assertEquals("email", errorRes.getErrors().stream().filter(e -> e.getName().equals("email")).
                findFirst().get().getName());
        assertEquals("contact", errorRes.getErrors().stream().filter(e -> e.getName().equals("contact"))
                .findFirst().get().getName());
        assertEquals("must not be blank", errorRes.getErrors().get(0).getMessage());
        assertEquals("must not be blank", errorRes.getErrors().get(1).getMessage());
        assertEquals("must not be blank", errorRes.getErrors().get(2).getMessage());
        assertEquals("must not be blank", errorRes.getErrors().get(3).getMessage());

    }

    @Test
    void getBasicInfoTest_success() throws Exception {
        String uri = "/getinfo";
        BasicInfoRes basicInfoRes = getBasicInfoRes();
        String basicInfoResString = mapToJson(basicInfoRes);
        Mockito.when(basicInfoService.getBasicInfo())
                .thenReturn(basicInfoRes);

        MvcResult mvcResult = mvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(basicInfoResString))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        BasicInfoRes res = mapFromJson(content, BasicInfoRes.class);
        assertEquals(1, res.getBasicInfoList().size());
        assertEquals("firstName", res.getBasicInfoList().get(0).getFirstName());
        assertEquals("lastName", res.getBasicInfoList().get(0).getLastName());
        assertEquals("xyz@domain.com", res.getBasicInfoList().get(0).getEmail());
        assertEquals("1234567892", res.getBasicInfoList().get(0).getContact());
    }

    @Test
    void getUpdateInfoTest() throws Exception {
        String uri = "/updateinfo/1";
        BasicInfo basicInfo = getBasicInfoUpdated();
        String basicInfoString = mapToJson(basicInfo);
        Mockito.when(basicInfoService.updateBasicInfo(1, basicInfo))
                .thenReturn("Record Successfully Updated with uniqueID : 1");

        MvcResult mvcResult = mvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(basicInfoString))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Record Successfully Updated with uniqueID : 1", content);
    }

    @Test
    void deleteInfoTest() throws Exception {
        String uri = "/deleteinfo/1";
        Mockito.when(basicInfoService.deleteInfo(1))
                .thenReturn("Record Successfully Deleted with uniqueID : 1");

        MvcResult mvcResult = mvc.perform(delete(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Record Successfully Deleted with uniqueID : 1", content);
    }
}