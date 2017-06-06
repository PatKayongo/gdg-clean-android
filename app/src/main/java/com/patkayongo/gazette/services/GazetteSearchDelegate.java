package com.patkayongo.gazette.services;

import com.patkayongo.gazette.domain.Gazette;

import java.util.List;

public interface GazetteSearchDelegate {
    void searchComplete(List<Gazette> gazetteList);
}
