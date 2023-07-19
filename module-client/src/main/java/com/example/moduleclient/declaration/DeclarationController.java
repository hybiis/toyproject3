package com.example.moduleclient.declaration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.modulecore.util.ApiUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/declaration")
@RequiredArgsConstructor
public class DeclarationController {
	private final DeclarartionService declarartionService;

	@PutMapping("/insert")
	public ResponseEntity<?> saveDeclaration(@RequestBody DeclarationRequest.SaveDto saveReqDto) {
		DeclarationResponse.SaveDto saveRespDto = declarartionService.saveDeclarartion(saveReqDto);

		return ResponseEntity.ok().body(ApiUtil.success(saveRespDto));
	}
}
