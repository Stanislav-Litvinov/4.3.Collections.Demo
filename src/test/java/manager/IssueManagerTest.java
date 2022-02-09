package manager;

import domain.Issue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import repository.IssueRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class IssueManagerTest {
    static IssueRepository repo = new IssueRepository();
    IssueManager manager = new IssueManager(repo);

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

    @Nested
    public class Empty {

        @Test
        public void mustReturnEmptyWhenFindByAuthor() {
            Collection<Issue> expected = new ArrayList<>();
            Collection<Issue> actual = manager.findAuthor(String.valueOf(new HashSet<>(List.of("Yuliya"))));
            assertEquals(expected, actual);
        }

        @Test
        public void mustReturnEmptyWhenFindByLabel() {
            List<Issue> expected = new ArrayList<>();
            Collection<Issue> actual = manager.findLabel(String.valueOf(new HashSet<>(List.of("label1"))));
            assertEquals(expected, actual);
        }

        @Test
        public void mustReturnEmptyWhenFindByAssignee() {
            List<Issue> expected = new ArrayList<>();
            Collection<Issue> actual = manager.findAssignee(String.valueOf(new HashSet<>(List.of("peter"))));
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItem {

        @Test
        public void shouldFindOneLabel() {
            List<Issue> expected = List.of(issue6);
            Collection<Issue> actual = manager.findLabel("label7");

            assertEquals(expected, actual);
        }

        @Test
        void mustReturnEmptyIfNoLabel() {
            repo.save(issue1);
            Collection<Issue> expected = new ArrayList<>();
            Collection<Issue> actual = manager.findLabel(String.valueOf(new HashSet<>(List.of("label25"))));
            assertEquals(expected, actual);
        }


        @Test
        public void shouldFindOneAssignee() {
            List<Issue> expected = List.of(issue5);
            Collection<Issue> actual = manager.findAssignee("Ira");

            assertEquals(expected, actual);
        }

        @Test
        public void mustReturnEmptyIfNoAssignee() {
            repo.save(issue1);
            List<Issue> expected = new ArrayList<>();
            Collection<Issue> actual = manager.findAssignee(String.valueOf(new HashSet<>(List.of("VALERA"))));

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindOneAuthor() {
            List<Issue> expected = List.of(issue3);
            Collection<Issue> actual = manager.findAuthor("Me");

            assertEquals(expected, actual);
        }

        @Test
        public void mustReturnEmptyIfNoAuthor() {
            repo.save(issue1);
            Collection<Issue> expected = new ArrayList<>();
            Collection<Issue> actual = manager.findAuthor(String.valueOf(new HashSet<>(List.of("Bob"))));

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindCloseIssueById() {
            manager.closeById(2);

            assertFalse(issue2.isOpen());
        }

        @Test
        public void shouldFindOpenIssueById() {
            manager.openById(1);

            assertTrue(issue1.isOpen());
        }
    }

    @Nested
    public class MultipleItems {
        @Test
        public void shouldFindAllLabels() {
            List<Issue> expected = List.of(issue1, issue2, issue4, issue6);
            Collection<Issue> actual = manager.findLabel("label1");

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindAllAssignee() {
            List<Issue> expected = List.of(issue1, issue2);
            Collection<Issue> actual = manager.findAssignee("julia");

            assertEquals(expected, actual);
        }


        @Test
        public void shouldFindAllAuthors() {
            List<Issue> expected = List.of(issue5, issue6);
            Collection<Issue> actual = manager.findAuthor("Bob");

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindCloseIssues() {
            List<Issue> expected = List.of(issue2, issue4, issue6);
            List<Issue> actual = manager.issueIsClosed();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindOpenIssues() {
            List<Issue> expected = List.of(issue1, issue3, issue5);
            List<Issue> actual = manager.issueIsOpen();

            assertEquals(expected, actual);
        }

        @Test
        void mustReturnEmptyWhenFindByAssignee() {
            Collection<Issue> expected = new ArrayList<>();
            Collection<Issue> actual = manager.findAssignee(String.valueOf(new HashSet<>(List.of("julia"))));
            assertEquals(expected, actual);
        }
    }
}