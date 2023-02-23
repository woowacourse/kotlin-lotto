package lotto.misc

fun <T> tryAndRerun(fn: () -> T): T {
    return runCatching(fn).onSuccess {
        return it
    }.getOrElse {
        println("잘못된 입력 : " + it.message)
        return tryAndRerun(fn)
    }
}
