package it.gruppogaspari.personalinfo.service.helper;

import it.gruppogaspari.personalinfo.entity.AddressEntity;
import it.gruppogaspari.personalinfo.entity.BasicInfoEntity;
import it.gruppogaspari.personalinfo.model.Address;
import it.gruppogaspari.personalinfo.model.BasicInfo;
import it.gruppogaspari.personalinfo.model.BasicInfoRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BasicInfoServiceHelper {

    public BasicInfoEntity basicInfoObjToEntity(BasicInfo basicInfo) {
        log.info("basicInfoObjToEntity called from BasicInfoServiceHelper");
        Address address = basicInfo.getAddress();
        return BasicInfoEntity.builder()
                .firstName(basicInfo.getFirstName())
                .lastName(basicInfo.getLastName())
                .email(basicInfo.getEmail())
                .contact(basicInfo.getContact())
                .address(AddressEntity.builder()
                        .addressLine1(address.getAddressLine1())
                        .addressLine2(address.getAddressLine2())
                        .city(address.getCity())
                        .state(address.getState())
                        .country(address.getCountry())
                        .pinCode(address.getPinCode())
                        .build())
                .build();
    }

    public BasicInfoRes basicInfoEntityToObj(List<BasicInfoEntity> basicInfoEntityList) {
        log.info("basicInfoEntityToObj called from BasicInfoServiceHelper");
        return BasicInfoRes.builder()
                .basicInfoList(basicInfoEntityList.stream()
                        .map(this::getBasicInfo)
                        .collect(Collectors.toList()))
                .build();
    }

    private BasicInfo getBasicInfo(BasicInfoEntity e) {
        log.info("getBasicInfo called from BasicInfoServiceHelper");
        AddressEntity addressEntity = e.getAddress();
        return BasicInfo.builder()
                .infoId(e.getInfoId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .email(e.getEmail())
                .contact(e.getContact())
                .address(Address.builder()
                        .addressLine1(addressEntity.getAddressLine1())
                        .addressLine2(addressEntity.getAddressLine2())
                        .city(addressEntity.getCity())
                        .state(addressEntity.getState())
                        .country(addressEntity.getCountry())
                        .pinCode(addressEntity.getPinCode())
                        .build())
                .build();
    }

    public BasicInfoEntity getBasicInfoEntity(BasicInfo basicInfo, BasicInfoEntity resEntity) {
        Address address = basicInfo.getAddress();
        BasicInfoEntity basicInfoEntity = resEntity.toBuilder()
                .firstName(basicInfo.getFirstName())
                .lastName(basicInfo.getLastName())
                .email(basicInfo.getEmail())
                .contact(basicInfo.getContact())
                .address(AddressEntity.builder()
                        .addressLine1(address.getAddressLine1())
                        .addressLine2(address.getAddressLine2())
                        .city(address.getCity())
                        .state(address.getState())
                        .country(address.getCountry())
                        .pinCode(address.getPinCode())
                        .build())
                .build();
        return basicInfoEntity;
    }

}
