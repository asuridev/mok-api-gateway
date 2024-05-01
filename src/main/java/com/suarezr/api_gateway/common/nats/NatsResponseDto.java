package com.suarezr.api_gateway.common.nats;

public record NatsResponseDto(boolean success, int statusCode, String errorMessage, String payload) {
}
