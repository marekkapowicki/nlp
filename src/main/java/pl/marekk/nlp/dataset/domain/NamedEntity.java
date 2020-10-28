package pl.marekk.nlp.dataset.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NamedEntity {
    private final int start;
    private final int end;
    private final String label;
}
