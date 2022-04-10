package com.smartFarm.was.web.logtrace;

public class TraceStatus {

    private TraceId traceId;
    private long startTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
