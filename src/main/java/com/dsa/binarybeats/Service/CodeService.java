package com.dsa.binarybeats.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dsa.binarybeats.Entity.Code;
import com.dsa.binarybeats.Entity.Difficulty_level;
import com.dsa.binarybeats.Exceptions.CodeException;
import com.dsa.binarybeats.Interface.ICodeService;
import com.dsa.binarybeats.Repository.CodeRepo;
import com.dsa.binarybeats.Repository.DifficultyRepo;
import com.dsa.binarybeats.Request.CodeRequest;
import com.dsa.binarybeats.Response.CodeResponse;

@Service
public class CodeService implements ICodeService {

    @Autowired
    DifficultyRepo difficultyRepo;

    @Autowired
    CodeRepo codeRepo;

    @Override
    public Code addCode(CodeRequest codeRequest) throws CodeException {
        Code code = new Code();
        Optional<Difficulty_level> difficulty = difficultyRepo.findById(codeRequest.getDifficulty_id());
        if (difficulty.isEmpty()) {
            throw new CodeException("Difficulty with given id not found");
        } else {
            code.setCode(codeRequest.getCode());
            code.setDescription(codeRequest.getDescription());
            code.setDifficulty(difficulty.get());
            code.setTopic(codeRequest.getTopic());
            code.setReferenceLink(codeRequest.getReferenceLink());
            code.setSolutionLink(codeRequest.getSolutionLink());
            codeRepo.save(code);
        }
        return code;
    }

    @Override
    public void deleteCode(long codeId) throws CodeException {
        Optional<Code> code = codeRepo.findById(codeId);
        if (code.isEmpty()) {
            throw new CodeException("Code with given id not found");
        } else {
            codeRepo.delete(code.get());
        }
    }

    @Override
    public Code updateCode(long codeId, CodeRequest codeRequest) throws CodeException {

        Optional<Difficulty_level> difficulty = difficultyRepo.findById(codeRequest.getDifficulty_id());
        if (difficulty.isEmpty()) {
            throw new CodeException("Difficulty with given id not found");
        }

        Optional<Code> code = codeRepo.findById(codeId);
        if (code.isEmpty()) {
            throw new CodeException("Difficulty with given id not found");
        }

        code.get().setCode(codeRequest.getCode());
        code.get().setDifficulty(difficulty.get());
        code.get().setDescription(codeRequest.getDescription());
        code.get().setTopic(codeRequest.getTopic());
        code.get().setReferenceLink(codeRequest.getReferenceLink());
        code.get().setSolutionLink(codeRequest.getSolutionLink());
        codeRepo.save(code.get());
        return code.get();

    }

    @Override
    public List<Code> addMultipleCode(List<CodeRequest> codeRequests) {
        for (CodeRequest codeRequest : codeRequests) {
            Optional<Difficulty_level> difficulty = difficultyRepo.findById(codeRequest.getDifficulty_id());
            if (difficulty.isEmpty()) {
                codeRequests.remove(codeRequest);
            }
        }
        List<Code> codes = new ArrayList<>();
        for (CodeRequest codeRequest : codeRequests) {
            Code code = new Code();
            code.setCode(codeRequest.getCode());
            code.setDifficulty(difficultyRepo.findById(codeRequest.getDifficulty_id()).get());
            code.setTopic(codeRequest.getTopic());
            code.setDescription(codeRequest.getDescription());
            code.setReferenceLink(codeRequest.getReferenceLink());
            code.setSolutionLink(codeRequest.getReferenceLink());
            codeRepo.save(code);
            codes.add(code);
        }
        return codes;

    }

    @Override
public Page<List<CodeResponse>> get_all_code(String topic, String sort, String difficulty, Integer page_no,
        Integer paage_size) {
    Pageable pageable = PageRequest.of(page_no, paage_size);
    List<Code> codes = codeRepo.filterAndSort(topic, difficulty, sort);
    int startIdx = (int) pageable.getOffset();
    int endIdx = Math.min(startIdx + pageable.getPageSize(), codes.size());
    List<Code> pageContent = codes.subList(startIdx, endIdx);
    Page<Code> filteredCodes = new PageImpl<>(pageContent, pageable, codes.size());
    
    List<List<CodeResponse>> codeResponsesList = new ArrayList<>();
    for (Code code : filteredCodes.getContent()) {
        CodeResponse codeResponse = new CodeResponse();
        codeResponse.setCode(code.getCode());
        codeResponse.setDescription(code.getDescription());
        codeResponse.setDifficulty_id(code.getDifficulty().getDifficulty_name());
        codeResponse.setTopic(code.getTopic());
        codeResponse.setReferenceLink(code.getReferenceLink());
        codeResponse.setSolutionLink(code.getSolutionLink());
        
        List<CodeResponse> singleResponseList = new ArrayList<>();
        singleResponseList.add(codeResponse);
        codeResponsesList.add(singleResponseList);
    }
    
    return new PageImpl<>(codeResponsesList, pageable, codes.size());
}


}
