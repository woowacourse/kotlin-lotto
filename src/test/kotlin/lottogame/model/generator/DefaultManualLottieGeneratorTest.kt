package lottogame.model.generator

import lottogame.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefaultManualLottieGeneratorTest {
    @Test
    fun `Int 리스트의 리스트로 로또 리스트 생성한다`() {
        // given
        val lottieNumbers = listOf(listOf(1, 2, 3, 4, 5, 6))
        val generator = DefaultManualLottieGenerator()
        val expected = listOf(Lotto(1, 2, 3, 4, 5, 6))
        // when
        val actual = generator.generate(lottieNumbers)
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
