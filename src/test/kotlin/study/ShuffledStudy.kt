package study

import org.junit.jupiter.api.Test

class ShuffledStudy {
    @Test
    fun `shuffled()의 동작 방식 확인`() {
        println((1..45).shuffled().subList(0, 6))
    }
}
