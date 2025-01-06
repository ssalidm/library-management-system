package za.co.pixelly.lms.service;

import java.util.List;

import za.co.pixelly.lms.dao.PublisherDao;
import za.co.pixelly.lms.model.Publisher;

public class PublisherService {
 private final PublisherDao publisherDao;

    public PublisherService() {
        this.publisherDao = new PublisherDao();
    }

    public void savePublisher(String name) {
        Publisher publisher = new Publisher(name);
        publisherDao.savePublisher(publisher);
        System.out.println("Publisher '" + name + "' saved successfully!.");
    }

    public List<Publisher> getAllPublishers() {
        return publisherDao.getAllPublishers();
    }

    public Publisher getPublisherById(int publisherId) {
        return publisherDao.getPublisherById(publisherId);
    }
}
