package br.com.provaQuestao1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.provaQuestao1.Entity.ToDo;
import br.com.provaQuestao1.Exceptions.GenericException;
import br.com.provaQuestao1.Repository.ToDoRepository;

@Service
public class ToDoService {
	@Autowired
	ToDoRepository toDoRepository;

	public List<ToDo> listaPorNome(String name) {
		List<ToDo> list = toDoRepository.findByName(name);
		return list;
	}

	public ToDo encontraPeloId(int id) {
		ToDo toDo = toDoRepository.findById(id);
		if (toDo == null) {
			throw new GenericException("Não foi possível encontrar tarefa.");
		}
		return toDo;
	}

	public void novoTodo(ToDo toDo) {
		if (toDo.equals(null) || toDo.getId() != 0) {
			throw new GenericException("Não foi possível criar tarefa\nTente novamente.");
		}
		toDoRepository.save(toDo);
	}

	public ToDo atualizaTodo(int id, ToDo toDoAtt) throws GenericException {
		ToDo toDo = this.encontraPeloId(id);
		toDo.setComplete(toDoAtt.isComplete());
		toDo.setName(toDoAtt.getName());
		toDoRepository.save(toDo);
		return toDo;
	}

	public List<ToDo> findAll() {
		List<ToDo> list = (List<ToDo>) toDoRepository.findAll();
		if ( list == null || list.isEmpty()) {
			throw new GenericException("Não existe nenhuma tarefa.");
		}
		return list;
	}

	public void removeToDo(int id) {
		
		if (toDoRepository.findById(id) == null) {
			throw new GenericException("Tarefa a ser excluída não existe");
		}
		toDoRepository.deleteById(id);
	}

}
