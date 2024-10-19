package com.ssafy.sandbox.paging.dto;

import java.sql.Timestamp;

public record Paging (Long id, String title, Timestamp createdAt) {
}
