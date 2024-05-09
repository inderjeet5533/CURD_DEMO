package it.gruppogaspari.personalinfo.controller;

import it.gruppogaspari.personalinfo.model.BasicInfo;
import it.gruppogaspari.personalinfo.model.BasicInfoRes;
import it.gruppogaspari.personalinfo.service.BasicInfoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@Slf4j
public class BasicInfoController {

    private final BasicInfoService basicInfoService;

    public BasicInfoController(BasicInfoService basicInfoService){
        this.basicInfoService = basicInfoService;
    }

    /*Save Basic details of the person*/
    @PostMapping(value = "/saveinfo", consumes = "application/json")
    public ResponseEntity<String> saveBasicInfo(@Valid @RequestBody BasicInfo basicInfo){
        log.info("saveBasicInfo called from BasicInfoController");
        String res = basicInfoService.saveBasicInfo(basicInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    /*Get List of All Basic details of the person*/
    @GetMapping(value = "/getinfo", produces = "application/json")
    public ResponseEntity<BasicInfoRes> getBasicInfo(){
        log.info("getBasicInfo called from BasicInfoController");
        BasicInfoRes res = basicInfoService.getBasicInfo();
        return ResponseEntity.ok(res);
    }

    /*Update Record of the person by InfoId*/
    @PutMapping(value = "/updateinfo/{id}", consumes = "application/json")
    public ResponseEntity<String> getUpdateInfo(@PathVariable("id") int infoId,
                                                @Valid @RequestBody BasicInfo basicInfo){
        log.info("getUpdateInfo called from BasicInfoController");
        String res = basicInfoService.updateBasicInfo(infoId, basicInfo);
        return res.isBlank() ? ResponseEntity.noContent().build() : ResponseEntity.ok(res);
    }

    /*Delete Record of the person by InfoId*/
    @DeleteMapping(value = "/deleteinfo/{id}")
    public ResponseEntity<String> deleteInfo(@PathVariable("id") int infoId){
        log.info("deleteInfo called from BasicInfoController");
        String res = basicInfoService.deleteInfo(infoId);
        return ResponseEntity.accepted().body(res);
    }

}
