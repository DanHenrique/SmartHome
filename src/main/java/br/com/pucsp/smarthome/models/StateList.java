package br.com.pucsp.smarthome.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StateList implements Serializable {
    public List<State> states = new ArrayList<>();
}
