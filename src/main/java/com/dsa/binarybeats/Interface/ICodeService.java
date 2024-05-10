package com.dsa.binarybeats.Interface;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dsa.binarybeats.Entity.Code;
import com.dsa.binarybeats.Exceptions.CodeException;
import com.dsa.binarybeats.Request.CodeRequest;
import com.dsa.binarybeats.Response.CodeResponse;

public interface ICodeService {

    Code addCode(CodeRequest codeRequest) throws CodeException;

    void deleteCode(long codeId) throws CodeException;

    Code updateCode(long codeId, CodeRequest codeRequest) throws CodeException;

    List<Code> addMultipleCode(List<CodeRequest> codeRequests);

    Page<List<CodeResponse>> get_all_code(String topic, String sort, String difficulty, Integer page_no, Integer paage_size);
}
