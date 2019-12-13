package com.example.demo;

import java.util.Collections;
import java.util.List;

public class Record {

    private final List<String> columns;

    public Record(final List<String> columns) {
        this.columns = columns;
    }

    public List<String> getColumns() {
        return Collections.unmodifiableList(this.columns);
    }
}
