package com.patkayongo.gazette.services;

public interface GazetteService {
    void searchGazettes(String searchTerm, GazetteSearchDelegate gazetteSearchDelegate);
}
