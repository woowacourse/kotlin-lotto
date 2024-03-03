package lottogame.model.generator

import lottogame.model.GeneralLottoNumber
import lottogame.model.Lotto
import lottogame.model.LottoResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefaultManualLottieGeneratorTest {
    @Test
    fun `LottoNumber 리스트로 LottoResult 리스트를 생성한다`() {
        // given
        val lottieNumbers =
            listOf(
                listOf(
                    GeneralLottoNumber(1),
                    GeneralLottoNumber(2),
                    GeneralLottoNumber(3),
                    GeneralLottoNumber(4),
                    GeneralLottoNumber(5),
                    GeneralLottoNumber(6),
                ),
            )
        val generator = DefaultManualLottieGenerator()
        val expected = listOf(LottoResult.Success(Lotto(1, 2, 3, 4, 5, 6)))
        // when
        val actual = generator.generate(lottieNumbers)
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
