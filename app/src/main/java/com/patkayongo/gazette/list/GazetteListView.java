package com.patkayongo.gazette.list;

import com.patkayongo.gazette.domain.Gazette;

import java.util.List;

public interface GazetteListView {
    String getSearchTerm();
    void displayGazettes(List<Gazette> gazetteList);
}
