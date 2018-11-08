package com.example.whiteboardfall2018serverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.models.YouTubeWidget;
import com.example.whiteboardfall2018serverjava.repositories.TopicRepository;
import com.example.whiteboardfall2018serverjava.repositories.YouTubeWidgetRepository;

@RestController
@CrossOrigin(origins="*")
public class YouTubeWidgetService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	YouTubeWidgetRepository youTubeWidgetRepository;
	@PostMapping("/api/topic/{topicId}/widget/youtube")
	public List<Widget> createYouTubeWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody YouTubeWidget youTubeWidget) {
		youTubeWidget.setWidgetType("YOU_TUBE");
		Topic topic = topicRepository.findById(topicId).get();
		youTubeWidget.setTopic(topic);
		youTubeWidget = youTubeWidgetRepository.save(youTubeWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
}
