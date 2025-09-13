package ifsul.ads.hackathon.controller;

import ifsul.ads.hackathon.domain.dto.RemedioCadastroDTO;
import ifsul.ads.hackathon.domain.entity.Remedio;
import ifsul.ads.hackathon.service.RemedioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/remedio")
public class RemedioController {

	@Autowired
	private RemedioService remedioService;

	@PostMapping("/cadastrar")
	public ResponseEntity<String> cadastrarRemedio(@RequestBody RemedioCadastroDTO remedioDTO) {
		remedioService.cadastrarRemedio(remedioDTO);
		return ResponseEntity.ok("Remédio cadastrado com sucesso!");
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Remedio>> listarRemedios() {
		List<Remedio> remedios = remedioService.listarRemedios();
		return ResponseEntity.ok(remedios);
	}
}
