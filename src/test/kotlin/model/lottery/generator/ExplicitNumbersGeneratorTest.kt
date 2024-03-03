package model.lottery.generator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExplicitNumbersGeneratorTest {
    @Test
    fun `명시적인 숫자 리스트를 정렬하여 숫자 리스트를 만든다`() {
        val numbersGenerator = ExplicitNumbersGenerator(listOf(1, 3, 2, 5, 6, 4))
        val actual = numbersGenerator.generate()
        assertThat(actual).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
    }
}
