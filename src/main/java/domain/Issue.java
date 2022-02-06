package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Issue implements Comparable<Issue> {
    private int id;
    private boolean isOpen;
    private String author;
    private int date;
    private Set<String> label = new HashSet<>();
    private Set<String> assignee = new HashSet<>();


    @Override
    public int compareTo(Issue o) {
        return this.date - o.date;
    }
}
