package util

fun <T> retryWhileNull(action: () -> T): T {
    while (true) {
        val result = action() ?: continue
        return result
    }
}
