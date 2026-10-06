package io.rahulnanhore.payload;

import lombok.Data;

import java.util.List;

@Data
public class SummarizeNotesRequest {
    private List<String> notes;
}
