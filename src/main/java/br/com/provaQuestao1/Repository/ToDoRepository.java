package br.com.provaQuestao1.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.provaQuestao1.Entity.ToDo;
@Repository
public interface ToDoRepository extends CrudRepository <ToDo, Integer>{

	List<ToDo>findByName(String name);

	ToDo findById(int id);
	
}
