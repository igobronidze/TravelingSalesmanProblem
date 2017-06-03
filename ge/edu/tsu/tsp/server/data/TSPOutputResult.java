package ge.edu.tsu.tsp.server.data;

public enum TSPOutputResult {

    SUCCESS {
        @Override
        public String toString() {
            return "წარმატებული";
        }
    },
    FAILED {
        @Override
        public String toString() {
            return "ჩავარდნილი";
        }
    },
    TIMEOUT {
        @Override
        public String toString() {
            return "დროის ლიმიტი";
        }
    }
}
