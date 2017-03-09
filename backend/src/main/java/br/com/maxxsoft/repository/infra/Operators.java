package br.com.maxxsoft.repository.infra;

public enum Operators {
    GTE(".$gte"),
    LT(".$lt"),
    IN(".$in") {
        @Override
        public boolean isSplit() {
            return true;
        }
    },
    IS(".$is"),
    PAGE("$page"),
    LIMIT("$limit"),
    ORDER_BY_ASC("$orderByAsc") {
        @Override
        public boolean isSplit() {
            return true;
        }
    },
    ORDER_BY_DESC("$orderByDesc") {
        @Override
        public boolean isSplit() {
            return true;
        }
    };

    private final String widcard;

    private Operators(String widcard) {
        this.widcard = widcard;
    }

    @Override
    public String toString() {
        return this.widcard;
    }

    public boolean isSplit() {
        return false;
    }
}
