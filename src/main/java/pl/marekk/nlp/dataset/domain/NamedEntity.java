package pl.marekk.nlp.dataset.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NamedEntity {
    private final int start;
    private final int end;
    private final String label;
}
