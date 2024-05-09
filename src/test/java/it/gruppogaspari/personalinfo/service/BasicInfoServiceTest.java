package it.gruppogaspari.personalinfo.service;

import it.gruppogaspari.personalinfo.TestHelper;
import it.gruppogaspari.personalinfo.entity.BasicInfoEntity;
import it.gruppogaspari.personalinfo.model.BasicInfo;
import it.gruppogaspari.personalinfo.model.BasicInfoRes;
import it.gruppogaspari.personalinfo.repository.BasicInfoRepository;
import it.gruppogaspari.personalinfo.service.helper.BasicInfoServiceHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BasicInfoServiceTest extends TestHelper {

    private BasicInfoRepository basicInfoRepository;
    private BasicInfoServiceHelper basicInfoServiceHelper;
    private BasicInfoService basicInfoService;

    @BeforeEach
    void setUp() {
        basicInfoRepository = Mockito.mock(BasicInfoRepository.class);
        basicInfoServiceHelper = new BasicInfoServiceHelper();
        basicInfoService = new BasicInfoService(basicInfoRepository, basicInfoServiceHelper);
    }

    @Test
    void saveBasicInfo() {
        BasicInfo basicInfo = getBasicInfo();
        BasicInfoEntity basicInfoEntity = getBasicInfoEntity();

        when(basicInfoRepository.save(any())).thenReturn(basicInfoEntity);
        String res =  basicInfoService.saveBasicInfo(basicInfo);

        assertEquals("Record Successfully Inserted with uniqueID : 1", res);

    }

    @Test
    void getBasicInfoTest() {
        List<BasicInfoEntity> formDataEntity = getAllBasicInfoEntity();

        when(basicInfoRepository.findAll()).thenReturn(formDataEntity);
        BasicInfoRes res =  basicInfoService.getBasicInfo();

        assertEquals(1, res.getBasicInfoList().size());
        assertEquals(1, res.getBasicInfoList().get(0).getInfoId());
        assertEquals("firstName", res.getBasicInfoList().get(0).getFirstName());
        assertEquals("lastName", res.getBasicInfoList().get(0).getLastName());
        assertEquals("xyz@domain.com", res.getBasicInfoList().get(0).getEmail());
        assertEquals("1234567892", res.getBasicInfoList().get(0).getContact());

    }

    @Test
    void updateBasicInfoTest() {
        BasicInfo basicInfo = getBasicInfoUpdated();
        Optional<BasicInfoEntity> basicInfoEntity = Optional.ofNullable(getBasicInfoEntity());

        when(basicInfoRepository.findById(1)).thenReturn(basicInfoEntity);
        when(basicInfoRepository.save(any())).thenReturn(basicInfoEntity.get());
        String res =  basicInfoService.updateBasicInfo(1, basicInfo);

        assertEquals("Record Successfully Updated with uniqueID : 1", res);
    }

    @Test
    void deleteInfoTest() {
        String res =  basicInfoService.deleteInfo(1);

        assertEquals("Record Successfully Deleted with uniqueID : 1", res);
    }
}