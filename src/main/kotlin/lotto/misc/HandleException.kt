package lotto.misc

fun tryAndRerun(fn: () -> Any): Any {
    return runCatching(fn).onSuccess {
        return it
    }.onFailure {
        println("잘못된 입력 : " + it.message)
        return tryAndRerun(fn)
    }
}
