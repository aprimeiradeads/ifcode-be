package ifsul.ads.hackathon.controller;

import ifsul.ads.hackathon.domain.dto.RemedioCadastroDTO;
import ifsul.ads.hackathon.domain.entity.Remedio;
import ifsul.ads.hackathon.service.RemedioService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/remedio")
public class RemedioController {

	@Autowired
	private RemedioService remedioService;

	@PostMapping("/cadastrar")
	public ResponseEntity<String> cadastrarRemedio(@RequestBody RemedioCadastroDTO remedioDTO) {
		System.out.println("-> Iniciando cadastro de remédio");
		String subject = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		remedioService.cadastrarRemedio(remedioDTO, subject);
		System.out.println("-> Remédio cadastrado com sucesso!");
		return ResponseEntity.ok("Remédio cadastrado com sucesso!");
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Remedio>> listarRemediosPorUsuario() {
		System.out.println("-> Iniciando listagem de remédios");
		String subject = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Remedio> remedios = remedioService.listarRemediosPorUsuario(subject);
		System.out.println("-> Listagem de remédios concluída com sucesso!");
		return ResponseEntity.ok(remedios);
	}

	@GetMapping("/listar/{remedioId}")
	public ResponseEntity<Remedio> obterRemedioPorId(@PathVariable UUID remedioId) {
		System.out.println("-> Iniciando obtenção de remédio por ID");
		String subject = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Remedio remedio = remedioService.obterRemedioPorId(subject, remedioId);
		System.out.println("-> Obtenção de remédio por ID concluída com sucesso! -> {" + remedio + "}");
		return ResponseEntity.ok(remedio);
	}

	@DeleteMapping("/{remedioId}")
	public ResponseEntity<String> deletarRemedio(@PathVariable UUID remedioId) {
		System.out.println("-> Iniciando deleção de remédio");
		String subject = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		remedioService.deletarRemedio(subject, remedioId);
		System.out.println("-> Deleção de remédio concluída com sucesso!");
		return ResponseEntity.ok("Remédio deletado com sucesso!");
	}

}
