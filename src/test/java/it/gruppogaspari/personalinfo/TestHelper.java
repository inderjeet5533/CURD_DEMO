package it.gruppogaspari.personalinfo;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.gruppogaspari.personalinfo.entity.AddressEntity;
import it.gruppogaspari.personalinfo.entity.BasicInfoEntity;
import it.gruppogaspari.personalinfo.model.Address;
import it.gruppogaspari.personalinfo.model.BasicInfo;
import it.gruppogaspari.personalinfo.model.BasicInfoRes;

import java.io.IOException;
import java.util.List;

public class TestHelper {

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    protected static BasicInfo getBasicInfo() {
        return BasicInfo.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("xyz@domain.com")
                .contact("1234567892")
                .address(getAddress())
                .build();
    }

    protected static BasicInfo getBasicInfoUpdated() {
        return BasicInfo.builder()
                .infoId(1)
                .firstName("firstNameUpdate")
                .lastName("lastName")
                .email("xyz@domain.com")
                .contact("1234567892")
                .address(getAddress())
                .build();
    }

    private static Address getAddress() {
        return Address.builder()
                .addressLine1("sfsf sdf sf sf ")
                .addressLine2("")
                .city("teracina")
                .state("Latina")
                .country("Italy")
                .pinCode("42105")
                .build();
    }

    protected static BasicInfo getBasicInfoWithMissingDetails() {
        return BasicInfo.builder()
                .build();
    }

    protected static BasicInfoRes getBasicInfoRes() {
        return BasicInfoRes.builder()
                .basicInfoList(List.of(BasicInfo.builder()
                        .infoId(1)
                        .firstName("firstName")
                        .lastName("lastName")
                        .email("xyz@domain.com")
                        .contact("1234567892")
                        .address(getAddress())
                        .build()))
                .build();
    }

    protected static BasicInfoEntity getBasicInfoEntity() {
        return BasicInfoEntity.builder()
                .infoId(1)
                .firstName("firstName")
                .lastName("lastName")
                .email("xyz@domain.com")
                .contact("1234567892")
                .address(getAddressEntity())
                .build();
    }

    private static AddressEntity getAddressEntity() {
        return AddressEntity.builder()
                .addressId(1)
                .addressLine1("sfsf sdf sf sf ")
                .addressLine2("")
                .city("teracina")
                .state("Latina")
                .country("Italy")
                .pinCode("42105")
                .build();
    }

    protected static List<BasicInfoEntity> getAllBasicInfoEntity() {
        return List.of(getBasicInfoEntity());
    }

}
