package dev.pavan.io.HelloWorld;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @GetMapping("/get")
    String getTodo(){
        return "Todo";
    }

    //path variable
    @GetMapping("/{id}")
    String getTodoById(@PathVariable long id) {
        return "Todo with Id " + id;
    }

    //Request Param
    @GetMapping("")
    String getTodoByIdParam(@RequestParam("todoId") long id) {
        return "Todo with Id " + id;
    }

}
