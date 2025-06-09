package BillionRow.data;

class Stats {
    int count = 0;
    double sum = 0, min = Double.MAX_VALUE, max = Double.MIN_VALUE;

    void add(double val) {
        sum += val;
        count++;
        min = Math.min(min, val);
        max = Math.max(max, val);
    }

    double avg() {
        return sum / count;
    }

    public String toString() {
        return String.format("%.1f/%.1f/%.1f", min, avg(), max);
    }

    public void merge(Stats other) {
        this.count += other.count;
        this.sum += other.sum;
        this.min = Math.min(this.min, other.min);
        this.max = Math.max(this.max, other.max);
    }

}
