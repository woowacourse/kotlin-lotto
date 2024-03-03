package lottogame.model.generator

import lottogame.fixture.createLottoNumbers
import lottogame.fixture.createSuccessLottoResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefaultManualLottieGeneratorTest {
    @Test
    fun `LottoNumber 리스트로 LottoResult 리스트를 생성한다`() {
        // given
        val lottieNumbers =
            listOf(createLottoNumbers(1, 2, 3, 4, 5, 6))
        val generator = DefaultManualLottieGenerator()
        val expected = listOf(createSuccessLottoResult(1, 2, 3, 4, 5, 6))
        // when
        val actual = generator.generate(lottieNumbers)
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
