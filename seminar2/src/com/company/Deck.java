package com.company;
import java.util.Iterator;
public class Deck implements Iterable<SpielKarte> {
    private SpielKarte[] karten;
    Deck(SpielKarte[] karten) {
        this.karten = karten;
    }

    @Override
    public Iterator<SpielKarte> iterator() {
        return new DeckIterator();
    }

    class DeckIterator implements Iterator<SpielKarte> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            if (currentIndex >= karten.length)
                return false;
            return true;
        }

        @Override
        public SpielKarte next() {
            if (hasNext()) {
                //currentIndex += 1;
                return karten[currentIndex++];
            }
            return null;
        }
    }
}