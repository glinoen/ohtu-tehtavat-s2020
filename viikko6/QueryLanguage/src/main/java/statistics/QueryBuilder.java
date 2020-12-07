/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import java.util.ArrayList;
import statistics.matcher.*;

/**
 *
 * @author julinden
 */
public class QueryBuilder {
    private ArrayList<Matcher> matchers;

    public QueryBuilder() {
        this.matchers = new ArrayList<>();
    }
     
    public Matcher build() {
       this.matchers.add(new All());
       Matcher matcher = new And(this.matchers.toArray(new Matcher[0]));
       this.matchers = new ArrayList<>();
       return matcher;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matchers.add(new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matchers.add(new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matchers.add(new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matchers.add(new Or(matchers));
        return this;
    }
}
