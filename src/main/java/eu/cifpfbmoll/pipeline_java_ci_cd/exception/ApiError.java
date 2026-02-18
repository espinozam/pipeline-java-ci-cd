package eu.cifpfbmoll.pipeline_java_ci_cd.exception;

import java.time.Instant;

public record ApiError(String message, Instant timestamp) {}
