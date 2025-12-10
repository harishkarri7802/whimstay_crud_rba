package com.whimstay.dto;
public record ApiResponse<T>(boolean success, String message, T data) {}