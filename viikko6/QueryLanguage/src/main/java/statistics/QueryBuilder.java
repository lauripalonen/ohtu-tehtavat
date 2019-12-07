package statistics;

import statistics.matcher.*;

import java.util.ArrayDeque;

public class QueryBuilder {
    Matcher matcher;

    public QueryBuilder() {
        matcher = new All();
    }

    public Matcher build() {
        Matcher returnable = this.matcher;
        this.matcher = new All();
        return returnable;
    }

    public QueryBuilder playsIn(String team){
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }


    public QueryBuilder hasAtLeast(int value, String category){
        this.matcher = new And(this.matcher, new HasAtLeast(value, category));
        return this;
    }


    public QueryBuilder and(Matcher... matchers){
        this.matcher = new And(this.matcher, new And(matchers));
        return this;
    }

    public QueryBuilder all(){
        this.matcher = new And(this.matcher, new All());
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category){
        this.matcher= new And(this.matcher, new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder not(Matcher matcher){
        this.matcher = new And(this.matcher, new Not(matcher));
        return this;
    }

    public QueryBuilder or(Matcher... matcher){
        this.matcher = new And(this.matcher, new Or(matcher));
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new And(this.matcher, new Or(matchers));
        return this;
    }


}
