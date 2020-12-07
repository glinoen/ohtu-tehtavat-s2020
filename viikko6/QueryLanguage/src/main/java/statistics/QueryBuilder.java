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
    private Matcher matcher;

    public QueryBuilder() {
        this.matcher = new All();
    }
     
    public Matcher build() {
       Matcher returnMatcher = this.matcher;
       this.matcher = new All();
       return returnMatcher;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(this.matcher ,new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(this.matcher,new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(this.matcher,new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    }
}
