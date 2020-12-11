package com.example.demo;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Lesson> findById(@PathVariable Long id) {
        return this.repository.findById(5L);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        this.repository.deleteById(5L);
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

}
