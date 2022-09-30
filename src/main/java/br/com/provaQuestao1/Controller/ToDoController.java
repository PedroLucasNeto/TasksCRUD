package br.com.provaQuestao1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.provaQuestao1.Entity.ToDo;
import br.com.provaQuestao1.Exceptions.GenericException;
import br.com.provaQuestao1.Service.ToDoService;

@RestController
@RequestMapping(value = "/toDo")
public class ToDoController {

	@Autowired
	private ToDoService toDoService;

	@GetMapping(value = "/lista/{name}")
	public ResponseEntity<List<ToDo>> buscaPeloNome(@PathVariable String name) {
		
		List<ToDo> listaRetorno = toDoService.listaPorNome(name);
		if (listaRetorno == null || listaRetorno.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ToDo>>(listaRetorno, HttpStatus.OK);
	}

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ToDo> buscaPeloId(@PathVariable int id) {
		
		ToDo toDo = toDoService.encontraPeloId(id);
		if (toDo == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ToDo>(toDo, HttpStatus.OK);
	}

	
	@GetMapping
	public ResponseEntity<List<ToDo>> buscaTodos() {
		
		List<ToDo> listaRetorno = toDoService.findAll();
		if (listaRetorno == null || listaRetorno.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ToDo>>(listaRetorno, HttpStatus.OK);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<ToDo> criaToDo(@RequestBody ToDo toDo) throws GenericException {
		try {
			toDoService.novoTodo(toDo);
		} catch (GenericException e) {
			e.Mensagem("Não foi possível criar uma tarefa");
		}
		return new ResponseEntity<ToDo>(toDo, HttpStatus.CREATED);
	}

	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ToDo> alteraToDo(@PathVariable int id, @RequestBody ToDo toDoAtt) throws GenericException {
		ToDo toDoNovo = toDoService.atualizaTodo(id, toDoAtt);
		return new ResponseEntity<ToDo>(toDoNovo, HttpStatus.OK);
	}
	

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ToDo> removeToDo(@PathVariable int id) {
		toDoService.removeToDo(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
