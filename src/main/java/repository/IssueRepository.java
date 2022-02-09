package repository;

import domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private final List<Issue> items = new ArrayList<>();

    public void save(Issue item ) {
        this.items.add(item);
    }

    public void remove(Issue item) {
        this.items.remove(item);
    }

    public List<Issue> findAll() {
        return items;
    }

    public void saveAll(List<? extends Issue> items) {
        this.items.addAll(items);
    }
}