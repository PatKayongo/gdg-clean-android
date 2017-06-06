package com.patkayongo.gazette.list;

import com.patkayongo.gazette.domain.Gazette;
import com.patkayongo.gazette.services.GazetteSearchDelegate;
import com.patkayongo.gazette.services.GazetteService;

import java.util.List;

public class GazetteListPresenter implements GazetteSearchDelegate {
    private final GazetteListView gazetteListView;
    private final GazetteService gazetteService;

    public GazetteListPresenter(GazetteListView gazetteListView, GazetteService gazetteService) {
        this.gazetteListView = gazetteListView;
        this.gazetteService = gazetteService;
    }

    public void search() {
        this.gazetteService.searchGazettes(this.gazetteListView.getSearchTerm(), this);
    }

    @Override
    public void searchComplete(List<Gazette> gazetteList) {
        this.gazetteListView.displayGazettes(gazetteList);
    }
}
