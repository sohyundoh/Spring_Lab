package org.practice.sse.exception

class NotFoundException(override val message : String) : RuntimeException(message) {
}