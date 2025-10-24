package dev.pavan.Todo.repository;

import dev.pavan.Todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD - Create Read Update Delete
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
