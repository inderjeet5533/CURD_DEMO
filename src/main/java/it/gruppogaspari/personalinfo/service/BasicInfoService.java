package it.gruppogaspari.personalinfo.service;

import it.gruppogaspari.personalinfo.entity.BasicInfoEntity;
import it.gruppogaspari.personalinfo.model.BasicInfo;
import it.gruppogaspari.personalinfo.model.BasicInfoRes;
import it.gruppogaspari.personalinfo.repository.BasicInfoRepository;
import it.gruppogaspari.personalinfo.service.helper.BasicInfoServiceHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BasicInfoService {

    private final BasicInfoRepository basicInfoRepository;
    private final BasicInfoServiceHelper basicInfoServiceHelper;

    public BasicInfoService(BasicInfoRepository basicInfoRepository, BasicInfoServiceHelper basicInfoServiceHelper){
        this.basicInfoRepository = basicInfoRepository;
        this.basicInfoServiceHelper = basicInfoServiceHelper;
    }

    public String saveBasicInfo(BasicInfo basicInfo) {
        log.info("saveBasicInfo called from BasicInfoService");
        BasicInfoEntity basicInfoEntity = basicInfoServiceHelper.basicInfoObjToEntity(basicInfo);
        BasicInfoEntity resEntity = basicInfoRepository.save(basicInfoEntity);
        return new StringBuilder("Record Successfully Inserted with uniqueID : ").append(resEntity.getInfoId()).toString();
    }

    public BasicInfoRes getBasicInfo() {
        log.info("getBasicInfo called from BasicInfoService");
        List<BasicInfoEntity> basicInfoEntityList = basicInfoRepository.findAll();
        return basicInfoServiceHelper.basicInfoEntityToObj(basicInfoEntityList);
    }

    public String updateBasicInfo(int infoId, BasicInfo basicInfo) {
        log.info("updateBasicInfo called from BasicInfoService");
        Optional<BasicInfoEntity> resEntity = basicInfoRepository.findById(infoId);
        if(resEntity.isPresent()){
            BasicInfoEntity basicInfoEntity = basicInfoServiceHelper.getBasicInfoEntity(basicInfo, resEntity.get());
            BasicInfoEntity res = basicInfoRepository.save(basicInfoEntity);
            return new StringBuilder("Record Successfully Updated with uniqueID : ").append(res.getInfoId()).toString();

        }else {
            return "";
        }
    }

    public String deleteInfo(int infoId) {
        log.info("DeleteInfo called from BasicInfoService");
        basicInfoRepository.deleteById(infoId);
        return new StringBuilder("Record Successfully Deleted with uniqueID : ").append(infoId).toString();
    }
}
