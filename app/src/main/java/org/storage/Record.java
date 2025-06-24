package org.storage;

public class Record {
    private final Object[] values;
    private final boolean[] isNull;
    private boolean isDeleted;
    private int visibilityId; // used for MVCC or transaction filtering
    private int version; // used for concurrency/version tracking

    public Record(Object[] values, boolean[] isNull) {
        this.values = values;
        this.isNull = isNull;
        this.isDeleted = false;
        this.visibilityId = 0;
        this.version = 1;
    }

    // === Accessors ===

    public Object[] getValues() {
        return values;
    }

    public boolean[] getNullFlags() {
        return isNull;
    }

    public boolean isNull(int index) {
        return isNull[index];
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void markDeleted() {
        this.isDeleted = true;
    }

    public int getVisibilityId() {
        return visibilityId;
    }

    public void setVisibilityId(int visibilityId) {
        this.visibilityId = visibilityId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    // === Convenience ===

    public int size() {
        return values.length;
    }

    public Object get(int index) {
        return values[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isDeleted)
            sb.append("[DELETED] ");
        sb.append("(");
        for (int i = 0; i < values.length; i++) {
            sb.append(isNull[i] ? "NULL" : values[i]);
            if (i < values.length - 1)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }
}
