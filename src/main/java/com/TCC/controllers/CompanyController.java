package com.TCC.controllers;

import com.TCC.domain.company.Company;
import com.TCC.domain.company.CompanyDTO;
import com.TCC.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies (
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email
    ){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompanies(name, address, phone, email));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getCompanyyId (@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompanyById(id));
    }

    @PostMapping
    public ResponseEntity<Company> createCompany (@RequestBody @Valid CompanyDTO companyDto){
        Company company = new Company();
        BeanUtils.copyProperties(companyDto, company);
        return ResponseEntity.status(HttpStatus.OK).body(companyService.createCompany(company));
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity<String> deleteCompany (@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.deleteCompany(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Company> updateCompany (@PathVariable(value = "id") Long id, @RequestBody @Valid CompanyDTO companyDTO){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.updateCompany(id, companyDTO));
    }

}
