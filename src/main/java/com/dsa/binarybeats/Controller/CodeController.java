package com.dsa.binarybeats.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsa.binarybeats.Entity.Code;
import com.dsa.binarybeats.Exceptions.CodeException;
import com.dsa.binarybeats.Interface.ICodeService;
import com.dsa.binarybeats.Request.CodeRequest;
import com.dsa.binarybeats.Response.CodeResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.domain.Page;


@RestController
@RequestMapping("/api/code")
public class CodeController {

    @Autowired
    ICodeService codeService;
    
    @PostMapping("/add")
    public ResponseEntity<Code> addCode(@RequestBody CodeRequest codeRequest) throws CodeException{
        Code code = codeService.addCode(codeRequest);
        if(code!=null){
            return new ResponseEntity<Code>(code,HttpStatus.CREATED);
        }
        else{
            throw new CodeException("Error adding a new code");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCode(@RequestParam Long id) throws CodeException {
        codeService.deleteCode(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Code> updateCode(@RequestBody CodeRequest codeRequest,@PathVariable Long id) throws CodeException{
        Code code = codeService.updateCode(id, codeRequest);
        if(code!=null){
            return new ResponseEntity<Code>(code,HttpStatus.ACCEPTED);
        }
        else{
            throw new CodeException("Error updating code");
        }
    }

    @PostMapping("/addMultiple")
    public ResponseEntity<List<Code>> addMultipleCode(@RequestBody List<CodeRequest> codeRequests) {
        List<Code> codes = codeService.addMultipleCode(codeRequests);
        return new ResponseEntity<>(codes,HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Page<List<CodeResponse>>> findCodessHandler(@RequestParam List<String> topic, @RequestParam String sort,@RequestParam List<String> difficulty,  @RequestParam Integer page_no, @RequestParam Integer paage_size) {
        Page<List<CodeResponse>> codes = codeService.get_all_code(topic, sort, difficulty, page_no, paage_size);
        return new ResponseEntity<>(codes, HttpStatus.OK);
    }
    
}
