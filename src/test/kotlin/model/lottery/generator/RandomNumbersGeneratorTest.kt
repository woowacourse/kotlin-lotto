package model.lottery.generator

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.assertDoesNotThrow

class RandomNumbersGeneratorTest {
    @RepeatedTest(50)
    fun `랜덤으로 숫자 리스트를 정렬하여 만든다`() {
        val randomNumbersGenerator = RandomNumbersGenerator(6, 1..45)
        assertDoesNotThrow { println(randomNumbersGenerator.generate()) }
    }
}
