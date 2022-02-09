package repository;

import domain.Issue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    static IssueRepository repo = new IssueRepository();

    static Issue issue1 = new Issue(1, true, "Yuliya", 3, new HashSet<>(Arrays.asList("label1", "label2", "label3")), new HashSet<>(Arrays.asList("julia", "mari", "peter")));
    static Issue issue2 = new Issue(2, false, "Oleg", 4, new HashSet<>(Arrays.asList("label1", "label2", "label5")), new HashSet<>(Arrays.asList("julia", "anton", "peter")));
    static Issue issue3 = new Issue(3, true, "Me", 5, new HashSet<>(Arrays.asList("label4", "label5", "label6")), new HashSet<>(Arrays.asList("bob", "lisa", "sophi")));
    static Issue issue4 = new Issue(4, false, "Serge", 6, new HashSet<>(Arrays.asList("label3", "label1", "label4")), new HashSet<>(Arrays.asList("mari", "lisa", "ani")));
    static Issue issue5 = new Issue(5, true, "Bob", 7, new HashSet<>(Arrays.asList("label6", "label5", "label3")), new HashSet<>(Arrays.asList("Ira", "Stas", "Oleg")));
    static Issue issue6 = new Issue(6, false, "Bob", 8, new HashSet<>(Arrays.asList("label1", "label2", "label7")), new HashSet<>(Arrays.asList("Stas", "Bob", "Serge")));

    @BeforeAll
    static void setUp() {
        repo.save(issue1);
        repo.save(issue2);
        repo.save(issue3);
        repo.save(issue4);
        repo.save(issue5);
        repo.save(issue6);
    }

    @Test
    public void shouldFindAll() {
        List<Issue> expected = List.of( issue2, issue3, issue4, issue5, issue6);
        Collection<Issue> actual = repo.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemove() {
        repo.remove(issue1);
        List<Issue> expected = List.of(issue2, issue3, issue4, issue5, issue6);
        Collection<Issue> actual = repo.findAll();

        assertEquals(expected, actual);
    }

}