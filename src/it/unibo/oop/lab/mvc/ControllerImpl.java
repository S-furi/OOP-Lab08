package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    private String currentPrint;
    private final List<String> history = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextString(final String s) {
        if ("".equals(s)) {
            System.out.println("Cannot accept null arguments");
            throw new IllegalArgumentException();
        }
        this.currentPrint = s;
        this.history.add(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextString() {
        return this.currentPrint;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getHistory() {
        return this.history;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printString() {
        if (!"".equals(this.currentPrint)) {
            System.out.println(this.currentPrint);
        } else {
            System.out.println("String is unset ...");
            throw new IllegalStateException();
        }
    }

}
