package manager;

import domain.Issue;
import repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {

    IssueRepository repository;
    private final List<Issue> items = new ArrayList<>();

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public List<Issue> issueIsOpen() {
        List<Issue> issues = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.isOpen()) {
                issues.add(item);
            }
        }
        return issues;

    }

    public List<Issue> issueIsClosed() {
        List<Issue> issues = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (!item.isOpen()) {
                issues.add(item);
            }
        }
        return issues;
    }

    public boolean openById(int id) {
        for (Issue item : items) {
            if (item.getId() == id && !item.isOpen()) {
                item.setOpen(true);
            }
        }
        return true;
    }

    public boolean closeById(int id) {
        for (Issue item : repository.findAll()) {
            if (item.getId() == id && item.isOpen()) {
                item.setOpen(false);
            }
        }
        return true;
    }

    public Collection<Issue> findAuthor(String text) {
        return filterBy(e -> e.getAuthor().equalsIgnoreCase(text));
    }

    public Collection<Issue> findLabel(String text) {
        return filterBy(e -> e.getLabel().contains(text));
    }

    public Collection<Issue> findAssignee(String text) {
        return filterBy(e -> e.getAssignee().contains(text));
    }

    private Collection<Issue> filterBy(Predicate<Issue> filter) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (filter.test(issue)) {
                result.add(issue);
            }
        }
        Collections.sort(result);
        return result;
    }
}