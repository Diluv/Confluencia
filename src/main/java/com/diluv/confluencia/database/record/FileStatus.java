package com.diluv.confluencia.database.record;

public enum FileStatus {
    UPLOADING,
    PENDING,
    RUNNING,
    SUCCESS,
    FAILED_UNSPECIFIED,
    FAILED_INTERNAL_SERVER_ERROR,
    FAILED_MALWARE_DETECTED,
    FAILED_MALWARE_SCAN_TIMEOUT,
    FAILED_INVALID_FILE
}
