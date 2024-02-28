package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FixedLottosGeneratorTest {
    @Test
    fun `구매할 로또는 중복이 허용되지 않습니다`() {
        val fixedNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(13, 14, 15, 16, 17, 18),
            )

        assertThrows<IllegalArgumentException> { FixedLottosGenerator(fixedNumbers) }
    }

    @Test
    fun `수동 구매 시 입력된 숫자들로 로또가 생성이 된다`() {
        val fixedNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(13, 14, 15, 16, 17, 18),
            )
        val fixedLottoNumbersGenerator = FixedLottosGenerator(fixedNumbers)
        val lottos = fixedLottoNumbersGenerator.generate(3)
        assertThat(lottos).isEqualTo(
            listOf(
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(7, 8, 9, 10, 11, 12),
                Lotto.of(13, 14, 15, 16, 17, 18),
            ),
        )
    }
}
