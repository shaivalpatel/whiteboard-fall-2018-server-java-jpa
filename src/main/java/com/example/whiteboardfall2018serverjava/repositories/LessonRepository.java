package com.example.whiteboardfall2018serverjava.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.whiteboardfall2018serverjava.models.Lesson;

public interface LessonRepository
	extends CrudRepository<Lesson, Integer>{

}

/*
INSERT INTO lesson (title) VALUES ('Lesson 123');
INSERT INTO lesson (title) VALUES ('Lesson 234');
INSERT INTO topic (title, lesson_id) VALUES ('Topic AAA', 1);
INSERT INTO topic (title, lesson_id) VALUES ('Topic BBB', 1);
INSERT INTO widget (title, topic_id) VALUES ('Widget 111', 1);
INSERT INTO widget (title, topic_id) VALUES ('Widget 222', 1);
*/