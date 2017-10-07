package com.pass.state.impl;

import com.pass.state.Event;
import com.pass.state.State;
import com.pass.state.StateContextInterface;
import com.pass.state.States;
import com.pass.utils.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashutosh on 07-10-2016.
 */
public class StateContext implements StateContextInterface {

    private static final Map<Pair<States, Event>, States> stateMap = new HashMap<>();

    static {
        stateMap.put(Pair.create(States.Draft, Event.Save), States.InReview);
        stateMap.put(Pair.create(States.Draft, Event.Exit), States.Draft);
        stateMap.put(Pair.create(States.InReview, Event.Approve), States.Queued);
        stateMap.put(Pair.create(States.InReview, Event.Reject), States.Completed);
        stateMap.put(Pair.create(States.Queued, Event.Publish), States.Published);
        stateMap.put(Pair.create(States.Queued, Event.Reject), States.Completed);
        stateMap.put(Pair.create(States.Published, Event.UnPublish), States.Completed);
        stateMap.put(Pair.create(States.Published, Event.Reject), States.Completed);
        stateMap.put(Pair.create(States.Completed, Event.Reopen), States.Queued);
        stateMap.put(Pair.create(States.Completed, Event.ReopenNow), States.Published);
    }

    @Override
    public State getNextState(States state, Event action) {
        Pair<States, Event> pair= Pair.create(state, action);
        return stateMap.get(pair).getState();
    }
}
