package com.patkayongo.gazette.list;

import com.patkayongo.gazette.domain.Gazette;
import com.patkayongo.gazette.services.GazetteSearchDelegate;
import com.patkayongo.gazette.services.GazetteService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class GazetteListPresenterTest {
    private GazetteService gazetteService;
    private GazetteListView gazetteListView;
    private GazetteListPresenter gazetteListPresenter;

    @Before
    public void setup() {
        this.gazetteService = Mockito.mock(GazetteService.class);
        this.gazetteListView = Mockito.mock(GazetteListView.class);
        this.gazetteListPresenter = new GazetteListPresenter(this.gazetteListView, this.gazetteService);
    }

    @Test
    public void shouldSearchForSpecifiedTerm() {
        Mockito.when(this.gazetteListView.getSearchTerm()).thenReturn("dogs");
        this.gazetteListPresenter.search();

        ArgumentCaptor<String> searchTermCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(this.gazetteService).searchGazettes(searchTermCaptor.capture(), Mockito.any(GazetteSearchDelegate.class));
        assertEquals("dogs", searchTermCaptor.getValue());
    }

    @Test
    public void shouldDisplayReturnedGazettes() {
        Mockito.when(this.gazetteListView.getSearchTerm()).thenReturn("dogs");

        List<Gazette> gazetteList = new ArrayList<>();
        gazetteList.add(new Gazette("title", "text", "sourceUrl"));
        gazetteList.add(new Gazette("title2", "text2", "sourceUrl2"));

        this.gazetteListPresenter.search();

        ArgumentCaptor<GazetteSearchDelegate> argumentCaptor = ArgumentCaptor.forClass(GazetteSearchDelegate.class);
        Mockito.verify(this.gazetteService).searchGazettes(Mockito.anyString(), argumentCaptor.capture());
        argumentCaptor.getValue().searchComplete(gazetteList);

        Mockito.verify(this.gazetteListView, Mockito.times(1)).displayGazettes(gazetteList);
    }
}