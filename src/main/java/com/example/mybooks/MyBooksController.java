package com.example.mybooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Queue;

@RestController
@RequestMapping(path = "api/")
public class MyBooksController
{
    private final MyBooksService myBooksService;

    @Autowired
    public MyBooksController(MyBooksService myBooksService){
        this.myBooksService = myBooksService;
    }

    @GetMapping(path = "tasks/customer/{id}")
    public Queue<Request> getTaskByCustomerId(@PathVariable("id") String id){
        return myBooksService.getTaskByCustomerId(id);
    }

    @GetMapping(path = "tasks/expert/{id}")
    public Queue<Request> getTaskByExpertId(@PathVariable("id") String id){
        return myBooksService.getTaskByExpertId(id);
    }

    @PostMapping(path = "tasks/add/{id}")
    public Boolean addTask(@RequestBody Request request, @PathVariable String id)
    {
        return myBooksService.addTask(request,id);
    }

    @PutMapping(path = "tasks/finish/{id}")
    public List<String> finishTasks(@PathVariable String id)
    {
        return myBooksService.finishTasks(id);
    }

    @PutMapping("tasks/assign")
    public String assignTasks()
    {
        return myBooksService.assignTasks();
    }
}
