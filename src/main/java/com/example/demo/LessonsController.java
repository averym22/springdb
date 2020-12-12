package com.example.demo;

import javassist.tools.rmi.ObjectNotFoundException;
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
    public Optional<Lesson> findByd(@PathVariable Long id) {
        return this.repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        this.repository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Lesson findByIdandUpdate(@PathVariable Long id, @RequestBody Lesson lessonDetails) throws ObjectNotFoundException {
        // find record from database and store locally

            Optional<Lesson> lesson = this.repository.findById(id);

            if(lesson.isPresent()) {
                Lesson lesson1 = lesson.get();
                lesson1.setTitle(lessonDetails.getTitle());
                lesson1.setDeliveredOn(lessonDetails.getDeliveredOn());
                return this.repository.save(lesson1);

            } else {
                throw new ObjectNotFoundException("Could not find lesson id");
            }


    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

}
